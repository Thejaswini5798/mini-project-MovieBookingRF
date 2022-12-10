package com.tutorials.jdbc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tutorials.jdbc.bo.Users;
import com.tutorials.jdbc.dao.UsersDAO;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "A Servlet to handle the Login Action", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("LoginServlet invoked!");
		
		//PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		//out.println("LoginServlet invoked! <br/>");
		
		//1. Get the Username
		String email = request.getParameter("email");
		
		//2. Get the password
		String password = request.getParameter("password");
		
		String url = null;
		
		
		//3. Authenticate / validate 
		// Typically it goes to the DB and verifies
		// For the sake of simplicity, we verify the contents matching with one another for now.
	
		
				try {
					UsersDAO.connectToDB();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			
				
					Users users = null;
					try {
						users = UsersDAO.getUsersByEmail(email);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					System.out.println(users);
					
					if(null!=users)
					{
					if(email.equals(users.getEmail())&& password.equals(users.getPassword()))
					{
				System.out.println("[INFO] Credentials matched!");
				//out.println("Success!");
				url = "/welcome.jsp";
				request.setAttribute("message", "Welcome "+users.getName());
				request.getSession().setAttribute("name", users.getName());
				request.getSession().setAttribute("users", users);
				
				
					}
					else {
				System.out.println("[ERR] Credentials Mismatch!");
				//out.println("Failure");
				request.setAttribute("errorMessage", "Invalid Credentials. Try again! or SignUp");
				url = "/login.jsp";
}
			
					}
					else {
						System.out.println("[ERR] Credentials Mismatch!");
						//out.println("Failure");
						request.setAttribute("errorMessage", "Invalid Credentials. Try again! or SignUp");
						url = "/login.jsp";
		}
					
		this.getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	}

