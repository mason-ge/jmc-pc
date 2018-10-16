package com.jmc.scm.system.dao;

import java.util.List;
import java.util.Map;

import com.jmc.scm.system.model.SysFile;

/**
 * 文件Dao定义
 * 
 * @author 三影塔
 * 
 */
public interface FileDao {

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
	List<SysFile> queryFile(String bizCode);

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
	List<Map<String, Object>> findListPathByUris(List<Object> uris);

	/**
	 * 查询文件表列表，根据bizCode和绝对路径
	 * 
	 * @param list
	 * @return List
	 */
	List<SysFile> findListByListParam(List<Map<String, Object>> list);

}
