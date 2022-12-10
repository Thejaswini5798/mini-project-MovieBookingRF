<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Users" %>

	<link rel="stylesheet" href="profile.css"/>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		
		<%
			Users users = (Users) request.getAttribute("users");
		
			if(null!=users) {
				session.setAttribute("users", users);
			}
		%>
		<fieldset id=fld>
			<legend>
			<h3> My profile</h3>
			</legend>
		<table >
			<thead>
							
			</thead>
			<tbody>	
				<tr>
					
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<div class="dropdown">
						 <button class="dropbtn" id="drop">Profile</button>
                           <div class="dropdown-content">
						
						<a href="editprofile.jsp" id="edprofile">Edit Profile</a> &nbsp;&nbsp;
						<a href="Deleteprofile?id=${users.id}">Delete Profile</a>&nbsp;&nbsp;
						</div>
						 </div>	
					</td>
					
				</tr>	
				<tr>
					<td>Name :</td>
					<td>${users.name}</td>
				</tr>
				<tr>
					<td>Age :</td>
					<td>${users.age}</td>
				</tr>
				<tr>
					<td>PinCode :</td>
					<td>${users.pincode}</td>
				</tr>
				<tr>
					<td>City :</td>
					<td>${users.city}</td>
				</tr>
				<tr>
					<td>Email:</td>
					<td>${users.email}</td>
				</tr>	
				<tr>
					<td>Password:</td>
					<td>${users.password}</td>
				</tr>
			
				
					
			</tbody>
		</table>
		</fieldset>
