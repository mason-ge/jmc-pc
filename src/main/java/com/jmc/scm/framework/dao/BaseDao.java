package com.jmc.scm.framework.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import com.bstek.bdf2.core.CoreHibernateDao;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.common.CustomBeanResultTransformer;
import com.jmc.scm.common.CustomMapResultTransformer;
import com.jmc.scm.util.ScmUtil;

/**
 * 基础的Dao
 * 
 * @author 三影塔
 * 
 */
@Transactional
public abstract class BaseDao extends CoreHibernateDao {

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
	public <T> void queryPage(Page<T> page, final String query,
			Map<String, Object> parameter) throws Exception {
		String dataSourceName = this.getModuleFixDataSourceName();
		String countQuery = "select count(*) %s ";

		countQuery = String.format(countQuery, query);
		this.pagingQuery(page, query, countQuery, parameter, dataSourceName);
	}

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
	public <T> void queryPage(Page<T> page, final String query,
			Object[] parameter) throws Exception {
		String dataSourceName = this.getModuleFixDataSourceName();
		String countQuery = "select count(*) %s ";

		countQuery = String.format(countQuery, query);
		this.pagingQuery(page, query, countQuery, parameter, null,
				dataSourceName);
	}

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
	@SuppressWarnings("unchecked")
	public <T> void queryPageBySql(Page<T> page, final String query,
			Map<String, Object> parameter, Class<?> clazz) throws Exception {
		String countQuery = "select count(1) from (%s) countQuery";
		SQLQuery sqlQuery, countSqlQuery;
		List<T> result;
		int firstResult, maxResult, totalCount;

		countQuery = String.format(countQuery, query);
		sqlQuery = this.getSession().createSQLQuery(query);
		countSqlQuery = this.getSession().createSQLQuery(countQuery);
		firstResult = (page.getPageNo() - 1) * page.getPageSize();
		maxResult = page.getPageSize();

		for (Entry<String, Object> entry : parameter.entrySet()) {
			if (entry.getValue() instanceof Collection<?>) {
				sqlQuery.setParameterList(entry.getKey(),
						(Collection<?>) entry.getValue());
				countSqlQuery.setParameterList(entry.getKey(),
						(Collection<?>) entry.getValue());
			} else {
				sqlQuery.setParameter(entry.getKey(), entry.getValue());
				countSqlQuery.setParameter(entry.getKey(), entry.getValue());
			}
		}

		sqlQuery.setFirstResult(firstResult);
		sqlQuery.setMaxResults(maxResult);
		sqlQuery.setResultTransformer(new CustomBeanResultTransformer(clazz));

		result = sqlQuery.list();
		totalCount = ScmUtil.getBigDecimal(countSqlQuery.uniqueResult())
				.intValue();
		page.setEntities(result);
		page.setEntityCount(totalCount);
	}

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
	@SuppressWarnings("unchecked")
	public <T> void queryPageBySql(Page<T> page, final String query,
			Object[] parameter, Class<?> clazz) throws Exception {
		String countQuery = "select count(1) from (%s) countQuery";
		SQLQuery sqlQuery, countSqlQuery;
		List<T> result;
		int firstResult, maxResult, totalCount;

		countQuery = String.format(countQuery, query);
		sqlQuery = this.getSession().createSQLQuery(query);
		countSqlQuery = this.getSession().createSQLQuery(countQuery);
		firstResult = (page.getPageNo() - 1) * page.getPageSize();
		maxResult = page.getPageSize();

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
			countSqlQuery.setParameter(index, parameter[index]);
		}

		sqlQuery.setFirstResult(firstResult);
		sqlQuery.setMaxResults(maxResult);
		sqlQuery.setResultTransformer(new CustomBeanResultTransformer(clazz));

		result = sqlQuery.list();
		totalCount = ScmUtil.getBigDecimal(countSqlQuery.uniqueResult())
				.intValue();
		page.setEntities(result);
		page.setEntityCount(totalCount);
	}

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
	@SuppressWarnings("unchecked")
	public void queryPageBySql(Page<Map<String, Object>> page,
			final String query, Map<String, Object> parameter) throws Exception {
		String countQuery = "select count(1) from (%s) countQuery";
		SQLQuery sqlQuery, countSqlQuery;
		List<Map<String, Object>> result;
		int firstResult, maxResult, totalCount;

		countQuery = String.format(countQuery, query);
		sqlQuery = this.getSession().createSQLQuery(query);
		countSqlQuery = this.getSession().createSQLQuery(countQuery);
		firstResult = (page.getPageNo() - 1) * page.getPageSize();
		maxResult = page.getPageSize();

		for (Entry<String, Object> entry : parameter.entrySet()) {
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
			countSqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		sqlQuery.setFirstResult(firstResult);
		sqlQuery.setMaxResults(maxResult);
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = sqlQuery.list();
		totalCount = ScmUtil.getBigDecimal(countSqlQuery.uniqueResult())
				.intValue();
		page.setEntities(result);
		page.setEntityCount(totalCount);
	}
	
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
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> queryPageListBySql(Page<Map<String, Object>> page,
			final String query, Map<String, Object> parameter) throws Exception {
		String countQuery = "select count(1) from (%s) countQuery";
		SQLQuery sqlQuery, countSqlQuery;
		List<Map<String, Object>> result;
		int firstResult, maxResult, totalCount;

		countQuery = String.format(countQuery, query);
		sqlQuery = this.getSession().createSQLQuery(query);
		countSqlQuery = this.getSession().createSQLQuery(countQuery);
		firstResult = (page.getPageNo() - 1) * page.getPageSize();
		maxResult = page.getPageSize();

		for (Entry<String, Object> entry : parameter.entrySet()) {
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
			countSqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		sqlQuery.setFirstResult(firstResult);
		sqlQuery.setMaxResults(maxResult);
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = sqlQuery.list();
		totalCount = ScmUtil.getBigDecimal(countSqlQuery.uniqueResult())
				.intValue();
		page.setEntities(result);
		page.setEntityCount(totalCount);
		return result;
	}

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
	@SuppressWarnings("unchecked")
	public void queryPageBySql(Page<Map<String, Object>> page,
			final String query, Object[] parameter) throws Exception {
		String countQuery = "select count(1) from (%s) countQuery";
		SQLQuery sqlQuery, countSqlQuery;
		List<Map<String, Object>> result;
		int firstResult, maxResult, totalCount;

		countQuery = String.format(countQuery, query);
		sqlQuery = this.getSession().createSQLQuery(query);
		countSqlQuery = this.getSession().createSQLQuery(countQuery);
		firstResult = (page.getPageNo() - 1) * page.getPageSize();
		maxResult = page.getPageSize();

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
			countSqlQuery.setParameter(index, parameter[index]);
		}

		sqlQuery.setFirstResult(firstResult);
		sqlQuery.setMaxResults(maxResult);
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = sqlQuery.list();
		totalCount = ScmUtil.getBigDecimal(countSqlQuery.uniqueResult())
				.intValue();
		page.setEntities(result);
		page.setEntityCount(totalCount);
	}

	/**
	 * HQL列表查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<T> 查询结果集
	 */
	public <T> List<T> queryList(final String query,
			Map<String, Object> parameter) {
		List<T> result;

		result = this.query(query, parameter);
		return result;
	}

	/**
	 * HQL列表查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<T> 查询结果集
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> queryList(final String query, Object[] parameter) {
		List<T> result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);
		for (int index = 0; index < parameter.length; index++) {
			hqlQuery.setParameter(index, parameter[index]);
		}
		result = hqlQuery.list();
		return result;
	}

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
	@SuppressWarnings("unchecked")
	public <T> List<T> queryListBySql(final String query,
			Map<String, Object> parameter, Class<?> clazz) {
		List<T> result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			if (entry.getValue() instanceof Collection<?>) {
				sqlQuery.setParameterList(entry.getKey(),
						(Collection<?>) entry.getValue());
			} else {
				sqlQuery.setParameter(entry.getKey(), entry.getValue());
			}
		}

		sqlQuery.setResultTransformer(new CustomBeanResultTransformer(clazz));

		result = sqlQuery.list();
		return result;
	}

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
	@SuppressWarnings("unchecked")
	public <T> List<T> queryListBySql(final String query, Object[] parameter,
			Class<?> clazz) {
		List<T> result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
		}

		sqlQuery.setResultTransformer(new CustomBeanResultTransformer(clazz));

		result = sqlQuery.list();
		return result;
	}

	/**
	 * SQL列表查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<Map<String, Object>> 查询结果集
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryListBySql(final String query,
			Map<String, Object> parameter) {
		List<Map<String, Object>> result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			if (entry.getValue() instanceof Collection) {
				sqlQuery.setParameterList(entry.getKey(),
						(Collection<?>) entry.getValue());
			} else {
				sqlQuery.setParameter(entry.getKey(), entry.getValue());
			}
		}
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = sqlQuery.list();
		return result;
	}

	/**
	 * SQL列表查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return List<Map<String, Object>> 查询结果集
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryListBySql(final String query,
			Object[] parameter) {
		List<Map<String, Object>> result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
		}
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = sqlQuery.list();
		return result;
	}

	/**
	 * HQL实体查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryEntity(final String query, Map<String, Object> parameter) {
		T result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			hqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		result = (T) hqlQuery.uniqueResult();
		return result;
	}

	/**
	 * HQL实体查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	@SuppressWarnings("unchecked")
	public <T> T queryEntity(final String query, Object[] parameter) {
		T result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			hqlQuery.setParameter(index, parameter[index]);
		}

		result = (T) hqlQuery.uniqueResult();
		return result;
	}

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
	@SuppressWarnings("unchecked")
	public <T> T queryEntityBySql(final String query,
			Map<String, Object> parameter, Class<?> clazz) {
		T result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		sqlQuery.setResultTransformer(new CustomBeanResultTransformer(clazz));

		result = (T) sqlQuery.uniqueResult();
		return result;
	}

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
	@SuppressWarnings("unchecked")
	public <T> T queryEntityBySql(final String query, Object[] parameter,
			Class<?> clazz) {
		T result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
		}

		sqlQuery.setResultTransformer(new CustomBeanResultTransformer(clazz));

		result = (T) sqlQuery.uniqueResult();
		return result;
	}

	/**
	 * SQL实体查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryMapBySql(final String query,
			Map<String, Object> parameter) {
		Map<String, Object> result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
		}
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = (Map<String, Object>) sqlQuery.uniqueResult();
		return result;
	}

	/**
	 * SQL实体查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return T 查询结果
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> queryMapBySql(final String query,
			Object[] parameter) {
		Map<String, Object> result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
		}
		sqlQuery.setResultTransformer(new CustomMapResultTransformer());

		result = (Map<String, Object>) sqlQuery.uniqueResult();
		return result;
	}

	/**
	 * 对象查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	public Object queryObject(final String query, Map<String, Object> parameter) {
		Object result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			hqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		result = hqlQuery.uniqueResult();
		return result;
	}

	/**
	 * 对象查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	public Object queryObject(final String query, Object[] parameter) {
		Object result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			hqlQuery.setParameter(index, parameter[index]);
		}

		result = hqlQuery.uniqueResult();
		return result;
	}

	/**
	 * 对象查询，支持Map传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	public Object queryObjectBySql(final String query,
			Map<String, Object> parameter) {
		Object result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		result = sqlQuery.uniqueResult();
		return result;
	}

	/**
	 * 对象查询，支持数组传参
	 * 
	 * @param query
	 *            查询语句
	 * @param parameter
	 *            查询参数
	 * @return Object 查询对象
	 */
	public Object queryObjectBySql(final String query, Object[] parameter) {
		Object result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
		}

		result = sqlQuery.uniqueResult();
		return result;
	}

	/**
	 * 保存Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	public <T> void save(T entity) {
		this.getSession().save(entity);
	}

	/**
	 * 保存Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	public <T> void saveAll(List<T> entities) {
		for (T entity : entities) {
			this.getSession().save(entity);
		}
	}

	/**
	 * 保存或更新Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	public <T> void saveOrUpdate(T entity) {
		this.getSession().saveOrUpdate(entity);
	}

	/**
	 * 更新Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	public <T> void update(T entity) {
		this.getSession().update(entity);
	}

	/**
	 * 更新Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	public <T> void updateAll(List<T> entities) {
		for (T entity : entities) {
			this.getSession().update(entity);
		}
	}

	/**
	 * hql更新数据库实体，支持更新、删除，返回成功条数
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public int update(final String query, Map<String, Object> parameter) {
		int result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			if (entry.getValue() instanceof Collection) {
				hqlQuery.setParameterList(entry.getKey(),
						(Collection<?>) entry.getValue());
			} else {
				hqlQuery.setParameter(entry.getKey(), entry.getValue());
			}
		}

		result = hqlQuery.executeUpdate();

		return result;
	}

	/**
	 * hql更新数据库实体，支持更新、删除，返回成功条数
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public int update(final String query, Object[] parameter) {
		int result;
		Query hqlQuery;

		hqlQuery = this.getSession().createQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			hqlQuery.setParameter(index, parameter[index]);
		}

		result = hqlQuery.executeUpdate();

		return result;
	}

	/**
	 * sql更新数据库实体，支持更新、删除，返回成功条数
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public int updateBySql(final String query, Map<String, Object> parameter) {
		int result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (Entry<String, Object> entry : parameter.entrySet()) {
			sqlQuery.setParameter(entry.getKey(), entry.getValue());
		}

		result = sqlQuery.executeUpdate();

		return result;
	}

	/**
	 * sql更新数据库实体，支持更新、删除，返回成功条数
	 * 
	 * @param query
	 * @param parameter
	 * @return
	 */
	public int updateBySql(final String query, Object[] parameter) {
		int result;
		SQLQuery sqlQuery;

		sqlQuery = this.getSession().createSQLQuery(query);

		for (int index = 0; index < parameter.length; index++) {
			sqlQuery.setParameter(index, parameter[index]);
		}

		result = sqlQuery.executeUpdate();

		return result;
	}

	/**
	 * 删除Hibernate实体
	 * 
	 * @param entity
	 *            Hibernate实体
	 */
	public <T> void delete(T entity) {
		this.getSession().delete(entity);
	}

	/**
	 * 删除Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	public <T> void deleteAll(List<T> entities) {
		for (T entity : entities) {
			this.getSession().delete(entity);
		}
	}

	/**
	 * 大批量删除Hibernate实体列表
	 * 
	 * @param entities
	 *            Hibernate实体列表
	 */
	public <T> void bulkDelete(List<T> entities) {
		Transaction transaction = this.getSession().beginTransaction();
		transaction.begin();
		for (T entity : entities) {
			this.getSession().delete(entity);
		}
		this.getSession().flush();
		transaction.commit();
	}

	/**
	 * hql批量更新数据
	 * 
	 * @param query
	 *            hql
	 * @param parameter
	 *            更新参数
	 */
	public void bulkUpdate(String query, List<Object[]> parameter) {
		Query hqlQuery;
		Object[] indexParameter;
		Session session = this.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		for (int index = 0; index < parameter.size(); index++) {
			hqlQuery = this.getSession().createQuery(query);

			indexParameter = parameter.get(index);

			for (int indexParam = 0; indexParam < indexParameter.length; indexParam++) {
				hqlQuery.setParameter(indexParam, indexParameter[indexParam]);
			}

			hqlQuery.executeUpdate();

			if (index % 100000 == 0) {
				session.flush();
				session.clear();
			}
		}
		session.flush();
		session.clear();

		transaction.commit();
		session.close();
	}

}
