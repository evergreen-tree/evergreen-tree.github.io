<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Dashboard - Admin Template</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/theme.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/theme1.css" />
<script type="text/javascript" src="http://lib.sinaapp.com/js/jquery/1.7.2/jquery.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/jquery.upload.js"></script>
<!--[if IE]>
<link rel="stylesheet" type="text/css" href="css/ie-sucks.css" />
<![endif]-->
</head>

<body>
	<div id="container">
    	<div id="header">
        	<h2>专用</h2>
    <div id="topmenu">
            	<ul>
                	<li class="current"><a href="<%=request.getContextPath() %>/LoginAction/doLogin.do">功能面板</a></li>
                    <li><a href="<%=request.getContextPath() %>/SearchAction/gotoSearch.do">查询</a></li>
                	<li><a href="users.html">Users</a></li>
                    <li><a href="#">Manage</a></li>
                    <li><a href="#">CMS</a></li>
                    <li><a href="#">Statistics</a></li>
                    <li><a href="#">Settings</a></li>
              </ul>
          </div>
      </div>
        <div id="top-panel">
            <div id="panel">
                <ul>
                    <li><a href="#" class="report">Sales Report</a></li>
                    <li><a href="#" class="report_seo">SEO Report</a></li>
                    <li><a href="#" class="search">Search</a></li>
                    <li><a href="#" class="feed">RSS Feed</a></li>
                </ul>
            </div>
      </div>