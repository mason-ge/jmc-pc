package com.jmc.scm.system.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.dao.SequenceDao;
import com.jmc.scm.system.model.SysSequence;
import com.jmc.scm.util.ScmUtil;

@Repository("sequenceDaoImpl")
public class SequenceDaoImpl extends BaseDaoImpl implements SequenceDao {

	public static final String BEAN_ID = "sequenceDaoImpl";

	@Override
	public SysSequence findSequence(String sequenceName) {
		Map<String, Object> conMap = new HashMap<>(0);
		final String hql = "from SysSequence where sequenceName = :sequenceName and client = :client ";
		SysSequence result = null;
		conMap.put("sequenceName", sequenceName);
		conMap.put("client", ScmUtil.getClient());
		result = this.queryEntity(hql, conMap);

		return result;
	}

	@Override
	public void updateSequnce(SysSequence alloveSequence) {
		final String hql = "update SysSequence set expressionFormat = ?, serialNo = ? where pkId = ?";
		Object[] paramArray = new Object[3];

		paramArray[0] = alloveSequence.getExpressionFormat();
		paramArray[1] = alloveSequence.getSerialNo();
		paramArray[2] = alloveSequence.getPkId();

		this.update(hql, paramArray);
	}

}
