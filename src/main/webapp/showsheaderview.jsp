<%@page import="com.tutorials.jdbc.bo.Shows"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="showsheader.jsp" %>
		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
		Shows shows = (Shows) request.getAttribute("ShowsForm");
			
			boolean isError = (null!=shows);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>View Shows</h1>
		<form id="viewForm" name="viewForm" action="ShowsView" method="get">
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
								placeholder="Screen Id"
								
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