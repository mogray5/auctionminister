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
public class ReportData implements Serializable {

	private int reportId = 0;
	private String reportName = "";
	private String reportFile = "";
	private String reportAction = "";
	private List paramList = null;
	
	/**
	 * 
	 */
	public ReportData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the paramList.
	 */
	public List getParamList() {
		return paramList;
	}
	/**
	 * @param paramList The paramList to set.
	 */
	public void setParamList(List paramList) {
		this.paramList = paramList;
	}
	/**
	 * @return Returns the reportFile.
	 */
	public String getReportFile() {
		return reportFile;
	}
	/**
	 * @param reportFile The reportFile to set.
	 */
	public void setReportFile(String reportFile) {
		this.reportFile = reportFile;
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
	 * @return Returns the reportName.
	 */
	public String getReportName() {
		return reportName;
	}
	/**
	 * @param reportName The reportName to set.
	 */
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	
	/**
	 * @return Returns the reportAction.
	 */
	public String getReportAction() {
		return reportAction;
	}
	/**
	 * @param reportAction The reportAction to set.
	 */
	public void setReportAction(String reportAction) {
		this.reportAction = reportAction;
	}
}
