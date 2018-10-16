package com.jmc.scm.sale.dao.impl;

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
import com.jmc.scm.baseData.model.SalePoItem;
import com.jmc.scm.baseData.model.SalePoMain;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.sale.dao.SalePoDao;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.system.service.SequenceService;
import com.jmc.scm.system.service.impl.SequenceServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Repository("salePoDaoImpl")
public class SalePoDaoImpl extends BaseDao implements SalePoDao {

	public static final String BEAN_ID = "salePoDaoImpl";

	@Autowired
	@Qualifier(SequenceServiceImpl.BEAN_ID)
	private SequenceService sequenceService;

	@Override
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		Object obj;
		try {
			StringBuffer sb = new StringBuffer();
			Map<String, Object> conMap = new HashMap<>(0);
			sb.append(" SELECT * from (                                                                  ");
			sb.append(" SELECT                                                                           ");
			sb.append(" t.*, (                                                                           ");
			sb.append(" CASE                                                                             ");
			sb.append(" WHEN (                                                                           ");
			sb.append(" COALESCE (TB1.tot_num, 0) - COALESCE (TB2.deli_num, 0)) <= 0 THEN                 ");
			sb.append(" '20'                                                                             ");
			sb.append(" ELSE                                                                             ");
			sb.append(" t.STATUS                                                                         ");
			sb.append(" END                                                                              ");
			sb.append(" ) AS so_status,                                                                  ");
			sb.append(" COALESCE (TB1.tot_num, 0) AS tot_num,                                            ");
			sb.append(" COALESCE (TB2.deli_num, 0) AS deli_num,                                          ");
			sb.append(" (                                                                                ");
			sb.append(" COALESCE (TB1.tot_num, 0) - COALESCE (TB2.deli_num, 0)                           ");
			sb.append(" ) AS owe_num                                                                     ");
			sb.append(" FROM                                                                             ");
			sb.append(" sale_po_main t                                                                   ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" i.SALE_PO_CODE,                                                                  ");
			sb.append(" i.CLIENT,                                                                        ");
			sb.append(" sum(i.NUMS) AS tot_num                                                           ");
			sb.append(" FROM                                                                             ");
			sb.append(" sale_po_item i                                                                   ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND i.DEL_FLG = '0'                                                              ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" i.SALE_PO_CODE,                                                                  ");
			sb.append(" i.CLIENT                                                                         ");
			sb.append(" ) TB1 ON (                                                                       ");
			sb.append(" t.SALE_PO_CODE = TB1.SALE_PO_CODE                                                ");
			sb.append(" AND t.CLIENT = TB1.CLIENT                                                        ");
			sb.append(" )                                                                                ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" m.SALE_PO_CODE,                                                                  ");
			sb.append(" m.CLIENT,                                                                        ");
			sb.append(" sum(i.NUMS) AS deli_num                                                          ");
			sb.append(" FROM                                                                             ");
			sb.append(" sale_out_item i                                                                  ");
			sb.append(" LEFT JOIN sale_out_main m ON (                                                   ");
			sb.append(" i.SALE_CODE = m.SALE_CODE                                                        ");
			sb.append(" AND i.CLIENT = m.CLIENT                                                          ");
			sb.append(" )                                                                                ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND i.DEL_FLG = '0'                                                              ");
			sb.append(" AND m.DEL_FLG = '0'                                                              ");
			sb.append(" AND m. STATUS = '20'                                                             ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" m.SALE_PO_CODE,                                                                  ");
			sb.append(" i.CLIENT                                                                         ");
			sb.append(" ) TB2 ON (                                                                       ");
			sb.append(" t.SALE_PO_CODE = TB2.SALE_PO_CODE                                                ");
			sb.append(" AND t.CLIENT = TB2.CLIENT                                                        ");
			sb.append(" )                                                                                ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND t.DEL_FLG = '0'                                                              ");

			sb.append(" )TBL                                                                             ");
			sb.append(" WHERE 1=1                                                                        ");
			sb.append("and TBL.client = :client 														 ");
			if (map != null && !map.isEmpty()) {
				// 单号
				obj = map.get("salePoCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.sale_po_code like :salePoCode	");
					conMap.put("salePoCode", "%" + obj + "%");
				}
				// 状态
				obj = map.get("soStatus");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.so_status like :soStatus	");
					conMap.put("soStatus", "%" + obj + "%");
				}
				// 客户编码
				obj = map.get("cltCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.clt_code like :cltCode	");
					conMap.put("cltCode", "%" + obj + "%");
				}
				// 客户名称
				obj = map.get("cltName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.clt_name like :cltName	");
					conMap.put("cltName", "%" + obj + "%");
				}
				// 发货日期(起)
				obj = map.get("createdD_F");
				if (!StringUtils.isEmpty(obj) && obj instanceof Date) {
					sb.append("    and TBL.created_d>= :createdD_F");
					conMap.put("createdD_F", obj);
				}
				// 发货日期(止)
				obj = map.get("createdD_T");
				if (!StringUtils.isEmpty(obj) && obj instanceof Date) {
					sb.append("    and TBL.created_d<= :createdD_T");
					conMap.put("createdD_T", obj);
				}
			}
			sb.append(" order by TBL.sale_po_code desc                               ");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public SalePoMain findBaseByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from SalePoMain t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.salePoCode = :saleCode ";
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                                                           ");
		sb.append(" t.*, COALESCE (TB1.tot_num, 0) AS tot_num                                       ");
		sb.append(" FROM                                                                             ");
		sb.append(" sale_po_main t                                                                  ");
		sb.append(" LEFT JOIN (                                                                      ");
		sb.append(" SELECT                                                                           ");
		sb.append(" i.SALE_PO_CODE,                                                                     ");
		sb.append(" i.CLIENT,                                                                        ");
		sb.append(" sum(i.NUMS) AS tot_num                                                          ");
		sb.append(" FROM                                                                             ");
		sb.append(" sale_po_item i                                                                  ");
		sb.append(" WHERE                                                                            ");
		sb.append(" i.DEL_FLG = '0'                                                                  ");
		sb.append(" GROUP BY                                                                         ");
		sb.append(" i.SALE_PO_CODE,                                                                     ");
		sb.append(" i.CLIENT                                                                         ");
		sb.append(" ) TB1 ON (                                                                       ");
		sb.append(" TB1.SALE_PO_CODE = T.SALE_PO_CODE                                                      ");
		sb.append(" AND TB1.CLIENT = t.CLIENT                                                        ");
		sb.append(" )                                                                                ");

		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND t.DEL_FLG = '0'                                                              ");
		sb.append(" AND t.CLIENT = :client                                                          ");
		sb.append(" AND t.SALE_PO_CODE = :saleCode                                                     ");
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryMapBySql(sb.toString(), conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveMain(SalePoMain entity) {
		Map<String, Object> result = new HashMap<>(1);
		try {
			// 生成流水码
			String seqCode = sequenceService.generateSequence("SalePoCode");
			entity.setSalePoCode(seqCode);
			entity.setClient(ScmUtil.getClient());
			entity.setStatus(CommonStatus.CREATED.value());

			entity.setCreatedBy(ContextHolder.getLoginUserName());
			entity.setCreatedD(new Date());
			entity.setUpdatedBy(ContextHolder.getLoginUserName());
			entity.setUpdatedD(new Date());
			entity.setDelFlg(DeletedFlag.NO.value());
			this.save(entity);
			result.put("seqCode", seqCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateMain(SalePoMain entity) {
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
	public void saveItem(List<SalePoItem> list) {
		try {
			for (SalePoItem entity : list) {
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
	public void updateItem(List<SalePoItem> list) {
		try {
			for (SalePoItem entity : list) {
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
	public List<SalePoItem> findItemByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from SalePoItem t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.salePoCode = :saleCode ";
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryList(hql, conMap);
	}

	@Override
	public List<PayMain> findPayListByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from PayMain t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.prestoCode = :saleCode ";
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryList(hql, conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void savePay(PayMain entity) {
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
	public List<Map<String, Object>> findItemMapByCode(String saleCode) {

		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                                                           ");
		sb.append(" t.*, p.SP_NAME,                                                                  ");
		sb.append(" p.PROD_CODE,                                                                     ");
		sb.append(" p.MOS_TYPE,                                                                      ");
		sb.append(" p.FIRST_CATG,                                                                    ");
		sb.append(" p.SEC_CATG,                                                                      ");
		sb.append(" p.FAC_PROD_CODE,                                                                 ");
		sb.append(" p.RET_PRICE,                                                                     ");
		sb.append(" p.GOLD_TYPE,                                                                     ");
		sb.append(" p.GOLD_COLOR,                                                                    ");
		sb.append(" p.MATERIAL,                                                                      ");
		sb.append(" p.CLARITY_G_WT,                                                                  ");
		sb.append(" p.WEIGHT,                                                                        ");
		sb.append(" p.SIZE,                                                                          ");
		sb.append(" p.GOLD_WASTAGE,                                                                  ");
		sb.append(" p.INCL_WASTAGE_G_WT,                                                             ");
		sb.append(" p.GOLD_PX,                                                                       ");
		sb.append(" p.FAC_G_PX,                                                                      ");
		sb.append(" p.CMPY_G_PX,                                                                     ");
		sb.append(" p.FAC_ACCY_PX,                                                                   ");
		sb.append(" p.COM_ACCY_PX,                                                                   ");
		sb.append(" p.FAC_ST_PX,                                                                     ");
		sb.append(" p.COM_ST_PX,                                                                     ");
		sb.append(" p.INIT_BAND_COST,                                                                ");
		sb.append(" p.BASE_COST,                                                                     ");
		sb.append(" p.OTHER_COST,                                                                    ");
		sb.append(" p.FAC_SETTLE_PX,                                                                 ");
		sb.append(" p.CER_TYPE,                                                                      ");
		sb.append(" p.CER_NUM,                                                                       ");
		sb.append(" p.COLOR,                                                                         ");
		sb.append(" p.NEAT,                                                                          ");
		sb.append(" p.NGTC_GOODS,                                                                    ");
		sb.append(" p.NGTC_TOT_QUALITY,                                                              ");
		sb.append(" p.NGTC_G_FINENESS,                                                               ");
		sb.append(" p.INLAIED_SCOOP,                                                                 ");
		sb.append(" p.MAN_ST_WT,                                                                     ");
		sb.append(" p.MAN_ST_INFO,                                                                   ");
		sb.append(" p.ASST_ST_WT,                                                                    ");
		sb.append(" p.ASST_ST_INFO,                                                                  ");
		sb.append(" p.ASST_ST_AMT,                                                                   ");
		sb.append(" p.LAU_DATE,                                                                      ");
		sb.append(" round(                                                                           ");
		sb.append(" (t.DISCT / p.RET_PRICE * 100),2) || '%' as disct_scale                           ");
		sb.append(" FROM                                                                             ");
		sb.append(" sale_out_item t                                                                  ");
		sb.append(" LEFT JOIN prop_uni_info p ON (                                                   ");
		sb.append(" t.BATCH_CODE = p.BATCH_CODE                                                      ");
		sb.append(" AND p.DEL_FLG = '0'                                                              ");
		sb.append(" AND p.CLIENT = t.CLIENT                                                          ");
		sb.append(" )                                                                                ");
		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND t.DEL_FLG = '0'                                                              ");
		sb.append(" AND t.CLIENT = :client                                                          ");
		sb.append(" AND t.sale_code = :saleCode                                                     ");
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryListBySql(sb.toString(), conMap);
	}

	@Override
	public List<Map<String, Object>> findPoItemInfoByShop() {
		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                                                           ");
		sb.append(" t.PROD_CODE,                                                                     ");
		sb.append(" t.PROD_NAME,                                                                     ");
		sb.append(" t.INLAIED_SCOOP,                                                                 ");
		sb.append(" t.SIZE,                                                                          ");
		sb.append(" t.NUMS,                                                                          ");
		sb.append(" t.GOLD_COLOR,                                                                    ");
		sb.append(" t.REMARK,                                                                        ");
		sb.append(" TF.FILE_HTTP_PATH AS img														 ");
		sb.append(" FROM                                                                             ");
		sb.append(" prod_shopping t                                                                  ");
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
		sb.append(" f.ATTR1,                                                                         ");
		sb.append(" FILE_HTTP_PATH                                                                   ");
		sb.append(" ) TF ON (t.PROD_CODE = TF.ATTR1)                                                 ");
		sb.append(" where 1=1                                                                        ");
		sb.append(" and t.CLIENT = :client                                                           ");
		sb.append(" and t.CREATED_BY = :logUser                                                      ");
		conMap.put("client", ScmUtil.getClient());
		conMap.put("logUser", ContextHolder.getLoginUserName());
		return this.queryListBySql(sb.toString(), conMap);
	}

	@Override
	public List<Map<String, Object>> findItemListByCode(String saleCode) {
		List<Map<String, Object>> result;
		try {
			Map<String, Object> conMap = new HashMap<>(2);
			StringBuffer sb = new StringBuffer();
			sb.append("select t.*,TF.FILE_HTTP_PATH AS img from sale_po_item t 							 ");
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
			sb.append(" f.ATTR1,                                                                         ");
			sb.append(" FILE_HTTP_PATH                                                                   ");
			sb.append(" ) TF ON (t.PROD_CODE = TF.ATTR1)                                                 ");
			sb.append(" where 1=1																		 ");
			sb.append(" and t.del_flg = '0'																 ");
			sb.append(" and t.client = :client															 ");
			sb.append(" and t.sale_po_code = :saleCode													 ");
			conMap.put("saleCode", saleCode);
			conMap.put("client", ScmUtil.getClient());
			result = this.queryListBySql(sb.toString(), conMap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
