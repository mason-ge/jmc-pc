package com.jmc.scm.baseData.view;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBankInfo;
import com.jmc.scm.baseData.model.ClientBaseInfo;
import com.jmc.scm.baseData.service.CltBaseService;
import com.jmc.scm.baseData.service.impl.CltBaseServiceImpl;

/**
 * 客户主数据控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("cltBaseController")
public class CltBaseController {

	@Autowired
	@Qualifier(CltBaseServiceImpl.BEAN_ID)
	private CltBaseService cltBaseService;

	/**
	 * 分页查询客户主数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getCltBaseListPage(Page<ClientBaseInfo> page,
			Map<String, Object> map) {
		cltBaseService.findBaseListPage(page, map);
	}

	/**
	 * 校验客户编码唯一
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	public Boolean checkCltCodeUnique(String cltCode) {
		ClientBaseInfo base = cltBaseService.findBaseByCode(cltCode);
		if (base != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据客户号查询数据库的状态
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	public String getStatus(String cltCode) {
		String result = "";
		ClientBaseInfo base = cltBaseService.findBaseByCode(cltCode);
		if (base != null) {
			result = base.getStatus();
		}
		return result;
	}

	/**
	 * 保存或更新数据
	 * 
	 * @param entity
	 * @param list
	 * @param action
	 * @return
	 */
	@DataResolver
	public Map<String, Object> saveOrUpdate(ClientBaseInfo entity,
			List<ClientBankInfo> list, String action) {
		return cltBaseService.saveOrUpdate(entity, list, action);
	}

	/**
	 * 根据单号查询主数据
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	@DataProvider
	public ClientBaseInfo getBaseByCode(String cltCode) {
		return cltBaseService.findBaseByCode(cltCode);
	}

	/**
	 * 根据单号查询银行数据
	 * 
	 * @param cltCode
	 * @return
	 */
	@DataProvider
	public List<ClientBankInfo> getBankByCode(String cltCode) {
		return cltBaseService.findBankByCode(cltCode);
	}
	
	/**
	 * 根据单号查询主数据
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	@DataProvider
	public Map<String,Object> getBaseMapByCode(String cltCode) {
		return cltBaseService.findBaseMapByCode(cltCode);
	}
}
