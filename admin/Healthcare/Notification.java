package com.example.smart_health_prediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.smart_health_prediction_doc.R;

public class Notification extends Fragment{
	String[] from=new String[]{"a","b","c","d","e"};
	int[] to=new int[]{R.id.notpid,R.id.notsym,R.id.notdis,R.id.nottype,R.id.nottime};
	ListView list;
	List<HashMap<String, String>> lst;
	HashMap<String, String> hm;
	SimpleAdapter adapt;
	String[] pid;
	String[] sym;
	String[] dis;
	String[] type;
	String[] time;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.notification, container,false);
		
		list=(ListView) v.findViewById(R.id.notlist);
		lst=new ArrayList<HashMap<String,String>>();
		hm=new HashMap<String, String>();
		
		new notasyc().execute();
		return v;
	}
	
	public class notasyc extends AsyncTask<Void, JSONParser, String>
	{

		@Override
		protected String doInBackground(Void... params) {
			
			String check="back";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject json=api.Notificaion();
				JSONParser jp=new JSONParser();
				check=jp.getnot(json);
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
			sym=new String[s.length];
			dis=new String[s.length];
			type=new String[s.length];
			time=new String[s.length];
			
			for(int i=0;i<s.length;i++)
			{
				String[] n=s[i].toString().split("\\$");
				//Toast.makeText(getActivity(), n[0]+" "+n[1]+" "+n[2]+" "+n[3]+ " "+n[4], Toast.LENGTH_SHORT).show();
				pid[i]=n[0];
				sym[i]=n[1];
				dis[i]=n[2];
				type[i]=n[3];
				time[i]=n[4];
				//Toast.makeText(getActivity(), pid[i]+" "+sym[i]+" "+dis[i]+" "+type[i]+" "+time[i]+" ", Toast.LENGTH_SHORT).show();
			}
			
			for(int a=0;a<pid.length;a++)
			{
				 hm=new HashMap<String, String>();
				 hm.put("a","Patient Id : "+pid[a]);
		   	     hm.put("b","Symtoms : "+sym[a]);
		   	     hm.put("c","Predicted Disease : "+dis[a].trim());
		   	     hm.put("d","Disease Type : "+type[a]);
		   	     hm.put("e","Date/TIme : "+time[a]);
		   	    
		   	     lst.add(hm);
			}
			
			adapt=new SimpleAdapter(getActivity().getBaseContext(), lst, R.layout.notification_items, from, to);
	  	    list.setAdapter(adapt);
			
		}
		
	}

}
