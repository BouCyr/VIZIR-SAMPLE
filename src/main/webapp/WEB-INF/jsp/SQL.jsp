
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vz" uri="/WEB-INF/vizr.tld"%>
<html>

<head>
	<title>Create chart</title>
	<link rel="stylesheet" type="text/css" href="/style.css">
	<script src="/Chart.bundle.js"></script>
	<script src="/vizr.js"></script>
	<script src="/testchart.js"></script>
</head>




<body>
	<fieldset id="sqlFS">
		<legend>Request</legend>
		<form id="sqlForm" action="/sql" method="POST">
			<textarea name="sql" rows="8" cols="90">
			<c:out value='${SQL}' />
		</textarea>
			<input type="submit" value="GO" /><br />


			<c:out value='${nbRows}' />
			rows.<br />
		</form>
	</fieldset>

	<fieldset id="chart">
		<legend>Chart</legend>
		<form:form method="post" modelAttribute="chart" action="/saveChart">
			<form:input type="hidden" name="sql" path="sql" />
			<table>
				<tr>
					<th>Column</th>
					<c:forEach items='${colNames}' var="name">
						<th><c:out value='${name}' /></th>
					</c:forEach>
				</tr>

				<tr>
					<th>X Axis</th>
					<c:forEach items='${colNames}' var="name">
						<td><form:radiobutton onclick="xChanged(this);" path="axisX"
								value="${name}" /><br></td>
					</c:forEach>
				</tr>

				<tr>
					<th>Y Axis</th>
					<c:forEach items='${colNames}' var="name">
						<td><form:radiobutton onclick="yChanged(this);" path="axisY"
								value="${name}" /><br></td>
					</c:forEach>
				</tr>

				<c:forEach items='${lines}' var="line">
					<tr>
						<td />
						<c:forEach items='${line}' var="cell">
							<td><c:out value='${cell}' /></td>
						</c:forEach>
					</tr>

				</c:forEach>
			</table>

			<form:select id="type" path="type" onchange="typeChanged();">
				<option value="bar">Bars</option>
				<option value="line">Lines</option>
				<option value="pie">Pie</option>
			</form:select>
			<br />

		</form:form>
	</fieldset>


	<div id="charts"">
		<div>
			<fieldSet id="fs">
				<canvas id="test" />
			</fieldSet>
		</div>

	</div>
</body>
</html>