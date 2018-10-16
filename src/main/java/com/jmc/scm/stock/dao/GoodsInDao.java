package com.jmc.scm.stock.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.model.StockMoveItem;
import com.jmc.scm.stock.model.StockMoveMain;

public interface GoodsInDao {

	void findBaseListPage(Page<Map<String,Object>> page, Map<String, Object> map);

	StockMoveMain findBaseByCode(String prestoCode);

	Map<String, Object> saveMain(StockMoveMain entity);

	void updateMain(StockMoveMain entity);

	void saveItem(List<StockMoveItem> list);

	void updateItem(List<StockMoveItem> list);

	List<StockMoveItem> findItemByCode(String prestoCode);

	void savePay(PayMain dsPaySure);

	Map<String, Object> findBaseMapByCode(String prestoCode);

	List<Map<String, Object>> findItemMapByCode(String prestoCode);

	List<PropUniInfo> findPropByCode(String prestoCode);

}
