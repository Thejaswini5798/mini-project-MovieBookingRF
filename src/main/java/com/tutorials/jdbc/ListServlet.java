package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Users;
import com.tutorials.jdbc.dao.UsersDAO;
/**
 * Servlet implementation class ListServlet
 */
@WebServlet(
		description = "A Servlet to list the data from the database", 
		urlPatterns = { 
				"/ListServlet",
				"/List"
		})
public class ListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException
	{
		System.out.println("ListServlet - doGet() invoked");
		
		// 1. Get the data from Database 
		try {
			UsersDAO.connectToDB();
			System.out.println("DB Connection has been obtained");
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			throw new ServletException(e.getMessage());
		}
		
		List<Users> usersList = UsersDAO.listAll();
		
		System.out.println("usersList from DAO : " + usersList);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("usersList", usersList);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("list.jsp").forward(request, response);
	}
}