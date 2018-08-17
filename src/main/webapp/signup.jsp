<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<link rel="stylesheet" href="resources/css/music.css" type="text/css" media="all" />

<title>Sign Up</title>

<script type="text/javascript">
function createUser(){
	//alert("hiee");
	var username = $('#username').val();
	var password = $('#password').val();
	var location = $('#location').val();

	$.ajax({
		url : "musicrecommender/user/signup",
	    type: "POST",
	    data : "username=" + username + "&password=" + password + "&location=" + location,
	   
	    success:function(data, textStatus, jqXHR){
	    	alert('success'+ data);
	    	window.location.href="login.jsp?user="+username;
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
                        <th colspan="2"><h1>Signup Here</h1></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><b>User Name</td>
                        <td><input type="text" id="username" class="required" placeholder="User Name"></td>
                    </tr>
                    <tr>
                        <td><b>Password</td>
                        <td><input type="password" id="password" name="passwordInput" class="required" placeholder="Password"></td>
                    </tr>
                    <tr>
                    <tr>
                    	<td><b>Location</td>
                        <td><input type="text" id="location" name="location" placeholder="location"></td>
                        </tr>
                    <tr>
                        <td><button id="signup" class="btn btn-primary" type="button" onclick="createUser()">Register</button></td>
                    </tr>
                    
                </tbody>
            </table>
            </center>
</body>
</html>