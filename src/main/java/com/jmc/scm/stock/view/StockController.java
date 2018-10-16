package com.jmc.scm.stock.view;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.service.StockService;
import com.jmc.scm.stock.service.impl.StockServiceImpl;

/**
 * 库存控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("stockController")
public class StockController {

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
		stockService.findBaseListPage(page, map);
	}

	/**
	 * 更新单品数据
	 * 
	 * @param dsMain
	 */
	@DataResolver
	public void updateProp(PropUniInfo dsMain) {
		stockService.updateProp(dsMain);
	}
}
