package com.jing.cloud.module;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class Authentication implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 152770067471896521L;

	private String secret;
	
	private String clientId;
	
	public Authentication() {
		
	}

	public Authentication(String secret, String clientId) {
		this.secret = secret;
		this.clientId = clientId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	
	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {
			json.put("secret", secret);
			json.put("clientId", clientId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
}
