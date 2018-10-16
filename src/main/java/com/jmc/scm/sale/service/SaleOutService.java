package com.jmc.scm.sale.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.uploader.DownloadFile;
import com.jmc.scm.baseData.model.ClientBpMoneyRelat;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.sale.model.SaleOutItem;
import com.jmc.scm.sale.model.SaleOutMain;

public interface SaleOutService {

	void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	SaleOutMain findBaseByCode(String saleCode);

	List<SaleOutItem> findItemByCode(String saleCode);

	Map<String, Object> saveOrUpdate(SaleOutMain entity,
			List<SaleOutItem> list, String action);

	List<PayMain> findPayListByCode(String saleCode);

	Map<String, Object> savePay(SaleOutMain dsBase, PayMain dsPaySure);

	Map<String, Object> findBaseMapByCode(String saleCode);

	List<Map<String, Object>> findItemMapByCode(String saleCode);

	DownloadFile exportExcelFile(String checkCode);

	ClientBpMoneyRelat findBpRelat();

}
