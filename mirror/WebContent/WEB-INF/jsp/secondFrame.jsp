<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0039)http://benblogged.com/dev/ninja_admin/# -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Loves Mirror</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/secondFrame/main.css">
<style type="text/css"></style>

<style type="text/css">
#yddContainer {
	display: block;
	font-family: Microsoft YaHei;
	position: relative;
	width: 100%;
	height: 100%;
	top: -4px;
	left: -4px;
	font-size: 12px;
	border: 1px solid
}

#yddTop {
	display: block;
	height: 22px
}

#yddTopBorderlr {
	display: block;
	position: static;
	height: 17px;
	padding: 2px 28px;
	line-height: 17px;
	font-size: 12px;
	color: #5079bb;
	font-weight: bold;
	border-style: none solid;
	border-width: 1px
}

#yddTopBorderlr .ydd-sp {
	position: absolute;
	top: 2px;
	height: 0;
	overflow: hidden
}

.ydd-icon {
	left: 5px;
	width: 17px;
	padding: 0px 0px 0px 0px;
	padding-top: 17px;
	background-position: -16px -44px
}

.ydd-close {
	right: 5px;
	width: 16px;
	padding-top: 16px;
	background-position: left -44px
}

#yddKeyTitle {
	float: left;
	text-decoration: none
}

#yddMiddle {
	display: block;
	margin-bottom: 10px
}

.ydd-tabs {
	display: block;
	margin: 5px 0;
	padding: 0 5px;
	height: 18px;
	border-bottom: 1px solid
}

.ydd-tab {
	display: block;
	float: left;
	height: 18px;
	margin: 0 5px -1px 0;
	padding: 0 4px;
	line-height: 18px;
	border: 1px solid;
	border-bottom: none
}

.ydd-trans-container {
	display: block;
	line-height: 160%
}

.ydd-trans-container a {
	text-decoration: none;
}

#yddBottom {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 22px;
	line-height: 22px;
	overflow: hidden;
	background-position: left -22px
}

.ydd-padding010 {
	padding: 0 10px
}

#yddWrapper {
	color: #252525;
	z-index: 10001;
	background:
		url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ab20.png);
}

#yddContainer {
	background: #fff;
	border-color: #4b7598
}

#yddTopBorderlr {
	border-color: #f0f8fc
}

#yddWrapper .ydd-sp {
	background-image:
		url(chrome-extension://eopjamdnofihpioajgfdikhhbobonhbb/ydd-sprite.png)
}

#yddWrapper a,#yddWrapper a:hover,#yddWrapper a:visited {
	color: #50799b
}

#yddWrapper .ydd-tabs {
	color: #959595
}

.ydd-tabs,.ydd-tab {
	background: #fff;
	border-color: #d5e7f3
}

#yddBottom {
	color: #363636
}

#yddWrapper {
	min-width: 250px;
	max-width: 400px;
}
</style>
</head>

<body>

	<div class="wrapper">
	
		<h1 class="logo">Grom's Mirror</h1>
		<p class="txt_right">Logged in as <strong>Mirror </strong>  <span class="v_line"> | </span> <a href="./Ninja Admin_files/Ninja Admin.htm"> Logout</a></p>
	
	<!-- Navigation -->
	
		<div class="navigation">
				<ul>
					<li><a href="./Ninja Admin_files/Ninja Admin.htm">WRITE</a></li>
					<li><a href="./Ninja Admin_files/Ninja Admin.htm" class="active">MANAGE</a></li>
					<li><a href="http://benblogged.com/dev/ninja_admin/#l">SETTINGS</a></li>
					<li><a href="./Ninja Admin_files/Ninja Admin.htm">USERS</a></li>
				</ul>
			
				<div id="searchform">
					<form method="get" action="">
					<input type="text" value="find something good..." class="search_box" name="search" onclick="this.value=&#39;&#39;;">
					<input type="submit" class="search_btn" value="SEARCH">
					</form>
				</div>
			
		</div>
		
		<div class="clear"></div>
	
	
		<div class="content">
		
	<!-- Intro -->
		
				<div class="in author">
					<h2>Lets Enter The Dragon</h2>
					<p>Author <a href="./Ninja Admin_files/Ninja Admin.htm">Bruce Lee</a> | created 10-14-08</p>
				</div>
			
				<div class="line"></div>
				
	<!-- Checks -->
	
			<div class="check_main">
					
				<div class="check">
					<div class="good">
						<img src="./Ninja Admin_files/check.gif" alt="check" class="icon">Nice work <strong>Ninja Admin!</strong>
					</div>
				</div>
				<div class="check">
					<div class="bad"><img src="./Ninja Admin_files/x.gif" alt="check" class="icon">You need more training, please <a href="./Ninja Admin_files/Ninja Admin.htm">try again</a>.</div>
				</div>
				
			</div>
			
	<!-- Form -->
			
				<div class="in forms">
					<form id="form1" name="form1" method="post" action="">
	
      				<p><strong>TITLE</strong><br>
					<input type="text" name="name" class="box"></p>
					 
	  				<p><strong>AUTHOR</strong><br>
							<select name="date_end" class="box2">
        					<option selected="selected"> Bruce Lee</option>
							<option>Jackie Chan</option>
        					<option>John Claude Van Damme</option>
        					<option>Ben Johnson</option>
					  </select></p>
					
	  				<p><strong>STORY</strong><br>
					<textarea name="mes" rows="5" cols="30" class="box"></textarea></p> 

					<p><input name="submit" type="submit" id="submit" tabindex="5" class="com_btn" value="UPDATE"></p>
					</form>
			
				</div>
				
				
				
	
	
	<div class="in">			
		<table width="850" border="0" cellspacing="0" cellpadding="10" class="table_main">
		  <tbody><tr style="background-color:#d9d8d8; font-size:14px;">
			<td width="179"><strong>USER</strong></td>
			<td width="184"><strong>EMAIL</strong></td>
			<td width="273"><strong>SOMETHING</strong></td>
			<td width="132"><strong>DO IT</strong></td>
		  </tr>
		  <tr class="gray">
			<td>Bruce Lee </td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">bruce@kungfu.com</a></td>
			<td>Loriem ipsum dolor sit amet </td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">EDIT  </a><span class="v_line">| </span> <a href="./Ninja Admin_files/Ninja Admin.htm" class="delete">DELETE </a></td>
		  </tr>
		  <tr>
			<td>Jackie Chan</td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">thechan@yahoo.com</a></td>
			<td>Loriem ipsum dolor sit amet </td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">EDIT  </a><span class="v_line">| </span> <a href="./Ninja Admin_files/Ninja Admin.htm" class="delete">DELETE </a></td>
		  </tr>
		  <tr class="gray">
			<td>John Claude Van Damme</td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">vandamme@gmail.com</a></td>
			<td>Loriem ipsum dolor sit amet </td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">EDIT  </a><span class="v_line">| </span> <a href="./Ninja Admin_files/Ninja Admin.htm" class="delete">DELETE </a></td>
		  </tr>
		   <tr>
			<td>Ben Johnson </td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">ben@kungu.com</a></td>
			<td>Loriem ipsum dolor sit amet </td>
			<td><a href="./Ninja Admin_files/Ninja Admin.htm">EDIT  </a><span class="v_line">| </span> <a href="./Ninja Admin_files/Ninja Admin.htm" class="delete">DELETE </a></td>
		  </tr>
		</tbody></table>
						
	</div>
		
		</div>
		
		
		<p class="footer"><a href="./Ninja Admin_files/Ninja Admin.htm">ADVANCED  SEARCH</a> <span class="v_line"> |</span> <a href="./Ninja Admin_files/Ninja Admin.htm">LOGOUT</a></p>
		
		
	</div>


</body></html>