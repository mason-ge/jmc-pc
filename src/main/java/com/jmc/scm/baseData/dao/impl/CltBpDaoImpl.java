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
import com.jmc.scm.baseData.dao.CltBpDao;
import com.jmc.scm.baseData.model.ClientBp;
import com.jmc.scm.baseData.model.ClientBpChangeList;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.util.ScmUtil;

@Repository("cltBpDaoImpl")
public class CltBpDaoImpl extends BaseDao implements CltBpDao {

	public static final String BEAN_ID = "cltBpDaoImpl";

	@Override
	public void findCltBpListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		Object obj;
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> conMap = new HashMap<>(0);
			sb.append(" SELECT                                                                           ");
			sb.append(" t.*, COALESCE (b.BP, 0) AS bp                                                    ");
			sb.append(" FROM                                                                             ");
			sb.append(" client_base_info t                                                               ");
			sb.append(" LEFT JOIN client_bp b ON (                                                       ");
			sb.append(" t.CLIENT = b.CLIENT                                                              ");
			sb.append(" AND t.CLT_CODE = b.CLT_CODE                                                      ");
			sb.append(" )                                                                                ");
			sb.append(" where 1=1                                                          			     ");
			sb.append(" AND t.DEL_FLG = '0'                                                              ");
			sb.append("and t.client = :client ");
			if (map != null && !map.isEmpty()) {
				/*
				 * 页面模糊查询参数
				 */
				// 客户编码
				obj = map.get("cltCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.clt_code like :cltCode	");
					conMap.put("cltCode", "%" + obj + "%");
				}
				// 客户名称
				obj = map.get("cltName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.clt_name like :cltName	");
					conMap.put("cltName", "%" + obj + "%");
				}
			}
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<ClientBpChangeList> findBpChangeList(String cltCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ClientBpChangeList t where 1=1 ";
		hql += "and t.client = :client ";
		hql += "and t.cltCode = :cltCode ";
		hql += "order by t.createdD desc ";
		conMap.put("cltCode", cltCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryList(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveChangeList(ClientBpChangeList dsNew) {
		try {
			dsNew.setCreatedBy(ContextHolder.getLoginUserName());
			dsNew.setCreatedD(new Date());
			dsNew.setClient(ScmUtil.getClient());
			this.save(dsNew);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ClientBp findtCltBpByCode(String cltCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ClientBp t where 1=1 ";
		hql += "and t.client = :client ";
		hql += "and t.cltCode = :cltCode ";
		conMap.put("cltCode", cltCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveCltBp(ClientBp bpNew) {
		try {
			bpNew.setCreatedBy(ContextHolder.getLoginUserName());
			bpNew.setCreatedD(new Date());
			bpNew.setClient(ScmUtil.getClient());
			this.save(bpNew);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateBp(ClientBp cltBp) {
		try {
			cltBp.setUpdatedBy(ContextHolder.getLoginUserName());
			cltBp.setUpdatedD(new Date());
			this.update(cltBp);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
