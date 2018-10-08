package com.wx.controller.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.framework.base.BaseController;
import com.framework.common.Config;
import com.framework.common.Response;
import com.util.PageBean;
import com.wx.entity.client.Client;
import com.wx.service.client.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController extends BaseController {
  
	@Autowired
	ClientService clientService ;
	
	@ResponseBody
	@RequestMapping("/selectClients.do")
	private JSONObject selectClients(@RequestParam Map<String,Object> client){
		
		try {
			PageBean<Client> page = clientService.selectClients(client) ;
			JSONObject ret = new JSONObject() ;
			ret.put("total", page.getTotal()) ;
			ret.put("rows", JSONArray.fromObject(page.getRows())) ;
			return ret;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return retEmptyJsonObj() ;
		}	
	}
	
	@RequestMapping(value="updateSign.do",method = RequestMethod.POST)
	@ResponseBody
	public Response updateSign(@RequestParam("file") MultipartFile file,
			@RequestParam("app_info_code") String app_info_code,
			@RequestParam("openid") String openid,
			@RequestParam("con_sign_days") int con_sign_days,
			@RequestParam("last_sign_day") String  last_sign_day,
			@RequestParam("score") int score
			){
		try {
			 Client client=new Client();
			 client.setApp_info_code(app_info_code);
			 client.setOpenid(openid);
			 client.setScore(score+Integer.parseInt(Config.getConfig("sign_sorce")));
			 if(last_sign_day!=null&&last_sign_day.length()>10){
				 DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
				 Date date=format.parse(last_sign_day);
				 if(new Date().getTime()-date.getTime()<=24*60*60*1000)
					 client.setCon_sign_days(client.getCon_sign_days()+1);
			 }
			 this.clientService.updateSignInfo(client,file);
			 return Response.success("成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.error(e.getMessage()) ;
		}
	}
}
