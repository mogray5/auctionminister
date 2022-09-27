package com.auctionminister.action.uploads;

import java.util.Map;

import com.auctionminister.action.system.BaseAction;
import com.opensymphony.xwork2.ActionContext;

import net.troja.eve.esi.auth.OAuth;

/**
 * Connect to eve using ESI API.  Downloaded wallet and journal transactions 
 * will be put into temp tables tmp001 and tmp003.
 *
 */
public class EsiAuthAction extends BaseAction {

	private static final long serialVersionUID = 7603223954353845235L;
	
	@Override
	public String execute() throws Exception {
					
		Map<String, Object> params = ActionContext.getContext().getParameters();
		
		if (params.containsKey("code")) {
		
			Object code = params.get("code");
			Object state = params.get("state");
			
			String sCode = ((String[]) code)[0];
			String sState = ((String[]) state)[0];
			
			ActionContext.getContext().getSession().put("authCode", sCode);
			ActionContext.getContext().getSession().put("postAuthSecret", sState);
			
		}
		
		return SUCCESS;
		
	}
}
