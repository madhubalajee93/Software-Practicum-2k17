package com.example.smart_health_prediction;

import java.util.Calendar;

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
import android.widget.Toast;

import com.example.smart_health_prediction_pat.R;

public class Feedback extends Fragment{
	String patid,date;
	EditText feedtxt;
	Calendar cal;
	Button btn;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.feedback, container,false);
		Intent i=getActivity().getIntent();
		patid=i.getStringExtra("patid");
		feedtxt=(EditText) v.findViewById(R.id.feedtxt);
		btn=(Button) v.findViewById(R.id.feedbtn);
		
		//Toast.makeText(getActivity(), date+"\n"+patid, Toast.LENGTH_SHORT).show();
		btn.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				getdate();
				new feedasync().execute(patid,feedtxt.getText().toString(),date);
			}
		});
		return v;
	}
	public class feedasync extends AsyncTask<String, JSONParser, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.PatFeed(params[0], params[1], params[2]);
				JSONParser jp=new JSONParser();
				a=jp.feeddata(json);
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
				Toast.makeText(getActivity(), "Feed Submitted", Toast.LENGTH_SHORT).show();
				feedtxt.setText("");
			}
			else
			{
				Toast.makeText(getActivity(), "Problem in Submitting", Toast.LENGTH_SHORT).show();
			}
		}
	}
	public void getdate()
	{
		cal=Calendar.getInstance();
		int yy=cal.get(Calendar.YEAR);
		int mm=cal.get(Calendar.MONTH);
		int dd=cal.get(Calendar.DAY_OF_MONTH);
		
		int HH=cal.get(Calendar.HOUR);
		int MM=cal.get(Calendar.MINUTE);
		int SS=cal.get(Calendar.SECOND);
		
		date=yy+"-"+mm+"-"+dd+" "+HH+":"+MM+":"+SS;
	}

}
