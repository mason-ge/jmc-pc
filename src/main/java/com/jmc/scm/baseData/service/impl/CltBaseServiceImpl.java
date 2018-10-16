package com.jmc.scm.baseData.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.dao.CltBaseDao;
import com.jmc.scm.baseData.dao.impl.CltBaseDaoImpl;
import com.jmc.scm.baseData.model.ClientBankInfo;
import com.jmc.scm.baseData.model.ClientBaseInfo;
import com.jmc.scm.baseData.service.CltBaseService;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.common.CommonEnum.UserActionType;

@Service("cltBaseServiceImpl")
public class CltBaseServiceImpl implements CltBaseService {

	@Autowired
	@Qualifier(CltBaseDaoImpl.BEAN_ID)
	private CltBaseDao cltBaseDao;

	public static final String BEAN_ID = "cltBaseServiceImpl";

	@Override
	public void findBaseListPage(Page<ClientBaseInfo> page,
			Map<String, Object> map) {
		cltBaseDao.findBaseListPage(page, map);
	}

	@Override
	public ClientBaseInfo findBaseByCode(String cltCode) {
		return cltBaseDao.findBaseByCode(cltCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveOrUpdate(ClientBaseInfo entity,
			List<ClientBankInfo> list, String action) {
		Map<String, Object> result = new HashMap<>(0);
		List<ClientBankInfo> list2Save = new ArrayList<>();
		List<ClientBankInfo> list2Update = new ArrayList<>();

		String pkId = entity.getPkId();
		String cltCode = entity.getCltCode();
		String itemId;

		if (UserActionType.SAVE.value().equals(action)) {
			if (StringUtils.isEmpty(pkId)) {
				entity.setStatus(CommonStatus.CREATED.value());
				result = cltBaseDao.saveMain(entity);
			} else {
				cltBaseDao.updateMain(entity);
			}
		} else if (UserActionType.DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.DELETED.value());
			entity.setDelFlg(DeletedFlag.YES.value());
			for (ClientBankInfo t : list) {
				t.setDelFlg(DeletedFlag.YES.value());
			}
			cltBaseDao.updateMain(entity);
			cltBaseDao.updateBank(list2Update);
		}

		if (UserActionType.SAVE.value().equals(action)) {
			for (ClientBankInfo t : list) {
				itemId = t.getPkId();
				if (StringUtils.isEmpty(itemId)) {
					t.setCltCode(cltCode);
					list2Save.add(t);
				} else {
					// 判断是否需要删除
					EntityState status = EntityUtils.getState(t);
					if ((EntityState.DELETED.equals(status))) {
						t.setDelFlg(DeletedFlag.YES.value());
					}
					list2Update.add(t);
				}
			}
			cltBaseDao.saveBank(list2Save);
			cltBaseDao.updateBank(list2Update);
		}
		return result;

	}

	@Override
	public List<ClientBankInfo> findBankByCode(String cltCode) {
		return cltBaseDao.findBankByCode(cltCode);
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String cltCode) {
		return cltBaseDao.findBaseMapByCode(cltCode);

	}

}
