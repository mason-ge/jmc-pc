package com.jmc.scm.system.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * sys_enum:枚举表
 */
@Entity
@Table(name = "sys_enum")
public class SysEnum implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键:主键
	 */
	@PropertyDef(label = "主键", description = "主键:主键")
	private String enumId;

	/**
	 * 枚举编码:枚举编码
	 */
	@PropertyDef(label = "枚举编码", description = "枚举编码:枚举编码")
	private String enumCode;

	/**
	 * 枚举名称:枚举名称
	 */
	@PropertyDef(label = "枚举名称", description = "枚举名称:枚举名称")
	private String enumName;

	/**
	 * 枚举描述:枚举描述
	 */
	@PropertyDef(label = "枚举描述", description = "枚举描述:枚举描述")
	private String enumDesc;

	/**
	 * 枚举类型:枚举类型
	 */
	@PropertyDef(label = "枚举类型", description = "枚举类型:枚举类型")
	private String enumType;

	/**
	 * 枚举模块:枚举模块
	 */
	@PropertyDef(label = "枚举模块", description = "枚举模块:枚举模块")
	private String model;

	/**
	 * 同步时间:同步时间
	 */
	@PropertyDef(label = "同步时间", description = "同步时间:同步时间")
	private Date syncDate;

	/**
	 * 同步标记:同步标记
	 */
	@PropertyDef(label = "同步标记", description = "同步标记:同步标记")
	private String syncFlag;

	/**
	 * 数据来源:数据来源
	 */
	@PropertyDef(label = "数据来源", description = "数据来源:数据来源")
	private String valueFrom;

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

	public SysEnum() {
		super();
	}

	public SysEnum(String enumId, String enumCode, String enumName,
			String enumDesc, String enumType, String model, Date syncDate,
			String syncFlag, String valueFrom, int version, String attribute1,
			String attribute2, String attribute3, String attribute4,
			String attribute5, int deletedFlag, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate) {
		super();
		this.enumId = enumId;
		this.enumCode = enumCode;
		this.enumName = enumName;
		this.enumDesc = enumDesc;
		this.enumType = enumType;
		this.model = model;
		this.syncDate = syncDate;
		this.syncFlag = syncFlag;
		this.valueFrom = valueFrom;
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

	public void setEnumId(String enumId) {
		this.enumId = enumId;
	}

	@Id
	@Column(name = "enum_id", length = 36, nullable = false)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getEnumId() {
		return enumId;
	}

	public void setEnumCode(String enumCode) {
		this.enumCode = enumCode;
	}

	@Column(name = "enum_code", length = 100, unique = true, nullable = false)
	public String getEnumCode() {
		return enumCode;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	@Column(name = "enum_name", length = 30, nullable = false)
	public String getEnumName() {
		return enumName;
	}

	public void setEnumDesc(String enumDesc) {
		this.enumDesc = enumDesc;
	}

	@Column(name = "enum_desc", length = 240)
	public String getEnumDesc() {
		return enumDesc;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	@Column(name = "enum_type", length = 30)
	public String getEnumType() {
		return enumType;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "model", length = 30)
	public String getModel() {
		return model;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "sync_date")
	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncFlag(String syncFlag) {
		this.syncFlag = syncFlag;
	}

	@Column(name = "sync_flag", length = 1)
	public String getSyncFlag() {
		return syncFlag;
	}

	public void setValueFrom(String valueFrom) {
		this.valueFrom = valueFrom;
	}

	@Column(name = "value_from", length = 30)
	public String getValueFrom() {
		return valueFrom;
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
		return "AlloveEnum [enumId=" + enumId + ",enumCode=" + enumCode
				+ ",enumName=" + enumName + ",enumDesc=" + enumDesc
				+ ",enumType=" + enumType + ",model=" + model + ",syncDate="
				+ syncDate + ",syncFlag=" + syncFlag + ",valueFrom="
				+ valueFrom + ",version=" + version + ",attribute1="
				+ attribute1 + ",attribute2=" + attribute2 + ",attribute3="
				+ attribute3 + ",attribute4=" + attribute4 + ",attribute5="
				+ attribute5 + ",deletedFlag=" + deletedFlag + ",createdBy="
				+ createdBy + ",createdDate=" + createdDate + ",updatedBy="
				+ updatedBy + ",updatedDate=" + updatedDate + "]";
	}

}
