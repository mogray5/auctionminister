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

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.PoBatchData;
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class DeletePoBatchAction extends BaseAction {

	private static final long serialVersionUID = 8694698847738542818L;
	private String isSecure = "S";

	private long docId = 0;
	
	public DeletePoBatchAction() {
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
				PoBatchData search = new PoBatchData();
				search.setUserId(oUserID.getUserId());
				search.setDocId(docId);
				
				List poList = session.selectList("GetOpenPoHeaderList", search);
				
				PoHeaderData po = null;
								
				if (poList.size()>0){
					
					for (int i=0; i<poList.size(); i++){
						po = (PoHeaderData)poList.get(i);
						session.update("DeleteOpenPoLines", Long.valueOf(po.getPoNumber()));
						session.update("DeleteOpenPoHeader", po);
					}
				}
				
				session.update("DeleteOpenPoBatchByDocId", search);
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
}
