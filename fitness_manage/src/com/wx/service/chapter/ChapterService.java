package com.wx.service.chapter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.wx.entity.chapter.Chapter;
import com.wx.entity.course.Course;

public interface ChapterService {
	 List<Chapter> selectChapters(Chapter Chapter) throws Exception ;
	 
	  void addChapter(Chapter Chapter) throws Exception ;
	  
	  void updateChapter(Chapter Chapter) throws Exception ; 
	  
	  void updateChapterVido(Chapter chapter,HttpServletRequest request) throws Exception ; 
	  
	  void deleteChapter(Chapter Chapter) throws Exception ; 
	  
	  void updateChapterImg(Chapter chapter,HttpServletRequest request) throws Exception ; 
	  
	  Chapter findChapterByCourse(Course course) throws Exception ;
	  
}
