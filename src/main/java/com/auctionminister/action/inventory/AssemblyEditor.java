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
package com.auctionminister.action.inventory;

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.ItemAssembly;
import com.auctionminister.data.ItemData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SearchParams;

public class AssemblyEditor extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String sIsSecure = "S";
	private List assemblyItemList;
	private List itemList;
	private List elementList;
	private String assemblyItem=null;
	private String elementItemId=null;
	private double elementQty = 0D;
	private String action="";
	
	@Override
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
			UserSmallData oUserID = this.verifyLogin();
				
			if (elementItemId != null &&
					assemblyItem != null) {
				
				ItemAssembly assembly = new ItemAssembly();
				assembly.setItemID(assemblyItem);
				assembly.setElementItemID(elementItemId);
				assembly.setElementQty(elementQty);
				assembly.setUserID(oUserID.getUserId());
				
				if (!action.equals("delete")){
					
					if(elementQty > 0){
						
						session.update("AddAssemblyElement", assembly);
						session.commit();
					}
				
				}
				else if (action.equals("delete")){
					session.update("DeleteAssemblyElement", assembly);
					session.commit();
				}
				
			} 
								
			assemblyItemList = session.selectList("GetItemAssemblies", oUserID);
			
			SearchParams itemSearch = null;
			
			if (assemblyItem != null){
				
				itemSearch = new SearchParams();
				itemSearch.setItemId(assemblyItem);
				itemSearch.setUserId(oUserID.getUserId());
				
			} else {
				
				if (assemblyItemList != null && assemblyItemList.size()>0){
					
					itemSearch = new SearchParams();
					ItemData item = (ItemData)assemblyItemList.get(0);
					itemSearch.setItemId(item.getItemId());
					itemSearch.setUserId(oUserID.getUserId());
				}
			}
			
			if (itemSearch != null){
				
				elementList = session.selectList("GetItemAssembly", itemSearch);
				itemList = session.selectList("GetItemListExclude", itemSearch);
			}
			
		} catch (Exception e ) {
			addActionError(e.toString());
			return INPUT;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	public double getElementQty() {
		return elementQty;
	}

	public void setElementQty(float elementQty) {
		this.elementQty = elementQty;
	}

	/**
	 * @return Returns the sIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}
	/**
	 * @param isSecure The sIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}

	public List getItemList() {
		return itemList;
	}

	public void setItemList(List itemList) {
		this.itemList = itemList;
	}

	public List getElementList() {
		return elementList;
	}

	public void setElementList(List elementList) {
		this.elementList = elementList;
	}

	public String getAssemblyItem() {
		return assemblyItem;
	}

	public void setAssemblyItem(String assemblyItem) {
		this.assemblyItem = assemblyItem;
	}

	public List getAssemblyItemList() {
		return assemblyItemList;
	}

	public void setAssemblyItemList(List assemblyItemList) {
		this.assemblyItemList = assemblyItemList;
	}

	public String getElementItemId() {
		return elementItemId;
	}

	public void setElementItemId(String elementItemId) {
		this.elementItemId = elementItemId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	
	
}
