<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - Expandability Menu</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/expandabilityMenu -->
	</head>

	<body>
	
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			Expandability Menu
		</div>
		
		<div id= "inputBox">
			<form action="${pageContext.servletContext.contextPath}/expandabilityMenu" method="post">
				<input type="Submit" name="newEnemy" value="Create New Enemy">	
				<input type="Submit" name="newArmor" value="Create New Armor">
				<input type="Submit" name="newWeapon" value="Create New Weapon">
				<input type="Submit" name="return" value="Return to Game">
			</form>
		</div>	
		
	</body>
</html>
