<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Booking" %>

<link rel="stylesheet" href="adminpage.css"/>
<body class="edit">
<%@include file="bookingheader.jsp" %>		
		<h1>Edit Booking</h1>
		<%
			/* [17Oct2022] - Bug Fix - START */
			/* Issue : 
			   --------
			   Direct hit to Edit does not fetch the right object, instead
			   it reuses the object in session which was stored for the previous
			   operations.
			   Reason being,the id parameter being passed to this page from the
			   list.jsp, has never been considered/used. 
			 */
			Object idParam = request.getParameter("bookingid");
			int bookingid = -1;
			if(null!=idParam) {
				bookingid = Integer.parseInt(idParam.toString());
				out.println("bookingId parameter passed is : " + bookingid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Booking booking = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			 booking = (Booking) request.getAttribute("booking");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null== booking) {
				booking = (Booking) session.getAttribute("booking");	
			}			
		%>
		<%
			String message = (String) request.getAttribute("message");			
			
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
			
			if(null!=message) {
		%>
				<div class="message">${message}</div>
		<% 
			}
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
			
			if(null== booking) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="BookingUpdate" method="post">
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
								<td>
									${booking.bookingid}
									<input type="hidden" name="bookingid" value="${booking.bookingid}"/>
								</td>
							</tr>				
							<tr>
								<td>BookingCode</td>
								<td>
									<input type="text" id="bookingcode" name="bookingcode" size=30
								placeholder="Booking code" value="${booking.booking_code}"
										required />
								</td>
							</tr>
							<tr>
						<td>NoOfTickets</td>
						
						<td>
							
							<input type="number" id="nooftickets" name="nooftickets" 
								min="1" max="100" step="1" size="5"
								placeholder="NoOfTickets"value="${booking.no_of_tickets}"
								
								required/>
							
						</td>
						
					</tr>
					
					<tr>
						<td>TotalCost</td>
						
						<td>
							
							<input type="number" id="totalcost" name="totalcost" 
								min="1" max="600" step="1" size="5"
								placeholder="TotalCost" value="${booking.total_cost}"
								
								required/>
							
						</td>
						
					</tr>
					<tr>
						<td>CardNumber</td>
						<td>
							<input type="text" id="cardnumber" name="cardnumber" size=30
								placeholder="Card Number" value="${booking.card_number}"
								
								required />
					</tr>
					<tr>
						<td>NameOncard</td>
						<td>
							<input type="text" id="nameoncard" name="nameoncard" size=30
								placeholder="Name On card" value="${booking.name_oncard}"
								
								required />
					</tr>
					<tr>
						<td>Id</td>
						
						<td>
							
							<input type="number" id="id" name="id" 
								min="1" max="100" step="1" size="5"
								placeholder="id" value="${booking.id}"
								
								required/>
							
						</td>
					</tr>
					<tr>
						<td>ShowsId</td>
						
						<td>
							
							<input type="number" id="showsid" name="showsid" 
								min="1" max="100" step="1" size="5"
								placeholder="showsid" value="${booking.showsid}"
								
								required/>
							
						</td>
					</tr>
						
							<tr>
								<td colspan="2">
									<input type="submit" name="Update" Value="Update"/>
								</td>
							</tr>			
						</tbody>
					</table>
				</form>
		<% 		
			}
		%>
		</body>
