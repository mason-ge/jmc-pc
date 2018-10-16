package com.jmc.scm.baseData.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.SupplierBankInfo;
import com.jmc.scm.baseData.model.SupplierBaseInfo;

public interface SupBaseService {

	void findBaseListPage(Page<SupplierBaseInfo> page, Map<String, Object> map);
	
	SupplierBaseInfo findBaseByCode(String supCode);

	Map<String, Object> saveOrUpdate(SupplierBaseInfo entity,
			List<SupplierBankInfo> list, String action);

	List<SupplierBankInfo> findBankByCode(String supCode);
}
