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

import com.auctionminister.util.AmFormat;

public class RptSalesActivity {
	
	private long userId;
	private int month;
	private int year;
	private String itemId;
	private double sales;
	private double profit;
	private long qty;
	
	private AmFormat format = new AmFormat();
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public String getSalesAsString() {
		return format.getRounded(sales);
	}
	public long getQty() {
		return qty;
	}
	public String getQtyAsString() {
		return format.getRounded(qty);
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	public double getProfit() {
		return profit;
	}
	public String getProfitAsString() {
		return format.getRounded(profit);
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	
	
	
	

}
