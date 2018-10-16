package com.jmc.scm.system.view;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Page;
import com.jmc.scm.system.model.SysEnum;
import com.jmc.scm.system.model.SysEnumv;
import com.jmc.scm.system.service.EnumService;
import com.jmc.scm.system.service.impl.EnumServiceImpl;
import com.jmc.scm.system.vo.UserVo;

/**
 * 枚举值PR
 * 
 * @author 三影塔
 * 
 */
@Component("enumPR")
public class EnumPR {

	@Autowired
	@Qualifier(EnumServiceImpl.BEAN_ID)
	private EnumService enumService;

	@DataProvider
	public void getPageEnum(Page<SysEnum> page, Map<String, Object> params) {
		enumService.findPageEnum(page, params);
	}

	@DataProvider
	public List<SysEnumv> getEnumvByEnumCode(String enumCode) {
		return enumService.findEnumvByEnumCode(enumCode);
	}

	@DataResolver
	public void updateEnums(SysEnum enum_, List<SysEnumv> enumvs) {
		enumService.updateEnums(enum_, enumvs);
	}

	@DataResolver
	public void deleteEnum(SysEnum enum_, List<SysEnumv> enumvs) {
		enumService.deleteEnum(enum_, enumvs);
	}

	@DataResolver
	public void deleteEnumvs(List<SysEnumv> enumvs) {
		enumService.deleteEnumvs(enumvs);
	}

	@DataProvider
	public List<String> getEnumvNameByEnumCodeAndEnumvCode(String enumCode,
			String[] enumvCodes) {
		return enumService.findEnumvNameByEnumCodeAndEnumvCode(enumCode,
				enumvCodes);
	}

	@Expose
	public SysEnumv getEnumvByEnumCodeAndEnumvCode(Map<String, Object> map) {
		String enumCode = String.valueOf(map.get("enumCode"));
		String enumvCode = String.valueOf(map.get("enumvCode"));
		return enumService.findEnumvByEnumCodeAndEnumvCode(enumCode, enumvCode);
	}

	@DataProvider
	public List<UserVo> getUserNameByCode() {
		return enumService.findUserNameByCode();
	}

	/**
	 * 查询品类二级枚举，根据品类一级
	 * 
	 * @param enumvDesc
	 * @return
	 */
	@DataProvider
	public List<SysEnumv> getPlejByEnumvCode(String enumvDesc) {
		return enumService.findPlejByEnumvCode(enumvDesc);
	}
}
