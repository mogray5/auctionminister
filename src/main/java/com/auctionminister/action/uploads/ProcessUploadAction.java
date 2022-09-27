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
package com.auctionminister.action.uploads;

import java.util.HashMap;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UploadData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 */
public class ProcessUploadAction extends BaseAction {

	private static final long serialVersionUID = 4135661839685216972L;
	private String sIncludeList = null;
	//private long batchId = 0;
	private String sTrxType = null;
	private String sIsSecure = "S";
	private String[] aIncludeList= null;
	private HashMap hmIncludeList=new HashMap();
	private String[] aTypeList = null;
	private HashMap hmTypeList = new HashMap();
	
	public ProcessUploadAction() {
		super();
	}

	/**
	 * @return Returns the sIncludeList.
	 */
	public String getIncludeList() {
		return sIncludeList;
	}
	/**
	 * @param includeList The sIncludeList to set.
	 */
	public void setIncludeList(String includeList) {
		sIncludeList = includeList;
	}
	/**
	 * @return Returns the sTrxType.
	 */
	public String getTrxType() {
		return sTrxType;
	}
	/**
	 * @param trxType The sTrxType to set.
	 */
	public void setTrxType(String trxType) {
		sTrxType = trxType;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
			
				UserSmallData oUserID = this.verifyLogin();
						
				addActionError(sIncludeList);
				addActionError(sTrxType);
				
				InitIncludeList();
				InitTypeList();
				
				session.update("ClearIncludeList", Long.valueOf(oUserID.getUserId()));
				session.commit();
				UploadData tranline = new UploadData();
				String sTmp = null;
				
				for (int i=0; i< aIncludeList.length; i++){
					tranline.setAmInclude(1);
					tranline.setTrxIndex(new Long(aIncludeList[i].trim()).longValue());
					sTmp = (String)hmTypeList.get(aIncludeList[i].trim());
					
					if (sTmp!=null){
						addActionError("Type:" + sTmp);
						tranline.setAmStatus(1);
						tranline.setAmType(Integer.valueOf(sTmp).intValue());
						tranline.setAmMsg("NA");
						session.update("EditNewTranStatus", tranline);
						session.commit();
					}
				}
				
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;

	}
	
	
	/**
	 * @return Returns the sIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}
	/**
	 * @param isSecure The sIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}
	
	private void InitIncludeList(){
		if (sIncludeList!=null && sIncludeList.length()>0){
			
			aIncludeList = sIncludeList.split(",");
			for (int i=0; i< aIncludeList.length; i++){
				
				hmIncludeList.put(aIncludeList[i], aIncludeList[i]);
				
			}
		}
	}
	
	private void InitTypeList(){
		
		String[] aTmp = null;
		AmFormat fmt = new AmFormat();
		
		if (sTrxType!=null && sTrxType.length()>0){
			
			aTypeList = sTrxType.split(",");
			for (int i=0; i< aTypeList.length; i++){
				
				aTmp = fmt.split(aTypeList[i], ':');
				
				if (aTmp.length>0){
					hmTypeList.put(aTmp[0].trim(), aTmp[1].trim());
				}
			}
		}
	}
}
