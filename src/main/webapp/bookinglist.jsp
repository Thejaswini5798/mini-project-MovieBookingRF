<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Booking" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="list">

<%@include file="bookingheader.jsp" %>
		<h1>List All Booking</h1>
		<%
			List<Booking> bookingList = new ArrayList<>();
			Object obj = request.getAttribute("bookingList");
			if(null!=obj) {
				bookingList = (List<Booking>) obj;
			}
		%>
		<%
			String message = (String) request.getAttribute("message");
			String flag = (String) request.getAttribute("flag");
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message <%= flag%>">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h2>Total </h2>
		Total list of booking is : <%= bookingList.size() %>
		<%
			if(bookingList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<h3>Booking List</h3>	
		<table  border="1" >
			<thead>
				<tr>
					<td>BookingId</td>
					<td>BookingCode</td>
					<td>NoOfTickets</td>
					<td>TotalCost</td>
					<td>CardNumber</td>
					<td>NameOncard</td>
					<td> Id</td>
					<td>ShowsId</td>
					
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Booking booking : bookingList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + booking.getBookingid() + " " + 
				"<a href='BookingView?bookingid=" + booking.getBookingid() + "'>V " + "</a> | " +
				"<a href='BookingEdit?id=" + booking.getBookingid() + "'>E</a> | " +
				"<a href='BookingDelete?id=" + booking.getBookingid()+ "'>D</a>" +
				"</td>");
				out.println("<td>" + booking.getBooking_code() + "</td>");
				out.println("<td class='center'>" + booking.getNo_of_tickets() + "</td>");
				out.println("<td class='center'>" + booking.getTotal_cost() + "</td>");
				out.println("<td>" + booking.getCard_number() + "</td>");
				out.println("<td>" + booking.getName_oncard() + "</td>");
				out.println("<td>" + booking.getId() + "</td>");
				out.println("<td>" + booking.getShowsid() + "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
	
</body>