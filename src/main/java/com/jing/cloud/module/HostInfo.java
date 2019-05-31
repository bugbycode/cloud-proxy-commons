package com.jing.cloud.module;

import org.json.JSONException;
import org.json.JSONObject;

public class HostInfo {
	
	private String ip;	//IP地址
	
	private String os;  //操作系统

	public HostInfo() {
		
	}
	
	public HostInfo(String ip, String os) {
		this.ip = ip;
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {
			json.put("ip", ip);
			json.put("os", os);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
	
}
