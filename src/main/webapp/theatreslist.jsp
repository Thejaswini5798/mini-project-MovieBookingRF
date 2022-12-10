<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Theatres" %>

<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@include file="theaterheader.jsp" %>
		<h1>List All Theatres</h1>
		<%
			List<Theatres> theatresList = new ArrayList<>();
			Object obj = request.getAttribute("theatresList");
			if(null!=obj) {
				theatresList = (List<Theatres>) obj;
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
		Total list of theatres is : <%= theatresList.size() %>
		<%
			if(theatresList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<h3>Theatres List</h3>	
		<table  border="1" >
			<thead>
				<tr>
					<td>TheatreId</td>
					<td>TheatreCode</td>
					<td>TheaterName</td>
					<td>NoOfScreens</td>
					<td>Area</td>
					
					
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Theatres theatres : theatresList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + theatres.getTheatreid() + " " + 
				"<a href='TheatresView?id=" + theatres.getTheatreid() + "'>V " + "</a> | " +
				"<a href='TheatresEdit?id=" + theatres.getTheatreid() + "'>E</a> | " +
				"<a href='TheatresDelete?id=" + theatres.getTheatreid() + "'>D</a>" +
				"</td>");
				out.println("<td>" + theatres.getTheatre_code () + "</td>");
				out.println("<td class='center'>" + theatres.getTheatrename() + "</td>");
				out.println("<td class='center'>" + theatres.getNo_of_screens() + "</td>");
				out.println("<td>" + theatres.getArea() + "</td>");
				
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
	
</body>