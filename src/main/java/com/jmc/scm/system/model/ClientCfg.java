package com.jmc.scm.system.model;

import java.io.Serializable;
import javax.persistence.*;
import com.bstek.dorado.annotation.PropertyDef;

/**
 * client_cfg:客户端配置表
 */
@Entity
@Table(name = "client_cfg")
public class ClientCfg implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端号:客户端号
	 */
	@PropertyDef(label = "客户端号", description = "客户端号:客户端号")
	private String client;

	/**
	 * FTP登录名:FTP登录名
	 */
	@PropertyDef(label = "FTP登录名", description = "FTP登录名:FTP登录名")
	private String ftpUser;

	/**
	 * FTP登录密码:FTP登录密码
	 */
	@PropertyDef(label = "FTP登录密码", description = "FTP登录密码:FTP登录密码")
	private String ftpPsw;

	public ClientCfg() {
		super();
	}

	public ClientCfg(String client, String ftpUser, String ftpPsw) {
		super();
		this.client = client;
		this.ftpUser = ftpUser;
		this.ftpPsw = ftpPsw;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Id
	@Column(name = "CLIENT", length = 4, nullable = false)
	public String getClient() {
		return client;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	@Column(name = "FTP_USER", length = 60)
	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpPsw(String ftpPsw) {
		this.ftpPsw = ftpPsw;
	}

	@Column(name = "FTP_PSW", length = 60)
	public String getFtpPsw() {
		return ftpPsw;
	}

	public String toString() {
		return "ClientCfg [client=" + client + ",ftpUser=" + ftpUser
				+ ",ftpPsw=" + ftpPsw + "]";
	}

}
