

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
 * Servlet implementation class restbyBusiness
 */
@WebServlet("/restbyBusiness")
public class restbyBusiness extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public restbyBusiness() {
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
			String qb2="select distinct state from business order by state;";
			PreparedStatement pss = con.prepareStatement(qb2);
			ResultSet rs1= pss.executeQuery();
			out.println("<body bgcolor=#E6E6FA>");
			out.println("<form action=BuInner>");
			out.println("Business Name: <input type = text name = bn>");
			out.println("<br> Choose State: <Select name = bs>");
			while(rs1.next()){
				out.println("<option>"+rs1.getString(1)+"</option>");
				}
			out.println("</select>");
			out.println("<input type = submit value = OK");
			
			
			out.println("</form>");
			out.println("<br> <a href='maxLikes'>Businesses with maximum likes </a>>");
			out.println("<br> <a href='categor'>Businesses with highest number of categories </a>>");

						
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
