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

import com.auctionminister.util.AmFormat;

public class ItemAssembly implements Serializable {

	private long userID = 0;
	private String itemID = "";
	private String elementItemID = "";
	private double elementQty = 0D;
	private long periodQty = 0L;
	private int buildTime = 0;
	
	private AmFormat format = new AmFormat();
	
	public ItemAssembly() {
		super();
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getElementItemID() {
		return elementItemID;
	}

	public void setElementItemID(String elementItemID) {
		this.elementItemID = elementItemID;
	}

	public double getElementQty() {
		return elementQty;
	}
	public String getElementQtyAsString() {
		return format.getRounded(elementQty);
	}
	public void setElementQty(double elementQty2) {
		this.elementQty = elementQty2;
	}

	public long getPeriodQty() {
		return periodQty;
	}

	public String getPeriodQtyAsString() {
		return format.getRounded(periodQty);
	}
	
	public void setPeriodQty(long periodQty) {
		this.periodQty = periodQty;
	}

	public int getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(int buildTime) {
		this.buildTime = buildTime;
	}
	
	
	
}
