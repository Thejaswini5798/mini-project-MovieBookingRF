package com.tutorials.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Screens;
import com.tutorials.jdbc.dao.ScreensDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/ScreensUpdate")
public class ScreensUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScreensUpdateServlet() {
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
		Screens screens = new Screens();
		

		String idStr = request.getParameter("screenid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing Id value, can't update the row!");
		}
		
		int screenid = Integer.parseInt(idStr);
		String screencode = request.getParameter("screencode");
		
		String no_of_goldseatsStr = request.getParameter("noofgoldseats");
		int no_of_goldseats= no_of_goldseatsStr!=null ? Integer.parseInt(no_of_goldseatsStr) : 0;

		
		String no_of_silverseatsStr = request.getParameter("noofsilverseats");
		int no_of_silverseats= no_of_silverseatsStr!=null ? Integer.parseInt(no_of_silverseatsStr) : 0;

		String theatreidStr = request.getParameter("theatreid");
		int theatreid= theatreidStr!=null ? Integer.parseInt(theatreidStr) : 0;


		String theatrename= request.getParameter("theatrename");
		
		
		
		//2. Prepare the Person Object with the values obtained from Request
		 screens.setScreenid(screenid);
			screens.setScreencode(screencode);
			screens.setNo_of_goldseats(no_of_goldseats);
			screens.setNo_of_silverseats(no_of_silverseats);
			screens.setTheatreid(theatreid);
			screens.setTheatrename(theatrename);
		
		
		System.out.println("theatres Object prepared from the Request parameters : " + screens);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			ScreensDAO.updateScreens(screens);
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
		request.getSession().setAttribute("screens", screens);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("screensedit.jsp").forward(request, response);
		
		
	}

}
