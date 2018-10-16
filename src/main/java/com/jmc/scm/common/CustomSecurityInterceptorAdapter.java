package com.jmc.scm.common;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.stereotype.Component;

import com.bstek.bdf2.core.security.SecurityInterceptorAdapter;
import com.jmc.scm.framework.dao.BaseDao;
import com.jmc.scm.framework.dao.impl.BaseDaoImpl;

/**
 * 自定义登陆拦截
 * 
 * @author 三影塔
 * 
 */
@Component
public class CustomSecurityInterceptorAdapter extends
		SecurityInterceptorAdapter {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	@Qualifier(BaseDaoImpl.BEAN_ID)
	private BaseDao baseDao;

	/**
	 * 重写登陆成功方法，登陆成功后，将登陆历史写到日志表中
	 */
	@Override
	public void loginSuccess(HttpRequestResponseHolder holder) {
		super.beforeLogin(holder);
		HttpServletRequest request = holder.getRequest();
		final String sql = "insert into sys_login_log (remote_addr, remote_host, remote_port, "
				+ "login_date, login_user_name) values (?, ?, ?, ?, ?)";
		SQLQuery sqlQuery;

		try {
			sqlQuery = baseDao.getSession().createSQLQuery(sql);

			sqlQuery.setParameter(0, request.getRemoteAddr());
			sqlQuery.setParameter(1, request.getRemoteHost());
			sqlQuery.setParameter(2, request.getRemotePort());
			sqlQuery.setParameter(3, new Date());
			sqlQuery.setParameter(4, request.getParameter("username_"));

			sqlQuery.executeUpdate();
		} catch (Exception ex) {
			logger.info(
					"IP为" + request.getRemoteAddr() + "的用户"
							+ request.getParameter("username_") + "存储登陆信息失败！",
					ex);
		}
	}

}
