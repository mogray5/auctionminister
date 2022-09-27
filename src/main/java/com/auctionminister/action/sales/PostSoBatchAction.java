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
package com.auctionminister.action.sales;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AM;
import com.auctionminister.core.AmCharge;
import com.auctionminister.data.ItemAssembly;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.SaLineData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.params.AdjustItemParams;

/**
 * @author wggray
 */
public class PostSoBatchAction extends BaseAction {

	private static final long serialVersionUID = -1756966386381229932L;
	private String isSecure = "S";	
	private long docId = 0;
	private String docList = "";
	
	public PostSoBatchAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				String[] aDocList;
				
				if (docList!=null && docList.length()>0){
					aDocList = docList.split(",");
				} else {
					addActionError("No documents found to post.");
					return ERROR;
				}

				GregorianCalendar gc = new GregorianCalendar();
				AccountParams account = new AccountParams();
				account.setUserId(oUserID.getUserId());
				account.setMonth(gc.get(Calendar.MONTH) + 1);
				account.setYear(gc.get(Calendar.YEAR));
				AdjustItemParams itemparams = new AdjustItemParams();
				ItemAssembly itemAssembly = null;
				
				List soList = null;
				List lineList = null;
				SaLineData line = null;
				SaHeaderData so = null;
				SaHeaderData search = null;
				ItemData item = null;
				
				for (int k=0; k<aDocList.length; k++){
				
					docId = new Long(aDocList[k].trim()).longValue();
					
					search = new SaHeaderData();
					search.setUserId(oUserID.getUserId());
					search.setDocId(docId);
				
					soList = session.selectList("GetOpenSoHeaderList", search);
				
					if (soList.size()>0){
					
						for(int i=0; i<soList.size(); i++){
						
							so = (SaHeaderData)soList.get(i);
							so.setUserId(oUserID.getUserId());
							lineList = session.selectList("GetOpenSoLineList", so);
						
							for (int j=0; j<lineList.size(); j++){
							
								
								
								line = (SaLineData) lineList.get(j);
								itemparams.setItemId(line.getItemId());
								itemparams.setUserId(oUserID.getUserId());
								itemparams.setAdjustVal(line.getQtySold() * -1);
							
								
								item = (ItemData)session.selectOne("GetItem", itemparams);
								
								if (item.getIsAssembly()==0){
								
								session.update("AdjustOnHand", itemparams);
								itemparams.setAdjustVal(line.getQtySold());
								session.update("AdjustQtySold", itemparams);
								itemparams.setAdjustVal(line.getSalePrice());
								session.update("AdjustListPrice", itemparams);
								
								//account.setAdjustVal(new Double(line.getQtySold()).doubleValue() * item.getCurrentCost());
								account.setAdjustVal(new Double(line.getQtySold()).doubleValue() * line.getSalePrice());
								account.setAccountIndex(AM.SALES);
								session.update("CreditAccount", account);
								session.commit();
								session.update("CreditMonthlyAccount", account);
								session.commit();
								account.setAccountIndex(AM.CASH);
								session.update("DebitAccount", account);
								session.commit();
								session.update("DebitMonthlyAccount", account);
								session.commit();

								account.setAdjustVal(new Double(line.getQtySold()).doubleValue() * item.getCurrentCost());
								account.setAccountIndex(AM.INVENTORY);
								session.update("CreditAccount", account);
								session.update("CreditMonthlyAccount", account);
								account.setAccountIndex(AM.COGS);
								session.update("DebitAccount", account);
								session.update("DebitMonthlyAccount", account);
								session.commit();
								
								} else {
									
									session.update("AdjustOnHand", itemparams);
									itemparams.setAdjustVal(line.getQtySold());
									session.update("AdjustQtySold", itemparams);
									itemparams.setAdjustVal(line.getSalePrice());
									session.update("AdjustListPrice", itemparams);
									account.setAdjustVal(new Double(line.getQtySold()).doubleValue() * line.getSalePrice());
									account.setAccountIndex(AM.SALES);
									account.setAccountIndex(AM.SALES);
									session.update("CreditAccount", account);
									session.update("CreditMonthlyAccount", account);
									account.setAccountIndex(AM.CASH);
									session.update("DebitAccount", account);
									session.update("DebitMonthlyAccount", account);
									session.commit();
									
									List assemblyItems = session.selectList("GetItemAssembly", itemparams);
									
									for (int l=0; l<assemblyItems.size(); l++){
										
										
										
										itemAssembly = (ItemAssembly)assemblyItems.get(l);
										itemparams.setItemId(itemAssembly.getElementItemID());
										item = (ItemData)session.selectOne("GetItem", itemparams);
										
										itemparams.setAdjustVal(itemAssembly.getElementQty() * new Double(line.getQtySold()).doubleValue() * -1);
										session.update("AdjustOnHand", itemparams);
										
										account.setAdjustVal(1D * itemAssembly.getElementQty() * line.getQtySold() * item.getCurrentCost());
										account.setAccountIndex(AM.INVENTORY);
										session.update("CreditAccount", account);
										session.update("CreditMonthlyAccount", account);
										account.setAccountIndex(AM.COGS);
										session.update("DebitAccount", account);
										session.update("DebitMonthlyAccount", account);
										session.commit();
										
									}
								}
							}
										
							session.update("MoveSoLinesToHistory", so);
							session.update("DeleteOpenSoLines", Long.valueOf(so.getSoNumber()));
							session.update("MoveSoHeaderToHistory", so);
							session.update("DeleteOpenSoHeader", so);
							session.commit();
						
						}
					
						session.update("MoveSoBatchToHistory", search);
						session.update("DeleteOpenSoBatchByDocId", search);
					
						session.commit();
					
					}
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
	 * @return Returns the docId.
	 */
	public long getDocId() {
		return docId;
	}
	/**
	 * @param docId The docId to set.
	 */
	public void setDocId(long docId) {
		this.docId = docId;
	}
	/**
	 * @return Returns the docList.
	 */
	public String getDocList() {
		return docList;
	}
	/**
	 * @param docList The docList to set.
	 */
	public void setDocList(String docList) {
		this.docList = docList;
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
}
