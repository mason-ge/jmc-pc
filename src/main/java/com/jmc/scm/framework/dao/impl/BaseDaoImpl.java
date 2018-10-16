package com.jmc.scm.framework.dao.impl;

import org.springframework.stereotype.Repository;

import com.jmc.scm.framework.dao.BaseDao;

/**
 * 基础的Dao实体类
 * 
 * @author 三影塔
 * 
 */
@Repository("baseDaoImpl")
public class BaseDaoImpl extends BaseDao {

	/**
	 * 实体ID，每一个Spring实体都需要定义一个，方便代理
	 */
	public static final String BEAN_ID = "baseDaoImpl";

}
