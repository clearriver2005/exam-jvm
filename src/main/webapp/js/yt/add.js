/**
 * 返回列表
 */
function closeMdItem(){
	location.href="list.htm";
}

	
/**
 * 保存
 */
function doSubmit(){
	var cid = $.trim($("#cid").val());//用户主键
	var name = $.trim($("#name").val());//用户
	var amount = $.trim($("#amount").val());//交易金额
	var status = $.trim($("#status").val());//交易状态
	var tradeType = $.trim($("#tradeType").val());//交易类型
	var moneyType = $.trim($("#moneyType").val());//交易币种
	if (amount == null || amount == "") {
		msgShow("交易金额不能为空")
		return false;
	}
	if(!!isNaN(amount-0)){
		msgShow("交易金额格式不正确!")
		return false;
	}
	var param={amount:amount,status:status,tradeType:tradeType,moneyType:moneyType,"customer.cid":cid,"customer.name":name};
	$.messager.confirm('提示:','确认要保存吗?',function(r) {
		if (r) {
			$.ajax({
				url : $("#contextPath").val()+"/s/trade/save.htm",
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
						msgShow("操作成功！");
						window.location.href=$("#contextPath").val()+"/list.htm";
					}
				}
			});
		}
	});
}

function setTab() {
	$('#newtab').tabs('select', '维护推荐商品');
}
function msgShow(msg, title) {
	$.messager.show({
		title : title || "提示信息",
		msg : msg,
		height:'auto',
		timeout : 2000,
		showType : 'slide'
	});
}