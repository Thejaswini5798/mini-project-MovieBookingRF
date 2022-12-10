<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Shows" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="showsheader.jsp" %>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>ViewShows</h1>
		<%
		Shows shows= (Shows) request.getAttribute("shows");
		
			if(null!=shows) {
				session.setAttribute("shows", shows);
			
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
					<td>ShowId</td>
					<td><%=shows.getShowsid()%></td>
					
				</tr>
				<tr>
					<td>ShowsCode</td>
					<td>${shows.showscode}</td>
					
				</tr>
				<tr>
					<td>ShowTime</td>
					<td>${shows.showtime}</td>
				</tr>
				<tr>
					<td>ShowDates</td>
					<td>${shows.showdate}</td>
					
				</tr>
					
				<tr>
					<td>ScreenId</td>
					<td>${shows.screenid}</td>
				</tr>
				<tr>
					<td>MovieId</td>
					<td>${shows.movie_id}</td>
				</tr>
				<tr>
					<td>TheatreName</td>
					<td>${shows.theatrename}</td>
				</tr>
				<tr>
					<td>MovieName</td>
					<td>${shows.movie_name}</td>
				</tr>
				<tr>
					<td>SeatsRemaining</td>
					<td>${shows.seats_remaining}</td>
					
				</tr>
			
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="showsedit.jsp">Edit</a> &nbsp;&nbsp;
						<a href="showsDelete?id=${shows.showsid}">Delete</a>&nbsp;&nbsp;
					</td>
				</tr>			
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No matching records for the given Id - ${param.id}.</div>
		<%
			}
		%>
</body>