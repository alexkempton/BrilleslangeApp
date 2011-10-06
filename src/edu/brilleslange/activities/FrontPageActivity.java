package edu.brilleslange.activities;


import com.google.android.maps.MapActivity;

import edu.brilleslange.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class FrontPageActivity extends MapActivity{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frontpage);
		

		ImageButton goToTwitterButton = (ImageButton)findViewById(R.id.gototwitterbutton);
		ImageButton goToSearchButton = (ImageButton)findViewById(R.id.gotosearchbutton);
		ImageButton goToBookALibrarianButton = (ImageButton)findViewById(R.id.gotobookalibrarianbutton);
		ImageButton goToFacebookButton = (ImageButton)findViewById(R.id.gotofacebookbutton);
		ImageButton goToBuildingMapButton = (ImageButton)findViewById(R.id.findthewaybutton);

		goToTwitterButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), TwitterActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});

		goToBuildingMapButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), BuildingPlanActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
	
		
		goToSearchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), SearchScreenActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		goToBookALibrarianButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), BookALibrarianActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		
		goToFacebookButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), FacebookActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});


	}

	@Override //Denne må være med når aktiviteten viser et kart
	protected boolean isRouteDisplayed() {
		return false;
	}

}
