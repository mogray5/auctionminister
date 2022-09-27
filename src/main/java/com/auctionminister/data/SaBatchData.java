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
public class SaBatchData implements Serializable {

	private long userId = 0;
	private long docId = 0;
	private String batchId = "";
	private Date batchDate;
	
	private AmFormat format = new AmFormat();
	
	/**
	 * 
	 */
	public SaBatchData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the batchDate.
	 */
	public Date getBatchDate() {
		return batchDate;
	}
	/**
	 * @param batchDate The batchDate to set.
	 */
	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
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
	 * @return Returns the batchDateString.
	 */
	public String getBatchDateString() {
		try {
			return format.getFmtDate(batchDate);
		} catch (Exception e){
			return "";
		}
	}
	/**
	 * @param batchDateString The batchDateString to set.
	 */
	public void setBatchDateString(String batchDateString) {
	}
}
