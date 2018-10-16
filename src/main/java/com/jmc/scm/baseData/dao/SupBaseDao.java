package com.jmc.scm.baseData.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.SupplierBankInfo;
import com.jmc.scm.baseData.model.SupplierBaseInfo;

public interface SupBaseDao {

	void findBaseListPage(Page<SupplierBaseInfo> page, Map<String, Object> map);

	SupplierBaseInfo findBaseByCode(String supCode);

	Map<String, Object> saveMain(SupplierBaseInfo entity);

	void updateMain(SupplierBaseInfo entity);

	void saveBank(List<SupplierBankInfo> list);

	void updateBank(List<SupplierBankInfo> list);

	List<SupplierBankInfo> findBankByCode(String supCode);

}
