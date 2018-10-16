package com.jmc.scm.sale.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.SalePoItem;
import com.jmc.scm.baseData.model.SalePoMain;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.system.model.SysFile;

public interface SalePoService {

	void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	SalePoMain findBaseByCode(String saleCode);

	List<SalePoItem> findItemByCode(String saleCode);

	Map<String, Object> saveOrUpdate(SalePoMain entity, List<SalePoItem> list,
			String action);

	List<PayMain> findPayListByCode(String saleCode);

	Map<String, Object> findBaseMapByCode(String saleCode);

	List<Map<String, Object>> findItemMapByCode(String saleCode);

	List<Map<String, Object>> findPoItemInfoByShop();

	List<SysFile> getProdImgList(List<SalePoItem> list);

	List<Map<String, Object>> findItemListByCode(String saleCode);

}
