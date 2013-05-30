<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>

<%@ include file="includes/default_include.jsp"%>
<script type="text/javascript">
var lineChart;
var localDepth = 1;
$(function() {
	drawChart();
});

function drawChart(){
	$.get(
			'getColumnChart', 
			{depth : localDepth}, 
			function(data) {
				data.tooltip.formatter = eval('('+data.tooltip.formatter+')');
				data.plotOptions={
						series: {
		                    cursor: 'pointer',
		                    point: {
		                        events: {
		                            click: function() {
		                            	localDepth ++;
		                            	drawChart();
		                            }
		                        }
		                    },
		                    marker: {
		                        lineWidth: 1
		                    }
						}
				};
				lineChart = new Highcharts.Chart(data);
			});
}

function redrawSecondLevelChart(){
	$.get(
			'getSecondLevelColumnChart', 
			null, 
			function(data) {
				data.tooltip.formatter = eval('('+data.tooltip.formatter+')');
				lineChart = new Highcharts.Chart(data);
			});
}
</script>
</head>
<body>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>
