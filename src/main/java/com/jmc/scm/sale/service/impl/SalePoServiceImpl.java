package com.jmc.scm.sale.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.baseData.dao.ProdBaseDao;
import com.jmc.scm.baseData.dao.impl.ProdBaseDaoImpl;
import com.jmc.scm.baseData.model.SalePoItem;
import com.jmc.scm.baseData.model.SalePoMain;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.common.CommonEnum.UserActionType;
import com.jmc.scm.sale.dao.SalePoDao;
import com.jmc.scm.sale.dao.impl.SalePoDaoImpl;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.sale.service.SalePoService;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.FileService;
import com.jmc.scm.system.service.impl.FileServiceImpl;

@Service("salePoServiceImpl")
public class SalePoServiceImpl implements SalePoService {

	@Autowired
	@Qualifier(SalePoDaoImpl.BEAN_ID)
	private SalePoDao salePoDao;

	@Autowired
	@Qualifier(ProdBaseDaoImpl.BEAN_ID)
	private ProdBaseDao prodBaseDao;
	
	@Autowired
	@Qualifier(FileServiceImpl.BEAN_ID)
	private FileService fileService;
	

	public static final String BEAN_ID = "salePoServiceImpl";

	@Override
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		salePoDao.findBaseListPage(page, map);
	}

	@Override
	public SalePoMain findBaseByCode(String saleCode) {
		return salePoDao.findBaseByCode(saleCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveOrUpdate(SalePoMain entity,
			List<SalePoItem> list, String action) {
		Map<String, Object> result = new HashMap<>(0);
		List<SalePoItem> list2Save = new ArrayList<>();
		List<SalePoItem> list2Update = new ArrayList<>();

		String pkId = entity.getPkId();
		String seqCode = "";
		String itemId;

		if (UserActionType.SAVE.value().equals(action)) {
			if (StringUtils.isEmpty(pkId)) {
				entity.setStatus(CommonStatus.CREATED.value());
				result = salePoDao.saveMain(entity);
				seqCode = result.get("seqCode").toString();
				// 保存完清空购物车表
				prodBaseDao.delShop();
			} else {
				seqCode = entity.getSalePoCode();
				salePoDao.updateMain(entity);
			}

		} else if (UserActionType.SUBMIT.value().equals(action)) {
			entity.setStatus(CommonStatus.SUBMITED.value());
			salePoDao.updateMain(entity);
			seqCode = entity.getSalePoCode();
		} else if (UserActionType.DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.DELETED.value());
			salePoDao.updateMain(entity);
			seqCode = entity.getSalePoCode();
		} else if (UserActionType.RE_DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.CREATED.value());
			salePoDao.updateMain(entity);
			seqCode = entity.getSalePoCode();
		}
		// 保存更新明细行
		for (SalePoItem t : list) {
			itemId = t.getPkId();
			if (StringUtils.isEmpty(itemId)) {
				t.setSalePoCode(seqCode);
				list2Save.add(t);
			} else {
				// 判断是否需要删除
				EntityState status = EntityUtils.getState(t);
				if ((EntityState.DELETED.equals(status))) {
					t.setDelFlg(DeletedFlag.YES.value());
				}
				list2Update.add(t);
			}
		}
		salePoDao.saveItem(list2Save);
		salePoDao.updateItem(list2Update);
		return result;

	}

	@Override
	public List<SalePoItem> findItemByCode(String saleCode) {
		return salePoDao.findItemByCode(saleCode);
	}

	@Override
	public List<Map<String, Object>> findItemListByCode(String saleCode) {
		return salePoDao.findItemListByCode(saleCode);

	}
	@Override
	public List<PayMain> findPayListByCode(String saleCode) {
		return salePoDao.findPayListByCode(saleCode);
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String saleCode) {
		return salePoDao.findBaseMapByCode(saleCode);
	}

	@Override
	public List<Map<String, Object>> findItemMapByCode(String saleCode) {
		return salePoDao.findItemMapByCode(saleCode);
	}

	@Override
	public List<Map<String, Object>> findPoItemInfoByShop() {
		return salePoDao.findPoItemInfoByShop();
	}

	@Override
	public List<SysFile> getProdImgList(List<SalePoItem> list) {
		List<String> listProd = new ArrayList<>();
		for (SalePoItem t : list) {
			listProd.add(t.getProdCode());
		}
		List<SysFile> listFile = fileService
				.findListImagesByProdCodeByCodes(listProd);
		return listFile;
	}


}
