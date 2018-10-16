package com.jmc.scm.sale.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.entity.EntityState;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.uploader.DownloadFile;
import com.jmc.scm.baseData.model.ClientBpChangeList;
import com.jmc.scm.baseData.model.ClientBpMoneyRelat;
import com.jmc.scm.baseData.service.CltBpService;
import com.jmc.scm.baseData.service.impl.CltBpServiceImpl;
import com.jmc.scm.common.CommonEnum.CommonStatus;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.common.CommonEnum.UserActionType;
import com.jmc.scm.sale.dao.SaleOutDao;
import com.jmc.scm.sale.dao.impl.SaleOutDaoImpl;
import com.jmc.scm.sale.model.PayMain;
import com.jmc.scm.sale.model.SaleOutItem;
import com.jmc.scm.sale.model.SaleOutMain;
import com.jmc.scm.sale.service.SaleOutService;
import com.jmc.scm.stock.dao.StockDao;
import com.jmc.scm.stock.dao.impl.StockDaoImpl;
import com.jmc.scm.stock.model.PropUniInfo;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Service("saleOutServiceImpl")
public class SaleOutServiceImpl implements SaleOutService {

	@Autowired
	@Qualifier(SaleOutDaoImpl.BEAN_ID)
	private SaleOutDao saleOutDao;

	@Autowired
	@Qualifier(StockDaoImpl.BEAN_ID)
	private StockDao stockDao;

	@Autowired
	@Qualifier(CltBpServiceImpl.BEAN_ID)
	private CltBpService cltBpService;

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	public static final String BEAN_ID = "saleOutServiceImpl";

	@Override
	public void findBaseListPage(Page<Map<String, Object>> page,
			Map<String, Object> map) {
		saleOutDao.findBaseListPage(page, map);
	}

	@Override
	public SaleOutMain findBaseByCode(String saleCode) {
		return saleOutDao.findBaseByCode(saleCode);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public Map<String, Object> saveOrUpdate(SaleOutMain entity,
			List<SaleOutItem> list, String action) {
		Map<String, Object> result = new HashMap<>(0);
		List<SaleOutItem> list2Save = new ArrayList<>();
		List<SaleOutItem> list2Update = new ArrayList<>();
		List<String> batchCodes = new ArrayList<>();
		List<PropUniInfo> listPropUpdate = new ArrayList<>();

		String pkId = entity.getPkId();
		String saleCode = "";
		String itemId;

		if (UserActionType.SAVE.value().equals(action)) {
			if (StringUtils.isEmpty(pkId)) {
				entity.setStatus(CommonStatus.CREATED.value());
				result = saleOutDao.saveMain(entity);
				saleCode = result.get("seqCode").toString();
			} else {
				saleCode = entity.getSaleCode();
				saleOutDao.updateMain(entity);
			}
		} else if (UserActionType.SUBMIT.value().equals(action)) {
			entity.setStatus(CommonStatus.SUBMITED.value());
			entity.setSubmitBy(ContextHolder.getLoginUserName());
			entity.setSubmitD(new Date());
			saleOutDao.updateMain(entity);
			saleCode = entity.getSaleCode();
			// 更新单品属性的数量为0
			for (SaleOutItem t : list) {
				// 判断是否需要删除
				EntityState status = EntityUtils.getState(t);
				if (!(EntityState.DELETED.equals(status))) {
					batchCodes.add(t.getBatchCode());
				}
			}
			List<PropUniInfo> listProp = stockDao
					.findPropInfoListByCodes(batchCodes);
			if (listProp != null && !listProp.isEmpty()) {
				BigDecimal re = BigDecimal.valueOf(0);
				for (PropUniInfo t : listProp) {
					re = t.getQty().subtract(BigDecimal.valueOf(1));
					if (re.compareTo(BigDecimal.valueOf(0)) >= 0) {
						t.setQty(re);
						listPropUpdate.add(t);
					} else {
						throw new RuntimeException("该操作会造成库存为负！请检查货号："
								+ t.getBatchCode() + "的数据！");
					}
				}
				stockDao.updatePropMain(listPropUpdate);
			}
			// 发货时候如果有填积分，写入积分表和明细表
			if (entity.getCltNowBp() != null) {
				ClientBpChangeList cbNew = new ClientBpChangeList();
				cbNew.setCltCode(entity.getCltCode());
				cbNew.setCltName(entity.getCltName());
				cbNew.setSaleCode(entity.getSaleCode());
				cbNew.setBp(entity.getCltNowBp());
				cltBpService.saveChangeList(cbNew);
			}
			if (entity.getIntclientNowBp() != null) {
				ClientBpChangeList cbNew2 = new ClientBpChangeList();
				cbNew2.setCltCode(entity.getIntclientCode());
				cbNew2.setCltName(ScmUtil.getVirtualEntity(entity,
						"intclientName").toString());
				cbNew2.setSaleCode(entity.getSaleCode());
				cbNew2.setBp(entity.getIntclientNowBp());
				cltBpService.saveChangeList(cbNew2);
			}
		} else if (UserActionType.DEL.value().equals(action)) {
			entity.setStatus(CommonStatus.DELETED.value());
			entity.setDelFlg(DeletedFlag.YES.value());
			for (SaleOutItem t : list) {
				t.setDelFlg(DeletedFlag.YES.value());
			}
			saleOutDao.updateMain(entity);
			saleOutDao.updateItem(list2Update);
			saleCode = entity.getSaleCode();
		} else if (UserActionType.DIS_SUBMIT.value().equals(action)) {
			entity.setStatus(CommonStatus.CREATED.value());
			entity.setSubmitBy(null);
			entity.setSubmitD(null);
			saleOutDao.updateMain(entity);
			saleCode = entity.getSaleCode();
			// 更新单品属性的数量加1
			for (SaleOutItem t : list) {
				// 判断是否需要删除
				EntityState status = EntityUtils.getState(t);
				if (!(EntityState.DELETED.equals(status))) {
					batchCodes.add(t.getBatchCode());
				}
			}
			List<PropUniInfo> listProp = stockDao
					.findPropInfoListByCodes(batchCodes);
			if (listProp != null && !listProp.isEmpty()) {
				for (PropUniInfo t : listProp) {
					t.setQty(t.getQty().add(BigDecimal.valueOf(1)));
					listPropUpdate.add(t);
				}
				stockDao.updatePropMain(listPropUpdate);
			}

			// 取消发货时候如果有填积分，写入积分表和明细表
			if (entity.getCltNowBp() != null) {
				ClientBpChangeList cbNew = new ClientBpChangeList();
				cbNew.setCltCode(entity.getCltCode());
				cbNew.setCltName(entity.getCltName());
				cbNew.setBp(BigDecimal.ZERO.subtract(entity.getCltNowBp()));
				cbNew.setSaleCode(entity.getSaleCode());
				cltBpService.saveChangeList(cbNew);
			}
			if (entity.getIntclientNowBp() != null) {
				ClientBpChangeList cbNew2 = new ClientBpChangeList();
				cbNew2.setCltCode(entity.getIntclientCode());
				cbNew2.setCltName(ScmUtil.getVirtualEntity(entity,
						"intclientName").toString());
				cbNew2.setBp(BigDecimal.ZERO.subtract(entity
						.getIntclientNowBp()));
				cbNew2.setSaleCode(entity.getSaleCode());
				cltBpService.saveChangeList(cbNew2);
			}
		}

		if (UserActionType.SAVE.value().equals(action)
				|| UserActionType.SUBMIT.value().equals(action)
				|| UserActionType.DIS_SUBMIT.value().equals(action)) {
			for (SaleOutItem t : list) {
				itemId = t.getPkId();
				if (StringUtils.isEmpty(itemId)) {
					t.setSaleCode(saleCode);
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
			saleOutDao.saveItem(list2Save);
			saleOutDao.updateItem(list2Update);
		}
		return result;

	}

	@Override
	public List<SaleOutItem> findItemByCode(String saleCode) {
		return saleOutDao.findItemByCode(saleCode);
	}

	@Override
	public List<PayMain> findPayListByCode(String saleCode) {
		return saleOutDao.findPayListByCode(saleCode);
	}

	@Override
	public Map<String, Object> savePay(SaleOutMain dsBase, PayMain dsPaySure) {
		dsPaySure.setPrestoCode(dsBase.getSaleCode());
		dsPaySure.setPayFlg("A");
		saleOutDao.savePay(dsPaySure);
		return null;
	}

	@Override
	public Map<String, Object> findBaseMapByCode(String saleCode) {
		return saleOutDao.findBaseMapByCode(saleCode);
	}

	@Override
	public List<Map<String, Object>> findItemMapByCode(String saleCode) {
		return saleOutDao.findItemMapByCode(saleCode);

	}

	@Override
	public DownloadFile exportExcelFile(String checkCode) {
		SaleOutMain main;
		List<Map<String, Object>> listItem;
		DownloadFile file = null;
		ByteArrayOutputStream bos;
		ByteArrayInputStream bis;
		Workbook workbook = null;
		Sheet sheet;
		Row row;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
		final String fileName = checkCode + "出货单.xlsx";
		final String pathName = "/scm/template/出货单打印模板.xlsx";
		CellStyle style = null;
		try {
			// 数据准备
			main = this.findBaseByCode(checkCode);
			listItem = this.findItemMapByCode(checkCode);
			// 下载FTP上的导出模板
			InputStream is = ftpService.downloadFile(pathName);
			workbook = WorkbookFactory.create(is);
			// 设置样式(边框线)
			style = workbook.createCellStyle();
			style.setBorderLeft(CellStyle.BORDER_THIN);
			style.setBorderRight(CellStyle.BORDER_THIN);
			style.setBorderTop(CellStyle.BORDER_THIN);
			style.setBorderBottom(CellStyle.BORDER_THIN);
			// 关闭流
			is.close();

			// 拿到sheet1
			sheet = workbook.getSheetAt(0);
			// 设置单元格的公式自动计算
			sheet.setForceFormulaRecalculation(true);

			// 抬头信息设置
			// 第二行
			row = sheet.getRow(1);
			row.createCell(5);
			row.getCell(5).setCellValue(sdf.format(new Date()));

			// 第三行
			row = sheet.getRow(2);
			row.createCell(2);
			row.getCell(2).setCellValue(
					ScmUtil.object2String(main.getCltName()));
			row.createCell(6);
			if (main.getDeliDate() == null || "".equals(main.getDeliDate())) {
				row.getCell(6).setCellValue(sdf2.format(main.getCreatedD()));
			} else {
				row.getCell(6).setCellValue(sdf2.format(main.getDeliDate()));
			}

			// 列表信息设置
			// 从第6行开始设置数据
			int rowNum = 5;
			BigDecimal totNum = BigDecimal.valueOf(0);
			BigDecimal totSale = BigDecimal.valueOf(0);
			for (Map<String, Object> t : listItem) {
				sheet.createRow(rowNum);
				row = sheet.getRow(rowNum);

				row.createCell(0);
				row.getCell(0).setCellStyle(style);
				row.getCell(0).setCellValue(
						ScmUtil.object2String(t.get("itemCode")));

				row.createCell(1);
				row.getCell(1).setCellStyle(style);
				row.getCell(1).setCellValue(
						ScmUtil.object2String(t.get("batchCode")));

				row.createCell(2);
				row.getCell(2).setCellStyle(style);
				row.getCell(2).setCellValue(
						ScmUtil.object2String(t.get("spName")));

				row.createCell(3);
				row.getCell(3).setCellStyle(style);
				row.getCell(3).setCellValue(
						ScmUtil.object2String(t.get("nums")));
				if (t.get("nums") != null && !"".equals(t.get("nums"))) {
					totNum = totNum.add(ScmUtil.getBigDecimal(t.get("nums")));
				}

				row.createCell(4);
				row.getCell(4).setCellStyle(style);
				row.getCell(4).setCellValue(
						ScmUtil.object2String(t.get("retPrice")));

				row.createCell(5);
				row.getCell(5).setCellStyle(style);
				row.getCell(5).setCellValue(
						ScmUtil.object2String(t.get("disct")));

				row.createCell(6);
				row.getCell(6).setCellStyle(style);
				row.getCell(6).setCellValue(
						ScmUtil.object2String(t.get("salePrice")));

				if (t.get("salePrice") != null
						&& !"".equals(t.get("salePrice"))) {
					totSale = totSale.add(ScmUtil.getBigDecimal(t
							.get("salePrice")));
				}

				row.createCell(7);
				row.getCell(7).setCellStyle(style);
				row.getCell(7).setCellValue(
						ScmUtil.object2String(t.get("remark")));

				rowNum++;
			}

			// 合计行计算
			sheet.createRow(rowNum);
			row = sheet.getRow(rowNum);

			row.createCell(0);
			row.getCell(0).setCellStyle(style);

			row.createCell(1);
			row.getCell(1).setCellStyle(style);

			row.createCell(2);
			row.getCell(2).setCellStyle(style);
			row.getCell(2).setCellValue("合计：");

			row.createCell(3);
			row.getCell(3).setCellStyle(style);
			row.getCell(3).setCellValue(ScmUtil.object2String(totNum));

			row.createCell(4);
			row.getCell(4).setCellStyle(style);
			row.getCell(4).setCellValue(ScmUtil.object2String(totSale));

			row.createCell(5);
			row.getCell(5).setCellStyle(style);

			row.createCell(6);
			row.getCell(6).setCellStyle(style);

			row.createCell(7);
			row.getCell(7).setCellStyle(style);

			// 落款
			sheet.createRow(rowNum + 2);
			row = sheet.getRow(rowNum + 2);
			row.createCell(1);
			row.getCell(1).setCellValue(
					"制单人：" + ContextHolder.getLoginUser().getCname());

			row.createCell(5);
			row.getCell(5).setCellValue(
					"制单时间：" + sdf2.format(main.getCreatedD()));

			// 将文件存到指定位置
			bos = new ByteArrayOutputStream();
			workbook.write(bos);
			bis = new ByteArrayInputStream(bos.toByteArray());
			file = new DownloadFile(fileName, bis);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return file;
	}

	@Override
	public ClientBpMoneyRelat findBpRelat() {
		return this.saleOutDao.findBpRelat();
	}

}
