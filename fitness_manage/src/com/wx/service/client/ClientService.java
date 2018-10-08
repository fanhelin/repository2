package com.wx.service.client;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.util.PageBean;
import com.wx.entity.client.Client;

public interface ClientService {
	PageBean<Client> selectClients(Map<String,Object> map) throws Exception ;
	
	void addClient(Client client) throws Exception ;
	
	void updateClient(Client client) throws Exception ; 
	
    void updateSignInfo(Client client, MultipartFile file)throws Exception;
}
