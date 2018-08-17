<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<title>Login</title>
<link rel="stylesheet" href="/RESTfulExample/resources/css/music.css" type="text/css" media="all" />
<script type="text/javascript">
function login(){
	var username = $('#username').val();
	var password = $('#password').val();
	var location = $('#location').val();
	var mood = $('#mood').val();
	var weather = $('#weather').val();
	   
	$.ajax({
		url : "musicrecommender/user/login",
	    type: "POST",
	    data : "username=" + username + "&password=" + password,
	   
	    success:function(data, textStatus, jqXHR){
	    	alert(data);
	    	console.log(data);
	    	
	    	var split1= data.split(";");
	    	alert(split1[2]);
	    	
	    	var check= split1[2].split(":");
	    	var check1=split1[3].split(":");
	    	
	    	alert("mood is ::"+check);
	    	alert("weather is ::"+ check1)
	    	
	    	var mood= check[1];
	    	var weather=check1[1];
	    	alert(mood);
	    	alert(weather);
	    	
	    	window.location.href="home.jsp?user="+username+"&weather="+weather+"&mood="+mood;
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
            <table border="1" width="30%" cellpadding="3">
                <thead>
                    <tr>
                        <th colspan="2"><h1>Login Here</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>User Name</td>
                        <td><input type= "text" name="username" id="username" placeholder="please enter your email ID"/></td>
                    </tr>
                    <tr>
                        <td><b>Password</td>
                        <td><input type="text" name="password" id="password" placeholder="password"/></td>
                    </tr>
                    <tr>
                    <tr>
                        <td><button type="submit" id="login" onclick="login()">Login</button></td>
                        <td><button class="btn btn-large btn-success margin-top-80" type="button" onclick="window.location.href='signup.jsp'">Sign up</button></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>
</body>
</html>