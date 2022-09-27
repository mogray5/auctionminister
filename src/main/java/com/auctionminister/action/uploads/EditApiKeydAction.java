/*
 * Copyright (c) 2013-2020 Wayne Gray All rights reserved
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
package com.auctionminister.action.uploads;

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UploadKey;

public class EditApiKeydAction extends BaseAction {

	private static final long serialVersionUID = 4315212103473535478L;
	private List<UploadKey> keys = null;
	private String clientId = null;
	private String vcode = "";
	private String appName = null;
	private String scopes = null;
	private String origClientId = null;
	private boolean doRemove = false;
	
	public boolean isDoRemove() {
		return doRemove;
	}

	public void setDoRemove(boolean doRemove) {
		this.doRemove = doRemove;
	}

	@Override
	public String execute() throws Exception {
		
		this.startSession();
		
		try {
			
			this.verifyLogin();
			
			UploadKey newKey = null;
			
			if (clientId != null){
				
				newKey = new UploadKey();
				newKey.setClientId(clientId);
				newKey.setvCode(vcode);
				newKey.setAppName(appName);
				newKey.setScopes(scopes);
				
			}
			
			if (haveKeyPair() & !doRemove){
				
				if (keyExists(newKey)){
					session.update("UpdateEveAPIKey", newKey);
				} else {
					session.insert("AddEveAPIKey", newKey);
				}

			} else if (doRemove){
				
				session.delete("DeleteEveAPIKey", newKey);
			}
			
			session.commit();
			
			keys = session.selectList("GetEveAPIKeys", null); 
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return INPUT;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	private boolean haveKeyPair(){
		if (clientId != null){
			if (vcode != null && vcode.length() > 0){
				return true;
			}
		}
		return false;
	}
	
	public List<UploadKey> getKeys() {
		return keys;
	}

	public void setKeys(List<UploadKey> keys) {
		this.keys = keys;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String keyId) {
		this.clientId = keyId;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getScopes() {
		return scopes;
	}

	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	public boolean keyExists(UploadKey newKey){
		
		UploadKey key = (UploadKey)session.selectOne("GetEveAPIKey", newKey);
		
		return key!=null;
		
	}
	
}
