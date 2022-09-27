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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.auctionminister.data.UploadData;
import com.auctionminister.exceptions.LineParseError;
import com.auctionminister.params.UploadParams;
import org.apache.ibatis.session.SqlSession;

public class LineParser {

	DateFormat dateFormat = new SimpleDateFormat("y-M-d");
	
	public UploadData ParseLine(String sLine, long userId, long batchId) throws LineParseError, ParseException{
		
		UploadData dat = new UploadData();
		String[] aLine = null;
		
		
		// addActionError(sLine);
		aLine = sLine.split(",");

		if (aLine == null) {
			throw new LineParseError();
		}
		
		//dat = new UploadData();

		dat.setUserId(userId);
		dat.setBatchId(batchId);
		
		if (aLine[5] != null) {
			//sStatus = "BuyerID: " + aLine[5];
			dat.setTmpBuyerId(Clean(aLine[5]));
		}

		// if (aLine[0]!=null){
		// if (aLine[0].length()>0){
		// sStatus = "TmpDate: " + aLine[0];
		dat.setTmpDate(dateFormat
				.parse(Clean(aLine[0].substring(0, 10))));
		// }
		// }

		if (aLine[4] != null) {
			if (aLine[4].length() > 0) {
				//sStatus = "TmpPrice: " + aLine[4];
				dat.setTmpPrice(new Float(Clean(aLine[4]))
								.floatValue());
			}
		}

		if (aLine[2] != null) {
			if (aLine[2].length() > 0) {
				//sStatus = "TmpQuantity: " + aLine[2];
				dat.setTmpQuantity(Integer.parseInt(Clean(aLine[2])));
			}
		}

		// if (aLine[21]!=null){
		// if (aLine[21].length()>0){
		// sStatus = "TmpInsuranceAmt: " + aLine[21];
		dat.setTmpInsuranceAmt(new Float(0));
		// }
		// }

		if (aLine[3] != null) {
			//sStatus = "TmpItemId: " + aLine[3];
			dat.setTmpItemId(Clean(aLine[3]));
			dat.setItemId(Clean(aLine[3]));
		}

		if (aLine[3] != null) {
			//sStatus = "TmpItemTitle: " + aLine[3];
			dat.setTmpItemTitle(Clean(aLine[3]));
		}

		// if (aLine[29]!=null){
		// sStatus = "TmpItemUrl: " + aLine[29];
		dat.setTmpItemUrl("NA");
		// }

		if (aLine[5] != null) {
			//sStatus = "TmpName: " + aLine[5];
			dat.setTmpName(Clean(aLine[5]));
		}

		if (aLine[1] != null) {
			//sStatus = "TmpRefTxnId: " + aLine[1];
			dat.setTmpRefTxnId(Clean(aLine[1]));
		}

		if (aLine[6] != null) {
			//sStatus = "TmpShipAddress: " + aLine[6];
			dat.setTmpShipAddress(Clean(aLine[6]));
		}

		if (aLine[6] != null) {
			//sStatus = "TmpType: " + aLine[6];
			dat.setTmpType(Clean(aLine[6]));
		}

		// if (aLine[4]!=null){
		// if (aLine[5].length()>0){
		// sStatus = "TmpShipAmt: " + aLine[5];
		dat.setTmpShipAmt(new Float(0));
		// }
		// }

		// if (aLine[6]!=null){
		// sStatus = "TmpSubject: " + aLine[6];
		dat.setTmpSubject("No Subject");
		// }

		// if (aLine[22]!=null){
		// if (aLine[22].length()>0){
		// sStatus = "TmpTaxAmt: " + aLine[22];
		dat.setTmpTaxAmt(0F);
		// }
		// }

		// if (aLine[12]!=null){
		// sStatus = "TmpToEmail: " + aLine[12];
		dat.setTmpToEmail("NA");
		// }

		/*
		 * Purchase = 1 **NOT USED Sale = 0 Refund to Customer = 2
		 * Refund to you = 3 Ebay charge = 4 paypal added fee = 5
		 * shipping charge=6 other charge=7
		 * 
		 */

		// Possible Sale, include
		if (aLine[7].equalsIgnoreCase("sell")) {
			dat.setAmInclude(1);
			dat.setAmType(0);
		} else if (aLine[7].equalsIgnoreCase("buy")) {
			dat.setAmInclude(1);
			dat.setAmType(1);
		} else {
			dat.setAmInclude(0);
			dat.setAmType(7);
		}

		
		return dat;
	}
	
	private String Clean(String sVal) {
		String sTmp = sVal.replaceAll("\"", "");

		if (sTmp.startsWith("\"")) {
			sTmp = sTmp.substring(1);
		}

		return sTmp;
	}
	
	public Long getNewBatchId(SqlSession session, long lUserID) {

		Long result = null;

		try {

			// get a new batch ID
			UploadParams uparams = new UploadParams();
			Calendar cal = Calendar.getInstance();
			Date dt = cal.getTime();
			uparams.setUpDate(dt);
			uparams.setUserId(lUserID);

			session.update("AddNewUploadBatch", uparams);
			session.commit();

			result = (Long) session.selectOne("GetBatchId",
					Long.valueOf(lUserID));

		} catch (Exception e) {
			//addActionError(e.toString());
			result = null;
		}

		return result;

	}
	
}
