package com.jmc.scm.common.importExcel;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bstek.bdf2.importexcel.model.CellWrapper;
import com.bstek.bdf2.importexcel.model.ExcelDataWrapper;
import com.bstek.bdf2.importexcel.model.RowWrapper;
import com.bstek.bdf2.importexcel.processor.IExcelProcessor;
import com.bstek.dorado.annotation.Expose;

@Component("goodsInmportProcessor")
public class GoodsInmportProcessor extends AbstractImportProcessor
		implements IExcelProcessor {

	@Override
	public int execute(ExcelDataWrapper excel) throws Exception {
		List<Map<String, Object>> parseResultList = new LinkedList<>();
		for (RowWrapper row : excel.getRowWrappers()) {
			Map<String, Object> main = new HashMap<>();
			for (CellWrapper cell : row.getCellWrappers()) {
				String columuName = cell.getColumnName();
				Object value = cell.getValue();
				main.put(columuName, value);
			}
			parseResultList.add(main);
		}
		super.putTempData("info",parseResultList);
		return excel.getRowWrappers().size();
	}

	@Override
	public String getName() {
		return "成品入库导入";
	}
	
	@Expose
	@Override
	public Object getTempData(String key) {
		return super.getTempData(key);
	}

}
