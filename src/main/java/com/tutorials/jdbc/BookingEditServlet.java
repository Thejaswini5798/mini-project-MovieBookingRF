package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Booking;
import com.tutorials.jdbc.dao.BookingDAO;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/BookingEdit" })
public class BookingEditServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingEditServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("ScreensEditServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			BookingDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
	/*	int id =1;
		String idStr = request.getParameter("id");
		
		if(null!=idStr && idStr.trim().length()>0) {
			id = Integer.parseInt(idStr);
		}
		
		System.out.println("Id Parameter from the Request : " + id);
		
		Users users = UsersDAO.getEmployeeById(id);*/
		
		// 2. Store it in a way where the data is accessible in the JSP
		int id =1;
		String idStr = request.getParameter("id");
		
		if(null!=idStr && idStr.trim().length()>0) {
			id = Integer.parseInt(idStr);
		}
		
		System.out.println("Id Parameter from the Request : " + id);
		
		Booking booking = BookingDAO.getEmployeeById(id);
		request.setAttribute("booking", booking);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("bookingedit.jsp").forward(request, response);
	}

}

