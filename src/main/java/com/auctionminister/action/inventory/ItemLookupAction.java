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
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SearchParams;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author wggray
 */
public class ItemLookupAction extends BaseAction {

	private static final long serialVersionUID = 7044511422945683626L;
	private String sItemId = "";
	private List lstItems = null;
	private String sIsSecure = "S";
	private int iPage =0;
	private int iCurrPage = 0;
	private int iMaxPages = 0;
	private String sReload = null;
	private Long lItemCount = null;
	private String caller = null;
	
	public ItemLookupAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();

				if (sReload!=null){
					
					//perform new search
					SearchParams search = new SearchParams();
					search.setUserId(oUserID.getUserId());
					if (sItemId != null && sItemId.length()>0){
						search.setSearchVal(sItemId + "%");
					} else {
						search.setSearchVal(sItemId);
					}
						
					
					lstItems = session.selectList("GetItemList", search);
					lItemCount = (Long)session.selectOne("GetItemListCount", search);
					
					iCurrPage = 1;
					iMaxPages = lItemCount.intValue() / 1000;
					
					if ((iMaxPages*100)<lItemCount.intValue()){
						iMaxPages++;
					}
					
					ActionContext.getContext().getSession().put("itemlist", lstItems);
					ActionContext.getContext().getSession().put("maxpages", new Integer(iMaxPages));
					
				} else {
					
				//use cached data
				lstItems = (List) ActionContext.getContext().getSession().get("itemlist");
				Integer iMax = (Integer)ActionContext.getContext().getSession().get("maxpages");
				Integer iCurr = (Integer)ActionContext.getContext().getSession().get("currpage");
				
				if (lstItems==null || iMax==null || iCurr==null){
					return SUCCESS;
				}
				
				iMaxPages = ((Integer)ActionContext.getContext().getSession().get("maxpages")).intValue();
				iCurrPage = ((Integer)ActionContext.getContext().getSession().get("currpage")).intValue();
				
			}
			
			ActionContext.getContext().getSession().put("currpage", Integer.valueOf(iCurrPage));
		
				
			} catch (Exception e ) {
				addActionError(e.toString());
				return INPUT;
			} finally {
				this.endSession();
			}
			
			return SUCCESS;
	}
	
	
	/**
	 * @return Returns the sItemId.
	 */
	public String getItemId() {
		return sItemId;
	}
	/**
	 * @param itemId The sItemId to set.
	 */
	public void setItemId(String itemId) {
		sItemId = itemId;
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
	 * @return Returns the iPage.
	 */
	public int getPage() {
		return iPage;
	}
	
	/**
	 * @param page The iPage to set.
	 */
	public void setPage(int page) {
		iPage = page;
	}
	
	/**
	 * @return Returns the sReload.
	 */
	public String getReload() {
		return sReload;
	}
	
	/**
	 * @param reload The sReload to set.
	 */
	public void setReload(String reload) {
		sReload = reload;
	}
	
	/**
	 * @return Returns the lItemCount.
	 */
	public Long getItemCount() {
		return lItemCount;
	}
	
	/**
	 * @param itemCount The lItemCount to set.
	 */
	public void setItemCount(Long itemCount) {
		lItemCount = itemCount;
	}
	
	/**
	 * @return Returns the iMaxPages.
	 */
	public int getMaxPages() {
		return iMaxPages;
	}
	
	/**
	 * @param maxPages The iMaxPages to set.
	 */
	public void setMaxPages(int maxPages) {
		iMaxPages = maxPages;
	}
	
	/**
	 * @return Returns the iCurrPage.
	 */
	public int getCurrPage() {
		return iCurrPage;
	}
	
	/**
	 * @param currPage The iCurrPage to set.
	 */
	public void setCurrPage(int currPage) {
		iCurrPage = currPage;
	}
	
	public List getItemList(){
		return lstItems;
	}
	
	
	/**
	 * @return Returns the caller.
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * @param caller The caller to set.
	 */
	public void setCaller(String caller) {
		this.caller = caller;
	}
}
