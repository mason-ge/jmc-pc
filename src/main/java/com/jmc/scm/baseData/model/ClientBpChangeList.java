package com.jmc.scm.baseData.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * client_bp_change_list:客户积分变更明细表
 */
@Entity
@Table(name = "client_bp_change_list")
public class ClientBpChangeList implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键:主键
	 */
	@PropertyDef(label = "主键", description = "主键:主键")
	private String pkId;

	/**
	 * 客户端号:客户端号
	 */
	@PropertyDef(label = "客户端号", description = "客户端号:客户端号")
	private String client;

	/**
	 * 客户编码:客户编码
	 */
	@PropertyDef(label = "客户编码", description = "客户编码:客户编码")
	private String cltCode;

	/**
	 * 客户名称:客户名称
	 */
	@PropertyDef(label = "客户名称", description = "客户名称:客户名称")
	private String cltName;

	/**
	 * 变更积分:变更积分
	 */
	@PropertyDef(label = "变更积分", description = "变更积分:变更积分")
	private BigDecimal bp;

	/**
	 * 变更原因:变更原因
	 */
	@PropertyDef(label = "变更原因", description = "变更原因:变更原因")
	private String changeRsn;

	/**
	 * 销售出库单号:销售出库单号
	 */
	@PropertyDef(label = "销售出库单号", description = "销售出库单号:销售出库单号")
	private String saleCode;

	/**
	 * 注释:注释
	 */
	@PropertyDef(label = "注释", description = "注释:注释")
	private String remark;

	/**
	 * 创建人:创建人
	 */
	@PropertyDef(label = "创建人", description = "创建人:创建人")
	private String createdBy;

	/**
	 * 创建时间:创建时间
	 */
	@PropertyDef(label = "创建时间", description = "创建时间:创建时间")
	private Date createdD;

	/**
	 * 更新人:更新人
	 */
	@PropertyDef(label = "更新人", description = "更新人:更新人")
	private String updatedBy;

	/**
	 * 更新时间:更新时间
	 */
	@PropertyDef(label = "更新时间", description = "更新时间:更新时间")
	private Date updatedD;

	/**
	 * 扩展字段1:扩展字段1
	 */
	@PropertyDef(label = "扩展字段1", description = "扩展字段1:扩展字段1")
	private String attr1;

	/**
	 * 扩展字段2:扩展字段2
	 */
	@PropertyDef(label = "扩展字段2", description = "扩展字段2:扩展字段2")
	private String attr2;

	/**
	 * 扩展字段3:扩展字段3
	 */
	@PropertyDef(label = "扩展字段3", description = "扩展字段3:扩展字段3")
	private String attr3;

	/**
	 * 扩展字段4:扩展字段4
	 */
	@PropertyDef(label = "扩展字段4", description = "扩展字段4:扩展字段4")
	private String attr4;

	/**
	 * 扩展字段5:扩展字段5
	 */
	@PropertyDef(label = "扩展字段5", description = "扩展字段5:扩展字段5")
	private String attr5;

	public ClientBpChangeList() {
		super();
	}

	public ClientBpChangeList(String pkId, String client, String cltCode,
			String cltName, BigDecimal bp, String changeRsn, String saleCode,
			String remark, String createdBy, Date createdD, String updatedBy,
			Date updatedD, String attr1, String attr2, String attr3,
			String attr4, String attr5) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.cltCode = cltCode;
		this.cltName = cltName;
		this.bp = bp;
		this.changeRsn = changeRsn;
		this.saleCode = saleCode;
		this.remark = remark;
		this.createdBy = createdBy;
		this.createdD = createdD;
		this.updatedBy = updatedBy;
		this.updatedD = updatedD;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	@Id
	@Column(name = "PK_ID", length = 36, nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getPkId() {
		return pkId;
	}

	public void setClient(String client) {
		this.client = client;
	}

	@Column(name = "CLIENT", length = 8, nullable = false)
	public String getClient() {
		return client;
	}

	public void setCltCode(String cltCode) {
		this.cltCode = cltCode;
	}

	@Column(name = "CLT_CODE", length = 20)
	public String getCltCode() {
		return cltCode;
	}

	public void setCltName(String cltName) {
		this.cltName = cltName;
	}

	@Column(name = "CLT_NAME", length = 100)
	public String getCltName() {
		return cltName;
	}

	public void setBp(BigDecimal bp) {
		this.bp = bp;
	}

	@Column(name = "BP")
	public BigDecimal getBp() {
		return bp;
	}

	public void setChangeRsn(String changeRsn) {
		this.changeRsn = changeRsn;
	}

	@Column(name = "CHANGE_RSN", length = 20)
	public String getChangeRsn() {
		return changeRsn;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	@Column(name = "SALE_CODE", length = 30)
	public String getSaleCode() {
		return saleCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", length = 255)
	public String getRemark() {
		return remark;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_BY", length = 60, nullable = false)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedD(Date createdD) {
		this.createdD = createdD;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_D", nullable = false)
	public Date getCreatedD() {
		return createdD;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "UPDATED_BY", length = 60)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedD(Date updatedD) {
		this.updatedD = updatedD;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_D")
	public Date getUpdatedD() {
		return updatedD;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	@Column(name = "ATTR1", length = 255)
	public String getAttr1() {
		return attr1;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	@Column(name = "ATTR2", length = 255)
	public String getAttr2() {
		return attr2;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	@Column(name = "ATTR3", length = 255)
	public String getAttr3() {
		return attr3;
	}

	public void setAttr4(String attr4) {
		this.attr4 = attr4;
	}

	@Column(name = "ATTR4", length = 255)
	public String getAttr4() {
		return attr4;
	}

	public void setAttr5(String attr5) {
		this.attr5 = attr5;
	}

	@Column(name = "ATTR5", length = 255)
	public String getAttr5() {
		return attr5;
	}

	public String toString() {
		return "ClientBpChangeList [pkId=" + pkId + ",client=" + client
				+ ",cltCode=" + cltCode + ",cltName=" + cltName + ",bp=" + bp
				+ ",changeRsn=" + changeRsn + ",saleCode=" + saleCode
				+ ",remark=" + remark + ",createdBy=" + createdBy
				+ ",createdD=" + createdD + ",updatedBy=" + updatedBy
				+ ",updatedD=" + updatedD + ",attr1=" + attr1 + ",attr2="
				+ attr2 + ",attr3=" + attr3 + ",attr4=" + attr4 + ",attr5="
				+ attr5 + "]";
	}

}
