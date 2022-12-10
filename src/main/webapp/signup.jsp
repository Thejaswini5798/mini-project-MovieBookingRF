<%@page import="com.tutorials.jdbc.bo.Users"%>


		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			Users users = (Users) request.getAttribute("UsersForm");
			
			boolean isError = (null!=users);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>REGISTRATION FROM</h1>
		<form id="createForm" name="createForm" action="SignUp" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the details</h3>
			</legend>
			<table class="table1">
				<thead>
							
				</thead>
				<tbody>	
					<tr>
						<td>Name</td>
						<td>
							<input type="text" id="name" name="name" size=20 
								placeholder="Your Name"
								value="<% if(isError) { 
									out.println(users.getName());
								} else {
									out.println("theju");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Age</td>
						<td>
							<%						
								int age = isError ? users.getAge() : 9;
							%>
							<input type="number" id="age" name="age" 
								min="1" max="100" step="1" size="5"
								placeholder="Your Age"
								value="<%=age%>" 
								required/>
						</td>
					</tr>
					<tr>
						<tr>
						<td>Phone Number</td>
						<td>
							<input type="tel" id="phone" name="phone"  size="10"
								placeholder="Your Phone No"
								
								
								required/>
						</td>
					</tr>
					<tr>
						<td>City</td>
						<td>
							<input type="text" id="city" name="city" size=20 
								placeholder="Your City" 
								value="<% if(isError) { 
									out.println(users.getCity());
								} else {
									out.println("Bangalore");
								}%>"required />
						</td>
					</tr>
					
					
					<tr>
						<td>PinCode</td>
						<td>
							<input type="text" id="pincode" name="pincode" size=15 
								placeholder="Your pincode"
								value="<% if(isError) { 
									out.println(users.getPincode());
								} else {
									out.println("pincode");
								}%>"required />
						</td>
					<tr>
					<tr>
						<td>Email</td>
						<td>
							<input type="email" id="email" name="email" size=20 
								placeholder="Your Email"
								value="<% if(isError) { 
									out.println(users.getEmail());
								} else {
									out.println("Enter the Email");
								}%>"required />
						</td>
					</tr>
					<tr>
						<td>Password</td>
						<td>
							<input type="password" id="password" name="password"  size=15
								placeholder="Your Password"
								value="<% if(isError) { 
									out.println(users.getPassword());
								} else {
									out.println("");
								}%>"required />
								
								
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" name="Create" Value="SignUp"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		</fieldset>
