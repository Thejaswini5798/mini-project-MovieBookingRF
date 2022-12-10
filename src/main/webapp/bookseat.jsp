<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.BookSeats" %>


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
			Object idParam = request.getParameter("bookid");
			int bookid = -1;
			if(null!=idParam) {
				bookid = Integer.parseInt(idParam.toString());
				out.println("bookId parameter passed is : " + bookid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			BookSeats bookseats = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			 bookseats = (BookSeats) request.getAttribute("bookseats");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null== bookseats) {
				bookseats = (BookSeats) session.getAttribute("bookseats");	
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
			
			if(null== bookseats) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="BookUpdate" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
							<tr>
								<td>BookId</td>
								<td>
									${bookseats.bookid}
									<input type="hidden" name="bookid" value="${bookseats.bookid}"/>
								</td>
							</tr>
							<tr>
								<td>theatreName</td>
								<td>
									${bookseats.theatrename}
									<input type="hidden" name="theatrename" value="${bookseats.theatrename}"/>
								</td>
							</tr>	
							<tr>
								<td>MovieName</td>
								<td>
									${bookseats.movie_name}
									<input type="hidden" name="moviename" value="${bookseats.movie_name}"/>
								</td>
							</tr>	
							<tr>
								<td>ShowsId</td>
								<td>
									${bookseats.getShowsid()}
									<input type="hidden" name="showsid" value="${bookseats.getShowsid()}"/>
								</td>
							</tr>
							<tr>
								<td>ShowTime</td>
								<td>
									${bookseats.showtime}
									<input type="hidden" name="showstime" value="${bookseats.showtime}"/>
								</td>
							</tr>
							<tr>
								<td>ShowDate</td>
								<td>
									${bookseats.showdate}
									<input type="hidden" name="showstime" value="${bookseats.showdate}"/>
								</td>
							</tr>
							
							<tr>
						<td>NoOfTickets</td>
						
						<td>
							
							<input type="number" id="nooftickets" name="nooftickets" 
								min="1" max="100" step="1" size="5"
								placeholder="NoOfTickets"value="${bookseats.no_of_tickets}"
								
								required/>
							
						</td>
						
					</tr>
					
					
							<tr>
								<td colspan="2">
									<input type="submit" name="Update" Value="BOOK IT"/>
								</td>
							</tr>			
						</tbody>
					</table>
				</form>
		<% 		
			}
		%>
		</body>
