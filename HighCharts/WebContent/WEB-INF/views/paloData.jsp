<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<div id="dbName" style="color:red;">
	All the database listed : <br>
	<c:forEach var="dbName" items="${dbNameList}">
		${dbName} <br>
	</c:forEach>
</div>

<div>
	<h3>all the cubeviews</h3>
	<c:forEach items="${cubeViews}" var="cubeView">
		${cubeView.id} | ${cubeView.name} | RawDefinition : ${cubeView.rawDefinition} | <br/>
	</c:forEach>
</div>
<hr/>
<div id="cubeName" style="color:blue;">
	Cube Name: <br>
		<c:forEach var="cubeName" items="${cubeNameList}">
			${cubeName}<br/>
		</c:forEach>
</div>
<hr/>
<div id="dimensionName" style="color:purple;">
	Dimension Name: <br>
	<c:forEach var="dimensionName" items="${dimensionNameList}">
		${dimensionName} <br/>
	</c:forEach>
</div>
</body>
</html>