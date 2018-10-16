package com.jmc.scm.stock.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.model.PropUniInfo;

public interface StockService {

	PropUniInfo findPropInfoByCode(String batchCode);

	List<PropUniInfo> findPropInfoListByCodes(List<String> batchCodes);

	void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	void updateProp(PropUniInfo dsMain);

}
