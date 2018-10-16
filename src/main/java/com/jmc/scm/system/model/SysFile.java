package com.jmc.scm.system.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * sys_file:文件表
 */
@Entity
@Table(name = "sys_file")
public class SysFile implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键:主键
	 */
	@PropertyDef(label = "主键", description = "主键:主键")
	private String pkId;

	/**
	 * 业务主键:业务主键
	 */
	@PropertyDef(label = "业务主键", description = "业务主键:业务主键")
	private String bizCode;

	/**
	 * 文件名:文件名
	 */
	@PropertyDef(label = "文件名", description = "文件名:文件名")
	private String fileName;

	/**
	 * 文件HTTP获取路径:文件HTTP获取路径
	 */
	@PropertyDef(label = "文件HTTP获取路径", description = "文件HTTP获取路径:文件HTTP获取路径")
	private String fileHttpPath;

	/**
	 * 文件绝对路径:文件绝对路径
	 */
	@PropertyDef(label = "文件绝对路径", description = "文件绝对路径:文件绝对路径")
	private String fileAbsolutePath;

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

	public SysFile() {
		super();
	}

	public SysFile(String pkId, String bizCode, String fileName,
			String fileHttpPath, String fileAbsolutePath, String attr1,
			String attr2, String attr3, String attr4, String attr5,
			String delFlg, String createdBy, Date createdD, String updatedBy,
			Date updatedD) {
		super();
		this.pkId = pkId;
		this.bizCode = bizCode;
		this.fileName = fileName;
		this.fileHttpPath = fileHttpPath;
		this.fileAbsolutePath = fileAbsolutePath;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.delFlg = delFlg;
		this.createdBy = createdBy;
		this.createdD = createdD;
		this.updatedBy = updatedBy;
		this.updatedD = updatedD;
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

	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	@Column(name = "BIZ_CODE", length = 36)
	public String getBizCode() {
		return bizCode;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name = "FILE_NAME", length = 200)
	public String getFileName() {
		return fileName;
	}

	public void setFileHttpPath(String fileHttpPath) {
		this.fileHttpPath = fileHttpPath;
	}

	@Column(name = "FILE_HTTP_PATH", length = 500)
	public String getFileHttpPath() {
		return fileHttpPath;
	}

	public void setFileAbsolutePath(String fileAbsolutePath) {
		this.fileAbsolutePath = fileAbsolutePath;
	}

	@Column(name = "FILE_ABSOLUTE_PATH", length = 500)
	public String getFileAbsolutePath() {
		return fileAbsolutePath;
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

	@Column(name = "DEL_FLG", length = 1)
	public String getDelFlg() {
		return delFlg;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_BY", length = 60)
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedD(Date createdD) {
		this.createdD = createdD;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_D")
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

	public String toString() {
		return "AlloveFile [pkId=" + pkId + ",bizCode=" + bizCode
				+ ",fileName=" + fileName + ",fileHttpPath=" + fileHttpPath
				+ ",fileAbsolutePath=" + fileAbsolutePath + ",attr1=" + attr1
				+ ",attr2=" + attr2 + ",attr3=" + attr3 + ",attr4=" + attr4
				+ ",attr5=" + attr5 + ",delFlg=" + delFlg + ",createdBy="
				+ createdBy + ",createdD=" + createdD + ",updatedBy="
				+ updatedBy + ",updatedD=" + updatedD + "]";
	}

}
