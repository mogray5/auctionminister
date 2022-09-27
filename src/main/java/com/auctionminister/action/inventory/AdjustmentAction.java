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

package com.auctionminister.action.inventory;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AM;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.params.AdjustItemParams;

/**
 * @author wggray
 */
public class AdjustmentAction extends BaseAction {

	
	private static final long serialVersionUID = -2447708164941180183L;
	private String itemId = "";
	private long adjustVal = 0;
	private ItemData oItem = null;
	private String isSecure = "S";
	
	public AdjustmentAction() {
		super();
	}

	/**
	 * @return Returns the adjustVal.
	 */
	public long getAdjustVal() {
		return adjustVal;
	}
	/**
	 * @param adjustVal The adjustVal to set.
	 */
	public void setAdjustVal(long adjustVal) {
		this.adjustVal = adjustVal;
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
	/**
	 * @return Returns the oItem.
	 */
	public ItemData getItem() {
		return oItem;
	}
	/**
	 * @param item The oItem to set.
	 */
	public void setItem(ItemData item) {
		oItem = item;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {				
				UserSmallData oUserID = this.verifyLogin();
						
				AdjustItemParams itemparams = new AdjustItemParams();
				itemparams.setItemId(itemId);
				itemparams.setUserId(oUserID.getUserId());
				itemparams.setAdjustVal(adjustVal);
				
				GregorianCalendar gc = new GregorianCalendar();
				AccountParams account = new AccountParams();
				account.setUserId(oUserID.getUserId());
				account.setMonth(gc.get(Calendar.MONTH) + 1);
				account.setYear(gc.get(Calendar.YEAR));
				
				ItemData item = (ItemData)session.selectOne("GetItem", itemparams);				
				
				if (item!=null){
				
					session.update("AdjustOnHand", itemparams);
					
					if (adjustVal<0){

						double adjustment = adjustVal * item.getCurrentCost()  * -1.0;
						account.setAdjustVal(adjustment);
						account.setAccountIndex(AM.INVENTORY);
						
						session.update("CreditAccount", account);
						session.update("CreditMonthlyAccount", account);
						
						account.setAccountIndex(AM.COGS);
						account.setAdjustVal(adjustment);
						session.update("DebitAccount", account);
						session.update("DebitMonthlyAccount", account);
						
					} else {
			
						account.setAdjustVal(1D * adjustVal * item.getCurrentCost());
						account.setAccountIndex(AM.INVENTORY);
						session.update("DebitAccount", account);
						session.update("DebitMonthlyAccount", account);
						account.setAccountIndex(AM.OFFSET);
						session.update("CreditAccount", account);
						session.update("CreditMonthlyAccount", account);
						
					}
					session.commit();

				} else {
					addActionError("Can not find Item. Canceling adjustment.");
					return ERROR;
				}
				
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
}
