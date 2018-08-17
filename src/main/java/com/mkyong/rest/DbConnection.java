package com.mkyong.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mkyong.User;
import com.mysql.jdbc.PreparedStatement;

public class DbConnection {

	/**
	 * 
	 * @param user
	 * @return Success message
	 */
	public String createUser(User user){
	    String query;
	    String returnUser = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/moodmusicreco", "root", "admin");
	        Statement stmt = (Statement) con.createStatement();
	        query = "INSERT into users (username, pwd,location) values "
	        		+ "('"+user.getUsername()+"','"
	        		+user.getPassword()+"','"
	        		+user.getLocation()+"')"+";";
	        System.out.println(query);
	        
	        stmt.executeUpdate(query);
	        System.out.println("User Created Successfully");
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    returnUser = getUser(user.getUsername(), user.getPassword());
	    return returnUser;
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return userDetails - User Details as string
	 */
	public String getUser(String username, String password){
	    String query;
	    String userDetails = "";
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
	        Statement stmt = (Statement) con.createStatement();
	        
	        query = "Select * from users where username = '" + username + "' and pwd = '" + password + "'";
	        ResultSet rs = stmt.executeQuery(query);
	        
	        while(rs.next()){
	        	userDetails += "username:" + rs.getString("username") + ";";
	        	userDetails += "location:" + rs.getString("location") + ";";
	        	userDetails += "mood:" + rs.getString("mood") + ";";
	        	userDetails += "weather:" + rs.getString("weather") + ";";
	        	System.out.println("ud"+ userDetails);
	        }
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    System.out.println("userdetails"+ userDetails);
	    return userDetails;
	}
	
	public String getLocation(String username){
		
		 String query;
		 String location = "";
		    try {
		        Class.forName("com.mysql.jdbc.Driver").newInstance();
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
		        Statement stmt = (Statement) con.createStatement();
		        
		        query = "Select location from users where username = '" + username + "'"+ ";" ;
		        ResultSet rs = stmt.executeQuery(query);
		        
		        System.out.println("in db"+rs);
		        
		        while(rs.next()){
		        	location= rs.getString("location");
		        }
		    } catch (InstantiationException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println(location);
		return location;
	}
	
	public void insertWeatherDetails(String weather, String user){
		
		String query;
	    try {
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
	        Statement stmt = (Statement) con.createStatement();
	        query = "Update users SET weather='" + weather + "'" + "where username='" + user + "'" + ";";
	      	              
	        stmt.executeUpdate(query);
	        System.out.println("Weather Conditions added successfully!");
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void setMood(String mood, String username){
		
		String query;
	    try {
	    	System.out.println("In Set mood");
	        Class.forName("com.mysql.jdbc.Driver").newInstance();
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
	        Statement stmt = (Statement) con.createStatement();
	        query = "Update users SET mood='" + mood + "'" + "where username='" + username + "'" + ";";
	      	              
	        stmt.executeUpdate(query);
	        System.out.println("Mood updated successfully!");
	        
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void setItemRecommendation(long id, long recoId, float value){
		
		String query;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
	   /*     query = "Insert into itemrecommendations (itemid, recoitemid, value) values (?, ?, ?);";
	        System.out.println(query);
	        PreparedStatement stmt = (PreparedStatement) con.prepareStatement(query);
	        		
	        stmt.setLong(1, id);
	        stmt.setLong(2, recoId);
	        stmt.setFloat(3, value);  */
	        //stmt.executeUpdate();
	      
	        Statement stmt = con.createStatement();
	        query = "INSERT into itemrecommendations (itemid, recoitemid, value) values "
	        		+ "('"+id+"','"
	        		+recoId+"','"
	        		+value+"')"+";";
	        System.out.println(query);
	        
	        stmt.executeUpdate(query);
	        
	        con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void setUserRecommendation(long id, long recoId, float value){
		
		String query;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
	  
	      
	        Statement stmt = con.createStatement();
	        query = "INSERT into userrecommendations (userid, recoitemid, value) values "
	        		+ "('"+id+"','"
	        		+recoId+"','"
	        		+value+"')"+";";
	        System.out.println(query);
	        
	        stmt.executeUpdate(query);
	        
	        con.close();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fetchUserRecoBasedonParameters(String mood, String weather, String userId){

		String query;
		ResultSet rs;
		
		    try {
		        Class.forName("com.mysql.jdbc.Driver").newInstance();
		        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco", "root", "admin");
		        Statement stmt = (Statement) con.createStatement();
		        
		        
		        query= "select * from song s, userrecommendations u where " 
        						+ "u.userid ='" + userId + "'" +" and "//username='" + username + "'" +
		        				+ "u.recoitemid = s.id and "
		        				+ "s.weather ='" + weather + "'" + " and "
		        				+ "s.mood ='" + mood + "'" 
		        				+ " ORDER BY s.song_hotttnesss,u.value DESC;";
		        System.out.println("Songs wali query::"+query);	
		        		
		       rs= stmt.executeQuery(query);
		       if (rs.next()) {
		    	   String id = rs.getString(3);
		    	   System.out.println("id=" + id);
		    	   }
		    
		    } catch (InstantiationException e) {
		        e.printStackTrace();
		    } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
