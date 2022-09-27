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

/**
 * @author wggray
 */
public class ItemClassData implements Serializable {

	long lUserId = 0;
	long iClassId = 0;
	String sClassName = null;
	String sClassDesc = null;
	
	/**
	 * 
	 */
	public ItemClassData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the iClassId.
	 */
	public long getClassId() {
		return iClassId;
	}
	/**
	 * @param classId The iClassId to set.
	 */
	public void setClassId(long classId) {
		iClassId = classId;
	}
	/**
	 * @return Returns the sClassDesc.
	 */
	public String getClassDesc() {
		return sClassDesc;
	}
	/**
	 * @param classDesc The sClassDesc to set.
	 */
	public void setClassDesc(String classDesc) {
		sClassDesc = classDesc;
	}
	/**
	 * @return Returns the sClassName.
	 */
	public String getClassName() {
		return sClassName;
	}
	/**
	 * @param className The sClassName to set.
	 */
	public void setClassName(String className) {
		sClassName = className;
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
}
