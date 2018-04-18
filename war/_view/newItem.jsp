<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure - New Item</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		<!-- Enter this into URL: http://localhost:8081/IslandAdventure/newItem -->
	</head>

	<body>
		<div id= "titleText">
			Island Adventure
		</div>
		
		<div id= "titleText">
			New Item
		</div>
		
		<div id= "inputBox">
			
			<form action="${pageContext.servletContext.contextPath}/newItem" method="post">
				Item Name:<input type="text" name="name" size="30" value="${name}" />
				<br><br>
				Item Description:<input type="text" name="description" size="60" value="${description}" />
				<br><br>
				Item Location (x):<input type="text" name="x" size="3" value="${locationX}" />
				<br><br>
				Item Location (y):<input type="text" name="y" size="3" value="${locationY}" />
				<br><br>
				Item Location (z):<input type="text" name="z" size="3" value="${locationZ}" />
				<br><br>
				<input type="Submit" name="submit" value="Create Item!">
			</form>
		</div>
		
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div id= "inputBox">
			<form action="${pageContext.servletContext.contextPath}/newItem" method="post">
				<input type="Submit" name="return" value="Return to Game">	
			</form>
		</div>
		
	</body>
</html>
