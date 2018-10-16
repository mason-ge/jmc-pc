package com.jmc.scm.stock.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.model.StockInvMain;
import com.jmc.scm.stock.model.StockMvDetail;

public interface MatMainDao {

	void findMainListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	void findDetailListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	List<StockInvMain> findMainListByListCodes(List<String> list);

	void saveMain(List<StockInvMain> list);

	void updateMain(List<StockInvMain> list);
	
	void saveItem(List<StockMvDetail> list);

	void updateItem(List<StockMvDetail> list);

	StockInvMain findMainByCode(String matCode);
	
}
