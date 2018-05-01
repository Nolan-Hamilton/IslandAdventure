<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - Login</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/login -->
	</head>

	<body>
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			Login Page
		</div>
		
		<div id= "loginBox">
			
			<form action="${pageContext.servletContext.contextPath}/login" method="post">
				Username:<input type="text" name="user" size="20" value="${user}" />
				<br><br>
				Password:<input type="password" name="pass" size="20" value="${pass}" />
				<br><br>
				<input type="Submit" name="submit" value="Login!">
			</form>
			
			<c:if test="${! empty errorMessage}">
				<br><div class="error">${errorMessage}</div>
			</c:if>
		</div>
		
		
		
		<div id= "loginBox">
			<form action="${pageContext.servletContext.contextPath}/login" method="post">
				<input type="Submit" name="newAcc" value="Create New Account">	
				<input type="Submit" name="justPlay" value="Just Start Playing">
			</form>
			<p>Warning:
			You will not be able to save if you do not login with an account!</p>
		</div>
		
		
	</body>
</html>
