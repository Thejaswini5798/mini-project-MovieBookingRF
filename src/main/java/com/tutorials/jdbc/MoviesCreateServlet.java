
package com.tutorials.jdbc;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Movies;
import com.tutorials.jdbc.dao.MoviesDAO;
/**
 * Servlet implementation class CreateServlet
 */
@WebServlet("/MoviesCreate")
public class MoviesCreateServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesCreateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		System.out.println("MoviescreateServlet - doPost() called");
		
		/*response.setContentType("text/html");
		response.getWriter().write("Create Servlet invoked!");*/
		
		// 1. Get the data from Database 
		try {
			MoviesDAO.connectToDB();
		} catch (Exception e) {
			System.err.println("Exception occurred while connecting to the Database");
			System.err.println("Error Message : " + e.getMessage());
			//TODO: Remove this later, as this is not a good practice
			e.printStackTrace();
			
			//throw new ServletException(e.getMessage());
		}
		
		// 2. Collect all the Input data and prepare a Person object
		Movies movies = new Movies();
		
		String movie_code = request.getParameter("moviecode");
		String movie_name = request.getParameter("moviename");
		String genre = request.getParameter("genre");
		String cast = request.getParameter("cast");
		String director = request.getParameter("director");
		
		
		String crew = request.getParameter("crew");
		
		
		String language = request.getParameter("language");
		String release_date = request.getParameter("releasedate");
		
		
		String music = request.getParameter("music");
		String rateing = request.getParameter("rateing");
		

		movies.setMovie_code(movie_code);
		movies.setMovie_name(movie_name);
		movies.setGenre(genre);
		movies.setCast(cast);
		movies.setDirector(director);
		movies.setCrew(crew);
		movies.setLanguage(language);
		movies.setRelease_date(release_date);
		movies.setMusic(music);
		movies.setRateing(rateing);

		
		
		
		System.out.println("movie Object prepared from the Request parameters : " + movies);
		
		// 3. Insert a record into the Database Table
		//int recordsInserted = PersonDAO.createPerson(person);
		//int lastInsertedId = PersonDAO.createPerson(person);
		
		// 3.1 A different flavor of CreateMovie() method that would throw an Exception back
		int lastInsertedId = 0;
		String exceptionMsg = null;
		
		try {
			lastInsertedId = MoviesDAO.createPersonFlavor1ThrowsException(movies);
		}catch(Exception exception) {
			exceptionMsg = exception.getMessage();
			System.err.println("Exception occurred while inserting the data into the Database Table");
			System.err.println("Message : " + exceptionMsg);
		}
		
		System.out.println(lastInsertedId);
		
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
	
		request.setAttribute("MoviesForm", movies);
		
		// 6. Forward / Delegate the control/flow the required JSP Page
		String url = null;

		if(lastInsertedId>0) {
			movies = MoviesDAO.getEmployeeById(lastInsertedId);
			request.setAttribute("movies", movies);
			url = "moviesview.jsp";
		}else {
			List<Movies> moviesList = MoviesDAO.listAll();
			request.setAttribute("moviessList", moviesList);
			//url = "movieslist.jsp";
			url = "moviescreate.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
