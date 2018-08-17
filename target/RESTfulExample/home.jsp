<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<title>Home</title>
<link rel="stylesheet" href="resources/css/music.css" type="text/css" media="all" />
<script type="text/javascript">
function getMood(){
	//alert("hiee");
	var mood = $('#mood').val();
	var weather = $('#weather').val();

	var test=window.location.search.replace("?", "");
	var username= test.split("=")[1];

	alert(test+"helloo "+username);
	$.ajax({
		url : "musicrecommender/user/mood",
	    type: "POST",
	    data : "mood=" + mood + "," + username,
	   
	    success:function(data, textStatus, jqXHR){
	    	alert('success'+ data);
	    	window.location.href="result.jsp?user="+username+ "&mood="+mood + "weather"+ weather;
	    },
	    error: function(jqXHR, textStatus, errorThrown){
	    	alert('Could not process request.. ' + errorThrown);
	    }
	});
}
</script>

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
            <table border="1" width="50%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2"><h1>Welcome to Music World!!</h1></th>
                    </tr>
                </thead>
                <tbody>
                <tbody>
                    <tr>
                        <td><b>Select Mood</td>
                        <td>
                        <select> 
 							 <option id = mood value="happy">Happy</option>
 							 <option id = mood value="sad">Sad</option>
  							 <option id = mood value="intense">Intense</option>
 							 <option id = mood value="party">Party</option>
						</select>
                        </td>
                    </tr>
                    <tr>
                        <td><button class="btn btn-primary" type="button" onclick="getMood()">Show me my Recommendations</button></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>

</body>
</html>