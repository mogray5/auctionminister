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
package com.auctionminister.action.sales;

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.SaBatchData;
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
*/
public class DeleteSoBatchAction extends BaseAction {

	private static final long serialVersionUID = -1177501371186167324L;
	private String isSecure = "S";
	private long docId = 0;
	
	public DeleteSoBatchAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				SaBatchData search = new SaBatchData();
				search.setUserId(oUserID.getUserId());
				search.setDocId(docId);
				
				List soList = session.selectList("GetOpenSoHeaderList", search);
				
				SaHeaderData so = null;
								
				if (soList.size()>0){
					
					for (int i=0; i<soList.size(); i++){
						so = (SaHeaderData)soList.get(i);
						session.update("DeleteOpenSoLines", Long.valueOf(so.getSoNumber()));
						session.update("DeleteOpenSoHeader", so);
					}
				}
				
				session.update("DeleteOpenSoBatchByDocId", search);
				session.commit();
				
		} catch (Exception e ) {
			session.rollback();
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	/**
	 * @return Returns the docId.
	 */
	public long getDocId() {
		return docId;
	}
	/**
	 * @param docId The docId to set.
	 */
	public void setDocId(long docId) {
		this.docId = docId;
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
}
