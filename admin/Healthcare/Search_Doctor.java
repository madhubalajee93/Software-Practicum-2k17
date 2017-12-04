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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smart_health_prediction_pat.R;

public class Search_Doctor extends Fragment{
	EditText txt;
	Spinner spin;
	String spinner;
	Button btn;
	
	//String[] from=new String[]{"a","b","c","d","e"};
	//int[] to=new int[]{R.id.docid,R.id.docnam,R.id.docadd,R.id.docno,R.id.doctype};
	ListView list;
	List<String> lst;
	ArrayAdapter<String> adapt;
	//List<HashMap<String, String>> lst;
	//HashMap<String, String> hm;
	//SimpleAdapter adapt;
	String[] docid;
	String[] docname;
	String[] docadd;
	String[] docno;
	String[] doctype;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.search_doctor, container,false);
		txt=(EditText) v.findViewById(R.id.txt);
		spin=(Spinner) v.findViewById(R.id.spinner1);
		btn=(Button) v.findViewById(R.id.searchbtn);
		
		list=(ListView) v.findViewById(R.id.doclist);
		lst=new ArrayList<String>();
		//hm=new HashMap<String, String>();
		
		ArrayAdapter aa=ArrayAdapter.createFromResource(getActivity(), R.array.Spinneritems,android.R.layout.simple_spinner_item);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				TextView t=(TextView) arg1;
				spinner=t.getText().toString();
				
				if(spinner.trim().compareTo("Name")==0)
				{
					txt.setHint("Enter Name");
				}
				if(spinner.trim().compareTo("Category")==0)
				{
					txt.setHint("Enter Category");
				}
				if(spinner.trim().compareTo("Address")==0)
				{
					txt.setHint("Enter Address");
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(spinner.compareTo("")==0)
				{
					Toast.makeText(getActivity(), "Select A Search by Element from the dropdown", Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					if(txt.getText().toString().compareTo("")==0)
					{
						Toast.makeText(getActivity(), "Enter "+spinner, Toast.LENGTH_SHORT).show();
					}
					else
					{
						try
						{
							if(adapt.getCount()>0)
							{
								adapt.clear();
							}
							new sdocasync().execute(spinner,txt.getText().toString());
						}
						catch(Exception e)
						{
							new sdocasync().execute(spinner,txt.getText().toString());
						}
						
					}
					
				}
			}
		});
		return v;
	}
	public class sdocasync extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String a="back";
			RestAPI api=new RestAPI();
			try {
				JSONObject json=api.SearchDoc(params[0], params[1]);
				JSONParser jp=new JSONParser();
				a=jp.searchdoc(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return a;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			String b="post";
			try{
				b=result;
				
				String[] s=b.split("\\*");
				docid=new String[s.length];
				docname=new String[s.length];
				docadd=new String[s.length];
				docno=new String[s.length];
				doctype=new String[s.length];
				
				for(int i=0;i<s.length;i++)
				{
					String[] n=s[i].toString().split("\\$");
					
					docid[i]=n[0];
					docname[i]=n[1];
					docadd[i]=n[2];
					docno[i]=n[3];
					doctype[i]=n[4];
					
				}
				
				for(int a=0;a<docid.length;a++)
				{
					lst.add("Id : "+docid[a]+"\n"+"Name : "+docname[a]+"\n"+"Address : "+docadd[a].trim()+"\n"+"Contact : "+docno[a]+"\n"+"Type : "+doctype[a]);
					 /*hm=new HashMap<String, String>();
					 hm.put("a","Id : "+docid[a]);
			   	     hm.put("b","Name : "+docname[a]);
			   	     hm.put("c","Address : "+docadd[a].trim());
			   	     hm.put("d","Contact : "+docno[a]);
			   	     hm.put("e","Type : "+doctype[a]);
					 
					 hm.put("", "Id : "+docid[a]+"\n"+"Name : "+docname[a]+"\n"+"Address : "+docadd[a].trim()+"\n"+"Contact : "+docno[a]+"\n"+"Type : "+doctype[a]);
					 lst.add(hm);*/
					
				}
				
				//adapt=new SimpleAdapter(getActivity().getBaseContext(), lst, R.layout.search_doc_items, from, to);
				//adapt.notifyDataSetChanged();
				//list.setAdapter(adapt);
				
				
				//ListAdapter adapt=new SimpleAdapter(getActivity().getBaseContext(), lst, R.layout.search_doc_items, new String[]{"Doctor Details"},new int[]{R.id.docdetails});
				//list.setAdapter(adapt);
				//setListAdapter(adapt);
				//lv=getListView();
				
				adapt=new ArrayAdapter<String>(getActivity().getBaseContext(), R.layout.search_doc_items,R.id.docdetails, lst);
				list.setAdapter(adapt);
			}
			catch(Exception e)
			{
				Toast.makeText(getActivity(), "NO Record Found by the "+spinner+" - "+txt.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		}
	}

}
