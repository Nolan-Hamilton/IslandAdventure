<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - New Weapon</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/newWeapon -->
	</head>

	<body>
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			New Weapon
		</div>
		
		<div id= "loginBox">
			
			<form action="${pageContext.servletContext.contextPath}/newWeapon" method="post">
				Weapon Name:<input type="text" name="name" size="30" value="${name}" />
				<br><br>
				Weapon Description:<input type="text" name="description" size="60" value="${description}" />
				<br><br>
				Weapon Location (x):<input type="text" name="x" size="3" value="${locationX}" />
				<br><br>
				Weapon Location (y):<input type="text" name="y" size="3" value="${locationY}" />
				<br><br>
				Weapon Location (z):<input type="text" name="z" size="3" value="${locationZ}" />
				<br><br>
				Weapon Damage:<input type="text" name="damage" size="5" value="${damage}" />
				<br><br>
				<input class = "button" input type="Submit" name="submit" value="Create Weapon!">
			</form>
			
			<c:if test="${! empty errorMessage}">
				<br><div class="error">${errorMessage}</div>
			</c:if>
		</div>
		
		
		
		<div id= "loginBox">
			<form action="${pageContext.servletContext.contextPath}/newWeapon" method="post">
				<input class = "button" input type="Submit" name="return" value="Return to Game">	
			</form>
		</div>
		
	</body>
</html>
