<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>signup</title>
  </head>
  <body>
   <h1>Signup Page</h1>
   <center> 
	   <h2>${welcomeMessage}</h2> 
	   <form action="signup" method="post" modelAttribute="user"> 
	   	<br/>First Name:<input path="firstName" type="text" name="firstName" /> 
	   	<br/>Last Name:<input path="lastName" type="text" name="lastName" />
	   	<br/>Phone Number:<input path="phoneNumber" type="number" name="phoneNumber" />
	   	<br/>EmailId:<input path="emailId" type="email" name="emailId" />
	   	<br/>Password:<input path="password" type="password" name="password" />
	   	<br/><input type="submit" value="Submit" /> 
	   </form>
   </center> 
  </body> 
 </html>

