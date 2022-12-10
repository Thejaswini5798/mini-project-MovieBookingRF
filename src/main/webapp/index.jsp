
<link rel="stylesheet" href="admin.css"/>
<body class="index">
<%@include file="header.jsp" %>
		<h1>WELCOME TO ADMIN PAGE</h1>
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