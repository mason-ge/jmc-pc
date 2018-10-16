package com.jmc.scm.baseData.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ProdBaseInfo;
import com.jmc.scm.baseData.model.ProdShopping;
import com.jmc.scm.system.model.SysFile;

public interface ProdBaseService {

	void findBaseListPage(Page<Map<String,Object>> page, Map<String, Object> map);

	ProdBaseInfo findBaseByCode(String prodCode);

	Map<String, Object> saveOrUpdate(ProdBaseInfo entity, String action);

	BigDecimal getShopNum();

	void saveShop(ProdShopping entity);

	List<ProdShopping> findShopList();

	void delShop();

	List<SysFile> getProdImgList(List<ProdBaseInfo> list);

	List<ProdBaseInfo> findProdListByCodes(List<String> list);
}
