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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/BookingUpdate")
public class BookingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingUpdateServlet() {
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
		Booking booking = new Booking();
		

		String idStr = request.getParameter("bookingid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing Id value, can't update the row!");
		}
		
		int bookingid = Integer.parseInt(idStr);
		
		
		String booking_code = request.getParameter("bookingcode");

		String no_of_ticketsStr = request.getParameter("nooftickets");
		int no_of_tickets= no_of_ticketsStr!=null ? Integer.parseInt(no_of_ticketsStr) : 0;

		String total_costStr = request.getParameter("totalcost");
		int total_cost= total_costStr!=null ? Integer.parseInt(total_costStr) : 0;

		String card_number= request.getParameter("cardnumber");
		String name_oncard= request.getParameter("nameoncard");


		String uidStr= request.getParameter("id");
		int id=uidStr!=null ? Integer.parseInt(uidStr) : 0;

		String showsidStr = request.getParameter("showsid");
		int showsid= showsidStr!=null ? Integer.parseInt(showsidStr) : 0;

		
		//2. Prepare the Person Object with the values obtained from Request
           booking.setBookingid(bookingid);
		    booking.setBooking_code(booking_code);
		    booking.setNo_of_tickets(no_of_tickets);
		    booking.setTotal_cost(total_cost);
		    booking.setCard_number(card_number);
		    booking.setName_oncard(name_oncard);
		    booking.setId(id);
		    booking.setShowsid(showsid);
		
		
		System.out.println("theatres Object prepared from the Request parameters : " + booking);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			BookingDAO.updateBooking(booking);
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
		request.getSession().setAttribute("booking", booking);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("bookingedit.jsp").forward(request, response);
		
		
	}

}
