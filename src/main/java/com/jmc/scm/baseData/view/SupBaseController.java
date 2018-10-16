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
import com.jmc.scm.baseData.model.SupplierBankInfo;
import com.jmc.scm.baseData.model.SupplierBaseInfo;
import com.jmc.scm.baseData.service.SupBaseService;
import com.jmc.scm.baseData.service.impl.SupBaseServiceImpl;

/**
 * 供应商主数据控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("supBaseController")
public class SupBaseController {

	@Autowired
	@Qualifier(SupBaseServiceImpl.BEAN_ID)
	private SupBaseService supBaseService;

	/**
	 * 分页查询供应商主数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getSupBaseListPage(Page<SupplierBaseInfo> page,
			Map<String, Object> map) {
		supBaseService.findBaseListPage(page, map);
	}

	/**
	 * 校验供应商编码唯一
	 * 
	 * @param supCode
	 * @return
	 */
	@Expose
	public Boolean checkSupCodeUnique(String supCode) {
		SupplierBaseInfo base = supBaseService.findBaseByCode(supCode);
		if (base != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据供应商号查询数据库的状态
	 * 
	 * @param supCode
	 * @return
	 */
	@Expose
	public String getStatus(String supCode) {
		String result = "";
		SupplierBaseInfo base = supBaseService.findBaseByCode(supCode);
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
	public Map<String, Object> saveOrUpdate(SupplierBaseInfo entity,
			List<SupplierBankInfo> list, String action) {
		return supBaseService.saveOrUpdate(entity, list, action);
	}

	/**
	 * 根据单号查询主数据
	 * 
	 * @param supCode
	 * @return
	 */
	@DataProvider
	public SupplierBaseInfo getBaseByCode(String supCode) {
		return supBaseService.findBaseByCode(supCode);
	}
	
	/**
	 * 根据单号查询银行数据
	 * 
	 * @param supCode
	 * @return
	 */
	@DataProvider
	public List<SupplierBankInfo> getBankByCode(String supCode) {
		return supBaseService.findBankByCode(supCode);
	}
}
