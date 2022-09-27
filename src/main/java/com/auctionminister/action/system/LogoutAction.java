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
import com.opensymphony.xwork2.ActionContext;

/**
 * @author wggray
 */
public class LogoutAction extends BaseAction {


	private static final long serialVersionUID = 5474298774004856168L;
	
	public LogoutAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				
			UserSmallData iCheck = this.verifyLogin();
						
			if (iCheck != null){
				session.update("LogOffUser", Long.valueOf(iCheck.getUserId()));
				ActionContext.getContext().getSession().remove("USERID");
			}						
			
			session.commit();
						
		} catch (Exception e ) {
			addActionError(e.toString());
			return INPUT;
		} finally {
			this.endSession();
		}
			
		return SUCCESS;
	}
}
