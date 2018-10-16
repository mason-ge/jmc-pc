package com.jmc.scm.baseData.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.dao.ProdBaseDao;
import com.jmc.scm.baseData.model.ProdBaseInfo;
import com.jmc.scm.baseData.model.ProdShopping;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.system.model.SysEnumv;
import com.jmc.scm.util.ScmUtil;

@Repository("prodBaseDaoImpl")
public class ProdBaseDaoImpl extends BaseDao implements ProdBaseDao {

	public static final String BEAN_ID = "prodBaseDaoImpl";

	@SuppressWarnings("unchecked")
	@Override
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		Object obj;
		String enumvCode, enumvDesc;
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> conMap = new HashMap<>(0);
			sb.append(" SELECT                                                                           ");
			sb.append(" TF.FILE_HTTP_PATH AS img,                                                        ");
			sb.append(" t.*                                                                              ");
			sb.append(" FROM                                                                             ");
			sb.append(" prod_base_info t                                                                 ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" f.ATTR1,                                                                         ");
			sb.append(" f.FILE_HTTP_PATH,                                                                ");
			sb.append(" max(f.CREATED_D)                                                                 ");
			sb.append(" FROM                                                                             ");
			sb.append(" sys_file f                                                                       ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" f.ATTR1                                                                         ");
			//sb.append(" FILE_HTTP_PATH                                                                   ");
			sb.append(" ) TF ON (t.PROD_CODE = TF.ATTR1)                                                 ");
			sb.append(" where 1=1																		 ");
			sb.append(" and t.del_flg = '0'																 ");
			sb.append("and t.client = :client 															 ");
			if (map != null && !map.isEmpty()) {
				SysEnumv mapEnum = (SysEnumv) map.get("sort");
				Map<String, Object> mapCon = (Map<String, Object>) map
						.get("condition");
				if (mapEnum != null) {
					enumvCode = mapEnum.getEnumvCode();
					enumvDesc = mapEnum.getEnumvDesc();
					// 判断是否是所有品类
					if (!"所有品类".equals(enumvCode)) {
						// 判断是否是一级品类
						if ("".equals(enumvDesc) || enumvDesc == null) {
							sb.append("and t.first_catg = :firstCatg	");
							conMap.put("firstCatg", enumvCode);
						} else {
							sb.append("and t.first_catg = :firstCatg	");
							sb.append("and t.sec_catg = :secCatg	");
							conMap.put("firstCatg", enumvDesc);
							conMap.put("secCatg", enumvCode);
						}
					}
				}
				/*
				 * 页面模糊查询参数
				 */
				// 商品编码
				obj = mapCon.get("prodCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.prod_code like :prodCode	");
					conMap.put("prodCode", "%" + obj + "%");
				}
				// 商品名称
				obj = mapCon.get("prodName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and t.prod_name like :prodName	");
					conMap.put("prodName", "%" + obj + "%");
				}
			}
			sb.append("order by t.created_d desc	");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public ProdBaseInfo findBaseByCode(String prodCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ProdBaseInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.prodCode = :prodCode ";
		conMap.put("prodCode", prodCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveMain(ProdBaseInfo entity) {
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
	public void updateMain(ProdBaseInfo entity) {
		try {
			entity.setUpdatedBy(ContextHolder.getLoginUserName());
			entity.setUpdatedD(new Date());
			this.update(entity);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public BigDecimal getShopNum() {
		StringBuffer sb = new StringBuffer();
		Map<String, Object> conMap = new HashMap<>(2);
		List<Map<String, Object>> list = new ArrayList<>();
		sb.append(" select COALESCE(sum(t.nums),0) as nums from prod_shopping t  ");
		sb.append(" where 1=1 													 ");
		sb.append(" and t.client = :client 								 		 ");
		sb.append(" and t.created_by = :logUser 								 ");
		conMap.put("logUser", ContextHolder.getLoginUserName());
		conMap.put("client", ScmUtil.getClient());
		list = this.queryListBySql(sb.toString(), conMap);
		return ScmUtil.getBigDecimal((list.get(0).get("nums")));
	}

	@Override
	public void saveShop(ProdShopping entity) {
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
	}

	@Override
	public List<ProdShopping> findShopList() {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ProdShopping t where 1=1 ";
		hql += "and t.client = :client ";
		hql += "and t.createdBy = :logUser ";
		conMap.put("client", ScmUtil.getClient());
		conMap.put("logUser", ContextHolder.getLoginUserName());
		return this.queryList(hql, conMap);
	}

	@Override
	public void delShop() {
		List<ProdShopping> list = this.findShopList();
		this.deleteAll(list);
	}

	@Override
	public List<ProdBaseInfo> findProdListByCodes(List<String> list) {
		Map<String, Object> conMap = new HashMap<>(3);
		String hql = "from ProdBaseInfo t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.prodCode in (:prodList) ";
		hql += "and t.client = :client ";
		hql += "and t.createdBy = :logUser ";
		conMap.put("prodList", list);
		conMap.put("client", ScmUtil.getClient());
		conMap.put("logUser", ContextHolder.getLoginUserName());
		return this.queryList(hql, conMap);
	}
}
