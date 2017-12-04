package com.example.smart_health_prediction;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.smart_health_prediction_pat.R;

public class Register extends Activity{
	String id,gender;
	EditText pid,name,age,add,mob,pass,email;
	RadioGroup rg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		pid=(EditText) findViewById(R.id.regid);
		name=(EditText) findViewById(R.id.regname);
		rg=(RadioGroup) findViewById(R.id.reggen);
		age=(EditText) findViewById(R.id.regage);
		add=(EditText) findViewById(R.id.regadd);
		mob=(EditText) findViewById(R.id.regcon);
		pass=(EditText) findViewById(R.id.regpass);
		email=(EditText) findViewById(R.id.regemail);
		
		new auto().execute();
		//pid.setText("User Id : "+id);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton temp=(RadioButton) findViewById(checkedId);
				gender=temp.getText().toString();
				//Toast.makeText(Register.this, "You Selected :"+temp.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}
	public void regbtn(View v)
	{
		if(id.compareTo("")==0 || name.getText().toString().compareTo("")==0 || gender.compareTo("")==0 || age.getText().toString().compareTo("")==0 || add.getText().toString().compareTo("")==0 || mob.getText().toString().compareTo("")==0 || pass.getText().toString().compareTo("")==0 || email.getText().toString().compareTo("")==0)
		{
			Toast.makeText(this, "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
		}
		else
		{
			new regasync().execute(id,name.getText().toString(),gender,age.getText().toString(),add.getText().toString(),mob.getText().toString(),pass.getText().toString(),email.getText().toString());
			
		}
	}
	public class auto extends AsyncTask<Void, JSONObject, String>
	{

		@Override
		protected String doInBackground(Void... arg0) {
			String check="back";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject  json=api.patid();
				JSONParser  jp=new JSONParser();
				check=jp.autoid(json);
			} catch (Exception e) {
				
				check=e.getMessage();
			}
			return check;
		}
		
		@Override
		protected void onPostExecute(String result) {
			
			super.onPostExecute(result);
			
			String res="post";
			res=result;
			
			pid.setText("Id : "+res);
			id=res;
			
		}
	}
	
	public class regasync extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String check="back";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject  json=api.PatRegister(params[0],params[1], params[2], params[3], params[4], params[5], params[6], params[7]);
				JSONParser  jp=new JSONParser();
				check=jp.regdata(json);
			} catch (Exception e) {
				
				check=e.getMessage();
			}
			return check;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			String res="post";
			res=result;
			//Toast.makeText(Register.this, res, Toast.LENGTH_SHORT).show();
			if(res.compareTo("true")==0)
			{
				Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
				Intent i=new Intent(Register.this,Login.class);
				i.putExtra("id",id);
				startActivity(i);
				finish();
				
			}
			else
			{
				Toast.makeText(Register.this, "Problem in Registration", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}
	
	

}
