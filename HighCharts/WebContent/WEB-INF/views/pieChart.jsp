<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>

<%@ include file="includes/default_include.jsp"%>
<script type="text/javascript">
var lineChart;
var localDep = 1;
$(function() {
	drawChart();
});
function drawChart(){
	$.get(
			'getPieChart', 
			{depth:localDep}, 
			function(data) {
				data.plotOptions.pie.dataLabels.formatter = eval('('+data.plotOptions.pie.dataLabels.formatter+')');
				data.plotOptions.pie.events={
						click:function(){
							localDep++;
							drawChart();
						}
				};
				lineChart = new Highcharts.Chart(data);
			});
}

function redrawSecondLevelChart(){
	$.get(
			'getSecondLevelPieChart', 
			null, 
			function(data) {
				data.plotOptions.pie.dataLabels.formatter = eval('('+data.plotOptions.pie.dataLabels.formatter+')');
				lineChart = new Highcharts.Chart(data);
			});
}
</script>
</head>
<body>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>
