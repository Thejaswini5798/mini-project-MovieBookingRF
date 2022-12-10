<%@page import="com.tutorials.jdbc.bo.Movies"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="movieheader.jsp" %>
		
		<h1>View Movies</h1>
		<form id="viewForm" name="viewForm" action="MoviesView" method="get">
			<fieldset id=field>
			<legend>
			<h3>Enter the details</h3>
			</legend>
			<table class="table1">
				<thead>
							
				</thead>
				<tbody>	
					
					<tr>
						<td>Enter the MovieID</td>
						<td>
							
							<input type="number" id="movieid" name="movieid" 
								 size="15"
								placeholder="Your MovieId"
								
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