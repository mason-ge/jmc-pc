package com.jmc.scm.stock.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.model.PropUniInfo;


public interface StockDao {

	PropUniInfo findPropInfoByCode(String batchCode);

	List<PropUniInfo> findPropInfoListByCodes(List<String> batchCodes);

	void savePropMain(List<PropUniInfo> list);
	
	void updatePropMain(List<PropUniInfo> list);

	void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

}
