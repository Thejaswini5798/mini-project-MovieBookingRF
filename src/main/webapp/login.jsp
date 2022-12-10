<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MovieTicket | Login Page</title>
		<link rel="stylesheet" href="moviestyle.css"/>
	</head>
	<body  class="log">	
		<%
			if(session.getAttribute("user")!=null) {
				request.getServletContext().getRequestDispatcher("/welcome.jsp").forward(request,response);
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
		<div class="ST">
		<h1 ><img src="https://eshop-4-prod-public.s3.amazonaws.com/cms/uploads/2020/06/TheStraitsTimesMini.png" alt="" height="60px" width="60px">SHOW TIME</h1>
		
		
				
				<a href="adminlogin.jsp"><input type="submit" name="Admin" Value="Admin"/></a>
				 
                <a href="signup.jsp"><input type="submit" name="Signin" Value="SignUp"/></a>
                   
      	 </div>
		<h2>Log In!</h2>
		<div class="login">
			<form id="loginForm" name="LoginForm" method="post" action="Login">
				<table>
					
					<tbody>
						<tr>
							<td>
								User Name : 
							</td>
							<td>
								<input type="text" id="email" name="email" 
										value="" size="20"  placeholder="User Email"
										required/>
							</td>
						</tr>
						<tr>
							<td>
								Password : 
							</td>
							<td >
								<input type="password" id="password" name="password" 
								value="" size="20"  placeholder="Password"
								required/>
									
								
							</td>
						</tr>
					
						
										
						<tr>
							<td colspan="2">
								<input type="submit" name="Login" Value="Login"/>
								<input type="reset" name="Reset" Value="Reset"/>
								<a href="" id="for" >Forget Password?</a>
						
								
							</td>
							
						</tr>					
					</tbody>
				</table>
			</form>
			
						
		</div>
		<hr/>
		
	</body>
</html>		