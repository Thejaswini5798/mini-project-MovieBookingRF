<%@page import="com.tutorials.jdbc.bo.Users"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="headview">
<%@ include file="header.jsp" %>
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
		<h1>view Users</h1>
		<form id="viewForm" name="viewForm" action="View" method="get">
			<fieldset id=field>
			<legend>
			<h3>Enter the details</h3>
			</legend>
			<table class="table1">
				<thead>
							
				</thead>
				<tbody>	
					
					<tr>
						<td>Enter the ID</td>
						<td>
							
							<input type="number" id="id" name="id" 
								 size="5"
								placeholder="Your Id"
								
								required/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" name="View" Value="view"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		</fieldset>
</body>