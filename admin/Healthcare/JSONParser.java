package com.example.smart_health_prediction;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	public String checklogin(JSONObject json) {
		String res="false";
		
		try {
			res=json.getString("Value");
		} catch (JSONException e) {
			 res=e.getMessage();
		}
		return res;
	}

	public String getdocdetails(JSONObject json) {
		String res=null;
		try {
			res=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public String getnot(JSONObject json) {
		
		String res="parse";
		try {
			res=json.getString("Value");
		} catch (JSONException e) {
			res=e.getMessage();
		}
		return res;
	}

	public String getpatt(JSONObject json) {
		
		String res="parse";
		try {
			res=json.getString("Value");
		} catch (JSONException e) {
			res=e.getMessage();
		}
		return res;
	}

	public String getdis(JSONObject json) {
		String res="parse";
		try {
			res=json.getString("Value");
		} catch (JSONException e) {
			res=e.getMessage();
		}
		return res;
	}

	public String update(JSONObject json) {
		String res="parse";
		try {
			res=json.getString("Value");
		} catch (JSONException e) {
			res=e.getMessage();
		}
		return res;
	}

	
	

}
