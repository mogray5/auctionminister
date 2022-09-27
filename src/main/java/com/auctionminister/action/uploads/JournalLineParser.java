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

import com.auctionminister.data.JournalUploadData;
import com.auctionminister.exceptions.LineParseError;

public class JournalLineParser {
DateFormat dateFormat = new SimpleDateFormat("y-M-d");
	
	public JournalUploadData ParseLine(String sLine, long userId) throws LineParseError, ParseException{
		
		JournalUploadData dat = new JournalUploadData();
		String[] aLine = null;
		
		aLine = sLine.split(",");

		if (aLine == null) {
			throw new LineParseError();
		}
		
		dat.setUserId(userId);
		
		if (aLine[0] != null) {
			dat.setTranDate(dateFormat
				.parse(Clean(aLine[0].substring(0, 10))));
		}

		if (aLine[1] != null) {
			dat.setRefNum(aLine[1]);
		}
		if (aLine[2] != null) {
			dat.setRefType(new Integer(Clean(aLine[2])).intValue());
		}
		if (aLine[3] != null) {
			dat.setOwner1(aLine[3]);
		}
		if (aLine[4] != null) {
			dat.setOwner2(aLine[4]);
		}
		if (aLine[5] != null) {
			dat.setArgName1(aLine[5]);
		}
		if (aLine[6] != null) {
			if (aLine[6].length() > 0) {
				dat.setAmount(new Float(Clean(aLine[6]))
								.floatValue()*-1D);
			}
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

}
