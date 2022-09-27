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

import java.util.Date;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.SaHeaderData;

/**
 * @author wggray
 */
public class SaveOpenSoAction extends BaseAction {

	
	private static final long serialVersionUID = -5672945593688114992L;
	private String isSecure = "S";

	private long docId = 0;
	private long soNumber = 0;
	private float paypalFee=0;
	private Date soDate;
	private int customerId=0;
	
	public SaveOpenSoAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
			
				this.verifyLogin();
						
				SaHeaderData search = new SaHeaderData();
				search.setDocId(docId);
				search.setSoNumber(soNumber);
				
				SaHeaderData so = (SaHeaderData)session.selectOne("GetOpenSoHeader", search);
				
				if (so !=null){
					so.setSaved(1);
					so.setCustomerId(customerId);
					so.setPaypalFee(paypalFee);
					so.setSoDate(soDate);
					session.update("EditOpenSoHeader", so);
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
	
	
	
	/**
	 * @return Returns the customerId.
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return Returns the paypalFee.
	 */
	public float getPaypalFee() {
		return paypalFee;
	}
	/**
	 * @param paypalFee The paypalFee to set.
	 */
	public void setPaypalFee(float paypalFee) {
		this.paypalFee = paypalFee;
	}
	/**
	 * @return Returns the soDate.
	 */
	public Date getSoDate() {
		return soDate;
	}
	/**
	 * @param soDate The soDate to set.
	 */
	public void setSoDate(Date soDate) {
		this.soDate = soDate;
	}
}
