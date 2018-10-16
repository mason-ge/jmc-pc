package com.jmc.scm.baseData.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * supplier_base_info:供应商基本信息表
 */
@Entity
@Table(name = "supplier_base_info")
public class SupplierBaseInfo implements Serializable {

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
	 * 供应商编码:供应商编码
	 */
	@PropertyDef(label = "供应商编码", description = "供应商编码:供应商编码")
	private String supCode;

	/**
	 * 供应商名称:供应商名称
	 */
	@PropertyDef(label = "供应商名称", description = "供应商名称:供应商名称")
	private String supName;

	/**
	 * 供应商分类编码:供应商分类编码
	 */
	@PropertyDef(label = "供应商分类编码", description = "供应商分类编码:供应商分类编码")
	private String supGrp;

	/**
	 * 供应商简称:供应商简称
	 */
	@PropertyDef(label = "供应商简称", description = "供应商简称:供应商简称")
	private String shortName;

	/**
	 * 税号:税号
	 */
	@PropertyDef(label = "税号", description = "税号:税号")
	private String taxCode;

	/**
	 * 地址信息1:地址信息1
	 */
	@PropertyDef(label = "地址信息1", description = "地址信息1:地址信息1")
	private String adr1;

	/**
	 * 地址信息2:地址信息2
	 */
	@PropertyDef(label = "地址信息2", description = "地址信息2:地址信息2")
	private String adr2;

	/**
	 * 联系人1:联系人1
	 */
	@PropertyDef(label = "联系人1", description = "联系人1:联系人1")
	private String conName1;

	/**
	 * 联系人1电话:联系人1电话
	 */
	@PropertyDef(label = "联系人1电话", description = "联系人1电话:联系人1电话")
	private String conPhone1;

	/**
	 * 联系人1手机:联系人1手机
	 */
	@PropertyDef(label = "联系人1手机", description = "联系人1手机:联系人1手机")
	private String conMobile1;

	/**
	 * 联系人1邮件:联系人1邮件
	 */
	@PropertyDef(label = "联系人1邮件", description = "联系人1邮件:联系人1邮件")
	private String conEmail1;

	/**
	 * 联系人2:联系人2
	 */
	@PropertyDef(label = "联系人2", description = "联系人2:联系人2")
	private String conName2;

	/**
	 * 联系人2电话:联系人2电话
	 */
	@PropertyDef(label = "联系人2电话", description = "联系人2电话:联系人2电话")
	private String conPhone2;

	/**
	 * 联系人2手机:联系人2手机
	 */
	@PropertyDef(label = "联系人2手机", description = "联系人2手机:联系人2手机")
	private String conMobile2;

	/**
	 * 联系人2邮件:联系人2邮件
	 */
	@PropertyDef(label = "联系人2邮件", description = "联系人2邮件:联系人2邮件")
	private String conEmail2;

	/**
	 * 固定电话:固定电话
	 */
	@PropertyDef(label = "固定电话", description = "固定电话:固定电话")
	private String tel;

	/**
	 * 传真号:传真号
	 */
	@PropertyDef(label = "传真号", description = "传真号:传真号")
	private String fax;

	/**
	 * EMAIL:EMAIL
	 */
	@PropertyDef(label = "EMAIL", description = "EMAIL:EMAIL")
	private String email;

	/**
	 * 公司官网:公司官网
	 */
	@PropertyDef(label = "公司官网", description = "公司官网:公司官网")
	private String cmpyWeb;

	/**
	 * QQ:QQ
	 */
	@PropertyDef(label = "QQ", description = "QQ:QQ")
	private String qq;

	/**
	 * 微信:微信
	 */
	@PropertyDef(label = "微信", description = "微信:微信")
	private String wechat;

	/**
	 * 规模:规模
	 */
	@PropertyDef(label = "规模", description = "规模:规模")
	private String scale;

	/**
	 * 产能:产能
	 */
	@PropertyDef(label = "产能", description = "产能:产能")
	private String capacity;

	/**
	 * 擅长品类:擅长品类
	 */
	@PropertyDef(label = "擅长品类", description = "擅长品类:擅长品类")
	private String goodAtField1;

	/**
	 * 供应商等级:供应商等级
	 */
	@PropertyDef(label = "供应商等级", description = "供应商等级:供应商等级")
	private String supLvl;

	/**
	 * 备注:备注
	 */
	@PropertyDef(label = "备注", description = "备注:备注")
	private String remark;

	/**
	 * 付款方式:付款方式
	 */
	@PropertyDef(label = "付款方式", description = "付款方式:付款方式")
	private String payment;

	/**
	 * 状态:状态
	 */
	@PropertyDef(label = "状态", description = "状态:状态")
	private String status;

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

	public SupplierBaseInfo() {
		super();
	}

	public SupplierBaseInfo(String pkId, String client, String supCode,
			String supName, String supGrp, String shortName, String taxCode,
			String adr1, String adr2, String conName1, String conPhone1,
			String conMobile1, String conEmail1, String conName2,
			String conPhone2, String conMobile2, String conEmail2, String tel,
			String fax, String email, String cmpyWeb, String qq, String wechat,
			String scale, String capacity, String goodAtField1, String supLvl,
			String remark, String payment, String status, String createdBy,
			Date createdD, String updatedBy, Date updatedD, String attr1,
			String attr2, String attr3, String attr4, String attr5,
			String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.supCode = supCode;
		this.supName = supName;
		this.supGrp = supGrp;
		this.shortName = shortName;
		this.taxCode = taxCode;
		this.adr1 = adr1;
		this.adr2 = adr2;
		this.conName1 = conName1;
		this.conPhone1 = conPhone1;
		this.conMobile1 = conMobile1;
		this.conEmail1 = conEmail1;
		this.conName2 = conName2;
		this.conPhone2 = conPhone2;
		this.conMobile2 = conMobile2;
		this.conEmail2 = conEmail2;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.cmpyWeb = cmpyWeb;
		this.qq = qq;
		this.wechat = wechat;
		this.scale = scale;
		this.capacity = capacity;
		this.goodAtField1 = goodAtField1;
		this.supLvl = supLvl;
		this.remark = remark;
		this.payment = payment;
		this.status = status;
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

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	@Column(name = "SUP_CODE", length = 20, unique = true, nullable = false)
	public String getSupCode() {
		return supCode;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	@Column(name = "SUP_NAME", length = 100, nullable = false)
	public String getSupName() {
		return supName;
	}

	public void setSupGrp(String supGrp) {
		this.supGrp = supGrp;
	}

	@Column(name = "SUP_GRP", length = 8)
	public String getSupGrp() {
		return supGrp;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "SHORT_NAME", length = 20)
	public String getShortName() {
		return shortName;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	@Column(name = "TAX_CODE", length = 120)
	public String getTaxCode() {
		return taxCode;
	}

	public void setAdr1(String adr1) {
		this.adr1 = adr1;
	}

	@Column(name = "ADR1", length = 70)
	public String getAdr1() {
		return adr1;
	}

	public void setAdr2(String adr2) {
		this.adr2 = adr2;
	}

	@Column(name = "ADR2", length = 80)
	public String getAdr2() {
		return adr2;
	}

	public void setConName1(String conName1) {
		this.conName1 = conName1;
	}

	@Column(name = "CON_NAME1", length = 20)
	public String getConName1() {
		return conName1;
	}

	public void setConPhone1(String conPhone1) {
		this.conPhone1 = conPhone1;
	}

	@Column(name = "CON_PHONE1", length = 30)
	public String getConPhone1() {
		return conPhone1;
	}

	public void setConMobile1(String conMobile1) {
		this.conMobile1 = conMobile1;
	}

	@Column(name = "CON_MOBILE1", length = 30)
	public String getConMobile1() {
		return conMobile1;
	}

	public void setConEmail1(String conEmail1) {
		this.conEmail1 = conEmail1;
	}

	@Column(name = "CON_EMAIL1", length = 40)
	public String getConEmail1() {
		return conEmail1;
	}

	public void setConName2(String conName2) {
		this.conName2 = conName2;
	}

	@Column(name = "CON_NAME2", length = 40)
	public String getConName2() {
		return conName2;
	}

	public void setConPhone2(String conPhone2) {
		this.conPhone2 = conPhone2;
	}

	@Column(name = "CON_PHONE2", length = 30)
	public String getConPhone2() {
		return conPhone2;
	}

	public void setConMobile2(String conMobile2) {
		this.conMobile2 = conMobile2;
	}

	@Column(name = "CON_MOBILE2", length = 30)
	public String getConMobile2() {
		return conMobile2;
	}

	public void setConEmail2(String conEmail2) {
		this.conEmail2 = conEmail2;
	}

	@Column(name = "CON_EMAIL2", length = 40)
	public String getConEmail2() {
		return conEmail2;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "TEL", length = 30)
	public String getTel() {
		return tel;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "FAX", length = 60)
	public String getFax() {
		return fax;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EMAIL", length = 30)
	public String getEmail() {
		return email;
	}

	public void setCmpyWeb(String cmpyWeb) {
		this.cmpyWeb = cmpyWeb;
	}

	@Column(name = "CMPY_WEB", length = 80)
	public String getCmpyWeb() {
		return cmpyWeb;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "QQ", length = 40)
	public String getQq() {
		return qq;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	@Column(name = "WECHAT", length = 60)
	public String getWechat() {
		return wechat;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@Column(name = "SCALE", length = 4)
	public String getScale() {
		return scale;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	@Column(name = "CAPACITY", length = 4)
	public String getCapacity() {
		return capacity;
	}

	public void setGoodAtField1(String goodAtField1) {
		this.goodAtField1 = goodAtField1;
	}

	@Column(name = "GOOD_AT_FIELD1", length = 40)
	public String getGoodAtField1() {
		return goodAtField1;
	}

	public void setSupLvl(String supLvl) {
		this.supLvl = supLvl;
	}

	@Column(name = "SUP_LVL", length = 1)
	public String getSupLvl() {
		return supLvl;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", length = 100)
	public String getRemark() {
		return remark;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Column(name = "PAYMENT", length = 20)
	public String getPayment() {
		return payment;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return status;
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
		return "SupplierBaseInfo [pkId=" + pkId + ",client=" + client
				+ ",supCode=" + supCode + ",supName=" + supName + ",supGrp="
				+ supGrp + ",shortName=" + shortName + ",taxCode=" + taxCode
				+ ",adr1=" + adr1 + ",adr2=" + adr2 + ",conName1=" + conName1
				+ ",conPhone1=" + conPhone1 + ",conMobile1=" + conMobile1
				+ ",conEmail1=" + conEmail1 + ",conName2=" + conName2
				+ ",conPhone2=" + conPhone2 + ",conMobile2=" + conMobile2
				+ ",conEmail2=" + conEmail2 + ",tel=" + tel + ",fax=" + fax
				+ ",email=" + email + ",cmpyWeb=" + cmpyWeb + ",qq=" + qq
				+ ",wechat=" + wechat + ",scale=" + scale + ",capacity="
				+ capacity + ",goodAtField1=" + goodAtField1 + ",supLvl="
				+ supLvl + ",remark=" + remark + ",payment=" + payment
				+ ",status=" + status + ",createdBy=" + createdBy
				+ ",createdD=" + createdD + ",updatedBy=" + updatedBy
				+ ",updatedD=" + updatedD + ",attr1=" + attr1 + ",attr2="
				+ attr2 + ",attr3=" + attr3 + ",attr4=" + attr4 + ",attr5="
				+ attr5 + ",delFlg=" + delFlg + "]";
	}

}
