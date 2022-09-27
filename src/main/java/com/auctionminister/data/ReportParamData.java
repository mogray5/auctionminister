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
import java.util.*;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ReportParamData implements Serializable {

	private int paramId = 0;
	private int reportId = 0;
	private String paramName = "";
	private String paramFriendlyName = "";
	private int paramType = 0;
	private int paramDefaultsType = 0;
	private String paramDefaults = "";
	private List defaultsList = null;
	private int paramLength = 10;
	
	
	/**
	 * 
	 */
	public ReportParamData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return Returns the paramDefaults.
	 */
	public String getParamDefaults() {
		return paramDefaults;
	}
	/**
	 * @param paramDefaults The paramDefaults to set.
	 */
	public void setParamDefaults(String paramDefaults) {
		this.paramDefaults = paramDefaults;
	}
	/**
	 * @return Returns the paramDefaultsType.
	 */
	public int getParamDefaultsType() {
		return paramDefaultsType;
	}
	/**
	 * @param paramDefaultsType The paramDefaultsType to set.
	 */
	public void setParamDefaultsType(int paramDefaultsType) {
		this.paramDefaultsType = paramDefaultsType;
	}
	/**
	 * @return Returns the paramFriendlyName.
	 */
	public String getParamFriendlyName() {
		return paramFriendlyName;
	}
	/**
	 * @param paramFriendlyName The paramFriendlyName to set.
	 */
	public void setParamFriendlyName(String paramFriendlyName) {
		this.paramFriendlyName = paramFriendlyName;
	}
	/**
	 * @return Returns the paramId.
	 */
	public int getParamId() {
		return paramId;
	}
	/**
	 * @param paramId The paramId to set.
	 */
	public void setParamId(int paramId) {
		this.paramId = paramId;
	}
	/**
	 * @return Returns the paramName.
	 */
	public String getParamName() {
		return paramName;
	}
	/**
	 * @param paramName The paramName to set.
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	/**
	 * @return Returns the paramType.
	 */
	public int getParamType() {
		return paramType;
	}
	/**
	 * @param paramType The paramType to set.
	 */
	public void setParamType(int paramType) {
		this.paramType = paramType;
	}
	/**
	 * @return Returns the reportId.
	 */
	public int getReportId() {
		return reportId;
	}
	/**
	 * @param reportId The reportId to set.
	 */
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	
	/**
	 * @return Returns the defaultsList.
	 */
	public List getDefaultsList() {
		return defaultsList;
	}
	/**
	 * @param defaultsList The defaultsList to set.
	 */
	public void setDefaultsList(List defaultsList) {
		this.defaultsList = defaultsList;
	}
	
	public void addDefault(String id, String value){
		if (defaultsList==null){
			defaultsList = new ArrayList();
		}
	}
	
	
	/**
	 * @return Returns the paramLength.
	 */
	public int getParamLength() {
		return paramLength;
	}
	/**
	 * @param paramLength The paramLength to set.
	 */
	public void setParamLength(int paramLength) {
		this.paramLength = paramLength;
	}
}
