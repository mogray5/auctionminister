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

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.ItemClassData;
import com.auctionminister.data.UserSmallData;


/**
 * @author wggray
 */
public class EditItemClassAction extends BaseAction  {

	private static final long serialVersionUID = 8313874058439460333L;
	private String sIsSecure = "S";
	private String className = null;
	private String classDesc = null;
	private long classId = 0;
	private String sStatus = null;
	
	public EditItemClassAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				ItemClassData search = new ItemClassData();
				search.setUserId(oUserID.getUserId());
				search.setClassName(className);
				search.setClassId(classId);
				search.setClassDesc(classDesc);
				
				ItemClassData itemClass = null;
				
				
				if (classId==0){
						
					sStatus += "classid = 0\n";
					//adding a new class check if new name already exists
				
					itemClass = (ItemClassData) session.selectOne("GetItemClassByName", search);
				
					if (itemClass==null){
						sStatus += "itemClass is null\n";
						//add class
						search.setClassDesc(classDesc);
						session.update("AddItemClass", search);
						session.commit();
					} else {
						
						sStatus += "new name already exists\n";
						
						//New name already exists in database,
						//raise error at this point
						//Need calling process to pass a clasid if editing
						addActionError("This item class allready exists.  Please edit the existing class or choose a new class name.");
						return INPUT;
					}
					
					
				} else {
					
					sStatus += "classid = " + Long.toString(classId) + "\n";
					
					//get the class by name
					itemClass = (ItemClassData) session.selectOne("GetItemClassByName", search);
					
					if (itemClass != null){
						
						sStatus += "itemClass is not null\n";
						
						//class already exists make sure id's are the same
						if (itemClass.getClassId() == search.getClassId()){
							
							sStatus += "classids match\n";
							
							//edit class
							session.update("EditItemClass", search);
							session.commit();
							
						} else {
							sStatus += "classids do not match\n";
							//new class name chosen that already exists, raise error
							addActionError("You have renamed this item class with a name that already exists.  Please choose an different name.");
							return INPUT;
						}
					} else {
						//edit class
						session.update("EditItemClass", search);
						session.commit();

					}
				}
				
				
			} catch (Exception e ) {
				addActionError(e.toString());
				return ERROR;
			} finally {
				this.endSession();
			}
			
			return SUCCESS;
	}
	
	
	
	/**
	 * @return Returns the classDesc.
	 */
	public String getClassDesc() {
		return classDesc;
	}
	/**
	 * @param classDesc The classDesc to set.
	 */
	public void setClassDesc(String classDesc) {
		this.classDesc = classDesc;
	}
	/**
	 * @return Returns the className.
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className The className to set.
	 */
	public void setClassName(String className) {
		this.className = className;
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
	
	
	/**
	 * @return Returns the classId.
	 */
	public long getClassId() {
		return classId;
	}
	/**
	 * @param classId The classId to set.
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
	
	/**
	 * @return Returns the sStatus.
	 */
	public String getStatus() {
		return sStatus;
	}
	/**
	 * @param status The sStatus to set.
	 */
	public void setStatus(String status) {
		sStatus = status;
	}
}
