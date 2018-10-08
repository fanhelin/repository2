package com.wx.mapper.payment;

import java.util.List;
import java.util.Map;

import com.wx.entity.pay.NotifyInfo;
import com.wx.entity.payment.WxPayment;

public interface PaymentMapper {
	public int addPayment(NotifyInfo payment) throws Exception;
	public List<WxPayment> findPaymentList(Map<?,?> map)throws Exception;
	public int findPaymentTotal(Map<?,?> map)throws Exception;
}
