<%@page import="com.tutorials.jdbc.bo.Movies"%>
<link rel="stylesheet" href="admin.css"/>
<body class="index1">
<%@ include file="movieheader.jsp" %>
		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			Movies movies = (Movies) request.getAttribute("MoviesForm");
			
			boolean isError = (null!=movies);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Create Movie</h1>
		<form id="createForm" name="createForm" action="MoviesCreate" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the  Movies details</h3>
			</legend>
			<table class="table1">
				<thead>
							
				</thead>
				<tbody>	
					<tr>
						<td>MovieCode</td>
						<td>
							<input type="text" id="moviecode" name="moviecode" size=15
								placeholder="movie code"
								value="<% if(isError) { 
									out.println(movies.getMovie_code());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>MovieName</td>
						<td>
							<input type="text" id="moviename" name="moviename" size=20
								placeholder="movie name"
								value="<% if(isError) { 
									out.println(movies.getMovie_name());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>Genre</td>
						<td>
							<input type="text" id="genre" name="genre" size=20
								placeholder="movie genre"
								value="<% if(isError) { 
									out.println(movies.getGenre());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Cast</td>
						<td>
							<input type="text" id="cast" name="cast" size=100
								placeholder="movie cast"
								value="<% if(isError) { 
									out.println(movies.getCast());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>Director</td>
						<td>
							<input type="text" id="director" name="director" size=20
								placeholder="movie director"
								value="<% if(isError) { 
									out.println(movies.getDirector());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Crew</td>
						<td>
							<input type="text" id="crew" name="crew" size=300
								placeholder="movie crew"
								value="<% if(isError) { 
									out.println(movies.getCrew());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>Language</td>
						<td>
							<input type="text" id="language" name="language" size=20
								placeholder="movie language"
								value="<% if(isError) { 
									out.println(movies.getLanguage());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>ReleaseDate</td>
						<td>
							<input type="date" id="releasedate" name="releasedate" size=20
								placeholder="movie releasedate"
								value="<% if(isError) { 
									out.println(movies.getRelease_date());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>Music</td>
						<td>
							<input type="text" id="music" name="music" size=20
								placeholder="movie music"
								value="<% if(isError) { 
									out.println(movies.getMusic());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Rateing</td>
						<td>
							<input type="text" id="rateing" name="rateing" size=20
								placeholder="movie rateing"
								value="<% if(isError) { 
									out.println(movies.getRateing());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" name="Create" Value="Create"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		</fieldset>
</body>