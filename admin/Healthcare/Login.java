package com.example.smart_health_prediction;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smart_health_prediction_doc.R;

public class Login extends Activity{
	EditText user,pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		user=(EditText) findViewById(R.id.userid);
		pass=(EditText) findViewById(R.id.pass);
		
		
		
		
	}
	public void login(View v)
	{
		if(user.getText().toString().compareTo("")!=0 && pass.getText().toString().compareTo("")!=0)
		{
			new loginasync().execute(user.getText().toString(),pass.getText().toString());
		}
		else
		{
			Toast.makeText(Login.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onResume() {
		pass.setText("");
		user.setText("");
		super.onResume();
	}
	
	@Override
	protected void onRestart() {
		pass.setText("");
		user.setText("");
		super.onRestart();
	}
	
	public class loginasync extends AsyncTask<String, JSONObject, String>
	{
		

		@Override
		protected String doInBackground(String... params) {
			String check="false";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject json=api.DocLogin(params[0], params[1]);
				JSONParser jp=new JSONParser();
				check=jp.checklogin(json);
			} catch (Exception e) {
				check=e.getMessage();
			}
			
			return check;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String res="false";
			
			try{
				res=result;
				//Toast.makeText(Login.this, res, Toast.LENGTH_SHORT).show();
				if(res.compareTo("true")==0)
				{
					Intent i=new Intent(Login.this,MainActivity.class);
					i.putExtra("id", user.getText().toString());
					startActivity(i);	
				}
				else
				{
					Toast.makeText(Login.this, "Aunthentication Failed", Toast.LENGTH_SHORT).show();
					user.setText("");
					pass.setText("");
					user.requestFocus();
				}
			}catch(Exception e)
			{
				Toast.makeText(Login.this, e.getMessage()+"\n"+res, Toast.LENGTH_SHORT).show();
			}
			
			
		}
	}
	
}
