<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Users" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="list">
<%@include file="header.jsp" %>
		<h1>List All Users</h1>
		<%
			List<Users> usersList = new ArrayList<>();
			Object obj = request.getAttribute("usersList");
			if(null!=obj) {
				usersList = (List<Users>) obj;
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
		Total list of users is : <%= usersList.size() %>
		<%
			if(usersList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<h3>Users List</h3>	
		
		<table  border="1"  >
			<thead>
				<tr >
					<td>Id</td>
					<td>Name</td>
					<td>Age</td>
					<td>Phone</td>
					<td>City</td>
					<td>PinCode</td>
					<td>Email</td>
					<td>Password</td>
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Users users : usersList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + users.getId() + " " + 
				"<a href='View?id=" + users.getId() + "'>V " + "</a> | " +
				"<a href='Edit?id=" + users.getId() + "'>E</a> | " +
				"<a href='Delete?id=" + users.getId() + "'>D</a>" +
				"</td>");
				out.println("<td>" + users.getName() + "</td>");
				out.println("<td class='center'>" + users.getAge() + "</td>");
				out.println("<td class='center'>" + users.getPhone() + "</td>");
				out.println("<td>" + users.getCity() + "</td>");
				out.println("<td>" + users.getPincode() + "</td>");
				out.println("<td>" + users.getEmail() + "</td>");
				out.println("<td>" + "*******" + "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
	
</body>