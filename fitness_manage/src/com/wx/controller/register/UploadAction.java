package com.wx.controller.register;

import com.framework.base.BaseController;
import com.framework.common.Response;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Controller
@RequestMapping("/file")
public class UploadAction extends BaseController { 

	@RequestMapping("/upLoadFile1.do")
    public String upload(@RequestParam(value = "data", required = false) MultipartFile data, HttpServletRequest request, ModelMap model) {

        System.out.println("开始");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = data.getOriginalFilename();

        System.out.println(path);
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

   
        //保存
        try {
        	data.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);

        return "result";
    }
	
	@RequestMapping("/upLoadFile.do")
	@ResponseBody
	public Response springUpload(HttpServletRequest request,HttpServletResponse rep) throws IllegalStateException, IOException {
		rep.addHeader("Access-Control-Allow-Origin", "*");
			long startTime = System.currentTimeMillis();
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			
			String course_code =  request.getParameter("course_code") ;
			String code =  request.getParameter("code") ;
			
			System.out.println("course_code:"+course_code) ;

			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator iter = multiRequest.getFileNames();
	
				while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					//String webPath = new WebUtils().getWebInfPath() + "upload/";
					String webPath = request.getSession().getServletContext().getRealPath("upload/"+course_code);
				    String fileName = file.getOriginalFilename();
				    String exe = fileName.substring(fileName.lastIndexOf("."));
				    System.out.println("exe:"+ exe ) ;
				    
				    File targetFile = new File(webPath, code+exe);
			        if(!targetFile.exists()){
			            targetFile.mkdirs();
			        }
	
				    
					String path = webPath + file.getOriginalFilename();
					// 上传
					
					try {
						file.transferTo(targetFile);
						// 暂存文件信息
						//memo.setFileurl(webPath);
						//memo.setFilename(file.getOriginalFilename());
						//outParam.setData(memo);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace(); 
						return Response.error(e.getMessage()) ;
					}
				}
	
				}
				
				long endTime = System.currentTimeMillis();
				System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime)
				+ "ms");
				
				 return Response.success("上传成功,耗时：" + String.valueOf(endTime - startTime) +"ms");

			}else{
				return Response.error("不是multipart/form-data") ;
			}
			
		}

}