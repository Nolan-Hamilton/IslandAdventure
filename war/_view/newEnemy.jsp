<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - New Enemy</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/newEnemy -->
	</head>

	<body>
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			New Enemy
		</div>
		
		<div id= "loginBox">
			
			<form action="${pageContext.servletContext.contextPath}/newEnemy" method="post">
				Enemy Name:<input type="text" name="name" size="30" value="${name}" />
				<br><br>
				Enemy Description:<input type="text" name="description" size="60" value="${description}" />
				<br><br>
				Enemy Health:<input type="text" name="health" size="5" value="${health}" />
				<br><br>
				Enemy Damage:<input type="text" name="damage" size="5" value="${damage}" />
				<br><br>
				Enemy Location(x):<input type="text" name="x" size="3" value="${locationX}" />
				<br><br>
				Enemy Location(y):<input type="text" name="y" size="3" value="${locationY}" />
				<br><br>
				Enemy Location(z):<input type="text" name="z" size="3" value="${locationZ}" />
				<br><br>
				<input class = "button" input type="Submit" name="submit" value="Create Enemy!">
			</form>
			
			<c:if test="${! empty errorMessage}">
				<br><div class="error">${errorMessage}</div>
			</c:if>
		</div>
		
		
		
		<div id= "loginBox">
			<form action="${pageContext.servletContext.contextPath}/newEnemy" method="post">
				<input class = "button" input type="Submit" name="return" value="Back to Game">	
			</form>
		</div>
		
	</body>
</html>
