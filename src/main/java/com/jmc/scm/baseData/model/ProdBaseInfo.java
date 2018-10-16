package com.jmc.scm.baseData.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * prod_base_info:商品基本信息表
 */
@Entity
@Table(name = "prod_base_info")
public class ProdBaseInfo implements Serializable {

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
	 * 其他编码:其他编码
	 */
	@PropertyDef(label = "其他编码", description = "其他编码:其他编码")
	private String otherProdCode;

	/**
	 * 品牌:品牌
	 */
	@PropertyDef(label = "品牌", description = "品牌:品牌")
	private String brand;

	/**
	 * 计量单位:计量单位
	 */
	@PropertyDef(label = "计量单位", description = "计量单位:计量单位")
	private String measUnit;

	/**
	 * 存货单位(重量单位):存货单位(重量单位)
	 */
	@PropertyDef(label = "存货单位(重量单位)", description = "存货单位(重量单位):存货单位(重量单位)")
	private String invtyUnit;

	/**
	 * 库存管理属性:库存管理属性
	 */
	@PropertyDef(label = "库存管理属性", description = "库存管理属性:库存管理属性")
	private String invtyMgtAttr;

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
	 * 主题:主题
	 */
	@PropertyDef(label = "主题", description = "主题:主题")
	private String prodTheme;

	/**
	 * 系列一:系列一
	 */
	@PropertyDef(label = "系列一", description = "系列一:系列一")
	private String firstRange;

	/**
	 * 品类一级:品类一级
	 */
	@PropertyDef(label = "品类一级", description = "品类一级:品类一级")
	private String firstCatg;

	/**
	 * 品类二级:品类二级
	 */
	@PropertyDef(label = "品类二级", description = "品类二级:品类二级")
	private String secCatg;

	/**
	 * 臂型:臂型
	 */
	@PropertyDef(label = "臂型", description = "臂型:臂型")
	private String armType;

	/**
	 * 造型:造型
	 */
	@PropertyDef(label = "造型", description = "造型:造型")
	private String model;

	/**
	 * 形状:形状
	 */
	@PropertyDef(label = "形状", description = "形状:形状")
	private String shape;

	/**
	 * 镶嵌方式:镶嵌方式
	 */
	@PropertyDef(label = "镶嵌方式", description = "镶嵌方式:镶嵌方式")
	private String mosType;

	/**
	 * 生产工艺:生产工艺
	 */
	@PropertyDef(label = "生产工艺", description = "生产工艺:生产工艺")
	private String prodCraft;

	/**
	 * 表面处理备注:表面处理备注
	 */
	@PropertyDef(label = "表面处理备注", description = "表面处理备注:表面处理备注")
	private String surfHandleRem;

	/**
	 * 其他工艺备注:其他工艺备注
	 */
	@PropertyDef(label = "其他工艺备注", description = "其他工艺备注:其他工艺备注")
	private String otherCraftRem;

	/**
	 * 配件备注:配件备注
	 */
	@PropertyDef(label = "配件备注", description = "配件备注:配件备注")
	private String cmptRem;

	/**
	 * 设计备注:设计备注
	 */
	@PropertyDef(label = "设计备注", description = "设计备注:设计备注")
	private String designRem;

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

	public ProdBaseInfo() {
		super();
	}

	public ProdBaseInfo(String pkId, String client, String prodCode,
			String prodName, String otherProdCode, String brand,
			String measUnit, String invtyUnit, String invtyMgtAttr,
			String supCode, String supName, String prodTheme,
			String firstRange, String firstCatg, String secCatg,
			String armType, String model, String shape, String mosType,
			String prodCraft, String surfHandleRem, String otherCraftRem,
			String cmptRem, String designRem, String status, String createdBy,
			Date createdD, String updatedBy, Date updatedD, String attr1,
			String attr2, String attr3, String attr4, String attr5,
			String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.prodCode = prodCode;
		this.prodName = prodName;
		this.otherProdCode = otherProdCode;
		this.brand = brand;
		this.measUnit = measUnit;
		this.invtyUnit = invtyUnit;
		this.invtyMgtAttr = invtyMgtAttr;
		this.supCode = supCode;
		this.supName = supName;
		this.prodTheme = prodTheme;
		this.firstRange = firstRange;
		this.firstCatg = firstCatg;
		this.secCatg = secCatg;
		this.armType = armType;
		this.model = model;
		this.shape = shape;
		this.mosType = mosType;
		this.prodCraft = prodCraft;
		this.surfHandleRem = surfHandleRem;
		this.otherCraftRem = otherCraftRem;
		this.cmptRem = cmptRem;
		this.designRem = designRem;
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

	@Column(name = "CLIENT", length = 4, unique = true, nullable = false)
	public String getClient() {
		return client;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	@Column(name = "PROD_CODE", length = 40, unique = true, nullable = false)
	public String getProdCode() {
		return prodCode;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	@Column(name = "PROD_NAME", length = 40)
	public String getProdName() {
		return prodName;
	}

	public void setOtherProdCode(String otherProdCode) {
		this.otherProdCode = otherProdCode;
	}

	@Column(name = "OTHER_PROD_CODE", length = 40)
	public String getOtherProdCode() {
		return otherProdCode;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "BRAND", length = 10)
	public String getBrand() {
		return brand;
	}

	public void setMeasUnit(String measUnit) {
		this.measUnit = measUnit;
	}

	@Column(name = "MEAS_UNIT", length = 3)
	public String getMeasUnit() {
		return measUnit;
	}

	public void setInvtyUnit(String invtyUnit) {
		this.invtyUnit = invtyUnit;
	}

	@Column(name = "INVTY_UNIT", length = 3)
	public String getInvtyUnit() {
		return invtyUnit;
	}

	public void setInvtyMgtAttr(String invtyMgtAttr) {
		this.invtyMgtAttr = invtyMgtAttr;
	}

	@Column(name = "INVTY_MGT_ATTR", length = 2)
	public String getInvtyMgtAttr() {
		return invtyMgtAttr;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	@Column(name = "SUP_CODE", length = 10)
	public String getSupCode() {
		return supCode;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	@Column(name = "SUP_NAME", length = 30)
	public String getSupName() {
		return supName;
	}

	public void setProdTheme(String prodTheme) {
		this.prodTheme = prodTheme;
	}

	@Column(name = "PROD_THEME", length = 30)
	public String getProdTheme() {
		return prodTheme;
	}

	public void setFirstRange(String firstRange) {
		this.firstRange = firstRange;
	}

	@Column(name = "FIRST_RANGE", length = 30)
	public String getFirstRange() {
		return firstRange;
	}

	public void setFirstCatg(String firstCatg) {
		this.firstCatg = firstCatg;
	}

	@Column(name = "FIRST_CATG", length = 5)
	public String getFirstCatg() {
		return firstCatg;
	}

	public void setSecCatg(String secCatg) {
		this.secCatg = secCatg;
	}

	@Column(name = "SEC_CATG", length = 5)
	public String getSecCatg() {
		return secCatg;
	}

	public void setArmType(String armType) {
		this.armType = armType;
	}

	@Column(name = "ARM_TYPE", length = 30)
	public String getArmType() {
		return armType;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "MODEL", length = 30)
	public String getModel() {
		return model;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	@Column(name = "SHAPE", length = 30)
	public String getShape() {
		return shape;
	}

	public void setMosType(String mosType) {
		this.mosType = mosType;
	}

	@Column(name = "MOS_TYPE", length = 30)
	public String getMosType() {
		return mosType;
	}

	public void setProdCraft(String prodCraft) {
		this.prodCraft = prodCraft;
	}

	@Column(name = "PROD_CRAFT", length = 30)
	public String getProdCraft() {
		return prodCraft;
	}

	public void setSurfHandleRem(String surfHandleRem) {
		this.surfHandleRem = surfHandleRem;
	}

	@Column(name = "SURF_HANDLE_REM", length = 255)
	public String getSurfHandleRem() {
		return surfHandleRem;
	}

	public void setOtherCraftRem(String otherCraftRem) {
		this.otherCraftRem = otherCraftRem;
	}

	@Column(name = "OTHER_CRAFT_REM", length = 255)
	public String getOtherCraftRem() {
		return otherCraftRem;
	}

	public void setCmptRem(String cmptRem) {
		this.cmptRem = cmptRem;
	}

	@Column(name = "CMPT_REM", length = 255)
	public String getCmptRem() {
		return cmptRem;
	}

	public void setDesignRem(String designRem) {
		this.designRem = designRem;
	}

	@Column(name = "DESIGN_REM", length = 255)
	public String getDesignRem() {
		return designRem;
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
		return "ProdBaseInfo [pkId=" + pkId + ",client=" + client
				+ ",prodCode=" + prodCode + ",prodName=" + prodName
				+ ",otherProdCode=" + otherProdCode + ",brand=" + brand
				+ ",measUnit=" + measUnit + ",invtyUnit=" + invtyUnit
				+ ",invtyMgtAttr=" + invtyMgtAttr + ",supCode=" + supCode
				+ ",supName=" + supName + ",prodTheme=" + prodTheme
				+ ",firstRange=" + firstRange + ",firstCatg=" + firstCatg
				+ ",secCatg=" + secCatg + ",armType=" + armType + ",model="
				+ model + ",shape=" + shape + ",mosType=" + mosType
				+ ",prodCraft=" + prodCraft + ",surfHandleRem=" + surfHandleRem
				+ ",otherCraftRem=" + otherCraftRem + ",cmptRem=" + cmptRem
				+ ",designRem=" + designRem + ",status=" + status
				+ ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ "]";
	}

}
