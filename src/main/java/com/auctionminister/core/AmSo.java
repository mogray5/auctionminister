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
package com.auctionminister.core;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.ibatis.session.SqlSession;

import com.auctionminister.data.ItemData;
import com.auctionminister.data.SaBatchData;
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.SaLineData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.exceptions.AmSoException;
import com.auctionminister.params.AccountParams;
import com.auctionminister.params.AdjustItemParams;
import com.auctionminister.params.SaLookupParams;


/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AmSo {

	//private String itemId = "";
	//private ItemData oItem = null;
	//private String isSecure = "S";
	
	
	//form data
	//private String batchId = "";
	//private long customerId = 0;
	
	/**
	 * 
	 */
	public AmSo() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public long AddBatch (String batch, long customer, 
				UserSmallData oUserID, SqlSession session) throws AmSoException{
		
		//batchId = batch;
		//customerId = customer;
		GregorianCalendar gc = new GregorianCalendar();
		long docId =0;
		
		try {
		
			//	check if passed batch already exists
			SaBatchData batchSearch = new SaBatchData();
			batchSearch.setBatchId(batch);
			batchSearch.setUserId(oUserID.getUserId());
			SaBatchData batchdata = (SaBatchData) session.selectOne("GetOpenSoBatch", batchSearch);
	
			if (batchdata==null){
				
				//create a new batch
		
				batchSearch.setBatchDate(gc.getTime());
				session.update("AddOpenSoBatch", batchSearch);
				session.commit();
				Long doc = (Long)session.selectOne("GetLastOpenSoBatch", oUserID);
				
				if (doc !=null){
					docId = doc.longValue();
				}
			
			} else {
				docId = batchdata.getDocId();
			}
			
		} catch (Exception e){
			throw new AmSoException(e.getMessage());
		}
		
		return docId;
	}
	
	public SaHeaderData AddHeader(SqlSession session, 
				UserSmallData oUserID, 
				long customerId, 
				long docId, float fee)  throws AmSoException{
		
		SaHeaderData so = null;
		
		try {
			GregorianCalendar gc = new GregorianCalendar();
			
			so = new SaHeaderData();
			so.setDocId(docId);
			so.setUserId(oUserID.getUserId());
			so.setCustomerId(customerId);
			so.setSoDate(gc.getTime());
			so.setPaypalFee(0);
			so.setSaved(0);
			session.update("AddSoHeader", so);
			
			Long soNumber = (Long)session.selectOne("GetLastOpenSoHeader", oUserID);
			so.setSoNumber(soNumber.longValue());
			session.commit();
			
		} catch (Exception e){
			throw new AmSoException(e.getMessage());
	}
		return so;
	}
	
	public void AddSoLine(SqlSession session, SaLineData line) throws AmSoException{
		
		try {
			
			session.update("DeleteOpenSoLine", line);
			session.update("AddOpenSoLine", line);
			session.commit();
			
		} catch (Exception e){
			throw new AmSoException(e.getMessage());
			}
	}
	
	public void Return(UserSmallData oUserID, SqlSession session, long soNumber, 
			String itemId) throws AmSoException{
		
		
		try {
		
			GregorianCalendar gc = new GregorianCalendar();
			AccountParams account = new AccountParams();
			account.setUserId(oUserID.getUserId());
			account.setMonth(gc.get(Calendar.MONTH) + 1);
			account.setYear(gc.get(Calendar.YEAR));
			AdjustItemParams itemparams = new AdjustItemParams();
			SaLineData line = null;
			SaLookupParams search = new SaLookupParams();
			ItemData item = null;
			
		
			search.setUserId(oUserID.getUserId());
			search.setSoNumber(soNumber);
			search.setItemId(itemId);
		
			line = (SaLineData)session.selectOne("GetClosedSoLine", search);
		
			if (line==null){
				throw new AmSoException("Could not find sale item in database." + soNumber);
			}

			itemparams.setItemId(line.getItemId());
			itemparams.setUserId(oUserID.getUserId());
			itemparams.setAdjustVal(line.getQtySold());
					
			session.update("AdjustOnHand", itemparams);
			itemparams.setAdjustVal(line.getQtySold());
			session.update("AdjustQtyReturned", itemparams);
						
			item = (ItemData)session.selectOne("GetItem", itemparams);


			account.setAdjustVal(new Double(line.getQtySold()).doubleValue() * line.getSalePrice());
			account.setAccountIndex(AM.RETURNS_SALES);
			session.update("DebitAccount", account);
			session.update("DebitMonthlyAccount", account);
			account.setAccountIndex(AM.CASH);
			session.update("CreditAccount", account);
			session.update("CreditMonthlyAccount", account);

			account.setAdjustVal(1D * line.getQtySold() * item.getCurrentCost());
			account.setAccountIndex(AM.INVENTORY);
			session.update("DebitAccount", account);
			session.update("DebitMonthlyAccount", account);
			account.setAccountIndex(AM.COGS);
			session.update("CreditAccount", account);
			session.update("CreditMonthlyAccount", account);
				
			session.update("DeleteClosedSoLine", line);
			session.commit();
			
			
		} catch (Exception e){
			try {session.rollback();} catch (Exception sqle){}
			throw new AmSoException("AmSo.Return error:" + e.getMessage());
		}
	}
	
}
