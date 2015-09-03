var url="";
$(document).ready(function() {
	init();
});
function getQueryParams(){
	var obj ={};
//	if($("#status").val()!=''){
//		obj.status=$("#status").val();
//		url=$("#contextPath").val()+ "/s/trade/queryByStatus.htm";
//	}else{
//		url=$("#contextPath").val()+ "/s/trade/query.htm";
//	}
	url=$("#contextPath").val()+ "/s/trade/query/"+$("#status").val();
	return obj;
}

function init(){
	$('#tt').datagrid({
		url :url,
		method : 'GET',
		singleSelect :false,
		rownumbers : "true",
		pagination : "true",
		width : "100%",
		height : 350,
		queryParams :{},
		columns : [ [ {
			checkbox:true,
			width : 40,
			align : 'center'
		},{
			field : 'tid',
			title : '交易 ID',
			width : 120,
			align : 'center'
		}, {
			field : 'status',
			title : '交易状态',
			width : 120,
			align : 'center',
			formatter : function(val,row,index){
				if (val == 1){
					return '交易成功';
				} else if (val == 0) {
					return '交易失败';
				}else{
					return '等待付款';
				}
			}
		}, {
			field : 'tradeType',
			title : '交易类型',
			width : 80,
			align : 'center',
			formatter : function(val,row,index){
				if (val == 1){
					return '支付';
				} else if (val == 2) {
					return '退款';
				}else{
					return val;
				}
			}
		}, {
			field : 'amount',
			title : '交易金额',
			width : 80,
			align : 'center'

		}, {
			field : 'moneyType',
			title : '交易币种',
			width : 120,
			align : 'center',
			formatter : function(val,row,index){
				if (val == 1){
					return '人民币';
				} else if (val == 2) {
					return '美元';
				}else{
					return val;
				}
			}
		}, {
			field : 'customer.cid',
			title : '客户 ID',
			width : 80,
			align : 'center',
			formatter : function(val,row,index){
				return row.customer.cid;
			}
		}, {
			field : 'customer.name',
			title : '活动名称',
			width : 80,
			align : 'center',
			formatter : function(val,row,index){
				return row.customer.name;
			}
		},{
			field : 'createDate',
			title : '创建时间',
			width : 130,
			align : 'center',
			formatter : function(val,row,index) {
				if (!!val) {
//					return formatdate(val,"yyyy-MM-dd HH:mm:ss");
					return val;
				}
			}
		}] ]
	});
}
/**
 * 查询
 */
function doSearch() {
	var param=getQueryParams();
	$.ajax({
		url :url,
		type : "POST",
		cache : false,
		dataType: "json",
		timeout:3000,
		data :param,
		error : function(e) {
			msgShow("系统异常，请稍后再试！");
		},
		success : function(data){
			if (data.success&&data.success==true) {
				var list=$.parseJSON(data.tradelist);
				$("#tt").datagrid('loadData', {"total":list.length,"rows":list});
			}
		}
	});
}
function add(){
	window.location.href=$("#contextPath").val()+"/add.htm";
}
/**
 * 将毫秒时间转为 yyyy-MM-dd HH:mm:ss日期格式
 * @param time 长整数
 * @param format  yyyy-MM-dd HH:mm:ss
 * @13011146
 */
function formatdate(time,format) {
	if (time == null || time == "") {
		return null;
	}
	var t = new Date(time);
	var tf = function(i) {
		return (i < 10 ? '0' : '') + i;
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
		switch (a) {
			case 'yyyy':
				return tf(t.getFullYear());
				break;
			case 'MM':
				return tf(t.getMonth() + 1);
				break;
			case 'mm':
				return tf(t.getMinutes());
				break;
			case 'dd':
				return tf(t.getDate());
				break;
			case 'HH':
				return tf(t.getHours());
				break;
			case 'ss':
				return tf(t.getSeconds());
				break;
		}
	});
}