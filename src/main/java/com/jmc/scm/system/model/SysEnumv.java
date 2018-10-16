package com.jmc.scm.system.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * sys_enumv:枚举值表
 */
@Entity
@Table(name = "sys_enumv")
public class SysEnumv implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 枚举值主键:枚举值主键
	 */
	@PropertyDef(label = "枚举值主键", description = "枚举值主键:枚举值主键")
	private String enumvId;

	/**
	 * 枚举主键:枚举主键
	 */
	@PropertyDef(label = "枚举主键", description = "枚举主键:枚举主键")
	private String enumId;

	/**
	 * 枚举值编码:枚举值编码
	 */
	@PropertyDef(label = "枚举值编码", description = "枚举值编码:枚举值编码")
	private String enumvCode;

	/**
	 * 枚举值名称:枚举值名称
	 */
	@PropertyDef(label = "枚举值名称", description = "枚举值名称:枚举值名称")
	private String enumvName;

	/**
	 * 枚举值描述:枚举值描述
	 */
	@PropertyDef(label = "枚举值描述", description = "枚举值描述:枚举值描述")
	private String enumvDesc;

	/**
	 * 顺序号:顺序号
	 */
	@PropertyDef(label = "顺序号", description = "顺序号:顺序号")
	private int orderNo;

	/**
	 * 父枚举主键:父枚举主键
	 */
	@PropertyDef(label = "父枚举主键", description = "父枚举主键:父枚举主键")
	private String parentEnumvId;

	/**
	 * 版本:版本
	 */
	@PropertyDef(label = "版本", description = "版本:版本")
	private int version;

	/**
	 * 扩展字段1:扩展字段1
	 */
	@PropertyDef(label = "扩展字段1", description = "扩展字段1:扩展字段1")
	private String attribute1;

	/**
	 * 扩展字段2:扩展字段2
	 */
	@PropertyDef(label = "扩展字段2", description = "扩展字段2:扩展字段2")
	private String attribute2;

	/**
	 * 扩展字段3:扩展字段3
	 */
	@PropertyDef(label = "扩展字段3", description = "扩展字段3:扩展字段3")
	private String attribute3;

	/**
	 * 扩展字段4:扩展字段4
	 */
	@PropertyDef(label = "扩展字段4", description = "扩展字段4:扩展字段4")
	private String attribute4;

	/**
	 * 扩展字段5:扩展字段5
	 */
	@PropertyDef(label = "扩展字段5", description = "扩展字段5:扩展字段5")
	private String attribute5;

	/**
	 * 删除标记:删除标记
	 */
	@PropertyDef(label = "删除标记", description = "删除标记:删除标记")
	private int deletedFlag;

	/**
	 * 创建人:创建人
	 */
	@PropertyDef(label = "创建人", description = "创建人:创建人")
	private String createdBy;

	/**
	 * 创建时间:创建时间
	 */
	@PropertyDef(label = "创建时间", description = "创建时间:创建时间")
	private Date createdDate;

	/**
	 * 更新人:更新人
	 */
	@PropertyDef(label = "更新人", description = "更新人:更新人")
	private String updatedBy;

	/**
	 * 更新时间:更新时间
	 */
	@PropertyDef(label = "更新时间", description = "更新时间:更新时间")
	private Date updatedDate;

	public SysEnumv() {
		super();
	}

	public SysEnumv(String enumvId, String enumId, String enumvCode,
			String enumvName, String enumvDesc, int orderNo,
			String parentEnumvId, int version, String attribute1,
			String attribute2, String attribute3, String attribute4,
			String attribute5, int deletedFlag, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate) {
		super();
		this.enumvId = enumvId;
		this.enumId = enumId;
		this.enumvCode = enumvCode;
		this.enumvName = enumvName;
		this.enumvDesc = enumvDesc;
		this.orderNo = orderNo;
		this.parentEnumvId = parentEnumvId;
		this.version = version;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
		this.deletedFlag = deletedFlag;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	public void setEnumvId(String enumvId) {
		this.enumvId = enumvId;
	}

	@Id
	@Column(name = "enumv_id", length = 36, nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getEnumvId() {
		return enumvId;
	}

	public void setEnumId(String enumId) {
		this.enumId = enumId;
	}

	@Column(name = "enum_id_", length = 36, unique = true)
	public String getEnumId() {
		return enumId;
	}

	public void setEnumvCode(String enumvCode) {
		this.enumvCode = enumvCode;
	}

	@Column(name = "enumv_code", length = 100, unique = true, nullable = false)
	public String getEnumvCode() {
		return enumvCode;
	}

	public void setEnumvName(String enumvName) {
		this.enumvName = enumvName;
	}

	@Column(name = "enumv_name", length = 100, nullable = false)
	public String getEnumvName() {
		return enumvName;
	}

	public void setEnumvDesc(String enumvDesc) {
		this.enumvDesc = enumvDesc;
	}

	@Column(name = "enumv_desc", length = 240)
	public String getEnumvDesc() {
		return enumvDesc;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "order_no")
	public int getOrderNo() {
		return orderNo;
	}

	public void setParentEnumvId(String parentEnumvId) {
		this.parentEnumvId = parentEnumvId;
	}

	@Column(name = "parent_enumv_id", length = 36)
	public String getParentEnumvId() {
		return parentEnumvId;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "version")
	public int getVersion() {
		return version;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	@Column(name = "attribute1", length = 240)
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	@Column(name = "attribute2", length = 240)
	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	@Column(name = "attribute3", length = 240)
	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	@Column(name = "attribute4", length = 240)
	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	@Column(name = "attribute5", length = 240)
	public String getAttribute5() {
		return attribute5;
	}

	public void setDeletedFlag(int deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	@Column(name = "deleted_flag")
	public int getDeletedFlag() {
		return deletedFlag;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "created_by", length = 60)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Column(name = "updated_by", length = 60)
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public String toString() {
		return "AlloveEnumv [enumvId=" + enumvId + ",enumId=" + enumId
				+ ",enumvCode=" + enumvCode + ",enumvName=" + enumvName
				+ ",enumvDesc=" + enumvDesc + ",orderNo=" + orderNo
				+ ",parentEnumvId=" + parentEnumvId + ",version=" + version
				+ ",attribute1=" + attribute1 + ",attribute2=" + attribute2
				+ ",attribute3=" + attribute3 + ",attribute4=" + attribute4
				+ ",attribute5=" + attribute5 + ",deletedFlag=" + deletedFlag
				+ ",createdBy=" + createdBy + ",createdDate=" + createdDate
				+ ",updatedBy=" + updatedBy + ",updatedDate=" + updatedDate
				+ "]";
	}

}
