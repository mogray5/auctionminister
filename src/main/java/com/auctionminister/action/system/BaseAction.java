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
package com.auctionminister.action.system;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.struts2.ServletActionContext;

import com.auctionminister.data.UserSmallData;
import com.auctionminister.exceptions.DataNotAvailableException;
import com.auctionminister.exceptions.UserNotAuthenticatedException;
import com.auctionminister.providers.SQLMapProvider;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = -803537679733433269L;
	protected SQLMapProvider mapPro=null;
	protected SqlSession session = null;
	
	public BaseAction(){		
		mapPro = (SQLMapProvider)ServletActionContext.getServletContext().getAttribute("sqlMap");
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public void startSession() throws IOException, DataNotAvailableException {
		
		if (mapPro==null){
			throw new DataNotAvailableException("Map provider not initialized.");
		}
		
		session = mapPro.getSqlMap().openSession();
	}
	
	public void endSession(){
		
		try {session.close();} catch(Exception e){}
		
	}
	
	public UserSmallData verifyLogin() throws UserNotAuthenticatedException{
		
		UserSmallData oUserID = (UserSmallData) ActionContext.getContext().getSession().get("USERID");
		
		if (oUserID == null){
			throw new UserNotAuthenticatedException();
		}
		
		return oUserID;
		
	}
}
