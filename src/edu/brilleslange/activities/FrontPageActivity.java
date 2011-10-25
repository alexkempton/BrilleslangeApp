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


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frontpage);
		map = (MapView) findViewById(R.id.mapview);

		//Location:
		MyLocationOverlay myLocationOverlay = new MyLocationOverlay(this, map);
		map.getOverlays().add(myLocationOverlay);
		myLocationOverlay.enableMyLocation();



		list = (ListView)findViewById(R.id.list);
		adapt = new LaztAdapter(this);
		list.setAdapter(adapt);

		controller = map.getController();
		controller.animateTo(new GeoPoint(59940127, 10723279));
		controller.setZoom(14);

		List<Overlay> mapOverlays = map.getOverlays();
		Drawable bib1Icon = this.getResources().getDrawable(R.drawable.maps1);
		Drawable bib2Icon = this.getResources().getDrawable(R.drawable.maps2);
		Drawable locIcon = this.getResources().getDrawable(R.drawable.blue_dot);
		MyOverlay bib1Overlay = new MyOverlay(bib1Icon, this);
		MyOverlay bib2Overlay = new MyOverlay(bib2Icon, this);
		MyOverlay locOverlay = new MyOverlay(locIcon); // todo

		GeoPoint vbh = new GeoPoint(59940127, 10723279);
		GeoPoint ojd = new GeoPoint(59944078, 10719089);
		GeoPoint loc = new GeoPoint(59944578, 10719589); // todo
		OverlayItem vbhItem = new OverlayItem(vbh, "Vilhelm Bjerknes' hus", "Moltke Moes vei 35\n0851 Oslo, Norge");
		OverlayItem ojdItem = new OverlayItem(ojd, "Ole Johan Dals Hus", "GaustadallÈen 23\n0373 Oslo, Norge");
		OverlayItem locItem = new OverlayItem(loc, null, null); // todo

		bib1Overlay.addOverlay(vbhItem);
		bib2Overlay.addOverlay(ojdItem);
		locOverlay.addOverlay(locItem); // todo
		mapOverlays.add(bib1Overlay);
		mapOverlays.add(bib2Overlay);
		mapOverlays.add(myLocationOverlay); // todo

		final Class[] activitados = {
				BookALibrarianActivity.class,
				ArticleOfTheWeekActivity.class,
				FacebookActivity.class,
				//BuildingPlanActivity.class,
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
