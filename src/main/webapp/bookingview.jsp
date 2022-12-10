<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Booking" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">
<%@ include file="bookingheader.jsp" %>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
		
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
		%>
		<h1>ViewBooking</h1>
		<%
		Booking booking= (Booking) request.getAttribute("booking");
		
			if(null!=booking) {
				session.setAttribute("booking", booking);
			
		%>
		<table border="1">
			<thead>
				<tr>
					<td>Field</td>
					<td>Value</td>
				</tr>				
			</thead>
			<tbody>	
			<tr>
					<td>BookingId</td>
					<td><%=booking.getBookingid()%></td>
					
				</tr>
				<tr>
					<td>BookingCode</td>
					<td>${booking.booking_code}</td>
					
				</tr>
				<tr>
					<td>NoOfTickets</td>
					<td>${booking.no_of_tickets}</td>
				</tr>
				<tr>
					<td>TotalCost</td>
					<td>${booking.total_cost}</td>
					
				</tr>
					<tr>
					<td>CardNumber</td>
					<td>${booking.card_number}</td>
					
				</tr>
				<tr>
					<td>NameOncard</td>
					<td>${booking.name_oncard}</td>
				</tr>
				<tr>
					<td>Id</td>
					<td>${booking.id}</td>
				</tr>
				<tr>
					<td>ShowsId</td>
					<td>${booking.showsid}</td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- <form id="editForm" name="EditForm" action="Edit" action="post">
							<input type="submit" name="Edit" Value="Edit"/>
						</form>-->
						<a href="bookingedit.jsp">Edit</a> &nbsp;&nbsp;
						<a href="BookingDelete?id=${booking.bookingid}">Delete</a>&nbsp;&nbsp;
					</td>
				</tr>			
			</tbody>
		</table>
		<%
			} else {
		%>
				<div class=errorMsg>No matching records for the given Id - ${param.bookingid}.</div>
		<%
			}
		%>
</body>