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
package com.auctionminister.params;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UploadParams implements Serializable {

	private long lUserId = 0;
	private Date dtUpDate = null;
	private long batchId = 0;
	private int amType=-1;
	private long trxIndex = 0;
	
	
	/**
	 * 
	 */
	public UploadParams() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the dtUpDate.
	 */
	public Date getUpDate() {
		return dtUpDate;
	}
	/**
	 * @param dtUpDate The dtUpDate to set.
	 */
	public void setUpDate(Date dtUpDate) {
		this.dtUpDate = dtUpDate;
	}
	/**
	 * @return Returns the lUserId.
	 */
	public long getUserId() {
		return lUserId;
	}
	/**
	 * @param userId The lUserId to set.
	 */
	public void setUserId(long userId) {
		lUserId = userId;
	}
	
	
	/**
	 * @return Returns the batchId.
	 */
	public long getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId The batchId to set.
	 */
	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}
	
	/**
	 * @return Returns the amType.
	 */
	public int getAmType() {
		return amType;
	}
	/**
	 * @param amType The amType to set.
	 */
	public void setAmType(int amType) {
		this.amType = amType;
	}
	
	
	/**
	 * @return Returns the trxIndex.
	 */
	public long getTrxIndex() {
		return trxIndex;
	}
	/**
	 * @param trxIndex The trxIndex to set.
	 */
	public void setTrxIndex(long trxIndex) {
		this.trxIndex = trxIndex;
	}
}
