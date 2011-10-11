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

public class FrontPageActivity extends MapActivity {
	
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
		
		


	}


	@Override //Denne må være med når aktiviteten viser et kart
	protected boolean isRouteDisplayed() {
		return false;
	}



	

}
