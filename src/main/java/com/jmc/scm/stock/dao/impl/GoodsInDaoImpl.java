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
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.stock.dao.GoodsInDao;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.model.StockMoveItem;
import com.jmc.scm.stock.model.StockMoveMain;
import com.jmc.scm.system.service.SequenceService;
import com.jmc.scm.system.service.impl.SequenceServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Repository("goodsInDaoImpl")
public class GoodsInDaoImpl extends BaseDao implements GoodsInDao {

	public static final String BEAN_ID = "goodsInDaoImpl";

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
			sb.append(" t.*,                                                                 			 ");
			sb.append("                                                                					 ");
			sb.append(" TB2.tot_pay,                                                                     ");
			sb.append(" t.TOT_PRICE - TB2.tot_pay AS tot_left_pay,                                  	 ");
			sb.append(" TB3.last_pay_day                                                                 ");
			sb.append(" FROM                                                                             ");
			sb.append(" stock_move_main t                                                                ");
			// sb.append(" LEFT JOIN (                                                                      ");
			// sb.append(" SELECT                                                                           ");
			// sb.append(" i.CLIENT,                                                                        ");
			// sb.append(" i.PRESTO_CODE,                                                                     ");
			// sb.append(" sum(i.NUMS) AS tot_num,                                                          ");
			// sb.append(" sum(i.RET_PRICE) AS tot_sale_amt                                                ");
			// sb.append(" FROM                                                                             ");
			// sb.append(" stock_move_item i                                                                  ");
			// sb.append(" WHERE                                                                            ");
			// sb.append(" 1 = 1                                                                            ");
			// sb.append(" AND i.DEL_FLG = '0'                                                              ");
			// sb.append(" GROUP BY                                                                         ");
			// sb.append(" i.CLIENT,                                                                        ");
			// sb.append(" i.PRESTO_CODE                                                                      ");
			// sb.append(" ) TB1 ON (                                                                       ");
			// sb.append(" TB1.PRESTO_CODE = t.PRESTO_CODE                                                      ");
			// sb.append(" AND t.CLIENT = TB1.CLIENT                                                        ");
			// sb.append(" )                                                                                ");
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
			sb.append(" TB2.PRESTO_CODE = t.PRESTO_CODE                                                    ");
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
			sb.append(" TB3.PRESTO_CODE = t.PRESTO_CODE                                                    ");
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
				obj = map.get("prestoCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.presto_code like :prestoCode	");
					conMap.put("prestoCode", "%" + obj + "%");
				}
				// 状态
				obj = map.get("status");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.status like :status	");
					conMap.put("status", "%" + obj + "%");
				}
				// 供应商编码
				obj = map.get("supCode");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.sup_code like :supCode	");
					conMap.put("supCode", "%" + obj + "%");
				}
				// 供应商名称
				obj = map.get("supName");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.sup_name like :supName	");
					conMap.put("supName", "%" + obj + "%");
				}
				// 入库日期(起)
				obj = map.get("recDateF");
				if (!StringUtils.isEmpty(obj) && obj instanceof Date) {
					sb.append("    and TBL.rec_date>= :recDateF");
					conMap.put("recDateF", obj);
				}
				// 入库日期(止)
				obj = map.get("recDateT");
				if (!StringUtils.isEmpty(obj) && obj instanceof Date) {
					sb.append("    and TBL.rec_date<= :recDateT");
					conMap.put("recDateT", obj);
				}
				// 参考单号
				obj = map.get("refOrderNum");
				if (!StringUtils.isEmpty(obj)) {
					sb.append("and TBL.ref_order_num like :refOrderNum	");
					conMap.put("refOrderNum", "%" + obj + "%");
				}
			}
			sb.append("order by TBL.presto_code desc	");
			conMap.put("client", ScmUtil.getClient());
			this.queryPageBySql(page, sb.toString(), conMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public StockMoveMain findBaseByCode(String prestoCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from StockMoveMain t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.prestoCode = :prestoCode ";
		conMap.put("prestoCode", prestoCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryEntity(hql, conMap);
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String prestoCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                                                           ");
		sb.append(" t.*,                                     										 ");
		sb.append(" COALESCE (TB3.tot_pay, 0) AS tot_pay                                             ");
		sb.append(" FROM                                                                             ");
		sb.append(" STOCK_MOVE_MAIN t                                                                 ");
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
		sb.append(" TB3.PRESTO_CODE = T.PRESTO_CODE                                                    ");
		sb.append(" AND TB3.CLIENT = t.CLIENT                                                        ");
		sb.append(" )                                                                                ");
		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND t.DEL_FLG = '0'                                                              ");
		sb.append(" AND t.CLIENT = :client                                                          ");
		sb.append(" AND t.PRESTO_CODE = :prestoCode                                                     ");
		conMap.put("prestoCode", prestoCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryMapBySql(sb.toString(), conMap);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveMain(StockMoveMain entity) {
		Map<String, Object> result = new HashMap<>(1);
		try {
			// 生成流水码
			String seqCode = sequenceService.generateSequence("PrestoCode");
			entity.setPrestoCode(seqCode);
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
	public void updateMain(StockMoveMain entity) {
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
	public void saveItem(List<StockMoveItem> list) {
		try {
			for (StockMoveItem entity : list) {
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
	public void updateItem(List<StockMoveItem> list) {
		try {
			for (StockMoveItem entity : list) {
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
	public List<StockMoveItem> findItemByCode(String prestoCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		String hql = "from StockMoveItem t where 1=1 ";
		hql += "and t.delFlg = '0' ";
		hql += "and t.client = :client ";
		hql += "and t.prestoCode = :prestoCode ";
		conMap.put("prestoCode", prestoCode);
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
	public List<Map<String, Object>> findItemMapByCode(String prestoCode) {
		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT                                                                           ");
		sb.append(" t.*                                                                				 ");
		sb.append(" FROM                                                                             ");
		sb.append(" STOCK_MOVE_ITEM t                                                                ");
		sb.append(" WHERE                                                                            ");
		sb.append(" 1 = 1                                                                            ");
		sb.append(" AND t.DEL_FLG = '0'                                                              ");
		sb.append(" AND t.CLIENT = :client                                                           ");
		sb.append(" AND t.presto_code = :prestoCode                                                    ");
		conMap.put("prestoCode", prestoCode);
		conMap.put("client", ScmUtil.getClient());
		return this.queryListBySql(sb.toString(), conMap);
	}

	@Override
	public List<PropUniInfo> findPropByCode(String prestoCode) {
		List<PropUniInfo> list = null;
		Map<String, Object> conMap = new HashMap<>(2);
		StringBuffer sb = new StringBuffer();
		sb.append(" select t.BATCH_CODE,                                                             ");
		sb.append(" T.SP_NAME,                                                                       ");
		sb.append(" T.PROD_CODE,                                                                     ");
		sb.append(" T.IS_EMPTY_BRACKET,                                                              ");
		sb.append(" T.MOS_TYPE,                                                                      ");
		sb.append(" T.FIRST_CATG,                                                                    ");
		sb.append(" T.SEC_CATG,                                                                      ");
		sb.append(" T.FAC_PROD_CODE,                                                                 ");
		sb.append(" T.RET_PRICE,                                                                     ");
		sb.append(" T.GOLD_TYPE,                                                                     ");
		sb.append(" T.GOLD_COLOR,                                                                    ");
		sb.append(" T.MATERIAL,                                                                      ");
		sb.append(" T.CLARITY_G_WT,                                                                  ");
		sb.append(" T.WEIGHT,                                                                        ");
		sb.append(" T.SIZE,                                                                          ");
		sb.append(" T.GOLD_WASTAGE,                                                                  ");
		sb.append(" T.INCL_WASTAGE_G_WT,                                                             ");
		sb.append(" T.GOLD_PX,                                                                       ");
		sb.append(" T.FAC_G_PX,                                                                      ");
		sb.append(" T.CMPY_G_PX,                                                                     ");
		sb.append(" T.FAC_ACCY_PX,                                                                   ");
		sb.append(" T.COM_ACCY_PX,                                                                   ");
		sb.append(" T.FAC_ST_PX,                                                                     ");
		sb.append(" T.COM_ST_PX,                                                                     ");
		sb.append(" T.INIT_BAND_COST,                                                                ");
		sb.append(" T.BASE_COST,                                                                     ");
		sb.append(" T.OTHER_COST,                                                                    ");
		sb.append(" T.FAC_SETTLE_PX,                                                                 ");
		sb.append(" T.CER_TYPE,                                                                      ");
		sb.append(" T.CER_NUM,                                                                       ");
		sb.append(" T.COLOR,                                                                         ");
		sb.append(" T.NEAT,                                                                          ");
		sb.append(" T.NGTC_GOODS,                                                                    ");
		sb.append(" T.NGTC_TOT_QUALITY,                                                              ");
		sb.append(" T.NGTC_G_FINENESS,                                                               ");
		sb.append(" T.INLAIED_SCOOP,                                                                 ");
		sb.append(" T.MAN_ST_WT,                                                                     ");
		sb.append(" T.MAN_ST_INFO,                                                                   ");
		sb.append(" T.ASST_ST_WT,                                                                    ");
		sb.append(" T.ASST_ST_INFO,                                                                  ");
		sb.append(" T.ASST_ST_AMT,                                                                   ");
		sb.append(" T.LAU_DATE,                                                                      ");
		sb.append(" T.PRESTO_CODE AS REF_ORDER_NUM,                                                  ");
		sb.append(" T.PRESTO_ITEM_CODE AS REF_LINE_NUM,                                              ");
		sb.append(" m.SUP_CODE,                                                                      ");
		sb.append(" m.SUP_NAME,                                                                      ");
		sb.append(" m.STOCK_LOCALE,                                                                  ");
		sb.append(" '0' AS STATUS,                                                                   ");
		sb.append(" T.REMARK,                                                                        ");
		sb.append(" T.NUMS AS QTY,                                                                   ");
		sb.append(" T.MAIN_ST_CODE,                                                                  ");
		sb.append(" T.MAIN_CER_CODE,                                                                 ");
		sb.append(" m.LOAN,			                                                                 ");
		sb.append(" T.MAIN_CER_TYPE,			                                                     ");
		sb.append(" T.OTHER_CER_TYPE,			                                                     ");
		sb.append(" T.OTHER_CER_CODE			                                                     ");
		sb.append(" from                                                                             ");
		sb.append(" stock_move_item t                                                                ");
		sb.append(" left join stock_move_main m                                                      ");
		sb.append(" on (t.PRESTO_CODE = m.PRESTO_CODE and m.DEL_FLG = '0' and m.CLIENT = t.CLIENT)   ");
		
		sb.append(" left join prop_uni_info u                                                      	 ");
		sb.append(" on (t.BATCH_CODE = u.BATCH_CODE and u.DEL_FLG = '0' and u.CLIENT = t.CLIENT)     ");
		
		sb.append(" where 1=1                                                                        ");
		sb.append(" and t.DEL_FLG = '0'                                                              ");
		sb.append(" and u.BATCH_CODE is null                                                         ");
		sb.append(" and t.PRESTO_CODE = :prestoCode                                                  ");
		sb.append(" and t.CLIENT = :client                                                           ");
		conMap.put("prestoCode", prestoCode);
		conMap.put("client", ScmUtil.getClient());
		list = this.queryListBySql(sb.toString(), conMap, PropUniInfo.class);
		return list;
	}

}
