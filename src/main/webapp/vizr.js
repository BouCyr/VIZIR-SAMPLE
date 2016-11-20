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