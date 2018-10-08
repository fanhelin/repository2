package com.wx.serviceImpl.chapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import com.util.VidoUtil;
import com.wx.entity.chapter.Chapter;
import com.wx.entity.course.Course;
import com.wx.mapper.chapter.ChapterMapper;
import com.wx.service.chapter.ChapterService;

@Service
public class ChapterServiceImpl extends BaseService implements ChapterService {

	@Autowired
	ChapterMapper chapterMapper ;
	
	@Override
	public List<Chapter> selectChapters(Chapter chapter) throws Exception {
		// TODO Auto-generated method stub
		return chapterMapper.selectChapters(chapter);
	}

	@Override
	public void addChapter(Chapter chapter) throws Exception {
		try {
			// TODO Auto-generated method stub
			chapter.setCode(UUIDUntil.genUUID(10, "cp_", CaseEnum.LOWER));
			
			int seq = chapterMapper.getSequence(chapter) ;
			chapter.setSequence(seq + 1) ;
			int ret = chapterMapper.addChapter(chapter);
			if (ret < 1) {
				throw new Exception("增加章节失败");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RunTimeException(e.getMessage()) ;
		}
	}

	@Override
	public void updateChapter(Chapter chapter) throws Exception {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			int ret = chapterMapper.updateChapter(chapter);
			if (ret < 1) {
				throw new Exception("修改章节失败");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RunTimeException(e.getMessage()) ;
		}
	}

	@Override
	public void deleteChapter(Chapter chapter) throws Exception {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			int ret = chapterMapper.updateSequence(chapter);
			  String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)+File.separator+chapter.getCourse_code()+File.separator+chapter.getCode() ;
			  

			    if(FunHelper.isValidate(chapter.getVido_name(), 1)) {
			    	String oldPath = webPath + File.separator +chapter.getVido_name() ;
			    	File fileold = new File(oldPath) ;
			    	if(fileold.exists()){
			    		fileold.deleteOnExit() ;
			    	}
			    	
			    }
			  
			ret = chapterMapper.deleteChapter(chapter);
			if (ret < 1) {
				throw new Exception("删除章节失败");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RunTimeException(e.getMessage()) ;
		}
	}

	@Override
	public void updateChapterVido(Chapter chapter,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		try {
			MultipartFile file = null ;
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			String vido_name_old =  request.getParameter("vido_name_old") ;
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)+File.separator+chapter.getCourse_code()+File.separator+chapter.getCode() ;
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
	
					    if(FunHelper.isValidate(vido_name_old, 1)) {
					    	String oldPath = webPath + File.separator +vido_name_old ;
					    	File fileold = new File(oldPath) ;
					    	if(fileold.exists()){
					    		fileold.deleteOnExit() ;
					    	}
					    	
					    }
						file.transferTo(targetFile);
						if("file".equals(file.getName())){
						 JSONObject fileJsonObject =  VidoUtil.getVidoTime(targetFile) ;
						  int minutes = fileJsonObject.getInt("seconds");//java获取视频文件时长
						 chapter.setMinutes(minutes/60) ;
						}
						
						
						
					}
		
					}
                    
				int ret = chapterMapper.updateChapterVido(chapter);
				if (ret < 1) {
					for (File targetFile : targetFiles)
						targetFile.deleteOnExit();
					throw new Exception("修改视频记录失败");
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
	public void updateChapterImg(Chapter chapter, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		try {
			MultipartFile file = null ;
			// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
			String img_name_old =  request.getParameter("img_name_old") ;
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
			
			// 检查form中是否有enctype="multipart/form-data"
			if (multipartResolver.isMultipart(request)) {
				
				String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)+File.separator+chapter.getCourse_code()+File.separator+chapter.getCode() ;
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
						if("file".equals(file.getName())){
						 JSONObject fileJsonObject =  VidoUtil.getVidoTime(targetFile) ;
						  int minutes = fileJsonObject.getInt("seconds");//java获取视频文件时长
						 chapter.setMinutes(minutes/60) ;
						}
					}
		
					}
                    
				int ret = chapterMapper.updateChapterImg(chapter);
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
	public Chapter findChapterByCourse(Course course) throws Exception {
		// TODO Auto-generated method stub
		return this.chapterMapper.findChapterByCourse(course);
	}
   
}
