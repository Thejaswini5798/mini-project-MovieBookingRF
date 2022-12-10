<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Shows" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="edit">

<%@include file="showsheader.jsp" %>		
		<h1>Edit Shows</h1>
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
			Object idParam = request.getParameter("showid");
			int showid = -1;
			if(null!=idParam) {
				showid = Integer.parseInt(idParam.toString());
				out.println("screenId parameter passed is : " + showid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Shows shows = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			 shows = (Shows) request.getAttribute("shows");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null== shows) {
				shows = (Shows) session.getAttribute("shows");	
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
			
			if(null== shows) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="ShowsUpdate" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
							<tr>
								<td>ShowsId</td>
								<td>
									${shows.showsid}
									<input type="hidden" name="showsid" value="${shows.showsid}"/>
								</td>
							</tr>	
							
							<tr>
						<td>ShowsCode</td>
						<td>
							<input type="text" id="showscode" name="showscode" size=20
								placeholder="shows code"value="${shows.showscode}"
								
								required />
						</td>
					</tr>
						<tr>
						<td>ShowTime</td>
						<td>
							<input type="time" id="showtime" name="showtime" size=20
								placeholder="show time"value="${shows.showtime}"
								
								required />
						</td>
					</tr>
					<tr>
						<td>ShowsDate</td>
						<td>
							<input type="date" id="showdate" name="showdate" size=20
								placeholder="shows date"value="${shows.showdate}"
								
								required />
						</td>
					</tr>
					
					
					<tr>
						<td>ScreenId</td>
						
						<td>
							
							<input type="number" id="screenid" name="screenid" 
								min="1" max="100" step="1" size="5"
								placeholder=" Screenid"value="${shows.screenid}"
								
								required/>
							
						</td>
						
					</tr>
						<tr>
						<td>MovieId</td>
						
						<td>
							
							<input type="number" id="movieid" name="movieid" 
								min="1" max="100" step="1" size="5"
								placeholder=" Movie id"value="${shows. movie_id }"
							
								required/>
							
						</td>
						
					</tr>
					
					<tr>
						<td>TheatreName</td>
						<td>
							<input type="text" id="theatrename" name="theatrename" size=30
								placeholder="theatre name"value="${shows.theatrename}"
							
								required />
						</td>
					</tr>
					
						<tr>
						<td>MovieName</td>
						<td>
							<input type="text" id="moviename" name="moviename" size=30
								placeholder="movie name"value="${shows.movie_name}"
								
								required />
						</td>
					</tr>
					
					<tr>
						<td>Seats_remaining</td>
						
						<td>
							
							<input type="number" id="seatsremaining" name="seatsremaining" 
								min="1" max="500" step="1" size="5"
								placeholder="seats remaining"value="${shows.seats_remaining}"
								
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