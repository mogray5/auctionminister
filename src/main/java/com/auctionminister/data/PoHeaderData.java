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
import java.io.Serializable;
import java.util.*;
import com.auctionminister.core.*;
import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoHeaderData implements Serializable {

	private String batchId = "";
	private long userId = 0;
	private long docId = 0;
	private long poNumber = 0;
	private int vendorId = 0;
	private String vendorName = "";
	private Date poDate;
	private int saved = 0;
	
	private AmFormat format = new AmFormat();
	
	/**
	 * 
	 */
	public PoHeaderData() {
		super();
		// TODO Auto-generated constructor stub
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
	
	
	/**
	 * @return Returns the vendorName.
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName The vendorName to set.
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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
	 * @return Returns the poDateString.
	 */
	public String getPoDateString() {
		try {
			return format.getFmtDate(poDate);
		} catch (Exception e){
			return "";
		}
	}
	/**
	 * @param poDateString The poDateString to set.
	 */
	public void setPoDateString(String poDateString) {
		
	}
}
