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

/**
 * @author wggray
 */
public class SecureAction extends BaseAction {
	

	private static final long serialVersionUID = -2678419627301988057L;
	private String sIsSecure = "S";
	private UserSmallData userDat = null;
	
	public SecureAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {
		
			userDat = this.verifyLogin();
			
			if (userDat==null) {
				addActionError("user info missing");
			}
			
			return SUCCESS;
		
		} finally {
			this.endSession();
		}
	}
	
	
	/**
	 * @return Returns the bIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}
	/**
	 * @param isSecure The bIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}
	
	
	/**
	 * @return Returns the userDat.
	 */
	public UserSmallData getUserData() {
		return userDat;
	}
	/**
	 * @param userDat The userDat to set.
	 */
	public void setUserData(UserSmallData userDat) {
		this.userDat = userDat;
	}
}
