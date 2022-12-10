package com.tutorials.jdbc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tutorials.jdbc.bo.Movies;
import com.tutorials.jdbc.dao.MoviesDAO;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/MoviesUpdate")
public class MoviesUpdateServlate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoviesUpdateServlate() {
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
		Movies movies = new Movies();
		
		String idStr = request.getParameter("movieid");
		if(null==idStr) {
			//TODO Revisit this later
			throw new ServletException("Missing Id value, can't update the row!");
		}
		int movie_id = Integer.parseInt(idStr);

		String movie_code= request.getParameter("moviecode");
		String movie_name = request.getParameter("moviename");
		String genre = request.getParameter("genre");
		String cast = request.getParameter("cast");
		String director = request.getParameter("director");
		
		
		String crew = request.getParameter("crew");
		
		
		String language = request.getParameter("language");
		String release_date = request.getParameter("releasedate");
		
		
		String music = request.getParameter("music");
		String rateing = request.getParameter("rateing");
		
		
		//2. Prepare the Person Object with the values obtained from Request
		movies.setMovie_id(movie_id);
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
		
		//3. Update the values in Database
		String exceptionMsg = null;
		try {
			MoviesDAO.updateMovie(movies);
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
		request.getSession().setAttribute("movies", movies);
		
		//5. Redirect/Delegate to the corresponding view 
		request.getRequestDispatcher("moviesedit.jsp").forward(request, response);
		
		
	}

}

