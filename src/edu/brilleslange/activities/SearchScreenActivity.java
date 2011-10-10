package edu.brilleslange.activities;


import java.util.ArrayList;

import edu.brilleslange.R;
import edu.brilleslange.bl.BibsysBridge;
import edu.brilleslange.bl.Record;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SearchScreenActivity extends Activity {
	//String searchValue;
	BibsysBridge bibsys = new BibsysBridge();
	ArrayList<String> l = new ArrayList<String>();
	ListView list;
	ArrayAdapter<String> adapt;
	ArrayList<Record> results;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchscreen);
		Button searchb = (Button) findViewById(R.id.searchbutton);
		adapt = new ArrayAdapter<String>(this,R.layout.searchresultsitem,l);
		list = (ListView)findViewById(R.id.searchresultslist);
		list.setAdapter(adapt);
		list.setTextFilterEnabled(true);

		searchb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				EditText edittxt = (EditText) findViewById(R.id.entry);
				String searchValue = edittxt.getText().toString();
				updateSearchResults(searchValue);
			}
		});

		

	}

	void updateSearchResults(String s){
		l.clear();
		results = bibsys.search(s);
		for(Record r : results){
			l.add(r.title);
		}
		adapt.notifyDataSetChanged();
	}
}


class MyItemClickListener implements OnItemClickListener{
	SearchScreenActivity ssa;
	
	MyItemClickListener(SearchScreenActivity ssa){
		this.ssa = ssa;
	}
	@Override
	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
		Intent myIntent = new Intent(v.getContext(), SearchResultsActivity.class);
		myIntent.putExtra("SearchString", ssa.results.get(position));
		ssa.startActivityForResult(myIntent, 0);
	}
}