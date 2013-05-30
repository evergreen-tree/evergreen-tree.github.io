<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dear.document.searcher.SearchResult"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/include/header.jsp" %>
<div id="infowrap">
<div id="infobox" class="margin-left">
<div>
	<form action="<%=request.getContextPath() %>/SearchAction/doSearch.do" method="post">
		<input type="text" value="${search}" name="search" id="search" />
		<input type="submit">
	</form>
</div>
<hr />
<%
    List<SearchResult> searchResults = ( List<SearchResult>)request.getAttribute("searchResults");
	if(searchResults!=null && searchResults.size() > 0){
	request.setAttribute("size", searchResults.size());
	for(SearchResult searchResult : searchResults){
%>
<div style="border-bottom:1px dotted grey;margin-bottom:2px;padding:5px;margin-top:3px;">
	<div>
		title: <a href="<%=request.getContextPath() %>/ReadAction/readFile.do?id=<%=searchResult.getId() %>">
					<%=searchResult.getFileName() %>
			   </a>
	</div>
	<div> 
		content:<%=searchResult.getContents() %>
	</div>
</div>
<%
		}
%>
<div style="text-align: right;padding-left:30px;">
	当前第${curPage}页 &nbsp;
	<a href="<%=request.getContextPath() %>/SearchAction/doSearch.do?targetPage=${curPage - 1}" style='display:${curPage <= 1?"none":""}'>上一页</a>
	<a href="<%=request.getContextPath() %>/SearchAction/doSearch.do?targetPage=${curPage + 1}" style='display:${size < 5?"none":""}'>下一页</a>
</div>
<%
	}else{
%>
No Result get!
<%
	}
%>
<div>

</div>
</div>
</div>
<%@include file="/WEB-INF/jsp/include/slideAndFooter.jsp" %>