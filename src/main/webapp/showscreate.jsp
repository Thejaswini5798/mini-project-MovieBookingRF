<%@page import="com.tutorials.jdbc.bo.Shows"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">

<%@ include file="showsheader.jsp" %>
		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
		Shows shows = (Shows) request.getAttribute("ShowsForm");
			
			boolean isError = (null!=shows);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Create Shows</h1>
		<form id="createForm" name="createForm" action="ShowsCreate" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the Shows details</h3>
			</legend>
			<table class="">
				
				<tbody>	
					<tr>
						<td>ShowsCode</td>
						<td>
							<input type="text" id="showscode" name="showscode" size=20
								placeholder="shows code"
								value="<% if(isError) { 
									out.println(shows.getShowscode());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>ShowTime</td>
						<td>
							<input type="time" id="showtime" name="showtime" size=20
								placeholder="show time"
								value="<% if(isError) { 
									out.println(shows.getShowtime());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>ShowsDate</td>
						<td>
							<input type="date" id="showdate" name="showdate" size=20
								placeholder="shows date"
								value="<% if(isError) { 
									out.println(shows.getShowdate());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					
					
					
					<tr>
						<td>ScreenId</td>
						
						<td>
							<%						
								int screenid = isError ?shows.getScreenid() : 2;
							%>
							<input type="number" id="screenid" name="screenid" 
								min="1" max="500" step="1" size="5"
								placeholder=" Screenid"
								value="<%=screenid%>" 
								required/>
							
						</td>
						
					</tr>
						<tr>
						<td>MovieId</td>
						
						<td>
							<%						
								int movie_id = isError ?shows.getMovie_id() : 2;
							%>
							<input type="number" id="movieid" name="movieid" 
								min="1" max="100" step="1" size="5"
								placeholder=" Movie id"
								value="<%=movie_id%>" 
								required/>
							
						</td>
						
					</tr>
					
					<tr>
						<td>TheatreName</td>
						<td>
							<input type="text" id="theatrename" name="theatrename" size=30
								placeholder="theatre name"
								value="<% if(isError) { 
									out.println(shows.getTheatrename());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					
						<tr>
						<td>MovieName</td>
						<td>
							<input type="text" id="moviename" name="moviename" size=30
								placeholder="movie name"
								value="<% if(isError) { 
									out.println(shows.getMovie_name());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Seats_remaining</td>
						
						<td>
							<%						
								int seats_remaining = isError ?shows.getSeats_remaining() : 1;
							%>
							<input type="number" id="seatsremaininggold" name="seatsremaininggold" 
								min="1" max="500" step="1" size="5"
								placeholder="seats remaininggold"
								value="<%=seats_remaining%>" 
								required/>
							
						</td>
						
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