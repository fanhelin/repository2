package com.wx.serviceImpl.register;

import java.io.File;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.base.BaseService;
import com.framework.exception.RunTimeException;
import com.util.FunHelper;
import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.register.Payment;
import com.wx.entity.register.Student;
import com.wx.mapper.register.RegisterMapper;
import com.wx.service.register.RegisterService;

@Service
public class RegisterServiceImpl extends BaseService 
implements RegisterService {
	
	@Autowired
	RegisterMapper  registerMapper ;

	 
	@Override
	public boolean saveStudentInfo(Student student) throws Exception {
		// TODO Auto-generated method stub
		try {
			 registerMapper.saveStudentInfo(student);
			 return true ;
		} catch (Exception e) {
			// TODO: handle exception
			
		    throw new RunTimeException(e.getMessage()) ;
		} 
	}


	@Override
	public Student selectStudentInfo(Student student) throws Exception {
		// TODO Auto-generated method stub
		return registerMapper.selectStudentInfo(student);
	}


	@Override
	public boolean updateRegInfo(Student student,String preFilePath) throws Exception {
		// TODO Auto-generated method stub
		try{
				int ret = registerMapper.updateRegInfo(student);
				if(ret>=1 ){
					if(FunHelper.isValidate(preFilePath, 2)){
						File oldfile = new File(preFilePath) ;
						oldfile.delete() ;
					}
					
				}else{
					throw new Exception("更新图片失败") ;
				}
				return true ;
		}catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage()) ;
		}
		
	}


	@Override
	public JSONObject selectClassifyStudents(Student student) throws Exception {
		// TODO Auto-generated method stub
		
		JSONObject retObject = new JSONObject() ;
		
		try {
			
			student.setClassify("cs") ;
	
			List<Student> lst_cs = registerMapper.selectClassifyStudents(student) ;
			retObject.put("students_cs", JSONArray.fromObject(lst_cs)) ;
			
			student.setClassify("sh") ;
			List<Student> lst_sh = registerMapper.selectClassifyStudents(student) ;
			retObject.put("students_sh", JSONArray.fromObject(lst_sh)) ;
			
			student.setClassify("yjf") ;
			List<Student> lst_yjf = registerMapper.selectClassifyStudents(student) ;
			retObject.put("students_yjf", JSONArray.fromObject(lst_yjf)) ;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage()) ;
		}
		return retObject ;
	}


	@Override
	public boolean updateStudent(Student student) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			int ret = registerMapper.updateRegInfo(student) ;
			if(ret==1){
				return true ;
			}
			return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RunTimeException(e.getMessage()) ;
		}
		
	}


	@Override
	public List<Payment> selectPayments(Payment payment) throws Exception {
		// TODO Auto-generated method stub
		return registerMapper.selectPayments(payment);
	}


	@Override
	public boolean addPayment(Payment payment) throws Exception {
		// TODO Auto-generated method stub
		try {
			
			int ret = registerMapper.addPayment(payment) ;
			if(ret >0){
				Student student = new Student() ;
				student.setApp_info_code(payment.getApp_info_code()) ;
				student.setCode(payment.getStudent_code()) ;
				student.setPaid_amount(payment.getAmount()) ;
				ret = registerMapper.updateRegInfo(student) ;
				if(ret<1){
					throw new Exception("更新总金额失败");
				}
				
				return true ;
			}else{
				throw new Exception("付费写入失败") ;
			}

			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RunTimeException(e.getMessage()) ;
		}
		
	}


	@Override
	public boolean addPaymentNotify(NotifyInfo notifyInfo) throws Exception {
		// TODO Auto-generated method stub
try {
			
			int ret = registerMapper.addPaymentNotify(notifyInfo);
			if(ret >0){
				Student student = new Student() ;
				//student.setApp_info_code(payment.getApp_info_code()) ;
				student.setApp_id(notifyInfo.getAppid()) ;
				student.setCode(notifyInfo.getOpenid()) ;
				student.setPaid_amount(notifyInfo.getTotal_fee()/100) ;
				ret = registerMapper.updateRegInfo(student) ;
				if(ret<1){
					throw new Exception("更新总金额失败");
				}
				
				return true ;
			}else{
				throw new Exception("付费写入失败") ;
			}

	
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new RunTimeException(e.getMessage()) ;
		}
	}

}
