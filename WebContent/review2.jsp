<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="com.mongodb.*"
     import="java.net.UnknownHostException"
%> 
<%@page import="java.sql.*"%>
<%!
  Connection con1=null;
  Statement st1=null;
  Statement st2=null;
  ResultSet rs_user=null;
  ResultSet rs_bus=null;
  %>
<%  String searchText=request.getParameter("textSearch");%>
<% 
Class.forName("org.gjt.mm.mysql.Driver");
con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","NAVYA");
st1=con1.createStatement();
st2=con1.createStatement();
Mongo m=new MongoClient("localhost",27017);
DB db = m.getDB("navya");
DBCollection collection=db.getCollection("Reviews");
DBObject searchCmd = new BasicDBObject("$text", new BasicDBObject("$search",searchText));
DBCursor cursor = collection.find(searchCmd).limit(10);

%>
<table border="1" class="tablesorter" style="background-color:#99FFFF;border-collapse:collapse;border:1px solid #FFCC00;color:#000000;width:100%" cellpadding="3" cellspacing="3">
<tr>
	<th>User Name</th>
	<th>Business Name</th>
	<th>Stars</th>
	<th>Date</th>
	<th>Review</th>
</tr>
<%
while(cursor.hasNext())
{
	try{
	DBObject dbo=cursor.next();
	String uID=dbo.get("user_id").toString();	
	String bID=dbo.get("business_id").toString();
	String qry_user="select name from user where user_id='"+uID+"'";
	String qry_bus="select name from business where business_id='"+bID+"'";
	rs_user=st1.executeQuery(qry_user);
	rs_bus=st2.executeQuery(qry_bus);
	out.println("<tr>");
	rs_user.next();
	rs_bus.next();
	out.println("<td>"+rs_user.getString(1)+"</td>");
	out.println("<td>"+rs_bus.getString(1)+"</td>");
	out.println("<td>"+dbo.get("stars")+"</td>");
	out.println("<td>"+dbo.get("date")+"</td>");
	out.println("<td>"+dbo.get("text")+"</td>");
	out.println("</tr>");
	}
	catch(Exception e){
		  System.out.println("from catch block");
		  System.out.println(e);
	}
}

%>
</table>
</body>
</html>