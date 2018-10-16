package com.jmc.scm.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.hibernate.transform.ResultTransformer;

import com.jmc.scm.util.ScmUtil;

/**
 * 自定义实体转换器，将数据库字段转换成驼峰字段，如PK_ID -> pkId，pk_id -> pkId
 * 
 * @author 三影塔
 * 
 */
public class CustomBeanResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = -1048783983586895758L;

	private Class<?> targetClass;

	public CustomBeanResultTransformer(Class<?> clazz) {
		this.targetClass = clazz;
	}

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Constructor<?> constructor;
		Object target = null, aliasData;
		Method[] methods;
		String alias, setterMethodName;

		try {
			constructor = targetClass.getDeclaredConstructor();
			if (constructor == null) {
				throw new RuntimeException("请为" + targetClass.getName()
						+ "提供一个无参的构造方法！");
			}

			target = targetClass.newInstance();

			methods = targetClass.getDeclaredMethods();
			for (int index = 0; index < aliases.length; index++) {
				alias = aliases[index];
				aliasData = tuple[index];

				setterMethodName = ScmUtil.captureSetterFieldName(alias);
				for (Method method : methods) {
					if (method.getName().equals(setterMethodName)) {
						method.invoke(target, aliasData);
						break;
					}
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return target;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return collection;
	}

}
