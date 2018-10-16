package com.jmc.scm.baseData.view;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ProdBaseInfo;
import com.jmc.scm.baseData.model.ProdShopping;
import com.jmc.scm.baseData.service.ProdBaseService;
import com.jmc.scm.baseData.service.impl.ProdBaseServiceImpl;
import com.jmc.scm.system.model.SysFile;

/**
 * 商品主数据控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("prodBaseController")
public class ProdBaseController {

	@Autowired
	@Qualifier(ProdBaseServiceImpl.BEAN_ID)
	private ProdBaseService prodBaseService;

	/**
	 * 分页查询客户主数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getBaseListPage(Page<Map<String,Object>> page, Map<String, Object> map) {
		prodBaseService.findBaseListPage(page, map);
	}

	/**
	 * 根据单号查询主数据
	 * 
	 * @param prodCode
	 * @return
	 */
	@DataProvider
	public ProdBaseInfo getBaseByCode(String prodCode) {
		return prodBaseService.findBaseByCode(prodCode);
	}

	/**
	 * 校验客户编码唯一
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	public Boolean checkCodeUnique(String prodCode) {
		ProdBaseInfo base = prodBaseService.findBaseByCode(prodCode);
		if (base != null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 根据客户号查询数据库的状态
	 * 
	 * @param prodCode
	 * @return
	 */
	@Expose
	public String getStatus(String prodCode) {
		String result = "";
		ProdBaseInfo base = prodBaseService.findBaseByCode(prodCode);
		if (base != null) {
			result = base.getStatus();
		}
		return result;
	}

	/**
	 * 保存或更新数据
	 * 
	 * @param entity
	 * @param action
	 * @return
	 */
	@DataResolver
	public Map<String, Object> saveOrUpdate(ProdBaseInfo entity, String action) {
		return prodBaseService.saveOrUpdate(entity, action);
	}

	/**
	 * 获取购物车已购物的数量
	 * 
	 * @return
	 */
	@Expose
	public BigDecimal getShopNum() {
		return prodBaseService.getShopNum();
	}

	/**
	 * 保存商品到购物车
	 * 
	 * @param entity
	 */
	@DataResolver
	public void saveShop(ProdShopping entity) {
		prodBaseService.saveShop(entity);
	}

	/**
	 * 获取主数据对应所有的图片信息
	 * 
	 * @param list
	 * @return
	 */
	@Expose
	public List<SysFile> getProdImgList(List<ProdBaseInfo> list) {
		return prodBaseService.getProdImgList(list);
	}
}
