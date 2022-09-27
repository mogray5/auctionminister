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
package com.auctionminister.providers;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


/**
 * @author wggray
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SQLMapProvider {

	SqlSessionFactory sqlmap = null;

	/**
	 * 
	 */
	public SQLMapProvider() {
		super();
	}

	public SqlSessionFactory getSqlMap() throws IOException {
		
		if (sqlmap==null){
			String resource = "com/auctionminister/data/mybatis.config.xml";
			Reader reader = Resources.getResourceAsReader (resource);
			sqlmap = new SqlSessionFactoryBuilder().build(reader,"development");
			
		}
		
		return sqlmap;
		
	}

}
