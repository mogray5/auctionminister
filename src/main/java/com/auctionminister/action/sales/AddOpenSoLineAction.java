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

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AmSo;
import com.auctionminister.data.SaLineData;

/**
 * @author wggray
 */
public class AddOpenSoLineAction extends BaseAction {

	private static final long serialVersionUID = -5297532850101813198L;
	private String isSecure = "S";	
	private long soNumber = 0;
	private String itemId = "";
	private float salePrice = 0;
	private int qtySold = 0;
	
	/**
	 * 
	 */
	public AddOpenSoLineAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				this.verifyLogin();
				SaLineData line = new SaLineData();
				line.setItemId(itemId);
				line.setSoNumber(soNumber);
				line.setSalePrice(salePrice);
				line.setQtySold(qtySold);
				
				AmSo so = new AmSo();
				so.AddSoLine(session, line);
				
		} catch (Exception e ) {
			session.rollback();
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
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
	
	
}
