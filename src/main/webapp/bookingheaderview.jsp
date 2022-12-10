<%@page import="com.tutorials.jdbc.bo.Booking"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">

<%@ include file="bookingheader.jsp" %>
		
		<h1>View Booking</h1>
		<form id="viewForm" name="viewForm" action="BookingView" method="get">
			<fieldset id=field>
			<legend>
			<h3>Enter the details</h3>
			</legend>
			<table class="table1">
				<thead>
							
				</thead>
				<tbody>	
					
					<tr>
						<td>Enter the BookingID</td>
						<td>
							
							<input type="number" id="bookingid" name="bookingid" 
								 size="15"
								placeholder="Your bookingId"
								
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