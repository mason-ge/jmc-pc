package com.jmc.scm.system.service;

import java.util.List;

import com.jmc.scm.system.model.ClientCfg;

/**
 * 客户端配置service
 * 
 * @author Mason_Ge
 * 
 */
public interface ClientCfgService {
	/**
	 * 获取客户端配置
	 * 
	 * @return
	 */
	ClientCfg getCfg();

	/**
	 * 获取客户端配置根据指定客户端号
	 * 
	 * @return
	 */
	ClientCfg getCfgByClient(String client);

	/**
	 * 获取所有客户端号
	 * 
	 * @return
	 */
	List<String> getAllClients();

	/**
	 * 获取所有不重复FTP信息的客户端号
	 * 
	 * @return
	 */
	List<String> getClientsWithNoRepeatFtp();

}
