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
package com.auctionminister.action.reports;

import java.util.Calendar;
import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.RptPurchaseActivity;
import com.auctionminister.data.RptSalesActivity;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 */
public class RptItemActivityMonthlyAction extends BaseAction {

	private static final long serialVersionUID = 6479449995919729660L;
	private String isSecure = "S";
	private List salesData;
	private List purchaseData;
	private String purchaseTotal;
	private String salesTotal;
	private int month=-1;
	private int year=-1;
	
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	private AmFormat format = new AmFormat();

	public RptItemActivityMonthlyAction() {
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
				AccountParams params = new AccountParams();
				params.setUserId(oUserID.getUserId());
				
				Calendar cal = Calendar.getInstance();
				
				if (month<0){
				
					params.setMonth( cal.get(Calendar.MONTH) + 1);
					month = params.getMonth();
				} else {
					
					params.setMonth(month);
				}
				
				if (year<0){
					
					params.setYear( cal.get(Calendar.YEAR));
					year = params.getYear();
					
				} else {
					
					params.setYear(year);
					
				}
				
				salesData = session.selectList("RptItemActivitySales", params);
				purchaseData = session.selectList("RptItemActivityPurchases", params);
				
				setTotals();
				
		} catch (Exception e ) {
			addActionError("RptProfitLossActionMonthly error: " + e.getMessage());
			e.printStackTrace();
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	public void setTotals(){
		
		if (salesData!=null){
			
			double total=0D;
			RptSalesActivity line;
			for (int i=0; i<salesData.size(); i++){
				line = (RptSalesActivity)salesData.get(i);
				total += line.getSales();
			}
			
			salesTotal = format.getRounded(total);
			
			total=0D;
			RptPurchaseActivity line2;
			for (int i=0; i<purchaseData.size(); i++){
				line2 = (RptPurchaseActivity)purchaseData.get(i);
				total += line2.getPurchases();
			}
			
			purchaseTotal = format.getRounded(total);
		}
		
	}
	

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List getSalesData() {
		return salesData;
	}
	public void setSalesData(List salesData) {
		this.salesData = salesData;
	}
	public List getPurchaseData() {
		return purchaseData;
	}
	public void setPurchaseData(List purchaseData) {
		this.purchaseData = purchaseData;
	}
	public String getPurchaseTotal() {
		return purchaseTotal;
	}
	public void setPurchaseTotal(String purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}
	public String getSalesTotal() {
		return salesTotal;
	}
	public void setSalesTotal(String salesTotal) {
		this.salesTotal = salesTotal;
	}


	
	
}
