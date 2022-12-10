<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Shows" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="list">

<%@include file="showsheader.jsp" %>
		<h1>List All Shows</h1>
		<%
			List<Shows>showsList = new ArrayList<>();
			Object obj = request.getAttribute("showsList");
			if(null!=obj) {
				showsList = (List<Shows>) obj;
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
		Total list of shows is : <%= showsList.size() %>
		<%
			if(showsList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<h3>Screens List</h3>	
		<table  border="1" >
			<thead>
				<tr>
					<td>ShowsId</td>
					<td>ShowsCode</td>
					<td>ShowTime</td>
					<td>ShowDate</td>
					<td>ScreenId</td>
					<td>MovieId</td>
					<td>TheatreName</td>
					<td>MovieName</td>
					<td>SeatsRemaining</td>
					
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Shows shows : showsList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + shows.getShowsid() + " " + 
				"<a href='ShowsView?id=" + shows.getShowsid() + "'>V " + "</a> | " +
				"<a href='ShowsEdit?id=" + shows.getShowsid() + "'>E</a> | " +
				"<a href='ShowsDelete?id=" + shows.getShowsid() + "'>D</a>" +
				"</td>");
				out.println("<td>" + shows.getShowscode() + "</td>");
				out.println("<td class='center'>" + shows.getShowtime() + "</td>");
				out.println("<td class='center'>" + shows.getShowdate() + "</td>");
				
				out.println("<td class='center'>" + shows.getScreenid() + "</td>");
				out.println("<td>" + shows.getMovie_id() + "</td>");
				out.println("<td>" + shows.getTheatrename() + "</td>");
				out.println("<td>" + shows.getMovie_name() + "</td>");
				out.println("<td>" + shows.getSeats_remaining() + "</td>");
				
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
	
</body>