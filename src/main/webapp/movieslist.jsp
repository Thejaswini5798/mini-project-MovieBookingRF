<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Movies" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="list">
<%@include file="movieheader.jsp" %>
		<h1>List All Movies</h1>
		<%
			List<Movies> moviesList = new ArrayList<>();
			Object obj = request.getAttribute("moviesList");
			if(null!=obj) {
				moviesList = (List<Movies>) obj;
			}
		%>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h2>Total </h2>
		Total list of movies is : <%= moviesList.size() %>
		<%
			if(moviesList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<h3>Movie List</h3>	
		<table  border="1" class="listtable">
			<thead>
				<tr>
					<td>MovieId</td>
					<td>movieCode</td>
					<td>MovieName</td>
					<td>Genre</td>
					<td>Cast</td>
					<td>Director</td>
					<td>Crew</td>
					<td>Language</td>
					<td>ReleaseDate</td>
					<td>Music</td>
					<td>Rateing</td>
					
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Movies movies : moviesList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + movies.getMovie_id() + " " + 
				"<a href='MoviesView?movieid=" + movies.getMovie_id() + "'>V " + "</a> | " +
				
				"<a href='MoviesDelete?movieid=" + movies.getMovie_id() + "'>D</a>" +
				"</td>");
				out.println("<td>" + movies.getMovie_code() + "</td>");
				out.println("<td>" + movies.getMovie_name() + "</td>");
				out.println("<td class='center'>" + movies.getGenre() + "</td>");
				out.println("<td class='center'>" + movies.getCast() + "</td>");
				out.println("<td>" + movies.getDirector() + "</td>");
				out.println("<td>" + movies.getCrew() + "</td>");
				out.println("<td>" + movies.getLanguage() + "</td>");
				out.println("<td>" +  movies.getRelease_date()+ "</td>");
				out.println("<td>" +  movies.getMusic()+ "</td>");
				out.println("<td>" +  movies.getRateing()+ "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
		</body>
