

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userblike
 */
@WebServlet("/userblike")
public class userblike extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userblike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","NAVYA");
			String qry1= "select distinct x.user_id,x.name,y.business_id,y.name "
					+ "from user x,business y,tip z,(select t.business_id as id, sum(t.likes) as Max_sum "
					+ "from tip t group by t.business_id "
					+ "having sum(t.likes) >= all (select sum(t1.likes) from tip t1 "
					+ "group by t1.business_id)) a "
					+ "where z.business_id = a.id and y.business_id = z.business_id and x.user_id = z.user_id;";
			PreparedStatement ps = con.prepareStatement(qry1);
			ResultSet rs = ps.executeQuery();
			out.println("<body bgcolor=#E6E6FA>");
			out.println("<table <table border=1 class=tablesorter style=background-color:#99FFFF;border-collapse:collapse;border:1px solid #FFCC00;color:#000000;width:100% cellpadding=3 cellspacing=3>>");
			out.println("<th>"+"User_ID"+"<th>"+"Username"+"<th>"+"Business_ID"+"<th>"+"Business_name");
			while(rs.next()){				
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"<td>"+rs.getString(2)+"<td>"+rs.getString(3)+"<td>"+rs.getString(4));
				out.println("</tr>");
			}
			out.println("</table>");
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
