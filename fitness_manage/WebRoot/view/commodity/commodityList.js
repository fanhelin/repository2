var editdg = null ;
$(function() {
	doSearch = function () {
        $('#commodityListDatagrid').datagrid("reload");
    };
    
    editdg = $('#commodityListDatagrid').datagrid({
	    idField:'id',
	    rownumbers:true,
	    pagination:false,
	    fit : true,
	    url :g_path+"/commodity/findCommodityInfoList.do",
	    fitColumns:true,
	    singleSelect :true,
	    method:'post',
	    onDblClickRow:onDblClickRow,
	    toolbar:'#tb_zcmx',
	    columns:[[
            {field:'checkbox',checkbox:true},
	        {field:'code',title:'商品编号',align:'left'},
	        {field:'name',title:'商品名  ',align:'left'},
	        {field:'score',title:'需要积分',align:'left'},
	        {field:'price',title:'价格',align:'left'},
	        {field:'r_price',title:'原价格',align:'left'},
	        {field:'sum',title:'库存',align:'left'},
	        {field:'status_str',title:'状态',align:'left'},
	        {field:'title',title:'商品标题',align:'left'},
	        {field:'des',title:'商品描述',align:'left'}
	    ]],
	});
    
	var dg=  new DatagridColumTool("commodityListDatagrid_modul" ,"commodityListDatagrid_meun" ,"commodityListDatagrid",null,2) ;
	  $('#searchBtn').click(function () {
	    	doSearch();
	   });
	  
	  
		//编辑按钮点击事件
		$("#editBtn").click(function(){
			var row = editdg.datagrid("getSelected");
		    if(row == null){
		   	 	$.messager.alert('提示',"请选择要查看的商品"); 
		   	 	return ;
		   }
		    onDblClickRow(null,row);
		});
		
		$("#addBtn").click(function(){	
			$('#dlg_add').dialog('open');
			setTimeout(function(){
				$('#addForm').form('reset');
				$('#Img1')[0].src="";
				$('#Img2')[0].src="";
				$("#add_code").textbox("setValue","待生成***") ;
				$("#add_total_chapters").textbox("setValue","0") ;
			} ,400) ;
		});
		
		
		$("#removeBtn").click(function(){
			
			var row =  $('#commodityListDatagrid').datagrid("getSelected");
			
			if(row == null){
				 $.messager.alert('提示',"请选择要删除的商品");
				 return  ;
			}
			if(row.status=='1'){
				 $.messager.alert('提示',"已上架的商品不能删除");
				 return;
			}
	    	$.messager.confirm('提示', '确定要删除该商品吗?', 
	    			function(r){
	    				
	    				if (r){
	    					$.ajax(
	    		    	             { 
	    		    	                 url: g_path+"/commodity/deleteCommodityInfo.do?code="+row.code, 
	    		    	                 type: 'POST',
	    		    	                 dataType: "json",
	    		    	                 //传送请求数据
	    		    	                  success: function (data) { 
	    		    	                        //根据返回值进行状态显示
	    		    	                        if (data.success) {
	    		    	                        	$('#commodityListDatagrid').datagrid("reload");
													ToolKits.popTip("成功",data.message);
													ToolKits.writeLog(data.message);	
	    		    	                        }
	    		    	                        else {
													 ToolKits.popTip("失败",data.message);
	    		    	                        }
	    		    	                    }
	    		    	                });	
	    				}else{
	    					
	    				}
	    			});
			
			
		});
		
        /***
         * 章节管理
         */
		$("#chapterBtn").click(function(){	
		    var row =  $('#commodityListDatagrid').datagrid("getSelected");
			if(row == null){
				 $.messager.alert('提示',"请选择一个商品");
				 return  ;
			}
			
			 var ps = JSON.stringify(row) ;
				
			  ps =  ps.replace(/\"/g, "'");
			  ps = escape(ps) ;
			  var tabInfo = {} ;
			  tabInfo.text = "【" + row.name + "】 章节管理" ;
			  tabInfo.url = "/view/chapter/chapterList.jsp?ps=" +ps  ; 
			  tabInfo.fName="chapterList" ;
			  tabInfo.closable = true ;
			  try{
			  	   ToolKits.closeTab(tabInfo.text) ;
			   }catch(e){
			   	
			   } 
			  ToolKits.addTab(tabInfo) ;
			
		}); 
		
		

	  
});

//行双击事件
function onDblClickRow(rowIndex, rowData){	
	$('#dlg_edit').dialog('open');
	setTimeout(function(){
		$('#editForm').form('load', rowData);
	    $("#eImg1")[0].src=upload_url+"/"+rowData.main_img;
	    $("#eImg2")[0].src=upload_url+"/"+rowData.fu_img;
	} ,400) ;
};


function addcommodity(){
	var formData = new FormData();
	formData.append("name",$("#add_name").val());
	formData.append("score",$("#add_score").val());
	formData.append("price",$("#add_price").val());
	formData.append("r_price",$("#add_r_price").val());
	formData.append("sum",$("#add_sum").val());
	formData.append("unit",$("#add_unit").val());
	formData.append("title",$("#add_title").val());
	formData.append("status", $('input:radio[name="add_status"]:checked').val());
	formData.append("des",$("#add_desc").val());
	if($("#add_main_img").val()!="")
		formData.append("main_img_file",$("#add_main_img")[0].files[0]);
	if($("#add_fu_img").val()!="")
		formData.append("fu_img_file",$("#add_fu_img")[0].files[0]);
	$.ajax({ 
       url:g_path + "/commodity/addCommodityInfo.do", 
       type: 'POST',
       data: formData,
       processData : false,
       contentType : false,
       beforeSend:function(){
    	   EasyUILoad("处理中，请稍候。。。");
       },
       success: function (ret){
    	      dispalyEasyUILoad();
              if(ret.success) {
           	    ToolKits.popTip("提示" ,ret.message) ;
           		$('#dlg_add').dialog('close');
           	    $('#commodityListDatagrid').datagrid("reload");
              }else {
           	    ToolKits.popTip("提示" ,ret.message) ;
           	    $('#dlg_add').dialog('close');
              }
        }
	});
	
}


function updatecommodity(){
	var formData = new FormData();
	formData.append("name",$("#edit_name").val());
	formData.append("code",$("#edit_code").val());
	formData.append("score",$("#edit_score").val());
	formData.append("price",$("#edit_price").val());
	formData.append("r_price",$("#edit_r_price").val());
	formData.append("sum",$("#edit_sum").val());
	formData.append("unit",$("#edit_unit").val());
	formData.append("title",$("#edit_title").val());
	formData.append("status", $('input:radio[name="status"]:checked').val());
	formData.append("des",$("#edit_desc").val());
	if($("#edit_main_img").val()!="")
		formData.append("main_img_file",$("#edit_main_img")[0].files[0]);
	if($("#edit_fu_img").val()!="")
		formData.append("fu_img_file",$("#edit_fu_img")[0].files[0]);
	formData.append("main_img",$("#main_img_h").val());
	formData.append("fu_img",$("#fu_img_h").val());
	$.ajax({ 
       url:g_path + "/commodity/updateCommodityInfo.do", 
       type: 'POST',
       data: formData,
       processData : false,
       contentType : false,
       beforeSend:function(){
    	   EasyUILoad("处理中，请稍候。。。");
       },
       success: function (ret){
    	      dispalyEasyUILoad();
              if(ret.success) {
           	    ToolKits.popTip("提示" ,ret.message) ;
           		$('#dlg_edit').dialog('close');
           	    $('#commodityListDatagrid').datagrid("reload");
              }else {
           	    ToolKits.popTip("提示" ,ret.message) ;
           	    $('#dlg_edit').dialog('close');
              }
        }
	});
	
}



