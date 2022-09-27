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
package com.auctionminister.action.system;

import com.auctionminister.data.UserData;
import com.auctionminister.params.LoginParams;

/**
 * @author wggray
 */
public class AddUserAction extends BaseAction {

	private static final long serialVersionUID = -5109483887000994316L;
	private String isSecure = "S";
		
	//user data
	private String userName = "";
	private String firstName = "";
	private String lastName = "";
	private String middleInitial = "";
	private String pswd = "";
	private String cpswd = "";
	private String address1 = "";
	private String address2 = "";
	private String address3 = "";
	private String city = "";
	private String state = "";
	private String zip = "";
	private String phone = "";
	UserData userData = null;
	
	public AddUserAction() {
		super();
	}

	/**
	 * @return Returns the isSecure.
	 */
	public String getIsSecure() {
		return isSecure;
	}
	/**
	 * @param isSecure The isSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		this.isSecure = isSecure;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
							
				//make sure passwords match
				if (!cpswd.equals(pswd)){
					addActionError("p1:" + pswd + " p2: " + cpswd);
					addActionError("Passwords do not match.  Please try again");
					return ERROR;
				}
								
				userData = getUserData();
				
				session.update("AddUser", userData);
				
				session.commit();
				
				LoginParams login= new LoginParams();
				login.setUserName(userName);
				login.setPwd(pswd);
				Integer iUserId = (Integer)session.selectOne("GetUserID", login);
				
				if (iUserId==null){
					addActionError("Error creating user account.");
					return ERROR;
				}
				
		} catch (Exception e ) {
			session.rollback();
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	
	
	/**
	 * @return Returns the address1.
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 The address1 to set.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return Returns the address2.
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 The address2 to set.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return Returns the address3.
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 The address3 to set.
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return Returns the firstName.
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName The firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return Returns the lastName.
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName The lastName to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return Returns the middleInitial.
	 */
	public String getMiddleInitial() {
		return middleInitial;
	}
	/**
	 * @param middleInitial The middleInitial to set.
	 */
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return Returns the pswd.
	 */
	public String getPswd() {
		return pswd;
	}
	/**
	 * @param pswd The pswd to set.
	 */
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	/**
	 * @return Returns the state.
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state The state to set.
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return Returns the zip.
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip The zip to set.
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	private UserData getUserData(){
		
		UserData user = new UserData();
		if (address1!=null){
			user.setAddress1(address1);
		}
		if (address2!=null){
			user.setAddress2(address2);
		}
		if (address3!=null){	
			user.setAddress3(address3);
		}
		if (city!=null){
			user.setCity(city);
		}
		if (firstName!=null){
			user.setFirstName(firstName);
		}
		if (lastName!=null){
			user.setLastName(lastName);
		}
		if (middleInitial!=null){
			user.setMiddleInitial(middleInitial);
		}
		if (phone!=null){
			user.setPhone(phone);
		}
		if (pswd!=null){
			user.setPswd(pswd);
		}
		if (state!=null){
			user.setState(state);
		}
		if (userName!=null){
			user.setUserName(userName);
		}
		if (zip!=null){
			user.setZip(zip);
		}

		return user;
		
	}
	
	
	/**
	 * @return Returns the cpswd.
	 */
	public String getCpswd() {
		return cpswd;
	}
	/**
	 * @param cpswd The cpswd to set.
	 */
	public void setCpswd(String cpswd) {
		this.cpswd = cpswd;
	
	}
	
	
	/**
	 * @param userData The userData to set.
	 */
	public void setUserData(UserData userData) {
		this.userData = userData;
	}
}
