<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Users" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="edit">
<%@include file="header.jsp" %>		
		<h1>Edit Users</h1>
		<%
			/* [17Oct2022] - Bug Fix - START */
			/* Issue : 
			   --------
			   Direct hit to Edit does not fetch the right object, instead
			   it reuses the object in session which was stored for the previous
			   operations.
			   Reason being,the id parameter being passed to this page from the
			   list.jsp, has never been considered/used. 
			 */
			Object idParam = request.getParameter("id");
			int id = -1;
			if(null!=idParam) {
				id = Integer.parseInt(idParam.toString());
				out.println("Id parameter passed is : " + id);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Users users = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			users = (Users) request.getAttribute("users");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null==users) {
				users = (Users) session.getAttribute("users");	
			}			
		%>
		<%
			String message = (String) request.getAttribute("message");			
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
			
			if(null==users) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="Update" method="post">
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
								<td>
									${users.id}
									<input type="hidden" name="id" value="${users.id}"/>
								</td>
							</tr>				
							<tr>
								<td>Name</td>
								<td>
									<input type="text" id="name" name="name" size=10 
										placeholder="Your Name" value="${users.name}"
										required />
								</td>
							</tr>
							<tr>
								<td>Age</td>
								<td>
									<input type="number" id="age" name="age" 
										min="1" max="100" step="1" size="5"
										placeholder="Your Age" value="${users.age}"
										required/>
								</td>
							</tr>
							
						<tr>
						<td>Phone Number</td>
						<td>
							<input type="tel" id="phone" name="phone"  size="10"
								placeholder="Your Phone No" value="${users.phone}"
								
								
								required/>
						</td>
					</tr>
							<tr>
								<td>City</td>
								<td>
									<input type="text" id="city" name="city" size=10 
										placeholder="Your City" 
										value="${users.city}" required/>
								</td>
							</tr>
							<tr>
						<td>PinCode</td>
						<td>
							<input type="text" id="pincode" name="pincode" size=15 
								placeholder="Your pincode"value="${users.pincode}"
								
								required />
						</td>
					<tr>
					<tr>
						<td>Email</td>
						<td>
							<input type="email" id="email" name="email" size=20 
								placeholder="Your Email"value="${users.email}"
								required />
						</td>
					</tr>
					<tr>
						<td>Password</td>
						<td>
							<input type="password" id="password" name="password"  size=15
								placeholder="Your Password" value="${users.password}"
								required />
								
								
						</td>
					</tr>
					<tr>
							<tr>
								<td colspan="2">
									<input type="submit" name="Update" Value="Update"/>
								</td>
							</tr>			
						</tbody>
					</table>
				</form>
		<% 		
			}
		%>
</body>