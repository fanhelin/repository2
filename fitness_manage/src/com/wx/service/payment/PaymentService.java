package com.wx.service.payment;

import java.util.Map;

import com.util.PageBean;
import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.payment.WxPayment;
import com.wx.entity.sys.AppInfo;
import com.wx.redis.JRedisClient;

public interface PaymentService {
	public PageBean<WxPayment> findPaymentList(Map<String,Object> map) throws Exception;
	public boolean addPayment(NotifyInfo payment,JRedisClient redis,int WX_NOTIFY_EXPIRE) throws Exception;
}
