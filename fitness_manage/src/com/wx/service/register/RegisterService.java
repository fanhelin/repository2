package com.wx.service.register;


import java.util.List;

import net.sf.json.JSONObject;

import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.register.Payment;
import com.wx.entity.register.Student;

public interface RegisterService {
	
	 boolean saveStudentInfo(Student student) throws Exception ; 
	 Student selectStudentInfo(Student student) throws Exception ;
	 boolean updateRegInfo(Student student,String preFilePath) throws Exception ;
	 
	 JSONObject selectClassifyStudents(Student student) throws Exception ;
	 
	 boolean updateStudent(Student student) throws Exception ;
	 
	 List<Payment> selectPayments(Payment payment) throws Exception ;
	 
	 boolean addPayment(Payment payment) throws Exception ;
	 
	 boolean addPaymentNotify(NotifyInfo notifyInfo) throws Exception ;
}
