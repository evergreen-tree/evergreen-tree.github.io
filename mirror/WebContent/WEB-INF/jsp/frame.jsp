<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.dear.po.DocumentHistory"%>
<%@page import="java.util.List"%>
<%@include file="/WEB-INF/jsp/include/header.jsp" %>
<SCRIPT LANGUAGE="JavaScript">
function doUpload() {
	$.upload({
			url: '<%=request.getContextPath() %>/UploadAction/doAjaxUpload.do', 
			fileName: 'filedata', 
			params: {name: 'pxblog'},
			dataType: 'json',
			onSend: function() {
					return true;
			},
			onComplate: function(data) {
				if(data.status == "true"){
					$(".youhave").html("文件"+data.fileName+"上传成功！");
				}else{
					$(".youhave").html("文件"+data.fileName+"上传出错！ due to :" + data.message);
				}
			}
	});
}
</SCRIPT>
        <div id="wrapper">
            <div id="content">
       			<div id="rightnow">
                    <h3 class="reallynow">
                        <span>功能</span>
                        <a href="#" class="add" onclick="doUpload()">新增文件</a>
                        <a href="#" class="app_add" >搜索文件</a>
                        <br />
                    </h3>
				    <p class="youhave">
				    	你现在一共有${allDocumentHistoryCount}份简历！
                    </p>
			  </div>
              <div id="infowrap">
                  <div id="infobox" class="margin-left">
                  <!-- 
                    <h3>新增一个文件</h3> 
                    <iframe src="<%=request.getContextPath() %>/UploadAction/gotoUpload.do" width="100%" height="100%">
                    	
                    </iframe>
                  -->
                  </div>
                  <div id="infobox">
                    <h3>最新添加的文件</h3>
                    <table>
						<thead>
							<tr>
                            	<th>文档名</th>
                                <th>所属用户</th>
                                <th>联系方式</th>
                            </tr>
						</thead>
						<tbody>
							<%
								List<DocumentHistory> list = (List<DocumentHistory>)request.getAttribute("historyList");
								if(list != null){
									for(DocumentHistory history : list){
							%>
							<tr>
                            	<td><a href="<%=request.getContextPath() %>/ReadAction/readFile.do?id=<%=history.getID() %>"><%=history.getName() %></a></td>
                                <td>TBD</td>
                                <td>TBD</td>
                            </tr>
							<%
									}
								}
							%>
						</tbody>
					</table>    
					<div style="text-align: right;padding-right:25px;">
						总共${DocumentHistorPageCount}页&nbsp; 
						- &nbsp;当前第${curPage}页&nbsp; 
						- &nbsp;<a href="<%=request.getContextPath() %>/LoginAction/alreadyLogin.do?targetPage=${curPage - 1}" style='display:${curPage <= 1?"none":""}'>上一页</a>&nbsp; 
						- &nbsp;<a href="<%=request.getContextPath() %>/LoginAction/alreadyLogin.do?targetPage=${curPage + 1}" style='display:${curPage >= DocumentHistorPageCount?"none":""}'>下一页</a>
					</div>        
                  </div>
              </div>
            </div>
            <div id="sidebar">
  				<ul>
                	<li><h3><a href="#" class="house">Dashboard</a></h3>
                        <ul>
                        	<li><a href="#" class="report">Sales Report</a></li>
                    		<li><a href="#" class="report_seo">SEO Report</a></li>
                            <li><a href="#" class="search">Search</a></li>
                        </ul>
                    </li>
                    <li><h3><a href="#" class="folder_table">Orders</a></h3>
          				<ul>
                        	<li><a href="#" class="addorder">New order</a></li>
                          <li><a href="#" class="shipping">Shipments</a></li>
                            <li><a href="#" class="invoices">Invoices</a></li>
                        </ul>
                    </li>
                    <li><h3><a href="#" class="manage">Manage</a></h3>
          				<ul>
                            <li><a href="#" class="manage_page">Pages</a></li>
                            <li><a href="#" class="cart">Products</a></li>
                            <li><a href="#" class="folder">Product categories</a></li>
            				<li><a href="#" class="promotions">Promotions</a></li>
                        </ul>
                    </li>
                  <li><h3><a href="#" class="user">Users</a></h3>
          				<ul>
                            <li><a href="#" class="useradd">Add user</a></li>
                            <li><a href="#" class="group">User groups</a></li>
            				<li><a href="#" class="search">Find user</a></li>
                            <li><a href="#" class="online">Users online</a></li>
                        </ul>
                    </li>
				</ul>       
          </div>
      </div>
        <div id="footer">
        <div id="credits">
   		Template by <a href="http://www.bloganje.com">Bloganje</a>
        </div>
        <div id="styleswitcher">
            <ul>
                <li><a href="javascript: document.cookie='theme='; window.location.reload();" title="Default" id="defswitch">d</a></li>
                <li><a href="javascript: document.cookie='theme=1'; window.location.reload();" title="Blue" id="blueswitch">b</a></li>
                <li><a href="javascript: document.cookie='theme=2'; window.location.reload();" title="Green" id="greenswitch">g</a></li>
                <li><a href="javascript: document.cookie='theme=3'; window.location.reload();" title="Brown" id="brownswitch">b</a></li>
                <li><a href="javascript: document.cookie='theme=4'; window.location.reload();" title="Mix" id="mixswitch">m</a></li>
                <li><a href="javascript: document.cookie='theme=5'; window.location.reload();" title="Mix" id="defswitch">m</a></li>
            </ul>
        </div><br />

        </div>
</div>
</body>
</html>