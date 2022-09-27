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
package com.auctionminister.exceptions;

public class UserNotAuthenticatedException extends Exception {
	private static final long serialVersionUID = -3683244561753944809L;
	String msg = null;
	
	public UserNotAuthenticatedException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public UserNotAuthenticatedException(String arg0) {
		super(arg0);
		msg = arg0;
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public UserNotAuthenticatedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 */
	public UserNotAuthenticatedException(Throwable arg0) {
		super(arg0);
	}

	public String getMessage() {
		return msg;
	}
}
