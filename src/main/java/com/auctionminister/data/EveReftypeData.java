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

public class EveReftypeData implements Serializable {

	private static final long serialVersionUID = 4702413534945000847L;

	private int refTypeId = 0;
	private String refTypename = "";
	private long costActIndex = 0;
	private boolean ignore = false;
	private long incActIndex =0;
	
	public int getRefTypeId() {
		return refTypeId;
	}
	public void setRefTypeId(int refTypeId) {
		this.refTypeId = refTypeId;
	}
	public String getRefTypename() {
		return refTypename;
	}
	public void setRefTypename(String refTypename) {
		this.refTypename = refTypename;
	}
	public long getCostActIndex() {
		return costActIndex;
	}
	public void setCostActIndex(long costTypeIndex) {
		this.costActIndex = costTypeIndex;
	}
	public boolean isIgnore() {
		return ignore;
	}
	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}
	public long getIncActIndex() {
		return incActIndex;
	}
	public void setIncActIndex(long incomeActIndex) {
		this.incActIndex = incomeActIndex;
	}
	
	
}
