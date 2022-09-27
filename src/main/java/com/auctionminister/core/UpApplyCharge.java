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
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.auctionminister.data.UploadData;
import com.auctionminister.exceptions.AmUploadException;
import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpApplyCharge {

	private SqlSession session = null;
	private long batchId = 0;
	private long userId = 0;
	
	/**
	 * 
	 */
	public UpApplyCharge(SqlSession s, long batch, long user) {
		session = s;
		batchId=batch;
		userId=user;
	
	}
/*
	public void Apply()  throws AmUploadException{
		
		UploadData tranline = null;
		AmCharge charge = new AmCharge();
		AmFormat fmt = new AmFormat();
		UploadData search = new UploadData();
		search.setBatchId(batchId);
		search.setUserId(userId);
		GregorianCalendar gc = new GregorianCalendar();
		
		try {
		
			List chargeList = session.selectList("GetChargesList", search);
			
			if (chargeList != null && chargeList.size()>0){
				for (int i=0; i< chargeList.size(); i++){
										
					tranline = (UploadData)chargeList.get(i);
					switch (tranline.getAmType()){
						case 4:
							charge.Apply(userId, 1, session, tranline.getTmpPrice()* -1 * tranline.getTmpQuantity());
							break;
						case 5:
							charge.Apply(userId, 3, session, tranline.getTmpPrice()* -1 * tranline.getTmpQuantity());
							break;
						case 6:
							charge.Apply(userId, 2, session, tranline.getTmpPrice()* -1 * tranline.getTmpQuantity());
							break;
						case 7:
							charge.Apply(userId, 4, session, tranline.getTmpPrice()* -1 * tranline.getTmpQuantity());
							break;
					}
					
					tranline.setAmStatus(2);
					tranline.setAmMsg("Charge applied on " + 
								fmt.getFmtDate(gc.getTime()) + 
								"  Amount: " +
								fmt.getRounded(tranline.getTmpPrice()*-1 * tranline.getTmpQuantity()) + 
								"  Fee applied:  " +
								fmt.getRounded(tranline.getTmpPrice() * -1 * tranline.getTmpQuantity()));
					session.update("EditTranStatus", tranline);
				
					session.commit();
				}
			}
		} catch (Exception e){
			try{session.rollback();} catch (Exception ex){}
			throw new AmUploadException(e.getMessage());
		} finally {
			try {session.close();} catch(Exception e){}
		}

	}
*/
}
