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
public class RptStockStatusAction extends BaseAction {

	private static final long serialVersionUID = 4377029123634756979L;
	private String isSecure = "S";
	
	public RptStockStatusAction() {
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

				AmReportWriter report = new AmReportWriter();
				
				report.RenderReport(parameters, "amstockstatus.pdf", 
						"com/auctionminister/action/reports/amr_stock_status.jasper");
				
		} catch (Exception e ) {
			addActionError(e.getMessage());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return NONE;
	}


}
