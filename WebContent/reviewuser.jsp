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
  ResultSet rs_bus=null;
  %>
<% 
Mongo m=new MongoClient("localhost",27017);
DB db = m.getDB("navya");
String uID=request.getParameter("Id");
System.out.println("UID:"+uID);
DBCollection collection=db.getCollection("Reviews");
DBObject searchCmd = new BasicDBObject();
searchCmd.put("user_id",uID);
DBObject proj = new BasicDBObject();
proj.put("date",1);
proj.put("text",1);
proj.put("business_id",1);
proj.put("_id",0);
System.out.println("1"+searchCmd);
DBCursor cursor = collection.find(searchCmd,proj);%>
<table border="1" class="tablesorter" style="background-color:#99FFFF;border-collapse:collapse;border:1px solid #FFCC00;color:#000000;width:100%" cellpadding="3" cellspacing="3">
<tr>
	<th>Business Name</th>
	<th>Date</th>
	<th>Review</th>
</tr>
<%
Class.forName("org.gjt.mm.mysql.Driver");
Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","NAVYA");
st1=con1.createStatement();
while(cursor.hasNext())
{
	try{
		System.out.println("inside");
	DBObject dbo=cursor.next();	
	String bID=dbo.get("business_id").toString();
	System.out.println(bID);
	String qry_bus="select name from business where business_id='"+bID+"'";
	System.out.println(qry_bus);
	rs_bus=st1.executeQuery(qry_bus);
	rs_bus.next();
	out.println("<tr>");
	out.println("<td>"+rs_bus.getString(1)+"</td>");
	out.println("<td>"+dbo.get("date")+"</td>");
	out.println("<td>"+dbo.get("text")+"</td>");
	out.println("</tr>");
	}
	catch(Exception e)
	{
		System.out.println("from catch"+e);
	}
}

%>
</table>
</body>
</html>