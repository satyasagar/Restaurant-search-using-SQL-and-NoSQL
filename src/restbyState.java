
//import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class restbyState
 */
@WebServlet("/restbyState")
public class restbyState extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public restbyState() {
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
			String qry1= "select distinct state from business order by state;";
			PreparedStatement ps = con.prepareStatement(qry1);
			ResultSet rs = ps.executeQuery();
			out.println("<body bgcolor=#E6E6FA>");
			out.println("<form action=Stinner>");
			out.println("Choose State: <Select name = n1>");
			while(rs.next()){
				out.println("<option>"+rs.getString(1)+"</option>");
				}
			out.println("</select>");
			out.println("<br> Sort by: <Select name = n2>");
			out.println("<option>"+"stars"+"</option>");
			out.println("<option>"+"review_count"+"</option>");
			out.println("<input type = submit value = OK");
			out.println("</form>");
			
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
