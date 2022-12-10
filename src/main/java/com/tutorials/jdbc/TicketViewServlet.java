package com.tutorials.jdbc;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Shows;
import com.tutorials.jdbc.dao.ShowsDAO;
/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/TicketViewServlet", "/TicketView" })
public class TicketViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TicketViewServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		// 1. Get the data from Database 
		try {
			ShowsDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		String movie_name = request.getParameter("moviename");
	
		
		
		
		System.out.println("Id Parameter from the Request : " + movie_name );
		List<Shows> showsList  = null;
		try {
			showsList = ShowsDAO.getTicketByMoviename(movie_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("showsList", showsList);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("booktickets.jsp").forward(request, response);
	}

}
