package com.example.happytraveler;

import java.io.IOException;
import java.util.List;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 *
 * @author Brendan
 *
 */
public class IHaveARide extends Activity {
	EditText timeText;
	int time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ihave_aride);
		time = 0;
//		mMap.setMyLocationEnabled(true);
	}


	
	public void submitClick(View v) throws IOException{
//		Location = myLocation = mMap.getLocation();
		double destinationLat, destinationLong; //, currentLat, currentLong;
		//timeText = (EditText)findViewById(R.id.editText2);
		
		EditText locationText = (EditText)findViewById(R.id.editText1);
		String locationStr = locationText.getText().toString();
		EditText zipText = (EditText)findViewById(R.id.editText3);
		String zipStr = zipText.getText().toString();
		
		locationStr = locationStr + " " + zipStr;
		
		List<Address> foundGeocode = null;
		// find the addresses  by using getFromLocationName() method with the given address
		foundGeocode = new Geocoder(this).getFromLocationName(locationStr, 1);
		 destinationLat = foundGeocode.get(0).getLatitude(); //getting latitude
		 destinationLong = foundGeocode.get(0).getLongitude();//getting longitude
		 
//		 currentLat = location.getLatitude();
		 
	 
		 Intent intent = new Intent( this, HappyTraveler.class);
		 startActivity(intent);
	}
	
	public void goHome(View v){
		 Intent intent = new Intent(this, HappyTraveler.class);
		 startActivity(intent);
	}
	
	

}
