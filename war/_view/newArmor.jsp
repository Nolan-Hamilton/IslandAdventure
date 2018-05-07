<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - New Armor</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/newItem -->
	</head>

	<body>
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			New Armor
		</div>
		
		<div id= "loginBox">
			
			<form action="${pageContext.servletContext.contextPath}/newArmor" method="post">
				Armor Name:<input type="text" name="name" size="30" value="${name}" />
				<br><br>
				Armor Description:<input type="text" name="description" size="60" value="${description}" />
				<br><br>
				Armor Location (x):<input type="text" name="x" size="3" value="${locationX}" />
				<br><br>
				Armor Location (y):<input type="text" name="y" size="3" value="${locationY}" />
				<br><br>
				Armor Location (z):<input type="text" name="z" size="3" value="${locationZ}" />
				<br><br>
				Armor Protection Value:<input type="text" name="armor" size="5" value="${armor}" />
				<br><br>
				<input class = "button" input type="Submit" name="submit" value="Create Armor!">
			</form>
			
			<c:if test="${! empty errorMessage}">
				<br><div class="error">${errorMessage}</div>
			</c:if>
		</div>
		
		
		
		<div id= "loginBox">
			<form action="${pageContext.servletContext.contextPath}/newArmor" method="post">
				<input class = "button" input type="Submit" name="return" value="Return to Game">	
			</form>
		</div>
		
	</body>
</html>
