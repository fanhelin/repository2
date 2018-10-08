var editdg = null ; 
var urlParams = null ;
var isEdit = false ;
$(function() {
	 urlParams = ToolKits.getRequestParams() ;
	 urlParams = eval("(" + urlParams.ps + ")")  ;
	console.log("urlParams:",urlParams) ;
	
	doSearch = function () {
		// $('#chapterListDatagrid').datagrid("options").queryParams.course_code = urlParams.code ;
        $('#chapterListDatagrid').datagrid("reload");
    };
    
    editdg = $('#chapterListDatagrid').datagrid({
	    idField:'id',
	    rownumbers:true,
	    pagination:false,
	    fit : true,
	    url :g_path+"/chapter/selectChapters.do",
	    fitColumns:true,
	    singleSelect :true,
	    method:'post',
	    onDblClickRow:onDblClickRow,
	    toolbar:'#tb_zcmx',
	    columns:[[
            {field:'checkbox',checkbox:true},
	        {field:'code',title:'章节编号',align:'left',width:120},
	        {field:'name',title:' 课 程 名  ',align:'left',width:150},
	        {field:'describe',title:'章节描述',align:'left',width:250},
	        {field:'minutes',title:'播放时长(分钟)',align:'left'},
	        {field:'img_name',title:'封面图片',align:'left'},
	        {field:'vido_name',title:'视频路径',align:'left'}
	    ]],
	    queryParams:{course_code:urlParams.code }
	});
    
	var dg=  new DatagridColumTool("chapterListDatagrid_modul" ,"chapterListDatagrid_meun" ,"chapterListDatagrid",null,2) ;
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
			
			var row =  $('#chapterListDatagrid').datagrid("getSelected");
			
			if(row == null){
				 $.messager.alert('提示',"请选择要删除的章节");
				 return  ;
			}
			var index =  $('#chapterListDatagrid').datagrid("getRowIndex");
	    	$.messager.confirm('提示', '确定要删除章节吗?', 
	    			function(r){
	    				
	    				if (r){
	    					$.ajax(
	    		    	             { 
	    		    	                 url: g_path+"/chapter/deleteChapter.do", 
	    		    	                 type: 'POST',
	    		    	                 dataType: "json",
	    		    	                 //传送请求数据
	    		    	                  data: row ,
	    		    	                  success: function (data) { 
	    		    	                        //根据返回值进行状态显示
	    		    	                        if (data.success) {
	    		    	                        	$('#chapterListDatagrid').datagrid("deleteRow" ,index);
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
		    var row =  $('#chapterListDatagrid').datagrid("getSelected");
			if(row == null){
				 $.messager.alert('提示',"请选择一个课程");
				 return  ;
			}
			
			 var ps = JSON.stringify(row) ;
				
			  ps =  ps.replace(/\"/g, "'");
			  ps = escape(ps) ;
			  var tabInfo = {} ;
			  tabInfo.text = "章节管理" ;
			  tabInfo.url = "/view/chapter/chapterList.jsp?ps=" +ps  ; 
			  tabInfo.fName="chapterList" ;
			  try{
			  	   ToolKits.closeTab(tabInfo.text) ;
			   }catch(e){
			   	
			   } 
			  ToolKits.addTab(tabInfo) ;
			
		}); 
		
		
		$('#edit_vido_name').textbox({  
		    onChange: function(value) {
		    	if(isEdit) {
		    		return ;
		    	}
		    	  var uploadFile = new FormData($("#editForm")[0]); 
		    	
		    		  uploadFile.append("course_code",urlParams.code) ;
		    		    console.log(uploadFile);  
		    		    if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){  
		    		        $.ajax({  
		    		            url:g_path + '/chapter/upLoadChapterVido.do',  
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
		    		                $('#chapterListDatagrid').datagrid("reload");
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
		
		
		$('#edit_img_name').textbox({  
		    onChange: function(value) {
		    	if(isEdit) {
		    		return ;
		    	}
		    	  var uploadFile = new FormData($("#editForm")[0]); 
		    	
		    		  uploadFile.append("course_code",urlParams.code) ;
		    		    console.log(uploadFile);  
		    		    if("undefined" != typeof(uploadFile) && uploadFile != null && uploadFile != ""){  
		    		        $.ajax({  
		    		            url:g_path + '/chapter/upLoadChapterImg.do',  
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
		    		                $('#chapterListDatagrid').datagrid("reload");
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
	$('#editForm').form('load', rowData);
	$("#edit_vido_name_old").textbox("setValue",rowData.vido_name) ;
	$("#edit_img_name_old").textbox("setValue",rowData.image_name) ;
	$("#Img1")[0].src=upload_url+rowData.course_code+"/"+rowData.code+"/"+rowData.image_name;
	setTimeout(function(){
		isEdit = false ;	
	} ,500) ;
	

};


function addChapter(){
	var sendData = $("#addForm").serialize();
	sendData += "&course_code=" + urlParams.code ;
	$.ajax({ 
       url:g_path + "/chapter/addChapter.do", 
       type: 'POST',
       dataType: "json",
       data: sendData,
       success: function (ret){ 
              if(ret.success) {
           	    ToolKits.popTip("提示" ,ret.message) ;
           	    
           		$('#dlg_add').dialog('close');
           	    $('#chapterListDatagrid').datagrid("reload");
              }else {
           	    ToolKits.popTip("提示" ,ret.message) ;
              }
        }
	});
	
}


function updateChapter(p1,p2){
	
	var sendData = $("#editForm").serialize();

	$.ajax({ 
       url:g_path + "/chapter/updateChapter.do", 
       type: 'POST',
       dataType: "json",
       data: sendData,
       success: function (ret){ 
              if(ret.success) {
           	    ToolKits.popTip("提示" ,ret.message) ;
           	    
           		$('#dlg_edit').dialog('close');
           	    $('#chapterListDatagrid').datagrid("reload");
              }else {
           	    ToolKits.popTip("提示" ,ret.message) ;
              }
        }
	});
	
}

function clickUploadVidoButton(){
	$("#id_fileSelect").trigger("click");
	return ;
}

function clickUploadImgButton(){
	$("#id_img_fileSelect").trigger("click");
	return ;
}


function uploadVido(){
	var filePath = $("#id_fileSelect").val() ;
      console.log(filePath) ;
      filePath = filePath.substring(filePath.lastIndexOf("\\")+1) ;
  	$('#edit_vido_name').textbox("setValue",filePath); 
	
}

function uploadImg(){
	preImg($("#id_img_fileSelect")[0].id,'Img1');
	var filePath = $("#id_img_fileSelect").val() ;
    console.log(filePath) ;
    filePath = filePath.substring(filePath.lastIndexOf("\\")+1) ;
  	$('#edit_img_name').textbox("setValue",filePath);
}



