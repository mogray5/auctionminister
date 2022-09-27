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

import org.apache.struts2.ServletActionContext;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.providers.SQLMapProvider;
import com.auctionminister.util.AmReportWriter;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author wggray
 */
public class RptOpenPurchaseOrdersAction extends BaseAction {

	private static final long serialVersionUID = 6484370274553889534L;
	private String isSecure = "S";

	public RptOpenPurchaseOrdersAction() {
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
				
				report.RenderReport(parameters, "amopenpurchaseorders.pdf", 
						"com/auctionminister/action/reports/amr_open_po_orders.jasper");
				
		} catch (Exception e ) {
			addActionError("RptProfitLossAction error: " + e.getMessage());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return NONE;
	}

}
