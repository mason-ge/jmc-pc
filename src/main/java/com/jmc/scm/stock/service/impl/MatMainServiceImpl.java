package com.jmc.scm.stock.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.stock.dao.MatMainDao;
import com.jmc.scm.stock.dao.impl.MatMainDaoImpl;
import com.jmc.scm.stock.model.StockInvMain;
import com.jmc.scm.stock.model.StockMvDetail;
import com.jmc.scm.stock.service.MatMainService;

@Service("matMainServiceImpl")
public class MatMainServiceImpl implements MatMainService {

	public static final String BEAN_ID = "matMainServiceImpl";

	@Autowired
	@Qualifier(MatMainDaoImpl.BEAN_ID)
	private MatMainDao matMainDao;

	@Override
	public void findMainListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		matMainDao.findMainListPage(page, map);

	}

	@Override
	public void findDetailListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		matMainDao.findDetailListPage(page, map);
	}

	@Override
	public List<StockInvMain> findMainListByListCodes(List<String> list) {
		return matMainDao.findMainListByListCodes(list);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveFirstMat(List<StockInvMain> list) {
		List<StockMvDetail> detail2Save = new ArrayList<>();
		try {
			// 保存主表
			matMainDao.saveMain(list);
			// 同时保存进明细表
			for (StockInvMain t : list) {
				StockMvDetail d = new StockMvDetail();
				d.setMatCode(t.getMatCode());
				d.setMatName(t.getMatName());
				d.setNums(t.getNums());
				d.setBaseUnit(t.getBaseUnit());
				d.setWeight(t.getWeight());
				d.setStorageDate(t.getStorageDate());
				d.setAmount(t.getAmount());
				d.setSupCode(t.getSupCode());
				d.setSupName(t.getSupName());
				d.setRemarks(t.getRemarks());
				detail2Save.add(d);
			}
			matMainDao.saveItem(detail2Save);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public StockInvMain findMainByCode(String matCode) {
		return matMainDao.findMainByCode(matCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void saveMod(StockMvDetail entity, String inOutFlg) {
		String matCode = entity.getMatCode();
		BigDecimal nums = BigDecimal.ZERO, amount = BigDecimal.ZERO;
		BigDecimal mainNums = BigDecimal.ZERO, mainAmount = BigDecimal.ZERO;
		StockInvMain main;
		List<StockInvMain> listMain2Update = new ArrayList<>();
		List<StockMvDetail> listDetail2Save = new ArrayList<>();
		try {
			// 新增一条出入库明细数据
			if (entity.getNums() != null) {
				nums = entity.getNums();
			}
			if (entity.getAmount() != null) {
				amount = entity.getAmount();
			}
			// 出库的时候数量和金额为负数
			if ("out".equals(inOutFlg)) {
				nums = BigDecimal.ZERO.subtract(nums);
				amount = BigDecimal.ZERO.subtract(amount);
			}
			entity.setNums(nums);
			entity.setAmount(amount);
			listDetail2Save.add(entity);
			matMainDao.saveItem(listDetail2Save);

			// 更新主数据的数量、金额
			main = this.findMainByCode(matCode);
			if (main != null) {
				if (main.getNums() != null) {
					mainNums = main.getNums();
				}
				if (main.getAmount() != null) {
					mainAmount = main.getAmount();
				}
				main.setNums(mainNums.add(nums));
				main.setAmount(mainAmount.add(amount));
				listMain2Update.add(main);
				matMainDao.updateMain(listMain2Update);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateMain(List<StockInvMain> list) {
		matMainDao.updateMain(list);
	}

}
