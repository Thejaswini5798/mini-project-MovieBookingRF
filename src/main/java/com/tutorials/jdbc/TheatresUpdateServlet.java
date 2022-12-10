package com.tutorials.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Theatres;
import com.tutorials.jdbc.dao.TheatresDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/TheatresUpdate")
public class TheatresUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatresUpdateServlet() {
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
		Theatres theatres = new Theatres();
		

		String idStr = request.getParameter("theatreid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing Id value, can't update the row!");
		}
		
		int theatreid = Integer.parseInt(idStr);
		
		
		String theatre_code = request.getParameter("theatrecode");
		String theatrename = request.getParameter("theatrename");
		String no_of_screensStr = request.getParameter("noofscreens");
		int no_of_screens  = no_of_screensStr!=null ? Integer.parseInt(no_of_screensStr) : 0;
		String area = request.getParameter("area");
		
		
		
		//2. Prepare the Person Object with the values obtained from Request
		
		theatres.setTheatreid(theatreid);
		theatres.setTheatre_code(theatre_code);
		theatres.setTheatrename(theatrename);
		theatres.setNo_of_screens(no_of_screens);
		theatres.setArea(area);
		
		System.out.println("theatres Object prepared from the Request parameters : " + theatres);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			TheatresDAO.updateTheatres(theatres);
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
		request.getSession().setAttribute("theatres", theatres);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("theatresedit.jsp").forward(request, response);
		
		
	}

}
