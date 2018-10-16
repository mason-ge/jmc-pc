package com.jmc.scm.system.service;

import java.util.List;
import java.util.Map;

import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.system.model.SysEnum;
import com.jmc.scm.system.model.SysEnumv;
import com.jmc.scm.system.vo.UserVo;

public interface EnumService {

	void findPageEnum(Page<SysEnum> page, Map<String, Object> params);

	List<SysEnumv> findEnumvByEnumCode(String enumCode);

	void updateEnums(SysEnum enum_, List<SysEnumv> enumvs);

	void deleteEnum(SysEnum enum_, List<SysEnumv> enumvs);

	void deleteEnumvs(List<SysEnumv> enumvs);

	List<String> findEnumvNameByEnumCodeAndEnumvCode(String enumCode,
			String... enumvCodes);

	/**
	 * 根据枚举项编码和枚举值编码查一条明细实体
	 * 
	 * @param enumCode
	 * @param enumvCode
	 * @return
	 */
	SysEnumv findEnumvByEnumCodeAndEnumvCode(String enumCode,
			String enumvCode);


	/**
	 * 查询所有用户信息
	 * 
	 * @return
	 */
	List<UserVo> findUserNameByCode();

	/**
	 * 查询用户名和中文名的匹配关系
	 * 
	 * @return Map 用户名和中文名的匹配关系
	 */
	Map<String, String> findUserNameMapping();

	List<SysEnumv> findPlejByEnumvCode(String enumvDesc);
}
