package com.jmc.scm.sale.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.uploader.DownloadFile;
import com.bstek.dorado.uploader.annotation.FileProvider;
import com.jmc.scm.baseData.model.ClientBaseInfo;
import com.jmc.scm.baseData.model.ClientBpMoneyRelat;
import com.jmc.scm.baseData.service.CltBaseService;
import com.jmc.scm.baseData.service.impl.CltBaseServiceImpl;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.sale.model.SaleOutItem;
import com.jmc.scm.sale.model.SaleOutMain;
import com.jmc.scm.sale.service.SaleOutService;
import com.jmc.scm.sale.service.impl.SaleOutServiceImpl;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.stock.service.StockService;
import com.jmc.scm.stock.service.impl.StockServiceImpl;

/**
 * 销售出库控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("saleOutController")
public class SaleOutController {

	@Autowired
	@Qualifier(SaleOutServiceImpl.BEAN_ID)
	private SaleOutService saleOutService;

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
		saleOutService.findBaseListPage(page, map);
	}

	/**
	 * 根据单号查询数据库的状态
	 * 
	 * @param saleCode
	 * @return
	 */
	@Expose
	public String getStatus(String saleCode) {
		String result = "";
		SaleOutMain base = saleOutService.findBaseByCode(saleCode);
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
	public Map<String, Object> saveOrUpdate(SaleOutMain entity,
			List<SaleOutItem> list, String action) {
		return saleOutService.saveOrUpdate(entity, list, action);
	}

	/**
	 * 根据单号查询主数据
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public SaleOutMain getBaseByCode(String saleCode) {
		return saleOutService.findBaseByCode(saleCode);
	}

	/**
	 * 根据单号查询主数据Map
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public Map<String, Object> getBaseMapByCode(String saleCode) {
		return saleOutService.findBaseMapByCode(saleCode);
	}
	
	/**
	 * 付款列表查询
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public List<PayMain> getPayListByCode(String saleCode) {
		return saleOutService.findPayListByCode(saleCode);
	}

	/**
	 * 根据单号查询行数据
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public List<SaleOutItem> getItemByCode(String saleCode) {
		return saleOutService.findItemByCode(saleCode);
	}
	
	/**
	 * 根据单号查询行数据
	 * 
	 * @param saleCode
	 * @return
	 */
	@DataProvider
	public List<Map<String,Object>> getItemMapByCode(String saleCode) {
		return saleOutService.findItemMapByCode(saleCode);
	}

	/**
	 * 查询单品属性
	 * 
	 * @param batchCode
	 * @return
	 */
	@Expose
	public PropUniInfo getPropInfoByCode(String batchCode) {
		BigDecimal qty = BigDecimal.valueOf(0);
		PropUniInfo prop = stockService.findPropInfoByCode(batchCode);
		if (prop != null) {
			if (prop.getQty() != null) {
				qty = prop.getQty();
				if (qty.compareTo(BigDecimal.valueOf(0)) == 1) {
					//这里设置PKID为null，防止塞到前台的行项目上的PKID
					prop.setPkId(null);
					return prop;
				} else {
					return null;
				}
			} else {
				return null;
			}

		} else {
			return null;
		}
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
	 * 保存付款信息
	 * 
	 * @param dsBase
	 * @param dsPaySure
	 * @return
	 */
	@DataResolver
	public Map<String, Object> savePay(SaleOutMain dsBase, PayMain dsPaySure) {
		return saleOutService.savePay(dsBase, dsPaySure);
	}

	/**
	 * 校验货号
	 * 
	 * @param list
	 * @return
	 */
	@Expose
	public Map<String, Object> checkBatch(List<SaleOutItem> list) {
		Map<String, Object> result = new HashMap<>();
		List<String> batchCodes = new ArrayList<>();
		List<String> batchCodesEx = new ArrayList<>();
		String batchCode, erroCodes = "", key1, key2;
		for (SaleOutItem t : list) {
			batchCodes.add(t.getBatchCode());
		}
		List<PropUniInfo> listProp = stockService
				.findPropInfoListByCodes(batchCodes);
		if (listProp != null && !listProp.isEmpty()) {
			for (PropUniInfo t : listProp) {
				batchCodesEx.add(t.getBatchCode());
			}
			// 判断不存在的货号
			for (SaleOutItem t : list) {
				batchCode = t.getBatchCode();
				if (!batchCodesEx.contains(batchCode)) {
					erroCodes += batchCode + ",";
				}
			}
			// 判断存在的货号的数量是否都大于0
			for (SaleOutItem t : list) {
				for (PropUniInfo p : listProp) {
					key1 = t.getBatchCode();
					key2 = t.getBatchCode();
					if (key1.equals(key2)) {
						if (p.getQty().compareTo(BigDecimal.valueOf(0)) != 1) {
							erroCodes += key1 + ",";
						}
					}
				}
			}
		}
		if (erroCodes != "") {
			erroCodes = erroCodes.substring(0, erroCodes.length() - 1);
		}
		result.put("erroCodes", erroCodes);
		return result;
	}
	
	/**
	 * 生成Excel文件
	 * 
	 * @param poCode
	 * @return
	 */
	@FileProvider
	public DownloadFile exportExcelFile(Map<String, Object> map) {
		String checkCode = map.get("checkCode").toString();
		return this.saleOutService.exportExcelFile(checkCode);
	}
	/**
	 * 查询客户端对应的积分金额转换关系表
	 * @return
	 */
	@Expose
	public ClientBpMoneyRelat getBpRelat(){
		return this.saleOutService.findBpRelat();
	};
}
