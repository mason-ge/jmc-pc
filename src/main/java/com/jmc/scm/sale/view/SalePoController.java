package com.jmc.scm.sale.view;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ClientBaseInfo;
import com.jmc.scm.baseData.model.SalePoItem;
import com.jmc.scm.baseData.model.SalePoMain;
import com.jmc.scm.baseData.service.CltBaseService;
import com.jmc.scm.baseData.service.impl.CltBaseServiceImpl;
import com.jmc.scm.sale.service.SalePoService;
import com.jmc.scm.sale.service.impl.SalePoServiceImpl;
import com.jmc.scm.stock.service.StockService;
import com.jmc.scm.stock.service.impl.StockServiceImpl;
import com.jmc.scm.system.model.SysFile;

/**
 * 销售单控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("salePoController")
public class SalePoController {

	@Autowired
	@Qualifier(SalePoServiceImpl.BEAN_ID)
	private SalePoService salePoService;

	@Autowired
	@Qualifier(StockServiceImpl.BEAN_ID)
	private StockService stockService;

	@Autowired
	@Qualifier(CltBaseServiceImpl.BEAN_ID)
	private CltBaseService cltBaseService;

	/**
	 * 分页查询主页面数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		salePoService.findBaseListPage(page, map);
	}

	/**
	 * 根据单号查询数据库的状态
	 * 
	 * @param saleCode
	 * @return
	 */
	@Expose
	public String getStatus(String salePoCode) {
		String result = "";
		SalePoMain base = salePoService.findBaseByCode(salePoCode);
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
	public Map<String, Object> saveOrUpdate(SalePoMain entity,
			List<SalePoItem> list, String action) {
		return salePoService.saveOrUpdate(entity, list, action);
	}

	/**
	 * 根据单号查询主数据
	 * 
	 * @param saleCode
	 * @return
	 */
	@Expose
	@DataProvider
	public SalePoMain getBaseByCode(String saleCode) {
		return salePoService.findBaseByCode(saleCode);
	}

	/**
	 * 根据单号查询主数据Map
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public Map<String, Object> getBaseMapByCode(String saleCode) {
		return salePoService.findBaseMapByCode(saleCode);
	}

	/**
	 * 根据单号查询行数据
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public List<SalePoItem> getItemByCode(String saleCode) {
		return salePoService.findItemByCode(saleCode);
	}
	/**
	 * 根据单号查询行数据和相关的数据
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public List<Map<String,Object>> getItemListByCode(String saleCode){
		return salePoService.findItemListByCode(saleCode);
	}

	/**
	 * 根据客户编码带信息
	 * 
	 * @param cltCode
	 * @return
	 */
	@Expose
	public ClientBaseInfo getCltInfo(String cltCode) {
		return cltBaseService.findBaseByCode(cltCode);
	}

	/**
	 * 获取购物车信息
	 * 
	 * @return
	 */
	@Expose
	public List<Map<String, Object>> getPoItemInfoByShop() {
		return salePoService.findPoItemInfoByShop();
	}

	/**
	 * 获取行项目所有的图片信息
	 * 
	 * @param list
	 * @return
	 */
	@Expose
	public List<SysFile> getProdImgList(List<SalePoItem> list) {
		return salePoService.getProdImgList(list);
	}
}
