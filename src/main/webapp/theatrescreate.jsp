<%@page import="com.tutorials.jdbc.bo.Theatres"%>

<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="theaterheader.jsp" %>
		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
		Theatres theatres = (Theatres) request.getAttribute("TheatresForm");
			
			boolean isError = (null!=theatres);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Create Theatres</h1>
		<form id="createForm" name="createForm" action="TheatresCreate" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the Theatres details</h3>
			</legend>
			<table class="">
				
				<tbody>	
					<tr>
						<td>Theatrecode</td>
						<td>
							<input type="text" id="theatrecode" name="theatrecode" size=20
								placeholder="theatre code"
								value="<% if(isError) { 
									out.println(theatres.getTheatre_code());
								} else {
									out.println("");
								}%>"
								required />
						</td>
					</tr>
					<tr>
						<td>Theatrename</td>
						<td>
							<input type="text" id="theatrename" name="theatrename" size=30
								placeholder="theatre name"
								value="<% if(isError) { 
									out.println(theatres.getTheatrename());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>No_of_screens</td>
						
						<td>
							<%						
								int no_of_screens = isError ? theatres.getNo_of_screens() : 9;
							%>
							<input type="number" id="noofscreens" name="noofscreens" 
								min="1" max="100" step="1" size="5"
								placeholder="No of screen"
								value="<%=no_of_screens%>" 
								required/>
							
						</td>
						
					</tr>
					<tr>
						<td>Area</td>
						<td>
							<input type="text" id="area" name="area" size=50
								placeholder="area"
								value="<% if(isError) { 
									out.println(theatres.getArea());
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