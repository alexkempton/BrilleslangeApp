package edu.brilleslange.activities;

import java.util.ArrayList;

import edu.brilleslange.R;
import edu.brilleslange.bl.BibsysBridge;
import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SearchResultsActivity extends ListActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       Bundle extras = getIntent().getExtras(); 
       String value = "";
       if(extras !=null) {
           value = extras.getString("SearchString");
       }
       
       BibsysBridge bibsys = new BibsysBridge();
       ArrayList<String> results = bibsys.search(value);
       setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, results ));

       ListView lv = getListView();
       lv.setTextFilterEnabled(true);
       
       

    }
}