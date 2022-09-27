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
public class SaLineData implements Serializable {

	private long soNumber = 0;
	private String itemId = "";
	private String itemDesc = "";
	private float salePrice = 0;
	private int qtySold = 0;
	private String eveTxnId = "";
	
	/**
	 * 
	 */
	public SaLineData() {
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
	 * @return Returns the qtySold.
	 */
	public int getQtySold() {
		return qtySold;
	}
	/**
	 * @param qtySold The qtySold to set.
	 */
	public void setQtySold(int qtySold) {
		this.qtySold = qtySold;
	}
	/**
	 * @return Returns the salePrice.
	 */
	public float getSalePrice() {
		return salePrice;
	}
	/**
	 * @param salePrice The salePrice to set.
	 */
	public void setSalePrice(float salePrice) {
		this.salePrice = salePrice;
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



	public String getEveTxnId() {
		return eveTxnId;
	}



	public void setEveTxnId(String eveTxnId) {
		this.eveTxnId = eveTxnId;
	}

	
}
