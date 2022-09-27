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
package com.auctionminister.data;

import java.io.Serializable;
import java.util.Date;

import com.auctionminister.util.AmFormat;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AccountData implements Serializable {

	private long userId = 0;
	private int accountIndex = 0;
	private String accountDescription="";
	private double creditBalance = 0;
	private double debitBalance = 0;
	private int year = 0;
	private String trxRef = "";
	private Date trxDate;
	private int month = 0;
	
	private AmFormat format = new AmFormat();
	
	/**
	 * 
	 */
	public AccountData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	/**
	 * @return Returns the accountIndex.
	 */
	public int getAccountIndex() {
		return accountIndex;
	}
	/**
	 * @param accountIndex The accountIndex to set.
	 */
	public void setAccountIndex(int accountIndex) {
		this.accountIndex = accountIndex;
	}
	/**
	 * @return Returns the creditBalance.
	 */
	public double getCreditBalance() {
		return creditBalance;
	}
	public String getCreditBalanceAsString() {
		return format.getRounded(creditBalance);
	}
	/**
	 * @param creditBalance The creditBalance to set.
	 */
	public void setCreditBalance(double creditBalance) {
		this.creditBalance = creditBalance;
	}
	/**
	 * @return Returns the debitBalance.
	 */
	public double getDebitBalance() {
		return debitBalance;
	}
	public String getDebitBalanceAsString() {
		return format.getRounded(debitBalance);
	}
	/**
	 * @param debitBalance The debitBalance to set.
	 */
	public void setDebitBalance(double debitBalance) {
		this.debitBalance = debitBalance;
	}
	/**
	 * @return Returns the userId.
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getGlAmountAsString(){
		return format.getRounded(creditBalance - debitBalance);
	}

	/**
	 * @return Returns the year.
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year The year to set.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	
	/**
	 * @return Returns the trxDate.
	 */
	public Date getTrxDate() {
		return trxDate;
	}
	/**
	 * @param trxDate The trxDate to set.
	 */
	public void setTrxDate(Date trxDate) {
		this.trxDate = trxDate;
	}
	/**
	 * @return Returns the trxRef.
	 */
	public String getTrxRef() {
		return trxRef;
	}
	/**
	 * @param trxRef The trxRef to set.
	 */
	public void setTrxRef(String trxRef) {
		this.trxRef = trxRef;
	}



	public String getAccountDescription() {
		return accountDescription;
	}



	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}



	public int getMonth() {
		return month;
	}



	public void setMonth(int month) {
		this.month = month;
	}
	
	
	
}
