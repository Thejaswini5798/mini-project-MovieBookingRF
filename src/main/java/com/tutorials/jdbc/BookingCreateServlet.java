package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Booking;
import com.tutorials.jdbc.dao.BookingDAO;
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/BookingCreate")
public class BookingCreateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingCreateServlet() {
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
			BookingDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		// 2. Collect all the Input data and prepare a Person object
		Booking booking = new Booking();
		
		String booking_code = request.getParameter("bookingcode");
		
		String no_of_ticketsStr = request.getParameter("nooftickets");
		int no_of_tickets= no_of_ticketsStr!=null ? Integer.parseInt(no_of_ticketsStr) : 0;
		
		String total_costStr = request.getParameter("totalcost");
		int total_cost= total_costStr!=null ? Integer.parseInt(total_costStr) : 0;

		String card_number= request.getParameter("cardnumber");
		String name_oncard= request.getParameter("nameoncard");
		
		String idStr = request.getParameter("id");
		int id= idStr!=null ? Integer.parseInt(idStr) : 0;
        
		String showsidStr = request.getParameter("showsid");
		int showsid= showsidStr!=null ? Integer.parseInt(showsidStr) : 0;
		
		
		
		
		
		
		
		
		
		    booking.setBooking_code(booking_code);
		    booking.setNo_of_tickets(no_of_tickets);
		    booking.setTotal_cost(total_cost);
		    booking.setCard_number(card_number);
		    booking.setName_oncard(name_oncard);
		    booking.setId(id);
		    booking.setShowsid(showsid);
		
		System.out.println("Users Object prepared from the Request parameters : " + booking);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = BookingDAO.createPersonFlavor1ThrowsException(booking);
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
	
		request.setAttribute("BookingForm", booking);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			booking = BookingDAO.getEmployeeById(lastInsertedId);
			request.setAttribute("booking", booking);
			url = "bookingview.jsp";
		}else {
			List<Booking> bookingList = BookingDAO.listAll();
			request.setAttribute("bookingList", bookingList);
			//url = "list.jsp";
			url = "bookingcreate.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
