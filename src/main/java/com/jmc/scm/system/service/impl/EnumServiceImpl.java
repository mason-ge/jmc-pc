package com.jmc.scm.system.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bstek.bdf2.core.context.ContextHolder;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.common.CommonEnum.DeletedFlag;
import com.jmc.scm.system.dao.EnumDao;
import com.jmc.scm.system.dao.impl.EnumDaoImpl;
import com.jmc.scm.system.model.SysEnum;
import com.jmc.scm.system.model.SysEnumv;
import com.jmc.scm.system.service.EnumService;
import com.jmc.scm.system.vo.UserVo;

@Service("enumServiceImpl")
public class EnumServiceImpl implements EnumService {

	public static final String BEAN_ID = "enumServiceImpl";

	@Autowired
	@Qualifier(EnumDaoImpl.BEAN_ID)
	private EnumDao enumDao;

	@Override
	public void findPageEnum(Page<SysEnum> page, Map<String, Object> params) {
		String hql = "from SysEnum where 1=1 ";
		Map<String, Object> convertParam = null;
		Object buffer = null;

		if (params == null || params.isEmpty()) {
			convertParam = Collections.emptyMap();
		} else {
			convertParam = new HashMap<String, Object>();

			buffer = params.get("model");
			if (buffer != null && StringUtils.isNotBlank(buffer.toString())) {
				convertParam.put("model", buffer);
				hql = hql + " and model = :model ";
			}

			buffer = params.get("enumName");
			if (buffer != null && StringUtils.isNotBlank(buffer.toString())) {
				convertParam.put("enumName", "%" + buffer + "%");
				hql = hql + " and enumName like :enumName ";
			}

			buffer = params.get("enumCode");
			if (buffer != null && StringUtils.isNotBlank(buffer.toString())) {
				convertParam.put("enumCode", "%" + buffer + "%");
				hql = hql + " and enumCode like :enumCode ";
			}
		}

		enumDao.queryPageEnum(page, hql, convertParam);
	}

	@Override
	public List<SysEnumv> findEnumvByEnumCode(String enumCode) {
		String hql = "from SysEnumv where enumId = (select enumId from SysEnum where enumCode = ? and deletedFlag = '0')"
				+ " and deletedFlag = '0'" + " order by orderNo asc";
		List<SysEnumv> result = null;
		result = enumDao.queryEnumvByEnumCode(hql, enumCode);
		return result;
	}

	@Override
	public void updateEnums(SysEnum enum_, List<SysEnumv> enumvs) {
		String userName = ContextHolder.getLoginUserName();
		Date dateNow = new Date();

		if (enum_.getEnumId() == null) {
			enum_.setDeletedFlag(Integer.parseInt(DeletedFlag.NO.value()));
			enum_.setCreatedBy(userName);
			enum_.setEnumType("MAP");
			enum_.setCreatedDate(dateNow);
		}
		enum_.setUpdatedBy(userName);
		enum_.setUpdatedDate(dateNow);
		enum_.setVersion(enum_.getVersion() + 1);

		for (SysEnumv enumv : enumvs) {
			if (enumv.getEnumvId() == null) {
				enumv.setDeletedFlag(Integer.parseInt(DeletedFlag.NO.value()));
				enumv.setCreatedBy(userName);
				enumv.setCreatedDate(dateNow);
			}
			enumv.setUpdatedBy(userName);
			enumv.setUpdatedDate(dateNow);
		}
		enumDao.updateEnums(enum_, enumvs);
	}

	@Override
	public void deleteEnum(SysEnum enum_, List<SysEnumv> enumvs) {
		enumDao.deleteEnum(enum_, enumvs);
	}

	@Override
	public void deleteEnumvs(List<SysEnumv> enumvs) {
		enumDao.deleteEnumvs(enumvs);
	}

	@Override
	public List<String> findEnumvNameByEnumCodeAndEnumvCode(String enumCode,
			String... enumvCodes) {
		String hql = "select enumvName from SysEnumv where enumId = (select enumId from "
				+ "SysEnum where enumCode = ?) and enumvCode in (?)";
		List<String> result = null;
		result = enumDao.queryEnumvNameByEnumCodeAndEnumvCode(hql, enumCode,
				enumvCodes);
		return result;
	}

	@Override
	public SysEnumv findEnumvByEnumCodeAndEnumvCode(String enumCode,
			String enumvCode) {
		SysEnumv result = null;
		Map<String, Object> convertParm = new HashMap<>();
		String hql = "from SysEnumv where enumId = (select enumId from "
				+ "SysEnum where enumCode = :enumCode and deletedFlag = '0') and enumvCode = :enumvCode and deletedFlag = '0'";
		convertParm.put("enumCode", enumCode);
		convertParm.put("enumvCode", enumvCode);
		result = enumDao.queryEnumvByEnumCodeAndEnumvCode(hql, convertParm);
		return result;
	}

	@Override
	public List<UserVo> findUserNameByCode() {
		return enumDao.queryUserNameByCode();
	}

	@Override
	public Map<String, String> findUserNameMapping() {
		List<UserVo> users;
		Map<String, String> result;

		users = this.findUserNameByCode();
		result = new HashMap<>(users.size());

		for (UserVo user : users) {
			result.put(user.getUserCode(), user.getUserName());
		}
		return result;
	}

	@Override
	public List<SysEnumv> findPlejByEnumvCode(String enumvDesc) {
		return enumDao.findPlejByEnumvCode(enumvDesc);
	}
}
