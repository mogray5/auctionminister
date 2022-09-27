/*
 * Copyright (c) 2013 Wayne Gray All rights reserved
 * 
 * This file is part of Auction Minister.
 * 
 * Auction Minister is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Auction Minister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Auction Minister.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.auctionminister.core;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.auctionminister.data.CustomerData;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.SaLineData;
import com.auctionminister.data.UploadData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.exceptions.AmUploadException;
import com.auctionminister.params.SearchParams;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpApplySaleBatch {

	private long batchId = 0;
	private long userId = 0;
	private SqlSession session = null;
	
	
	private CustomerData customer = null;
	
	/**
	 * 
	 */
	public UpApplySaleBatch(SqlSession s, long batch, long user) {
		super();
		session = s;
		batchId=batch;
		userId=user;
	}
	
	public void Apply() throws AmUploadException {
	
			try {
				
				long docId=0;
				long currCustomer=0;
				
				AmSo soAdd = new AmSo();
				GregorianCalendar gc = new GregorianCalendar();
				SaHeaderData so = null;
				SaLineData sa = new SaLineData();
				UploadData tranline = null;
				UserSmallData user = new UserSmallData();
				user.setUserId(userId);
				SearchParams itemSearch = new SearchParams();
				ItemData item = null;
				//AmCharge charge = new AmCharge();
				
				UploadData search = new UploadData();
				search.setBatchId(batchId);
				search.setAmType(0);
				search.setUserId(userId);
				
				
				List saleList = session.selectList("GetBatchIncludeListByType", search);
				
				if (saleList != null && saleList.size()>0){
					for (int i=0; i< saleList.size(); i++){
												
						tranline = (UploadData)saleList.get(i);
						ResolveCustomer(tranline);
						
						if (customer==null){
							throw new AmUploadException("Error creating customer.");
						}
						
						if (currCustomer != customer.getCustomerId()){
							
							//start a new batch
							String sbatch = "Paypal_" + 
							Integer.toString(gc.get(Calendar.MONTH)+1) +
							"-" +
							Integer.toString(gc.get(Calendar.DAY_OF_MONTH)) +
							"-" +
							Integer.toString(gc.get(Calendar.YEAR)) +
							"-" +
							Integer.toString(gc.get(Calendar.MINUTE)) +
							Integer.toString(gc.get(Calendar.MILLISECOND)) +
							Integer.toString(i);
							
							docId = soAdd.AddBatch(sbatch, customer.getCustomerId(), 
									user, session);
							
						}
						
						if (tranline.getItemId() == null || tranline.getItemId().length()==0){
							//no item id error
							tranline.setAmStatus(3);
							tranline.setAmMsg("Missing Item Number.");
							session.update("EditTranStatus", tranline);
						} else {
							
							//add the line
							so = soAdd.AddHeader(session, user, 
									customer.getCustomerId(), docId, tranline.getGrossAmt());
							
							if (so==null){
								throw new AmUploadException("Error creating SO header.");
							}
							
							itemSearch.setItemId(tranline.getItemId());
							itemSearch.setUserId(userId);
							item = (ItemData)session.selectOne("GetItem", itemSearch);
							
							if (item==null){
								ItemData newItem = getItemInfo(tranline.getItemId());
								session.update("AddItem", newItem);
								item = newItem;
							}
							
							sa.setItemDesc(item.getItemDesc());
							sa.setItemId(item.getItemId());
							sa.setQtySold(tranline.getTmpQuantity());
							sa.setSalePrice(tranline.getTmpPrice());
							sa.setSoNumber(so.getSoNumber());
							sa.setEveTxnId(tranline.getTmpRefTxnId());
							
							
							soAdd.AddSoLine(session, sa);
							
							tranline.setAmStatus(2);
							tranline.setAmMsg("Transferred to Sales Order #:  " 
										+ Long.toString(so.getSoNumber()));
							session.update("EditTranStatus", tranline);
						}
						
						session.commit();
						
						currCustomer = customer.getCustomerId();
					}
				}
				
				
			} catch (Exception e){
				try {session.rollback();} catch (Exception ex){}
				e.printStackTrace();
				throw new AmUploadException("Sales Apply Error:" + e.getMessage());
				
			}
		
	}
	
	private void ResolveCustomer(UploadData tranline) throws AmUploadException{
		
		try {
		
			//try to find a customer id
			SearchParams custSearch = new SearchParams();
		
			custSearch.setSearchVal(tranline.getTmpName().trim());
			custSearch.setUserId(userId);
		
			List custList = session.selectList("GetCustomerListByName", custSearch);
		
			if (custList != null && custList.size()>0){
				//	customer found, use id
				customer = (CustomerData)custList.get(0);

			} else {
				//add the customer
				customer = new CustomerData();
				customer.setShippingAddress(tranline.getTmpShipAddress());
				customer.setCustName(tranline.getTmpName());
				customer.setBuyerId(tranline.getTmpBuyerId());
				customer.setUserId(userId);
				customer.setCustEmail(tranline.getTmpType());

				session.update("AddCustomer", customer);
				
				custList = session.selectList("GetCustomerListByName", custSearch);
				customer = (CustomerData)custList.get(0);
			}
			
			tranline.setCustomerId(customer.getCustomerId());
			
		} catch (Exception e){
			throw new AmUploadException("Error resolving customer:" + e.getMessage());
		}
	}
	
	
	/**
	 * @return Returns the batchId.
	 */
	public long getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId The batchId to set.
	 */
	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}
	
	private ItemData getItemInfo(String itemId){
		
		
		
		ItemData id = new ItemData();
		id.setUserId(userId);
		id.setItemId(itemId);
		id.setBinNumber("0");
		
		id.setCogsIndex(AM.COGS);
		id.setOffsetIndex(AM.OFFSET);
		id.setIvIndex(AM.INVENTORY);
		id.setReturnsIndex(AM.RETURNS_PURCH);
		
		GregorianCalendar gc = new GregorianCalendar();
		id.setCreateDate(gc.getTime());
		id.setCurrentCost(.01);
		id.setItemClassId(1);
		id.setItemDesc(itemId);
		id.setItemTypeId(1);
		id.setListPrice(10);
		id.setPrimaryVendor(1);
		id.setQtyLastOrder(0);
		id.setQtyOnHand(0);
		id.setQtyReturned(0);
		id.setQtySold(0);
		id.setShippingWeight(0.5);
		id.setLastOrderDate(gc.getTime());
		
		
		return id;
	}
	
}
