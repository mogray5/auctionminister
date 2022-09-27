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
package com.auctionminister.action.inventory;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.DayLookupParams;

public class MfgPlanner extends BaseAction {

	private static final long serialVersionUID = -4296598868745377502L;
	private String sIsSecure = "S";
	private List assemblyItemList;
	private List assemblyTotalsList;
	private int days = 7;
	
	public MfgPlanner(){
	}

	@Override
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
			UserSmallData oUserID = this.verifyLogin();
					
			if (oUserID == null){
				addActionError("Login could not be verified.");
				return INPUT;
			}
			
			
			DayLookupParams params = new DayLookupParams();
			params.setUserId(oUserID.getUserId());
			params.setDays(days);
			
			assemblyItemList = session.selectList("GetAssemblyQtyForPeriod", params);
			assemblyTotalsList = session.selectList("GetAssemblyQtyTotalsForPeriod", params);
		
		} catch (Exception e ) {
			addActionError(e.toString());
			return INPUT;
		} finally {
			this.endSession();
		}
	
	return SUCCESS;
	}
	
	/**
	 * @return Returns the sIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}
	/**
	 * @param isSecure The sIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}
	
	public List getAssemblyItemList() {
		return assemblyItemList;
	}

	public void setAssemblyItemList(List assemblyItemList) {
		this.assemblyItemList = assemblyItemList;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public List getAssemblyTotalsList() {
		return assemblyTotalsList;
	}

	public void setAssemblyTotalsList(List assemblyTotalsList) {
		this.assemblyTotalsList = assemblyTotalsList;
	}
	
	
	
}
