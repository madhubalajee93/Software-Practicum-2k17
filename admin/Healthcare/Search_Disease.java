package com.example.smart_health_prediction;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.smart_health_prediction_pat.R;

public class Search_Disease extends Fragment{
	TextView title2,moresymtoms,spinnertxt,title3,suspectedtxt,suspectedans,typetxt,typeans,doctxt;
	EditText onesymptxt;
	Spinner spinneritems;
	Button nxtbtn,nnxtbtn,nonebtn;
	ScrollView scroll;
	String moresyms;
	String date,patid;
	Calendar cal;
	ListView doclist;
	List<String> lst;
	ArrayAdapter<String> adapt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.search_disease, container,false);
		scroll=(ScrollView) v.findViewById(R.id.scrollid);
		scroll.setVerticalScrollBarEnabled(false);
		Intent i=getActivity().getIntent();
		patid=i.getStringExtra("patid");
		//1st
		onesymptxt=(EditText) v.findViewById(R.id.onesymptxt);
		nxtbtn=(Button) v.findViewById(R.id.nxtbtn);
		
		//2nd
		title2=(TextView) v.findViewById(R.id.title2);
		moresymtoms=(TextView) v.findViewById(R.id.moresymtoms);
		spinnertxt=(TextView) v.findViewById(R.id.spinnertxt);
		spinneritems=(Spinner) v.findViewById(R.id.spinneritems);
		nnxtbtn=(Button) v.findViewById(R.id.nnextbtn);
		nonebtn=(Button) v.findViewById(R.id.nonebtn);
		
		title2.setVisibility(View.INVISIBLE);
		moresymtoms.setVisibility(View.INVISIBLE);
		spinnertxt.setVisibility(View.INVISIBLE);
		spinneritems.setVisibility(View.INVISIBLE);
		nnxtbtn.setVisibility(View.INVISIBLE);
		nonebtn.setVisibility(View.INVISIBLE);
		
		//3rd
		title3=(TextView) v.findViewById(R.id.title3);
		suspectedtxt=(TextView) v.findViewById(R.id.suspectedtxt);
		suspectedans=(TextView) v.findViewById(R.id.suspectedans);
		typetxt=(TextView) v.findViewById(R.id.typetxt);
		typeans=(TextView) v.findViewById(R.id.typeans);
		doctxt=(TextView) v.findViewById(R.id.doctxt);
		doclist=(ListView) v.findViewById(R.id.doclist);
		
		title3.setVisibility(View.INVISIBLE);
		suspectedtxt.setVisibility(View.INVISIBLE);
		suspectedans.setVisibility(View.INVISIBLE);
		typetxt.setVisibility(View.INVISIBLE);
		typeans.setVisibility(View.INVISIBLE);
		doctxt.setVisibility(View.INVISIBLE);
		doclist.setVisibility(View.INVISIBLE);
		lst=new ArrayList<String>();
		
		scroll.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		
		
		nxtbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(onesymptxt.getText().toString().compareTo("")!=0)
				{
					new onesymasync().execute(onesymptxt.getText().toString());
					scroll.setVerticalScrollBarEnabled(true);
					scroll.setOnTouchListener(null);
				}
				else
				{
					Toast.makeText(getActivity(), "Enter Any 1 Symtom", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
		
		
		nnxtbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				moresymtoms.setText("");
				new twosync().execute(moresyms);
				
				/*
				scroll.fullScroll(View.FOCUS_DOWN);
				
				spinneritems.setEnabled(false);
				nnxtbtn.setEnabled(false);
				nonebtn.setEnabled(false);
				
				title3.setVisibility(View.VISIBLE);
				suspectedtxt.setVisibility(View.VISIBLE);
				suspectedans.setVisibility(View.VISIBLE);
				typetxt.setVisibility(View.VISIBLE);
				typeans.setVisibility(View.VISIBLE);
				doctxt.setVisibility(View.VISIBLE);
				doclist.setVisibility(View.VISIBLE);*/
			}
		});
		
		nonebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				getdate();
				new Finaldis().execute(patid,date);
				
				title3.setVisibility(View.VISIBLE);
				suspectedtxt.setVisibility(View.VISIBLE);
				suspectedans.setVisibility(View.VISIBLE);
				typetxt.setVisibility(View.VISIBLE);
				typeans.setVisibility(View.VISIBLE);
				doctxt.setVisibility(View.VISIBLE);
				doclist.setVisibility(View.VISIBLE);
				
				//scroll.setOnTouchListener(null);
				spinneritems.setEnabled(false);
				nnxtbtn.setEnabled(false);
				nonebtn.setEnabled(false);
				
				
			}
		});
		
		spinneritems.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView txt=(TextView) arg1;
				moresyms=txt.getText().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		return v;
	}
	public class onesymasync extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.sysone(params[0]);
				JSONParser jp=new JSONParser();
				a=jp.onsymdata(json);
			} catch (Exception e) {
				a="back: "+e.getMessage();
			}
			
			return a;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String b="post";
			b=result;
			
			if(b.compareTo("No Disease Found, Please check your Symptom")==0)
			{
				Toast.makeText(getActivity(), "Enter any other Symptom", Toast.LENGTH_SHORT).show();
				onesymptxt.setText("");
				onesymptxt.requestFocus();
			}
			else
			{
				
				onesymptxt.setEnabled(false);
				nxtbtn.setEnabled(false);
				
				title2.setVisibility(View.VISIBLE);
				moresymtoms.setVisibility(View.VISIBLE);
				spinnertxt.setVisibility(View.VISIBLE);
				spinneritems.setVisibility(View.VISIBLE);
				nnxtbtn.setVisibility(View.VISIBLE);
				nonebtn.setVisibility(View.VISIBLE);
				
				
				String str[]=b.split("\\$");
				List<String> list=new ArrayList<String>();
				for(int i=0;i<str.length;i++)
				{
					moresymtoms.setText(moresymtoms.getText().toString()+str[i]+"\n\n");
					list.add(str[i]);
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinneritems.setAdapter(adapter);
			}
			
		}
		
		
		
	}
	
	public class twosync extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.systwo(params[0]);
				JSONParser jp=new JSONParser();
				a=jp.twosymdata(json);
			} catch (Exception e) {
				a="back: "+e.getMessage();
			}
			
			return a;
		}
		
		@Override
		protected void onPostExecute(String result) {
			try{
			super.onPostExecute(result);
			
				String b="post";
				b=result;
				String str[]=b.split("\\$");
				List<String> list=new ArrayList<String>();
				if(b.compareTo("error")!=0)
				{
					for(int i=0;i<str.length;i++)
					{
						moresymtoms.setText(moresymtoms.getText().toString()+str[i]+"\n");
						list.add(str[i]);
					}
					
					ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
					adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					spinneritems.setAdapter(adapter1);
				}
				else
				{
					getdate();
					new Finaldis().execute(patid,date);
					
					title3.setVisibility(View.VISIBLE);
					suspectedtxt.setVisibility(View.VISIBLE);
					suspectedans.setVisibility(View.VISIBLE);
					typetxt.setVisibility(View.VISIBLE);
					typeans.setVisibility(View.VISIBLE);
					doctxt.setVisibility(View.VISIBLE);
					doclist.setVisibility(View.VISIBLE);
					//scroll.setOnTouchListener(null);
					spinneritems.setEnabled(false);
					nnxtbtn.setEnabled(false);
					nonebtn.setEnabled(false);
					
					
				}
			
			}catch(Exception e)
			{
				getdate();
				new Finaldis().execute(patid,date);
			}
		}
	}
		
		public class Finaldis extends AsyncTask<String, JSONObject, String>
		{

			@Override
			protected String doInBackground(String... params) {
				String a="back";
				RestAPI api=new RestAPI();
				try {
					JSONObject json=api.final1(params[0], params[1]);
					JSONParser jp=new JSONParser();
					a=jp.getdis(json);
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
				
				String str[]=b.split("\\*");
				String dis=str[0];
				String str1[]=dis.split("\\$");
				
				if(str1.length==1)
				{
					suspectedans.setText(dis);
				}
				else
				{
					for(int i=0;i<=(str1.length-1);i++)
					{
						suspectedans.setText(suspectedans.getText()+str1[i]+"\n");
					}
						
				}
				typeans.setText(typeans.getText().toString()+str[1]+"\n");
				String doc=str[2];
				String str2[]=doc.split("\\$");
				if(str2.length==1)
				{
					lst.add(doc);
				}
				else
				{
					for(int j=0;j<str2.length;j++)
					{
						lst.add("Name : "+str2[0]+"\n"+"Address : "+str2[1]+"\n"+"Contact : "+str2[2]);
					}
				}
				
				adapt=new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.search_doc_items,R.id.docdetails, lst);
				doclist.setAdapter(adapt);
				scroll.fullScroll(View.FOCUS_DOWN);
				
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
			//Toast.makeText(getActivity(), date, Toast.LENGTH_SHORT).show();
		}
	
	}

	
	
