<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>

<%@ include file="includes/default_include.jsp"%>
<script type="text/javascript">
$(function() {
	$.get(
		'getLineChart', 
		null, 
		function(data) {
			data.tooltip.formatter = eval('('+data.tooltip.formatter+')');
			var lineChart = new Highcharts.Chart(data);
		});
});
</script>
</head>
<body>
	<div id="container" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
</body>
</html>
