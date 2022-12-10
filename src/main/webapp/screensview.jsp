<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Screens" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="screensheader.jsp" %>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>ViewScreens</h1>
		<%
		Screens screens= (Screens) request.getAttribute("screens");
		
			if(null!=screens) {
				session.setAttribute("screens", screens);
			
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
					<td>ScreenId</td>
					<td><%=screens.getScreenid()%></td>
					
				</tr>
				<tr>
					<td>ScreenCode</td>
					<td>${screens.screencode}</td>
					
				</tr>
				<tr>
					<td>TheatreName</td>
					<td>${screens.theatrename}</td>
				</tr>
				<tr>
					<td>NoOfGoldseats</td>
					<td>${screens.no_of_goldseats}</td>
					
				</tr>
					<tr>
					<td>NoOfsilverseats</td>
					<td>${screens.no_of_silverseats}</td>
					
				</tr>
				<tr>
					<td>TheatreId</td>
					<td>${screens.theatreid}</td>
				</tr>
				<tr>
					<td>TheatreName</td>
					<td>${screens.theatrename}</td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="screensedit.jsp">Edit</a> &nbsp;&nbsp;
						<a href="screensDelete?id=${theatres.theatreid}">Delete</a>&nbsp;&nbsp;
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