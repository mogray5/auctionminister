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
package com.auctionminister.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.auctionminister.data.ReportData;
import com.auctionminister.data.ReportParamData;
import com.auctionminister.data.ReportParamDefaultData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.exceptions.AmReportException;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AmReportFactory {

	/**
	 * 
	 */
	public AmReportFactory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportData getReport(SqlSession session, int reportId, UserSmallData oUserID) throws AmReportException {
		
		ReportData report = null;
		List paramList = null;
		List paramDefaultsList = null;
		Integer rpt = Integer.valueOf(reportId);
		ReportParamDefaultData reportDefault = null;
		ReportParamData reportParam = null;
		String[] aDefaults = null;
		String[] aTmp = null;
		
		try {
		
			report = (ReportData)session.selectOne("GetReport", rpt);
			
			if (report!=null){
				
				paramList = session.selectList("GetReportParams", rpt);
				
				if (paramList != null){
					
					
					for (int i=0; i<paramList.size(); i++){
						
						reportParam = (ReportParamData) paramList.get(i);
						
						
						if (reportParam.getParamDefaultsType()==1){
						
							paramDefaultsList = new ArrayList();
							
							//inline list
							aDefaults = reportParam.getParamDefaults().split(",");
							
							for (int j=0; j<aDefaults.length; j++){
								if (aDefaults[j].indexOf(":")> 0){
									aTmp = aDefaults[j].split(":");
									
									reportDefault = new ReportParamDefaultData();
									
									if (aTmp.length>0){
										reportDefault.setId(aTmp[1]);
										reportDefault.setValue(aTmp[0]);
									} else {
										reportDefault.setId(aDefaults[j]);
										reportDefault.setValue(aDefaults[j]);
									}
									
									paramDefaultsList.add(reportDefault);	
								}
							}
						
						} else if (reportParam.getParamDefaultsType()==2){
							//sql list
							paramDefaultsList = session.selectList(reportParam.getParamDefaults(), oUserID);
							
							if (paramDefaultsList==null || paramDefaultsList.size()==0){
								throw new AmReportException("paramDefaultList is null");
							}
							
						} else {
							//no defaults
						}
					
						if (paramDefaultsList != null){
							reportParam.setDefaultsList(paramDefaultsList);
						}
					}
					
					report.setParamList(paramList);
				}
				
			}
		
		
		} catch (Exception sqle) {
			throw new AmReportException("Get Report Error:  " + sqle.getMessage());
		}
		
		return report;
	}
	
}
