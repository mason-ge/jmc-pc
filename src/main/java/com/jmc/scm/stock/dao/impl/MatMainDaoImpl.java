package com.jmc.scm.stock.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.stock.dao.MatMainDao;
import com.jmc.scm.stock.model.StockInvMain;
import com.jmc.scm.stock.model.StockMvDetail;
import com.jmc.scm.util.ScmUtil;

@Repository("matMainDaoImpl")
public class MatMainDaoImpl extends BaseDao implements MatMainDao {
	public static final String BEAN_ID = "matMainDaoImpl";

	@Override
	public void findMainListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		Object obj;
		StringBuffer sb = new StringBuffer();
		Map<String, Object> conMap = new HashMap<>(0);
		try {
			sb.append(" SELECT * from (                                                                  ");
			sb.append(" select t.* from stock_inv_main t                                                 ");
			sb.append(" where 1=1                                                                        ");
			sb.append(" and t.DEL_FLG = '0'                                                              ");
			sb.append(" and t.CLIENT = :client                                                           ");
			sb.append(" )TBL                                                                             ");
			sb.append(" WHERE 1=1                                                                        ");
			if (map != null && !map.isEmpty()) {
				// 物料号
				obj = map.get("matCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.mat_code like :matCode	");
					conMap.put("matCode", "%" + obj + "%");
				}
				// 物料名称
				obj = map.get("matName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.mat_name like :matName	");
					conMap.put("matName", "%" + obj + "%");
				}
			}
			sb.append("order by TBL.created_d desc	");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void findDetailListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		Object obj;
		StringBuffer sb = new StringBuffer();
		Map<String, Object> conMap = new HashMap<>(0);
		try {
			sb.append(" SELECT * from (                                                                  ");
			sb.append(" select t.* from stock_mv_detail t                                                 ");
			sb.append(" where 1=1                                                                        ");
			sb.append(" and t.DEL_FLG = '0'                                                              ");
			sb.append(" and t.CLIENT = :client                                                           ");
			sb.append(" )TBL                                                                             ");
			sb.append(" WHERE 1=1                                                                        ");
			if (map != null && !map.isEmpty()) {
				// 物料号
				obj = map.get("matCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.mat_code = :matCode	");
					conMap.put("matCode", obj);
				}

			}
			sb.append("order by TBL.created_d desc	");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<StockInvMain> findMainListByListCodes(List<String> list) {
		Map<String, Object> conMap = new HashMap<>(0);
		String hql = "from StockInvMain t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";

		for (int i = 0; i < list.size(); i++) {
			// 判断第一次循环
			if (i == 0) {
				hql += "  and ( ";
			} else if (i <= list.size() - 1) {
				// 中间
				hql += "  or ";
			}
			hql += "  (t.matCode = '" + list.get(i) + "')  ";
			if (i == list.size() - 1) {
				// 最后一次循环
				hql += "  ) ";
			}
		}
		conMap.put("client", ScmUtil.getClient());
		return this.queryList(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveMain(List<StockInvMain> list) {
		try {
			for (StockInvMain entity : list) {
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
	public void updateMain(List<StockInvMain> list) {
		try {
			for (StockInvMain entity : list) {
				entity.setUpdatedBy(ContextHolder.getLoginUserName());
				entity.setUpdatedD(new Date());
				this.update(entity);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveItem(List<StockMvDetail> list) {
		try {
			for (StockMvDetail entity : list) {
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
	public void updateItem(List<StockMvDetail> list) {
		try {
			for (StockMvDetail entity : list) {
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
	public StockInvMain findMainByCode(String matCode) {
		Map<String, Object> conMap = new HashMap<>(0);
		String hql = "from StockInvMain t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.matCode = :matCode ";
		conMap.put("client", ScmUtil.getClient());
		conMap.put("matCode", matCode);
		return this.queryEntity(hql, conMap);
	}

}
