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
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class EditOpenSoAction extends BaseAction {

	private static final long serialVersionUID = -2803811172027748844L;
	private String isSecure = "S";
	private long docId = 0;
	private long soNumber = 0;
	
	public EditOpenSoAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				
				//save any po's in edit first
				SaHeaderData search = new SaHeaderData();
				search.setUserId(oUserID.getUserId());
				search.setSaved(0);
				
				List soList = session.selectList("GetOpenSoHeaderListByStatus", search);
				
				if (soList.size()>0){
					search = (SaHeaderData)soList.get(0);
					search.setSaved(1);
					session.update("ToggleEditSoHeader", search);
					session.commit();
				}
				
				//open passed in po
				search.setDocId(docId);
				search.setSoNumber(soNumber);
				
				SaHeaderData so = (SaHeaderData)session.selectOne("GetOpenSoHeader", search);
				
				if (so !=null){
					so.setSaved(0);
					session.update("ToggleEditSoHeader", so);
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
	/**
	 * @return Returns the soNumber.
	 */
	public long getSoNumber() {
		return soNumber;
	}
	/**
	 * @param soNumber The soNumber to set.
	 */
	public void setSoNumber(long soNumber) {
		this.soNumber = soNumber;
	}
}
