package com.jmc.scm.stock.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.model.StockInvMain;
import com.jmc.scm.stock.model.StockMvDetail;

public interface MatMainService {

	void findMainListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	void findDetailListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	List<StockInvMain> findMainListByListCodes(List<String> list);

	void saveFirstMat(List<StockInvMain> list);

	StockInvMain findMainByCode(String matCode);

	void saveMod(StockMvDetail entity, String inOutFlg);

	void updateMain(List<StockInvMain> list);
}
