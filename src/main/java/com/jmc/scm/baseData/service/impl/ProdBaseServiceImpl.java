package com.jmc.scm.baseData.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.dao.ProdBaseDao;
import com.jmc.scm.baseData.dao.impl.ProdBaseDaoImpl;
import com.jmc.scm.baseData.model.ProdBaseInfo;
import com.jmc.scm.baseData.model.ProdShopping;
import com.jmc.scm.baseData.service.ProdBaseService;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.common.CommonEnum.UserActionType;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.FileService;
import com.jmc.scm.system.service.impl.FileServiceImpl;

@Service("prodBaseServiceImpl")
public class ProdBaseServiceImpl implements ProdBaseService {

	@Autowired
	@Qualifier(ProdBaseDaoImpl.BEAN_ID)
	private ProdBaseDao prodBaseDao;

	@Autowired
	@Qualifier(FileServiceImpl.BEAN_ID)
	private FileService fileService;

	public static final String BEAN_ID = "prodBaseServiceImpl";

	@Override
	public void findBaseListPage(Page<Map<String,Object>> page,
			Map<String, Object> map) {
		prodBaseDao.findBaseListPage(page, map);
	}

	@Override
	public ProdBaseInfo findBaseByCode(String prodCode) {
		return prodBaseDao.findBaseByCode(prodCode);
	}

	@Override
	public Map<String, Object> saveOrUpdate(ProdBaseInfo entity, String action) {
		Map<String, Object> result = new HashMap<>(0);
		String pkId = entity.getPkId();

		if (UserActionType.SAVE.value().equals(action)) {
			if (StringUtils.isEmpty(pkId)) {
				entity.setStatus(CommonStatus.CREATED.value());
				result = prodBaseDao.saveMain(entity);
			} else {
				prodBaseDao.updateMain(entity);
			}
		} else if (UserActionType.DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.DELETED.value());
			entity.setDelFlg(DeletedFlag.YES.value());
			prodBaseDao.updateMain(entity);
		}

		return result;
	}

	@Override
	public BigDecimal getShopNum() {
		return prodBaseDao.getShopNum();
	}

	@Override
	public void saveShop(ProdShopping entity) {
		prodBaseDao.saveShop(entity);
	}

	@Override
	public List<ProdShopping> findShopList() {
		return prodBaseDao.findShopList();
	}

	@Override
	public void delShop() {
		prodBaseDao.delShop();
	}

	@Override
	public List<SysFile> getProdImgList(List<ProdBaseInfo> list) {
		List<String> listProd = new ArrayList<>();
		String attr2 = "";
		for (ProdBaseInfo t : list) {
			attr2 = t.getAttr2();
			if (!"added".equals(attr2)) {
				listProd.add(t.getProdCode());
			}
		}
		List<SysFile> listFile = fileService
				.findListImagesByProdCodeByCodes(listProd);
		return listFile;
	}

	@Override
	public List<ProdBaseInfo> findProdListByCodes(List<String> list) {
		return prodBaseDao.findProdListByCodes(list);

	}
}
