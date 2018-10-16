package com.jmc.scm.baseData.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import com.bstek.dorado.annotation.PropertyDef;

/**
 * client_bp_money_relat:积分&金额转换关系
 */
@Entity
@Table(name = "client_bp_money_relat")
public class ClientBpMoneyRelat implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 客户端号:客户端号
	 */
	@PropertyDef(label = "客户端号", description = "客户端号:客户端号")
	private String client;

	/**
	 * X积分:X积分
	 */
	@PropertyDef(label = "X积分", description = "X积分:X积分")
	private BigDecimal xbp;

	/**
	 * 金额:金额
	 */
	@PropertyDef(label = "金额", description = "金额:金额")
	private BigDecimal ymoney;

	public ClientBpMoneyRelat() {
		super();
	}

	public ClientBpMoneyRelat(String client, BigDecimal xbp, BigDecimal ymoney) {
		super();
		this.client = client;
		this.xbp = xbp;
		this.ymoney = ymoney;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Id
	@Column(name = "CLIENT", length = 8, nullable = false)
	public String getClient() {
		return client;
	}

	public void setXbp(BigDecimal xbp) {
		this.xbp = xbp;
	}

	@Column(name = "XBP")
	public BigDecimal getXbp() {
		return xbp;
	}

	public void setYmoney(BigDecimal ymoney) {
		this.ymoney = ymoney;
	}

	@Column(name = "YMONEY")
	public BigDecimal getYmoney() {
		return ymoney;
	}

	public String toString() {
		return "ClientBpMoneyRelat [client=" + client + ",xbp=" + xbp
				+ ",ymoney=" + ymoney + "]";
	}

}
