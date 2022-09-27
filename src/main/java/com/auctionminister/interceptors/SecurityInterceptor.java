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
package com.auctionminister.interceptors;

import com.auctionminister.data.UserSmallData;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author wggray
 */
public class SecurityInterceptor implements Interceptor{
	
	private static final long serialVersionUID = 9176853954372282278L;

	public SecurityInterceptor() {
		super();
	}

	public void destroy() {
	}

	public void init() {
	}

	public String intercept(ActionInvocation actionInv) throws Exception {
		
		UserSmallData oUser = (UserSmallData) ActionContext.getContext().getSession().get("USERID");
		
		if (oUser == null){
			ActionSupport action = (ActionSupport) actionInv.getAction();
			action.addActionError("You are not currently logged into the system. Please login and try again.");
			return Action.LOGIN;
		}
		
		return actionInv.invoke();
			
	}
}
