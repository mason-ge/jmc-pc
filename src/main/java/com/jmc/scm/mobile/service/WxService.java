package com.jmc.scm.mobile.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface WxService {

	List<Map<String, Object>> findProdlist(int pageSize, int pageNo,
			JSONObject reqBody);

}
