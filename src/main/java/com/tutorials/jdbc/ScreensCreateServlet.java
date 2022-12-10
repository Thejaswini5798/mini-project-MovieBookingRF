package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Screens;
import com.tutorials.jdbc.dao.ScreensDAO;
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/ScreensCreate")
public class ScreensCreateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScreensCreateServlet() {
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
			ScreensDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		// 2. Collect all the Input data and prepare a Person object
		Screens screens = new Screens();
		
		String screencode = request.getParameter("screencode");
		
		String no_of_goldseatsStr = request.getParameter("noofgoldseats");
		int no_of_goldseats= no_of_goldseatsStr!=null ? Integer.parseInt(no_of_goldseatsStr) : 0;
		String no_of_silverseatsStr = request.getParameter("noofsilverseats");
		int no_of_silverseats= no_of_silverseatsStr!=null ? Integer.parseInt(no_of_silverseatsStr) : 0;

		String theatreidStr = request.getParameter("theatreid");
		int theatreid= theatreidStr!=null ? Integer.parseInt(theatreidStr) : 0;
        String theatrename= request.getParameter("theatrename");
		
		screens.setScreencode(screencode);
		screens.setNo_of_goldseats(no_of_goldseats);
		screens.setNo_of_silverseats(no_of_silverseats);
		screens.setTheatreid(theatreid);
		screens.setTheatrename(theatrename);
		
		
		
		System.out.println("Users Object prepared from the Request parameters : " + screens);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = ScreensDAO.createPersonFlavor1ThrowsException(screens);
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
	
		request.setAttribute("ScreensForm", screens);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			screens = ScreensDAO.getEmployeeById(lastInsertedId);
			request.setAttribute("screens", screens);
			url = "screensview.jsp";
		}else {
			List<Screens> screensList = ScreensDAO.listAll();
			request.setAttribute("screensList", screensList);
			//url = "list.jsp";
			url = "screenscreate.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
