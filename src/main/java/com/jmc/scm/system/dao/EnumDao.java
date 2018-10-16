package com.jmc.scm.system.dao;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.system.model.SysEnum;
import com.jmc.scm.system.model.SysEnumv;
import com.jmc.scm.system.vo.UserVo;

/**
 * 枚举值Dao定义
 * 
 * @author 三影塔
 * 
 */
public interface EnumDao {

	void queryPageEnum(Page<SysEnum> page, String hql,
			Map<String, Object> params);

	List<SysEnumv> queryEnumvByEnumCode(String hql, String enumCode);

	void updateEnums(SysEnum enum_, List<SysEnumv> enumvs);

	void deleteEnum(SysEnum enum_, List<SysEnumv> enumvs);

	void deleteEnumvs(List<SysEnumv> enumvs);

	/**
	 * 通过编码删除枚举项与枚举值
	 * 
	 * @param CaoYu
	 */
	void deleteEntity(String enumCode);

	List<String> queryEnumvNameByEnumCodeAndEnumvCode(String hql,
			String enumCode, String... enumvCodes);

	SysEnumv queryEnumvByEnumCodeAndEnumvCode(String hql,
			Map<String, Object> map);

	/**
	 * 查询所有用户信息
	 * 
	 * @return
	 */
	List<UserVo> queryUserNameByCode();

	List<SysEnumv> findPlejByEnumvCode(String enumvDesc);

}
