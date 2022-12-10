<%@ page import="java.util.List, java.util.ArrayList,com.tutorials.jdbc.bo.Screens" %>
<link rel="stylesheet" href="adminpage.css"/>
<body class="list">

<%@include file="screensheader.jsp" %>
		<h1>List All Screens</h1>
		<%
			List<Screens> screensList = new ArrayList<>();
			Object obj = request.getAttribute("screensList");
			if(null!=obj) {
				screensList = (List<Screens>) obj;
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
		Total list of screens is : <%= screensList.size() %>
		<%
			if(screensList.size()<=0) {
		%>
				<div class=errorMsg>No records are available to display.</div>
		<%
			} else {
		%>
		<h3>Screens List</h3>	
		<table  border="1" >
			<thead>
				<tr>
					<td>ScreenId</td>
					<td>ScreenCode</td>
					<td>NoOfGoldseats</td>
					<td>NoOfSilverseats</td>
					<td>TheatreId</td>
					<td>TheatreName</td>
					
				</tr>				
			</thead>
			<tbody>	
		<%				
			for(Screens screens : screensList)
			{
				out.println("<tr>");
				out.println("<td class='center'>" + screens.getScreenid() + " " + 
				"<a href='ScreensView?id=" + screens.getScreenid() + "'>V " + "</a> | " +
				"<a href='ScreensEdit?id=" + screens.getScreenid() + "'>E</a> | " +
				"<a href='ScreensDelete?id=" + screens.getScreenid() + "'>D</a>" +
				"</td>");
				out.println("<td>" + screens.getScreencode() + "</td>");
				out.println("<td class='center'>" + screens.getNo_of_goldseats() + "</td>");
				out.println("<td class='center'>" + screens.getNo_of_silverseats() + "</td>");
				out.println("<td>" + screens.getTheatreid() + "</td>");
				out.println("<td>" + screens.getTheatrename() + "</td>");
				out.println("</tr>");
			}			
		%>		
			</tbody>
		</table>
		<%
			}
		%>
	
</body>