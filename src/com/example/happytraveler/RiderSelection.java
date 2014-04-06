package com.example.happytraveler;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;


/**
 * This Activity collects information about where the rider is going
 * and sends it to the server along with the user information.
 * 
 * @author Nick Vargas
 *
 */

public class RiderSelection extends ListActivity {
	String[] DayOfWeek = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rider_selection);
		ListActivity la = new ListActivity();
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DayOfWeek));
	}
	

	
}
