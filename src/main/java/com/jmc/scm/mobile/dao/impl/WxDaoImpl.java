package com.jmc.scm.mobile.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.mobile.dao.WxDao;

@Repository("wxDaoImpl")
public class WxDaoImpl extends BaseDao implements WxDao {

	public static final String BEAN_ID = "wxDaoImpl";

	@Override
	public List<Map<String, Object>> findProdlist(int pageSize, int pageNo,
			JSONObject reqBody) {
		StringBuffer sb = new StringBuffer();
		Map<String, Object> conMap = new HashMap<>(0);
		try{
			sb.append(" SELECT                                                                           ");
			sb.append(" TF.FILE_HTTP_PATH AS img,                                                        ");
			sb.append(" t.*                                                                              ");
			sb.append(" FROM                                                                             ");
			sb.append(" prod_base_info t                                                                 ");
			sb.append(" LEFT JOIN (                                                                      ");
			sb.append(" SELECT                                                                           ");
			sb.append(" f.ATTR1,                                                                         ");
			sb.append(" f.FILE_HTTP_PATH,                                                                ");
			sb.append(" max(f.CREATED_D)                                                                 ");
			sb.append(" FROM                                                                             ");
			sb.append(" sys_file f                                                                       ");
			sb.append(" WHERE                                                                            ");
			sb.append(" 1 = 1                                                                            ");
			sb.append(" GROUP BY                                                                         ");
			sb.append(" f.ATTR1,                                                                         ");
			sb.append(" FILE_HTTP_PATH                                                                   ");
			sb.append(" ) TF ON (t.PROD_CODE = TF.ATTR1)                                                 ");
			sb.append(" where 1=1																		 ");
			sb.append(" and t.del_flg = '0'																 ");
			sb.append("and t.client = '0001' 															 ");
			Page<Map<String, Object>> pg = new Page<>(pageSize, pageNo);
			return this.queryPageListBySql(pg, sb.toString(), conMap);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}
