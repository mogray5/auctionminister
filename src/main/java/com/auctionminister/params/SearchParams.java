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
public class SearchParams implements Serializable {

	private long lUserId = -1;
	private String sItem = "";
	
	/**
	 * 
	 */
	public SearchParams() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the lUserId.
	 */
	public long getUserId() {
		return lUserId;
	}
	/**
	 * @param userId The lUserId to set.
	 */
	public void setUserId(long userId) {
		lUserId = userId;
	}
	/**
	 * @return Returns the sItem.
	 */
	public String getSearchVal() {
		return sItem;
	}
	/**
	 * @param item The sItem to set.
	 */
	public void setSearchVal(String item) {
		sItem = item;
	}
	
	
	/**
	 * @return Returns the sItem.
	 */
	public String getItemId() {
		return sItem;
	}
	/**
	 * @param item The sItem to set.
	 */
	public void setItemId(String item) {
		sItem = item;
	}
}
