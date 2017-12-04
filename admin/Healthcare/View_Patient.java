package com.example.smart_health_prediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smart_health_prediction.Notification.notasyc;
import com.example.smart_health_prediction_doc.R;

public class View_Paitent extends Fragment{
	String item;
	String[] from=new String[]{"a","b","c","d","e","f"};
	int[] to=new int[]{R.id.patid,R.id.patname,R.id.patgen,R.id.patage,R.id.patadd,R.id.patcon};
	ListView list;
	List<HashMap<String, String>> lst;
	HashMap<String, String> hm;
	SimpleAdapter adapt;
	String[] pid;
	String[] name;
	String[] gender;
	String[] age;
	String[] add;
	String[] contact;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.view_patient, container,false);
		
		list=(ListView) v.findViewById(R.id.patlist);
		lst=new ArrayList<HashMap<String,String>>();
		hm=new HashMap<String, String>();
		
		new patasync().execute();
		
		return v;
	}

	public class patasync extends AsyncTask<Void, JSONParser, String>
	{

		@Override
		protected String doInBackground(Void... params) {
			
			String check="back";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject json=api.ViewUser();
				JSONParser jp=new JSONParser();
				check=jp.getpatt(json);
			} catch (Exception e) {
				check=e.getMessage();
			}
			
			return check;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			String str="post";
			str=result;
			
			String[] s=str.split("\\*");
			pid=new String[s.length];
			name=new String[s.length];
			gender=new String[s.length];
			age=new String[s.length];
			add=new String[s.length];
			contact=new String[s.length];
			//Toast.makeText(getActivity(), s[0], Toast.LENGTH_SHORT).show();
			
			for(int i=0;i<s.length;i++)
			{
				String[] n=s[i].toString().split("\\$");
				//Toast.makeText(getActivity(), n[0]+" "+n[1]+" "+n[2]+" "+n[3]+ " "+n[4], Toast.LENGTH_SHORT).show();
				pid[i]=n[0];
				name[i]=n[1];
				gender[i]=n[2];
				age[i]=n[3];
				add[i]=n[4];
				contact[i]=n[5];
				
			}
			
			for(int a=0;a<pid.length;a++)
			{
				 hm=new HashMap<String, String>();
				 hm.put("a","Id : "+pid[a]);
		   	     hm.put("b","Name : "+name[a]);
		   	     hm.put("c","Gender : "+gender[a].trim());
		   	     hm.put("d","Age : "+age[a]);
		   	     hm.put("e","Address : "+add[a]);
		   	     hm.put("f","Contact : "+contact[a]);
		   	    
		   	     lst.add(hm);
			}
			
			adapt=new SimpleAdapter(getActivity().getBaseContext(), lst, R.layout.patient_items, from, to);
	  	    list.setAdapter(adapt);
			
		}
		
		
	}
}
