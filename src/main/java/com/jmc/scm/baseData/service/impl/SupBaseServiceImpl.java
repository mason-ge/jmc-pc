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
import com.jmc.scm.baseData.dao.SupBaseDao;
import com.jmc.scm.baseData.dao.impl.SupBaseDaoImpl;
import com.jmc.scm.baseData.model.SupplierBankInfo;
import com.jmc.scm.baseData.model.SupplierBaseInfo;
import com.jmc.scm.baseData.service.SupBaseService;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.common.CommonEnum.UserActionType;

@Service("supBaseServiceImpl")
public class SupBaseServiceImpl implements SupBaseService {

	@Autowired
	@Qualifier(SupBaseDaoImpl.BEAN_ID)
	private SupBaseDao supBaseDao;

	public static final String BEAN_ID = "supBaseServiceImpl";

	@Override
	public void findBaseListPage(Page<SupplierBaseInfo> page,
			Map<String, Object> map) {
		supBaseDao.findBaseListPage(page, map);
	}

	@Override
	public SupplierBaseInfo findBaseByCode(String supCode) {
		return supBaseDao.findBaseByCode(supCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveOrUpdate(SupplierBaseInfo entity,
			List<SupplierBankInfo> list, String action) {
		Map<String, Object> result = new HashMap<>(0);
		List<SupplierBankInfo> list2Save = new ArrayList<>();
		List<SupplierBankInfo> list2Update = new ArrayList<>();

		String pkId = entity.getPkId();
		String supCode = entity.getSupCode();
		String itemId;

		if (UserActionType.SAVE.value().equals(action)) {
			if (StringUtils.isEmpty(pkId)) {
				entity.setStatus(CommonStatus.CREATED.value());
				result = supBaseDao.saveMain(entity);
			} else {
				supBaseDao.updateMain(entity);
			}
		} else if (UserActionType.DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.DELETED.value());
			entity.setDelFlg(DeletedFlag.YES.value());
			for (SupplierBankInfo t : list) {
				t.setDelFlg(DeletedFlag.YES.value());
			}
			supBaseDao.updateMain(entity);
			supBaseDao.updateBank(list2Update);
		}

		if (UserActionType.SAVE.value().equals(action)) {
			for (SupplierBankInfo t : list) {
				itemId = t.getPkId();
				if (StringUtils.isEmpty(itemId)) {
					t.setSupCode(supCode);
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
			supBaseDao.saveBank(list2Save);
			supBaseDao.updateBank(list2Update);
		}
		return result;

	}

	@Override
	public List<SupplierBankInfo> findBankByCode(String supCode) {
		return supBaseDao.findBankByCode(supCode);
	}

}
