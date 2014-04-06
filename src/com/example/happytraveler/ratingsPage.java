package com.example.happytraveler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
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
		stars = (RatingBar) findViewById(R.id.stars);
		send = (Button) findViewById(R.id.send);
		send.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//float rating=stars.getRating();	
				//GetConfirm confirm=new GetConfirm(stars.getRating(),getRatedName(),getDriverStatus());
				//GetConfirm confirm=new GetConfirm();
				//String response=confirm.doInBackground();
				(new GetConfirm()).execute((Object)null);
				//System.out.printf("%s %s %s %d\n","Data submited!!",getRatedName(),"was rated",stars.getRating());
		 	}
		});
	}
	
	private class GetConfirm extends AsyncTask<Object,Object,Object>{
		//private float r=0;
		//private int u=0;
		//private boolean d=null;
		
		/**
		 * the actual constructor, passes the values of user_id and rating from
		 * the ratingsPage class to GetConfirm
		 * @param rating
		 * @param user_id
		 */
		/**public GetConfirm(float rating,int user_id,boolean driverStatus){
			r=rating;
			u=user_id;
			d=driverStatus;
		}
		*/
		/**
		 * sends user_id and rating information to web-server for storage
		 * will print an error message if the values weren't initialized
		 */
		protected String doInBackground(Object... args) { 
			setDriverStatus(true);
			setRatedName(1);
			/**if(r==0||u==0){
				System.err.printf("%s\n","user_id and rating were not passed to GetConfirm");
			    return "no";
			}*/
			/**else{
				if(d)
					return ServerInterface.setDriverRating(""+u,""+r,getString(R.string.server_url));
				else
					return ServerInterface.setRiderRating(""+u,""+r,getString(R.string.server_url));
			}*/
			if(getDriverStatus())
				return ServerInterface.setDriverRating(getRatedName(),stars.getRating(),getString(R.string.server_url));
			else
				return ServerInterface.setRiderRating(getRatedName(),stars.getRating(),getString(R.string.server_url));
		}
		
		protected void onPostExectute(Object objResult){
			if(objResult != null && objResult instanceof String) {                          
                String result = (String) objResult;
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(getApplicationContext(), "Didn't get any result from server", Toast.LENGTH_SHORT).show();
		}
	}
}

class ServerInterface{
	 public static String SERVER_URL ;

	 public static String setDriverRating(int user, float rating, String server_url) {
	     SERVER_URL = server_url ;
	     String data = "command=" + URLEncoder.encode("setDriverRating");
	     data += "&user_id=" + user;
	     data += "&rating=" + rating;
	     return executeHttpRequest(data);
	 }
	 
	 public static String setRiderRating(int user, float rating, String server_url) {
	     SERVER_URL = server_url ;
	     String data = "command=" + URLEncoder.encode("setRiderRating");
	     data += "&user_id=" + user;
	     data += "&rating=" + rating;
	     return executeHttpRequest(data);
	 }
	 
	 private static String executeHttpRequest(String data) {
		 String result = "";
	     try {
	    	 URL url = new URL(SERVER_URL);
	         URLConnection connection = url.openConnection();
	         connection.setDoInput(true);
	         connection.setDoOutput(true);
	         connection.setUseCaches(false);
	         connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	         DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
	         dataOut.writeBytes(data);
	         dataOut.flush();
	         dataOut.close();
	         DataInputStream dataIn = new DataInputStream(connection.getInputStream()); 
	         String inputLine;
	         while ((inputLine = dataIn.readLine()) != null) {
	        	 result += inputLine;
	         }
	         dataIn.close();
	     } catch (IOException e) {
	         e.printStackTrace();
	         result = null;
	     }

	     return result;
	 }
}
