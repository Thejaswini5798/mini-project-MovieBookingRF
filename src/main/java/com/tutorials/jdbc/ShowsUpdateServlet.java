package com.tutorials.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Shows;
import com.tutorials.jdbc.dao.ShowsDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/ShowsUpdate")
public class ShowsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowsUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("UpdateServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("UpdateServlet invoked!");*/
		
		//1. Collect all the Input data and prepare a Person object
		Shows shows = new Shows();
		

		String idStr = request.getParameter("showsid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing Id value, can't update the row!");
		}
		
		int showsid = Integer.parseInt(idStr);
		String showscode = request.getParameter("showscode");
		
		String showtime = request.getParameter("showtime");
		String showdate = request.getParameter("showdate");
		
		

		String screenidStr = request.getParameter("screenid");
		int screenid = screenidStr!=null ? Integer.parseInt(screenidStr) : 0;
		
		String movie_idStr = request.getParameter("movieid");
		int movie_id = movie_idStr!=null ? Integer.parseInt(movie_idStr) : 0;
		

		String theatrename = request.getParameter("theatrename");
		String movie_name = request.getParameter("moviename");
		

		String seats_remainingStr = request.getParameter("seatsremaining");
		int seats_remaining = seats_remainingStr!=null ? Integer.parseInt(seats_remainingStr) : 0;
		
		
		
		//2. Prepare the Person Object with the values obtained from Request
		shows.setShowsid(showsid);
		shows.setShowscode(showscode);
		 
		shows.setShowtime(showtime);
		shows.setShowdate(showdate);
		
		
		shows.setScreenid(screenid);
		shows.setMovie_id(movie_id);
		shows.setTheatrename(theatrename);
		shows.setMovie_name(movie_name);
		shows.setSeats_remaining(seats_remaining);
		
		System.out.println("theatres Object prepared from the Request parameters : " + shows);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			ShowsDAO.updateScreens(shows);
		} catch (Exception exception) {
			exceptionMsg = exception.getMessage();
			System.err.println("Exception occurred while updating the data into the Database Table");
			System.err.println("Message : " + exceptionMsg);
			//TODO Remove later as it is not a good practice
			exception.printStackTrace();
		}
		
		//4. Prepare the response message  
		if(null!=exceptionMsg) {
			request.setAttribute("exceptionMsg", exceptionMsg);
		} else {
			request.setAttribute("message", "Record updated successfully!");
		}
		request.getSession().setAttribute("shows", shows);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("showsedit.jsp").forward(request, response);
		
		
	}

}
