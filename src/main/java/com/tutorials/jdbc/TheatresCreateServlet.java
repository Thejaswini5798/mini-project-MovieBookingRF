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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/TheatresCreate")
public class TheatresCreateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatresCreateServlet() {
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
			TheatresDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		// 2. Collect all the Input data and prepare a Person object
		Theatres theatres = new Theatres();
		
		String theatre_code = request.getParameter("theatrecode");
		String theatrename= request.getParameter("theatrename");
		
		String no_of_screensStr = request.getParameter("noofscreens");
		int no_of_screens= no_of_screensStr!=null ? Integer.parseInt(no_of_screensStr) : 0;

		

		String area = request.getParameter("area");
		
		theatres.setTheatre_code(theatre_code);
		theatres.setTheatrename(theatrename);
		theatres.setNo_of_screens(no_of_screens);
		
		theatres.setArea(area);
		
		
		
		System.out.println("Users Object prepared from the Request parameters : " + theatres);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = TheatresDAO.createPersonFlavor1ThrowsException(theatres);
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
	
		request.setAttribute("theatresForm", theatres);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			theatres = TheatresDAO.getEmployeeById(lastInsertedId);
			request.setAttribute("theatres", theatres);
			url = "theatresview.jsp";
		}else {
			List<Theatres> theatresList = TheatresDAO.listAll();
			request.setAttribute("theatresList", theatresList);
			//url = "list.jsp";
			url = "theatrescreate.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
