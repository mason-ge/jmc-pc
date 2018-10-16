package com.jmc.scm.baseData.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.model.ProdBaseInfo;
import com.jmc.scm.baseData.model.ProdShopping;

public interface ProdBaseDao {

	void findBaseListPage(Page<Map<String,Object>> page, Map<String, Object> map);

	ProdBaseInfo findBaseByCode(String prodCode);

	Map<String, Object> saveMain(ProdBaseInfo entity);

	void updateMain(ProdBaseInfo entity);

	BigDecimal getShopNum();

	void saveShop(ProdShopping entity);

	List<ProdShopping> findShopList();

	void delShop();

	List<ProdBaseInfo> findProdListByCodes(List<String> list);

}
