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
import com.auctionminister.providers.SQLMapProvider;

/**
 * @author wggray
 */
public class SaLoadAction extends BaseAction {

	private static final long serialVersionUID = 7450231768156411703L;
	private String isSecure = "S";
	private SaHeaderData soHeader = null;
	private List soLines = null;
	private long docId = 0;
	private long soNumber = 0;
	
	public SaLoadAction() {
		super();
	}

	public String execute() throws Exception {
		
		boolean useHistory = false;
		this.startSession();
		
		try {	
			
			UserSmallData oUserID = this.verifyLogin();
										
			SaHeaderData search = new SaHeaderData();
			search.setUserId(oUserID.getUserId());
			search.setDocId(docId);
			search.setSoNumber(soNumber);
				
			soHeader = (SaHeaderData) session.selectOne("GetOpenSoHeader", search);
			
			if (soHeader==null){
				//try closed orders
				soHeader = (SaHeaderData) session.selectOne("GetClosedSoHeader", search);
				useHistory = true;
			}
		
			if (soHeader != null){
				
				soHeader.setUserId(oUserID.getUserId());

				//load lines
				if (useHistory){
					soLines = session.selectList("GetClosedSoLineList", soHeader);
				} else {
					soLines = session.selectList("GetOpenSoLineList", soHeader);
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
	 * @return Returns the soHeader.
	 */
	public SaHeaderData getSoHeader() {
		return soHeader;
	}
	/**
	 * @param soHeader The soHeader to set.
	 */
	public void setSoHeader(SaHeaderData soHeader) {
		this.soHeader = soHeader;
	}
	/**
	 * @return Returns the soLines.
	 */
	public List getSoLines() {
		return soLines;
	}
	/**
	 * @param soLines The soLines to set.
	 */
	public void setSoLines(List soLines) {
		this.soLines = soLines;
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
