package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Theatres;
import com.tutorials.jdbc.dao.TheatresDAO;
/**
 * Servlet implementation class 	m	ovieListServlet
 */
@WebServlet(
		description = "A Servlet to list the data from the database", 
		urlPatterns = { 
				"/TheatresListServlet",
				"/TheatresList"
		})
public class TheatresListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatresListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("MoviesListServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			TheatresDAO.connectToDB();
			System.out.println("DB Connection has been obtained");
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			throw new ServletException(e.getMessage());
		}
		
		List<Theatres> theatresList =TheatresDAO.listAll();
		
		System.out.println("theatresList from DAO : " + theatresList);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("theatresList", theatresList);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("theatreslist.jsp").forward(request, response);
	}
}
