package edu.brilleslange.activities;
/* Denne bruker vi vel ikke mer
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
//	SearchResultsAdapter adapt;
	ArrayAdapter adapt;
	
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

		
//		adapt = new SearchResultsAdapter(this,results);
		ArrayList<Record> results = bibsys.search(value);
		ArrayList<String> l = new ArrayList<String>();
		for(Record r : results){
			l.add(r.title);
		}
		
		adapt = new ArrayAdapter(this,R.layout.searchresultsitem,l);
		list = (ListView)findViewById(R.id.searchresultslist);
		list.setAdapter(adapt);
		list.setTextFilterEnabled(true);





	}
}*/