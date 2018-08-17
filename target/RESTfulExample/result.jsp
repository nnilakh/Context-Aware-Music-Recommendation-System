<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script  src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

<script type="text/javascript">
				function showRecommendations(){
					        $.ajax({
					                url : "musicrecommender/recommendation/userRecommendation",
					            type: "POST",
					            success:function(data, textStatus, jqXHR){
					                    alert(data);
					                    console.log(data);
					                    window.location.href="home.jsp?user="+username;
					            },
					            error: function(jqXHR, textStatus, errorThrown){
					                    alert('Could not process request.. ' + errorThrown);
					            }
					        });
					}
</script> 


<title>Recommendations</title>
<link rel="stylesheet" href="resources/css/music.css" type="text/css" media="all" />
<center><h2>Hello <b><%= request.getParameter("user") %></b>!</h2></center>
</head>
		<body>
		 <center>
		 <br>
		 <br>
		 <br>
		 <br>
		 <br>
		 <br>
		 <br>
            <table border="1" width="60%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2"><h1>We recommend the following Songs for you.!!!</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        	<td><b>Artist</td>
                        	<td><b>Song</td>

                    </tr>

<%@ page import ="java.sql.*" %>
<%
    String username = request.getParameter("user");
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moodmusicreco",
            "root", "aneri");
    Statement st = con.createStatement();
    ResultSet rs, user_rs;
    user_rs = st.executeQuery("select id, mood, weather from users where username = '" + username + "'"+ ";");
    if (user_rs.next()) {
    	String id = user_rs.getString(1);
    	String mood = user_rs.getString(2);
    	String weather = user_rs.getString(3);

    String query = 	"select u.userid, s.title, s.artist_name from moodmusicreco.song s, moodmusicreco.new_table u where "
    		+ "u.userid = '" + id + "'"+ " and "
    		+ "u.songid = s.id and "
    		+ "s.weather = '" + weather + "'"+ " and "
    		+ "s.mood = '" + mood + "'"
    		+ " ORDER BY s.song_hotttnesss,u.pref DESC;";
    System.out.println("query =" + query);
    		
    rs = st.executeQuery("select u.userid, s.title, s.artist_name from moodmusicreco.song s, moodmusicreco.new_table u where "
    		+ "u.userid = '" + id + "'"+ " and "
    		+ "u.songid = s.id and "
    		+ "s.weather = '" + weather + "'"+ " and "
    		+ "s.mood = '" + mood + "'"
    		+ " ORDER BY s.song_hotttnesss,u.pref DESC;");

	while (rs.next()) {
        
String song = rs.getString(2);
String artist = rs.getString(3);
System.out.println("Artist =" + artist);
System.out.println("Song =" + song);
		%>
                    <tr>
                        <td><%=artist %></td>
                        <td><%=song %></td>
                    </tr>
                    <tr>

	    
	<% 
	} 
    }
	%>	
            </tbody>
            </table>
            </center>

<button type="button" id="songs" onclick="showRecommendations()" value="GO !!">Grab It!!</button>

</body>
</html>
