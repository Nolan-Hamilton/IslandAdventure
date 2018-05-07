<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - New Account</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/login -->
	</head>

	<body>
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			New Account
		</div>
		
		<div id= "loginBox">
			
			<form action="${pageContext.servletContext.contextPath}/newAccount" method="post">
				Username:<input type="text" name="user" size="20" value="${user}" />
				<br><br>
				Password:<input type="password" name="pass" size="20" value="${pass}" />
				<br><br>
				Confirm Password:<input type="password" name="pass2" size="20" value="${pass2}" />
				<br><br>
				<input class = "button" input type="Submit" name="submit" value="Create Account!">
			</form>
			
			<c:if test="${! empty errorMessage}">
				<br><div class="error">${errorMessage}</div>
			</c:if>
		</div>
		

		
		<div id= "loginBox">
			<form action="${pageContext.servletContext.contextPath}/newAccount" method="post">
				<input class = "button" input type="Submit" name="back2Login" value="Back to Login">	
			</form>
		</div>
		
		
	</body>
</html>
