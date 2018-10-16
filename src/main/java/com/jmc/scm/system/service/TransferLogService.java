package com.jmc.scm.system.service;

import com.jmc.scm.system.model.SysTransferLog;

/**
 * 三方接口传输日志Service
 * 
 * @author 三影塔
 * 
 */
public interface TransferLogService {

	/**
	 * 保存日志
	 * 
	 * @param log
	 */
	void saveLog(SysTransferLog log);

}
