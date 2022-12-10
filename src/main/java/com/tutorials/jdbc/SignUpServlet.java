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
 * Servlet implementation class CreateServlet
 */
@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
			UsersDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		// 2. Collect all the Input data and prepare a Person object
		Users users = new Users();
		
String name = request.getParameter("name");
		
		String ageStr = request.getParameter("age");
		int age = ageStr!=null ? Integer.parseInt(ageStr) : 0;
		

		String phone = request.getParameter("phone");
		
		
		String city = request.getParameter("city");
		String pincode = request.getParameter("pincode");
		
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		users.setName(name);
		users.setAge(age);
		//validate that the gender is not null or empty, 
		//otherwise it will throw a NullPointerException
		users.setPhone(phone);
		users.setCity(city);
		users.setPincode(pincode);
		users.setEmail(email);
		users.setPassword(password);
		
		
		
		System.out.println("Users Object prepared from the Request parameters : " + users);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreatePerson() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = UsersDAO.createPersonFlavor1ThrowsException(users);
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
	
		request.setAttribute("usersForm", users);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId > 0) {
			users = UsersDAO.getEmployeeById(lastInsertedId);
			request.setAttribute("users", users);
			url = "login.jsp";
		}else {
			List<Users> usersList = UsersDAO.listAll();
			request.setAttribute("usersList", usersList);
			//url = "list.jsp";
			url = "signup.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
