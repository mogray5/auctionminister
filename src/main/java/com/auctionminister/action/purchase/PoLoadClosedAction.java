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
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class PoLoadClosedAction extends BaseAction {

	private static final long serialVersionUID = 2999914872124547531L;
	private String isSecure = "S";
	private PoHeaderData poHeader = null;
	private List poLines = null;
	private long docId = 0;
	private long poNumber = 0;
	
	public PoLoadClosedAction() {
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
						
			if (oUserID == null){
				addActionError("Login could not be verified.");
				return ERROR;
			}
				
			PoHeaderData search = new PoHeaderData();
			search.setUserId(oUserID.getUserId());
			search.setDocId(docId);
			search.setPoNumber(poNumber);
				
			poHeader = (PoHeaderData) session.selectOne("GetClosedPoLookupByNumber", search);
			//poHeader.setUserId(oUserID.getUserId());
			if (poHeader != null){
				//load lines
				poHeader.setUserId(oUserID.getUserId());
				poLines = session.selectList("GetClosedPoLineList", poHeader);
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
	 * @return Returns the poLines.
	 */
	public List getPoLines() {
		return poLines;
	}
	/**
	 * @param poLines The poLines to set.
	 */
	public void setPoLines(List poLines) {
		this.poLines = poLines;
	}
	
	/**
	 * @return Returns the poHeader.
	 */
	public PoHeaderData getPoHeader() {
		return poHeader;
	}
	/**
	 * @param poHeader The poHeader to set.
	 */
	public void setPoHeader(PoHeaderData poHeader) {
		this.poHeader = poHeader;
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
	 * @return Returns the poNumber.
	 */
	public long getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber The poNumber to set.
	 */
	public void setPoNumber(long poNumber) {
		this.poNumber = poNumber;
	}
}