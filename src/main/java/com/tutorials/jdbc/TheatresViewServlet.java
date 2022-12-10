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
 * Servlet implementation class TheatresViewServlet
 */
@WebServlet({ "/TheatresViewServlet", "/TheatresView" })
public class TheatresViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TheatresViewServlet() {
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
			TheatresDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		int id =1;
		String idStr = request.getParameter("id");
		
		if(null!=idStr && idStr.trim().length()>0) {
			id = Integer.parseInt(idStr);
		}
		
		System.out.println("Id Parameter from the Request : " + id);
		
		Theatres theatres = TheatresDAO.getEmployeeById(id);
		
		// 2. Store it in a way where the data is accessible in the JSP
		request.setAttribute("theatres", theatres);
		
		// 3. Forward / Delegate the control/flow the required JSP Page
		request.getRequestDispatcher("theatresview.jsp").forward(request, response);
	}

}
