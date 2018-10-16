package com.jmc.scm.sale.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBpMoneyRelat;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.sale.model.SaleOutItem;
import com.jmc.scm.sale.model.SaleOutMain;

public interface SaleOutDao {

	void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	SaleOutMain findBaseByCode(String saleCode);

	Map<String, Object> saveMain(SaleOutMain entity);

	void updateMain(SaleOutMain entity);

	void saveItem(List<SaleOutItem> list);

	void updateItem(List<SaleOutItem> list);

	List<SaleOutItem> findItemByCode(String saleCode);

	List<PayMain> findPayListByCode(String saleCode);

	void savePay(PayMain dsPaySure);

	Map<String, Object> findBaseMapByCode(String saleCode);

	List<Map<String, Object>> findItemMapByCode(String saleCode);

	ClientBpMoneyRelat findBpRelat();

}
