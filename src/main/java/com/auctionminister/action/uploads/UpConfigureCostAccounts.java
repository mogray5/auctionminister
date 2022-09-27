package com.auctionminister.action.uploads;

import java.util.HashMap;
import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.AccountData;
import com.auctionminister.data.EveReftypeData;
import com.auctionminister.data.GlAccountTypes;
import com.auctionminister.data.UserSmallData;

public class UpConfigureCostAccounts extends BaseAction {

	private static final long serialVersionUID = 2508716705609050413L;
	private List<EveReftypeData> refTypes = null;
	private List<AccountData> glAccountTypes = null;
	
	private String[] returnTypes = null;
	private String[] costList = null;
	private String[] ignoreList = null;
	
	@Override
	public String execute() throws Exception {
	
		this.startSession();
	
		try {	
			
			UserSmallData oUserID = this.verifyLogin();
			
			refTypes = session.selectList("GetEveReftypes");
			glAccountTypes = session.selectList("GetNonMerchChargeAccounts");
			
	} catch (Exception e ) {
		addActionError(e.toString());
		return ERROR;
	} finally {
		this.endSession();
	}
	
	return SUCCESS;

}
	
	public String doUpdate() throws Exception {
		
		this.startSession();
		boolean saveIgnored = false;
		HashMap<String, String> ignoreMap = new HashMap<String, String>();
		
		try {	
		
			//Populate ignore map
			if (ignoreList != null && ignoreList.length>0){
				for (int j=0; j<ignoreList.length; j++){
					ignoreMap.put(ignoreList[j], ignoreList[j]);
				}
			}
			
			if (returnTypes != null && costList != null) {
				for (int i=0; i< returnTypes.length; i++) {
					
					EveReftypeData lookup = new EveReftypeData();
					lookup.setRefTypeId(Integer.parseInt(returnTypes[i]));
					
					EveReftypeData refTypeData = session.selectOne("GetEveReftype", lookup);
					
					if (ignoreMap.containsKey(Integer.toString(refTypeData.getRefTypeId())) && !refTypeData.isIgnore()){
						saveIgnored = true;
						refTypeData.setIgnore(true);
					} else if (!ignoreMap.containsKey(Integer.toString(refTypeData.getRefTypeId())) && refTypeData.isIgnore()){
						saveIgnored = true;
						refTypeData.setIgnore(false);
					}
					
					//Cost removed
					if (costList[i].equalsIgnoreCase("na") && refTypeData.getCostActIndex()!=0){
						refTypeData.setCostActIndex(0);
						session.update("AssignCostIndex", refTypeData);
						session.commit();
					} else if (!costList[i].equalsIgnoreCase("na")) {
						
						//Check if different than saved index
						Long idx = Long.parseLong(costList[i]);
						if (idx != refTypeData.getCostActIndex())
						{
							//Cost index changed, save it.
							refTypeData.setCostActIndex(idx);
							session.update("AssignCostIndex", refTypeData);
							session.commit();
						}
					} else if (saveIgnored){
						session.update("SetReftypeIgnore", refTypeData);
						session.commit();
					}
					
					
				}
			}
			
			
			refTypes = session.selectList("GetEveReftypes");
			glAccountTypes = session.selectList("GetNonMerchChargeAccounts");
			
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
	
		return SUCCESS;
		
	}

	public List<EveReftypeData> getRefTypes() {
		return refTypes;
	}

	public void setRefTypes(List<EveReftypeData> refTypes) {
		this.refTypes = refTypes;
	}

	public List<AccountData> getGlAccountTypes() {
		return glAccountTypes;
	}

	public void setGlAccountTypes(List<AccountData> glAccountTypes) {
		this.glAccountTypes = glAccountTypes;
	}


	public String[] getCostList() {
		return costList;
	}


	public void setCostList(String[] costList) {
		this.costList = costList;
	}

	public String[] getReturnTypes() {
		return returnTypes;
	}

	public void setReturnTypes(String[] returnTypes) {
		this.returnTypes = returnTypes;
	}

	public String[] getIgnoreList() {
		return ignoreList;
	}

	public void setIgnoreList(String[] ignoreList) {
		this.ignoreList = ignoreList;
	}
	
	
	
}
