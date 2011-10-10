package edu.brilleslange.activities;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import edu.brilleslange.R;
import edu.brilleslange.adapters.LaztAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class FrontPageActivity extends MapActivity implements Runnable {
	
	ListView list;
	LaztAdapter adapt;
	MapView map;
	MapController controller;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frontpage);
		map = (MapView) findViewById(R.id.mapview);
		map.setStreetView(true);
		map.setSatellite(false);
		
		list = (ListView)findViewById(R.id.list);
		adapt = new LaztAdapter(this);
		list.setAdapter(adapt);

		controller = map.getController();
		controller.animateTo(new GeoPoint(59913837, 10752139));
		controller.setZoom(12);
		//run();
		
		
		final Class[] activitados = {
			BookALibrarianActivity.class,
			BookALibrarianActivity.class,
			FacebookActivity.class,
			BuildingPlanActivity.class,
			SearchScreenActivity.class,
			TwitterActivity.class
		};
		
		list.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent myIntent = new Intent(v.getContext(), activitados[position]);
				startActivityForResult(myIntent, 0);
			}
		});
		
		
/*
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
		*/


	}


	@Override //Denne må være med når aktiviteten viser et kart
	protected boolean isRouteDisplayed() {
		return false;
	}


	@Override
	public void run() {
		try {
			wait(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			controller.zoomInFixing(0, 0);
		}
		
	}

}
