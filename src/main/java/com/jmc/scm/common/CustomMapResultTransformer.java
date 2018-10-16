package com.jmc.scm.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.ResultTransformer;

import com.jmc.scm.util.ScmUtil;

/**
 * 自定义Map转换器，将数据库字段转换成驼峰字段，如PK_ID -> pkId，pk_id -> pkId
 * 
 * @author 三影塔
 * 
 */
public class CustomMapResultTransformer implements ResultTransformer {

	private static final long serialVersionUID = -6545159569050931864L;

	@Override
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map<String, Object> data = new HashMap<>();
		String fiedName;

		for (int index = 0; index < aliases.length; index++) {
			fiedName = ScmUtil.captureFieldName(aliases[index]);
			data.put(fiedName, tuple[index]);
		}
		return data;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List transformList(List collection) {
		return collection;
	}

}
