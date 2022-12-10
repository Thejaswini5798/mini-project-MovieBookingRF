<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MovieTicket | Login Page</title>
		<link rel="stylesheet" href="admin.css"/>
	</head>
	<body  class="AD">	
		<%
			if(session.getAttribute("user")!=null) {
				request.getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
			}
		%>
		<%
			String errorMessage = (String) request.getAttribute("errorMessage");
			String message = (String) request.getAttribute("message");
		
			if(null!=errorMessage) {
		%>
				<div class="errorMsg"><%= errorMessage %></div>
		<%
			} else {
				if(null!=message) {
		%>	
				<div class=successMsg><%= message %></div>
		<%	
				}
			}
		%>	
		<h1>WELCOME TO ADMIN LOGIN PAGE</h1>
		
		<h3>Login Page</h3>
		<div class="login">
			<form id="loginForm" name="loginForm" method="post" action="AdminLogin">
				<table>
					
					<tbody>
						<tr>
							<td>
								User Name : 
							</td>
							<td>
								<input type="text" id="userName" name="userName" 
										value="" size="20" required placeholder="User name"/>
							</td>
						</tr>
						<tr>
							<td>
								Password : 
							</td>
							<td>
								<input type="password" id="password" name="password" 
								value="" size="20" required placeholder="Password"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="submit" name="Login" Value="Login"/>
								<input type="reset" name="Reset" Value="Reset"/>
								
							</td>
						</tr>					
					</tbody>
				</table>
			</form>
		</div>
		<hr/>
	
	</body>
</html>		