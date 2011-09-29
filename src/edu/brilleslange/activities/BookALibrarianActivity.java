package edu.brilleslange.activities;

import edu.brilleslange.R;
import edu.brilleslange.R.layout;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookALibrarianActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bookalibrarian);
		Button b = (Button)findViewById(R.id.bookbutton);
		
		
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
