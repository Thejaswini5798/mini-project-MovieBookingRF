<%@page import="com.tutorials.jdbc.bo.Ticket"%>



		<%
			String exceptionMsg = (String) request.getAttribute("exceptionMsg");
		Ticket ticket = (Ticket) request.getAttribute("TheatresForm");
			
			boolean isError = (null!=ticket);
			
			if(null!=exceptionMsg) {
		%>
				<div class="errorMsg">${exceptionMsg}</div>
		<%
			}
		%>
		<h1>Create Ticket</h1>
		<form id="updateForm" name="updateForm" action="UpdateTicket" method="post">
			<fieldset id=field>
			<legend>
			<h3>Enter the Ticket details</h3>
			</legend>
			<table class="">
				
				<tbody>	
					
					<tr>
						<td>no_of_tickets</td>
						
						<td>
							<%						
								int no_of_tickets = isError ? ticket.getNo_of_tickets() : 0;
							%>
							<input type="number" id="nooftickets" name="nooftickets" 
								min="1" max="100" step="1" size="5"
								placeholder=""
								value="<%=no_of_tickets%>" 
								required/>
							
						</td>
						
					</tr>
					<tr>
						<td>ticketclass</td>
						<td>
							<input type="text" id="ticketclass" name="ticketclass" size=50
								placeholder="ticketclass"
								value="<% if(isError) { 
									out.println(ticket.getTicketclass());
								} else {
									out.println("");
								}%>"
								required />
					</tr>
					
					
					<tr>
						<td colspan="2">
							<input type="submit" name="Update" Value="update"/>
						</td>
					</tr>				
				</tbody>
			</table>
		</form>
		</fieldset>
</body>