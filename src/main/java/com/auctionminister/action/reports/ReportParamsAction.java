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

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.ReportData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.util.AmReportFactory;

/**
 * @author wggray
 */
public class ReportParamsAction extends BaseAction {

	private static final long serialVersionUID = -8638578716200097329L;
	private String isSecure = "S";
	private int reportId = 0;
	private ReportData report = null;
	
	public ReportParamsAction() {
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

	/**
	 * @return Returns the reportId.
	 */
	public int getReportId() {
		return reportId;
	}
	/**
	 * @param reportId The reportId to set.
	 */
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	
	/**
	 * @return Returns the report.
	 */
	public ReportData getReport() {
		return report;
	}
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				AmReportFactory reportFactory = new AmReportFactory();
				report = reportFactory.getReport(session, reportId, oUserID);
				
				
		} catch (Exception e ) {
			addActionError("ReportParamsAction error: " + e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	/**
	 * @param report The report to set.
	 */
	public void setReport(ReportData report) {
		this.report = report;
	}
}
