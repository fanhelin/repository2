package com.wx.serviceImpl.client;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.framework.base.BaseService;
import com.framework.common.Config;
import com.framework.common.StaticFinal;
import com.framework.exception.RunTimeException;
import com.util.PageBean;
import com.wx.entity.client.Client;
import com.wx.mapper.client.ClientMapper;
import com.wx.service.client.ClientService;

@Service
public class ClientServiceImpl extends BaseService implements ClientService {
	
	 @Autowired
	 ClientMapper mapper ;
	 
	 private final static String SIGN_IMAGE_PATH="client";

	@Override
	public PageBean<Client> selectClients(Map<String,Object> map) throws Exception {
		// TODO Auto-generated method stub
		PageBean<Client> pageBean=new PageBean<Client>(Integer.parseInt(map.get("rows").toString()),Integer.parseInt(map.get("page").toString()));
		pageBean.setTotal(this.mapper.findClientTotal(map));
		map.put("currentLine", (pageBean.getPageNumber()-1)*pageBean.getPageSize());
		pageBean.setRows(this.mapper.selectClients(map));
		return pageBean;
	}

	@Override
	public void addClient(Client client) throws Exception {
		try {
			// TODO Auto-generated method stub
			int ret = mapper.addClient(client);
			if (ret != 1) {
				throw new Exception("注册失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage()) ;
		}
		
		
	}

	@Override
	public void updateClient(Client client) throws Exception {
		// TODO Auto-generated method stub
		try {
			// TODO Auto-generated method stub
			int ret = mapper.updateClient(client);
			if (ret != 1) {
				throw new Exception("修改注册信息失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e.getMessage()) ;
		}
		
	}

	@Override
	public void updateSignInfo(Client client,MultipartFile file)
			throws Exception {
		try {
			String webPath = Config.getConfig(StaticFinal.UPLOAD_FOLD)
					+ File.separator + SIGN_IMAGE_PATH;
			File webPathFile = new File(webPath);
			if (!webPathFile.exists()) {
				webPathFile.mkdirs();
			}
			File targetFile = null;
			if (file != null) {
				String fileName = file.getOriginalFilename();
				if (fileName == null || "".equals(fileName.trim()))
					throw new RuntimeException("文件为空");
				fileName = client.getOpenid() + "_" + fileName;
				client.setSign_image(fileName);
				targetFile = new File(webPathFile, fileName);
				if (targetFile.exists()) {
					targetFile.deleteOnExit();
				}
				file.transferTo(targetFile);
			}
			int ret = this.mapper.updateSignInfo(client);
			if (ret < 1 && targetFile != null) {
				targetFile.deleteOnExit();
				throw new Exception("修改图片失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RunTimeException(e.getMessage());
		}
	}



	
}
