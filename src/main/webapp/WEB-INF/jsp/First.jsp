
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="vz" uri="/WEB-INF/vizr.tld"%>
<html>

<head>

</head>
<script src="/Chart.bundle.js"></script>
<script src="/vizr.js"></script>
<body>
	<h1>Spring MVC Hello World Example</h1>

	<h2>
		Hello
		<c:out value='${name}' />
	</h2>

	<div id="charts"
		style="display: flex; flex-direction: row; flex-wrap: wrap; justify-content: center; align-content: flex-start; align-items: center;">
		<div style="width: 100%">
			<div
				style="border: 1px solid gray; margin: 5px; width: auto; border-radius: 10px;">
				<vz:line />
			</div>
		</div>
		<div style="width: 50%">
			<div
				style="border: 1px solid gray; margin: 5px; width: auto; border-radius: 10px;">
				<vz:line />
			</div>
		</div>
		<div style="width: 50%">
			<div
				style="border: 1px solid gray; margin: 5px; width: auto; border-radius: 10px;">
				<vz:line />
			</div>
		</div>
	</div>
</body>
</html>