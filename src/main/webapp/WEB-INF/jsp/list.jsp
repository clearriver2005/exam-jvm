<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jeasyui/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/jeasyui/themes/icon.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jeasyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jeasyui/jquery.easyui.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jeasyui/locale/easyui-lang-zh_CN.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/js/yt/list.js"></script> 
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath">
	<div id="searchPanel" class="easyui-panel" title="交易信息查询"
		style="text-align:left;width:auto;background: #fafafa;" collapsible="false"
		minimizable="false" maximizable="false" data-options="fit:true">
	<div class="grid-toolbar" id="searchForm">
	<form id="form1" name="form1" method="get">
	<table class="tblContent" style="width:100%;">
		<tr>
			<td class="tdLeft" style="width:5rem"><span>交易状态：</span> </td>
			<td class="tdRight" style="width:5.5rem">
				<select id="status" name="status">
				    <option value="">全部状态</option>
				    <option value="1">交易成功</option>
				    <option value="0">交易失败</option>
				</select>
			</td>
			<td class="tdLeft">	
				<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="doSearch()">查询</a>
				<a href="javascript:void(0);"  class="easyui-linkbutton" data-options="iconCls:'icon-search'"  onclick="add()">添加数据</a>
			</td>
		</tr>
	</table>
	</form>
	</div>
	<div id="tt" class="grid-auto">  
	</div>
 </div>  
</body>
</html>
