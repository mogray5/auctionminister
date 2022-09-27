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

import java.util.HashMap;
import java.util.Map;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.util.AmReportWriter;

/**
 * @author wggray
 */
public class RptIvValueMonthlyAction extends BaseAction {

	private static final long serialVersionUID = 5943573620512601863L;
	private String isSecure = "S";
	
	private int pStartYear = 2003;
	private int pEndYear = 2003;
	
	public RptIvValueMonthlyAction() {
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

				//parameters
				Map parameters = new HashMap();
				parameters.put("pUser", Long.valueOf(oUserID.getUserId()));
				parameters.put("pStartYear", Integer.valueOf(this.getPStartYear()));
				parameters.put("pEndYear", Integer.valueOf(this.getPEndYear()));

				AmReportWriter report = new AmReportWriter();
				
				report.RenderReport(parameters, "amivvaluemonthly.pdf", 
						"com/auctionminister/action/reports/amr_iv_value_by_month.jasper");
				
		} catch (Exception e ) {
			addActionError(e.getMessage());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return NONE;
	}



	/**
	 * @return Returns the pEndYear.
	 */
	public int getPEndYear() {
		return pEndYear;
	}
	/**
	 * @param endYear The pEndYear to set.
	 */
	public void setPEndYear(int endYear) {
		pEndYear = endYear;
	}
	/**
	 * @return Returns the pStartYear.
	 */
	public int getPStartYear() {
		return pStartYear;
	}
	/**
	 * @param startYear The pStartYear to set.
	 */
	public void setPStartYear(int startYear) {
		pStartYear = startYear;
	}
}
