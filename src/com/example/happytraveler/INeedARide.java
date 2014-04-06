package com.example.happytraveler;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;


/**
 * This Activity collects information about where the rider is going
 * and sends it to the server along with the user information.
 * 
 * @author Nick Vargas
 *
 */

public class INeedARide extends Activity {
	
	private GoogleMap googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ride_needed_alt_alt);
//		googleMap.setMyLocationEnabled(true);
	}
	
	public void sendRequest(View view) throws IOException {
		double destinationLat, destinationLong, currentLat, currentLong;
		EditText locationText = (EditText)findViewById(R.id.editText1);
		String locationStr = locationText.getText().toString();
		EditText zipText = (EditText)findViewById(R.id.editText3);
		String zipStr = zipText.getText().toString();
		EditText stateText = (EditText)findViewById(R.id.editText2);
		String stateStr = stateText.getText().toString();
		locationStr = locationStr + " " + stateStr + " " + zipStr;
		
		List<Address> foundGeocode = null;
		// find the addresses  by using getFromLocationName() method with the given address
		foundGeocode = new Geocoder(this).getFromLocationName(locationStr, 1);
		String geoStr = foundGeocode.toString();
		Log.w("myApp", "Geocode: ");
		Log.w("myApp", geoStr);
		 destinationLat = foundGeocode.get(0).getLatitude(); //getting latitude
		 destinationLong = foundGeocode.get(0).getLongitude();//getting longitude
		 
		 LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		 Criteria criteria = new Criteria();
		 String provider = locationManager.getBestProvider(criteria, true);

		    //Get current location
		 Location myLocation = locationManager.getLastKnownLocation(provider);
		// if( myLocation != null){
			 currentLat = myLocation.getLatitude();
			 currentLong = myLocation.getLongitude();
	//	 }
//*/		 
		 
/****************************************************************************************************
		 // move destinationLat, destinationLong, currentLat, and currentLong to server
		  
****************************************************************************************************/ 
	}
	
	
}
