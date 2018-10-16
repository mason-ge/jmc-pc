package com.jmc.scm.baseData.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBankInfo;
import com.jmc.scm.baseData.model.ClientBaseInfo;

public interface CltBaseDao {

	void findBaseListPage(Page<ClientBaseInfo> page, Map<String, Object> map);

	ClientBaseInfo findBaseByCode(String cltCode);

	Map<String, Object> saveMain(ClientBaseInfo entity);

	void updateMain(ClientBaseInfo entity);

	void saveBank(List<ClientBankInfo> list);

	void updateBank(List<ClientBankInfo> list);

	List<ClientBankInfo> findBankByCode(String cltCode);

	Map<String, Object> findBaseMapByCode(String cltCode);

}
