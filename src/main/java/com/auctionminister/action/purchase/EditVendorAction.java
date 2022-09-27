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
package com.auctionminister.action.purchase;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.data.VendorData;
import com.auctionminister.data.VendorSmallData;

/**
 * @author wggray
 */
public class EditVendorAction extends BaseAction {

	private static final long serialVersionUID = -2332910130286884258L;
	private String sIsSecure = "S";
	private int vendorId = 0;
	private String vendorName = null;
	private String taxId = null;
	private String address1 = null;
	private String address2 = null;
	private String address3 = null;
	private String city = null;
	private String state = null;
	private String zip = null;
	private String country = null;
	private String phone1 = null;
	private String phone2 = null;
	private String fax = null;
	private int status = 0;
	UserSmallData oUserID = null;
	
	public EditVendorAction() {
		super();
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
	 * @return Returns the sStatus.
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * @return Returns the vendorId.
	 */
	public long getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId The vendorId to set.
	 */
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				oUserID = this.verifyLogin();
				VendorSmallData search = new VendorSmallData();
				search.setUserId(oUserID.getUserId());
				search.setVendorId(vendorId);
				
				VendorData vendor = null;
				
				addActionError("Vendor:  " + Integer.toString(vendorId));
				
				if (vendorId==0){
						
					//adding a new class check if new name already exists
				
					vendor = (VendorData) session.selectOne("GetVendorByName", search);
				
					if (vendor==null){
						//add vendor
						vendor = getVendorInfo();

						session.update("AddVendor", vendor);
						session.commit();
					} else {
						
						//new name already exists in database,
						//raise error at this point
						//Need calling process to pass a clasid if editing
						addActionError("This vendor allready exists.  Please choose another name.");
						return INPUT;
					}
					
					
				} else {
					
					//get the class by name
					vendor = (VendorData) session.selectOne("GetVendorByName", search);
					
					if (vendor != null){
						
						//class already exists make sure id's are the same
						if (vendor.getVendorId() == search.getVendorId()){
							
							vendor = getVendorInfo();
							
							//edit vendor
							session.update("EditVendor", vendor);
							session.commit();
							
						} else {
							//new class name chosen that already exists, raise error
							addActionError("You have renamed this vendor to a name that already exists.  Please choose an different name.");
							return INPUT;
						}
					} else {
						//edit class
						vendor = getVendorInfo();
						session.update("EditVendor", vendor);
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
	 * @return Returns the country.
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country The country to set.
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return Returns the fax.
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * @param fax The fax to set.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * @return Returns the phone1.
	 */
	public String getPhone1() {
		return phone1;
	}
	/**
	 * @param phone1 The phone1 to set.
	 */
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	/**
	 * @return Returns the phone2.
	 */
	public String getPhone2() {
		return phone2;
	}
	/**
	 * @param phone2 The phone2 to set.
	 */
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
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
	 * @return Returns the taxId.
	 */
	public String getTaxId() {
		return taxId;
	}
	/**
	 * @param taxId The taxId to set.
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}
	/**
	 * @return Returns the vendorName.
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName The vendorName to set.
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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
	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	private VendorData getVendorInfo(){

		VendorData vendor = new VendorData();
		vendor.setUserId(oUserID.getUserId());
		vendor.setVendorId(vendorId);
		vendor.setAddress1(address1);
		vendor.setAddress2(address2);
		vendor.setAddress3(address3);
		vendor.setCity(city);
		vendor.setCountry(country);
		vendor.setFax(fax);
		vendor.setPhone1(phone1);
		vendor.setPhone2(phone2);
		vendor.setState(state);
		vendor.setStatus(status);
		vendor.setTaxId(taxId);
		vendor.setVendorName(vendorName);
		vendor.setZip(zip);
		return vendor;
	}
}
