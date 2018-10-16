package com.jmc.scm.system.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import com.bstek.dorado.annotation.PropertyDef;

/**
 * sys_login_log:登陆日志表
 */
@Entity
@Table(name = "sys_login_log")
public class SysLoginLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 登陆人IP地址:登陆人IP地址
	 */
	@PropertyDef(label = "登陆人IP地址", description = "登陆人IP地址:登陆人IP地址")
	private String remoteAddr;

	/**
	 * 登陆人最后代理IP:登陆人最后代理IP
	 */
	@PropertyDef(label = "登陆人最后代理IP", description = "登陆人最后代理IP:登陆人最后代理IP")
	private String remoteHost;

	/**
	 * 登陆人使用端口:登陆人使用端口
	 */
	@PropertyDef(label = "登陆人使用端口", description = "登陆人使用端口:登陆人使用端口")
	private String remotePort;

	/**
	 * 登陆时间:登陆时间
	 */
	@PropertyDef(label = "登陆时间", description = "登陆时间:登陆时间")
	private Date loginDate;

	/**
	 * 登陆用户名:登陆用户名
	 */
	@PropertyDef(label = "登陆用户名", description = "登陆用户名:登陆用户名")
	private String loginUserName;

	public SysLoginLog() {
		super();
	}

	public SysLoginLog(String remoteAddr, String remoteHost,
			String remotePort, Date loginDate, String loginUserName) {
		super();
		this.remoteAddr = remoteAddr;
		this.remoteHost = remoteHost;
		this.remotePort = remotePort;
		this.loginDate = loginDate;
		this.loginUserName = loginUserName;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	/**
	 * 这个表没有主键，只是因为Hibernate需要增加一个
	 * 
	 * @return
	 */
	@Id
	@Column(name = "REMOTE_ADDR", length = 50, nullable = false)
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	@Column(name = "REMOTE_HOST", length = 50, nullable = false)
	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemotePort(String remotePort) {
		this.remotePort = remotePort;
	}

	@Column(name = "REMOTE_PORT", length = 50, nullable = false)
	public String getRemotePort() {
		return remotePort;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_DATE", nullable = false)
	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}

	@Column(name = "LOGIN_USER_NAME", length = 50, nullable = false)
	public String getLoginUserName() {
		return loginUserName;
	}

	public String toString() {
		return "AlloveLoginLog [remoteAddr=" + remoteAddr + ",remoteHost="
				+ remoteHost + ",remotePort=" + remotePort + ",loginDate="
				+ loginDate + ",loginUserName=" + loginUserName + "]";
	}

}
