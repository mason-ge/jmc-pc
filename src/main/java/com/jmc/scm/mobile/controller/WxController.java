package com.jmc.scm.mobile.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.jmc.scm.mobile.service.WxService;
import com.jmc.scm.mobile.service.impl.WxServiceImpl;

@RestController
@RequestMapping("/prod")
public class WxController {

	@Autowired
	@Qualifier(WxServiceImpl.BEAN_ID)
	private WxService wxService;

	/**
	 * 查询商品列表
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param reqBody
	 * @return
	 */
	@RequestMapping(value = "prodList")
	public Map<String, Object> getProdlist(@RequestParam int pageSize,
			@RequestParam int pageNo, @RequestBody JSONObject reqBody) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int status = 200;
		String msg = "success";
		List<Map<String, Object>> resList = new ArrayList<>();
		try {
			resList = wxService.findProdlist(pageSize, pageNo, reqBody);
			resultMap.put("dataList", resList);

		} catch (Exception e) {
			status = 300;
			msg = "内部错误";
		}
		resultMap.put("status", status);
		resultMap.put("msg", msg);
		return resultMap;
	}

}
