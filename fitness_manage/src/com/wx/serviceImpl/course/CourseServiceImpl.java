package com.wx.serviceImpl.course;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.framework.base.BaseService;
import com.framework.common.Config;
import com.framework.common.StaticFinal;
import com.framework.exception.RunTimeException;
import com.util.FunHelper;
import com.util.UUIDUntil;
import com.util.UUIDUntil.CaseEnum;
import com.wx.entity.course.Course;
import com.wx.entity.course.CourseOrder;
import com.wx.mapper.course.CourseMapper;
import com.wx.service.course.CourseService;

@Service
public class CourseServiceImpl extends BaseService implements CourseService {
	
	@Autowired
	CourseMapper courseMapper ;

	@Override
	public List<Course> selectCourses(Course course) throws Exception {
		// TODO Auto-generated method stub
		return courseMapper.selectCourses(course);
	}

	@Override
	public void addCourses(Course course) throws Exception {
		// TODO Auto-generated method stub
		try {
			course.setCode(UUIDUntil.genUUID(10, "cs_", CaseEnum.LOWER));
			int ret = courseMapper.addCourse(course) ;
			if(ret <=0 ){
				throw new Exception("增加课程失败") ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		   //e.printStackTrace();
			throw new RuntimeException(e.getMessage()) ;
		}
	}

	@Override
	public void updateCourses(Course course) throws Exception {
		// TODO Auto-generated method stub
		try {
			int ret = courseMapper.updateCourse(course) ;
			if(ret <=0 ){
				throw new Exception("修改课程失败") ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		   //e.printStackTrace();
			throw new RuntimeException(e.getMessage()) ;
		}
	}

	@Override
	public void deleteCourse(Course course) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		try {
			int ret = courseMapper.deleteCourse(course) ;
			if(ret <=0 ){
				throw new Exception("删除课程失败") ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
		   //e.printStackTrace();
			throw new RuntimeException(e.getMessage()) ;
		}
	}

	@Override
	public void updateCourseImg(Course course, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			MultipartFile file = null ;
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			String img_name_old =  request.getParameter("img_name_old") ;
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)+File.separator+course.getCode();
				File webPathFile=new File(webPath);
				if(!webPathFile.exists()){
					webPathFile.mkdirs();
			    }
				// 将request变成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				// 获取multiRequest 中所有的文件名
				Iterator<String> iter = multiRequest.getFileNames();
				
			 
				List<File> targetFiles=new ArrayList<File>();
				while (iter.hasNext()) {
					// 一次遍历所有文件
					file = multiRequest.getFile(iter.next().toString());
					if (file != null) {
						
						    String fileName = file.getOriginalFilename();
						    if(fileName==null||"".equals(fileName.trim()))
						    	continue;
						    File targetFile = new File(webPathFile, fileName);
						    targetFiles.add(targetFile);
	
					    if(FunHelper.isValidate(img_name_old, 1)) {
					    	String oldPath = webPath + File.separator +img_name_old ;
					    	File fileold = new File(oldPath) ;
					    	if(fileold.exists()){
					    		fileold.deleteOnExit() ;
					    	}
					    	
					    }
						file.transferTo(targetFile);
					}
		
					}
                    
				int ret = courseMapper.updateCourseImg(course);
				if (ret < 1) {
					for (File targetFile : targetFiles)
						targetFile.deleteOnExit();
					throw new Exception("修改图片失败");
				}
			}else{
				throw new Exception("不是multipart/form-data") ;
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage()) ;
		}
	}

	@Override
	public List<Course> findCourseByOrder(Course course) throws Exception {
		// TODO Auto-generated method stub
		return this.courseMapper.findCourseByOrder(course);
	}

	@Override
	public void courseRestart(CourseOrder courseOrder) throws Exception {
		// TODO Auto-generated method stub
		this.courseMapper.courseRestart(courseOrder);
	}

	@Override
	public int getClientCourseCount(CourseOrder courseOrder) throws Exception {
		// TODO Auto-generated method stub
		return this.courseMapper.getClientCourseCount(courseOrder);
	}

}
