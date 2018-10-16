package com.jmc.scm.baseData.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * client_bank_info:客户银行信息
 */
@Entity
@Table(name = "client_bank_info")
public class ClientBankInfo implements Serializable {

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
	 * 银行国家:银行国家
	 */
	@PropertyDef(label = "银行国家", description = "银行国家:银行国家")
	private String bankCntry;

	/**
	 * 开户银行:开户银行
	 */
	@PropertyDef(label = "开户银行", description = "开户银行:开户银行")
	private String bankName;

	/**
	 * 开户银行帐号:开户银行帐号
	 */
	@PropertyDef(label = "开户银行帐号 ", description = "开户银行帐号:开户银行帐号")
	private String bankAcct;

	/**
	 * 银行开户名:银行开户名
	 */
	@PropertyDef(label = "银行开户名", description = "银行开户名:银行开户名")
	private String bankAcctName;

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

	public ClientBankInfo() {
		super();
	}

	public ClientBankInfo(String pkId, String client, String cltCode,
			String bankCntry, String bankName, String bankAcct,
			String bankAcctName, String createdBy, Date createdD,
			String updatedBy, Date updatedD, String attr1, String attr2,
			String attr3, String attr4, String attr5, String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.cltCode = cltCode;
		this.bankCntry = bankCntry;
		this.bankName = bankName;
		this.bankAcct = bankAcct;
		this.bankAcctName = bankAcctName;
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

	@Column(name = "CLIENT", length = 8, unique = true, nullable = false)
	public String getClient() {
		return client;
	}

	public void setCltCode(String cltCode) {
		this.cltCode = cltCode;
	}

	@Column(name = "CLT_CODE", length = 20, unique = true, nullable = false)
	public String getCltCode() {
		return cltCode;
	}

	public void setBankCntry(String bankCntry) {
		this.bankCntry = bankCntry;
	}

	@Column(name = "BANK_CNTRY", length = 20, nullable = false)
	public String getBankCntry() {
		return bankCntry;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "BANK_NAME", length = 100, nullable = false)
	public String getBankName() {
		return bankName;
	}

	public void setBankAcct(String bankAcct) {
		this.bankAcct = bankAcct;
	}

	@Column(name = "BANK_ACCT", length = 40, unique = true, nullable = false)
	public String getBankAcct() {
		return bankAcct;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	@Column(name = "BANK_ACCT_NAME", length = 40, nullable = false)
	public String getBankAcctName() {
		return bankAcctName;
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
		return "ClientBankInfo [pkId=" + pkId + ",client=" + client
				+ ",cltCode=" + cltCode + ",bankCntry=" + bankCntry
				+ ",bankName=" + bankName + ",bankAcct=" + bankAcct
				+ ",bankAcctName=" + bankAcctName + ",createdBy=" + createdBy
				+ ",createdD=" + createdD + ",updatedBy=" + updatedBy
				+ ",updatedD=" + updatedD + ",attr1=" + attr1 + ",attr2="
				+ attr2 + ",attr3=" + attr3 + ",attr4=" + attr4 + ",attr5="
				+ attr5 + ",delFlg=" + delFlg + "]";
	}

}
