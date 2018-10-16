package com.jmc.scm.stock.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * stock_inv_main:库存主表
 */
@Entity
@Table(name = "stock_inv_main")
public class StockInvMain implements Serializable {

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
	 * 物料编码:物料编码
	 */
	@PropertyDef(label = "物料编码 ", description = "物料编码:物料编码")
	private String matCode;

	/**
	 * 物料名称:物料名称
	 */
	@PropertyDef(label = "物料名称", description = "物料名称:物料名称")
	private String matName;

	/**
	 * 数量:数量
	 */
	@PropertyDef(label = "数量", description = "数量:数量")
	private BigDecimal nums;

	/**
	 * 单位:单位
	 */
	@PropertyDef(label = "单位", description = "单位:单位")
	private String baseUnit;

	/**
	 * 重量:重量
	 */
	@PropertyDef(label = "重量", description = "重量:重量")
	private BigDecimal weight;

	/**
	 * 首次入库日期:首次入库日期
	 */
	@PropertyDef(label = "首次入库日期", description = "首次入库日期:首次入库日期")
	private Date storageDate;

	/**
	 * 金额:金额
	 */
	@PropertyDef(label = "金额", description = "金额:金额")
	private BigDecimal amount;

	/**
	 * 首次供应商编码:首次供应商编码
	 */
	@PropertyDef(label = "首次供应商编码", description = "首次供应商编码:首次供应商编码")
	private String supCode;

	/**
	 * 首次供应商名称:首次供应商名称
	 */
	@PropertyDef(label = "首次供应商名称", description = "首次供应商名称:首次供应商名称")
	private String supName;

	/**
	 * 图片ID:图片ID
	 */
	@PropertyDef(label = "图片ID", description = "图片ID:图片ID")
	private String imgId;

	/**
	 * 供应商单号:供应商单号
	 */
	@PropertyDef(label = "供应商单号", description = "供应商单号:供应商单号")
	private String supNo;

	/**
	 * 备注:备注
	 */
	@PropertyDef(label = "备注", description = "备注:备注")
	private String remarks;

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

	public StockInvMain() {
		super();
	}

	public StockInvMain(String pkId, String client, String matCode,
			String matName, BigDecimal nums, String baseUnit,
			BigDecimal weight, Date storageDate, BigDecimal amount,
			String supCode, String supName, String imgId, String supNo,
			String remarks, String createdBy, Date createdD, String updatedBy,
			Date updatedD, String attr1, String attr2, String attr3,
			String attr4, String attr5, String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.matCode = matCode;
		this.matName = matName;
		this.nums = nums;
		this.baseUnit = baseUnit;
		this.weight = weight;
		this.storageDate = storageDate;
		this.amount = amount;
		this.supCode = supCode;
		this.supName = supName;
		this.imgId = imgId;
		this.supNo = supNo;
		this.remarks = remarks;
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

	@Column(name = "CLIENT", length = 4, unique = true, nullable = false)
	public String getClient() {
		return client;
	}

	public void setMatCode(String matCode) {
		this.matCode = matCode;
	}

	@Column(name = "MAT_CODE", length = 80, unique = true, nullable = false)
	public String getMatCode() {
		return matCode;
	}

	public void setMatName(String matName) {
		this.matName = matName;
	}

	@Column(name = "MAT_NAME", length = 100)
	public String getMatName() {
		return matName;
	}

	public void setNums(BigDecimal nums) {
		this.nums = nums;
	}

	@Column(name = "NUMS", nullable = false)
	public BigDecimal getNums() {
		return nums;
	}

	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}

	@Column(name = "BASE_UNIT", length = 10)
	public String getBaseUnit() {
		return baseUnit;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name = "WEIGHT")
	public BigDecimal getWeight() {
		return weight;
	}

	public void setStorageDate(Date storageDate) {
		this.storageDate = storageDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STORAGE_DATE")
	public Date getStorageDate() {
		return storageDate;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Column(name = "AMOUNT")
	public BigDecimal getAmount() {
		return amount;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	@Column(name = "SUP_CODE", length = 30)
	public String getSupCode() {
		return supCode;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	@Column(name = "SUP_NAME", length = 100)
	public String getSupName() {
		return supName;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	@Column(name = "IMG_ID", length = 36)
	public String getImgId() {
		return imgId;
	}

	public void setSupNo(String supNo) {
		this.supNo = supNo;
	}

	@Column(name = "SUP_NO", length = 60)
	public String getSupNo() {
		return supNo;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Lob
	@Basic(fetch = FetchType.EAGER)
	@Column(name = "REMARKS", columnDefinition = "CLOB")
	public String getRemarks() {
		return remarks;
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
	@Column(name = "CREATED_D", unique = true, nullable = false)
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

	@Column(name = "DEL_FLG", length = 1, unique = true, nullable = false)
	public String getDelFlg() {
		return delFlg;
	}

	public String toString() {
		return "StockInvMain [pkId=" + pkId + ",client=" + client + ",matCode="
				+ matCode + ",matName=" + matName + ",nums=" + nums
				+ ",baseUnit=" + baseUnit + ",weight=" + weight
				+ ",storageDate=" + storageDate + ",amount=" + amount
				+ ",supCode=" + supCode + ",supName=" + supName + ",imgId="
				+ imgId + ",supNo=" + supNo + ",remarks=" + remarks
				+ ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ "]";
	}

}
