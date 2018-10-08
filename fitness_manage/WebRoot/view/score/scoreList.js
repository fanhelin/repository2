var scoredg = null ;
var doSearch=null;
$(function() {
	doSearch = function () {
		var opts =$('#scoreListDatagrid').datagrid('options');
		opts.url =g_path+'/score/findScoreList.do';
        var mobile=$('#mobileId').val();
        var startTime=$('#startTimeId').val();
        var endTime=$('#endTimeId').val();
        var address=$('#addressId').val();
        var orderState=$('#stateId').combobox('getValue');
        var paras={};
        paras.mobile=mobile;
        paras.startTime=startTime;
        paras.endTime=endTime;
        paras.address=address;
        paras.orderState=orderState;
        $('#scoreListDatagrid').datagrid('options').queryParams =paras;
        $('#scoreListDatagrid').datagrid("reload");
    };
    
    scoredg = $('#scoreListDatagrid').datagrid({
	    idField:'code',
	    rownumbers:true,
	    pagination:true,
	    pageSize:10,
	    collapsible:true,
	    fit : true,
	    url :'',
	    fitColumns:true,
	    singleSelect :true,
	    method:'post',
	    toolbar:'#tb_zcmx',
	    onDblClickRow:onDblClickRow,
	    columns:[[
            {field:'checkbox',checkbox:true},
	        {field:'openid',title:'用户openid',align:'left'},
	        {field:'mobile',title:'手机号',align:'left'},
	        {field:'score',title:'扣除积分',align:'left'},
	        {field:'order_date_str',title:'兑换时间',align:'left'},
	        {field:'handle_date_str',title:'处理时间',align:'left'},
	        {field:'stateStr',title:'处理状态',align:'left'},
	        {field:'address',title:'发货地址',align:'left'}
	    ]],
	});
    
    $('#scoreListDatagrid').datagrid('getPager').pagination('refresh',{
		pageSize: 10,   
        pageList: [10, 20,30,40,50],
        onSelectPage: function(pageNumber, pageSize) {
            doSearch();
        }
	});
    
	$('#searchBtn').click(function () {
	    	doSearch();
	   });
	  
	  
		//编辑按钮点击事件
    $("#doBtn").click(function(){
		    onDblClickRow();
		});
		

	  
});

//行双击事件
function onDblClickRow(){	
	var row = scoredg.datagrid("getSelected");
    if(row == null){
   	 	$.messager.alert('提示',"请选择要处理的记录！"); 
   	 	return ;
    }
    if(row.stateStr=='已处理'){
	   	 	$.messager.alert('提示',"该条积分兑换记录已处理！"); 
	   	 	return ;
    }
    $.messager.confirm('Confirm','你确定要处理该条积分兑换记录？',function(r){
        if (r){
        	$.ajax(
                    { 
                        url: g_path+"/score/updateScoreOrderState.do",
                        type: 'POST',
                        dataType: "json",
                        //传送请求数据
                        data: { code: row.code,state:'1'},
                        success: function (ret) { //登录成功后返回的数据
                               //根据返回值进行状态显示
                               if (ret. success) {
                            	   doSearch();
                               }
                               else {
                                   //$("#divError").show().html("用户名或密码错误！" + strValue);
                           }
                    }
           });
        }
    });
};