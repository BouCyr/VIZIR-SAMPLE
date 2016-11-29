<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vz" uri="/WEB-INF/vizr.tld"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Existing charts</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
</head>
<script src="/Chart.bundle.js"></script>
<script src="/vizr.js"></script>

<script>
function displayChart(){
	var selectName = document.getElementById('chartName').value;
	
	loadChart('/vizr/chart/'+selectName, 'test');
	
}
</script>
<body>

	<div id="select">
	
	<form:select id="chartName" path="chartName" items="${chartNames}" onchange="displayChart()"/>
	
	</div>
	<div id="charts"
		style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: center; align-content: flex-start; align-items: center;">
		<div style="flex-grow: 10;">
			<fieldSet style="height: 400px;">
				<vz:line name="test" load="false" />
			</fieldSet>
		</div>
	</div>

</body>

<script>
displayChart();
</script>
</html>