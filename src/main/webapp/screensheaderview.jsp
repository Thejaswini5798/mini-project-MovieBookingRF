<%@page import="com.tutorials.jdbc.bo.Screens"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
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
		<h1>view Screens</h1>
		<form id="viewForm" name="viewForm" action="ScreensView" method="get">
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
								placeholder="theatre Id"
								
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