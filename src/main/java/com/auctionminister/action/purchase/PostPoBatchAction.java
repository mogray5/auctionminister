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
package com.auctionminister.action.purchase;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AM;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.PoLineData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.params.AdjustItemParams;

/**
 * @author wggray
 */
public class PostPoBatchAction extends BaseAction {

	private static final long serialVersionUID = 3634580700917164616L;
	private String isSecure = "S";
	private long docId = 0;
	private String docList = "";
	
	public PostPoBatchAction() {
		super();
	}

	/**
	 * @return Returns the isSecure.
	 */
	public String getIsSecure() {
		return isSecure;
	}
	/**
	 * @param isSecure The isSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		this.isSecure = isSecure;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				String[] aDocList;
				
				if (docList!=null && docList.length()>0){
					aDocList = docList.split(",");
				} else {
					addActionError("No documents found to post.");
					return ERROR;
				}

				GregorianCalendar gc = new GregorianCalendar();
				AccountParams account = new AccountParams();
				account.setUserId(oUserID.getUserId());
				account.setMonth(gc.get(Calendar.MONTH) + 1);
				account.setYear(gc.get(Calendar.YEAR));
				AdjustItemParams itemparams = new AdjustItemParams();
				List poList = null;
				List lineList = null;
				PoLineData line = null;
				PoHeaderData po = null;
				PoHeaderData search = null;
				ItemData item = null;
				
				for (int k=0; k<aDocList.length; k++){
				
					try {
						docId = new Long(aDocList[k].trim()).longValue();
					} catch (NumberFormatException nfe) {}
					
					search = new PoHeaderData();
					search.setUserId(oUserID.getUserId());
					search.setDocId(docId);
				
					poList = session.selectList("GetOpenPoHeaderList", search);
				
					if (poList.size()>0){
										
						for(int i=0; i<poList.size(); i++){
						
							po = (PoHeaderData)poList.get(i);
							po.setUserId(oUserID.getUserId());
							lineList = session.selectList("GetOpenPoLineList", po);
						
							for (int j=0; j<lineList.size(); j++){
							
								
								
								line = (PoLineData) lineList.get(j);
								itemparams.setItemId(line.getItemId());
								itemparams.setUserId(oUserID.getUserId());
								itemparams.setAdjustVal(line.getQtyPurch());
							
								session.update("AdjustOnHand", itemparams);
								
								item = (ItemData)session.selectOne("GetItem", itemparams);
								item.setUserId(oUserID.getUserId());
								
								
								
								item.setQtyLastOrder(line.getQtyPurch());
								item.setLastOrderDate(po.getPoDate());
								item.setLastOrderVendor(po.getVendorId());
								item.setCurrentCost(line.getPurchPrice());
								
								session.update("EditItem", item);
							
								account.setAdjustVal(1D * line.getQtyPurch() * line.getPurchPrice());
								account.setAccountIndex(AM.INVENTORY);
								session.update("DebitAccount", account);
								session.update("DebitMonthlyAccount", account);
								account.setAccountIndex(AM.CASH);
								session.update("CreditAccount", account);
								session.update("CreditMonthlyAccount", account);
							
							}
						
							session.update("MovePoLinesToHistory", po);
							session.update("DeleteOpenPoLines", Long.valueOf(po.getPoNumber()));
							session.update("MovePoHeaderToHistory", po);
							session.update("DeleteOpenPoHeader", po);
						
						}
					
						session.update("MovePoBatchToHistory", search);
						session.update("DeleteOpenPoBatchByDocId", search);
					
						session.commit();
					
					}
				}
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	
	
	/**
	 * @return Returns the docList.
	 */
	public String getDocList() {
		return docList;
	}
	/**
	 * @param docList The docList to set.
	 */
	public void setDocList(String docList) {
		this.docList = docList;
	}
}
