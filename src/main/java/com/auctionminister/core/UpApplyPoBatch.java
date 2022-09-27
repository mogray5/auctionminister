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

import com.auctionminister.data.ItemData;
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.PoLineData;
import com.auctionminister.data.UploadData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.data.VendorData;
import com.auctionminister.exceptions.AmUploadException;
import com.auctionminister.params.SearchParams;

public class UpApplyPoBatch {

	private long batchId = 0;
	private long userId = 0;
	private SqlSession session = null;
	
	
	private VendorData vendor = null;
	
	/**
	 * 
	 */
	public UpApplyPoBatch(SqlSession s, long batch, long user) {
		super();
		session = s;
		batchId=batch;
		userId=user;
	}
	
	public void Apply() throws AmUploadException {
	
			try {
				
				long docId=0;
				long currVendor=0;
				
				AmPo poAdd = new AmPo();
				GregorianCalendar gc = new GregorianCalendar();
				PoHeaderData po = null;
				PoLineData sa = new PoLineData();
				UploadData tranline = null;
				UserSmallData user = new UserSmallData();
				user.setUserId(userId);
				SearchParams itemSearch = new SearchParams();
				ItemData item = null;
				
				UploadData search = new UploadData();
				search.setBatchId(batchId);
				search.setAmType(1);
				search.setUserId(userId);
				
				
				List poList = session.selectList("GetBatchIncludeListByType", search);
				
				if (poList != null && poList.size()>0){
					for (int i=0; i< poList.size(); i++){
						
						tranline = (UploadData)poList.get(i);
						ResolveVendor(tranline);
						
						if (vendor==null){
							throw new AmUploadException("Error creating vendor.");
						}
						
						if (currVendor != vendor.getVendorId()){
							
							//start a new batch
							String sbatch = "eveWallet_" + 
							Integer.toString(gc.get(Calendar.MONTH)+1) +
							"-" +
							Integer.toString(gc.get(Calendar.DAY_OF_MONTH)) +
							"-" +
							Integer.toString(gc.get(Calendar.YEAR)) +
							"-" +
							Integer.toString(gc.get(Calendar.MINUTE)) +
							Integer.toString(gc.get(Calendar.MILLISECOND)) +
							Integer.toString(i);
							
							docId = poAdd.AddBatch(sbatch, vendor.getVendorId(), 
									user, session);
							
						}
						
						if (tranline.getItemId() == null || tranline.getItemId().length()==0){
							//no item id error
							tranline.setAmStatus(3);
							tranline.setAmMsg("Missing Item Number.");
							session.update("EditTranStatus", tranline);
						} else {
							
							//add the line
							po = poAdd.AddHeader(session, user, 
									vendor.getVendorId(), docId);
							
							if (po==null){
								throw new AmUploadException("Error creating PO header.");
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
							sa.setQtyPurch(tranline.getTmpQuantity());
							sa.setPurchPrice(tranline.getTmpPrice());
							sa.setPoNumber(po.getPoNumber());
							sa.setEveTxnId(tranline.getTmpRefTxnId());
							
							poAdd.AddPoLine(session, sa);
							
							tranline.setAmStatus(2);
							tranline.setAmMsg("Transferred to Purchase Order #:  " 
										+ Long.toString(po.getPoNumber()));
							session.update("EditTranStatus", tranline);
						}
						
						session.commit();
						currVendor = vendor.getVendorId();
					}
				}
				
				
			} catch (Exception e){
				try {session.rollback();} catch (Exception ex){}
				e.printStackTrace();
				throw new AmUploadException("Sales Apply Error:" + e.getMessage());
			}
		
	}
	
	private void ResolveVendor(UploadData tranline) throws AmUploadException{
		
		try {
		
			//try to find a customer id
			SearchParams vendSearch = new SearchParams();
		
			vendSearch.setSearchVal(tranline.getTmpName().trim());
			vendSearch.setUserId(userId);
		
			List vendList = session.selectList("GetVendorListByName", vendSearch);
		
			if (vendList != null && vendList.size()>0){
				//	customer found, use id
				vendor = (VendorData)vendList.get(0);

			} else {
				//add the customer
				vendor = new VendorData();
				vendor.setAddress1(tranline.getTmpShipAddress());
				vendor.setVendorName(tranline.getTmpName());
				vendor.setStatus(1);
				vendor.setUserId(userId);

				session.update("AddVendor", vendor);
				
				//get the vendor again
				vendList = session.selectList("GetVendorListByName", vendSearch);
				vendor = (VendorData)vendList.get(0);
			}
			
			if (vendList != null && vendList.size()>0){
				
				tranline.setVendorId(vendor.getVendorId());
			}
			
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
