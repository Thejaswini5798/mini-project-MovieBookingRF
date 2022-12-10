<%@include file="theaterheader.jsp" %>
<link rel="stylesheet" href="admin.css"/>
<body class="index">
		<h1>WELCOME TO THEATER PAGE</h1>
		<%
			//String message = (String) request.getAttribute("message");
			String userInSession = (String) session.getAttribute("user");
		
			if(null!=userInSession) {
		%>			
				<div class="successMsg"><%= userInSession%></div>
		<%		
			}
		%>
</body>