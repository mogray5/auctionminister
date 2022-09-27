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

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.UploadParams;

/**
 * @author wggray
 */
public class UpLoadSubmitalAction extends BaseAction {

	private static final long serialVersionUID = 5805513804914644228L;
	private String isSecure = "S";
	//private long batchId = 0;
	
	private List poList = null;
	private List soList = null;
	private List returnList = null;
	private List ebayChargeList = null;
	private List paypalChargeList = null;
	private List shipChargeList = null;
	private List otherChargeList = null;
	
	public UpLoadSubmitalAction() {
		super();
	}

	/**
	 * @return Returns the isSecure.
	 */
	public String getIsSecure() {
		return isSecure;
	}
	/**
	 * @param isSecure The isSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		this.isSecure = isSecure;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				
				addActionError("in submital");
				
				UploadParams search = new UploadParams();
				search.setUserId(oUserID.getUserId());

				search.setAmType(1);
				poList = session.selectList("GetBatchIncludeListByType", search); 
				
				search.setAmType(0);
				soList = session.selectList("GetBatchIncludeListByType", search); 
				
				search.setAmType(2);
				returnList = session.selectList("GetBatchIncludeListByType", search); 

				search.setAmType(4);
				ebayChargeList = session.selectList("GetBatchIncludeListByType", search); 

				search.setAmType(5);
				paypalChargeList = session.selectList("GetBatchIncludeListByType", search); 

				search.setAmType(6);
				shipChargeList = session.selectList("GetBatchIncludeListByType", search); 

				search.setAmType(7);
				otherChargeList = session.selectList("GetBatchIncludeListByType", search); 
				
				addActionError("finished submital");
				
		} catch (Exception e ) {
			addActionError("Submital Error: " + e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * @return Returns the ebayChargeList.
	 */
	public List getEbayChargeList() {
		return ebayChargeList;
	}
	/**
	 * @param ebayChargeList The ebayChargeList to set.
	 */
	public void setEbayChargeList(List ebayChargeList) {
		this.ebayChargeList = ebayChargeList;
	}
	/**
	 * @return Returns the otherChargeList.
	 */
	public List getOtherChargeList() {
		return otherChargeList;
	}
	/**
	 * @param otherChargeList The otherChargeList to set.
	 */
	public void setOtherChargeList(List otherChargeList) {
		this.otherChargeList = otherChargeList;
	}
	/**
	 * @return Returns the paypalChargeList.
	 */
	public List getPaypalChargeList() {
		return paypalChargeList;
	}
	/**
	 * @param paypalChargeList The paypalChargeList to set.
	 */
	public void setPaypalChargeList(List paypalChargeList) {
		this.paypalChargeList = paypalChargeList;
	}
	/**
	 * @return Returns the poList.
	 */
	public List getPoList() {
		return poList;
	}
	/**
	 * @param poList The poList to set.
	 */
	public void setPoList(List poList) {
		this.poList = poList;
	}
	/**
	 * @return Returns the returnList.
	 */
	public List getReturnList() {
		return returnList;
	}
	/**
	 * @param returnList The returnList to set.
	 */
	public void setReturnList(List returnList) {
		this.returnList = returnList;
	}
	/**
	 * @return Returns the shipChargeList.
	 */
	public List getShipChargeList() {
		return shipChargeList;
	}
	/**
	 * @param shipChargeList The shipChargeList to set.
	 */
	public void setShipChargeList(List shipChargeList) {
		this.shipChargeList = shipChargeList;
	}
	/**
	 * @return Returns the soList.
	 */
	public List getSoList() {
		return soList;
	}
	/**
	 * @param soList The soList to set.
	 */
	public void setSoList(List soList) {
		this.soList = soList;
	}


}
