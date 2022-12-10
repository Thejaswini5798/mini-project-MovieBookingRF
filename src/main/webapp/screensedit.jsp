<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Screens" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="edit">

<%@include file="screensheader.jsp" %>		
		<h1>Edit Screens</h1>
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
			Object idParam = request.getParameter("screenid");
			int screenid = -1;
			if(null!=idParam) {
				screenid = Integer.parseInt(idParam.toString());
				out.println("screenId parameter passed is : " + screenid);
			}
			/* [17Oct2022] - Bug Fix - END */
			
			Screens screens = null;
			
			/* 1. See if the object is available in request scope */
			// from the /Edit (EditServlet)
			 screens = (Screens) request.getAttribute("screens");
			
			/* from the view.jsp page, see if it is available in Session scope */
			if(null== screens) {
				screens = (Screens) session.getAttribute("screens");	
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
			
			if(null== screens) {
		%>
				<div class="errorMsg">No object available to edit!</div>
		<%		
			} else {
		%>	
				<form id="updateForm" name="UpdateForm" action="ScreensUpdate" method="post">
					<table border="1">
						<thead>
							<tr>
								<td>Field</td>
								<td>Value</td>
							</tr>				
						</thead>
						<tbody>	
							<tr>
								<td>ScreenId</td>
								<td>
									${screens.screenid}
									<input type="hidden" name="screenid" value="${screens.screenid}"/>
								</td>
							</tr>				
							<tr>
								<td>ScreenCode</td>
								<td>
									<input type="text" id="screencode" name="screencode" size=10 
										placeholder="code" value="${screens.screencode}"
										required />
								</td>
							</tr>
							<tr>
								<td>NoOfGoldseats</td>
								<td>
									<input type="number" id="noofgoldseats" name="noofgoldseats" 
										min="1" max="100" step="1" size="5"
										placeholder="no_of_screens" value="${screens.no_of_goldseats}"
										required/>
								</td>
							<tr>
								<td>NoOfSilverseats </td>
								<td>
									<input type="number" id="noofsilverseats" name="noofsilverseats" 
										min="1" max="100" step="1" size="5"
										placeholder="no_of_screens" value="${screens.no_of_silverseats}"
										required/>
								</td>
							</tr>
							<tr>
								<td>TheatreId</td>
								<td>
									<input type="number" id="theatreid" name="theatreid" 
										min="1" max="100" step="1" size="5"
										placeholder="theatreid" value="${screens.theatreid}"
										required/>
								</td>
							</tr>
						<tr>
								<td>TheatreName</td>
								<td>
									<input type="text" id="theatrename" name="theatrename" size=30 
										placeholder="Name" value="${screens.theatrename}"
										required />
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