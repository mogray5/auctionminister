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

public interface GlMapper {

	void doDebitAccount();
	void doCreditAccount();
	void doDebitMonthlyAccount();
	void doCreditMonthlyAccount();
	AccountData getAccount();
	/*
	List<LinkData> getLinkData(LinkDataArgs args);
	List<LinkData> getReportList(ReportListArgs args);
	List<String> getUserList(LinkDataArgs args);
	List<String> getUserListAll(LinkDataArgs args);
	List<EntityArgs> getDisplayTypes();
	UserData getUser(String arg);
	ReportRangeDefault getReportDefaultRange(String arg);
	*/
}
