package com.jmc.scm.system.view;

import org.springframework.stereotype.Component;

import com.bstek.dorado.annotation.Expose;
import com.jmc.scm.util.ScmUtil;

@Component("userPR")
public class UserPR {

	@Expose
	public String getClient() {
		return ScmUtil.getClient();
	}
}
