package edu.brilleslange.activities;

import edu.brilleslange.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.EditText;

public class BookALibrarianActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookalibrarian);
		ImageButton b = (ImageButton)findViewById(R.id.bookbutton);
		
		EditText editTextUsername = (EditText)findViewById(R.id.bookALibrarianUsername);
		
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);
		
		
		b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				EditText editTextUsername = (EditText)findViewById(R.id.bookALibrarianUsername);
				String username =  editTextUsername.getText().toString();
				
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
				emailIntent.setType("plain/text");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"alex.kempton@gmail.com"});

				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Testmail");

				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Brukernavn: " + username);

				startActivity(Intent.createChooser(emailIntent, "Send mail..."));
			}
		});  

	}
}
