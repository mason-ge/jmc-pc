package com.jmc.scm.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;
import com.jmc.scm.system.dao.EnumDao;
import com.jmc.scm.system.model.SysEnum;
import com.jmc.scm.system.model.SysEnumv;
import com.jmc.scm.system.vo.UserVo;

@Repository("enumDaoImpl")
public class EnumDaoImpl extends BaseDaoImpl implements EnumDao {

	public static final String BEAN_ID = "enumDaoImpl";

	@Override
	public void queryPageEnum(Page<SysEnum> page, String hql,
			Map<String, Object> params) {
		String chql = "select count(*) " + hql;
		try {
			this.pagingQuery(page, hql, chql, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SysEnumv> queryEnumvByEnumCode(String hql, String enumCode) {
		Query query = null;
		List<SysEnumv> result = null;

		query = this.getSession().createQuery(hql);
		query.setString(0, enumCode);

		result = query.list();

		return result;
	}

	@Override
	public void updateEnums(SysEnum enum_, List<SysEnumv> enumvs) {
		Session session = this.getSession();
		if (enum_.getEnumId() == null) {
			session.save(enum_);
		} else {
			session.update(enum_);
		}

		for (SysEnumv enumv : enumvs) {
			if (enumv.getEnumvId() == null) {
				enumv.setEnumId(enum_.getEnumId());
				session.save(enumv);
			} else {
				session.update(enumv);
			}
		}

	}

	@Override
	public void deleteEnum(SysEnum enum_, List<SysEnumv> enumvs) {
		deleteEnumvs(enumvs);
		this.getSession().delete(enum_);
	}

	@Override
	public void deleteEnumvs(List<SysEnumv> enumvs) {
		for (SysEnumv enumv : enumvs) {
			this.getSession().delete(enumv);
		}
	}

	@Override
	public void deleteEntity(String enumCode) {
		final String deleteEnumHql = "delete AlloveEnum where enumCode = ?";
		final String deleteEnumvHql = "delete AlloveEnumv where attribute1 = ?";
		Object[] indexParameter = new Object[] { enumCode };
		this.update(deleteEnumvHql, indexParameter);
		this.update(deleteEnumHql, indexParameter);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryEnumvNameByEnumCodeAndEnumvCode(String hql,
			String enumCode, String... enumvCodes) {
		Query query = this.getSession().createQuery(hql);

		query.setString(0, enumCode);
		query.setParameter(1, enumvCodes);

		return query.list();
	}

	@Override
	public SysEnumv queryEnumvByEnumCodeAndEnumvCode(String hql,
			Map<String, Object> map) {
		return this.queryEntity(hql, map);
	}

	@Override
	public List<UserVo> queryUserNameByCode() {
		String[] myList = new String[0];
		String sql = "select t.username_ as user_code, t.cname_ as user_name ";
		sql += "from BDF2_USER t where 1=1 ";
		return this.queryListBySql(sql, myList, UserVo.class);
	}

	@Override
	public List<SysEnumv> findPlejByEnumvCode(String enumvDesc) {
		Map<String, Object> conMap = new HashMap<>(1);
		String hql = "from SysEnumv t where 1=1 ";
		hql += "and t.deletedFlag = '0' ";
		hql += "and t.enumId = (select enumId from ";
		hql += "SysEnum where enumCode = 'PLEJ' and deletedFlag = '0') ";
		hql += "and t.enumvDesc = :enumvDesc";
		conMap.put("enumvDesc", enumvDesc);
		return this.queryList(hql, conMap);
	}

}
