package com.jmc.scm.stock.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * stock_move_item:入库子表
 */
@Entity
@Table(name = "stock_move_item")
public class StockMoveItem implements Serializable {

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
	 * 入库单号:入库单号
	 */
	@PropertyDef(label = "入库单号", description = "入库单号:入库单号")
	private String prestoCode;

	/**
	 * PRESTO_ITEM_CODE:
	 */
	@PropertyDef(label = "PRESTO_ITEM_CODE", description = "PRESTO_ITEM_CODE:")
	private String prestoItemCode;

	/**
	 * 批次号(货号):批次号(货号)
	 */
	@PropertyDef(label = "批次号(货号)", description = "批次号(货号):批次号(货号)")
	private String batchCode;

	/**
	 * 单品名称:单品名称
	 */
	@PropertyDef(label = "单品名称", description = "单品名称:单品名称")
	private String spName;

	/**
	 * 商品编码:商品编码
	 */
	@PropertyDef(label = "商品编码", description = "商品编码:商品编码")
	private String prodCode;

	/**
	 * 质检是否合格:质检是否合格
	 */
	@PropertyDef(label = "质检是否合格", description = "质检是否合格:质检是否合格")
	private String qaTestsQualified;

	/**
	 * 是否空托:是否空托
	 */
	@PropertyDef(label = "是否空托", description = "是否空托:是否空托")
	private String isEmptyBracket;

	/**
	 * 镶嵌方式:镶嵌方式
	 */
	@PropertyDef(label = "镶嵌方式", description = "镶嵌方式:镶嵌方式")
	private String mosType;

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
	 * 工厂款号:工厂款号
	 */
	@PropertyDef(label = "工厂款号", description = "工厂款号:工厂款号")
	private String facProdCode;

	/**
	 * 零售价:零售价
	 */
	@PropertyDef(label = "零售价", description = "零售价:零售价")
	private BigDecimal retPrice;

	/**
	 * 金种类:金种类
	 */
	@PropertyDef(label = "金种类", description = "金种类:金种类")
	private String goldType;

	/**
	 * 金成色:金成色
	 */
	@PropertyDef(label = "金成色", description = "金成色:金成色")
	private String goldColor;

	/**
	 * 材质:材质
	 */
	@PropertyDef(label = "材质", description = "材质:材质")
	private String material;

	/**
	 * 净金重:净金重
	 */
	@PropertyDef(label = "净金重", description = "净金重:净金重")
	private BigDecimal clarityGWt;

	/**
	 * 重量:重量
	 */
	@PropertyDef(label = "重量", description = "重量:重量")
	private BigDecimal weight;

	/**
	 * 尺寸:尺寸
	 */
	@PropertyDef(label = "尺寸", description = "尺寸:尺寸")
	private String size;

	/**
	 * 金损耗:金损耗
	 */
	@PropertyDef(label = "金损耗", description = "金损耗:金损耗")
	private BigDecimal goldWastage;

	/**
	 * 含耗金重:含耗金重
	 */
	@PropertyDef(label = "含耗金重", description = "含耗金重:含耗金重")
	private BigDecimal inclWastageGWt;

	/**
	 * 金价:金价
	 */
	@PropertyDef(label = "金价", description = "金价:金价")
	private BigDecimal goldPx;

	/**
	 * 工厂金料款:工厂金料款
	 */
	@PropertyDef(label = "工厂金料款", description = "工厂金料款:工厂金料款")
	private BigDecimal facGPx;

	/**
	 * 公司金料款:公司金料款
	 */
	@PropertyDef(label = "公司金料款", description = "公司金料款:公司金料款")
	private BigDecimal cmpyGPx;

	/**
	 * 工厂配件款:工厂配件款
	 */
	@PropertyDef(label = "工厂配件款", description = "工厂配件款:工厂配件款")
	private BigDecimal facAccyPx;

	/**
	 * 公司配件款:公司配件款
	 */
	@PropertyDef(label = "公司配件款", description = "公司配件款:公司配件款")
	private BigDecimal comAccyPx;

	/**
	 * 工厂石料款:工厂石料款
	 */
	@PropertyDef(label = "工厂石料款", description = "工厂石料款:工厂石料款")
	private BigDecimal facStPx;

	/**
	 * 公司石料款:公司石料款
	 */
	@PropertyDef(label = "公司石料款", description = "公司石料款:公司石料款")
	private BigDecimal comStPx;

	/**
	 * 起版费:起版费
	 */
	@PropertyDef(label = "起版费", description = "起版费:起版费")
	private BigDecimal initBandCost;

	/**
	 * 基本工费:基本工费
	 */
	@PropertyDef(label = "基本工费", description = "基本工费:基本工费")
	private BigDecimal baseCost;

	/**
	 * 其他费用:其他费用
	 */
	@PropertyDef(label = "其他费用", description = "其他费用:其他费用")
	private BigDecimal otherCost;

	/**
	 * 工厂结算款:工厂结算款
	 */
	@PropertyDef(label = "工厂结算款", description = "工厂结算款:工厂结算款")
	private BigDecimal facSettlePx;

	/**
	 * 证书类型:证书类型
	 */
	@PropertyDef(label = "证书类型", description = "证书类型:证书类型")
	private String cerType;

	/**
	 * 主石石号:主石石号
	 */
	@PropertyDef(label = "主石石号", description = "主石石号:主石石号")
	private String mainStCode;

	/**
	 * 主石证书类型:主石证书类型
	 */
	@PropertyDef(label = "主石证书类型", description = "主石证书类型:主石证书类型")
	private String mainCerType;

	/**
	 * 主石证书号:主石证书号
	 */
	@PropertyDef(label = "主石证书号", description = "主石证书号:主石证书号")
	private String mainCerCode;

	/**
	 * 证书号:证书号
	 */
	@PropertyDef(label = "证书号", description = "证书号:证书号")
	private String cerNum;

	/**
	 * 主石颜色:主石颜色
	 */
	@PropertyDef(label = "主石颜色", description = "主石颜色:主石颜色")
	private String color;

	/**
	 * 主石净度:主石净度
	 */
	@PropertyDef(label = "主石净度", description = "主石净度:主石净度")
	private String neat;

	/**
	 * 主石重量:主石重量
	 */
	@PropertyDef(label = "主石重量", description = "主石重量:主石重量")
	private BigDecimal manStWt;

	/**
	 * 证书货品名称:证书货品名称
	 */
	@PropertyDef(label = "证书货品名称", description = "证书货品名称:证书货品名称")
	private String ngtcGoods;

	/**
	 * 证书总质量:证书总质量
	 */
	@PropertyDef(label = "证书总质量", description = "证书总质量:证书总质量")
	private BigDecimal ngtcTotQuality;

	/**
	 * 证书金成色:证书金成色
	 */
	@PropertyDef(label = "证书金成色", description = "证书金成色:证书金成色")
	private String ngtcGFineness;

	/**
	 * 其他证书类型:其他证书类型
	 */
	@PropertyDef(label = "其他证书类型", description = "其他证书类型:其他证书类型")
	private String otherCerType;

	/**
	 * 其他证书号:其他证书号
	 */
	@PropertyDef(label = "其他证书号", description = "其他证书号:其他证书号")
	private String otherCerCode;

	/**
	 * 镶口:镶口
	 */
	@PropertyDef(label = "镶口", description = "镶口:镶口")
	private BigDecimal inlaiedScoop;

	/**
	 * 主石其他信息:主石其他信息
	 */
	@PropertyDef(label = "主石其他信息", description = "主石其他信息:主石其他信息")
	private String manStInfo;

	/**
	 * 副石重量:副石重量
	 */
	@PropertyDef(label = "副石重量", description = "副石重量:副石重量")
	private BigDecimal asstStWt;

	/**
	 * 副石其他信息:副石其他信息
	 */
	@PropertyDef(label = "副石其他信息", description = "副石其他信息:副石其他信息")
	private String asstStInfo;

	/**
	 * 副石数量:副石数量
	 */
	@PropertyDef(label = "副石数量", description = "副石数量:副石数量")
	private BigDecimal asstStAmt;

	/**
	 * 上市时间:上市时间
	 */
	@PropertyDef(label = "上市时间", description = "上市时间:上市时间")
	private String lauDate;

	/**
	 * 数量:数量
	 */
	@PropertyDef(label = "数量", description = "数量:数量")
	private BigDecimal nums;

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

	public StockMoveItem() {
		super();
	}

	public StockMoveItem(String pkId, String client, String prestoCode,
			String prestoItemCode, String batchCode, String spName,
			String prodCode, String qaTestsQualified, String isEmptyBracket,
			String mosType, String firstCatg, String secCatg,
			String facProdCode, BigDecimal retPrice, String goldType,
			String goldColor, String material, BigDecimal clarityGWt,
			BigDecimal weight, String size, BigDecimal goldWastage,
			BigDecimal inclWastageGWt, BigDecimal goldPx, BigDecimal facGPx,
			BigDecimal cmpyGPx, BigDecimal facAccyPx, BigDecimal comAccyPx,
			BigDecimal facStPx, BigDecimal comStPx, BigDecimal initBandCost,
			BigDecimal baseCost, BigDecimal otherCost, BigDecimal facSettlePx,
			String cerType, String mainStCode, String mainCerType,
			String mainCerCode, String cerNum, String color, String neat,
			BigDecimal manStWt, String ngtcGoods, BigDecimal ngtcTotQuality,
			String ngtcGFineness, String otherCerType, String otherCerCode,
			BigDecimal inlaiedScoop, String manStInfo, BigDecimal asstStWt,
			String asstStInfo, BigDecimal asstStAmt, String lauDate,
			BigDecimal nums, String remark, String createdBy, Date createdD,
			String updatedBy, Date updatedD, String attr1, String attr2,
			String attr3, String attr4, String attr5, String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.prestoCode = prestoCode;
		this.prestoItemCode = prestoItemCode;
		this.batchCode = batchCode;
		this.spName = spName;
		this.prodCode = prodCode;
		this.qaTestsQualified = qaTestsQualified;
		this.isEmptyBracket = isEmptyBracket;
		this.mosType = mosType;
		this.firstCatg = firstCatg;
		this.secCatg = secCatg;
		this.facProdCode = facProdCode;
		this.retPrice = retPrice;
		this.goldType = goldType;
		this.goldColor = goldColor;
		this.material = material;
		this.clarityGWt = clarityGWt;
		this.weight = weight;
		this.size = size;
		this.goldWastage = goldWastage;
		this.inclWastageGWt = inclWastageGWt;
		this.goldPx = goldPx;
		this.facGPx = facGPx;
		this.cmpyGPx = cmpyGPx;
		this.facAccyPx = facAccyPx;
		this.comAccyPx = comAccyPx;
		this.facStPx = facStPx;
		this.comStPx = comStPx;
		this.initBandCost = initBandCost;
		this.baseCost = baseCost;
		this.otherCost = otherCost;
		this.facSettlePx = facSettlePx;
		this.cerType = cerType;
		this.mainStCode = mainStCode;
		this.mainCerType = mainCerType;
		this.mainCerCode = mainCerCode;
		this.cerNum = cerNum;
		this.color = color;
		this.neat = neat;
		this.manStWt = manStWt;
		this.ngtcGoods = ngtcGoods;
		this.ngtcTotQuality = ngtcTotQuality;
		this.ngtcGFineness = ngtcGFineness;
		this.otherCerType = otherCerType;
		this.otherCerCode = otherCerCode;
		this.inlaiedScoop = inlaiedScoop;
		this.manStInfo = manStInfo;
		this.asstStWt = asstStWt;
		this.asstStInfo = asstStInfo;
		this.asstStAmt = asstStAmt;
		this.lauDate = lauDate;
		this.nums = nums;
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

	@Column(name = "CLIENT", length = 4, unique = true, nullable = false)
	public String getClient() {
		return client;
	}

	public void setPrestoCode(String prestoCode) {
		this.prestoCode = prestoCode;
	}

	@Column(name = "PRESTO_CODE", length = 20, unique = true, nullable = false)
	public String getPrestoCode() {
		return prestoCode;
	}

	public void setPrestoItemCode(String prestoItemCode) {
		this.prestoItemCode = prestoItemCode;
	}

	@Column(name = "PRESTO_ITEM_CODE", length = 10, unique = true, nullable = false)
	public String getPrestoItemCode() {
		return prestoItemCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	@Column(name = "BATCH_CODE", length = 20, unique = true, nullable = false)
	public String getBatchCode() {
		return batchCode;
	}

	public void setSpName(String spName) {
		this.spName = spName;
	}

	@Column(name = "SP_NAME", length = 80)
	public String getSpName() {
		return spName;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	@Column(name = "PROD_CODE", length = 40)
	public String getProdCode() {
		return prodCode;
	}

	public void setQaTestsQualified(String qaTestsQualified) {
		this.qaTestsQualified = qaTestsQualified;
	}

	@Column(name = "QA_TESTS_QUALIFIED", length = 20)
	public String getQaTestsQualified() {
		return qaTestsQualified;
	}

	public void setIsEmptyBracket(String isEmptyBracket) {
		this.isEmptyBracket = isEmptyBracket;
	}

	@Column(name = "IS_EMPTY_BRACKET", length = 1)
	public String getIsEmptyBracket() {
		return isEmptyBracket;
	}

	public void setMosType(String mosType) {
		this.mosType = mosType;
	}

	@Column(name = "MOS_TYPE", length = 30)
	public String getMosType() {
		return mosType;
	}

	public void setFirstCatg(String firstCatg) {
		this.firstCatg = firstCatg;
	}

	@Column(name = "FIRST_CATG", length = 10)
	public String getFirstCatg() {
		return firstCatg;
	}

	public void setSecCatg(String secCatg) {
		this.secCatg = secCatg;
	}

	@Column(name = "SEC_CATG", length = 10)
	public String getSecCatg() {
		return secCatg;
	}

	public void setFacProdCode(String facProdCode) {
		this.facProdCode = facProdCode;
	}

	@Column(name = "FAC_PROD_CODE", length = 40)
	public String getFacProdCode() {
		return facProdCode;
	}

	public void setRetPrice(BigDecimal retPrice) {
		this.retPrice = retPrice;
	}

	@Column(name = "RET_PRICE")
	public BigDecimal getRetPrice() {
		return retPrice;
	}

	public void setGoldType(String goldType) {
		this.goldType = goldType;
	}

	@Column(name = "GOLD_TYPE", length = 10)
	public String getGoldType() {
		return goldType;
	}

	public void setGoldColor(String goldColor) {
		this.goldColor = goldColor;
	}

	@Column(name = "GOLD_COLOR", length = 10)
	public String getGoldColor() {
		return goldColor;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "MATERIAL", length = 30)
	public String getMaterial() {
		return material;
	}

	public void setClarityGWt(BigDecimal clarityGWt) {
		this.clarityGWt = clarityGWt;
	}

	@Column(name = "CLARITY_G_WT")
	public BigDecimal getClarityGWt() {
		return clarityGWt;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name = "WEIGHT")
	public BigDecimal getWeight() {
		return weight;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Column(name = "SIZE", length = 10)
	public String getSize() {
		return size;
	}

	public void setGoldWastage(BigDecimal goldWastage) {
		this.goldWastage = goldWastage;
	}

	@Column(name = "GOLD_WASTAGE")
	public BigDecimal getGoldWastage() {
		return goldWastage;
	}

	public void setInclWastageGWt(BigDecimal inclWastageGWt) {
		this.inclWastageGWt = inclWastageGWt;
	}

	@Column(name = "INCL_WASTAGE_G_WT")
	public BigDecimal getInclWastageGWt() {
		return inclWastageGWt;
	}

	public void setGoldPx(BigDecimal goldPx) {
		this.goldPx = goldPx;
	}

	@Column(name = "GOLD_PX")
	public BigDecimal getGoldPx() {
		return goldPx;
	}

	public void setFacGPx(BigDecimal facGPx) {
		this.facGPx = facGPx;
	}

	@Column(name = "FAC_G_PX")
	public BigDecimal getFacGPx() {
		return facGPx;
	}

	public void setCmpyGPx(BigDecimal cmpyGPx) {
		this.cmpyGPx = cmpyGPx;
	}

	@Column(name = "CMPY_G_PX")
	public BigDecimal getCmpyGPx() {
		return cmpyGPx;
	}

	public void setFacAccyPx(BigDecimal facAccyPx) {
		this.facAccyPx = facAccyPx;
	}

	@Column(name = "FAC_ACCY_PX")
	public BigDecimal getFacAccyPx() {
		return facAccyPx;
	}

	public void setComAccyPx(BigDecimal comAccyPx) {
		this.comAccyPx = comAccyPx;
	}

	@Column(name = "COM_ACCY_PX")
	public BigDecimal getComAccyPx() {
		return comAccyPx;
	}

	public void setFacStPx(BigDecimal facStPx) {
		this.facStPx = facStPx;
	}

	@Column(name = "FAC_ST_PX")
	public BigDecimal getFacStPx() {
		return facStPx;
	}

	public void setComStPx(BigDecimal comStPx) {
		this.comStPx = comStPx;
	}

	@Column(name = "COM_ST_PX")
	public BigDecimal getComStPx() {
		return comStPx;
	}

	public void setInitBandCost(BigDecimal initBandCost) {
		this.initBandCost = initBandCost;
	}

	@Column(name = "INIT_BAND_COST")
	public BigDecimal getInitBandCost() {
		return initBandCost;
	}

	public void setBaseCost(BigDecimal baseCost) {
		this.baseCost = baseCost;
	}

	@Column(name = "BASE_COST")
	public BigDecimal getBaseCost() {
		return baseCost;
	}

	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}

	@Column(name = "OTHER_COST")
	public BigDecimal getOtherCost() {
		return otherCost;
	}

	public void setFacSettlePx(BigDecimal facSettlePx) {
		this.facSettlePx = facSettlePx;
	}

	@Column(name = "FAC_SETTLE_PX")
	public BigDecimal getFacSettlePx() {
		return facSettlePx;
	}

	public void setCerType(String cerType) {
		this.cerType = cerType;
	}

	@Column(name = "CER_TYPE", length = 10)
	public String getCerType() {
		return cerType;
	}

	public void setMainStCode(String mainStCode) {
		this.mainStCode = mainStCode;
	}

	@Column(name = "MAIN_ST_CODE", length = 40)
	public String getMainStCode() {
		return mainStCode;
	}

	public void setMainCerType(String mainCerType) {
		this.mainCerType = mainCerType;
	}

	@Column(name = "MAIN_CER_TYPE", length = 40)
	public String getMainCerType() {
		return mainCerType;
	}

	public void setMainCerCode(String mainCerCode) {
		this.mainCerCode = mainCerCode;
	}

	@Column(name = "MAIN_CER_CODE", length = 40)
	public String getMainCerCode() {
		return mainCerCode;
	}

	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}

	@Column(name = "CER_NUM", length = 40)
	public String getCerNum() {
		return cerNum;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(name = "COLOR", length = 10)
	public String getColor() {
		return color;
	}

	public void setNeat(String neat) {
		this.neat = neat;
	}

	@Column(name = "NEAT", length = 10)
	public String getNeat() {
		return neat;
	}

	public void setManStWt(BigDecimal manStWt) {
		this.manStWt = manStWt;
	}

	@Column(name = "MAN_ST_WT")
	public BigDecimal getManStWt() {
		return manStWt;
	}

	public void setNgtcGoods(String ngtcGoods) {
		this.ngtcGoods = ngtcGoods;
	}

	@Column(name = "NGTC_GOODS", length = 100)
	public String getNgtcGoods() {
		return ngtcGoods;
	}

	public void setNgtcTotQuality(BigDecimal ngtcTotQuality) {
		this.ngtcTotQuality = ngtcTotQuality;
	}

	@Column(name = "NGTC_TOT_QUALITY")
	public BigDecimal getNgtcTotQuality() {
		return ngtcTotQuality;
	}

	public void setNgtcGFineness(String ngtcGFineness) {
		this.ngtcGFineness = ngtcGFineness;
	}

	@Column(name = "NGTC_G_FINENESS", length = 20)
	public String getNgtcGFineness() {
		return ngtcGFineness;
	}

	public void setOtherCerType(String otherCerType) {
		this.otherCerType = otherCerType;
	}

	@Column(name = "OTHER_CER_TYPE", length = 30)
	public String getOtherCerType() {
		return otherCerType;
	}

	public void setOtherCerCode(String otherCerCode) {
		this.otherCerCode = otherCerCode;
	}

	@Column(name = "OTHER_CER_CODE", length = 40)
	public String getOtherCerCode() {
		return otherCerCode;
	}

	public void setInlaiedScoop(BigDecimal inlaiedScoop) {
		this.inlaiedScoop = inlaiedScoop;
	}

	@Column(name = "INLAIED_SCOOP")
	public BigDecimal getInlaiedScoop() {
		return inlaiedScoop;
	}

	public void setManStInfo(String manStInfo) {
		this.manStInfo = manStInfo;
	}

	@Column(name = "MAN_ST_INFO", length = 50)
	public String getManStInfo() {
		return manStInfo;
	}

	public void setAsstStWt(BigDecimal asstStWt) {
		this.asstStWt = asstStWt;
	}

	@Column(name = "ASST_ST_WT")
	public BigDecimal getAsstStWt() {
		return asstStWt;
	}

	public void setAsstStInfo(String asstStInfo) {
		this.asstStInfo = asstStInfo;
	}

	@Column(name = "ASST_ST_INFO", length = 50)
	public String getAsstStInfo() {
		return asstStInfo;
	}

	public void setAsstStAmt(BigDecimal asstStAmt) {
		this.asstStAmt = asstStAmt;
	}

	@Column(name = "ASST_ST_AMT")
	public BigDecimal getAsstStAmt() {
		return asstStAmt;
	}

	public void setLauDate(String lauDate) {
		this.lauDate = lauDate;
	}

	@Column(name = "LAU_DATE", length = 30)
	public String getLauDate() {
		return lauDate;
	}

	public void setNums(BigDecimal nums) {
		this.nums = nums;
	}

	@Column(name = "NUMS")
	public BigDecimal getNums() {
		return nums;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", length = 500)
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

	@Column(name = "DEL_FLG", length = 1, nullable = false)
	public String getDelFlg() {
		return delFlg;
	}

	public String toString() {
		return "StockMoveItem [pkId=" + pkId + ",client=" + client
				+ ",prestoCode=" + prestoCode + ",prestoItemCode="
				+ prestoItemCode + ",batchCode=" + batchCode + ",spName="
				+ spName + ",prodCode=" + prodCode + ",qaTestsQualified="
				+ qaTestsQualified + ",isEmptyBracket=" + isEmptyBracket
				+ ",mosType=" + mosType + ",firstCatg=" + firstCatg
				+ ",secCatg=" + secCatg + ",facProdCode=" + facProdCode
				+ ",retPrice=" + retPrice + ",goldType=" + goldType
				+ ",goldColor=" + goldColor + ",material=" + material
				+ ",clarityGWt=" + clarityGWt + ",weight=" + weight + ",size="
				+ size + ",goldWastage=" + goldWastage + ",inclWastageGWt="
				+ inclWastageGWt + ",goldPx=" + goldPx + ",facGPx=" + facGPx
				+ ",cmpyGPx=" + cmpyGPx + ",facAccyPx=" + facAccyPx
				+ ",comAccyPx=" + comAccyPx + ",facStPx=" + facStPx
				+ ",comStPx=" + comStPx + ",initBandCost=" + initBandCost
				+ ",baseCost=" + baseCost + ",otherCost=" + otherCost
				+ ",facSettlePx=" + facSettlePx + ",cerType=" + cerType
				+ ",mainStCode=" + mainStCode + ",mainCerType=" + mainCerType
				+ ",mainCerCode=" + mainCerCode + ",cerNum=" + cerNum
				+ ",color=" + color + ",neat=" + neat + ",manStWt=" + manStWt
				+ ",ngtcGoods=" + ngtcGoods + ",ngtcTotQuality="
				+ ngtcTotQuality + ",ngtcGFineness=" + ngtcGFineness
				+ ",otherCerType=" + otherCerType + ",otherCerCode="
				+ otherCerCode + ",inlaiedScoop=" + inlaiedScoop
				+ ",manStInfo=" + manStInfo + ",asstStWt=" + asstStWt
				+ ",asstStInfo=" + asstStInfo + ",asstStAmt=" + asstStAmt
				+ ",lauDate=" + lauDate + ",nums=" + nums + ",remark=" + remark
				+ ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ "]";
	}

}
