package com.example.happytraveler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class HappyTraveler extends FragmentActivity {
	static final LatLng UCSANTACRUZ = new LatLng(36.98904,-122.06441);
    static final LatLng SANTACRUZDOWNTOWN = new LatLng(36.96996,-122.02497);
    private GoogleMap map;
	Button driver, rider; //the two buttons

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_happy_traveler);
        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        Marker origin = map.addMarker(new MarkerOptions().position(UCSANTACRUZ)
                .title("Santa Cruz"));
            Marker dest = map.addMarker(new MarkerOptions()
                .position(SANTACRUZDOWNTOWN)
                .title("Downtown")
                .snippet("Santa Cruz is cool")
                .icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.ic_launcher)));

        // Move the camera instantly to hamburg with a zoom of 15.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(UCSANTACRUZ, 15));

        // Zoom in, animating the camera.
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        addListenersOnButtons(); //Sets buttons to respond to clicks
	}

	public void addListenersOnButtons(){
		
		final Context context = this;
		
		driver = (Button) findViewById(R.id.driver_button);
		rider = (Button) findViewById(R.id.rider_button);
		
		driver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, IHaveARide.class);
				startActivity(intent);
			}
		});
		
		rider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, INeedARide.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.happy_traveler, menu);
		return true;
	}

}