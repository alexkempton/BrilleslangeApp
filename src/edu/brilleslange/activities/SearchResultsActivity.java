package edu.brilleslange.activities;

import java.util.ArrayList;

import edu.brilleslange.R;
import edu.brilleslange.adapters.LaztAdapter;
import edu.brilleslange.adapters.SearchResultsAdapter;
import edu.brilleslange.bl.BibsysBridge;
import edu.brilleslange.bl.Record;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResultsActivity extends Activity {
	ListView list;
	SearchResultsAdapter adapt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresults);
		Bundle extras = getIntent().getExtras(); 
		String value = "";
		if(extras !=null) {
			value = extras.getString("SearchString");
		}

		BibsysBridge bibsys = new BibsysBridge();

		ArrayList<Record> results = bibsys.search(value);
		list = (ListView)findViewById(R.id.searchresultslist);
		adapt = new SearchResultsAdapter(this,results);
		list.setAdapter(adapt);





	}
}