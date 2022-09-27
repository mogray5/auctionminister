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
import com.auctionminister.data.AccountData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 */
public class RptProfitLossAction extends BaseAction {

	private static final long serialVersionUID = -8056531183762734858L;
	private String isSecure = "S";
	private List reportData;
	private String reportTotal;
	private AmFormat format = new AmFormat();

	public RptProfitLossAction() {
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
				params.setYear( cal.get(Calendar.YEAR));
				reportData = session.selectList("RptProfitLoss", params);
				setTotal();
				
		} catch (Exception e ) {
			addActionError("RptProfitLossAction error: " + e.getMessage());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	public void setTotal(){
		
		if (reportData!=null){
			
			double total=0D;
			AccountData line;
			for (int i=0; i<reportData.size(); i++){
				line = (AccountData)reportData.get(i);
				total += line.getCreditBalance() - line.getDebitBalance();
			}
			
			reportTotal = format.getRounded(total);
			
		}
		
	}
	
	public List getReportData() {
		return reportData;
	}
	public void setReportData(List reportData) {
		this.reportData = reportData;
	}
	public String getReportTotal() {
		return reportTotal;
	}
	public void setReportTotal(String reportTotal) {
		this.reportTotal = reportTotal;
	}


	
	
}
