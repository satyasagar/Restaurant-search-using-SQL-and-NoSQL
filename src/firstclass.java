

import java.io.IOException;

//import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class firstclass
 */
@WebServlet("/firstclass")
public class firstclass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public firstclass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
	//	PrintWriter out=response.getWriter();
		String s1=request.getParameter("fc");
		if(s1.equals("State"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("restbyState");
			rd.include(request,response);
		}
		
		else if(s1.equals("Business"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("restbyBusiness");
			rd.include(request,response);
		}
		else if(s1.equals("User"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("rentbyUser.jsp");
			rd.include(request,response);
		}
		else if(s1.equals("Review"))
		{
			RequestDispatcher rd=request.getRequestDispatcher("restbyReview.jsp");
			rd.include(request,response);
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
