var paymentdg = null ;

$(function(){
		
		doSearch = function () {
			var opts =$('#paymentlistdg').datagrid('options');
			opts.url =g_path+'/payment/findPaymentList.do';
			var startTime=$('#startTimeId').datetimebox('getValue');
	        var endTime=$('#endTimeId').datetimebox('getValue');
	        var mobile=$('#mobileId').val();
	        var paras={};
	        paras.mobile=mobile;
	        paras.startTime=startTime;
	        paras.endTime=endTime;
	        $('#paymentlistdg').datagrid('options').queryParams =paras;
	        $('#paymentlistdg').datagrid("reload");
	    };
	    
	    paymentdg = $('#paymentlistdg').datagrid({
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
			    toolbar:'#tb_zcmx',
			    columns:[[
			        {field:'openid',title:'用户标示',align:'left'},
			        {field:'mobile',title:'手机号',align:'left'},
			        {field:'time_end',title:'支付时间',align:'left'},
			        {field:'nonce_str',title:'支付订单号',align:'left'},
			        {field:'total_fee',title:'支付金额(元)',align:'left',formatter:function(v){
			        	var f = 0.0 ;
			        	f = v /100 ; 
			        	return f ;
			        }},
			        {field:'return_code_str',title:'支付状态',align:'left'},
			        {field:'bank_type',title:'银行类型',align:'left'}
			    ]],
			    view: detailview,
			    detailFormatter: function(rowIndex, rowData){
			    	return '<div style="padding:2px"><table title="订购详情" id="ddv-' + rowIndex + '"></table></div>';  
				},
				onExpandRow:function(rowIndex, rowData){
					 $('#ddv-'+rowIndex).datagrid({  
						 url:g_path+"/course/findCourseByOrder.do?code="+rowData.nonce_str,
						 fitColumns:true,  
		                 singleSelect:true,  
		                 height:'auto',
		                 columns:[[  
		                           {field:'code',title:'课程编号',align:'left'},
		                           {field:'name',title:'课程名称',align:'left'},
		                           {field:'price',title:'单价(元)',align:'left'},
		                           {field:'num',title:'订购数量',align:'left'}
		                 ]],
		                 onResize:function(){  
		                        $('#paymentlistdg').datagrid('fixDetailRowHeight',rowIndex);  
		                 },
		                 onLoadSuccess:function(){  
		                     setTimeout(function(){  
		                            $('#paymentlistdg').datagrid('fixDetailRowHeight',rowIndex);  
		                        },0);  
		                 }  
					 });
					 $('#paymentlistdg').datagrid('fixDetailRowHeight',rowIndex);  
				}
			});
		 
	    $('#paymentlistdg').datagrid('getPager').pagination('refresh',{
			pageSize: 10,   
	        pageList: [10, 20,30,40,50],
	        onSelectPage: function(pageNumber, pageSize) {
	            doSearch();
	        }
		});
	    
	    $('#searchBtn').click(function () {
	    	doSearch();
	    	});
	    
	    var dg=  new DatagridColumTool("paymentlistdg_car" ,"paymentlistdg_meun" ,"paymentlistdg",null,2);
	});