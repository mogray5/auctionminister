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
package com.auctionminister.action.purchase;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AM;
import com.auctionminister.data.PoLineData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.params.AdjustItemParams;
import com.auctionminister.params.PoLookupParams;

/**
 * @author wggray
 */
public class PoReturnAction extends BaseAction {

	private static final long serialVersionUID = 4089261911418418385L;
	private String isSecure = "S";
	private String itemId = "";
	private long poNumber = 0;
	
	public PoReturnAction() {
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
				GregorianCalendar gc = new GregorianCalendar();
				AccountParams account = new AccountParams();
				account.setUserId(oUserID.getUserId());
				account.setMonth(gc.get(Calendar.MONTH) + 1);
				account.setYear(gc.get(Calendar.YEAR));
				AdjustItemParams itemparams = new AdjustItemParams();
				PoLineData line = null;
				PoLookupParams search = new PoLookupParams();
				
				search.setUserId(oUserID.getUserId());
				search.setPoNumber(poNumber);
				search.setItemId(itemId);
				
				
				line = (PoLineData)session.selectOne("GetClosedPoLine", search);
				
				if (line==null){
					addActionError("Could not find purchase item in database." + poNumber);
					return ERROR;
				}
				
				itemparams.setItemId(line.getItemId());
				itemparams.setUserId(oUserID.getUserId());
				itemparams.setAdjustVal(line.getQtyPurch() * -1);
							
				session.update("AdjustOnHand", itemparams);
				itemparams.setAdjustVal(line.getQtyPurch());
				account.setAdjustVal(1D * line.getQtyPurch() * line.getPurchPrice());
				account.setAccountIndex(AM.INVENTORY);
				session.update("CreditAccount", account);
				session.update("CreditMonthlyAccount", account);
				account.setAccountIndex(AM.CASH);
				session.update("DebitAccount", account);
				session.update("DebitMonthlyAccount", account);

						
				session.update("DeleteClosedPoLine", line);
				
				session.commit();
			
		} catch (Exception e ) {
			session.rollback();
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	
	
	/**
	 * @return Returns the itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return Returns the poNumber.
	 */
	public long getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber The poNumber to set.
	 */
	public void setPoNumber(long poNumber) {
		this.poNumber = poNumber;
	}
}
