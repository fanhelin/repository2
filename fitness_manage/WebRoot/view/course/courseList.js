var editdg = null ;
var isEdit = false ;
$(function() {
	doSearch = function () {
		var opts =$('#courseListDatagrid').datagrid('options');
		var name=$('#nameId').val();
		var state=$('#stateId').combobox('getValue');
		var paras={};
	    paras.name=name;
	    paras.state=state;
	    $('#courseListDatagrid').datagrid('options').queryParams =paras;
        $('#courseListDatagrid').datagrid("reload");
    };
    
    editdg = $('#courseListDatagrid').datagrid({
	    idField:'id',
	    rownumbers:true,
	    pagination:false,
	    fit : true,
	    url :g_path+"/course/selectCourses.do",
	    fitColumns:true,
	    singleSelect :true,
	    method:'post',
	    onDblClickRow:onDblClickRow,
	    toolbar:'#tb_zcmx',
	    columns:[[
            {field:'checkbox',checkbox:true},
	        {field:'code',title:'课程编号',align:'left'},
	        {field:'name',title:'  课 程 名  ',align:'left'},
	        {field:'describe',title:'课程描述',align:'left',width:90},
	        {field:'state_str',title:'状态',align:'left'},
	        {field:'price',title:'价 格',align:'left'},
	        {field:'total_chapter',title:'总章节数',align:'left'},
	        {field:'app_info_code',align:'left',hidden:'true'}
	    ]],
	});
    
	var dg=  new DatagridColumTool("courseListDatagrid_modul" ,"courseListDatagrid_meun" ,"courseListDatagrid",null,2) ;
	  $('#searchBtn').click(function () {
	    	doSearch();
	   });
	  
	  
		//编辑按钮点击事件
		$("#editBtn").click(function(){
			var row = editdg.datagrid("getSelected");
			
		    
		    if(row == null){
		   	 	$.messager.alert('提示',"请选择要查看的课程"); 
		   	 	return ;
		   }
		    
		    onDblClickRow(null,row);
		
		});
		
		$("#addBtn").click(function(){	
			$('#dlg_add').dialog('open');
			setTimeout(function(){
				$('#addForm').form('reset');
				$("#add_code").textbox("setValue","待生成***") ;
				$("#add_total_chapters").textbox("setValue","0") ;
			} ,400) ;
		});
		
		
		$("#removeBtn").click(function(){
			
			var row =  $('#courseListDatagrid').datagrid("getSelected");
			
			if(row == null){
				 $.messager.alert('提示',"请选择要删除的课程");
				 return  ;
			}
			if(row.state=='1'){
				$.messager.alert('提示',"该课程正在上架销售，不能删除，请先下架");
				 return  ;
			}
			var index =  $('#courseListDatagrid').datagrid("getRowIndex");
	    	$.messager.confirm('提示', '确定要删除课程吗?', 
	    			function(r){
	    				
	    				if (r){
	    					$.ajax(
	    		    	             { 
	    		    	                 url: g_path+"/course/deleteCourse.do", 
	    		    	                 type: 'POST',
	    		    	                 dataType: "json",
	    		    	                 //传送请求数据
	    		    	                  data: row ,
	    		    	                  success: function (data) { 
	    		    	                        //根据返回值进行状态显示
	    		    	                        if (data.success) {
	    		    	                        	//$('#courseListDatagrid').datagrid("deleteRow" ,index);
	    		    	                        	doSearch();
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
		    var row =  $('#courseListDatagrid').datagrid("getSelected");
			if(row == null){
				 $.messager.alert('提示',"请选择一个课程");
				 return  ;
			}
			
			if(row.state=='1'){
				 $.messager.alert('提示',"该课程正在上架销售，不能进行章节变动，请先下架");
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
			  	 //  ToolKits.closeTab(tabInfo.text) ;
				  ToolKits.addTab(tabInfo) ;
			   }catch(e){
			   	
			   } 
			;
			
		}); 
		
		
		$('#edit_img_name').textbox({  
		    onChange: function(value) {
		    	if(isEdit) {
		    		return ;
		    	}
		    	  var uploadFile = new FormData($("#editForm")[0]); 
		    		    if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){  
		    		        $.ajax({  
		    		            url:g_path + '/course/upLoadCourseImg.do',  
		    		            type:'POST',  
		    		            data : uploadFile,  
		    		            async: false,    
		    		            cache: false,
		    					crossDomain: true,  			
		    		            contentType: false, //不设置内容类型  
		    		            processData: false, //不处理数据  
		    		            success:function(data){  
		    		                console.log(data);  
		    		                ToolKits.popTip("提示" ,data.message) ;
		    		                $('#courseListDatagrid').datagrid("reload");
		    		            },  
		    		            error:function(e){  
		    					    console.log(e) ;
		    		                ToolKits.popTip("提示" ,"上传失败") ;
		    		            }  
		    		        }) ; 
		    		    }else {  
		    		      
		    		        ToolKits.popTip("提示" ,"选择的文件无效！请重新选择") ;
		    		    }  
		    }
		});
		
		

	  
});

//行双击事件
function onDblClickRow(rowIndex, rowData){	
	$('#dlg_edit').dialog('open');
	isEdit = true ;
	if(rowData.state=='1'){
		$('#edit_name').textbox('textbox').attr('readonly',true);
		$('#edit_describe').textbox('textbox').attr('readonly',true);
		$('#edit_price').textbox('textbox').attr('readonly',true);
		$('#edit_shangchuan').linkbutton('disable');
	}
	else{
		$('#edit_name').textbox('textbox').attr('readonly',false);
		$('#edit_describe').textbox('textbox').attr('readonly',false);
		$('#edit_price').textbox('textbox').attr('readonly',false);
		$('#edit_shangchuan').linkbutton('enable');
	}
	$('#editForm').form('load', rowData);
	$("#edit_img_name_old").textbox("setValue",rowData.image_name) ;
	$("#Img1")[0].src=upload_url+rowData.code+"/"+rowData.image_name;
	setTimeout(function(){isEdit = false ;} ,500) ;
};


function addCourse(){
	var sendData = $("#addForm").serialize();
	$.ajax({ 
       url:g_path + "/course/addCourse.do", 
       type: 'POST',
       dataType: "json",
       data: sendData,
       success: function (ret){ 
              if(ret.success) {
           	    ToolKits.popTip("提示" ,ret.message) ;
           	    
           		$('#dlg_add').dialog('close');
           	    $('#courseListDatagrid').datagrid("reload");
              }else {
           	    ToolKits.popTip("提示" ,ret.message) ;
              }
        }
	});
	
}


function updateCourse(){
	var sendData = $("#editForm").serialize();
	$.ajax({ 
       url:g_path + "/course/updateCourse.do", 
       type: 'POST',
       dataType: "json",
       data: sendData,
       success: function (ret){ 
              if(ret.success) {
           	    ToolKits.popTip("提示" ,ret.message) ;
           	    
           		$('#dlg_edit').dialog('close');
           	    $('#courseListDatagrid').datagrid("reload");
              }else {
           	    ToolKits.popTip("提示" ,ret.message) ;
              }
        }
	});
	
}

function clickUploadImgButton(){
	$("#id_img_fileSelect").trigger("click");
	return ;
}


function uploadImg(){
	preImg($("#id_img_fileSelect")[0].id,'Img1');
	var filePath = $("#id_img_fileSelect").val() ;
    console.log(filePath) ;
    filePath = filePath.substring(filePath.lastIndexOf("\\")+1) ;
  	$('#edit_img_name').textbox("setValue",filePath);
}



