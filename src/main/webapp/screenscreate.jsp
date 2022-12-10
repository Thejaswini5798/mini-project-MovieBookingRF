<%@page import="com.tutorials.jdbc.bo.Screens"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="create">

<%@ include file="screensheader.jsp" %>
		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
		Screens screens = (Screens) request.getAttribute("ScreensForm");
			
			boolean isError = (null!=screens);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Create Screens</h1>
		<form id="createForm" name="createForm" action="ScreensCreate" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the Screens details</h3>
			</legend>
			<table class="">
				
				<tbody>	
					<tr>
						<td>ScreenCode</td>
						<td>
							<input type="text" id="screencode" name="screencode" size=20
								placeholder="screen code"
								value="<% if(isError) { 
									out.println(screens.getScreencode());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					
					<tr>
						<td>No_of_goldseats</td>
						
						<td>
							<%						
								int no_of_goldseats = isError ?screens.getNo_of_goldseats() : 1;
							%>
							<input type="number" id="noofgoldseats" name="noofgoldseats" 
								min="1" max="100" step="1" size="5"
								placeholder="no of goldseats"
								value="<%=no_of_goldseats%>" 
								required/>
							
						</td>
						
					</tr>
					<tr>
						<td>No_of_silverseats</td>
						
						<td>
							<%						
								int no_of_silverseats = isError ?screens.getNo_of_silverseats() : 2;
							%>
							<input type="number" id="noofsilverseats" name="noofsilverseats" 
								min="1" max="100" step="1" size="5"
								placeholder="no of silverseats"
								value="<%=no_of_silverseats%>" 
								required/>
							
						</td>
						
					</tr>
					
					<tr>
						<td>TheatreId</td>
						
						<td>
							<%						
								int theatreid = isError ?screens.getTheatreid() : 5;
							%>
							<input type="number" id="theatreid" name="theatreid" 
								min="1" max="100" step="1" size="5"
								placeholder="theatreid"
								value="<%=theatreid%>" 
								required/>
							
						</td>
					
					<tr>
						<td>TheatreName</td>
						<td>
							<input type="text" id="theatrename" name="theatrename" size=30
								placeholder="theatre name"
								value="<% if(isError) { 
									out.println(screens.getTheatrename());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" name="Create" Value="Create"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		</fieldset>
</body>