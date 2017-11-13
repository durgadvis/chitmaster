<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login</title>
  </head>
  <body>
   <h1>Login Page</h1>
   <center> 
	   <h2>${welcomeMessage}</h2> 
	   <form action='login' method="post"> 
	   	<br/>Username:<input type="text" name="userId"> 
	   	<br/>Password:<input type="password" name="password">
	   	<br/>EmailId:<input type="email" name="emailId">
	   	<br/><input type="submit" value="Submit"> 
	   </form>
   </center> 
  </body> 
 </html>

