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
@WebServlet({ "/MoviesViewServlet", "/MoviesView" })
public class MoviesViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesViewServlet() {
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
			MoviesDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		int movie_id =1;
		String idStr = request.getParameter("movieid");
		
		if(null!=idStr && idStr.trim().length()>0) {
			movie_id = Integer.parseInt(idStr);
		}
		
		
		
		
		System.out.println("Id Parameter from the Request : " + movie_id);
		
		Movies movies = MoviesDAO.getEmployeeById(movie_id);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("movies", movies);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("moviesview.jsp").forward(request, response);
	}

	}
