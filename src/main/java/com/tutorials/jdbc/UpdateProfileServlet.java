package com.tutorials.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Users;
import com.tutorials.jdbc.dao.UsersDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateProfile")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfileServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("UpdateProfileServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("UpdateServlet invoked!");*/
		
		//1. Collect all the Input data and prepare a Person object
		Users users = new Users();
		
		String idStr = request.getParameter("id");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing Id value, can't update the row!");
		}
		
		int id = Integer.parseInt(idStr);
		
		String name = request.getParameter("name");
		
		String ageStr = request.getParameter("age");
		int age = ageStr!=null ? Integer.parseInt(ageStr) : 0;
		String phone = request.getParameter("phone");
		
		String city = request.getParameter("city");
		String pincode = request.getParameter("pincode");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		//2. Prepare the Person Object with the values obtained from Request
		users.setId(id);
		users.setName(name);
		users.setAge(age);
		//validate that the gender is not null or empty, 
		//otherwise it will throw a NullPointerException
		users.setPhone(phone);
		
		users.setCity(city);
		users.setPincode(pincode);
		users.setEmail(email);
		users.setPassword(password);
		System.out.println("users Object prepared from the Request parameters : " + users);
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			UsersDAO.updateUsers(users);
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
		request.getSession().setAttribute("users", users);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("editprofile.jsp").forward(request, response);
		
		
	}

}
