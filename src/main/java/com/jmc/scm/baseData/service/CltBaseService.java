package com.jmc.scm.baseData.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBankInfo;
import com.jmc.scm.baseData.model.ClientBaseInfo;

public interface CltBaseService {

	void findBaseListPage(Page<ClientBaseInfo> page, Map<String, Object> map);
	
	ClientBaseInfo findBaseByCode(String cltCode);

	Map<String, Object> saveOrUpdate(ClientBaseInfo entity,
			List<ClientBankInfo> list, String action);

	List<ClientBankInfo> findBankByCode(String cltCode);

	Map<String, Object> findBaseMapByCode(String cltCode);
}
