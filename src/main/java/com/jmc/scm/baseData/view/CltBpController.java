package com.jmc.scm.baseData.view;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBp;
import com.jmc.scm.baseData.model.ClientBpChangeList;
import com.jmc.scm.baseData.service.CltBpService;
import com.jmc.scm.baseData.service.impl.CltBpServiceImpl;

/**
 * 客户积分控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("cltBpController")
public class CltBpController {

	@Autowired
	@Qualifier(CltBpServiceImpl.BEAN_ID)
	private CltBpService cltBpService;

	/**
	 * 分页查询客户积分List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getCltBpListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		cltBpService.findCltBpListPage(page, map);
	}

	/**
	 * 查询客户的积分历史记录
	 * 
	 * @param cltCode
	 * @return
	 */
	@DataProvider
	public List<ClientBpChangeList> getBpChangeList(String cltCode) {
		return cltBpService.findBpChangeList(cltCode);
	}
	
	@DataProvider
	public ClientBp getCltBpByCode(String cltCode) {
		return cltBpService.findtCltBpByCode(cltCode);
	}

	@DataResolver
	public void saveChangeList(ClientBpChangeList dsNew) {
		cltBpService.saveChangeList(dsNew);
	}
	
	

}
