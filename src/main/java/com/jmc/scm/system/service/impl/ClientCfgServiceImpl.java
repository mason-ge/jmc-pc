package com.jmc.scm.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.model.ClientCfg;
import com.jmc.scm.system.service.ClientCfgService;
import com.jmc.scm.util.ScmUtil;

@Service("clientCfgServiceImpl")
public class ClientCfgServiceImpl implements ClientCfgService {

	public static final String BEAN_ID = "clientCfgServiceImpl";

	@Autowired
	@Qualifier(BaseDaoImpl.BEAN_ID)
	private BaseDao baseDao;

	@Override
	public ClientCfg getCfg() {
		ClientCfg result = null;
		Map<String, Object> conMap = new HashMap<>(1);
		try {
			String hql = "from ClientCfg t where 1=1 ";
			hql += "and t.client = :client ";
			conMap.put("client", ScmUtil.getClient());
			result = baseDao.queryEntity(hql, conMap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public ClientCfg getCfgByClient(String client) {
		ClientCfg result = null;
		Map<String, Object> conMap = new HashMap<>(1);
		try {
			String hql = "from ClientCfg t where 1=1 ";
			hql += "and t.client = :client ";
			conMap.put("client", client);
			result = baseDao.queryEntity(hql, conMap);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String> getAllClients() {
		List<String> result = new ArrayList<>();
		Map<String, Object> conMap = new HashMap<>(0);
		try {
			String hql = "from ClientCfg t where 1=1 ";
			List<ClientCfg> listCfg = baseDao.queryList(hql, conMap);
			if (listCfg != null && !listCfg.isEmpty()) {
				for (ClientCfg c : listCfg) {
					result.add(c.getClient());
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<String> getClientsWithNoRepeatFtp() {
		List<String> result = new ArrayList<>();
		Map<String, Object> conMap = new HashMap<>(0);
		Map<String, Object> mapCheck = new HashMap<>(0);
		String ftpUser = "";
		try {
			String hql = "from ClientCfg t where 1=1 ";
			List<ClientCfg> listCfg = baseDao.queryList(hql, conMap);
			if (listCfg != null && !listCfg.isEmpty()) {
				for (ClientCfg c : listCfg) {
					ftpUser = c.getFtpUser();
					if(!mapCheck.containsKey(ftpUser)){
						result.add(c.getClient());
						mapCheck.put(ftpUser, null);
					}
					
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
