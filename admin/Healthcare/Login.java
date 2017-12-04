package com.example.smart_health_prediction;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smart_health_prediction_pat.R;

public class Login extends Activity{
	EditText user,pass;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		user=(EditText) findViewById(R.id.userid);
		pass=(EditText) findViewById(R.id.pass);
		
		Intent i=getIntent();
		try
		{
			String s=i.getStringExtra("id");
			if(s.compareTo("")==0)
			{
				user.setText("");
				pass.setText("");
			}
			else
			{
				user.setText(s);
				pass.requestFocus();
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void login(View v)
	{
		if(user.getText().toString().compareTo("")==0 || pass.getText().toString().compareTo("")==0)
		{
			Toast.makeText(Login.this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
		}
		else
		{
			new logindata().execute(user.getText().toString(),pass.getText().toString());
		}
	}
	public void register(View v)
	{
		Intent i=new Intent(this,Register.class);
		startActivity(i);
	}
	
	
	
	public void onBackPressed()
	{
		finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Intent i=getIntent();
		try
		{
			String s=i.getStringExtra("id");
			if(s.compareTo("")==0)
			{
				user.setText("");
				pass.setText("");
			}
			else
			{
				user.setText(s);
				pass.requestFocus();
			}
		}
		catch(Exception e)
		{
			user.setText("");
			pass.setText("");
		}
		
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Intent i=getIntent();
		try
		{
			String s=i.getStringExtra("id");
			if(s.compareTo("")==0)
			{
				user.setText("");
				pass.setText("");
			}
			else
			{
				user.setText(s);
				pass.requestFocus();
			}
		}
		catch(Exception e)
		{
			user.setText("");
			pass.setText("");
		}
	}
	
	public class logindata extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.PatLogin(params[0], params[1]);
				JSONParser jp=new JSONParser();
				a=jp.logindata(json);
			} catch (Exception e) {
				a=e.getMessage();
			}
			
			return a;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			String b="post";
			b=result;
			if(b.compareTo("true")==0)
			{
				Intent i=new Intent(Login.this,MainActivity.class);
				i.putExtra("patid", user.getText().toString());
				startActivity(i);
				
			}
			else if(b.compareTo("false")==0)
			{
				Toast.makeText(Login.this, "Invalid Username & Password", Toast.LENGTH_SHORT).show();
				user.setText("");
				pass.setText("");
			}
			else
			{
				Toast.makeText(Login.this, b, Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	
}
