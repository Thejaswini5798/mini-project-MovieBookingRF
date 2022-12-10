<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Theatres" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="view1">

<%@include file="theaterheader.jsp" %>		
		<h1>Edit Theatres</h1>
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
			Object idParam = request.getParameter("theatreid");
			int theatreid = -1;
			if(null!=idParam) {
				theatreid = Integer.parseInt(idParam.toString());
				out.println("theatreId parameter passed is : " + theatreid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Theatres theatres = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			 theatres = (Theatres) request.getAttribute("theatres");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null== theatres) {
				 theatres = (Theatres) session.getAttribute("theatres");	
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
			
			if(null== theatres) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="TheatresUpdate" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
							<tr>
								<td>TheatreId</td>
								<td>
									${theatres.theatreid}
									<input type="hidden" name="theatreid" value="${theatres.theatreid}"/>
								</td>
							</tr>				
							<tr>
								<td>Theatre_code</td>
								<td>
									<input type="text" id="theatrecode" name="theatrecode" size=10 
										placeholder="code" value="${theatres.theatre_code}"
										required />
								</td>
							</tr>
							<tr>
								<td>TheatreName</td>
								<td>
									<input type="text" id="theatrename" name="theatrename" size=30 
										placeholder="Name" value="${theatres.theatrename}"
										required />
								</td>
							</tr>
							
							
							
							
							<tr>
								<td>no_of_screens</td>
								<td>
									<input type="number" id="noofscreens" name="noofscreens" 
										min="1" max="100" step="1" size="5"
										placeholder="no_of_screens" value="${theatres.no_of_screens}"
										required/>
								</td>
							</tr>
							
						
					
							<tr>
								<td>Area</td>
								<td>
									<input type="text" id="area" name="area" size=10 
										placeholder="Area" 
										value="${theatres.area}" required/>
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