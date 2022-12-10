<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Users" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@include file="header.jsp" %>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>View Users</h1>
		<%
			Users users = (Users) request.getAttribute("users");
		
			if(null!=users) {
				session.setAttribute("users", users);
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
					<td>Id</td>
					<td><%=users.getId()%></td>
					
				</tr>
				<tr>
					<td>Name</td>
					<td>${users.name}</td>
				</tr>
				<tr>
					<td>Age</td>
					<td>${users.age}</td>
				</tr>
				<tr>
					<td>PinCode</td>
					<td>${users.pincode}</td>
				</tr>
				<tr>
					<td>City</td>
					<td>${users.city}</td>
				</tr>
				<tr>
					<td>Email</td>
					<td>${users.email}</td>
				</tr>	
				<tr>
					<td>Password</td>
					<td>${users.password}</td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="edit.jsp">Edit</a> &nbsp;&nbsp;
						<a href="Delete?id=${users.id}">Delete</a>&nbsp;&nbsp;
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