<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <html>
  <head>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>createChitGroup</title>
  </head>
  <body>
   <h1>Create Chit Group Form</h1>
   <center>  
	   <form action="createGroup" method="post" modelAttribute="chitGroup"> 
	   	<br/>ChitGroup Name:<input path="chitGroupName" type="text" name="chitGroupName" /> 
	   	<br/>Chit Value:<input path="chitValue" type="number" name="chitValue" />
	   	<br/>Commission Percent:<input path="commissionPercent" type="number" name="commissionPercent" />
	   	<br/>Min % Bidding:<input path="minPercentBidding" type="number" name="minPercentBidding" />
	   	<br/>Max % Bidding:<input path="maxPercentBidding" type="number" name="maxPercentBidding" />
	   	<br/>Date of Bidding:<input path="dateofBidding" type="date" name="dateofBidding" />
	   	<br/>Start Date:<input path="startDate" type="date" name="startDate" />
	   	<br/>Email Id List:<input type="text" name="csvEmailIdList" />
	   	<br/><input type="submit" value="Submit" /> 
	   </form>
   </center> 
  </body> 
 </html>
