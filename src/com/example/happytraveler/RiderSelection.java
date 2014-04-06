package com.example.happytraveler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * This Activity collects information about where the rider is going
 * and sends it to the server along with the user information.
 * 
 * @author Nick Vargas
 *
 */

public class RiderSelection extends Activity   {
	String[] DayOfWeek = new String[] {"Leta Block", "Livia Tribble", "Anamaria Mcgivney", "Eloise Effler", "Carisa Fabre"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rider_selection);
		ListView list1 = (ListView) findViewById(R.id.listView1);
		list1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DayOfWeek));
		//la.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DayOfWeek));
	}
	

	
}
