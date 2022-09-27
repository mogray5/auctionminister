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

import java.util.GregorianCalendar;

import org.apache.ibatis.session.SqlSession;

import com.auctionminister.data.PoBatchData;
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.PoLineData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.exceptions.AmPoException;

public class AmPo {

	public long AddBatch (String batch, long customer, 
			UserSmallData oUserID, SqlSession session) throws AmPoException{
	
	//batchId = batch;
	//customerId = customer;
	GregorianCalendar gc = new GregorianCalendar();
	long docId =0;
	
	try {
	
		//	check if passed batch already exists
		PoBatchData batchSearch = new PoBatchData();
		batchSearch.setBatchId(batch);
		batchSearch.setUserId(oUserID.getUserId());
		PoBatchData batchdata = (PoBatchData) session.selectOne("GetOpenPoBatch", batchSearch);

		if (batchdata==null){
			
			//create a new batch
	
			batchSearch.setBatchDate(gc.getTime());
			session.update("AddOpenPoBatch", batchSearch);
			Long doc = (Long)session.selectOne("GetLastOpenPoBatch", oUserID);
			
			if (doc !=null){
				docId = doc.longValue();
			}
		
		} else {
			docId = batchdata.getDocId();
		}
		
	} catch (Exception e){
		throw new AmPoException(e.getMessage());
	}
	
	return docId;
}

public PoHeaderData AddHeader(SqlSession session, 
			UserSmallData oUserID, 
			int vendorId, 
			long docId)  throws AmPoException{
	
	PoHeaderData po = null;
	
	try {
		GregorianCalendar gc = new GregorianCalendar();
		
		//now add header
		po = new PoHeaderData();
		po.setDocId(docId);
		po.setUserId(oUserID.getUserId());
		po.setVendorId(vendorId);
		po.setPoDate(gc.getTime());
		po.setSaved(0);
		session.update("AddOpenPoHeader", po);
		session.commit();
		Long poNumber = (Long)session.selectOne("GetLastOpenPoNumber", docId);
		po.setPoNumber(poNumber.longValue());
		
	} catch (Exception e){
		throw new AmPoException(e.getMessage());
}
	return po;
}

public void AddPoLine(SqlSession session, PoLineData line) throws AmPoException{
	
	try {
		
		session.update("DeleteOpenPoLine", line);
		session.update("AddOpenPoLine", line);
		session.commit();

	} catch (Exception e){
		throw new AmPoException(e.getMessage());
		}
}

}
