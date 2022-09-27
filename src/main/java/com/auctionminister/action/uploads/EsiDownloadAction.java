package com.auctionminister.action.uploads;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UploadData;
import com.auctionminister.data.UploadKey;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.UploadKeyParams;
import com.opensymphony.xwork2.ActionContext;

import net.troja.eve.esi.ApiClient;
import net.troja.eve.esi.ApiClientBuilder;
import net.troja.eve.esi.api.SsoApi;
import net.troja.eve.esi.api.WalletApi;
import net.troja.eve.esi.auth.OAuth;
import net.troja.eve.esi.model.CharacterInfo;
import net.troja.eve.esi.model.CharacterWalletTransactionsResponse;

/**
 * Connect to eve using ESI API.  Downloaded wallet and journal transactions 
 * will be put into temp tables tmp001 and tmp003.
 *
 */
public class EsiDownloadAction extends BaseAction {

	private static final long serialVersionUID = 7603223954353845235L;

	private String sIsSecure = "S";
	private String sUrl = null;
	private String sRefreshToken = null;
	private UploadKey oUploadKey = null;
	
	@Override
	public String execute() throws Exception {
		
		this.startSession();

		Object refreshToken = null;
		Object authCode = null;
		
		UserSmallData oUserID = this.verifyLogin();
		// Try to use the refresh token stored in the DB
		sRefreshToken = oUserID.getRefreshToken();
		
		if (sRefreshToken == null || sRefreshToken.length() == 0)
			refreshToken = ActionContext.getContext().getSession().get("authRefreshToken");
		else
			refreshToken = sRefreshToken;
		
		if (refreshToken == null) {

			authCode = ActionContext.getContext().getSession().get("authCode");	
			
			if (authCode == null || authCode.toString().length()==0) {
				
				// Need to get a new refresh token
				try {
					
					newEsiLogin();
					return "redirect";
				
				} catch (Exception e ) {
					addActionError(e.toString());
					return INPUT;
				} finally {
					this.endSession();
				}
			
			} else {
				// Have an auth code.  
				//Finish the ESI login and get a refresh token
				finishEsiLogin(authCode);
			}
		}
		
		// We need to have a refresh token at this point
		if (sRefreshToken == null) return ERROR;
		
		// Save the current token
		saveRefreshToken(sRefreshToken, oUserID.getUserId());
		
		oUploadKey = getApiKey();
		
		ApiClient client = new ApiClientBuilder().clientID(oUploadKey.getClientId()).refreshToken(sRefreshToken).build();
        final SsoApi api = new SsoApi(client);
        CharacterInfo info = api.getCharacterInfo();

        Integer characterId = info.getCharacterID();
		WalletApi wApi = new WalletApi(client);
		List<CharacterWalletTransactionsResponse> trans = wApi.getCharactersCharacterIdWalletTransactions(characterId, null,null,null, null);
		
		if (trans != null && trans.size() > 0) {
			
			// Get a new batch Id
			//
			long batchId = (long)session.selectOne("GetBatchId");
			
			for (CharacterWalletTransactionsResponse tran : trans) {
				
				/*
				 * 
				 * batch id

INSERT INTO tmp002 (USERID, UPDT)
		SELECT u.userid, current_timestamp
		FROM sy001 u WHERE u.username=$1`
		
`INSERT INTO tmp001(
		USERID, BATCHID, ITEMID,TMPDATE, TMPNAME, TMPTYPE,
		TMPSUBJECT, TMPPRICE, TMPQUANTITY, TMPTOEMAIL, TMPSHIPADDRESS,
		TMPITEMTITLE, TMPITEMID, TMPSHIPAMT, TMPINSURANCEAMT,
		TMPTAXAMT, TMPBUYERID, TMPITEMURL, TMPREFTXNID, AMINCLUDE,
		AMTYPE)
		SELECT u.userid, $1, $2, $3, $4, $5,
		$6, $7, $8, $9, $10, $11, $12, $13,
		$14, $15, $16, $17, $18, $19, $20 
		FROM sy001 u WHERE u.username=$21`
		
		SELECT batchid from tmp002 where updt = (select max(updt) from tmp002)
				 * values(#{userId}, #{batchId}, #{itemId}, #{tmpDate}, #{tmpName}, #{tmpType},
        #{tmpSubject}, #{tmpPrice}, #{tmpQuantity}, #{tmpToEmail},
        #{tmpShipAddress}, #{tmpItemTitle}, #{tmpItemId}, 
        #{tmpShipAmt}, #{tmpInsuranceAmt}, #{tmpTaxAmt},
        #{tmpBuyerId}, #{tmpItemUrl}, #{tmpRefTxnId}, #{amInclude},
        #{amType}
				 * 
				 * 
				 */
				
				//tran.
				UploadData row = new UploadData();
				row.setUserId(oUserID.getUserId());
				row.setBatchId(batchId);
				
				row.setTmpDate(localDatetimeToDate(tran.getDate().toLocalDateTime()));
				row.setTmpName("TODO"); //Name of player transacted with
				row.setTmpType(tran.getIsBuy() ? "buy":"sell"); //buy/sell
				row.setTmpPrice(tran.getUnitPrice().floatValue());
				row.setTmpQuantity(tran.getQuantity());
				row.setTmpShipAddress(tran.getLocationId().toString()); // Station transacted
				row.setTmpItemId(tran.getTypeId().toString());
				session.insert("AddPaypalTran", null);
			}
			session.commit();
		}
		
		return SUCCESS;
	}

	public String getUrl() {
		return sUrl;
	}

	public void setUrl(String sUrl) {
		this.sUrl = sUrl;
	}

	public String getIsSecure() {
		return sIsSecure;
	}

	public void setIsSecure(String sIsSecure) {
		this.sIsSecure = sIsSecure;
	}

	/**
	 * Scopes are stored in the DB as a space separated string.
	 * Convert this to a Set<String>
	 * @param key key object containing the scopes
	 * @return Set<String> of scopes
	 */
	private Set<String> getScopeSet(UploadKey key) {
		
		if (key.getScopes() == null || key.getScopes().length() == 0 )
			return null;
		else {
			
			Set<String> result = new HashSet<String>(1000);
			StringTokenizer tokens = new StringTokenizer(key.getScopes(), " ");
			
			while(tokens.hasMoreTokens())
				result.add(tokens.nextToken());
			
			return result;
		}
	}

	/**
	 * Random code we want to get back from ESI
	 * as a security measure
	 * @return
	 */
	private String getSecret() {		
		byte[] array = new byte[7];
	    new Random().nextBytes(array);
	    return "mydeepdarksecret" + 
	    	new String(array, Charset.forName("UTF-8"));
	}

	/**
	 * Start a new oAuth session with ESI
	 */
	private void newEsiLogin() {
		String secret = getSecret();

		try {
			ActionContext.getContext().getSession().remove("authCode");
			ActionContext.getContext().getSession().remove("postAuthSecret");
		} catch (Exception e) {};
		
		UploadKey key = getApiKey();

		if (key != null) {
			
			
			ApiClient client = new ApiClientBuilder().clientID(key.getClientId()).build();
			final OAuth auth = (OAuth) client.getAuthentication("evesso");
			Set<String> scopes = getScopeSet(key);
			final String authorizationUri = auth.getAuthorizationUri(
					"http://localhost:8080/auctionminister/pages/esiauth.action ", 
					scopes, 
					secret);
			
			ActionContext.getContext().getSession().put("authClient", auth);
			
			sUrl = authorizationUri;
		}
	}
	
	/**
	 * Call the finishFlow method of the eve-esi library to finish authentication and
	 * get a refresh token.
	 * @param authCode
	 */
	private OAuth finishEsiLogin(Object authCode) {
		final OAuth auth = (OAuth) ActionContext.getContext().getSession().get("authClient");
		String postAuthSecret = ActionContext.getContext().getSession().get("postAuthSecret").toString();
		System.out.println("state = " + (String)postAuthSecret);
		System.out.println("auth code = " + (String)authCode);
		try {
			auth.finishFlow((String)authCode, (String)postAuthSecret);
		} catch (Exception e) {
			addActionError(e.toString());
		}
		
		sRefreshToken = auth.getRefreshToken();
		// Add it to session in case need it elsewhere in the app
		ActionContext.getContext().getSession().put("authRefreshToken", sRefreshToken);
		
		return auth;
	}
	
	/**
	 * ESI uses new time libraries introduce din Java 8.  
	 * Convert to older date format.
	 * @param localDate
	 * @return
	 */
	private Date localDatetimeToDate(LocalDateTime localDate) {
		
		return Date.from(
				localDate.atZone(ZoneId.systemDefault()).toInstant());
		
	}
	
	/**
	 * Gets client id from the DB if not already set for the class
	 * @return string client id from database.
	 */
	private UploadKey getApiKey() {
		
		if (oUploadKey == null) {
			List<Object> keys = session.selectList("GetEveAPIKeys");
			if (keys != null && keys.size()>0) {
				//Just work with one initially
				oUploadKey =  (UploadKey) keys.get(0);
			}
		}
		
		return oUploadKey;
	}
	
	private void saveRefreshToken(String token, long userId) {
		
		UploadKeyParams params = new UploadKeyParams();
		params.setRefreshToken(token);
		params.setUserId(userId);
		session.update("UpdateRefreshToken", params);
		session.commit();
	}
}
