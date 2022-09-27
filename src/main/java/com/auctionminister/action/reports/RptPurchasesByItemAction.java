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

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.util.AmReportWriter;

/**
 * @author wggray
 */
public class RptPurchasesByItemAction extends BaseAction {

	private static final long serialVersionUID = -6273886200860748441L;
	private String isSecure = "S";
	
	private Date pStartDate = null;
	private Date pEndDate = null;

	public RptPurchasesByItemAction() {
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
				parameters.put("pStartDate", pStartDate);
				parameters.put("pEndDate", pEndDate);

				AmReportWriter report = new AmReportWriter();
				
				report.RenderReport(parameters, "ampurchasesbyitem.pdf", 
						"com/auctionminister/action/reports/amr_po_by_item.jasper");
				
		} catch (Exception e ) {
			addActionError("RptProfitLossAction error: " + e.getMessage());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return NONE;
	}



	/**
	 * @return Returns the pEndDate.
	 */
	public Date getPEndDate() {
		return pEndDate;
	}
	/**
	 * @param endDate The pEndDate to set.
	 */
	public void setPEndDate(Date endDate) {
		pEndDate = endDate;
	}
	/**
	 * @return Returns the pStartDate.
	 */
	public Date getPStartDate() {
		return pStartDate;
	}
	/**
	 * @param startDate The pStartDate to set.
	 */
	public void setPStartDate(Date startDate) {
		pStartDate = startDate;
	}
}
