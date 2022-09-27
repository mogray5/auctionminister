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
package com.auctionminister.data;

import java.io.Serializable;
import java.util.Date;

import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaHeaderData implements Serializable {

	private String batchId = "";
	private long userId = 0;
	private long docId = 0;
	private long soNumber = 0;
	private long customerId = 0;
	private String custName = "";
	private Date soDate;
	private int saved = 0;
	private float paypalFee = 0;
	
	private AmFormat format = new AmFormat();
	
	/**
	 * 
	 */
	public SaHeaderData() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return Returns the custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName The custName to set.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return Returns the customerId.
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
	 * @return Returns the saved.
	 */
	public int getSaved() {
		return saved;
	}
	/**
	 * @param saved The saved to set.
	 */
	public void setSaved(int saved) {
		this.saved = saved;
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
	 * @return Returns the userId.
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
	/**
	 * @return Returns the soDateString.
	 */
	public String getSoDateString() {
		try {
			return format.getFmtDate(soDate);
		} catch (Exception e){
			return "";
		}
	}
	/**
	 * @param soDateString The soDateString to set.
	 */
	public void setSoDateString(String soDateString) {
		
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
}
