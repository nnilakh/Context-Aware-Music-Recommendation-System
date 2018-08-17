package com.mkyong.rest;

import java.net.MalformedURLException;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.mkyong.User;
import com.sun.jersey.api.view.Viewable;

@Path("/user")
public class Authentication {

public DbConnection conn;
FetchWeather weather= new FetchWeather();
	
	/**
	 * Sign up functionality for the user
	 * @param username
	 * @param password
	 * @param age
	 * @param gender
	 * @param occupation
	 * @return
	 * @throws MalformedURLException 
	 */
	@POST
	@Path("/signup")
	public Response userprofile(
		@FormParam("username") String username,
		@FormParam("password") String password,
		@FormParam("location") String location, 
		@FormParam("mood") String mood ) throws MalformedURLException {
	
		User user = new User(username, password, location, mood);			
		conn = new DbConnection();
		String userDetails = "";
		userDetails = conn.createUser(user);	
		
		
		location= conn.getLocation(username);
		System.out.println("location in service"+ location);
		
		String weather1= weather.getWeather(location);
		
		conn.insertWeatherDetails(weather1, username);
		
		System.out.println(weather1);


		return Response.status(200).entity(userDetails).build();

	}
	
	/**
	 * Login functionality for the user
	 * @param username
	 * @param password
	 * @return
	 * @throws MalformedURLException 
	 */
	@POST
	@Path("/login")
	public Response loginProfile(
		@FormParam("username") String username,
		@FormParam("password") String password, 
		@FormParam("location") String location	) throws MalformedURLException{

		conn = new DbConnection();
		String userDetails = "";
		
		location= conn.getLocation(username);
		System.out.println("location in login"+ location);
		
		String weather1= weather.getWeather(location);
		
		conn.insertWeatherDetails(weather1, username);
		
		System.out.println("new weather "+ weather1);
		
		
		userDetails = conn.getUser(username, password);
		
		
		
		System.out.println("paass "+password+" afterPAS ");
		System.out.println("hello"+userDetails);
		
		
				
		return Response.status(200).entity(userDetails).build();
	}
	
	@POST
	@Path("/mood")
	public Response fetchMood(@FormParam("mood") String mood){
		
		
		
		conn = new DbConnection();
	//	String userDetails = "";
		
		
		System.out.println("My moood iss:: "+mood);
		
		String[] parts = mood.split(",");
		String part1 = parts[0]; // 004
		String part2 = parts[1];
		System.out.println("part1: "+part1);
		System.out.println("part2: "+part2);
	
		conn = new DbConnection();
		conn.setMood(part1, part2);
		
	//	userDetails = conn.getUser(username, password);
		
		return Response.status(200).build();
		
	}
	

}
