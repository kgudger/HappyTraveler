package com.example.happytraveler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.*;

public class ServerInterface {
	 public static String SERVER_URL ;
	 private Activity parentActivity;
	 
	 public ServerInterface(Activity context){
		 parentActivity = context;
	 }
	 
	 public String setDriverRating(int user, float rating, int server_url) {
	     SERVER_URL = parentActivity.getString(server_url) ;
	     String data = "command=" + URLEncoder.encode("setDriverRating");
	     data += "&user_id=" + user;
	     data += "&rating=" + rating;
	     return executeHttpRequest(data);
	 }
	 
	 public int getDriver(String user, int server_url){
		 SERVER_URL = parentActivity.getString(server_url) ;
	     String data = "command=" + URLEncoder.encode("getDriver");
	     data += "&user_id=" + user;
	     return Integer.parseInt(executeHttpRequest(data));
	 }
	 
	 public String setRiderRating(int user, float rating, int server_url) {
	     SERVER_URL = parentActivity.getString(server_url) ;
	     String data = "command=" + URLEncoder.encode("setRiderRating");
	     data += "&user_id=" + user;
	     data += "&rating=" + rating;
	     return executeHttpRequest(data);
	 }
	 
	 public int getRider(String user, int server_url){
		 SERVER_URL = parentActivity.getString(server_url) ;
	     String data = "command=" + URLEncoder.encode("getRider");
	     data += "&user_id=" + user;
	     return Integer.parseInt(executeHttpRequest(data));
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
