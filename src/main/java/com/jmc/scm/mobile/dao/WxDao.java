package com.jmc.scm.mobile.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface WxDao {

	List<Map<String, Object>> findProdlist(int pageSize, int pageNo,
			JSONObject reqBody);


}
