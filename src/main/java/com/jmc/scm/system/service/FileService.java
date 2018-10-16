package com.jmc.scm.system.service;

import java.util.List;
import java.util.Map;

import com.jmc.scm.system.model.SysFile;

/**
 * SCM文件存储
 * 
 * @author Mason Ge
 * 
 */
public interface FileService {

	/**
	 * 保存附件
	 * 
	 * @param params
	 */
	Map<String, Object> saveAtt(Map<String, Object> params);

	/**
	 * 查询附件
	 * 
	 * @param bizCode
	 *            业务主键
	 * @return
	 */
	List<SysFile> findFile(String bizCode);

	/**
	 * 查询单一附件
	 * 
	 * @param parameter
	 * @return
	 */
	SysFile findFile(Map<String, Object> parameter);

	/**
	 * 删除附件（只删除SCM附件表里记录）
	 * 
	 * @param params
	 */
	void deleteAtt(Map<String, Object> params);

	/**
	 * 通过URI查询图片的绝对路径，返回URI与图片的对应关系
	 * 
	 * @param uris
	 * @return
	 */
	Map<String, Object> findPathUriMappingByUris(List<Object> uris);

	/**
	 * 查询ftp商品图片列表
	 * 
	 * @param prodCode
	 *            商品编码
	 * @return List ftp商品图片列表
	 */
	List<SysFile> findListImagesByProdCode(String prodCode);

	/**
	 * 商品图片查询（多个商品编码一次查询）
	 * 
	 * @param list
	 *            商品编码list
	 * @return List 匹配的商品图片
	 */
	List<SysFile> findListImagesByProdCodeByCodes(List<String> list);

	/**
	 * 查询文件表列表，根据bizCode和绝对路径
	 * 
	 * @param list
	 * @return List
	 */
	List<SysFile> findListByListParam(List<Map<String, Object>> list);

}
