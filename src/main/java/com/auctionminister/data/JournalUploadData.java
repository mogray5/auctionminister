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

import java.util.Date;

import com.auctionminister.util.AmFormat;

public class JournalUploadData {

	private long userId = 0;
	private Date tranDate;
	private String refNum = null;
	private int refType = 0;
	private String refTypeName = null;
	private String owner1 = null;
	private String owner2 = null;
	private String argName1 = null;
	private double amount = 0;
	private double balance = 0;
	private int isDup = 0;
	private int costActIndex = 0;
	private int incActIndex = 0;
	
	private AmFormat format = new AmFormat();
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Date getTranDate() {
		return tranDate;
	}
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public int getRefType() {
		return refType;
	}
	public void setRefType(int refType) {
		this.refType = refType;
	}
	
	public String getRefTypeName() {
		return refTypeName;
	}
	public void setRefTypeName(String refTypeName) {
		this.refTypeName = refTypeName;
	}
	public String getOwner1() {
		return owner1;
	}
	public void setOwner1(String owner1) {
		this.owner1 = owner1;
	}
	public String getOwner2() {
		return owner2;
	}
	public void setOwner2(String owner2) {
		this.owner2 = owner2;
	}
	public String getArgName1() {
		return argName1;
	}
	public void setArgName1(String argName1) {
		this.argName1 = argName1;
	}
	public double getAmount() {
		return amount;
	}
	public String getAmountAsString() {
		return format.getRounded(amount);
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public String getBalanceAsString() {
		return format.getRounded(balance);
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getIsDup() {
		return isDup;
	}
	public void setIsDup(int isDup) {
		this.isDup = isDup;
	}
	public int getCostActIndex() {
		return costActIndex;
	}
	public void setCostActIndex(int costActIndex) {
		this.costActIndex = costActIndex;
	}
	public int getIncActIndex() {
		return incActIndex;
	}
	public void setIncActIndex(int incActIndex) {
		this.incActIndex = incActIndex;
	}
	
}
