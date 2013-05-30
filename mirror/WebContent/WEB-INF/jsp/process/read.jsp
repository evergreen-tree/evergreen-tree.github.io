<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/header.jsp" %>
<div style="padding-top:100px;border-bottom:2px solid;margin-bottom:15px;padding-bottom:3px;">
	<a href="<%=request.getContextPath() %>/DownLoadAction/downLoadFile.do?fileId=${history.ID}">
		点击我下载
	</a>
</div>
<div>
	以下是原文:<br/><br/>
	${history.fullText}
</div>
<%@include file="/WEB-INF/jsp/include/slideAndFooter.jsp" %>