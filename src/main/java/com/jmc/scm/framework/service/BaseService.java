package com.jmc.scm.framework.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;

/**
 * 基础Service定义，对Dao的功能做引用，方便简单功能直接调用Dao
 * 
 * @author 三影塔
 * 
 */
public interface BaseService {

	/**
	 * HQL分页查询，支持Map传参
	 * 
	 * @param page
	 *            dorado分页对象
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @throws Exception
	 */
	<T> void queryPage(Page<T> page, String query, Map<String, Object> parameter)
			throws Exception;

	/**
	 * HQL分页查询，支持数组传参
	 * 
	 * @param page
	 *            dorado分页对象
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @throws Exception
	 */
	<T> void queryPage(Page<T> page, String query, Object[] parameter)
			throws Exception;

	/**
	 * SQL分页查询，支持Map传参
	 * 
	 * @param page
	 *            dorado分页对象
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @param clazz
	 *            查询结果封装实体类
	 * @throws Exception
	 */
	<T> void queryPageBySql(Page<T> page, String query,
			Map<String, Object> parameter, Class<?> clazz) throws Exception;

	/**
	 * SQL分页查询，支持数组传参
	 * 
	 * @param page
	 *            dorado分页对象
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @param clazz
	 *            查询结果封装实体类
	 * @throws Exception
	 */
	<T> void queryPageBySql(Page<T> page, String query, Object[] parameter,
			Class<?> clazz) throws Exception;

	/**
	 * SQL分页查询，支持Map传参
	 * 
	 * @param page
	 *            dorado分页对象
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @throws Exception
	 */
	void queryPageBySql(Page<Map<String, Object>> page, String query,
			Map<String, Object> parameter) throws Exception;

	/**
	 * SQL分页查询，支持数组传参
	 * 
	 * @param page
	 *            dorado分页对象
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @throws Exception
	 */
	void queryPageBySql(Page<Map<String, Object>> page, String query,
			Object[] parameter) throws Exception;

	/**
	 * HQL列表查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<T> 查询结果集
	 */
	<T> List<T> queryList(String query, Map<String, Object> parameter);

	/**
	 * HQL列表查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<T> 查询结果集
	 */
	<T> List<T> queryList(String query, Object[] parameter);

	/**
	 * SQL列表查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @param clazz
	 *            查询结果封装实体类
	 * @return List<T> 查询结果集
	 */
	<T> List<T> queryListBySql(String query, Map<String, Object> parameter,
			Class<?> clazz);

	/**
	 * SQL列表查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @param clazz
	 *            查询结果封装实体类
	 * @return List<T> 查询结果集
	 */
	<T> List<T> queryListBySql(String query, Object[] parameter, Class<?> clazz);

	/**
	 * SQL列表查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<Map<String, Object>> 查询结果集
	 */
	List<Map<String, Object>> queryListBySql(String query,
			Map<String, Object> parameter);

	/**
	 * SQL列表查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<Map<String, Object>> 查询结果集
	 */
	List<Map<String, Object>> queryListBySql(String query, Object[] parameter);

	/**
	 * HQL实体查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	<T> T queryEntity(String query, Map<String, Object> parameter);

	/**
	 * HQL实体查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	<T> T queryEntity(String query, Object[] parameter);

	/**
	 * SQL实体查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @param clazz
	 *            查询结果封装实体类
	 * @return T 查询结果
	 */
	<T> T queryEntityBySql(String query, Map<String, Object> parameter,
			Class<?> clazz);

	/**
	 * SQL实体查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @param clazz
	 *            查询结果封装实体类
	 * @return T 查询结果
	 */
	<T> T queryEntityBySql(String query, Object[] parameter, Class<?> clazz);

	/**
	 * SQL实体查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	Map<String, Object> queryMapBySql(String query,
			Map<String, Object> parameter);

	/**
	 * SQL实体查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	Map<String, Object> queryMapBySql(String query, Object[] parameter);

	/**
	 * 对象查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	Object queryObject(String query, Map<String, Object> parameter);

	/**
	 * 对象查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	Object queryObject(String query, Object[] parameter);

	/**
	 * 对象查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	Object queryObjectBySql(String query, Map<String, Object> parameter);

	/**
	 * 对象查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	Object queryObjectBySql(String query, Object[] parameter);

	/**
	 * 保存Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	<T> void save(T entity);

	/**
	 * 保存Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	<T> void saveAll(List<T> entities);

	/**
	 * 更新Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	<T> void update(T entity);

	/**
	 * 更新Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	<T> void updateAll(List<T> entities);

	/**
	 * 删除Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	<T> void delete(T entity);

	/**
	 * 删除Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	<T> void deleteAll(List<T> entities);

	/**
	 * 大批量删除Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	<T> void bulkDelete(List<T> entities);
}
