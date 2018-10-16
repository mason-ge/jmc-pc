package com.jmc.scm.baseData.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.dao.CltBpDao;
import com.jmc.scm.baseData.dao.impl.CltBpDaoImpl;
import com.jmc.scm.baseData.model.ClientBp;
import com.jmc.scm.baseData.model.ClientBpChangeList;
import com.jmc.scm.baseData.service.CltBpService;

@Service("cltBpServiceImpl")
public class CltBpServiceImpl implements CltBpService {

	@Autowired
	@Qualifier(CltBpDaoImpl.BEAN_ID)
	private CltBpDao cltBpDao;

	public static final String BEAN_ID = "cltBpServiceImpl";

	@Override
	public void findCltBpListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		cltBpDao.findCltBpListPage(page, map);
	}

	@Override
	public List<ClientBpChangeList> findBpChangeList(String cltCode) {
		return cltBpDao.findBpChangeList(cltCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveChangeList(ClientBpChangeList dsNew) {
		BigDecimal changeBp = BigDecimal.ZERO;
		BigDecimal resultBp = BigDecimal.ZERO;
		BigDecimal existBp = BigDecimal.ZERO;
		changeBp = dsNew.getBp();
		// 根据客户编码查询客户已有的积分
		ClientBp cltBp = this.findtCltBpByCode(dsNew.getCltCode());
		if (cltBp != null) {
			// 校验结果的积分不能为负数
			existBp = cltBp.getBp();
			resultBp = existBp.add(changeBp);
			if (resultBp.compareTo(BigDecimal.ZERO) < 0) {
				throw new RuntimeException("此次更改积分会造成该客户：" + dsNew.getCltName()
						+ "积分为负,请修改！");
			} else {
				cltBp.setBp(resultBp);
				cltBpDao.updateBp(cltBp);
			}
		} else {
			// 没有积分时直接插入积分
			// 判断积分是否为负数
			if (changeBp.compareTo(BigDecimal.ZERO) < 0) {
				throw new RuntimeException("此次更改积分会造成该客户：" + dsNew.getCltName()
						+ "积分为负,请修改！");
			} else {
				ClientBp bpNew = new ClientBp();
				bpNew.setCltCode(dsNew.getCltCode());
				bpNew.setCltName(dsNew.getCltName());
				bpNew.setBp(changeBp);
				cltBpDao.saveCltBp(bpNew);
			}

		}
		// 直接插入明细记录
		cltBpDao.saveChangeList(dsNew);

	}

	@Override
	public ClientBp findtCltBpByCode(String cltCode) {
		return cltBpDao.findtCltBpByCode(cltCode);
	}

}
