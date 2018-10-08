var clientdg = null ;
$(function() {
	doSearch = function () {
		var opts =$('#clientListDatagrid').datagrid('options');
		opts.url =g_path+'/client/selectClients.do';
        var mobile=$('#mobileId').val();
        var userName=$('#userNameId').val();
        var paras={};
        paras.mobile=mobile;
        paras.userName=userName;
        $('#clientListDatagrid').datagrid('options').queryParams =paras;
        $('#clientListDatagrid').datagrid("reload");
    };
    
    clientdg = $('#clientListDatagrid').datagrid({
	    idField:'id',
	    rownumbers:true,
	    pagination:true,
	    pageSize:10,
	    collapsible:true,
	    fit : true,
	    url :'',
	    fitColumns:true,
	    singleSelect :true,
	    method:'post',
	    onDblClickRow:onDblClickRow,
	    toolbar:'#tb_zcmx',
	    columns:[[
            {field:'checkbox',checkbox:true},
	        {field:'openid',title:'用户openid',align:'left'},
	        {field:'name',title:'  姓     名  ',align:'left'},
	        {field:'mobile',title:'手机号',align:'left'},
	        {field:'score',title:'总积分',align:'left',hidden:'true'},
	        {field:'sign_days',title:'签到天数',align:'left'},
	        {field:'con_sign_days',title:'连续签到天数',align:'left'},
	        {field:'email',title:'邮件地址',hidden:true,align:'left'},
	        {field:'address',title:'详细地址',align:'left',width:250},
	        {field:'sign_image',title:'签到图片',align:'left',width:150},
	        {field:'last_share_info',title:'最后分享信息',align:'left',width:250,hidden:true}
	    ]],
	});
    
    $('#clientListDatagrid').datagrid('getPager').pagination('refresh',{
		pageSize: 10,   
        pageList: [10, 20,30,40,50],
        onSelectPage: function(pageNumber, pageSize) {
            doSearch();
        }
	});
    
	var dg=  new DatagridColumTool("clientListDatagrid_car" ,"clientListDatagrid_meun" ,"clientListDatagrid",null,2) ;
	$('#searchBtn').click(function () {
	    	doSearch();
	   });
	  
	  
		//编辑按钮点击事件
		$("#editBtn").click(function(){
			var row = clientdg.datagrid("getSelected");
			
		    
		    if(row == null){
		   	 	$.messager.alert('提示',"请选择要查看的记录"); 
		   	 	return ;
		   }
		    
		    onDblClickRow(null,row);
		
		});
		

	  
});

//行双击事件
function onDblClickRow(rowIndex, rowData){	

	$('#dlg_edit').dialog('open');
	setTimeout(function(){$('#editForm').form('load', rowData);} ,400) ;
	var imgPath = upload_url+"/"+rowData.sign_image;
	console.log("imgpath:",imgPath) ;
	  $("#sign_image")[0].src = imgPath ;

};







