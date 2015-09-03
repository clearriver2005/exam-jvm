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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/yt/add.js"></script> 
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath">
<div id="searchPanel" class="easyui-panel" title="添加交易信息"
			style="text-align:left;width:auto;background: #fafafa;" collapsible="false"
			minimizable="false" maximizable="false" data-options="fit:true">
		<div class="easyui-tabs" id="newtab" style="width:700px;height:auto">
			<div title="基本信息" style="padding:10px">
				<div style="border:1px #99CCFF solid; line-height:20px;width:680px">
			        <form id="fm" method="post" autocomplete="off">
			            <table cellpadding="5">
			               	<tr>
				                <td class="tdLeft">用户：</td>
				                <td  class="tdRight">
				                <input id="cid" name="cid" type="hidden" value="1">
				                <input id="name" name="name" class="easyui-validatebox" readonly="readonly" value="张三"/></td>
			               	</tr>
			               	<tr>
				                <td class="tdLeft">交易金额：</td>
				                <td  class="tdRight"><input id="amount" name="amount" 
				                	class="easyui-validatebox"/></td>
			               	</tr>
			               	<tr>
				                <td class="tdLeft">交易状态：</td>
				                <td class="tdRight" style="width:5.5rem">
									<select id="status" name="status" style="width:5.5rem">
									    <option value="1">交易成功</option>
									    <option value="0">交易失败</option>
									</select>
								</td>
			               	</tr>
			               	<tr>
				                <td class="tdLeft">交易类型：</td>
				                <td class="tdRight" style="width:5.5rem">
									<select id="tradeType" name="tradeType" style="width:5.5rem">
									    <option value="1">支付</option>
									    <option value="2">退款</option>
									</select>
								</td>
			               	</tr>
			               	<tr>
				                <td class="tdLeft">交易币种：</td>
				                <td class="tdRight" style="width:5.5rem">
									<select id="moneyType" name="moneyType" style="width:5.5rem">
									    <option value="1">人民币</option>
									    <option value="2">美元</option>
									</select>
								</td>
			               	</tr>	               	              		               	
			            </table>
			        </form>
			    </div>
				<div style="padding-left:200px;">
						<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="doSubmit()">保存</a>
				 		<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo'" onclick="closeMdItem()">返回</a>
				</div>	
			</div>
		</div>
	</div>
</body>
</html>
