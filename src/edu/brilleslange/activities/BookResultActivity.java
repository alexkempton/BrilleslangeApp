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

public class BookResultActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchresults);
		Bundle extras = getIntent().getExtras(); 
		Record r;
		if(extras !=null) {
			r = (Record) getIntent().getSerializableExtra("Record");

		}




	}
}