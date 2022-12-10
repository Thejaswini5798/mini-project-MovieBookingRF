<%@page import="com.tutorials.jdbc.bo.Booking"%>
<link rel="stylesheet" href="adminpage.css"/>
<body class="create">
<link rel="stylesheet" href="adminpage.css"/>
<body class="bookinngcreat">
<%@ include file="bookingheader.jsp" %>
		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
		Booking booking = (Booking) request.getAttribute("BookingForm");
			
			boolean isError = (null!=booking);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Create Screens</h1>
		<form id="createForm" name="createForm" action="BookingCreate" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the Screens details</h3>
			</legend>
			<table class="">
				
				<tbody>	
					<tr>
						<td>BookingCode</td>
						<td>
							<input type="text" id="bookingcode" name="bookingcode" size=30
								placeholder="Booking code"
								value="<% if(isError) { 
									out.println(booking.getBooking_code() );
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					
						<tr>
						<td>NoOfTickets</td>
						
						<td>
							<%						
								int no_of_tickets = isError ?booking.getNo_of_tickets() : 1;
							%>
							<input type="number" id="nooftickets" name="nooftickets" 
								min="1" max="100" step="1" size="5"
								placeholder="NoOfTickets"
								value="<%=no_of_tickets%>" 
								required/>
							
						</td>
						
					</tr>
					
					<tr>
						<td>TotalCost</td>
						
						<td>
							<%						
								int total_cost = isError ?booking.getTotal_cost()  : 2;
							%>
							<input type="number" id="totalcost" name="totalcost" 
								min="1" max="600" step="1" size="5"
								placeholder="TotalCost"
								value="<%=total_cost%>" 
								required/>
							
						</td>
						
					</tr>
					<tr>
						<td>CardNumber</td>
						<td>
							<input type="text" id="cardnumber" name="cardnumber" size=30
								placeholder="Card Number"
								value="<% if(isError) { 
									out.println(booking.getCard_number()  );
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>NameOncard</td>
						<td>
							<input type="text" id="nameoncard" name="nameoncard" size=30
								placeholder="Name On card"
								value="<% if(isError) { 
									out.println(booking.getName_oncard());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					<tr>
						<td>Id</td>
						
						<td>
							<%						
								int id = isError ?booking.getId() : 5;
							%>
							<input type="number" id="id" name="id" 
								min="1" max="100" step="1" size="5"
								placeholder="id"
								value="<%=id%>" 
								required/>
							
						</td>
					</tr>
					<tr>
						<td>ShowsId</td>
						
						<td>
							<%						
								int showsid = isError ?booking.getShowsid() : 5;
							%>
							<input type="number" id="showsid" name="showsid" 
								min="1" max="100" step="1" size="5"
								placeholder="showsid"
								value="<%=showsid%>" 
								required/>
							
						</td>
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