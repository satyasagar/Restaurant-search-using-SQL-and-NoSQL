

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
 * Servlet implementation class Stinner
 */
@WebServlet("/Stinner")
public class Stinner extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stinner() {
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
		String name1 = request.getParameter("n1");
		String name2 = request.getParameter("n2");
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","NAVYA");
			
			String qry1= "select name, city, stars, review_count from business where state='"+name1+"'order by "+name2+" desc";
			PreparedStatement ps = con.prepareStatement(qry1);
			ResultSet rs = ps.executeQuery();
			out.println("<body bgcolor=#E6E6FA>");
			out.println("<table <table border=1 class=tablesorter style=background-color:#99FFFF;border-collapse:collapse;border:1px solid #FFCC00;color:#000000;width:100% cellpadding=3 cellspacing=3>>");
			out.println("<th>"+"Business_name"+"<th>"+" City"+"<th>"+" Stars"+"<th>"+" Review_Count");
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"<td>"+rs.getString(2)+"<td>"+rs.getDouble(3)+"<td>"+rs.getDouble(4));
				out.println("</tr>");
			}
			
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
