package com.jmc.scm.stock.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.stock.model.StockMoveItem;
import com.jmc.scm.stock.model.StockMoveMain;

public interface GoodsInService {

	void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	StockMoveMain findBaseByCode(String prestoCode);

	List<StockMoveItem> findItemByCode(String prestoCode);

	Map<String, Object> saveOrUpdate(StockMoveMain entity,
			List<StockMoveItem> list, String action);

	Map<String, Object> savePay(StockMoveMain dsBase, PayMain dsPaySure);

	Map<String, Object> findBaseMapByCode(String prestoCode);

	List<Map<String, Object>> findItemMapByCode(String prestoCode);

}
