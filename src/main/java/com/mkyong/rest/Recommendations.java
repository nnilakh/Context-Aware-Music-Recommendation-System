package com.mkyong.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/recommendation")
public class Recommendations {

	/**
	 * @param args
	 */
	
	@POST
	@Path("/userRecommendation")
	public Response userRecommendations(){
		
		
		System.out.println("Hello");
		DbConnection con= new DbConnection();
		
		String mood= "happy";
		String weather= "sunny";
		String userId= "2";
		System.out.println("Before recoo:");
		con.fetchUserRecoBasedonParameters(mood, weather, userId);
		System.out.println("after reco..");
		
		return Response.status(200).build();
	}
	
	
	
}
