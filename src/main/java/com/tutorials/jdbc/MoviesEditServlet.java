package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Movies;
import com.tutorials.jdbc.dao.MoviesDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/Moviesedit" })
public class MoviesEditServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("EditServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			MoviesDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		int id =1;
		
		System.out.println("Id Parameter from the Request : " + id);
		
		Movies movies = MoviesDAO.getEmployeeById(id);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("movies", movies);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("moviesedit.jsp").forward(request, response);
	}

}
