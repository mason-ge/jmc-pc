package com.jmc.scm.common.importExcel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.bdf2.importexcel.model.CellWrapper;
import com.bstek.bdf2.importexcel.model.ExcelDataWrapper;
import com.bstek.bdf2.importexcel.model.RowWrapper;
import com.bstek.bdf2.importexcel.processor.IExcelProcessor;
import com.bstek.dorado.annotation.Expose;
import com.jmc.scm.baseData.dao.ProdBaseDao;
import com.jmc.scm.baseData.dao.impl.ProdBaseDaoImpl;
import com.jmc.scm.baseData.model.ProdBaseInfo;
import com.jmc.scm.baseData.service.ProdBaseService;
import com.jmc.scm.baseData.service.impl.ProdBaseServiceImpl;
import com.jmc.scm.system.service.EnumService;
import com.jmc.scm.system.service.impl.EnumServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Component("prodImportProcessor")
public class ProdImportProcessor extends AbstractImportProcessor implements
		IExcelProcessor {

	@Autowired
	@Qualifier(ProdBaseServiceImpl.BEAN_ID)
	private ProdBaseService prodBaseService;

	@Autowired
	@Qualifier(ProdBaseDaoImpl.BEAN_ID)
	private ProdBaseDao prodBaseDao;

	@Autowired
	@Qualifier(EnumServiceImpl.BEAN_ID)
	private EnumService enumService;

	// 枚举值
	// 品类一级
	public static final String ENUM_FIRST = "PLYJ";
	// 品类二级
	public static final String ENUM_SEC = "PLEJ";

	@Override
	public int execute(ExcelDataWrapper excel) throws Exception {
		List<Map<String, Object>> parseResultList = new LinkedList<>();
		String columuName;
		Object value;
		for (RowWrapper row : excel.getRowWrappers()) {
			Map<String, Object> main = new HashMap<>();
			for (CellWrapper cell : row.getCellWrappers()) {
				columuName = cell.getColumnName();
				if (cell.getValue() instanceof Double) {
					value = ScmUtil.getBigDecimal(cell.getValue());
				} else {
					value = ScmUtil.object2String(cell.getValue());
				}
				main.put(columuName, value);
			}
			parseResultList.add(main);
		}
		// 校验数据
		this.checkImpData(parseResultList);
		// 校验没问题保存到数据库
		this.saveImpData(parseResultList);
		super.putTempData("info", parseResultList);
		return excel.getRowWrappers().size();
	}

	@Transactional
	private void saveImpData(List<Map<String, Object>> list) {
		ProdBaseInfo base;
		for (Map<String, Object> t : list) {
			base = new ProdBaseInfo();
			base.setProdCode(ScmUtil.object2String(t.get("prodCode")));
			base.setProdName(ScmUtil.object2String(t.get("prodName")));
			base.setOtherProdCode(ScmUtil.object2String(t.get("otherProdCode")));
			base.setBrand(ScmUtil.object2String(t.get("brand")));
			base.setMeasUnit(ScmUtil.object2String(t.get("measUnit")));
			base.setInvtyUnit(ScmUtil.object2String(t.get("invtyUnit")));
			base.setInvtyMgtAttr(ScmUtil.object2String(t.get("invtyMgtAttr")));
			base.setSupCode(ScmUtil.object2String(t.get("supCode")));
			base.setSupName(ScmUtil.object2String(t.get("supName")));
			base.setProdTheme(ScmUtil.object2String(t.get("prodTheme")));
			base.setFirstRange(ScmUtil.object2String(t.get("firstRange")));
			base.setFirstCatg(ScmUtil.object2String(t.get("firstCatg")));
			base.setSecCatg(ScmUtil.object2String(t.get("secCatg")));
			base.setArmType(ScmUtil.object2String(t.get("armType")));
			base.setModel(ScmUtil.object2String(t.get("model")));
			base.setShape(ScmUtil.object2String(t.get("shape")));
			base.setMosType(ScmUtil.object2String(t.get("mosType")));
			base.setProdCraft(ScmUtil.object2String(t.get("prodCraft")));
			base.setSurfHandleRem(ScmUtil.object2String(t.get("surfHandleRem")));
			base.setOtherCraftRem(ScmUtil.object2String(t.get("otherCraftRem")));
			base.setCmptRem(ScmUtil.object2String(t.get("cmptRem")));
			base.setDesignRem(ScmUtil.object2String(t.get("designRem")));
			base.setAttr1(ScmUtil.object2String(t.get("attr1")));
			base.setAttr2(ScmUtil.object2String(t.get("attr2")));
			base.setAttr3(ScmUtil.object2String(t.get("attr3")));
			base.setAttr4(ScmUtil.object2String(t.get("attr4")));
			base.setAttr5(ScmUtil.object2String(t.get("attr5")));
			prodBaseDao.saveMain(base);
		}
	}

	private void checkImpData(List<Map<String, Object>> list) {
		String prodCode, key1, key2, erroProdCodes = "";
		List<ProdBaseInfo> listProd;
		List<String> listProdCodes = new ArrayList<>();

		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> t : list) {
				prodCode = ScmUtil.object2String(t.get("prodCode"));
				listProdCodes.add(prodCode);
			}
			/**
			 * 一：校验EXCEL的商品编码不可重复
			 */
			if (!ScmUtil.checkRepeat(listProdCodes.toArray())) {
				throw new RuntimeException("存在相同的商品编码！");
			}
			/**
			 * 二：校验数据库重是否存在相同的商品编码
			 */
			listProd = prodBaseService.findProdListByCodes(listProdCodes);
			if (listProd != null && !listProd.isEmpty()) {
				for (ProdBaseInfo t : listProd) {
					for (Map<String, Object> i : list) {
						key1 = t.getProdCode();
						key2 = i.get("prodCode").toString();
						if (key1.equals(key2)) {
							erroProdCodes += key1 + ",";
						}
					}
				}
				if (!"".equals(erroProdCodes)) {
					erroProdCodes = erroProdCodes.substring(0,
							erroProdCodes.length() - 1);
					throw new RuntimeException("以下商品编码在系统中已存在！ :"
							+ erroProdCodes + "");
				}
			}
			/**
			 * 三 ：校验枚举值是否存在
			 */
			// 1.品类一级
		} else {
			throw new RuntimeException("请至少添加一条数据！");
		}

	}

	@Override
	public String getName() {
		return "商品主数据导入";
	}

	@Expose
	@Override
	public Object getTempData(String key) {
		return super.getTempData(key);
	}

}
