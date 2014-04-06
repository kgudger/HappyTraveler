package com.example.happytraveler;

import java.util.LinkedList;

import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class profile extends Activity{
	private int phoneNo;
	LinkedList<String> cars=new LinkedList<String>();
	private String username;
	private String email;
	private String name;
	ServerInterface server= new ServerInterface(this);
	private Button changePassword;
	private Button logout;
	
	public profile(String name, String username, String email,int phoneNo){
		setName(name);
		setUsername(username);
		//setCars(cars_head);
		setEmail(email);
		setPhone(phoneNo);
	}
	
	public profile(){
		String u=""+(int)(Math.random()*5);
		String n="";
		for(int c=0;c<6;c++){
			char i=(char) (65+(int)(Math.random()*(c+20)));
			n+=i;
		}
		String e=n+"@gmail.com";
		int p=0;
		for(int c=0;c<7;c++){
			p+=Math.pow(10.0,(double)c)*(Math.random()*10);
		}
		//profile(n,u,e,p);
		setName(n);
		setUsername(u);
		setEmail(e);
		setPhone(p);
	}

	public int getDriverRating(){
		return server.getDriver(getUsername(),R.string.server_url);
	}
	
	public int getRiderRating(){
		return server.getRider(getUsername(),R.string.server_url);
	}
	
	public int getPhone(){
		return phoneNo;
	}

	private void setPhone(int p) {
		phoneNo=p;
	}

	public String getEmail(){
		return email;
	}
	
	private void setEmail(String e) {
		email=e;
	}

	public String getUsername(){
		return username;
	}
	
	private void setUsername(String u) {
		username=u;
	}

	public String getName(){
		return name;
	}
	
	private void setName(String n) {
		name=n;
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        //addListenersOnButtons(); //Sets buttons to respond to clicks
	}
	
	public void addListenersOnButtons(){
		//final Context context = this;
		changePassword = (Button) findViewById(R.id.change_password);
		logout=(Button)findViewById(R.id.logout);
		changePassword.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				//STUB
			}
		});
		logout.setOnClickListener(new OnClickListener() {
			public void onClick(View view){
				//STUB
			}
		});
	}
	
}
