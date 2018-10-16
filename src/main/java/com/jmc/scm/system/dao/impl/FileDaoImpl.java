package com.jmc.scm.system.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.bstek.dorado.core.Configure;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.dao.FileDao;
import com.jmc.scm.system.model.SysFile;
import com.jmc.scm.system.service.FTPService;
import com.jmc.scm.system.service.impl.FTPServiceImpl;
import com.jmc.scm.util.ScmUtil;

@Repository("fileDaoImpl")
public class FileDaoImpl extends BaseDaoImpl implements FileDao {

	@Autowired
	@Qualifier(FTPServiceImpl.BEAN_ID)
	private FTPService ftpService;

	private static String fileOpenPath = Configure
			.getString("jmc.scm.ftp.fileOpen.httpPath");

	@Override
	public Map<String, Object> saveAtt(Map<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		try {
			String abPath = String.valueOf(params.get("abPath"));
			String sName = String.valueOf(params.get("sName"));
			String bizCode = String.valueOf(params.get("bizCode"));
			// 根据bizCode和绝对路径查看是否有相同的文件，相同的则覆盖
			SysFile af = this.getFileByCodeName(bizCode, abPath);
			if (af != null) {
				af.setUpdatedBy(ScmUtil.getLoginUserName());
				af.setUpdatedD(new Date());
				result.put("httpPath", af.getFileHttpPath());
				result.put("pkId", af.getPkId());
				this.update(af);
			} else {
				SysFile f = new SysFile();
				f.setBizCode(bizCode);
				f.setFileName(sName);
				f.setFileAbsolutePath(abPath);
				f.setCreatedBy(ScmUtil.getLoginUserName());
				f.setCreatedD(new Date());
				this.save(f);
				f.setFileHttpPath(fileOpenPath + f.getPkId() + "/0");
				this.update(f);
				result.put("httpPath", f.getFileHttpPath());
				result.put("pkId", f.getPkId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<SysFile> queryFile(String bizCode) {
		List<SysFile> list = null;
		try {
			Map<String, Object> queryParam = new HashMap<>();
			String hql = "from SysFile t where 1=1 ";
			Object buffer;

			if (!"".equals(bizCode) && bizCode != null) {
				buffer = bizCode;
				if (!StringUtils.isEmpty(buffer)) {
					hql += "and t.bizCode = :bizCode";
					queryParam.put("bizCode", buffer);
				}
			}
			list = this.queryList(hql, queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public SysFile findFile(Map<String, Object> parameter) {
		StringBuffer hqlBuffer = new StringBuffer("from SysFile where 1=1 ");
		Map<String, Object> convertParam = new HashMap<>();
		Object buffer;
		SysFile file;

		if (ScmUtil.isNotEmptyMap(parameter)) {
			buffer = parameter.get("pkId");
			if (StringUtils.isEmpty(buffer) == false) {
				convertParam.put("pkId", buffer);
				hqlBuffer.append("and pkId = :pkId ");
			}

			buffer = parameter.get("bizCode");
			if (StringUtils.isEmpty(buffer) == false) {
				convertParam.put("bizCode", buffer);
				hqlBuffer.append("and bizCode = :bizCode ");
			}
		}

		file = this.queryEntity(hqlBuffer.toString(), convertParam);

		return file;
	}

	@Override
	public void deleteAtt(Map<String, Object> params) {
		try {
			String pkIds = String.valueOf(params.get("pkIds"));

			List<SysFile> f = getFileByPkIds(pkIds);
			if (f != null && !f.isEmpty()) {
				this.deleteAll(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Map<String, Object>> findListPathByUris(List<Object> uris) {
		String query = "select t.file_http_path, t.file_absolute_path from SYS_FILE t "
				+ "where t.file_http_path in (:uris)";
		Map<String, Object> parameter = new HashMap<>(1);
		List<Map<String, Object>> result;

		parameter.put("uris", uris);

		result = this.queryListBySql(query, parameter);
		return result;
	}

	private List<SysFile> getFileByPkIds(String pkIds) {
		List<SysFile> f = null;
		try {
			String hql = "from SysFile t where 1=1 ";
			Object buffer;
			Map<String, Object> queryParam = new HashMap<>();
			if (!"".equals(pkIds) && pkIds != null) {
				String[] strArray = null;
				strArray = pkIds.split(",");
				buffer = strArray;
				if (!StringUtils.isEmpty(buffer)) {
					hql += "and t.pkId in :pkIds ";
					queryParam.put("pkIds", buffer);
				}
			}
			f = this.queryList(hql, queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	private SysFile getFileByCodeName(String bizCode, String abPath) {
		SysFile f = null;
		try {
			String hql = "from SysFile t where 1=1 ";
			Object buffer;
			Map<String, Object> queryParam = new HashMap<>();
			if (!"".equals(bizCode) && bizCode != null) {
				buffer = bizCode;
				if (!StringUtils.isEmpty(buffer)) {
					hql += "and t.bizCode = :bizCode ";
					queryParam.put("bizCode", buffer);
				}
			}
			if (!"".equals(abPath) && abPath != null) {
				buffer = abPath;
				if (!StringUtils.isEmpty(buffer)) {
					hql += "and t.fileAbsolutePath = :fileAbsolutePath ";
					queryParam.put("fileAbsolutePath", buffer);
				}
			}
			f = this.queryEntity(hql, queryParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	@Override
	public List<SysFile> findListByListParam(List<Map<String, Object>> list) {
		Map<String, Object> conMap = new HashMap<>();
		Map<String, Object> mapEach = new HashMap<>();
		Object bizCode, abPath;
		try {
			String hql = "from SysFile t where 1=1 ";
			for (int i = 0; i < list.size(); i++) {
				mapEach = list.get(i);
				bizCode = mapEach.get("bizCode");
				abPath = mapEach.get("abPath");
				// 判断第一次循环
				if (i == 0) {
					hql += "  and ( ";
				} else if (i <= list.size() - 1) {
					// 中间
					hql += "  or ";
				}
				hql += "  (t.bizCode = '" + bizCode + "'				    ";
				hql += "	 and t.fileAbsolutePath = '" + abPath + "')     ";
				if (i == list.size() - 1) {
					// 最后一次循环
					hql += "  ) ";
				}
			}
			return this.queryList(hql, conMap);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
