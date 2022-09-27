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
package com.auctionminister.data;

import java.io.Serializable;

public class GlAccountTypes implements Serializable {

	private static final long serialVersionUID = 5185765054134969680L;

	private int acctType = 0;
	private String acctTypeDesc = "";
	public int getAcctType() {
		return acctType;
	}
	public void setAcctType(int acctType) {
		this.acctType = acctType;
	}
	public String getAcctTypeDesc() {
		return acctTypeDesc;
	}
	public void setAcctTypeDesc(String acctTypeDesc) {
		this.acctTypeDesc = acctTypeDesc;
	}

}

