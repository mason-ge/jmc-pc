package com.jmc.scm.sale.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * pay_main:收付款表
 */
@Entity
@Table(name = "pay_main")
public class PayMain implements Serializable {

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
	 * 单号:单号
	 */
	@PropertyDef(label = "单号", description = "单号:单号")
	private String prestoCode;

	/**
	 * 日期:日期
	 */
	@PropertyDef(label = "日期", description = "日期:日期")
	private Date payDate;

	/**
	 * 金额:金额
	 */
	@PropertyDef(label = "金额", description = "金额:金额")
	private BigDecimal payAmount;

	/**
	 * 收付款标记:收付款标记
	 */
	@PropertyDef(label = "收付款标记", description = "收付款标记:收付款标记")
	private String payFlg;

	/**
	 * 备注:备注
	 */
	@PropertyDef(label = "备注", description = "备注:备注")
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

	/**
	 * 删除标记:删除标记
	 */
	@PropertyDef(label = "删除标记", description = "删除标记:删除标记")
	private String delFlg;

	public PayMain() {
		super();
	}

	public PayMain(String pkId, String client, String prestoCode, Date payDate,
			BigDecimal payAmount, String payFlg, String remark,
			String createdBy, Date createdD, String updatedBy, Date updatedD,
			String attr1, String attr2, String attr3, String attr4,
			String attr5, String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.prestoCode = prestoCode;
		this.payDate = payDate;
		this.payAmount = payAmount;
		this.payFlg = payFlg;
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
		this.delFlg = delFlg;
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

	@Column(name = "CLIENT", length = 4, nullable = false)
	public String getClient() {
		return client;
	}

	public void setPrestoCode(String prestoCode) {
		this.prestoCode = prestoCode;
	}

	@Column(name = "PRESTO_CODE", length = 20)
	public String getPrestoCode() {
		return prestoCode;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PAY_DATE")
	public Date getPayDate() {
		return payDate;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "PAY_AMOUNT")
	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayFlg(String payFlg) {
		this.payFlg = payFlg;
	}

	@Column(name = "PAY_FLG", length = 1)
	public String getPayFlg() {
		return payFlg;
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

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	@Column(name = "DEL_FLG", length = 1, nullable = false)
	public String getDelFlg() {
		return delFlg;
	}

	public String toString() {
		return "PayMain [pkId=" + pkId + ",client=" + client + ",prestoCode="
				+ prestoCode + ",payDate=" + payDate + ",payAmount="
				+ payAmount + ",payFlg=" + payFlg + ",remark=" + remark
				+ ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ "]";
	}

}
