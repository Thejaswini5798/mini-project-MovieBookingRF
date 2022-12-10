<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Movies" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@include file="movieheader.jsp" %>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>View Movies</h1>
		<%
			Movies movies = (Movies) request.getAttribute("movies");
		
			if(null!=movies) {
				session.setAttribute("movies", movies);
			
		%>
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
					<td><%= movies.getMovie_id() %></td>
					
				</tr>
				<tr>
					<td>MovieCode</td>
					<td>${movies.movie_code}</td>
					
				</tr>
				<tr>
					<td>MovieName</td>
					<td>${movies.movie_name}</td>
				</tr>
				<tr>
					<td>Genre</td>
					<td>${movies.genre}</td>
					
				</tr>
				<tr>
					<td>Cast</td>
					<td>${movies.cast}</td>
				</tr>
				<tr>
					<td>Director</td>
					<td>${movies.director}</td>
					
				</tr>
				<tr>
					<td>Crew</td>
					<td>${movies.crew}</td>
				</tr>
				<tr>
					<td>Language</td>
					<td>${movies.language}</td>
					
				</tr>
				<tr>
					<td>ReleaseDate</td>
					<td>${movies.release_date}</td>
				</tr>
				<tr>
					<td>Music</td>
					<td>${movies.music}</td>
					
				</tr>
				<tr>
					<td>Rateing</td>
					<td>${movies.rateing}</td>
				</tr>
				
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="moviesedit.jsp">Edit</a> &nbsp;&nbsp;
						<a href="MoviesDelete?id=${movies.movie_id}">Delete</a>&nbsp;&nbsp;
					</td>
				</tr>			
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No matching records for the given Id - ${param.movieid}.</div>
		<%
			}
		%>
</body>