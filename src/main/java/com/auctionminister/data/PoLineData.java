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

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoLineData implements Serializable {

	private long poNumber = 0;
	private String itemId = "";
	private String itemDesc = "";
	private float purchPrice = 0;
	private int qtyPurch = 0;
	private String eveTxnId = "";
	
	/**
	 * 
	 */
	public PoLineData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the itemDesc.
	 */
	public String getItemDesc() {
		return itemDesc;
	}
	/**
	 * @param itemDesc The itemDesc to set.
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	/**
	 * @return Returns the itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	 * @return Returns the purchPrice.
	 */
	public float getPurchPrice() {
		return purchPrice;
	}
	/**
	 * @param purchPrice The purchPrice to set.
	 */
	public void setPurchPrice(float purchPrice) {
		this.purchPrice = purchPrice;
	}
	/**
	 * @return Returns the qtyPurch.
	 */
	public int getQtyPurch() {
		return qtyPurch;
	}
	/**
	 * @param qtyPurch The qtyPurch to set.
	 */
	public void setQtyPurch(int qtyPurch) {
		this.qtyPurch = qtyPurch;
	}

	public String getEveTxnId() {
		return eveTxnId;
	}

	public void setEveTxnId(String eveTxnId) {
		this.eveTxnId = eveTxnId;
	}
	
	
}
