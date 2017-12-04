package com.example.smart_health_prediction;

import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smart_health_prediction_pat.R;

public class My_Details extends Fragment{
	String patid;
	TextView id;
	Button edit,cancel;
	EditText name,add,tel,email;
	int z=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.my_details, container,false);
		
		name=(EditText) v.findViewById(R.id.nametxt);
		id=(TextView) v.findViewById(R.id.detailid);
		edit=(Button) v.findViewById(R.id.editbtn);
		cancel=(Button) v.findViewById(R.id.cancelbtn);
		add=(EditText) v.findViewById(R.id.addtxt);
		tel=(EditText) v.findViewById(R.id.teltxt);
		email=(EditText) v.findViewById(R.id.emailtxt);
		cancel.setAlpha(0);
		
		Intent i=getActivity().getIntent();
		patid=i.getStringExtra("patid");
		id.setText(patid);
		new detailsasync().execute(patid);
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				z++;
				if(z==1)
				{
					
					add.setEnabled(true);
					tel.setEnabled(true);
					email.setEnabled(true);
					edit.setText("update");
					cancel.setAlpha(128);
				}
				if(z==2)
				{
					if(add.getText().toString().compareTo("")!=0 && tel.getText().toString().compareTo("")!=0 && email.getText().toString().compareTo("")!=0)
					{
						new Patupdate().execute(patid,add.getText().toString(),tel.getText().toString(),email.getText().toString());
						add.setEnabled(false);
						tel.setEnabled(false);
						email.setEnabled(false);
						edit.setText("Edit");
						cancel.setAlpha(0);
						Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
						z=0;
					}
					else
					{
						Toast.makeText(getActivity(), "All Fields are Mandatory", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				add.setEnabled(false);
				tel.setEnabled(false);
				email.setEnabled(false);
				edit.setText("Edit");
				cancel.setAlpha(0);
				z=0;
			}
		});
		return v;
	}
	
	public class detailsasync extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.PatData(params[0]);
				JSONParser jp=new JSONParser();
				a=jp.getpatdata(json);
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
			//Toast.makeText(getActivity(), b, Toast.LENGTH_SHORT).show();
			
			String[] s=b.split("\\$");
			name.setText(s[1]);
			add.setText(s[2]);
			tel.setText(s[3]);
			email.setText(s[4]);
			
			
		}
	}
	
	public class Patupdate extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.updatepat(params[0], params[1], params[2], params[3]);
				JSONParser jp=new JSONParser();
				a=jp.patupdate(json);
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
			Toast.makeText(getActivity(), b, Toast.LENGTH_SHORT).show();
			
		}
	}

}
