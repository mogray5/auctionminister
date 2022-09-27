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
package com.auctionminister.action.system;

import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.LoginParams;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author wggray
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 7759513022838647917L;
	private String sUserName = null;
	private String sPwd = null;
	
	public LoginAction() {
		super();
	}

	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
												
				//check user entered
				LoginParams login = new LoginParams();
				login.setUserName(sUserName);
				login.setPwd(sPwd);
				Integer iResult =  (Integer) session.selectOne("GetUserID", login);
					
				if (iResult==null){
					addActionError("Invalid username or password.  Please try again.");
					return INPUT;
				}
					
				UserSmallData iCheck = (UserSmallData) session.selectOne("GetLoggedInUserData", iResult);
					
				if (iCheck == null){
					session.update("LogOnUser", iResult);
				} else {
					session.update("LogOffUser", iResult);
					session.update("LogOnUser", iResult);
				}
				
				session.commit();
				
				ActionContext.getContext().getSession().put("USERID", iCheck);
					
		} catch (Exception e ) {
			addActionError("LoginAction error:" + e.toString());
			return INPUT;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	/**
	 * @return Returns the sPwd.
	 */
	public String getPwd() {
		return sPwd;
	}
	/**
	 * @param pwd The sPwd to set.
	 */
	public void setPwd(String pwd) {
		sPwd = pwd;
	}
	/**
	 * @return Returns the sUserName.
	 */
	public String getUserName() {
		return sUserName;
	}
	/**
	 * @param userName The sUserName to set.
	 */
	public void setUserName(String userName) {
		sUserName = userName;
	}
}
