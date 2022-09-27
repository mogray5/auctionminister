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
package com.auctionminister.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.auctionminister.providers.SQLMapProvider;

public class CustomServletContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		ServletContext ctx = event.getServletContext();
        
		
	     try{     
	    	 SQLMapProvider sqlMap = new SQLMapProvider();
	    	 ctx.setAttribute("sqlMap", sqlMap); //set the sqlSessionFactory as an application scoped variable
	     }
	     catch(Exception e){
	      System.out.println("FATAL ERROR: myBatis could not be initialized");
	      System.exit(1);
	     }    

	}

}
