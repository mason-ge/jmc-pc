package com.jmc.scm.stock.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.dao.StockDao;
import com.jmc.scm.stock.dao.impl.StockDaoImpl;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.service.StockService;

@Service("stockServiceImpl")
public class StockServiceImpl implements StockService {

	@Autowired
	@Qualifier(StockDaoImpl.BEAN_ID)
	private StockDao stockDao;

	public static final String BEAN_ID = "stockServiceImpl";

	@Override
	public PropUniInfo findPropInfoByCode(String batchCode) {
		return stockDao.findPropInfoByCode(batchCode);
	}

	@Override
	public List<PropUniInfo> findPropInfoListByCodes(List<String> batchCodes) {
		return stockDao.findPropInfoListByCodes(batchCodes);
	}

	@Override
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		stockDao.findBaseListPage(page, map);
	}

	@Override
	public void updateProp(PropUniInfo dsMain) {
		List<PropUniInfo> list = new ArrayList<>(1);
		list.add(dsMain);
		stockDao.updatePropMain(list);
	}

}
