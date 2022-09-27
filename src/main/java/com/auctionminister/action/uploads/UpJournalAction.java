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
import com.auctionminister.core.AM;
import com.auctionminister.core.AmCharge;
import com.auctionminister.core.AmIncome;
import com.auctionminister.data.JournalUploadData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wayne
 *
 */
public class UpJournalAction extends BaseAction {

	private static final long serialVersionUID = 7713683587168333871L;
	private String sIsSecure = "S";
	private List journalTran;
	private String action = "";
	private String refNum = "";
	private int journalCount = 0;
	
	@Override
	public String execute() throws Exception {
		
		this.startSession();
		
		try {
			UserSmallData oUserID = this.verifyLogin();
					
			if (action.equals("delall")){
				session.update("DeleteAllJournalLines", oUserID);
				session.commit();
			} else if (action.equals("deldup")){
				session.update("DeleteDuplicateJournalLines", oUserID);
				session.commit();
			} else if (action.equals("delline")){
			
				if (refNum != null){
					
					JournalUploadData line = new JournalUploadData();
					line.setRefNum(refNum);
					line.setUserId(oUserID.getUserId());
					session.update("DeleteJournalLine", line);
					session.commit();
				}
					
			} else if (action.equals("post")){
				
				List trans = session.selectList("GetJournalTranNoDups", oUserID);
				
				if (trans.size()>0){
					
					AmCharge charge = new AmCharge();
					AmIncome income = new AmIncome();
					boolean canArchive = false;
					
					int costType;
					
					for (int i=0; i<trans.size(); i++){
						
						JournalUploadData data = (JournalUploadData)trans.get(i);
						
						if (data.getCostActIndex()>0){
							charge.Apply(oUserID.getUserId(), 
									data.getCostActIndex(), session, data.getAmount()*-1D,
									data.getRefTypeName());
							canArchive = true;
							
						} else if (data.getIncActIndex() > 0) {
						
							income.Apply(oUserID.getUserId(), 
									data.getIncActIndex(), session, data.getAmount(),
									data.getRefTypeName());
							canArchive = true;
						}
						
						if (canArchive) {
							
							session.update("ArchiveJournalTran", data);
							session.commit();
							//check if transaction was copied
							
							JournalUploadData backupData = (JournalUploadData)session.selectOne("GetArchivedJournalTranLine", data); 
							if (backupData != null){
								session.update("DeleteJournalLine", backupData);
								session.commit();
							}
							
							canArchive = false;
							
						}
					}
					
				}
				
			}
			
			journalTran = session.selectList("GetJournalTran", oUserID);
			
			journalCount = journalTran.size(); 
			
		} catch (Exception e ) {
			addActionError(e.toString());
			return INPUT;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	public String getIsSecure() {
		return sIsSecure;
	}

	public void setIsSecure(String sIsSecure) {
		this.sIsSecure = sIsSecure;
	}

	public List getJournalTran() {
		return journalTran;
	}

	public void setJournalTran(List journalTran) {
		this.journalTran = journalTran;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getRefNum() {
		return refNum;
	}

	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}

	public int getJournalCount() {
		return journalCount;
	}

	public void setJournalCount(int journalCount) {
		this.journalCount = journalCount;
	}

	
	
}
