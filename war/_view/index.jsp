<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Island Adventure</title>
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
	</head>

	<body>
		<div id= "titleText">
			<h1>Island Adventure</h1>
		</div>
		
		<c:if test="${! empty user}">
			<h2>${user} is now playing</h2>
		</c:if>
		<c:if test="${ empty user}">
			<h2>You are playing with a temporary Account.</h2>
		</c:if>
		
		
		
		<div id= "outputBox">
			Welcome to Island Adventure!
			<br />
			<p>
			${response}
			</p>
		</div>
		
		<div id= "variablesBox">
			Score: ${score} <br />
			Health: ${health} <br />
			Stamina: ${stamina} <br />
			Time: ${time} <br />
			Location Coordinates: [${locationX}, ${locationY}, ${locationZ}] <br />
			Inventory:
			<ul>
    			<c:forEach items="${map}" var="itemName">
        			<li> ${itemName.key.name} = ${itemName.value}</li>
    			</c:forEach>
			</ul>
			Armor: ${armor} <br />
			Weapon: ${weapon} <br />
			<br /> <br /> <br />
			<form action="${pageContext.servletContext.contextPath}/index" method="post">
				<input type="Submit" name="expandabilityMenu" value="Open Expandability Menu">
			</form>
		</div>
		
		<div id= "inputBox">
			What Next?
			<form action="${pageContext.servletContext.contextPath}/index" method="post">
				<input type="text" name="action" size="12" value="${action}" autofocus="autofocus" />
			</form>
		</div>
		
		<!-- This is the stuff for the direction commands -->
		<div id="inputBox">
			<form action="${pageContext.servletContext.contextPath}/index" method="post" style="text-align: center;">
					<input type="Submit" name="north" value="North"><br>
					<input type="Submit" name="west" value="West">
					<input type="Submit" name="east" value="East"><br>
					<input type="Submit" name="south" value="South">
				</form>
		</div>
		
	</body>
</html>
