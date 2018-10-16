package com.jmc.scm.mobile.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.jmc.scm.mobile.dao.WxDao;
import com.jmc.scm.mobile.dao.impl.WxDaoImpl;
import com.jmc.scm.mobile.service.WxService;

@Service("wxServiceImpl")
public class WxServiceImpl implements WxService {

	@Autowired
	@Qualifier(WxDaoImpl.BEAN_ID)
	private WxDao wxServiceDao;

	public static final String BEAN_ID = "wxServiceImpl";

	@Override
	public List<Map<String, Object>> findProdlist(int pageSize, int pageNo,
			JSONObject reqBody) {
		return wxServiceDao.findProdlist(pageSize, pageNo, reqBody);
	}

}
