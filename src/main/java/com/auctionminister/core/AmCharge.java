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

import com.auctionminister.exceptions.AmSoException;
import com.auctionminister.params.AccountParams;

/**
 * @author wggray
 */
public class AmCharge {

	public AmCharge() {
		super();
	}
	
	public void Apply(long userId, int costType, 
			SqlSession session, double costAmount, String trxRef) throws AmSoException{
		
		
		try {
		
			GregorianCalendar gc = new GregorianCalendar();
			AccountParams account = new AccountParams();
			account.setUserId(userId);
			account.setMonth(gc.get(Calendar.MONTH) + 1);
			account.setYear(gc.get(Calendar.YEAR));
			account.setTrxDate(gc.getTime());

			account.setAdjustVal(costAmount);
			account.setAccountIndex(AM.CASH);
			session.update("CreditAccount", account);
			session.update("CreditMonthlyAccount", account);
			account.setTrxRef("non merchandise charge");
			session.update("RecordCreditTransaction", account);
		
			account.setAccountIndex(costType);
			account.setTrxRef(trxRef);
			
			session.update("DebitAccount", account);
			session.update("DebitMonthlyAccount", account);
			session.update("RecordDebitTransaction", account);

			session.commit();
		
		} catch (Exception e){
			throw new AmSoException("Apply Charge Error:  " + 
					e.getMessage());
		}
	}

}
