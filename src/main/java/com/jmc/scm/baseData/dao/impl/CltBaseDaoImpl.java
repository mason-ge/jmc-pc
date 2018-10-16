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
import com.jmc.scm.baseData.dao.CltBaseDao;
import com.jmc.scm.baseData.model.ClientBankInfo;
import com.jmc.scm.baseData.model.ClientBaseInfo;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.util.ScmUtil;

@Repository("cltBaseDaoImpl")
public class CltBaseDaoImpl extends BaseDao implements CltBaseDao {

	public static final String BEAN_ID = "cltBaseDaoImpl";

	@Override
	public void findBaseListPage(Page<ClientBaseInfo> page,
			Map<String, Object> map) {
		Object obj;
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> conMap = new HashMap<>(0);
			sb.append("from ClientBaseInfo t where 1=1 and t.delFlg = '0' ");
			sb.append("and t.client = :client ");
			if (map != null && !map.isEmpty()) {
				/*
				 * 页面模糊查询参数
				 */
				// 客户编码
				obj = map.get("cltCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.cltCode like :cltCode	");
					conMap.put("cltCode", "%" + obj + "%");
				}
				// 客户名称
				obj = map.get("cltName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.cltName like :cltName	");
					conMap.put("cltName", "%" + obj + "%");
				}
			}
			conMap.put("client", ScmUtil.getClient());
			this.queryPage(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public ClientBaseInfo findBaseByCode(String cltCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ClientBaseInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.cltCode = :cltCode ";
		conMap.put("cltCode", cltCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveMain(ClientBaseInfo entity) {
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
	public void updateMain(ClientBaseInfo entity) {
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
	public void saveBank(List<ClientBankInfo> list) {
		try {
			for (ClientBankInfo entity : list) {
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
	public void updateBank(List<ClientBankInfo> list) {
		try {
			for (ClientBankInfo entity : list) {
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
	public List<ClientBankInfo> findBankByCode(String cltCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ClientBankInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.cltCode = :cltCode ";
		conMap.put("cltCode", cltCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryList(hql, conMap);
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String cltCode) {
		StringBuffer sb = new StringBuffer();
		Map<String, Object> result = new HashMap<>(0);
		Map<String, Object> conMap = new HashMap<>(2);

		try {
			sb.append(" SELECT                                                                           ");
			sb.append(" t.*, b.CLT_NAME AS INTCLIENT_NAME                                                ");
			sb.append(" FROM                                                                             ");
			sb.append(" client_base_info t                                                               ");
			sb.append(" LEFT JOIN client_base_info b ON (                                                ");
			sb.append(" t.CLIENT = b.CLIENT                                                              ");
			sb.append(" AND t.INTCLIENT_CODE = b.CLT_CODE                                                ");
			sb.append(" AND b.DEL_FLG = '0'                                                              ");
			sb.append(" )                                                                                ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND t.DEL_FLG = '0'                                                              ");
			sb.append(" and t.client = :client 															 ");
			sb.append(" and t.clt_code = :cltCode 														 ");
			conMap.put("cltCode", cltCode);
			conMap.put("client", ScmUtil.getClient());
			result = this.queryMapBySql(sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;
	}

}
