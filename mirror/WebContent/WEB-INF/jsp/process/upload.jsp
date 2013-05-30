<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="message" style="border-bottom: 1px solid grey;">
		<%=request.getAttribute("message") %>
	</div>

	请选择一个文件：
	<form action="<%=request.getContextPath() %>/UploadAction/doUpload.do" method="post" 
		enctype="multipart/form-data">
		<input type="file" name="file" id="file"/>
		<input type="submit" value="上传"/>
	</form>
</body>
</html>