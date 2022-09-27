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
import com.auctionminister.core.*;
import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ItemData implements Serializable {

	private long lUserId = 0;
	private String sItemId = "";
	private String sItemDesc = "";
	private int iItemTypeId = 1; //1=item, 2=lot/kit
	private long lItemClassId=0;
	private String sItemClassName="";
	private double dCurrentCost = 0;
	private double dListPrice = 0;
	private double dShippingWeight = 0;
	private int iIvIndex = 0;
	private int iOffsetIndex = 0;
	private int iReturnsIndex = 0;
	private int iCogsIndex = 0;
	private Date dtCreateDate;
	private String sBinNumber = "";
	private int iPrimaryVendor = 0;
	private int iQtyLastOrder = 0;
	private int iQtyOnHand = 0;
	private int iQtyReturned = 0;
	private int iQtySold = 0;
	private Date dtLastOrderDate;
	private int iLastOrderVendor = 0;
	private String lastOrderVendorName = "";
	private int isAssembly = 0;
	private double buildCost = 0;
	private int buildTime = 0;
	private int buildQty = 0;
	
	private AmFormat format = new AmFormat();
	
	//private AmFormat format = new AmFormat();
	
	/**
	 * 
	 */
	public ItemData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the dCurrentCost.
	 */
	public double getCurrentCost() {
		return dCurrentCost;
	}
	
	public String getCurrentCostAsString() {
		return format.getRounded(dCurrentCost);
	}
	/**
	 * @param currentCost The dCurrentCost to set.
	 */
	public void setCurrentCost(double currentCost) {
		dCurrentCost = currentCost;
	}
	/**
	 * @return Returns the dListPrice.
	 */
	public double getListPrice() {
		return dListPrice;
	}
	public String getListPriceAsString() {
		return format.getRounded(dListPrice);
	}
	/**
	 * @param listPrice The dListPrice to set.
	 */
	public void setListPrice(double listPrice) {
		dListPrice = listPrice;
	}
	/**
	 * @return Returns the dListPrice.
	 */
	public String getMarkup() {
		
		double markup = 0; 
		
		if (buildCost > 0){
			markup = ((dListPrice-buildCost)/buildCost)*100D;
		} else{
			markup = ((dListPrice-dCurrentCost)/dCurrentCost)*100D;
		}
			
		
		if (markup > 1000 || markup < -1000){
			return null;
		} else {
			return format.getRounded(markup) + "%";
		}
	}
	/**
	 * @return Returns the dListPrice.
	 */
	public String getProfit() {
		
		if (buildCost>0){
			return format.getRounded(((dListPrice-buildCost) * iQtySold)/1000000D);	
		} else {
			return format.getRounded(((dListPrice-dCurrentCost) * iQtySold)/1000000D);
		}
		
	}
	/**
	 * @return Returns the dShippingWeight.
	 */
	public double getShippingWeight() {
		return dShippingWeight;
	}
	/**
	 * @param shippingWeight The dShippingWeight to set.
	 */
	public void setShippingWeight(double shippingWeight) {
		dShippingWeight = shippingWeight;
	}
	/**
	 * @return Returns the dtCreateDate.
	 */
	public Date getCreateDate() {
		return dtCreateDate;
	}
	/**
	 * @param dtCreateDate The dtCreateDate to set.
	 */
	public void setCreateDate(Date dtCreateDate) {
		this.dtCreateDate = dtCreateDate;
	}
	/**
	 * @return Returns the dtLastOrderDate.
	 */
	public Date getLastOrderDate() {
		return dtLastOrderDate;
	}
	/**
	 * @param dtLastOrderDate The dtLastOrderDate to set.
	 */
	public void setLastOrderDate(Date dtLastOrderDate) {
		this.dtLastOrderDate = dtLastOrderDate;
	}
	/**
	 * @return Returns the iCogsIndex.
	 */
	public int getCogsIndex() {
		return iCogsIndex;
	}
	/**
	 * @param cogsIndex The iCogsIndex to set.
	 */
	public void setCogsIndex(int cogsIndex) {
		iCogsIndex = cogsIndex;
	}
	/**
	 * @return Returns the iItemTypeId.
	 */
	public int getItemTypeId() {
		return iItemTypeId;
	}
	/**
	 * @param itemTypeId The iItemTypeId to set.
	 */
	public void setItemTypeId(int itemTypeId) {
		iItemTypeId = itemTypeId;
	}
	/**
	 * @return Returns the iIvIndex.
	 */
	public int getIvIndex() {
		return iIvIndex;
	}
	/**
	 * @param ivIndex The iIvIndex to set.
	 */
	public void setIvIndex(int ivIndex) {
		iIvIndex = ivIndex;
	}
	/**
	 * @return Returns the iLastOrderVendor.
	 */
	public int getLastOrderVendor() {
		return iLastOrderVendor;
	}
	/**
	 * @param lastOrderVendor The iLastOrderVendor to set.
	 */
	public void setLastOrderVendor(int lastOrderVendor) {
		iLastOrderVendor = lastOrderVendor;
	}
	/**
	 * @return Returns the iOffsetIndex.
	 */
	public int getOffsetIndex() {
		return iOffsetIndex;
	}
	/**
	 * @param offsetIndex The iOffsetIndex to set.
	 */
	public void setOffsetIndex(int offsetIndex) {
		iOffsetIndex = offsetIndex;
	}
	/**
	 * @return Returns the iPrimaryVendor.
	 */
	public int getPrimaryVendor() {
		return iPrimaryVendor;
	}
	/**
	 * @param primaryVendor The iPrimaryVendor to set.
	 */
	public void setPrimaryVendor(int primaryVendor) {
		iPrimaryVendor = primaryVendor;
	}
	/**
	 * @return Returns the iQtyLastOrder.
	 */
	public int getQtyLastOrder() {
		return iQtyLastOrder;
	}
	public String getQtyLastOrderAsString() {
		return format.getRounded(iQtyLastOrder*1D);
	}
	/**
	 * @param qtyLastOrder The iQtyLastOrder to set.
	 */
	public void setQtyLastOrder(int qtyLastOrder) {
		iQtyLastOrder = qtyLastOrder;
	}
	/**
	 * @return Returns the iQtyOnHand.
	 */
	public int getQtyOnHand() {
		return iQtyOnHand;
	}
	public String getQtyOnHandAsString() {
		return format.getRounded(iQtyOnHand*1D);
	}
	/**
	 * @param qtyOnHand The iQtyOnHand to set.
	 */
	public void setQtyOnHand(int qtyOnHand) {
		iQtyOnHand = qtyOnHand;
	}
	/**
	 * @return Returns the iQtyReturned.
	 */
	public int getQtyReturned() {
		return iQtyReturned;
	}
	/**
	 * @param qtyReturned The iQtyReturned to set.
	 */
	public void setQtyReturned(int qtyReturned) {
		iQtyReturned = qtyReturned;
	}
	/**
	 * @return Returns the iQtySold.
	 */
	public int getQtySold() {
		return iQtySold;
	}
	public String getQtySoldAsString() {
		return format.getRounded(iQtySold*1D);
	}
	/**
	 * @param qtySold The iQtySold to set.
	 */
	public void setQtySold(int qtySold) {
		iQtySold = qtySold;
	}
	/**
	 * @return Returns the iReturnsIndex.
	 */
	public int getReturnsIndex() {
		return iReturnsIndex;
	}
	/**
	 * @param returnsIndex The iReturnsIndex to set.
	 */
	public void setReturnsIndex(int returnsIndex) {
		iReturnsIndex = returnsIndex;
	}
	/**
	 * @return Returns the sBinNumber.
	 */
	public String getBinNumber() {
		return sBinNumber;
	}
	/**
	 * @param binNumber The sBinNumber to set.
	 */
	public void setBinNumber(String binNumber) {
		sBinNumber = binNumber;
	}
	/**
	 * @return Returns the sItemClass.
	 */
	public long getItemClassId() {
		return lItemClassId;
	}
	/**
	 * @param itemClass The sItemClass to set.
	 */
	public void setItemClassId(long itemClass) {
		lItemClassId = itemClass;
	}
	/**
	 * @return Returns the sItemDesc.
	 */
	public String getItemDesc() {
		return sItemDesc;
	}
	/**
	 * @param itemDesc The sItemDesc to set.
	 */
	public void setItemDesc(String itemDesc) {
		sItemDesc = itemDesc;
	}
	/**
	 * @return Returns the sItemId.
	 */
	public String getItemId() {
		return sItemId;
	}
	/**
	 * @param itemId The sItemId to set.
	 */
	public void setItemId(String itemId) {
		sItemId = itemId;
	}
	
	
	/**
	 * @return Returns the sItemClassName.
	 */
	public String getItemClassName() {
		return sItemClassName;
	}
	/**
	 * @param itemClassName The sItemClassName to set.
	 */
	public void setItemClassName(String itemClassName) {
		sItemClassName = itemClassName;
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
	
	
	public int getIsAssembly() {
		return isAssembly;
	}

	public void setIsAssembly(int isAssembly) {
		this.isAssembly = isAssembly;
	}

	/**
	 * @return Returns the lastOrderVendorName.
	 */
	public String getLastOrderVendorName() {
		return lastOrderVendorName;
	}
	/**
	 * @param lastOrderVendorName The lastOrderVendorName to set.
	 */
	public void setLastOrderVendorName(String lastOrderVendorName) {
		this.lastOrderVendorName = lastOrderVendorName;
	}
	
	
	/**
	 * @return Returns the lastOrderDateString.
	 */
	public String getLastOrderDateString() {
		try {
			
			return format.getFmtDate(dtLastOrderDate);
			
		}catch (Exception e){
			return "";
		}
	}
	/**
	 * @param lastOrderDateString The lastOrderDateString to set.
	 */
	public void setLastOrderDateString(String lastOrderDateString) {
		
	}
	
	
	/**
	 * @return Returns the createDateString.
	 */
	public String getCreateDateString() {
		try {
			return format.getFmtDate(dtCreateDate);
		} catch (Exception e){
			return "";
		}
	}
	
	
	/**
	 * @param createDateString The createDateString to set.
	 */
	public void setCreateDateString(String createDateString) {
	}

	public double getBuildCost() {
		return buildCost;
	}
	public String getBuildCostAsString() {
		return format.getRounded(buildCost);
	}
	public void setBuildCost(double buildCost) {
		this.buildCost = buildCost;
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
