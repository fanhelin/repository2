package com.wx.mapper.chapter;

import java.util.List;

import com.wx.entity.chapter.Chapter;
import com.wx.entity.course.Course;








public interface ChapterMapper {
  List<Chapter> selectChapters(Chapter chapter) throws Exception ;
 
  int addChapter(Chapter chapter) throws Exception ;
  
  int updateChapter(Chapter chapter) throws Exception ; 
  
  int updateChapterVido(Chapter chapter) throws Exception ; 
  
  int updateChapterImg(Chapter chapter) throws Exception ; 

  int deleteChapter(Chapter chapter) throws Exception ; 
  
  int getSequence(Chapter chapter) throws Exception ; 
  
  int updateSequence(Chapter chapter) throws Exception ;  
  
  Chapter findChapterByCourse(Course course) throws Exception ;
  
}
