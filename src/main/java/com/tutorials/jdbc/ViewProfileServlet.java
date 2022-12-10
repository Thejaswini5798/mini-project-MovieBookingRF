package com.tutorials.jdbc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tutorials.jdbc.bo.Users;
import com.tutorials.jdbc.dao.UsersDAO;
/**
 * Servlet implementation class ViewServlet
 */
@WebServlet({ "/ViewProfileServlet", "/ViewProfile" })
public class ViewProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfileServlet() {
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
			UsersDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		String email = request.getParameter("email");
	
		
		
		
		System.out.println("Id Parameter from the Request : " +email );
		Users users = null;
		try {
			users = UsersDAO.getUsersByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("users", users);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("profile.jsp").forward(request, response);
	}

}
