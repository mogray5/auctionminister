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
package com.auctionminister.params;

import java.io.Serializable;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class LoginParams implements Serializable {

	private String sUserName = null;
	private String sPwd = null;
	
	/**
	 * 
	 */
	public LoginParams() {
		super();
		
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
