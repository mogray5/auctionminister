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

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.PoLineData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SearchParams;
import com.auctionminister.providers.SQLMapProvider;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author wggray
 */
public class AddOpenPoLineAction extends BaseAction {

	private static final long serialVersionUID = -7335146806233755760L;
	private String isSecure = "S";
	
	private long poNumber = 0;
	private String itemId = "";
	private float purchPrice = 0;
	private int qtyPurch = 0;
	
	public AddOpenPoLineAction() {
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
				UserSmallData oUserID = this.verifyLogin();
				
				//check if item id is valid
				SearchParams search = new SearchParams();
				search.setItemId(itemId);
				search.setUserId(oUserID.getUserId());
				
				ItemData item = (ItemData)session.selectOne("GetItem", search);
				
				if (item==null){
					addActionError("Item (" + itemId + ") does not exist.  Click the back button in your browser to return to the purchase order");
					return ERROR;
				}
				
				PoLineData line = new PoLineData();
				line.setItemId(itemId);
				line.setPoNumber(poNumber);
				line.setPurchPrice(purchPrice);
				line.setQtyPurch(qtyPurch);
				
				session.update("DeleteOpenPoLine", line);
				session.update("AddOpenPoLine", line);
				session.commit();
				
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
}
