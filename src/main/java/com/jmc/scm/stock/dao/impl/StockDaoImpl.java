package com.jmc.scm.stock.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.stock.dao.StockDao;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.system.service.SequenceService;
import com.jmc.scm.system.service.impl.SequenceServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Repository("stockDaoImpl")
public class StockDaoImpl extends BaseDao implements StockDao {

	public static final String BEAN_ID = "stockDaoImpl";

	@Autowired
	@Qualifier(SequenceServiceImpl.BEAN_ID)
	private SequenceService sequenceService;

	@Override
	public PropUniInfo findPropInfoByCode(String batchCode) {
		Map<String, Object> conMap = new HashMap<>(0);
		String hql = "from PropUniInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.batchCode = :batchCode ";
		conMap.put("client", ScmUtil.getClient());
		conMap.put("batchCode", batchCode);
		return this.queryEntity(hql, conMap);
	}

	@Override
	public List<PropUniInfo> findPropInfoListByCodes(List<String> batchCodes) {
		Map<String, Object> conMap = new HashMap<>(0);
		String hql = "from PropUniInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.batchCode in (:batchCodes) ";
		conMap.put("client", ScmUtil.getClient());
		conMap.put("batchCodes", batchCodes);
		return this.queryList(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void savePropMain(List<PropUniInfo> list) {
		try {
			for (PropUniInfo entity : list) {
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
	public void updatePropMain(List<PropUniInfo> list) {
		try {
			for (PropUniInfo entity : list) {
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
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		Object obj;
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> conMap = new HashMap<>(0);
			sb.append(" select t.*,                                                                      ");
			sb.append(" b.PROD_NAME                                                                      ");
			sb.append(" from prop_uni_info t                                                             ");
			sb.append(" left join prod_base_info b                                                       ");
			sb.append(" on (t.PROD_CODE = b.PROD_CODE and t.CLIENT = b.CLIENT and b.del_flg = '0')       ");
			sb.append(" where 1=1                                                                        ");
			sb.append(" and t.del_flg = '0'                                                              ");
			if (map != null && !map.isEmpty()) {
				// 库存地点
				obj = map.get("stockLocale");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.stock_locale like :stockLocale	");
					conMap.put("stockLocale", "%" + obj + "%");
				}

				// 商品编码
				obj = map.get("prodCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.prod_code like :prodCode	");
					conMap.put("prodCode", "%" + obj + "%");
				}

				// 商品名称
				obj = map.get("prodName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and b.prod_name like :prodName	");
					conMap.put("prodName", "%" + obj + "%");
				}

				// 货号
				obj = map.get("batchCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.batch_code like :batchCode	");
					conMap.put("batchCode", "%" + obj + "%");
				}

				// 单品名称
				obj = map.get("spName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.sp_name like :spName	");
					conMap.put("spName", "%" + obj + "%");
				}
				
				// 借贷
				obj = map.get("loan");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.loan like :loan	");
					conMap.put("loan", "%" + obj + "%");
				}
				
				// 全部库存
				obj = map.get("allStock");
				if (!StringUtils.isEmpty(obj)) {
					if("false".equals(obj.toString())){
						sb.append("and t.qty <> 0    											 ");
					}
				}

			}
			
			sb.append("and t.client = :client 										 ");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
