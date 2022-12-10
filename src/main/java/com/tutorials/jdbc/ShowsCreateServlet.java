package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.sql.Time;
import com.tutorials.jdbc.bo.Shows;
import com.tutorials.jdbc.dao.ShowsDAO;
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/ShowsCreate")
public class ShowsCreateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowsCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("CreateServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("Create Servlet invoked!");*/
		
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
		
		// 2. Collect all the Input data and prepare a Person object
		Shows shows = new Shows();
		
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
		shows.setShowscode(showscode);
		 
		shows.setShowtime(showtime);
		shows.setShowdate(showdate);
		
		
		shows.setScreenid(screenid);
		shows.setMovie_id(movie_id);
		shows.setTheatrename(theatrename);
		shows.setMovie_name(movie_name);
		shows.setSeats_remaining(seats_remaining);
		

		
		
		System.out.println("Users Object prepared from the Request parameters : " + shows);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = ShowsDAO.createPersonFlavor1ThrowsException(shows);
		}catch(Exception exception) {
			exceptionMsg = exception.getMessage();
			System.err.println("Exception occurred while inserting the data into the Database Table");
			System.err.println("Message : " + exceptionMsg);
		}
		
		// 4. Prepare the message to be shown to the User
		String message = null;
		String flag = null;
		
		if(lastInsertedId > 0) {
			//message = "Record inserted successfully";
			message = "Record inserted successfully, with the Id : " + lastInsertedId;
			flag = "success";
		} else {
			message = "Records was NOT inserted!";
			request.setAttribute("exceptionMsg", exceptionMsg);
			flag = "failure";
		}
		
		// 5. Store it in a way where the data is accessible in the JSP
		request.setAttribute("message", message);
		request.setAttribute("flag", flag);
	
		request.setAttribute("ShowsForm", shows);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			shows = ShowsDAO.getEmployeeById(lastInsertedId);
			request.setAttribute("shows", shows);
			url = "showsview.jsp";
		}else {
			List<Shows> showsList = ShowsDAO.listAll();
			request.setAttribute("showsList", showsList);
			//url = "list.jsp";
			url = "showscreate.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
