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

import java.util.Date;
import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.PoLookupParams;

/**
 * @author wggray
 */
public class PoLookupAction extends BaseAction  {

	private static final long serialVersionUID = 3662166805129963579L;
	private String isSecure = "S";
	
	private String batchId = "";
	private Date startDate;
	private Date endDate;
	private long poNumber=0;
	private List openPoList=null;
	private List closedPoList = null;
	private int pullOption = 0;
	private int includeHistory = 0;
	private String vendorName = "";
	
	public PoLookupAction() {
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
				PoLookupParams search = new PoLookupParams();
				search.setUserId(oUserID.getUserId());
				
				switch (pullOption){
					case 1:
						//pull by batch
						search.setBatchId(batchId);
						openPoList = session.selectList("GetOpenPoLookupByBatch", search);
						if (includeHistory!=0){
							closedPoList = session.selectList("GetClosedPoLookupByBatch", search);
						}
						break;
				    case 2:
				    	//pull by date
				    	search.setStartDate(startDate);
				    	search.setEndDate(endDate);
				    	openPoList = session.selectList("GetOpenPoLookupByDate", search);
				    	if (includeHistory!=0){
				    		closedPoList = session.selectList("GetClosedPoLookupByDate", search);
				    	}
				    	break;
				    case 3:
				    	//pull by po number
				    	search.setPoNumber(poNumber);
				    	openPoList = session.selectList("GetOpenPoLookupByNumber", search);
				    	if (includeHistory!=0){
				    		closedPoList = session.selectList("GetClosedPoLookupByNumber", search);
				    	}
						break;
				    case 4:
				    	//pull by vendor name
				    	search.setVendorName(vendorName + "%");
				    	openPoList = session.selectList("GetOpenPoLookupByVendorName", search);
				    	if (includeHistory!=0){
				    		closedPoList = session.selectList("GetClosedPoLookupByVendorName", search);
				    	}
						break;

				}
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
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
	 * @return Returns the endDate.
	 */
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return Returns the poList.
	 */
	public List getOpenPoList() {
		return openPoList;
	}
	/**
	 * @param poList The poList to set.
	 */
	public void setOpenPoList(List poList) {
		this.openPoList = poList;
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
	 * @return Returns the pullOption.
	 */
	public int getPullOption() {
		return pullOption;
	}
	/**
	 * @param pullOption The pullOption to set.
	 */
	public void setPullOption(int pullOption) {
		this.pullOption = pullOption;
	}
	/**
	 * @return Returns the startDate.
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	/**
	 * @return Returns the includeHistory.
	 */
	public int getIncludeHistory() {
		return includeHistory;
	}
	/**
	 * @param includeHistory The includeHistory to set.
	 */
	public void setIncludeHistory(int includeHistory) {
		this.includeHistory = includeHistory;
	}
	
	
	/**
	 * @return Returns the closedPoList.
	 */
	public List getClosedPoList() {
		return closedPoList;
	}
	/**
	 * @param closedPoList The closedPoList to set.
	 */
	public void setClosedPoList(List closedPoList) {
		this.closedPoList = closedPoList;
	}
	
	
	/**
	 * @return Returns the vendorName.
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName The vendorName to set.
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
}
