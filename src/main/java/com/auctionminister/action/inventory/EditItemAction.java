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
package com.auctionminister.action.inventory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AM;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AdjustItemParams;
import com.auctionminister.providers.SQLMapProvider;

/**
 * @author wggray
 */
public class EditItemAction extends BaseAction {

	private static final long serialVersionUID = -3817522541421418943L;
	private String sIsSecure = "S";
	private String sStatus = null;
	private int directive=0;
	private long userId = 0;
	private String itemId = "";
	private String itemDesc = "";
	private int itemTypeId = 1; //1=item, 2=lot/kit
	private long itemClassId=0;
	private double currentCost = 0;
	private double listPrice = 0;
	private double shippingWeight = 0;
	private int ivIndex = 0;
	private int offsetIndex = 0;
	private int returnsIndex = 0;
	private int cogsIndex = 0;
	private String binNumber = "";
	private int primaryVendor = 0;
	private Date lastOrderDate;
	private int lastOrderVendor = 0;
	private int isAssembly = 0;
	private int buildTime = 0;
	private int buildQty = 0;
	
	public EditItemAction() {
		super();
	}


	/**
	 * @return Returns the sIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}
	
	/**
	 * @param isSecure The sIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();

				userId = oUserID.getUserId();
				ItemData item=getItemInfo(session);
				
				
				if (directive==1){
					
					//item edit
					session.update("EditItem", item);
					session.commit();
					
				} else if (directive==2){
					
					//new item, check if name already exists
					ItemData search = (ItemData)session.selectOne("GetItem", item);
					if (search==null){
						//add item
						session.insert("AddItem", item);
					} else{
						//item already exists in database for this user, edit it
						session.update("EditItem", item);
					}
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
	
	private ItemData getItemInfo(SqlSession session) throws SQLException{
		
		ItemData id;
		AdjustItemParams aip = new AdjustItemParams();
		aip.setItemId(itemId);
		aip.setUserId(userId);
		id = (ItemData)session.selectOne("GetItem", aip);
		
		if (id==null){
			id = new ItemData();
		}
		
		id.setUserId(userId);
		id.setItemId(itemId);
		id.setBinNumber(binNumber);
		
		id.setCogsIndex(AM.COGS);
		id.setOffsetIndex(AM.OFFSET);
		id.setIvIndex(AM.INVENTORY);
		id.setReturnsIndex(AM.RETURNS_PURCH);
		
		GregorianCalendar gc = new GregorianCalendar();
		id.setCreateDate(gc.getTime());
		id.setCurrentCost(currentCost);
		id.setItemClassId(itemClassId);
		id.setItemDesc(itemDesc);
		id.setItemTypeId(itemTypeId);
		id.setListPrice(listPrice);
		id.setPrimaryVendor(primaryVendor);
		id.setShippingWeight(shippingWeight);
		id.setIsAssembly(isAssembly);
		id.setLastOrderDate(gc.getTime());
		id.setBuildTime(buildTime);
		id.setBuildQty(buildQty);		
		
		return id;
	}
	
	/**
	 * @return Returns the directive.
	 */
	public int getDirective() {
		return directive;
	}
	/**
	 * @param directive The directive to set.
	 */
	public void setDirective(int directive) {
		this.directive = directive;
	}
	
	
	/**
	 * @return Returns the binNumber.
	 */
	public String getBinNumber() {
		return binNumber;
	}
	/**
	 * @param binNumber The binNumber to set.
	 */
	public void setBinNumber(String binNumber) {
		this.binNumber = binNumber;
	}
	/**
	 * @return Returns the cogsIndex.
	 */
	public int getCogsIndex() {
		return cogsIndex;
	}
	/**
	 * @param cogsIndex The cogsIndex to set.
	 */
	public void setCogsIndex(int cogsIndex) {
		this.cogsIndex = cogsIndex;
	}
	/**
	 * @return Returns the currentCost.
	 */
	public double getCurrentCost() {
		return currentCost;
	}
	/**
	 * @param currentCost The currentCost to set.
	 */
	public void setCurrentCost(double currentCost) {
		this.currentCost = currentCost;
	}
	/**
	 * @return Returns the itemClassId.
	 */
	public long getItemClassId() {
		return itemClassId;
	}
	/**
	 * @param itemClassId The itemClassId to set.
	 */
	public void setItemClassId(long itemClassId) {
		this.itemClassId = itemClassId;
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
	 * @return Returns the itemTypeId.
	 */
	public int getItemTypeId() {
		return itemTypeId;
	}
	/**
	 * @param itemTypeId The itemTypeId to set.
	 */
	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	/**
	 * @return Returns the ivIndex.
	 */
	public int getIvIndex() {
		return ivIndex;
	}
	/**
	 * @param ivIndex The ivIndex to set.
	 */
	public void setIvIndex(int ivIndex) {
		this.ivIndex = ivIndex;
	}
	/**
	 * @return Returns the lastOrderDate.
	 */
	public Date getLastOrderDate() {
		return lastOrderDate;
	}
	/**
	 * @param lastOrderDate The lastOrderDate to set.
	 */
	public void setLastOrderDate(Date lastOrderDate) {
		this.lastOrderDate = lastOrderDate;
	}
	/**
	 * @return Returns the lastOrderVendor.
	 */
	public int getLastOrderVendor() {
		return lastOrderVendor;
	}
	/**
	 * @param lastOrderVendor The lastOrderVendor to set.
	 */
	public void setLastOrderVendor(int lastOrderVendor) {
		this.lastOrderVendor = lastOrderVendor;
	}
	/**
	 * @return Returns the listPrice.
	 */
	public double getListPrice() {
		return listPrice;
	}
	/**
	 * @param listPrice The listPrice to set.
	 */
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}
	/**
	 * @return Returns the offsetIndex.
	 */
	public int getOffsetIndex() {
		return offsetIndex;
	}
	/**
	 * @param offsetIndex The offsetIndex to set.
	 */
	public void setOffsetIndex(int offsetIndex) {
		this.offsetIndex = offsetIndex;
	}
	/**
	 * @return Returns the primaryVendor.
	 */
	public int getPrimaryVendor() {
		return primaryVendor;
	}
	/**
	 * @param primaryVendor The primaryVendor to set.
	 */
	public void setPrimaryVendor(int primaryVendor) {
		this.primaryVendor = primaryVendor;
	}
	/**
	 * @return Returns the returnsIndex.
	 */
	public int getReturnsIndex() {
		return returnsIndex;
	}
	/**
	 * @param returnsIndex The returnsIndex to set.
	 */
	public void setReturnsIndex(int returnsIndex) {
		this.returnsIndex = returnsIndex;
	}
	/**
	 * @return Returns the shippingWeight.
	 */
	public double getShippingWeight() {
		return shippingWeight;
	}
	/**
	 * @param shippingWeight The shippingWeight to set.
	 */
	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}
	/**
	 * @return Returns the sStatus.
	 */
	public String getStatus() {
		return sStatus;
	}
	/**
	 * @param status The sStatus to set.
	 */
	public void setStatus(String status) {
		sStatus = status;
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

	public int getIsAssembly() {
		return isAssembly;
	}

	public void setIsAssembly(int isAssembly) {
		this.isAssembly = isAssembly;
	}

	public int getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(int buildTime) {
		this.buildTime = buildTime;
	}

	public int getBuildQty() {
		return buildQty;
	}

	public void setBuildQty(int buildQty) {
		this.buildQty = buildQty;
	}
	
	
}
