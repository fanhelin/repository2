package com.wx.controller.chapter;


import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.base.BaseController;
import com.framework.common.Response;
import com.wx.entity.chapter.Chapter;
import com.wx.service.chapter.ChapterService;



@Controller
@RequestMapping("/chapter")
public class ChapterController extends BaseController {
    
	@Autowired
	ChapterService chapterService ;
	
	
	@ResponseBody
	@RequestMapping("/selectChapters.do")
	private JSONObject selectChapters(Chapter chapter){
		try {
			List<Chapter> list = chapterService.selectChapters(chapter) ;
			JSONObject ret = new JSONObject() ;
			ret.put("total", list.size()) ;
			ret.put("rows", JSONArray.fromObject(list)) ;
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj() ;
		}
	
	}
	

	@ResponseBody
	@RequestMapping("/addChapter.do")
	private Response addChapter(Chapter chapter){
		try {

			chapterService.addChapter(chapter) ;
			return Response.success("新增课程成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	

	@ResponseBody
	@RequestMapping("/updateChapter.do")
	private Response updateChapter(Chapter Chapter){
		try {
		
			chapterService.updateChapter(Chapter) ;
			return Response.success("修改课程成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	
	@ResponseBody
	@RequestMapping("/deleteChapter.do")
	private Response deleteChapter(Chapter Chapter){
		try {
		
			chapterService.deleteChapter(Chapter) ;
			return Response.success("删除课程成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	
	}
	
	
	@RequestMapping("/upLoadChapterVido.do")
	@ResponseBody
	public Response upLoadChapterVido(Chapter chapter,HttpServletRequest request , HttpServletResponse rep) throws IllegalStateException, IOException {
		rep.addHeader("Access-Control-Allow-Origin", "*");
				try {
					chapterService.updateChapterVido(chapter ,request) ;

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
					return Response.error(e.getMessage()) ;
				}

				return Response.success("上传成功" );

			
		}
	
	@RequestMapping("/upLoadChapterImg.do")
	@ResponseBody
	public Response upLoadChapterImg(Chapter chapter,HttpServletRequest request , HttpServletResponse rep) throws IllegalStateException, IOException {
		rep.addHeader("Access-Control-Allow-Origin", "*");
				try {
					chapterService.updateChapterImg(chapter ,request) ;

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
					return Response.error(e.getMessage()) ;
				}

				return Response.success("上传成功" );

			
		}
	
	
}
