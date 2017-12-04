package com.example.smart_health_prediction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.jar.Attributes.Name;

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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.smart_health_prediction_doc.R;

public class My_Details extends Fragment{
	Button edit,cancel;
	EditText special,add,tel,name;
	TextView id;
	
	int z=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.my_details, container,false);
		id=(TextView) v.findViewById(R.id.iddata);
		Intent i=getActivity().getIntent();
		id.setText(i.getStringExtra("id"));
		name=(EditText) v.findViewById(R.id.nametxt);
		edit=(Button) v.findViewById(R.id.editbtn);
		cancel=(Button) v.findViewById(R.id.cancelbtn);
		special=(EditText) v.findViewById(R.id.spectxt);
		
		add=(EditText) v.findViewById(R.id.addtxt);
		tel=(EditText) v.findViewById(R.id.teltxt);
		cancel.setAlpha(0);
		
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				z++;
				if(z==1)
				{
					add.setEnabled(true);
					tel.setEnabled(true);
					edit.setText("Update");
					cancel.setAlpha(128);
				}
				if(z==2)
				{
					add.setEnabled(false);
					tel.setEnabled(false);
					edit.setText("Edit");
					cancel.setAlpha(0);
					new updatesyn().execute(id.getText().toString(),add.getText().toString(),tel.getText().toString());
					z=0;
				}
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				add.setEnabled(false);
				tel.setEnabled(false);
				edit.setText("Edit");
				cancel.setAlpha(0);
				z=0;
			}
		});
		
		new detailsasync().execute(id.getText().toString());
		
		return v;
	}
	
	public class detailsasync extends AsyncTask<String, JSONObject, String>
	{
		

		@Override
		protected String doInBackground(String... params) {
			String check=null;
			RestAPI api=new RestAPI();
			
			try {
				JSONObject json=api.DocDetails(params[0]);
				JSONParser jp=new JSONParser();
				check=jp.getdocdetails(json);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return check;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			String check=null;
			check=result;
			
			String[] s=check.split("\\$"); //split by $
			
			name.setText(s[1]);
			add.setText(s[2]);
			tel.setText(s[3]);
			special.setText(s[4]);
			//Toast.makeText(getActivity(), s[1], Toast.LENGTH_SHORT).show();
			
		}
		
	}
	
	public class updatesyn extends AsyncTask<String, JSONObject, String>
	{

		@Override
		protected String doInBackground(String... params) {
			String check="back";
			RestAPI api=new RestAPI();
			
			try {
				JSONObject json=api.UpdateDoc(params[0], params[1], params[2]);
				JSONParser jp=new JSONParser();
				check=jp.update(json);
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
			if(res.compareTo("post")==0)
			{
				Toast.makeText(getActivity(), "Problem in Updated", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(getActivity(), "Updated", Toast.LENGTH_SHORT).show();
			}
		}
		
	}

}
