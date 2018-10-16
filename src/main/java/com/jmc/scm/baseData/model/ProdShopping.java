package com.jmc.scm.baseData.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * prod_shopping:购物车表
 */
@Entity
@Table(name = "prod_shopping")
public class ProdShopping implements Serializable {

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
	 * 商品编码:商品编码
	 */
	@PropertyDef(label = "商品编码", description = "商品编码:商品编码")
	private String prodCode;

	/**
	 * 商品名称:商品名称
	 */
	@PropertyDef(label = "商品名称", description = "商品名称:商品名称")
	private String prodName;

	/**
	 * 镶口:镶口
	 */
	@PropertyDef(label = "镶口", description = "镶口:镶口")
	private BigDecimal inlaiedScoop;

	/**
	 * 尺码:尺码
	 */
	@PropertyDef(label = "尺码", description = "尺码:尺码")
	private String size;

	/**
	 * 数量:数量
	 */
	@PropertyDef(label = "数量", description = "数量:数量")
	private BigDecimal nums;

	/**
	 * 金成色:金成色
	 */
	@PropertyDef(label = "金成色", description = "金成色:金成色")
	private String goldColor;

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

	public ProdShopping() {
		super();
	}

	public ProdShopping(String pkId, String client, String prodCode,
			String prodName, BigDecimal inlaiedScoop, String size,
			BigDecimal nums, String goldColor, String remark, String createdBy,
			Date createdD, String updatedBy, Date updatedD, String attr1,
			String attr2, String attr3, String attr4, String attr5,
			String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.inlaiedScoop = inlaiedScoop;
		this.size = size;
		this.nums = nums;
		this.goldColor = goldColor;
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

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	@Column(name = "PROD_CODE", length = 40)
	public String getProdCode() {
		return prodCode;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Column(name = "PROD_NAME", length = 100)
	public String getProdName() {
		return prodName;
	}

	public void setInlaiedScoop(BigDecimal inlaiedScoop) {
		this.inlaiedScoop = inlaiedScoop;
	}

	@Column(name = "INLAIED_SCOOP")
	public BigDecimal getInlaiedScoop() {
		return inlaiedScoop;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "SIZE", length = 30)
	public String getSize() {
		return size;
	}

	public void setNums(BigDecimal nums) {
		this.nums = nums;
	}

	@Column(name = "NUMS")
	public BigDecimal getNums() {
		return nums;
	}

	public void setGoldColor(String goldColor) {
		this.goldColor = goldColor;
	}

	@Column(name = "GOLD_COLOR", length = 30)
	public String getGoldColor() {
		return goldColor;
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
		return "ProdShopping [pkId=" + pkId + ",client=" + client
				+ ",prodCode=" + prodCode + ",prodName=" + prodName
				+ ",inlaiedScoop=" + inlaiedScoop + ",size=" + size + ",nums="
				+ nums + ",goldColor=" + goldColor + ",remark=" + remark
				+ ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ "]";
	}

}
