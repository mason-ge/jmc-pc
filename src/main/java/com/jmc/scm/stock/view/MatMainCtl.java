package com.jmc.scm.stock.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.framework.service.BaseService;
import com.jmc.scm.framework.service.impl.BaseServiceImpl;
import com.jmc.scm.stock.model.StockInvMain;
import com.jmc.scm.stock.model.StockMvDetail;
import com.jmc.scm.stock.service.MatMainService;
import com.jmc.scm.stock.service.impl.MatMainServiceImpl;
import com.jmc.scm.system.model.SysFile;

/**
 * 物料库存管理控制层
 * 
 * @author Mason_Ge
 * 
 */
@Component("matMainCtl")
public class MatMainCtl {

	@Autowired
	@Qualifier(MatMainServiceImpl.BEAN_ID)
	private MatMainService matMainService;

	@Autowired
	@Qualifier(BaseServiceImpl.BEAN_ID)
	private BaseService baseService;

	/**
	 * 分页查询主页面数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getMainListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		matMainService.findMainListPage(page, map);
	}

	/**
	 * 分页查询主页面明细数据List
	 * 
	 * @param map
	 * @return
	 */
	@DataProvider
	public void getDetailListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		matMainService.findDetailListPage(page, map);
	}

	/**
	 * 根据物料号S查询库存主表列表
	 * 
	 * @param list
	 * @return
	 */
	@DataProvider
	public List<StockInvMain> getMainListByListCodes(List<String> list) {
		return matMainService.findMainListByListCodes(list);
	}

	/**
	 * 检查库存是否已经存在首次入库的物料号
	 * 
	 * @param list
	 * @return
	 */
	@Expose
	public String checkSt(List<StockInvMain> list) {
		String result = "", key1, key2;
		List<String> listCodes = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (StockInvMain t : list) {
				listCodes.add(t.getMatCode());
			}
			List<StockInvMain> listInvMain = this
					.getMainListByListCodes(listCodes);
			if (listInvMain != null && !listInvMain.isEmpty()) {
				for (StockInvMain p : listInvMain) {
					for (StockInvMain t : list) {
						key1 = p.getMatCode();
						key2 = t.getMatCode();
						if (key1.equals(key2)) {
							result += key1 + ",";
							break;
						}
					}
				}
			}
		}
		if (!"".equals(result)) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/**
	 * 保存首次入库
	 * 
	 * @param main
	 * @param detail
	 */
	@DataResolver
	public void saveFirstMat(List<StockInvMain> list) {
		matMainService.saveFirstMat(list);
	}

	/**
	 * 出库校验已有的库存，是否大于数量。金额
	 * 
	 * @param entity
	 * @return
	 */
	@Expose
	public String checkStock(StockMvDetail entity) {
		StockInvMain main;
		String result = "";
		String matCode = entity.getMatCode();
		BigDecimal num, amount;

		try {
			// 根据物料号查询主表数据
			main = this.getMainByCode(matCode);
			if (main != null) {
				// 校验数量
				num = entity.getNums();
				if (num != null) {
					if (main.getNums() == null) {
						result += "出库数量不能大于已有库存数量： 0 ;";
					} else {
						if (num.compareTo(main.getNums()) > 0) {
							result += "出库数量不能大于已有库存数量： " + main.getNums()
									+ " ;";
						}
					}
				}
				// 校验金额
				amount = entity.getAmount();
				if (amount != null) {
					if (main.getAmount() == null) {
						result += "出库金额不能大于已有库存金额： 0 ;";
					} else {
						if (amount.compareTo(main.getAmount()) > 0) {
							result += "出库数量不能大于已有库存金额： " + main.getAmount()
									+ " ;";
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return result;
	}

	/**
	 * 根据物料号查询主表实体
	 * 
	 * @param matCode
	 * @return
	 */
	@DataProvider
	@Expose
	public StockInvMain getMainByCode(String matCode) {
		return matMainService.findMainByCode(matCode);
	}

	/**
	 * 出入库更新
	 * 
	 * @param entity
	 * @param inOutFlg
	 */
	@DataResolver
	public void saveMod(StockMvDetail entity, String inOutFlg) {
		matMainService.saveMod(entity, inOutFlg);
	}

	/**
	 * 更新入库信息
	 * 
	 * @param main
	 * @param detail
	 */
	@DataResolver
	public void updateMain(StockInvMain entity) {
		List<StockInvMain> list2Update = new ArrayList<>();
		list2Update.add(entity);
		matMainService.updateMain(list2Update);
	}

	/**
	 * 批量删除图片
	 * 
	 * @param files
	 */
	@Transactional
	@DataResolver
	public void delImgs(List<SysFile> files) {
		// 先删除数据库的记录的图片信息
		baseService.deleteAll(files);
		// 删除FTP上的图片
		// TODO
	}

	/**
	 * 删除图片
	 * 
	 * @param files
	 */
	@Transactional
	@DataResolver
	public void delImg(SysFile file) {
		// 先删除数据库的记录的图片信息
		baseService.delete(file);
		// 删除FTP上的图片
		// TODO
	}
}
