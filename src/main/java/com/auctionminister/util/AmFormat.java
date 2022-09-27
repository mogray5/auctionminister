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
package com.auctionminister.util;

import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AmFormat {

	/**
	 * 
	 */
	public AmFormat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFmtDate(Date dt){
		
		Date dtDate = dt;
		
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String sDTFmt = format.format(dtDate);
		
		return sDTFmt;
	}

	public String[] split(String sLine, char sChar){
		
		String[] sResult = null;
		ArrayList clBuffer = new ArrayList();
		char[] aLine = sLine.toCharArray();
		int iSplitIndex=0;
		
		for (int i=0; i<aLine.length; i++){
			if (aLine[i] == sChar){
				clBuffer.add(sLine.substring(iSplitIndex, i));
				iSplitIndex = i+1;
			}
		}
		
		//save last piece
		if (sLine.length()>0){
			clBuffer.add(sLine.substring(iSplitIndex));
		}
		
		sResult = new String[clBuffer.size()];
		for (int j=0; j<clBuffer.size(); j++){
			sResult[j] = (String)clBuffer.get(j);
		}
		return sResult;
	}
	
	public String getRounded(double num){
		DecimalFormat fmt = new DecimalFormat("#,##0.00");
		return fmt.format(num);
	}
	
}
