<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DashBoard</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<%-- <script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script> --%>
<script type="text/javascript">
var running = "<span class=\"label label-success\">running</span>";
var stopped = "<span class=\"label label-important\">stopped</span>";

	function startProcess(action, processName) {
		if(processName == "core.exe"){
			showMessage("正在启动core，请大约需要一分钟的时间，请稍后。。。");
		}
		var url = "/ProcessManagerDashboard/dashboard/" + action;
		$.get(url, {
			processName : processName
		}, function(data, textStatus) {
			if(data.processName == "tomcat"){
				if (data.status == "running") {
					$("#tomcatStatus").html(running);
				} else {
					$("#tomcatStatus").html(stopped);
				}
			}else if(data.processName == "palo"){
				if (data.status == "running") {
					$("#paloStatus").html(running);
				} else {
					$("#paloStatus").html(stopped);
				}
			}else if(data.processName == "httpd"){
				if (data.status == "running") {
					$("#httpdStatus").html(running);
				} else {
					$("#httpdStatus").html(stopped);
				}
			}else if(data.processName == "core"){
				if (data.status == "running") {
					$("#coreStatus").html(running);
				} else {
					$("#coreStatus").html(stopped);
				}
			}
			//init the show message on the addressCard
		}, "json");
	}
	
	function stopProcess(action, processName) {
		var url = "/ProcessManagerDashboard/dashboard/" + action;
		$.get(url, {
			processName : processName
		}, function(data, textStatus) {
			if(data.processName == "tomcat"){
				if (data.status == "stopped") {
					$("#tomcatStatus").html(stopped);
				} else {
					$("#tomcatStatus").html(running);
				}
			}else if(data.processName == "palo"){
				if (data.status == "stopped") {
					$("#paloStatus").html(stopped);
				} else {
					$("#paloStatus").html(running);
				}
			}else if(data.processName == "httpd"){
				if (data.status == "stopped") {
					$("#httpdStatus").html(stopped);
				} else {
					$("#httpdStatus").html(running);
				}
			}else if(data.processName == "core"){
				if (data.status == "stopped") {
					$("#coreStatus").html(stopped);
				} else {
					$("#coreStatus").html(running);
				}
			}
		}, "json");
	}
	$(document).ready(function(){
		var callAjax = function(){
			showMessage("刷新状态中...");
			clearMessage();
			$.ajax({
			url:"/ProcessManagerDashboard/dashboard/checkStatus",
			data:{process1:"javaw.exe",process2:"palo.exe",process3:"httpd.exe",process4:"core.exe"},
			success:function(response,textStatus,jqXHR){
				
					jQuery.each(response,function(index,item){
						if(item.processName=="javaw.exe"){
							if(item.status == "running"){
								$('#tomcatStatus').html(running);
							}else{
								$('#tomcatStatus').html(stopped);
							}
						}
						if(item.processName=="palo.exe"){
							if(item.status == "running"){
								$('#paloStatus').html(running);
							}else{
								$('#paloStatus').html(stopped);
							}
						}
						if(item.processName=="httpd.exe"){
							if(item.status == "running"){
								$('#httpdStatus').html(running);
							}else{
								$('#httpdStatus').html(stopped);
							}
						}
						if(item.processName=="core.exe"){
							if(item.status == "running"){
								$('#coreStatus').html(running);
							}else{
								$('#coreStatus').html(stopped);
							}
						}
					});
				
			},
			error:function(response, textStatus,jqXHR){
				$('#status').html("No Data available");
				return;
			}
		});
		};
		setInterval(callAjax,10000);
		callAjax();
		
		// add class to a link
		$("a").each(function(){
			if($(this).html()=="Start"){
				$(this).addClass("btn btn-success");
			}
			if($(this).html()=="Stop"){
				$(this).addClass("btn btn-danger");
			}
		});
		
	});	
function showMessage(message){
	$("#message").html(message);
	$("#message").show(1000);
}
	
function clearMessage(){
	$("#message").hide(5000);
}

function doValidate(){
	var url = "http://localhost:8080/ProcessManagerDashboard/validate/1";
	$.get(url, {
	}, function(data, textStatus) {
		alert(data.status);
	}, "json");
}
</script>
<style type="text/css">
#dashboard {
	text-align: center;
}

#dashboard #processA,#dashboard #processB,#dashboard #processC,#dashboard #processD
	{
	padding-top: 20px;
	padding-bottom: 20px;
}

#succesful {
	color: green;
}

#faild {
	color: red;
}
img{
	height:18px;
	weight:18px;
}
#container{
	position:relative;
	margin:20px;
	margin-left:120px;
	margin-right:150px;
	padding-left:20px;
	padding-top:20px;
	padding-right:10px;
}
/* Base class */
.bs-docs-example {
  position: relative;
  margin: 15px 0;
  padding: 39px 19px 14px;
  *padding-top: 19px;
  background-color: #fff;
  border: 1px solid #ddd;
  -webkit-border-radius: 4px;
  -moz-border-radius: 4px;
  border-radius: 4px;
}
img{
	width:0px;
	height:0px;
	margin-right:0px;
}
td{
	font-size:14px;
	font-weight: bold;
}
</style>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
</head>
<body>
<div>
	<button onclick="doValidate()"></button>
</div>
<div id="container" class="bs-docs-example">
	<table class="table table-hover table-striped">
	<caption><h1>PlanForce server Manager</h1></caption>
		<thead>
			<tr>
				<th>Process</th>
				<th>Status</th>
				<th width="30%">Action</th>
			</tr>
		</thead>
		<tbody>
			<tr class="">
				<td>
					Tomcat
					</td>
				<td id="tomcatStatus"></td>
				<td>
					 
				</td>
			</tr>
			<tr class="info">
				<td>Palo</td>
				<td id="paloStatus"></td>
				<td>
					<a href="#" onclick="startProcess('startPalo','palo.exe')" class="btn btn-success">Start</a> 
					/ 
					<a href="#" onclick="stopProcess('stopPalo','palo.exe')" class="btn btn-danger">Stop</a>
				</td>
			</tr>
			<tr class="">
				<td>Httpd</td>
				<td id="httpdStatus"></td>
				<td><a href="#" onclick="startProcess('startHttpd','httpd.exe')">Start</a> / <a href="#" onclick="stopProcess('stopHttpd','httpd.exe')">Stop</a></td>
			</tr>
			<tr class="info">
				<td>Core</td>
				<td id="coreStatus"></td>
				<td><a href="#" onclick="startProcess('startCore','core.exe')">Start</a> / <a href="#" onclick="stopProcess('stopCore','core.exe')">Stop</a></td>
			</tr>
		</tbody>
	</table>
	<div id="message" class="alert alert-success">
		
	</div>
</div>
<div style="font-size:9px;text-align: center;">
		Copyright © Enterprise Forecasting Solution Co.,Ltd, All Right Reserved 
</div>
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
</body>
</html>