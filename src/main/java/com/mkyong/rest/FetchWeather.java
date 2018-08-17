package com.mkyong.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class FetchWeather {

	public static void main(String args[]){
		
	}
	public String getWeather(String location) throws MalformedURLException {

		 URL url;
		//String location1="ohio";
			url = new URL("https://api.worldweatheronline.com/free/v2/weather.ashx?q="+location+"&format=json&num_of_days=1&key=f42bf4e770b1c2260b16172b1f01b");
		
        System.out.println("URL for location: " + url);
        String currentWeather ="";
        try (InputStream is = url.openStream();
                JsonReader rdr = Json.createReader(is)) {
            JsonObject obj = rdr.readObject();
            JsonObject places = obj.getJsonObject("data");
            JsonArray results = places.getJsonArray("current_condition");
           
            for (JsonObject result : results.getValuesAs(JsonObject.class)) {

            	JsonArray result1= result.getJsonArray("weatherDesc");
            	
            	for(JsonObject weather : result1.getValuesAs(JsonObject.class)){
            		
            		 currentWeather= weather.getString("value");
            	}
            	

            }} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        return currentWeather;
	}
}