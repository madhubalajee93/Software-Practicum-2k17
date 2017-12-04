package com.example.smart_health_prediction;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser {

	public String regdata(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		return result;
	}

	public String logindata(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		return result;
	}

	public String getpatdata(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		return result;
	}

	public String feeddata(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		return result;
	}

	public String searchdoc(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result=e.getMessage();
		}
		return result;
	}

	public String onsymdata(JSONObject json) {
			String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result="parse: "+e.getMessage();
		}
		return result;
	}

	public String autoid(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result="parse: "+e.getMessage();
		}
		return result;
	}

	public String twosymdata(JSONObject json) {
		String result="json";
		
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result="parse: "+e.getMessage();
		}
		return result;
	}

	public String patupdate(JSONObject json) {
		String result="json";
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result="parse: "+e.getMessage();
		}
		return result;

	}

	public String getdis(JSONObject json) {
		String result="json";
		try {
			result=json.getString("Value");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			result="parse: "+e.getMessage();
		}
		return result;
	}
}
