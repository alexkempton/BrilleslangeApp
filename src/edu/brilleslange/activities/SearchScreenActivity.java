package edu.brilleslange.activities;


import edu.brilleslange.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchScreenActivity extends Activity {
	String searchValue;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchscreen);
		Button searchb = (Button) findViewById(R.id.searchbutton);
		searchb.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				EditText edittxt = (EditText) findViewById(R.id.entry);
				searchValue = edittxt.getText().toString();
				Intent myIntent = new Intent(view.getContext(), SearchResultsActivity.class);
				myIntent.putExtra("SearchString", searchValue);
				startActivityForResult(myIntent, 0);
			}
		});

	}
}
