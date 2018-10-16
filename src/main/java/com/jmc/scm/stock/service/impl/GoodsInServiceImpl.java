package com.jmc.scm.stock.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.common.CommonEnum.UserActionType;
import com.jmc.scm.framework.service.BarcodeService;
import com.jmc.scm.framework.service.impl.BarcodeServiceImpl;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.stock.dao.GoodsInDao;
import com.jmc.scm.stock.dao.StockDao;
import com.jmc.scm.stock.dao.impl.GoodsInDaoImpl;
import com.jmc.scm.stock.dao.impl.StockDaoImpl;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.model.StockMoveItem;
import com.jmc.scm.stock.model.StockMoveMain;
import com.jmc.scm.stock.service.GoodsInService;
import com.jmc.scm.system.service.FileService;
import com.jmc.scm.system.service.impl.FileServiceImpl;

@Service("goodsInServiceImpl")
public class GoodsInServiceImpl implements GoodsInService {

	@Autowired
	@Qualifier(GoodsInDaoImpl.BEAN_ID)
	private GoodsInDao goodsInDao;

	@Autowired
	@Qualifier(StockDaoImpl.BEAN_ID)
	private StockDao stockDao;

	@Autowired
	@Qualifier(BarcodeServiceImpl.BEAN_ID)
	private BarcodeService barcodeService;

	@Autowired
	@Qualifier(FileServiceImpl.BEAN_ID)
	private FileService fileService;

	public static final String BEAN_ID = "goodsInServiceImpl";

	@Override
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		goodsInDao.findBaseListPage(page, map);
	}

	@Override
	public StockMoveMain findBaseByCode(String prestoCode) {
		return goodsInDao.findBaseByCode(prestoCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveOrUpdate(StockMoveMain entity,
			List<StockMoveItem> list, String action) {
		Map<String, Object> result = new HashMap<>(0);
		List<StockMoveItem> list2Save = new ArrayList<>();
		List<StockMoveItem> list2Update = new ArrayList<>();
		List<String> batchCodes = new ArrayList<>();
		List<PropUniInfo> listPropUpdate = new ArrayList<>();

		String pkId = entity.getPkId();
		String prestoCode = "";
		String itemId;

		if (UserActionType.SAVE.value().equals(action)) {
			if (StringUtils.isEmpty(pkId)) {
				entity.setStatus(CommonStatus.CREATED.value());
				result = goodsInDao.saveMain(entity);
				prestoCode = result.get("seqCode").toString();

			} else {
				prestoCode = entity.getPrestoCode();
				goodsInDao.updateMain(entity);
			}
		} else if (UserActionType.SUBMIT.value().equals(action)) {
			entity.setStatus(CommonStatus.SUBMITED.value());
			entity.setSubmitBy(ContextHolder.getLoginUserName());
			entity.setSubmitD(new Date());
			goodsInDao.updateMain(entity);
			prestoCode = entity.getPrestoCode();
			// 写入库存属性表
			this.generateProp(prestoCode);
			// 单品属性表重已存在的货品，需要加1
			// 更新单品属性的数量为+1
			for (StockMoveItem t : list) {
				// 判断是否需要删除
				EntityState status = EntityUtils.getState(t);
				if (!(EntityState.DELETED.equals(status))) {
					batchCodes.add(t.getBatchCode());
				}
			}
			List<PropUniInfo> listProp = stockDao
					.findPropInfoListByCodes(batchCodes);
			if (listProp != null && !listProp.isEmpty()) {
				for (PropUniInfo t : listProp) {
					t.setQty(t.getQty().add(BigDecimal.valueOf(1)));
					listPropUpdate.add(t);
				}
				stockDao.updatePropMain(listPropUpdate);
			}

		} else if (UserActionType.DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.DELETED.value());
			entity.setDelFlg(DeletedFlag.YES.value());
			for (StockMoveItem t : list) {
				t.setDelFlg(DeletedFlag.YES.value());
			}
			goodsInDao.updateMain(entity);
			goodsInDao.updateItem(list2Update);
			prestoCode = entity.getPrestoCode();
		} else if (UserActionType.DIS_SUBMIT.value().equals(action)) {
			entity.setStatus(CommonStatus.CREATED.value());
			entity.setSubmitBy(null);
			entity.setSubmitD(null);
			goodsInDao.updateMain(entity);
			// 更新单品属性的数量为0
			for (StockMoveItem t : list) {
				// 判断是否需要删除
				EntityState status = EntityUtils.getState(t);
				if (!(EntityState.DELETED.equals(status))) {
					batchCodes.add(t.getBatchCode());
				}
			}
			List<PropUniInfo> listProp = stockDao
					.findPropInfoListByCodes(batchCodes);
			if (listProp != null && !listProp.isEmpty()) {
				BigDecimal re = BigDecimal.valueOf(0);
				for (PropUniInfo t : listProp) {
					re = t.getQty().subtract(BigDecimal.valueOf(1));
					if (re.compareTo(BigDecimal.valueOf(0)) >= 0) {
						t.setQty(re);
						listPropUpdate.add(t);
					} else {
						throw new RuntimeException("该操作会造成库存为负！请检查货号："
								+ t.getBatchCode() + "的数据！");
					}
				}
				stockDao.updatePropMain(listPropUpdate);
			}
		}
		if (UserActionType.SAVE.value().equals(action)
				|| UserActionType.SUBMIT.value().equals(action)) {
			for (StockMoveItem t : list) {
				itemId = t.getPkId();
				if (StringUtils.isEmpty(itemId)) {
					t.setPrestoCode(prestoCode);
					list2Save.add(t);

					// 新建时生成条码

					String bizCode = t.getBatchCode();
					String filePath = barcodeService.generateBarcode(5d, 17d,
							5, 400, bizCode);
					String fileName = filePath.split("/")[2];
					Map<String, Object> params = new HashMap<>();
					params.put("abPath", filePath);
					params.put("sName", fileName);
					params.put("bizCode", bizCode);
					fileService.saveAtt(params);

				} else {
					// 判断是否需要删除
					EntityState status = EntityUtils.getState(t);
					if ((EntityState.DELETED.equals(status))) {
						t.setDelFlg(DeletedFlag.YES.value());
					}
					list2Update.add(t);
				}
			}
			goodsInDao.updateItem(list2Update);
			goodsInDao.saveItem(list2Save);
		}
		return result;

	}

	@Override
	public List<StockMoveItem> findItemByCode(String prestoCode) {
		return goodsInDao.findItemByCode(prestoCode);
	}

	@Override
	public Map<String, Object> savePay(StockMoveMain dsBase, PayMain dsPaySure) {
		dsPaySure.setPrestoCode(dsBase.getPrestoCode());
		dsPaySure.setPayFlg("A");
		goodsInDao.savePay(dsPaySure);
		return null;
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String prestoCode) {
		return goodsInDao.findBaseMapByCode(prestoCode);
	}

	@Override
	public List<Map<String, Object>> findItemMapByCode(String prestoCode) {
		return goodsInDao.findItemMapByCode(prestoCode);
	}

	/**
	 * 根据单号生成单品属性
	 * 
	 * @param prestoCode
	 */
	private void generateProp(String prestoCode) {
		// 单品属性在入库表中没有的货号
		List<PropUniInfo> listProp = goodsInDao.findPropByCode(prestoCode);
		if (listProp != null && !listProp.isEmpty()) {
			stockDao.savePropMain(listProp);
		}
	}

}
