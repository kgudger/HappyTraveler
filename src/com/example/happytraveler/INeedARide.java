package com.example.happytraveler;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;


/**
 * This Activity collects information about where the rider is going
 * and sends it to the server along with the user information.
 * 
 * @author Nick Vargas
 *
 */

public class INeedARide extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ride_needed);
	}
	
	public void sendRequest(View view) {
		EditText streetEdit  = (EditText)findViewById(R.id.editText1);
		Spinner stateSelect = (Spinner) findViewById(R.id.spinner1);
		String street = streetEdit.getText().toString();
		String state = stateSelect.getSelectedItem().toString();
	}
	
}
