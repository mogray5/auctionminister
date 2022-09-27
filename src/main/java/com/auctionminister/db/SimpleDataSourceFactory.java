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
package com.auctionminister.db;

import javax.sql.*;
import java.sql.*;
import java.util.*;
import javax.naming.*;

import com.auctionminister.exceptions.*;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SimpleDataSourceFactory {
	
	private Connection conn = null;
	
	/**
	 * 
	 */
	public SimpleDataSourceFactory() {
		super();
	}

	public Connection getConnection() throws AmReportException{
//		 Obtain our environment naming context
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

//		 	Look up our data source
			DataSource ds = (DataSource)
			envCtx.lookup("jdbc/minister");

//		 Allocate and use a connection from the pool
			conn = ds.getConnection();
		
		} catch (NamingException ne){
			throw new AmReportException("Get Connection naming exception: " + ne.getMessage());
		} catch (SQLException sqle){
			throw new AmReportException("Get Connection SQL exception: " + sqle.getMessage());
		}
		
		return conn;
	}
	
	public void Close(){
		if (conn!=null){
			try {
				conn.close();
			} catch (Exception e){}
		}
	}

}
