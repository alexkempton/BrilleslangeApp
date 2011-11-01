package edu.brilleslange.activities;


import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import edu.brilleslange.R;
import edu.brilleslange.activities.MyLocation.LocationResult;
import edu.brilleslange.adapters.LaztAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
	MyLocationOverlay myLocationOverlay;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frontpage);
		map = (MapView) findViewById(R.id.mapview);

		//Location:
		myLocationOverlay = new MyLocationOverlay(this, map);
		map.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableMyLocation();



		list = (ListView)findViewById(R.id.list);
		adapt = new LaztAdapter(this);
		list.setAdapter(adapt);

		controller = map.getController();
		controller.animateTo(new GeoPoint(59940127, 10723279));
		controller.setZoom(14);
		
		// Overlays:
		List<Overlay> mapOverlays = map.getOverlays();
		
		Drawable bib2Icon = this.getResources().getDrawable(R.drawable.maps2);
		Drawable bib1Icon = this.getResources().getDrawable(R.drawable.maps1);
		
		MyOverlay bib2Overlay = new MyOverlay(bib2Icon, this);
		MyOverlay bib1Overlay = new MyOverlay(bib1Icon, this);

		GeoPoint ojd = new GeoPoint(59944078, 10719089);
		GeoPoint vbh = new GeoPoint(59940127, 10723279);
		
		OverlayItem ojdItem = new OverlayItem(ojd, "Ole Johan Dals Hus", "GaustadallÈen 23\n0373 Oslo, Norge");
		OverlayItem vbhItem = new OverlayItem(vbh, "Vilhelm Bjerknes' hus", "Moltke Moes vei 35\n0851 Oslo, Norge");

		bib2Overlay.addOverlay(ojdItem);
		bib1Overlay.addOverlay(vbhItem);
		
		mapOverlays.add(bib2Overlay);
		mapOverlays.add(bib1Overlay);
		mapOverlays.add(myLocationOverlay); 
		

		final Class[] activitados = {
				SearchScreenActivity.class,
				BookALibrarianActivity.class,
				ArticleOfTheWeekActivity.class,
				FacebookActivity.class,
				//BuildingPlanActivity.class,
				TwitterActivity.class
		};

		list.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent myIntent = new Intent(v.getContext(), activitados[position]);
				startActivityForResult(myIntent, 0);
			}
		});

	}
	
	
	
	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableMyLocation();
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		myLocationOverlay.enableMyLocation();
	}



	@Override //Denne må være med når aktiviteten viser et kart
	protected boolean isRouteDisplayed() {
		return false;
	}

}
