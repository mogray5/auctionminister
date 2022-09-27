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

import java.util.GregorianCalendar;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.PoBatchData;
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 *
 */
public class AddOpenPoAction extends BaseAction {

	private static final long serialVersionUID = 8739082586410573340L;
	private String itemId = "";
	private ItemData oItem = null;
	private String isSecure = "S";
	
	//form data
	private String batchId = "";
	private int vendorId = 0;
		
	public AddOpenPoAction() {
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
		
		Long docId = null;
		GregorianCalendar gc = new GregorianCalendar();
		
		this.startSession();
		
		try {	
			
				UserSmallData oUserID = this.verifyLogin();
				//make sure no unsaved po already exists
				PoHeaderData search = new PoHeaderData();
				search.setUserId(oUserID.getUserId());
				search.setSaved(0);
				
				PoHeaderData po = 
					(PoHeaderData) session.selectOne("GetOpenPoHeaderListByStatus", search);
			
				if (po==null){
					//check if passed batch already exists
					PoBatchData batchSearch = new PoBatchData();
					batchSearch.setBatchId(batchId);
					batchSearch.setUserId(oUserID.getUserId());
					PoBatchData batch = (PoBatchData) session.selectOne("GetOpenPoBatch", batchSearch);
					
					if (batch==null){
						//create a new batch
						
						batchSearch.setBatchDate(gc.getTime());
						session.update("AddOpenPoBatch", batchSearch);
						session.commit();
						docId = (Long)session.selectOne("GetLastOpenPoBatch", oUserID);
					} else {
						docId = Long.valueOf(batch.getDocId());
					}
					
					if (docId==null){
						addActionError("Error creating PO batch");
						return INPUT;
					}
					
					//now add header
					po = new PoHeaderData();
					po.setDocId(docId.longValue());
					po.setUserId(oUserID.getUserId());
					po.setVendorId(vendorId);
					po.setPoDate(gc.getTime());
					session.update("AddOpenPoHeader", po);
					session.commit();
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
	 * @return Returns the batchId.
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId The batchId to set.
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return Returns the vendorId.
	 */
	public int getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId The vendorId to set.
	 */
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
}
