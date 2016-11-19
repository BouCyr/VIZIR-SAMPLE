<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>

</head>
<script src="/Chart.bundle.js"></script>
<body>
	<h1>Spring MVC Hello World Example</h1>

	<h2>
		Hello
		<c:out value='${name}' />
	</h2>

	<div style="padding: 30px; border: 1px solid black; width: 50%; resize: both; overflow: auto;">
		<canvas id="myChart2" width="100%" height="100%" />
	</div>
	<div style="padding: 30px; border: 1px solid black; width: 50%; resize: both; overflow: auto;">
		<canvas id="myChart" width="100%" height="100%" />
	</div>
	<script>

	function loadChart(url, chart){

		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var myArr = JSON.parse(this.responseText);
				myFunction(myArr, chart);
			}
		};
		

		xmlhttp.open("GET", url, true);
		xmlhttp.setRequestHeader("Accept","application/json");
		xmlhttp.setRequestHeader("Content-Type","application/json")
		xmlhttp.send();
	}

		function myFunction(arr, chart) {
			var ctx2 = document.getElementById(chart);
			var myChart2 = new Chart(ctx2, arr);
		}
		
		loadChart('/lineChart','myChart');
		loadChart('/lineChart','myChart2');
	</script>
</body>
</html>