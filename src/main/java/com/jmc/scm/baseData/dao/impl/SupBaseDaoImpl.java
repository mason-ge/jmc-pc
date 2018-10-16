package com.jmc.scm.baseData.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.dao.SupBaseDao;
import com.jmc.scm.baseData.model.SupplierBankInfo;
import com.jmc.scm.baseData.model.SupplierBaseInfo;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.util.ScmUtil;

@Repository("supBaseDaoImpl")
public class SupBaseDaoImpl extends BaseDao implements SupBaseDao {

	public static final String BEAN_ID = "supBaseDaoImpl";

	@Override
	public void findBaseListPage(Page<SupplierBaseInfo> page,
			Map<String, Object> map) {
		Object obj;
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> convertParam = new HashMap<>(0);
			sb.append("from SupplierBaseInfo t where 1=1 and t.delFlg = '0' ");
			sb.append("and t.client = :client ");
			if (map != null && !map.isEmpty()) {
				/*
				 * 页面模糊查询参数
				 */
				// 供应商编码
				obj = map.get("supCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.supCode like :supCode	");
					convertParam.put("supCode", "%" + obj + "%");
				}
				// 供应商名称
				obj = map.get("supName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.supName like :supName	");
					convertParam.put("supName", "%" + obj + "%");
				}
			}
			convertParam.put("client", ScmUtil.getClient());
			this.queryPage(page, sb.toString(), convertParam);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public SupplierBaseInfo findBaseByCode(String supCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from SupplierBaseInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.supCode = :supCode ";
		conMap.put("supCode", supCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveMain(SupplierBaseInfo entity) {
		try {
			entity.setCreatedBy(ContextHolder.getLoginUserName());
			entity.setCreatedD(new Date());
			entity.setUpdatedBy(ContextHolder.getLoginUserName());
			entity.setUpdatedD(new Date());
			entity.setDelFlg(DeletedFlag.NO.value());
			entity.setClient(ScmUtil.getClient());
			this.save(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateMain(SupplierBaseInfo entity) {
		try {
			entity.setUpdatedBy(ContextHolder.getLoginUserName());
			entity.setUpdatedD(new Date());
			this.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveBank(List<SupplierBankInfo> list) {
		try {
			for (SupplierBankInfo entity : list) {
				entity.setCreatedBy(ContextHolder.getLoginUserName());
				entity.setCreatedD(new Date());
				entity.setUpdatedBy(ContextHolder.getLoginUserName());
				entity.setUpdatedD(new Date());
				entity.setDelFlg(DeletedFlag.NO.value());
				entity.setClient(ScmUtil.getClient());
				this.save(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateBank(List<SupplierBankInfo> list) {
		try {
			for (SupplierBankInfo entity : list) {
				entity.setUpdatedBy(ContextHolder.getLoginUserName());
				entity.setUpdatedD(new Date());
				this.update(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<SupplierBankInfo> findBankByCode(String supCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from SupplierBankInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.supCode = :supCode ";
		conMap.put("supCode", supCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryList(hql, conMap);
	}

}
