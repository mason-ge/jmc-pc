package com.jmc.scm.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.bstek.bdf2.core.business.IUser;
import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.bdf2.core.exception.NoneLoginException;
import com.bstek.dorado.data.entity.EntityUtils;

/**
 * SCM UTIL工具类
 * 
 */

public class ScmUtil {

	public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final SimpleDateFormat datetimeFormat2 = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * obtain the current login user
	 * 
	 * @return
	 */
	public static String getLoginUserName() {
		IUser user = ScmUtil.getLoginUser();
		if (user == null) {
			throw new NoneLoginException("操作超时或已退出，请重新登录。");
		}
		return ContextHolder.getLoginUserName();
	}

	public static IUser getLoginUser() {
		return ContextHolder.getLoginUser();
	}

	/**
	 * 字符串截取英文逗号，返回'01','02'类似的字符串，便于SQL查询
	 * 
	 * @param Str
	 *            (类似01,02这样的字符串)
	 * @return
	 */
	public static String convertStrComma2StrQuo(String Str) {
		String strCondition = "";
		String[] strArray = Str.split(",");
		for (int i = 0; i < strArray.length; i++) {
			strCondition += "'" + strArray[i] + "',";
		}
		strCondition = strCondition.substring(0, strCondition.length() - 1);
		System.out.println(strCondition);
		return strCondition;
	}

	/**
	 * 将null或null字符串转化为空字符串,并trim
	 * 
	 * @param s
	 * @return
	 */
	public static String convertNull2String(String str) {
		String res = null;
		if (str == null || "null".equals(StringUtils.trim(str))) {
			res = "";
		} else {
			res = StringUtils.trim(str);
		}
		return res;
	}

	/**
	 * 对象类型转换为字符串
	 * 
	 * @param obj
	 *            参数
	 * @return String 字符串
	 */
	public static String object2String(Object obj) {
		return convertNull2String(String.valueOf(obj));
	}

	/**
	 * 首字母小写
	 * 
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] = Character.toLowerCase(cs[0]);
		return String.valueOf(cs);
	}

	public static String captureNameU(String name) {
		char[] cs = name.toCharArray();
		cs[0] = Character.toUpperCase(cs[0]);
		return String.valueOf(cs);
	}

	@SuppressWarnings("rawtypes")
	public static String objMethod(Object obj, String method, Object... params)
			throws Exception {
		if (obj == null)
			return null;
		Class[] cla = new Class[params.length];
		int i = 0;
		for (Object para : params) {
			if (para == null)
				continue;
			cla[i] = para.getClass();
			i++;
		}

		Object v = obj.getClass().getMethod(method, cla).invoke(obj, params);
		if (v == null)
			return null;
		return v.toString();

	}

	/**
	 * 获取较早时间
	 * 
	 * @return Date
	 */
	public static Date getEarlyDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 1970);
		return c.getTime();
	}

	/**
	 * 获取较晚时间
	 * 
	 * @return Date
	 */
	public static Date getLateDate() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2099);
		return c.getTime();
	}

	public static void copyValue(Object src, Object dest) {
		Class<?> srccla = src.getClass(); // ScmWfSupplier
		Class<?> destcla = dest.getClass(); // ScmSupplier

		try {
			Field[] fs = destcla.getDeclaredFields();
			for (Field f : fs) {
				if (f != null && f.getName().equals("serialVersionUID")
						|| f.getName().equals("pkId"))
					continue;
				if (f.getName().equals("createBy")
						|| f.getName().equals("createDate"))
					continue;
				try {
					f.setAccessible(true);
					Method m = srccla.getMethod("get"
							+ captureNameU(f.getName()));
					if (m != null)
						f.set(dest, m.invoke(src));
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static <T> T toEntity(T obj, Map<String, Object> m) throws Exception {
		if (m == null || m.size() == 0)
			return obj;
		List<String> labels = new LinkedList<String>();
		List<Object> datas = new LinkedList<Object>();

		for (Entry<String, Object> entry : m.entrySet()) {
			labels.add(entry.getKey());
			datas.add(entry.getValue());
		}

		obj = toEntity(obj, datas.toArray(), labels.toArray(new String[] {}));
		return obj;
	}

	public static <T> T toEntity(T entity, Object[] data, String... labels) {
		// 每个字段进行赋值
		Class<? extends Object> clas = entity.getClass();
		for (int j = 0; j < labels.length; j++) {
			if (null == data[j] || 0 == data[j].toString().trim().length()) {
				continue;
			}
			// 得到设值方法名称
			String methodName = "set"
					+ ((labels[j].charAt(0) + "").toUpperCase())
					+ labels[j].substring(1);
			try {
				Field field = clas.getDeclaredField(labels[j]);
				// 得到字段类型
				String parameterType = field.getType().getSimpleName()
						.toUpperCase();
				// 得到设值方法
				Method method = clas.getDeclaredMethod(methodName,
						field.getType());
				String val = data[j].toString();
				if (parameterType.equals("STRING")) {
					method.invoke(entity, val);
				} else if (parameterType.equals("CHARACTER")
						|| parameterType.equals("CHAR")) {
					method.invoke(entity, val.charAt(0));
				} else if (parameterType.toUpperCase().equals("BOOLEAN")) {
					method.invoke(entity,
							val.equals("true") || val.equals("1") ? true
									: false);
				} else if (parameterType.equals("SHORT")) {
					method.invoke(entity, Short.parseShort(val));
				} else if (parameterType.equals("INTEGER")
						|| parameterType.equals("INT")) {
					method.invoke(entity, Integer.parseInt(val));
				} else if (parameterType.equals("FLOAT")) {
					method.invoke(entity, Float.parseFloat(val));
				} else if (parameterType.equals("LONG")) {
					method.invoke(entity, Long.parseLong(val));
				} else if (parameterType.equals("DOUBLE")) {
					method.invoke(entity, Double.parseDouble(val));
				} else if (parameterType.toUpperCase().equals("BIGDECIMAL")) {
					method.invoke(entity, new BigDecimal(val));
				} else if (parameterType.toUpperCase().equals("DATE")) {
					method.invoke(entity, datetimeFormat.parse(val));
				} else {
					method.invoke(entity, data[j]);
				}
			} catch (Exception e) {
				continue;
			}
		}
		return entity;
	}

	public static String escapeQuote(String s) {
		s = s.replaceAll("'", "\"");
		s = s.replaceAll("\\\\", "/");
		return s;
	}

	public static String escapeQuote1(String s) {
		s = s.replaceAll("'", "\\\\\"");
		return s;
	}

	public static String escapeQuote2(String s) {
		s = s.replaceAll("\\\\", "/");
		if (s.indexOf("\"") > 0)
			s = s.replaceAll("\"", "\\\\\"");
		else if (s.indexOf("'") > 0)
			s = s.replaceAll("'", "\\\\\"");
		return s;
	}

	public static String escapeDoubleQuote(String s) {
		s = s.replaceAll("\"", "'");
		return s;
	}

	@SuppressWarnings("rawtypes")
	public static void delRecur(Object obj) throws Exception {
		Class cla = obj.getClass();
		String name = captureName(cla.getSimpleName());
		Field _f;
		Field[] fs = cla.getDeclaredFields();
		for (Field f : fs) {
			if (Collection.class.isAssignableFrom(f.getType())) {
				f.setAccessible(true);
				Collection coll = (Collection) f.get(obj);
				if (coll == null)
					continue;
				for (Object el : coll) {
					_f = el.getClass().getDeclaredField(name);
					if (_f != null) {
						_f.setAccessible(true);
						_f.set(el, null);
					}
				}
			}
		}
	}

	public static String modifyEntityName(String objName) {
		int slash = objName.indexOf("_");
		if (slash > -1) {
			objName = objName.substring(0, slash);
		}
		return objName;
	}

	@SuppressWarnings("rawtypes")
	public static void converObjectNullField(Object obj) throws Exception {
		Class cla = obj.getClass();
		Field[] fs = cla.getDeclaredFields();
		for (Field f : fs) {
			if (f.getType().getSimpleName().toUpperCase().equals("BIGDECIMAL")) {
				f.setAccessible(true);
				if (f.get(obj) == null) {
					f.set(obj, new BigDecimal(0));
				}
			} else if (f.getType().getSimpleName().toUpperCase()
					.equals("STRING")) {
				f.setAccessible(true);
				if (f.get(obj) == null) {
					f.set(obj, "");
				}
			}
		}
	}

	/**
	 * 日期类型转换为8位字符串,null转为当前日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDate2String(Date date) {
		String result = null;
		DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
		if (date != null) {
			result = format1.format(date);
		} else {
			result = format1.format(new Date());
		}

		return result;
	}

	/**
	 * 日期类型转换为8位字符串,null转为当前日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String convertDate2String(Date date, String format) {
		String result = null;
		DateFormat format1 = new SimpleDateFormat(format);
		if (date != null) {
			result = format1.format(date);
		}
		return result;
	}

	/**
	 * String转Date(yyyy-MM-dd)
	 * 
	 * @param s
	 * @return
	 */
	public static Date convertString2Date(String s) {
		Date d = null;
		if (!"".equals(convertNull2String(s))) {
			try {
				d = datetimeFormat2.parse(s);
			} catch (ParseException e) {
				throw new RuntimeException("日期转换错误");
			}
		}
		return d;
	}

	/**
	 * String转Date(yyyy-MM-dd HH:mm:ss)
	 * 
	 * @param s
	 * @return
	 */
	public static Date convertString2Date2(String s) {
		Date d = null;
		if (!"".equals(convertNull2String(s))) {
			try {
				d = datetimeFormat.parse(s);
			} catch (ParseException e) {
				throw new RuntimeException("日期转换错误");
			}
		}
		return d;
	}

	public static String getNow() {
		return convertDate2String(new Date(), "yyyyMMddHHmmss");
	}

	public static String getNowSSS() {
		return convertDate2String(new Date(), "yyyyMMddHHmmssSSS");
	}

	// 解决中文乱码问题
	public static String changeUTF8(String key) {
		String name = "";
		try {
			if (key != null) {
				name = new String(key.getBytes("ISO8859-1"), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 为对象创建虚拟属性
	 * 
	 * @param obj
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object setVirtualEntity(Object obj, String key, Object value) {
		Object obj1 = null;
		try {
			obj1 = EntityUtils.toEntity(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		EntityUtils.setValue(obj1, key, value);
		return obj1;
	}

	/**
	 * 为对象创建多个虚拟属性
	 * 
	 * @param obj
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object setVirtualEntitys(Object obj, Map<String, Object> map) {
		Object obj1 = null;
		try {
			obj1 = EntityUtils.toEntity(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		EntityUtils.setValues(obj1, map);
		return obj1;
	}

	/**
	 * 获取对象中的虚拟属性信息
	 * 
	 * @param entity
	 * @param property
	 * @return
	 */
	public static Object getVirtualEntity(Object entity, String property) {
		return EntityUtils.getValue(entity, property);
	}

	/**
	 * 去掉数组中重复的元素
	 * 
	 * @param coords
	 * @return
	 */
	public static String[] distinctArray(String[] arr) {
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}
		String newArr[] = new String[set.size()];
		int index = 0;
		for (String str : set) {
			newArr[index] = str;
			index++;
		}
		return newArr;
	}

	/**
	 * ORACLE 大写转骆驼命名
	 * 
	 * @param d
	 * @return
	 */
	public static List<Map<String, Object>> mapToMap(
			List<Map<String, Object>> mapList) {
		List<Map<String, Object>> resultlist = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : mapList) {
			Map<String, Object> result = new HashMap<String, Object>();
			for (Entry<String, Object> entry : map.entrySet()) {
				String[] str = entry.getKey().split("_");
				String key = "";
				int j = 0;
				for (int i = 0; i < str.length; i++) {
					if (j == 0) {
						key = str[i].toLowerCase();
						j++;
					} else {
						key += str[i].substring(0, 1)
								+ str[i].substring(1).toLowerCase();
					}
				}
				result.put(key, entry.getValue());
			}
			resultlist.add(result);
		}
		return resultlist;
	}

	/**
	 * 骆驼命名转ORACLE 大写
	 * 
	 * @param d
	 * @return
	 */
	public static Map<String, Object> mapToMap(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Entry<String, Object> entry : map.entrySet()) {
			String str = entry.getKey();
			String key = "";
			for (int i = 0; i < str.length(); i++) {
				String c = str.substring(i, i + 1);
				if (c.equals(c.toLowerCase())) {
					key += c;
				} else {
					key += '_' + c;
				}
			}
			result.put(key, entry.getValue());
		}
		return result;
	}

	/***
	 * map转换成对象
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToObject(Map<String, Object> map, Class<?> beanClass)
			throws Exception {
		if (map == null)
			return null;

		T obj = (T) beanClass.newInstance();
		Object val;
		Type type;
		ParameterizedType paramType;
		Class<?> genericClass;

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			Method setter = property.getWriteMethod();

			if (setter != null) {
				val = map.get(property.getName());
				if (val == null) {
					continue;
				}

				type = setter.getGenericParameterTypes()[0];

				if (val instanceof Collection<?>) {
					paramType = (ParameterizedType) type;
					genericClass = (Class<?>) paramType
							.getActualTypeArguments()[0];

					List<Object> data = mapListToObjectList(
							(List<Map<String, Object>>) val, genericClass);
					setter.invoke(obj, data);
				} else {
					genericClass = (Class<?>) type;
					if (val.getClass().equals(genericClass)) {
						setter.invoke(obj, val);
					} else if (genericClass.equals(String.class)) {
						setter.invoke(obj, val.toString());
					} else if (genericClass.equals(BigDecimal.class)) {
						setter.invoke(obj, new BigDecimal(val.toString()));
					} else if (genericClass.equals(Integer.class)) {
						setter.invoke(obj, new Integer(val.toString()));
					}
				}
			}
		}
		return obj;
	}

	/***
	 * mapList转换成对象List
	 */
	public static <T> List<T> mapListToObjectList(
			List<Map<String, Object>> mapList, Class<?> beanClass)
			throws Exception {
		if (mapList == null)
			return null;

		List<T> objList = new ArrayList<>();

		for (Map<String, Object> map : mapList) {
			T obj = mapToObject(map, beanClass);
			objList.add(obj);
		}

		return objList;
	}

	/***
	 * 对象 转换成 map
	 */
	public static Map<String, Object> objectToMap(Object obj) throws Exception {
		if (obj == null)
			return null;

		Map<String, Object> map = new HashMap<String, Object>();

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			if (key.compareToIgnoreCase("class") == 0) {
				continue;
			}
			Method getter = property.getReadMethod();
			Object value = getter != null ? getter.invoke(obj) : null;
			map.put(key, value);
		}

		return map;
	}

	/**
	 * map 排序共用方法
	 * 
	 * @param mapList
	 *            map列表
	 * @param sort
	 *            排序字段
	 * @param order升序
	 *            ：asc 降序 desc
	 * @return
	 */
	public static List<Map<String, Object>> sortToMap(
			List<Map<String, Object>> mapList, final String sort,
			final String order) {
		Collections.sort(mapList, new Comparator<Map<String, Object>>() {
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				if (o1.get(sort) instanceof String) {
					String map1value = object2String(o1.get(sort));
					String map2value = object2String(o2.get(sort));
					if ("DESC".equals(order.toUpperCase())) {
						return map1value.compareTo(map2value);
					} else {
						return map2value.compareTo(map1value);
					}
				} else {
					Double map1value = object2Double(o1.get(sort));
					Double map2value = object2Double(o2.get(sort));
					if ("DESC".equals(order.toUpperCase())) {
						return map1value - map2value > 0 ? 1 : 1;
					} else {
						return map1value - map2value > 0 ? -1 : 1;
					}
				}
			}
		});
		return mapList;
	}

	/**
	 * 将Object转换为Double
	 */
	public static Double object2Double(Object o) {
		if (o instanceof BigDecimal) {
			return ((BigDecimal) o).doubleValue();
		} else if (o instanceof String) {
			return Double.valueOf((String) o);
		} else if (o instanceof Integer) {
			return Double.valueOf((Integer) o);
		} else if (o instanceof Double) {
			return (Double) o;
		} else if (o instanceof Long) {
			return ((Long) o).doubleValue();
		} else {
			return 0.0;
		}
	}

	/**
	 * OBJ转BigDecimal
	 * 
	 * @param value
	 * @return
	 */
	public static BigDecimal getBigDecimal(Object value) {
		BigDecimal ret = null;
		if (value != null) {
			if (value instanceof BigDecimal) {
				ret = (BigDecimal) value;
			} else if (value instanceof String) {
				ret = new BigDecimal((String) value);
			} else if (value instanceof BigInteger) {
				ret = new BigDecimal((BigInteger) value);
			} else if (value instanceof Number) {
				ret = new BigDecimal(((Number) value).doubleValue());
			} else {
				throw new ClassCastException("Not possible to coerce [" + value
						+ "] from class " + value.getClass()
						+ " into a BigDecimal.");
			}
		}
		return ret;
	}

	// 生成随机数：当前年月日时分秒+五位随机数
	public static String getRandomFileName() {

		SimpleDateFormat simpleDateFormat;

		simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

		Date date = new Date();

		String str = simpleDateFormat.format(date);

		Random random = new Random();

		int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

		return rannum + str;// 当前时间
	}

	/**
	 * 转换字段的setter方法名，如pkId -> setPkId， pk_id -> setPkId
	 * 
	 * @param fieldName
	 *            字段名
	 * @return
	 */
	public static String captureSetterFieldName(String fieldName) {
		String fragment, lowerFieldName;
		StringBuffer stringBuffer;
		StringTokenizer stringTokenizer;
		char[] chars;
		boolean init = true;

		lowerFieldName = fieldName.toLowerCase();
		stringTokenizer = new StringTokenizer(lowerFieldName, "_");

		stringBuffer = new StringBuffer("set");

		while (stringTokenizer.hasMoreTokens()) {
			fragment = stringTokenizer.nextToken();

			chars = fragment.toCharArray();
			if (init) {
				if (chars.length > 1) {
					chars[0] = Character.toUpperCase(chars[0]);
					fragment = new String(chars);
				}
				init = false;
			} else {
				chars[0] = Character.toUpperCase(chars[0]);
				fragment = new String(chars);
			}
			stringBuffer.append(chars);
		}

		return stringBuffer.toString();
	}

	/**
	 * 转换字段名，如pkId -> pkId， pk_id -> pkId
	 * 
	 * @param fieldName
	 *            字段名
	 * @return
	 */
	public static String captureFieldName(String fieldName) {
		String fragment, lowerFieldName;
		StringBuffer stringBuffer;
		StringTokenizer stringTokenizer;
		char[] chars;
		boolean init = false;

		lowerFieldName = fieldName.toLowerCase();
		stringTokenizer = new StringTokenizer(lowerFieldName, "_");

		stringBuffer = new StringBuffer();

		while (stringTokenizer.hasMoreTokens()) {
			fragment = stringTokenizer.nextToken();

			if (init) {
				chars = fragment.toCharArray();
				chars[0] = Character.toUpperCase(chars[0]);
				fragment = new String(chars);
				stringBuffer.append(chars);
			} else {
				stringBuffer.append(fragment);
				init = true;
			}
		}

		return stringBuffer.toString();
	}

	/**
	 * 判断一个Map是否为空
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isEmptyMap(Map<?, ?> map) {
		boolean flag;

		if (map == null || map.isEmpty()) {
			flag = true;
		} else {
			flag = false;
		}

		return flag;
	}

	/**
	 * 判断一个Map是否不为空
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isNotEmptyMap(Map<?, ?> map) {
		return !isEmptyMap(map);
	}

	/** 判断字符串是否为空 */
	public static boolean isEmptyString(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/** 判断字符串是否不为空 */
	public static boolean isNotEmptyString(String str) {
		return !isEmptyString(str);
	}

	/** 判断List是否为空 */
	public static <T> boolean isEmptyList(List<T> list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	/** 判断List是否不为空 */
	public static <T> boolean isNotEmptyList(List<T> list) {
		return !isEmptyList(list);
	}

	public static BigDecimal BigDecimalFDP(double d) {
		String result = String.format("%.4f", d);
		BigDecimal bd = new BigDecimal(result);
		return bd;
	}

	/**
	 * 获取当前登录人客户端号，截取前四位
	 * 
	 * @return
	 */
	public static String getClient() {
		String logInUser = ContextHolder.getLoginUserName();
		if(!StringUtils.isEmpty(logInUser)){
			if (logInUser.length() > 4) {
				return logInUser.substring(0, 4);
			} else {
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * 判断数组中是否有重复值
	 * 
	 * @param objects
	 * @return
	 */
	public static boolean checkRepeat(Object[] objects) {
		Set<Object> set = new HashSet<Object>();
		for (Object str : objects) {
			set.add(str);
		}
		if (set.size() != objects.length) {
			return false;// 有重复
		} else {
			return true;// 不重复
		}
	}
}
