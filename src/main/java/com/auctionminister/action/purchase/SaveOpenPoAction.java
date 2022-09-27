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

import java.util.Date;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.PoHeaderData;

/**
 * @author wggray
 */
public class SaveOpenPoAction extends BaseAction {

	private static final long serialVersionUID = -8084576040964667064L;
	private String isSecure = "S";
	private long docId = 0;
	private long poNumber = 0;
	private Date poDate;
	private int vendorId=0;
	
	public SaveOpenPoAction() {
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
				this.verifyLogin();
				PoHeaderData search = new PoHeaderData();
				search.setDocId(docId);
				search.setPoNumber(poNumber);
				search.setPoDate(poDate);
				search.setVendorId(vendorId);
				
				PoHeaderData po = (PoHeaderData)session.selectOne("GetOpenPoHeader", search);
				
				if (po !=null){
					po.setPoDate(poDate);
					po.setVendorId(vendorId);
					po.setSaved(1);
					session.update("EditOpenPoHeader", po);
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
	
	
	/**
	 * @return Returns the poDate.
	 */
	public Date getPoDate() {
		return poDate;
	}
	/**
	 * @param poDate The poDate to set.
	 */
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
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
