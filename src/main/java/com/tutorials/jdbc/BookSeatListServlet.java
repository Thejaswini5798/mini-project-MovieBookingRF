package com.tutorials.jdbc;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.BookSeats;
import com.tutorials.jdbc.dao.BookSeatsDAO;
/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/BookSeatListServlet", "/BookSeatList" })
public class BookSeatListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSeatListServlet() {
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
			BookSeatsDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		String movie_name = request.getParameter("moviename");
	
		
		
		
		System.out.println("Id Parameter from the Request : " + movie_name );
		List<BookSeats> bookseatsList  = null;
		try {
			bookseatsList = BookSeatsDAO.getSeatByMoviename(movie_name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("bookseatsList",bookseatsList);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("booktickets.jsp").forward(request, response);
	}

}
