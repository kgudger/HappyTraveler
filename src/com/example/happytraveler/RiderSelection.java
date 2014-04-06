package com.example.happytraveler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
	ListView list;
	String[] namesOfRiders = new String[] {"Leta Block", "Livia Tribble", "Anamaria Mcgivney", "Eloise Effler", "Carisa Fabre"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rider_selection);
		ListView list1 = (ListView) findViewById(R.id.riderList);
		list1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namesOfRiders));
		//addListenersOnButtons();
		//la.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DayOfWeek));
	}
	
	public void moveToNext(View view) {
		Intent intent = new Intent(this, ConfirmPickup.class);
		startActivity(intent);
	}
	
	/*private void addListenersOnButtons(){
		final Context context = this;
		
		list = (ListView) findViewById(R.id.riderList);
		
		
		list.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view){
				
				Intent intent = new Intent(context, ConfirmPickup.class);
				startActivity(intent);
			}
		});
	}*/
	
}
