package com.wx.mapper.client;

import java.util.List;


import java.util.Map;

import com.wx.entity.client.Client;




public interface ClientMapper {
  List<Client> selectClients(Map<?,?> client) throws Exception ;
  public int findClientTotal(Map<?,?> map)throws Exception;
 
  public Client getUserInfo(Map<?,?> map) throws Exception ;
  
  public int addClient(Client client) throws Exception ;

  public int updateClient(Client client) throws Exception ;
  
  public int updateSignInfo(Client client)throws Exception;
  
}
