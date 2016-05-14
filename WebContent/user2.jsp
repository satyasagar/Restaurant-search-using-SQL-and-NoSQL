<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@page import="java.sql.*"%>

  
<%!
  Connection con1=null;
  Statement st1=null;
  ResultSet rs=null;
  %>
<%  String namesearch=request.getParameter("nameSearch");%>
<%    
try
{
	Class.forName("org.gjt.mm.mysql.Driver");
	con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","NAVYA");
	st1=con1.createStatement();
	String query1="select user_id, name, review_count, fans, votesuseful, yelping_since, average_stars from user where name like '%"+namesearch+"%'";
	//System.out.println("Name is: "+namesearch);
	//System.out.println(query1);
	rs=st1.executeQuery(query1);
	System.out.println(".....................Query Executed Sucessfully in display.jsp...........");
}
catch(Exception e)
{
System.out.println("from catch block");
System.out.println(e);
}
%>
<table border="1" class="tablesorter" style="background-color:#99FFFF;border-collapse:collapse;border:1px solid #FFCC00;color:#000000;width:100%" cellpadding="3" cellspacing="3">
	<thead>
	<tr>
		<th>User Name</th>
		<th>Review Count</th>
		<th>Fans</th>
		<th>Useful Votes</th>
		<th>Yelping Since</th>
		<th>Average Stars</th>
	</tr>
	</thead>
    <%
	while(rs.next())
	{
	System.out.println("Testing: "+rs.getString("name")+rs.getString("user_id"));
	out.println("<tbody>");
	out.println("<tr>\n");
	out.println("<td width=100>"+rs.getString("name")+"</td>\n");
	//System.out.println("<a href=review.jsp?Id="+rs.getString("user_id")+"</a>");
	out.println("<td width=100><a href='reviewuser.jsp?Id="+rs.getString("user_id")+"'>"+rs.getString("review_count")+"</a></td>\n");
	//out.println("<td width=100><a href=review.jsp?Id="+rs.getString("user_id")+">"+rs.getString("review_count")+"</a></td>\n");
	out.println("<td width=100>"+rs.getString("fans") +"</td>\n");
	out.println("<td width=100>"+rs.getString("votesuseful") +"</td>\n");
	out.println("<td width=100>"+rs.getString("yelping_since") +"</td>\n");
	out.println("<td width=100>"+rs.getString("average_stars") +"</td>\n");
	out.println("</tbody>");
	}	
	%>
</table>
</body>
</html>