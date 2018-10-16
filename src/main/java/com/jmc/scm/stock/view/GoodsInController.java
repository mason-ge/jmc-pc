package com.jmc.scm.stock.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.SupplierBaseInfo;
import com.jmc.scm.baseData.service.SupBaseService;
import com.jmc.scm.baseData.service.impl.SupBaseServiceImpl;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.model.StockMoveItem;
import com.jmc.scm.stock.model.StockMoveMain;
import com.jmc.scm.stock.service.GoodsInService;
import com.jmc.scm.stock.service.StockService;
import com.jmc.scm.stock.service.impl.GoodsInServiceImpl;
import com.jmc.scm.stock.service.impl.StockServiceImpl;

/**
 * 成品入库控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("goodsInController")
public class GoodsInController {

	@Autowired
	@Qualifier(GoodsInServiceImpl.BEAN_ID)
	private GoodsInService goodsInService;

	@Autowired
	@Qualifier(SupBaseServiceImpl.BEAN_ID)
	private SupBaseService supBaseService;

	@Autowired
	@Qualifier(StockServiceImpl.BEAN_ID)
	private StockService stockService;

	/**
	 * 分页查询主页面数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		goodsInService.findBaseListPage(page, map);
	}

	/**
	 * 根据单号查询数据库的状态
	 * 
	 * @param prestoCode
	 * @return
	 */
	@Expose
	public String getStatus(String prestoCode) {
		String result = "";
		StockMoveMain base = goodsInService.findBaseByCode(prestoCode);
		if (base != null) {
			result = base.getStatus();
		}
		return result;
	}

	/**
	 * 保存或更新数据
	 * 
	 * @param entity
	 * @param list
	 * @param action
	 * @return
	 */
	@DataResolver
	public Map<String, Object> saveOrUpdate(StockMoveMain entity,
			List<StockMoveItem> list, String action) {
		return goodsInService.saveOrUpdate(entity, list, action);
	}

	/**
	 * 根据单号查询主数据
	 * 
	 * @param prestoCode
	 * @return
	 */
	@DataProvider
	public StockMoveMain getBaseByCode(String prestoCode) {
		return goodsInService.findBaseByCode(prestoCode);
	}

	/**
	 * 根据单号查询主数据Map
	 * 
	 * @param prestoCode
	 * @return
	 */
	@DataProvider
	public Map<String, Object> getBaseMapByCode(String prestoCode) {
		return goodsInService.findBaseMapByCode(prestoCode);
	}

	/**
	 * 根据单号查询行数据
	 * 
	 * @param prestoCode
	 * @return
	 */
	@DataProvider
	public List<StockMoveItem> getItemByCode(String prestoCode) {
		return goodsInService.findItemByCode(prestoCode);
	}

	/**
	 * 根据单号查询行数据
	 * 
	 * @param prestoCode
	 * @return
	 */
	@DataProvider
	public List<Map<String, Object>> getItemMapByCode(String prestoCode) {
		return goodsInService.findItemMapByCode(prestoCode);
	}

	/**
	 * 保存付款信息
	 * 
	 * @param dsBase
	 * @param dsPaySure
	 * @return
	 */
	@DataResolver
	public Map<String, Object> savePay(StockMoveMain dsBase, PayMain dsPaySure) {
		return goodsInService.savePay(dsBase, dsPaySure);
	}

	/**
	 * 根据供应商编码带信息
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	public SupplierBaseInfo getSupInfo(String supCode) {
		return supBaseService.findBaseByCode(supCode);
	}

	/**
	 * 检查单品是否已经存在货号
	 * 
	 * @param list
	 * @return
	 */
	@Expose
	public String checkSt(List<StockMoveItem> list) {
		String result = "", key1, key2;
		List<String> listCodes = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (StockMoveItem t : list) {
				listCodes.add(t.getBatchCode());
			}
			List<PropUniInfo> listProp = stockService
					.findPropInfoListByCodes(listCodes);
			if (listProp != null && !listProp.isEmpty()) {
				for (PropUniInfo p : listProp) {
					for (StockMoveItem t : list) {
						key1 = p.getBatchCode();
						key2 = t.getBatchCode();
						if (key1.equals(key2)) {
							result += key1 + ",";
							break;
						}
					}
				}
			}
		}
		if (!"".equals(result)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
	
}
