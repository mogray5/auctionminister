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
package com.auctionminister.interceptors;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.AccountParams;
import com.auctionminister.providers.SQLMapProvider;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


/**
 * @author wggray
 */
public class MissingRecordInterceptor implements Interceptor {

	private static final long serialVersionUID = 3318660265533761756L;

	public MissingRecordInterceptor() {
		super();
	}

	public void destroy() {
		}

	public void init() {
		}

	public String intercept(ActionInvocation actionInv) throws Exception {
	
		SQLMapProvider mapPro = (SQLMapProvider)ServletActionContext.getServletContext().getAttribute("sqlMap");
		SqlSession session = null;
		
		try {
		
			session = mapPro.getSqlMap().openSession();
		
			UserSmallData userDat = (UserSmallData) actionInv
			.getInvocationContext()
			.getSession()
			.get(
			"USERID");
			
			//calculate current month
			AccountParams account = new AccountParams();
			GregorianCalendar gc = 	new GregorianCalendar();
			account.setMonth(gc.get(Calendar.MONTH) + 1);
			account.setYear(gc.get(Calendar.YEAR));
			account.setUserId(userDat.getUserId());
			
			Integer cnt = (Integer)session.selectOne("CheckMonthlyTable", account);	
			
			if (cnt==null || cnt.intValue()==0){
				session.update("AddMonthlyLines", account);
				session.commit();
			}
			
			cnt = (Integer)session.selectOne("CheckYearlyTable", account);	
			
			if (cnt==null || cnt.intValue()==0){
				session.update("AddYearlyLines", account);
				session.commit();
			}
			
			return actionInv.invoke();
		
		} finally {
			
			try {session.close();} catch(Exception e){}
			
		}

	}

}
