<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.BookSeats" %>



	
		<%
			List<BookSeats> bookseatsList = new ArrayList<>();
			Object obj = request.getAttribute("bookseatsList");
			if(null!=obj) {
				bookseatsList = (List<BookSeats>) obj;
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
		
		Total list of shows is : <%= bookseatsList.size() %>
		<%
			if(bookseatsList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<div class="">
			<div>
		<table  border="" >
			<thead>
				<tr>
				<td>BookId</td>
					<td>TheatreName</td>
					<td>MovieName</td>
					<td>ShowsId</td>
					<td>ShowTime</td>
					<td>ShowDate</td>
					<td>Total_No_Seat</td>
					
				</tr>	
				
					
			</thead>
			<tbody>	
			<% 
        if(request.getParameter("moviename") != null) {
               session.setAttribute("status", "guest");
        }
    %>
		<%				
			for(BookSeats bookseats : bookseatsList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + bookseats .getBookid() + " " + 
						"<a href='BookEdit?id=" +bookseats.getBookid() + "'>BookNow " + "</a> | " +
						
						"</td>");
				out.println("<td>" +bookseats.getTheatrename() + "</td>");
				out.println("<td>" + bookseats.getMovie_name() + "</td>");
				out.println("<td>" +bookseats.getShowsid() + "</td>");
				
				out.println("<td class='center'>" +bookseats.getShowtime() + "</td>");	
				out.println("<td class='center'>" +bookseats.getShowdate() + "</td>");
				
			
			
				out.println("<td>" + bookseats.getTotal_no_seat() +"</td>");
				
				out.println("</tr>");
			}			
		%>	
		
			
			</tbody>

		</table>
	
		
		<%
			}
		%>
	
</body>