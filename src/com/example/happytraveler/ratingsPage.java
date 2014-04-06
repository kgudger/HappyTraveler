/**
 * 
 * 
 * 
 * ATTENTION: in your doInbackground method you have hardcoded 2 
 * temp values for variables which need to be given proper values
 * FIX THIS
 * 
 * 
 * 
 */

package com.example.happytraveler;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.content.Context;
import android.content.Intent;

/**
 * class accepts input from user, indicating rating of driver/passenger
 * input is formated as a selection of 5 stars
 * user touches a star indicating a number value that they want the user to be rated to receive
 * 
 * GUI: stars initially appear gray, when a star is touched it and all stars to the left of it turn gold
 * and a message appears correlated to the star rating
 * user then presses a confirm button to send rating input
 * 
 * sends this input along with the name of the user to be rated to web-server
 * 
 * @author batesmotel37
 *
 * @param: name of driver/passenger
 *{none, triggered by a button press from other class}
 * 
 * @return: none
 */
public class ratingsPage extends Activity{
	private int ratedName;
	private boolean driverStatus;
	private Button send;
	private RatingBar stars;
	private TextView displayRating;
	private ServerInterface server;
	
	public int getRatedName() {
		return ratedName;
	}

	private void setRatedName(int ratedName) {
		this.ratedName = ratedName;
	}
	
	/**
	 * a secondary constructer used purely to catch errors
	 * if the previous class doesn't send a userToBeRatedName or driverStatus
	 * then the program will send an error message and abort
	 */
	public ratingsPage(){
		System.err.printf("%s\n","no user name recieved--operation ratingsPage aborted");
	}
	
	/**
	 * constructer; stores userToBeRatedName and driverStatus for later use
	 * @param userToBeRatedName
	 * @return nothing
	 */
	public ratingsPage(int userToBeRatedName, boolean driverStatus){
		//setRatedName(userToBeRatedName);
		setRatedName(1);
		setDriverStatus(driverStatus);
	}
	
	private void setDriverStatus(boolean d) {
		this.driverStatus=d;
	}
	
	private boolean getDriverStatus(){
		return driverStatus;
	}  
	
	/**
	 * a set of methods that shows 5 grey stars
	 * user touches a star turning all stars to the left of it green
	 * prompt appears with a verbal equivalent to stars given
	 * and a button to send the rating to the server
	 * when the button is pressed an integer value representing the number of stars 
	 * and the name of the user being rated are sent to a web-server
	 */	
	/**
	 * code used in onCreate() and addListenerButton() adapted from example found at:
	 * http://www.mkyong.com/android/android-rating-bar-example/
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		server= new ServerInterface(this);
		setContentView(R.layout.ratings_page_star1);
		addListenerOnRatingBar();
		addListenerOnButton();
	}
 
	public void addListenerOnRatingBar() {	  
		stars = (RatingBar) findViewById(R.id.stars);
		displayRating = (TextView) findViewById(R.id.displayRating);
		stars.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar stars, float rating,boolean fromUser) {
		 		displayRating.setText(String.valueOf(rating));
		 	}
		});
	}
	
	public void addListenerOnButton() {
		final Context context = this;
		stars = (RatingBar) findViewById(R.id.stars);
		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//final Context context = this;
				//float rating=stars.getRating();	
				//GetConfirm confirm=new GetConfirm(stars.getRating(),getRatedName(),getDriverStatus());
				//GetConfirm confirm=new GetConfirm();
				//String response=confirm.doInBackground();
				(new GetConfirm()).execute((Object)null);
				if(getDriverStatus()){//if the person being rated is a driver
					//then this person is a passenger and should just be spit back out at the homepage
					Intent intent = new Intent(context, HappyTraveler.class);
					startActivity(intent);
				}
				else{
					Intent intent = new Intent(context, HappyTraveler.class);
					startActivity(intent);
					/*
					Intent intent = new Intent(context, );
					startActivity(intent);
					*/
					/*String data = "http://sluglug.soe.ucsc.edu/~keith/HT/DocAuto.png";

	                Uri uri = Uri.parse(data);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			        startActivity(intent);*/
				}
				//System.out.printf("%s %s %s %d\n","Data submited!!",getRatedName(),"was rated",stars.getRating());
		 	}
		});
	}
	
	private class GetConfirm extends AsyncTask<Object,Object,Object>{
		protected String doInBackground(Object... args) { 
			setDriverStatus(true);
			setRatedName(1);
			//ServerInterface server= new ServerInterface(this);
			if(getDriverStatus())
				return server.setDriverRating(getRatedName(),stars.getRating(),R.string.server_url);
			else
				return server.setRiderRating(getRatedName(),stars.getRating(),R.string.server_url);
		}
		/**
		protected void onPostExectute(Object objResult){
			if(objResult != null && objResult instanceof String) {                          
                String result = (String) objResult;
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(getApplicationContext(), "Didn't get any result from server", Toast.LENGTH_SHORT).show();
		}
		*/
	}
}
