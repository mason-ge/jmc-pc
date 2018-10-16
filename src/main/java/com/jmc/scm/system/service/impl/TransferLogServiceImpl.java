package com.jmc.scm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.model.SysTransferLog;
import com.jmc.scm.system.service.SequenceService;
import com.jmc.scm.system.service.TransferLogService;

/**
 * 三方接口传输日志Service实现
 * 
 * @author 三影塔
 * 
 */
@Service(TransferLogServiceImpl.BEAN_ID)
public class TransferLogServiceImpl implements TransferLogService {

	public static final String BEAN_ID = "transferLogServiceImpl";

	@Autowired
	@Qualifier(SequenceServiceImpl.BEAN_ID)
	private SequenceService sequenceService;

	@Autowired
	@Qualifier(BaseDaoImpl.BEAN_ID)
	private BaseDao baseDao;

	@Override
	public void saveLog(SysTransferLog log) {
		final String sequenceCode = "TransferCode";
		String transferCode;

		transferCode = sequenceService.generateSequence(sequenceCode);

		log.setTransferCode(transferCode);

		baseDao.save(log);
	}

}
