<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Movies" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="edit">
<%@include file="movieheader.jsp" %>		
		<h1>Edit Movie</h1>
		<%
			/* [17Oct2022] - Bug Fix - START */
			/* Issue : 
			   --------
			   Direct hit to Edit does not fetch the right object, instead
			   it reuses the object in session which was stored for the previous
			   operations.
			   Reason being,the id parameter being passed to this page from the
			   list.jsp, has never been considered/used. 
			 */
			Object idParam = request.getParameter("movieid");
			int movie_id =-1;
			if(null!=idParam) {
				movie_id = Integer.parseInt(idParam.toString());
				out.println("movieId parameter passed is : " + movie_id);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Movies movies = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			movies = (Movies) request.getAttribute("movies");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null==movies) {
				movies = (Movies) session.getAttribute("movies");	
			}			
		%>
		<%
			String message = (String) request.getAttribute("message");			
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
			
			if(null==movies) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="MoviesUpdate" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
						<tr>
								<td>MovieId</td>
								<td>
									${movies.movie_id}
									<input type="hidden" name="movieid" value="${movies.movie_id}"/>
								</td>
							<tr>
								<td>MovieCode</td>
								<td>
									<input type="text" id="moviecode" name="moviecode" size=15 
										placeholder="Movie code" value="${movies.movie_code}"
										required />
								</td>
							</tr>				
							<tr>
								<td>MovieName</td>
								<td>
									<input type="text" id="moviename" name="moviename" size=20 
										placeholder="Movie Name" value="${movies.movie_name}"
										required />
								</td>
							</tr>
							<tr>
								<td>Genre</td>
								<td>
									<input type="text" id="genre" name="genre" size=20 
										placeholder="Movie genre" value="${movies.genre}"
										required />
								</td>
							</tr>				
							<tr>
								<td>Cast</td>
								<td>
									<input type="text" id="cast" name="cast" size=100 
										placeholder="Movie cast" value="${movies.cast}"
										required />
								</td>
							</tr>
							<tr>
								<td>Director</td>
								<td>
									<input type="text" id="director" name="director" size=20 
										placeholder="Movie director" value="${movies.director}"
										required />
								</td>
							</tr>				
							<tr>
								<td>Crew</td>
								<td>
									<input type="text" id="crew" name="crew" size=300
										placeholder="Movie crew" value="${movies.crew}"
										required />
								</td>
							</tr>
							<tr>
								<td>Language</td>
								<td>
									<input type="text" id="language" name="language" size=20 
										placeholder="Movie language" value="${movies.language}"
										required />
								</td>
							</tr>				
							<tr>
								<td>ReleaseDate</td>
								<td>
									<input type="text" id="releasedate" name="releasedate" size=20 
										placeholder="Movie Releasedate" value="${movies.release_date}"
										required />
								</td>
							</tr>
							<tr>
								<td>Music</td>
								<td>
									<input type="text" id="music" name="music" size=20 
										placeholder="Movie music" value="${movies.music}"
										required />
								</td>
							</tr>				
							<tr>
								<td>Rateing</td>
								<td>
									<input type="text" id="rateing" name="rateing" size=20 
										placeholder="Movie rateing" value="${movies.rateing}"
										required />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<input type="submit" name="Update" Value="Update"/>
								</td>
							</tr>			
						</tbody>
					</table>
				</form>
		<% 		
			}
		%>
</body>