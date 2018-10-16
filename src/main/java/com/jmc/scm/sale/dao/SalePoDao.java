package com.jmc.scm.sale.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.SalePoItem;
import com.jmc.scm.baseData.model.SalePoMain;
import com.jmc.scm.sale.model.PayMain;

public interface SalePoDao {

	void findBaseListPage(Page<Map<String,Object>> page, Map<String, Object> map);

	SalePoMain findBaseByCode(String saleCode);

	Map<String, Object> saveMain(SalePoMain entity);

	void updateMain(SalePoMain entity);

	void saveItem(List<SalePoItem> list);

	void updateItem(List<SalePoItem> list);

	List<SalePoItem> findItemByCode(String saleCode);

	List<PayMain> findPayListByCode(String saleCode);

	void savePay(PayMain dsPaySure);

	Map<String, Object> findBaseMapByCode(String saleCode);

	List<Map<String, Object>> findItemMapByCode(String saleCode);

	List<Map<String, Object>> findPoItemInfoByShop();

	List<Map<String, Object>> findItemListByCode(String saleCode);

}
