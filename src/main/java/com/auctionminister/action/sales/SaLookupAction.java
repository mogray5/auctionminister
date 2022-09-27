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

import java.util.Date;
import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SaLookupParams;

/**
 * @author wggray
 */
public class SaLookupAction extends BaseAction {

	private static final long serialVersionUID = 7770343979037790636L;
	private String isSecure = "S";	
	private String batchId = "";
	private Date startDate;
	private Date endDate;
	private long soNumber=0;
	private List openSoList=null;
	private List closedSoList = null;
	private int pullOption = 0;
	private int includeHistory = 0;
	private long customerId = 0;
	private String custName = "";
	
	public SaLookupAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
									
				UserSmallData oUserID = this.verifyLogin();
						
				SaLookupParams search = new SaLookupParams();
				search.setUserId(oUserID.getUserId());
				
				switch (pullOption){
					case 1:
						//pull by batch
						search.setBatchId(batchId);
						openSoList = session.selectList("GetOpenSoLookupByBatch", search);
						if (includeHistory!=0) {
							closedSoList = session.selectList("GetClosedSoLookupByBatch", search);
						}
						break;
				    case 2:
				    	//pull by date
				    	search.setStartDate(startDate);
				    	search.setEndDate(endDate);
				    	openSoList = session.selectList("GetOpenSoLookupByDate", search);
				    	if (includeHistory!=0) {
				    		closedSoList = session.selectList("GetClosedSoLookupByDate", search);
				    	}
				    	break;
				    case 3:
				    	//pull by so number
				    	search.setSoNumber(soNumber);
				    	openSoList = session.selectList("GetOpenSoLookupByNumber", search);
				    	if (includeHistory!=0) {
				    		closedSoList = session.selectList("GetClosedSoLookupByNumber", search);
				    	}
						break;
					case 4:
						//pull by customer id
						search.setCustomerId(customerId);
				    	openSoList = session.selectList("GetOpenSoLookupByCustomer", search);
				    	if (includeHistory!=0) {
				    		closedSoList = session.selectList("GetClosedSoLookupByCustomer", search);
				    	}
				    	break;
				    case 5:
				    	//pull by customer name
				    	search.setCustName(custName + "%");
				    	openSoList = session.selectList("GetOpenSoLookupByCustomerName", search);
				    	if (includeHistory!=0) {
				    		closedSoList = session.selectList("GetClosedSoLookupByCustomerName", search);
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
	 * @return Returns the closedSoList.
	 */
	public List getClosedSoList() {
		return closedSoList;
	}
	/**
	 * @param closedSoList The closedSoList to set.
	 */
	public void setClosedSoList(List closedSoList) {
		this.closedSoList = closedSoList;
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
	 * @return Returns the openSoList.
	 */
	public List getOpenSoList() {
		return openSoList;
	}
	/**
	 * @param openSoList The openSoList to set.
	 */
	public void setOpenSoList(List openSoList) {
		this.openSoList = openSoList;
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
	 * @return Returns the custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName The custName to set.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
}
