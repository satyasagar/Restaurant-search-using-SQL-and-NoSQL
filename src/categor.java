

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
 * Servlet implementation class categor
 */
@WebServlet("/categor")
public class categor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public categor() {
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
			String qry1= "select max(x.cats), name from "
					+ "(select (length(categories)-length(replace(categories,',','')))+1 cats, business_id id "
					+ "from business) x,business b where b.business_id=x.id "
					+ "group by business_id "
					+ "having max(x.cats)>= (select max(x.cats) from "
					+ "(select (length(categories)-length(replace(categories,',','')))+1 cats, business_id id "
					+ "from business) x, business b where b.business_id=x.id);";
			PreparedStatement ps = con.prepareStatement(qry1);
			ResultSet rs = ps.executeQuery();
			out.println("<body bgcolor=#E6E6FA>");
			out.println("<table <table border=1 class=tablesorter style=background-color:#99FFFF;border-collapse:collapse;border:1px solid #FFCC00;color:#000000;width:100% cellpadding=3 cellspacing=3>>");
			out.println("<th>"+"max_categories"+"<th>"+"names");
			while(rs.next()){				
				out.println("<tr>");
				out.println("<td>"+rs.getString(1)+"<td>"+rs.getString(2));
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
