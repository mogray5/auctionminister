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
public class UploadData implements Serializable {

	long lBatchId = -1;
	long lTrxIndex = -1;
	Date dtTmpDate = null;
	String sTmpName = "";
	String sTmpType = "";
	String sTmpSubject = "";
	float dTmpPrice = 0;
	int iTmpQuantity = 0;
	String sTmpToEmail = "";
	String sTmpShipAddress ="";
	String sTmpItemTitle = "";
	String sTmpItemId = "";
	float fTmpShipAmt = 0;
	float fTmpInsuranceAmt = 0;
	float fTmpTaxAmt = 0;
	String sTmpBuyerId = "";
	String sTmpItemUrl = "";
	String sTmpRefTxnId = "";
	int amInclude = 0;
	int amType=0;
	long soNumber = 0;
	long poNumber = 0;
	long customerId=0;
	int vendorId=0;
	String itemId = "";
	int amStatus = 0;
	String amMsg = null;
	long userId = 0;
	
	private AmFormat format = new AmFormat();
	
	/**
	 * 
	 */
	public UploadData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the dTmpGross.
	 */
	public float getTmpPrice() {
		return dTmpPrice;
	}
	/**
	 * @param tmpGross The dTmpGross to set.
	 */
	public void setTmpPrice(float tmpPrice) {
		dTmpPrice = tmpPrice;
	}
	/**
	 * @return Returns the dtTmpDate.
	 */
	public Date getTmpDate() {
		return dtTmpDate;
	}
	/**
	 * @param dtTmpDate The dtTmpDate to set.
	 */
	public void setTmpDate(Date dtDate) {
		this.dtTmpDate = dtDate;
	}
	/**
	 * @return Returns the fTmpInsuranceAmt.
	 */
	public float getTmpInsuranceAmt() {
		return fTmpInsuranceAmt;
	}
	/**
	 * @param tmpInsuranceAmt The fTmpInsuranceAmt to set.
	 */
	public void setTmpInsuranceAmt(float tmpInsuranceAmt) {
		fTmpInsuranceAmt = tmpInsuranceAmt;
	}
	/**
	 * @return Returns the fTmpShipAmt.
	 */
	public float getTmpShipAmt() {
		return fTmpShipAmt;
	}
	/**
	 * @param tmpShipAmt The fTmpShipAmt to set.
	 */
	public void setTmpShipAmt(float tmpShipAmt) {
		fTmpShipAmt = tmpShipAmt;
	}
	/**
	 * @return Returns the fTmpTaxAmt.
	 */
	public float getTmpTaxAmt() {
		return fTmpTaxAmt;
	}
	/**
	 * @param tmpTaxAmt The fTmpTaxAmt to set.
	 */
	public void setTmpTaxAmt(float tmpTaxAmt) {
		fTmpTaxAmt = tmpTaxAmt;
	}
	/**
	 * @return Returns the lBatchId.
	 */
	public long getBatchId() {
		return lBatchId;
	}
	/**
	 * @param batchId The lBatchId to set.
	 */
	public void setBatchId(long batchId) {
		lBatchId = batchId;
	}
	/**
	 * @return Returns the sShipAddress.
	 */
	public String getTmpShipAddress() {
		return sTmpShipAddress;
	}
	/**
	 * @param shipAddress The sShipAddress to set.
	 */
	public void setTmpShipAddress(String shipAddress) {
		sTmpShipAddress = shipAddress;
	}
	/**
	 * @return Returns the sTmpBuyerId.
	 */
	public String getTmpBuyerId() {
		return sTmpBuyerId;
	}
	/**
	 * @param tmpBuyerId The sTmpBuyerId to set.
	 */
	public void setTmpBuyerId(String tmpBuyerId) {
		sTmpBuyerId = tmpBuyerId;
	}
	/**
	 * @return Returns the sTmpItemId.
	 */
	public String getTmpItemId() {
		return sTmpItemId;
	}
	/**
	 * @param tmpItemId The sTmpItemId to set.
	 */
	public void setTmpItemId(String tmpItemId) {
		sTmpItemId = tmpItemId;
	}
	/**
	 * @return Returns the sTmpItemTitle.
	 */
	public String getTmpItemTitle() {
		return sTmpItemTitle;
	}
	/**
	 * @param tmpItemTitle The sTmpItemTitle to set.
	 */
	public void setTmpItemTitle(String tmpItemTitle) {
		sTmpItemTitle = tmpItemTitle;
	}
	/**
	 * @return Returns the sTmpItemUrl.
	 */
	public String getTmpItemUrl() {
		return sTmpItemUrl;
	}
	/**
	 * @param tmpItemUrl The sTmpItemUrl to set.
	 */
	public void setTmpItemUrl(String tmpItemUrl) {
		sTmpItemUrl = tmpItemUrl;
	}
	/**
	 * @return Returns the sTmpName.
	 */
	public String getTmpName() {
		return sTmpName;
	}
	/**
	 * @param tmpName The sTmpName to set.
	 */
	public void setTmpName(String tmpName) {
		sTmpName = tmpName;
	}
	/**
	 * @return Returns the sTmpRefTxnId.
	 */
	public String getTmpRefTxnId() {
		return sTmpRefTxnId;
	}
	/**
	 * @param tmpRefTxnId The sTmpRefTxnId to set.
	 */
	public void setTmpRefTxnId(String tmpRefTxnId) {
		sTmpRefTxnId = tmpRefTxnId;
	}
	/**
	 * @return Returns the sTmpSubject.
	 */
	public String getTmpSubject() {
		return sTmpSubject;
	}
	/**
	 * @param tmpSubject The sTmpSubject to set.
	 */
	public void setTmpSubject(String tmpSubject) {
		sTmpSubject = tmpSubject;
	}
	/**
	 * @return Returns the sTmpToEmail.
	 */
	public String getTmpToEmail() {
		return sTmpToEmail;
	}
	/**
	 * @param tmpToEmail The sTmpToEmail to set.
	 */
	public void setTmpToEmail(String tmpToEmail) {
		sTmpToEmail = tmpToEmail;
	}
	/**
	 * @return Returns the sTmpType.
	 */
	public String getTmpType() {
		return sTmpType;
	}
	/**
	 * @param tmpType The sTmpType to set.
	 */
	public void setTmpType(String tmpType) {
		sTmpType = tmpType;
	}
	
	
	/**
	 * @return Returns the fTmpFee.
	 */
	public int getTmpQuantity() {
		return iTmpQuantity;
	}
	/**
	 * @param tmpFee The fTmpFee to set.
	 */
	public void setTmpQuantity(int tmpQuantity) {
		iTmpQuantity = tmpQuantity;
	}
	
	/**
	 * @return Returns the lTrxIndex.
	 */
	public long getTrxIndex() {
		return lTrxIndex;
	}
	/**
	 * @param trxIndex The lTrxIndex to set.
	 */
	public void setTrxIndex(long trxIndex) {
		lTrxIndex = trxIndex;
	}
	
	
	/**
	 * @return Returns the amInclude.
	 */
	public int getAmInclude() {
		return amInclude;
	}
	/**
	 * @param amInclude The amInclude to set.
	 */
	public void setAmInclude(int val) {
		amInclude = val;
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
	public void setAmType(int val) {
		amType = val;
	}
	
	
	/**
	 * @return Returns the customerId.
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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
	 * @return Returns the tmpDateString.
	 */
	public String getTmpDateString() {
		return format.getFmtDate(dtTmpDate);
	}
	/**
	 * @param tmpDateString The tmpDateString to set.
	 */
	public void setTmpDateString(String tmpDateString) {
	
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
	 * @return Returns the netAmt.
	 */
	public float getGrossAmt() {
		return new 
			Float(dTmpPrice * 
					iTmpQuantity).floatValue();
	}


	/**
	 * @return Returns the amMsg.
	 */
	public String getAmMsg() {
		return amMsg;
	}
	/**
	 * @param amMsg The amMsg to set.
	 */
	public void setAmMsg(String amMsg) {
		this.amMsg = amMsg;
	}
	/**
	 * @return Returns the amStatus.
	 */
	public int getAmStatus() {
		return amStatus;
	}
	/**
	 * @param amStatus The amStatus to set.
	 */
	public void setAmStatus(int amStatus) {
		this.amStatus = amStatus;
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
}
