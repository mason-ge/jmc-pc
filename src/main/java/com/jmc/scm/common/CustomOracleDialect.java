package com.jmc.scm.common;

import java.sql.Types;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StandardBasicTypes;

/**
 * 自定义Hibernate方言，解决因数据库版本问题导致数据查询报错
 * 
 * @author 三影塔
 * 
 */
public class CustomOracleDialect extends Oracle10gDialect {

	public CustomOracleDialect() {
		super();
		// 添加nvarchar匹配，解决Hibernate使用SQL时报No Dialect mapping for JDBC type:
		// -9的错误
		registerHibernateType(Types.NVARCHAR,
				StandardBasicTypes.STRING.getName());
	}

}
