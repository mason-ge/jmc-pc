package com.jmc.scm.framework.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.framework.service.BaseService;

/**
 * 基础Service实体类
 * 
 * @author 三影塔
 * 
 */
@Service("baseServiceImpl")
public class BaseServiceImpl implements BaseService {

	public static final String BEAN_ID = "baseServiceImpl";

	@Autowired
	@Qualifier(BaseDaoImpl.BEAN_ID)
	private BaseDao baseDao;

	@Override
	public <T> void queryPage(Page<T> page, String query,
			Map<String, Object> parameter) throws Exception {
		baseDao.queryPage(page, query, parameter);
	}

	@Override
	public <T> void queryPage(Page<T> page, String query, Object[] parameter)
			throws Exception {
		baseDao.queryPage(page, query, parameter);
	}

	@Override
	public <T> void queryPageBySql(Page<T> page, String query,
			Map<String, Object> parameter, Class<?> clazz) throws Exception {
		baseDao.queryPageBySql(page, query, parameter, clazz);
	}

	@Override
	public <T> void queryPageBySql(Page<T> page, String query,
			Object[] parameter, Class<?> clazz) throws Exception {
		baseDao.queryPageBySql(page, query, parameter, clazz);
	}

	@Override
	public void queryPageBySql(Page<Map<String, Object>> page, String query,
			Map<String, Object> parameter) throws Exception {
		baseDao.queryPageBySql(page, query, parameter);
	}

	@Override
	public void queryPageBySql(Page<Map<String, Object>> page, String query,
			Object[] parameter) throws Exception {
		baseDao.queryPageBySql(page, query, parameter);
	}

	@Override
	public <T> List<T> queryList(String query, Map<String, Object> parameter) {
		return baseDao.queryList(query, parameter);
	}

	@Override
	public <T> List<T> queryList(String query, Object[] parameter) {
		return baseDao.queryList(query, parameter);
	}

	@Override
	public <T> List<T> queryListBySql(String query,
			Map<String, Object> parameter, Class<?> clazz) {
		return baseDao.queryListBySql(query, parameter, clazz);
	}

	@Override
	public <T> List<T> queryListBySql(String query, Object[] parameter,
			Class<?> clazz) {
		return baseDao.queryListBySql(query, parameter, clazz);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String query,
			Map<String, Object> parameter) {
		return baseDao.queryListBySql(query, parameter);
	}

	@Override
	public List<Map<String, Object>> queryListBySql(String query,
			Object[] parameter) {
		return baseDao.queryListBySql(query, parameter);
	}

	@Override
	public <T> T queryEntity(String query, Map<String, Object> parameter) {
		return baseDao.queryEntity(query, parameter);
	}

	@Override
	public <T> T queryEntity(String query, Object[] parameter) {
		return baseDao.queryEntity(query, parameter);
	}

	@Override
	public <T> T queryEntityBySql(String query, Map<String, Object> parameter,
			Class<?> clazz) {
		return baseDao.queryEntityBySql(query, parameter, clazz);
	}

	@Override
	public <T> T queryEntityBySql(String query, Object[] parameter,
			Class<?> clazz) {
		return baseDao.queryEntityBySql(query, parameter, clazz);
	}

	@Override
	public Map<String, Object> queryMapBySql(String query,
			Map<String, Object> parameter) {
		return baseDao.queryMapBySql(query, parameter);
	}

	@Override
	public Map<String, Object> queryMapBySql(String query, Object[] parameter) {
		return baseDao.queryMapBySql(query, parameter);
	}

	@Override
	public Object queryObject(String query, Map<String, Object> parameter) {
		return baseDao.queryObject(query, parameter);
	}

	@Override
	public Object queryObject(String query, Object[] parameter) {
		return baseDao.queryObject(query, parameter);
	}

	@Override
	public Object queryObjectBySql(String query, Map<String, Object> parameter) {
		return baseDao.queryObjectBySql(query, parameter);
	}

	@Override
	public Object queryObjectBySql(String query, Object[] parameter) {
		return baseDao.queryObjectBySql(query, parameter);
	}

	@Override
	public <T> void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public <T> void saveAll(List<T> entities) {
		baseDao.saveAll(entities);
	}

	@Override
	public <T> void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public <T> void updateAll(List<T> entities) {
		baseDao.updateAll(entities);
	}

	@Override
	public <T> void delete(T entity) {
		baseDao.delete(entity);
	}

	@Override
	public <T> void deleteAll(List<T> entities) {
		baseDao.deleteAll(entities);
	}

	@Override
	public <T> void bulkDelete(List<T> entities) {
		baseDao.bulkDelete(entities);
	}

}
