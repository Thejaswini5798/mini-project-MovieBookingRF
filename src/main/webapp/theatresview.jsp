<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Theatres" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="theaterheader.jsp" %>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>ViewTheatres</h1>
		<%
		Theatres theatres= (Theatres) request.getAttribute("theatres");
		
			if(null!=theatres) {
				session.setAttribute("theatres", theatres);
			
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
					<td>TheatreId</td>
					<td><%=theatres.getTheatreid()%></td>
					
				</tr>
				<tr>
					<td>Theatre_code</td>
					<td>${theatres.theatre_code}</td>
					
				</tr>
				<tr>
					<td>TheatreName</td>
					<td>${theatres.theatrename}</td>
				</tr>
				<tr>
					<td>No_of_screens</td>
					<td>${theatres.no_of_screens}</td>
					
				</tr>
				<tr>
					<td>Area</td>
					<td>${theatres.area}</td>
				</tr>
				
				
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="theatresedit.jsp">Edit</a> &nbsp;&nbsp;
						<a href="TheatresDelete?id=${theatres.theatreid}">Delete</a>&nbsp;&nbsp;
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