package com.wx.mapper.register;

import java.util.List;

import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.register.LicenseType;
import com.wx.entity.register.Payment;
import com.wx.entity.register.Station;
import com.wx.entity.register.Student;



public interface RegisterMapper {
	 List <Station> selectStations(Station station) throws Exception ;
	 
	 List<LicenseType> selectLicenseTypes(LicenseType licenseType) throws Exception ;
	 
	 List<Payment> selectPayments(Payment payment) throws Exception ; 
	 
	 void saveStudentInfo(Student student) throws Exception ; 
	 
	 Student selectStudentInfo(Student student) throws Exception ;
	 
	 int updateRegInfo(Student student) throws Exception ;
	 
	 List<Student> selectClassifyStudents(Student student) throws Exception ;
	 
	// List<Payment> selectPaymens(Payment payment) throws Exception ;
	 
	 int addPayment(Payment payment) throws Exception ;
	 
	 int addPaymentNotify(NotifyInfo notifyInfo) throws Exception ;

}
