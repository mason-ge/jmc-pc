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
import com.jmc.scm.baseData.model.ClientBpMoneyRelat;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.sale.dao.SaleOutDao;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.sale.model.SaleOutItem;
import com.jmc.scm.sale.model.SaleOutMain;
import com.jmc.scm.system.service.SequenceService;
import com.jmc.scm.system.service.impl.SequenceServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Repository("saleOutDaoImpl")
public class SaleOutDaoImpl extends BaseDao implements SaleOutDao {

	public static final String BEAN_ID = "saleOutDaoImpl";

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
			sb.append(" t.*, COALESCE(TB1.tot_num,0) as tot_num,                                         ");
			sb.append(" COALESCE(TB1.tot_sale_amt,0) as tot_sale_amt,                                    ");
			sb.append(" COALESCE(TB2.tot_pay,0) as  tot_pay,                                             ");
			sb.append(" COALESCE(TB1.tot_sale_amt,0) - COALESCE(TB2.tot_pay,0) as tot_left_pay,          ");
			sb.append(" TB3.last_pay_day                                                                 ");
			sb.append(" FROM                                                                             ");
			sb.append(" sale_out_main t                                                                  ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" i.CLIENT,                                                                        ");
			sb.append(" i.SALE_CODE,                                                                     ");
			sb.append(" sum(i.NUMS) AS tot_num,                                                          ");
			sb.append(" sum(i.sale_price) AS tot_sale_amt                                                ");
			sb.append(" FROM                                                                             ");
			sb.append(" sale_out_item i                                                                  ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND i.DEL_FLG = '0'                                                              ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" i.CLIENT,                                                                        ");
			sb.append(" i.SALE_CODE                                                                      ");
			sb.append(" ) TB1 ON (                                                                       ");
			sb.append(" TB1.SALE_CODE = t.SALE_CODE                                                      ");
			sb.append(" AND t.CLIENT = TB1.CLIENT                                                        ");
			sb.append(" )                                                                                ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" p.CLIENT,                                                                        ");
			sb.append(" p.PRESTO_CODE,                                                                   ");
			sb.append(" sum(p.PAY_AMOUNT) AS tot_pay                                                     ");
			sb.append(" FROM                                                                             ");
			sb.append(" pay_main p                                                                       ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND p.DEL_FLG = '0'                                                              ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" p.CLIENT,                                                                        ");
			sb.append(" p.PRESTO_CODE                                                                    ");
			sb.append(" ) TB2 ON (                                                                       ");
			sb.append(" TB2.PRESTO_CODE = t.SALE_CODE                                                    ");
			sb.append(" AND TB2.CLIENT = t.CLIENT                                                        ");
			sb.append(" )                                                                                ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" p1.CLIENT,                                                                       ");
			sb.append(" p1.PRESTO_CODE,                                                                  ");
			sb.append(" max(p1.pay_date) AS last_pay_day                                                 ");
			sb.append(" FROM                                                                             ");
			sb.append(" pay_main p1                                                                      ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND p1.DEL_FLG = '0'                                                             ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" p1.CLIENT,                                                                       ");
			sb.append(" p1.PRESTO_CODE                                                                   ");
			sb.append(" ) TB3 ON (                                                                       ");
			sb.append(" TB3.PRESTO_CODE = t.SALE_CODE                                                    ");
			sb.append(" AND TB3.CLIENT = t.CLIENT                                                        ");
			sb.append(" )                                                                                ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" AND t.DEL_FLG = '0'                                                              ");
			sb.append(" )TBL                                                                             ");
			sb.append(" WHERE 1=1                                                                        ");
			sb.append("and TBL.client = :client 														 ");
			if (map != null && !map.isEmpty()) {
				// 单号
				obj = map.get("saleCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.sale_code like :saleCode	");
					conMap.put("saleCode", "%" + obj + "%");
				}
				// 状态
				obj = map.get("status");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.status like :status	");
					conMap.put("status", "%" + obj + "%");
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
				obj = map.get("deliDateF");
				if (!StringUtils.isEmpty(obj) && obj instanceof Date) {
					sb.append("    and TBL.deli_date>= :deliDateF");
					conMap.put("deliDateF", obj);
				}
				// 发货日期(止)
				obj = map.get("deliDateT");
				if (!StringUtils.isEmpty(obj) && obj instanceof Date) {
					sb.append("    and TBL.deli_date<= :deliDateT");
					conMap.put("deliDateT", obj);
				}

				// 快递公司
				obj = map.get("expressCmpy");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.express_cmpy like :expressCmpy	");
					conMap.put("expressCmpy", "%" + obj + "%");
				}
			}
			sb.append(" order by TBL.sale_code desc 														 ");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public SaleOutMain findBaseByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from SaleOutMain t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.saleCode = :saleCode ";
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                                                           ");
		sb.append(" t.*, COALESCE (TB1.tot_num, 0) AS tot_num,                                       ");
		sb.append(" COALESCE (TB1.tot_sale_amt, 0) - COALESCE(t.dedt_money,0) AS tot_sale_amt,       ");
		sb.append(" COALESCE (TB2.tot_weight, 0) AS tot_weight,                                      ");
		sb.append(" COALESCE (TB3.tot_pay, 0) AS tot_pay,                                            ");
		sb.append(" cb.clt_name as intclient_name		                                             ");
		sb.append(" FROM                                                                             ");
		sb.append(" sale_out_main t                                                                  ");
		sb.append(" LEFT JOIN (                                                                      ");
		sb.append(" SELECT                                                                           ");
		sb.append(" i.SALE_CODE,                                                                     ");
		sb.append(" i.CLIENT,                                                                        ");
		sb.append(" sum(i.NUMS) AS tot_num,                                                          ");
		sb.append(" sum(i.SALE_PRICE) AS tot_sale_amt                                                ");
		sb.append(" FROM                                                                             ");
		sb.append(" sale_out_item i                                                                  ");
		sb.append(" WHERE                                                                            ");
		sb.append(" i.DEL_FLG = '0'                                                                  ");
		sb.append(" GROUP BY                                                                         ");
		sb.append(" i.SALE_CODE,                                                                     ");
		sb.append(" i.CLIENT                                                                         ");
		sb.append(" ) TB1 ON (                                                                       ");
		sb.append(" TB1.SALE_CODE = T.SALE_CODE                                                      ");
		sb.append(" AND TB1.CLIENT = t.CLIENT                                                        ");
		sb.append(" )                                                                                ");
		sb.append(" LEFT JOIN (                                                                      ");
		sb.append(" SELECT                                                                           ");
		sb.append(" i.SALE_CODE,                                                                     ");
		sb.append(" i.CLIENT,                                                                        ");
		sb.append(" sum(p.WEIGHT) AS tot_weight                                                      ");
		sb.append(" FROM                                                                             ");
		sb.append(" prop_uni_info p                                                                  ");
		sb.append(" LEFT JOIN sale_out_item i ON (                                                   ");
		sb.append(" p.BATCH_CODE = i.BATCH_CODE                                                      ");
		sb.append(" AND p.CLIENT = i.CLIENT                                                          ");
		sb.append(" AND i.DEL_FLG = '0'                                                              ");
		sb.append(" )                                                                                ");
		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND p.DEL_FLG = '0'                                                              ");
		sb.append(" GROUP BY                                                                         ");
		sb.append(" i.SALE_CODE,                                                                     ");
		sb.append(" i.CLIENT                                                                         ");
		sb.append(" ) TB2 ON (                                                                       ");
		sb.append(" TB2.SALE_CODE = T.SALE_CODE and TB2.CLIENT = t.CLIENT                            ");
		sb.append(" )                                                                                ");
		sb.append(" LEFT JOIN (                                                                      ");
		sb.append(" SELECT                                                                           ");
		sb.append(" p.PRESTO_CODE,                                                                   ");
		sb.append(" p.CLIENT,                                                                        ");
		sb.append(" sum(p.PAY_AMOUNT) AS tot_pay                                                     ");
		sb.append(" FROM                                                                             ");
		sb.append(" pay_main p                                                                       ");
		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND p.DEL_FLG = '0'                                                              ");
		sb.append(" GROUP BY                                                                         ");
		sb.append(" p.PRESTO_CODE                                                                    ");
		sb.append(" ) TB3 ON (                                                                       ");
		sb.append(" TB3.PRESTO_CODE = T.SALE_CODE                                                    ");
		sb.append(" AND TB3.CLIENT = t.CLIENT                                                        ");
		sb.append(" )                                                                                ");
		sb.append(" left join client_base_info cb on												 ");
		sb.append(" (cb.clt_code = t.intclient_code and cb.client = t.client 						 ");
		sb.append(" 	and cb.del_flg = '0')														 ");

		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND t.DEL_FLG = '0'                                                              ");
		sb.append(" AND t.CLIENT = :client                                                          ");
		sb.append(" AND t.sale_code = :saleCode                                                     ");
		conMap.put("saleCode", saleCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryMapBySql(sb.toString(), conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveMain(SaleOutMain entity) {
		Map<String, Object> result = new HashMap<>(1);
		try {
			// 生成流水码
			String seqCode = sequenceService.generateSequence("SaleCode");
			entity.setSaleCode(seqCode);
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
	public void updateMain(SaleOutMain entity) {
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
	public void saveItem(List<SaleOutItem> list) {
		try {
			for (SaleOutItem entity : list) {
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
	public void updateItem(List<SaleOutItem> list) {
		try {
			for (SaleOutItem entity : list) {
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
	public List<SaleOutItem> findItemByCode(String saleCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from SaleOutItem t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.saleCode = :saleCode ";
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
		sb.append(" concat(round(                                                                    ");
		sb.append(" (t.DISCT / p.RET_PRICE * 100),2),'%') as disct_scale                          	 ");
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
	public ClientBpMoneyRelat findBpRelat() {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from ClientBpMoneyRelat t where 1=1 ";
		hql += "and t.client = :client ";
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

}
