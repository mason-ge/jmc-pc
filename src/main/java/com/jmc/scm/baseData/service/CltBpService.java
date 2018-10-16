package com.jmc.scm.baseData.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBp;
import com.jmc.scm.baseData.model.ClientBpChangeList;

public interface CltBpService {

	void findCltBpListPage(Page<Map<String, Object>> page,
			Map<String, Object> map);

	List<ClientBpChangeList> findBpChangeList(String cltCode);

	void saveChangeList(ClientBpChangeList dsNew);

	ClientBp findtCltBpByCode(String cltCode);

}
