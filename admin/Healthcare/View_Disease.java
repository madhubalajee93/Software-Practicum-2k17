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
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smart_health_prediction_doc.R;

public class View_Disease extends Fragment{
	
	String[] from=new String[]{"a","b","c","d"};
	int[] to=new int[]{R.id.disiid,R.id.disname,R.id.dissym,R.id.distype};
	ListView list;
	List<HashMap<String, String>> lst;
	HashMap<String, String> hm;
	SimpleAdapter adapt;
	String[] did;
	String[] dname;
	String[] dsym;
	String[] dtype;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.view_disease, container,false);
		
		list=(ListView) v.findViewById(R.id.dislist);
		lst=new ArrayList<HashMap<String,String>>();
		hm=new HashMap<String, String>();
		new disasync().execute();
		return v;
	}
	
	public class disasync extends AsyncTask<Void, JSONParser, String>
	{

		@Override
		protected String doInBackground(Void... params) {
			
			String check="back";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject json=api.ViewDisease();
				JSONParser jp=new JSONParser();
				check=jp.getdis(json);
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
			//Toast.makeText(getActivity(), s[0], Toast.LENGTH_SHORT).show();
			did=new String[s.length];
			dname=new String[s.length];
			dsym=new String[s.length];
			dtype=new String[s.length];
			
			
			
			for(int i=0;i<s.length;i++)
			{
				String[] n=s[i].toString().split("\\$");
				//Toast.makeText(getActivity(), n[0]+" "+n[1]+" "+n[2]+" "+n[3]+ " "+n[4], Toast.LENGTH_SHORT).show();
				did[i]=n[0];
				dname[i]=n[1];
				dsym[i]=n[2];
				dtype[i]=n[3];
				
				//Toast.makeText(getActivity(), did[i]+" "+dname[i]+" "+dsym[i]+" "+dtype[i]+" "+contact[i]+" ", Toast.LENGTH_SHORT).show();
			}
			
			for(int a=0;a<did.length;a++)
			{
				 hm=new HashMap<String, String>();
				 hm.put("a","Id : "+did[a]);
		   	     hm.put("b","Name : "+dname[a]);
		   	     hm.put("c","Symtoms : "+dsym[a].trim());
		   	     hm.put("d","Type : "+dtype[a]);
		   	     lst.add(hm);
			}
			
			adapt=new SimpleAdapter(getActivity().getBaseContext(), lst, R.layout.disease_items, from, to);
	  	    list.setAdapter(adapt);
			
		}
		
	}

}
