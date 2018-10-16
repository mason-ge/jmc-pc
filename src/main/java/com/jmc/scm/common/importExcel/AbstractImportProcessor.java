package com.jmc.scm.common.importExcel;

import java.util.HashMap;
import java.util.Map;

import com.bstek.bdf2.importexcel.ImportExcelHibernateDao;

/**
 * Excel导入时设置内存数据的父类，在导入时可以使用
 * 
 * @author 三影塔
 * 
 */
public abstract class AbstractImportProcessor extends ImportExcelHibernateDao {

	private Map<String, Object> tempData;

	/**
	 * 设置内存数据
	 * 
	 * @param key
	 *            唯一值
	 * @param data
	 *            内存数据
	 */
	public void putTempData(String key, Object data) {
		if (tempData == null) {
			tempData = new HashMap<String, Object>();
		}

		this.tempData.put(key, data);
	}

	/**
	 * 获取内存数据。如数据需要被前台获取，可以重写此方法，并设置Dorado识别标记，如@Expose或@DataProvider
	 * 
	 * @param key
	 *            唯一值
	 * @return Object 内存数据
	 */
	public Object getTempData(String key) {
		if (tempData == null) {
			return null;
		} else {
			return this.tempData.get(key);
		}
	}

	/**
	 * 清空内存数据，如果传入的key为空，则清空整个内存数据
	 * 
	 * @param key
	 *            唯一值
	 */
	public void clearTempData(String key) {
		if (this.tempData != null) {
			if (key == null) {
				this.tempData.clear();
			} else {
				this.tempData.remove(key);
			}
		}
	}

}
