package com.jmc.scm.stock.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * stone_uni_info:裸石单品属性表
 */
@Entity
@Table(name = "stone_uni_info")
public class StoneUniInfo implements Serializable {

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
	 * 批次号:批次号
	 */
	@PropertyDef(label = "批次号", description = "批次号:批次号")
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
	 * 零售价:零售价
	 */
	@PropertyDef(label = "零售价", description = "零售价:零售价")
	private BigDecimal retPrice;

	/**
	 * 重量:重量
	 */
	@PropertyDef(label = "重量", description = "重量:重量")
	private BigDecimal weight;

	/**
	 * 证书类型:证书类型
	 */
	@PropertyDef(label = "证书类型", description = "证书类型:证书类型")
	private String cerType;

	/**
	 * 证书号:证书号
	 */
	@PropertyDef(label = "证书号", description = "证书号:证书号")
	private String cerNum;

	/**
	 * 证书颜色:证书颜色
	 */
	@PropertyDef(label = "证书颜色", description = "证书颜色:证书颜色")
	private String cerColor;

	/**
	 * 证书净度:证书净度
	 */
	@PropertyDef(label = "证书净度", description = "证书净度:证书净度")
	private String cerNeat;

	/**
	 * 证书货品名称:证书货品名称
	 */
	@PropertyDef(label = "证书货品名称", description = "证书货品名称:证书货品名称")
	private String cerGoodName;

	/**
	 * 证书总质量:证书总质量
	 */
	@PropertyDef(label = "证书总质量", description = "证书总质量:证书总质量")
	private BigDecimal cerWet;

	/**
	 * 形状:形状
	 */
	@PropertyDef(label = "形状", description = "形状:形状")
	private String shape;

	/**
	 * 规格:规格
	 */
	@PropertyDef(label = "规格", description = "规格:规格")
	private String specs;

	/**
	 * 颜色:颜色
	 */
	@PropertyDef(label = "颜色", description = "颜色:颜色")
	private String color;

	/**
	 * 净度:净度
	 */
	@PropertyDef(label = "净度", description = "净度:净度")
	private String neat;

	/**
	 * 荧光:荧光
	 */
	@PropertyDef(label = "荧光", description = "荧光:荧光")
	private String fluo;

	/**
	 * 切工:切工
	 */
	@PropertyDef(label = "切工", description = "切工:切工")
	private String cut;

	/**
	 * 抛光:抛光
	 */
	@PropertyDef(label = "抛光", description = "抛光:抛光")
	private String polish;

	/**
	 * 对称:对称
	 */
	@PropertyDef(label = "对称", description = "对称:对称")
	private String symmetry;

	/**
	 * 级别:级别
	 */
	@PropertyDef(label = "级别", description = "级别:级别")
	private BigDecimal level;

	/**
	 * 最小直径(宽):最小直径(宽)
	 */
	@PropertyDef(label = "最小直径(宽)", description = "最小直径(宽):最小直径(宽)")
	private BigDecimal minDia;

	/**
	 * 最大直径(长):最大直径(长)
	 */
	@PropertyDef(label = "最大直径(长)", description = "最大直径(长):最大直径(长)")
	private BigDecimal maxDia;

	/**
	 * 高:高
	 */
	@PropertyDef(label = "高", description = "高:高")
	private BigDecimal height;

	/**
	 * 台宽比:台宽比
	 */
	@PropertyDef(label = "台宽比", description = "台宽比:台宽比")
	private BigDecimal tabScale;

	/**
	 * 全深比:全深比
	 */
	@PropertyDef(label = "全深比", description = "全深比:全深比")
	private BigDecimal depthScale;

	/**
	 * GEMEX证书号:GEMEX证书号
	 */
	@PropertyDef(label = "GEMEX证书号", description = "GEMEX证书号:GEMEX证书号")
	private String gemexCer;

	/**
	 * GEMEX证书价格等级:GEMEX证书价格等级
	 */
	@PropertyDef(label = "GEMEX证书价格等级", description = "GEMEX证书价格等级:GEMEX证书价格等级")
	private String gemexCerPri;

	/**
	 * GEMEX亮度:GEMEX亮度
	 */
	@PropertyDef(label = "GEMEX亮度", description = "GEMEX亮度:GEMEX亮度")
	private String gemexLight;

	/**
	 * GEMEX火彩:GEMEX火彩
	 */
	@PropertyDef(label = "GEMEX火彩", description = "GEMEX火彩:GEMEX火彩")
	private String gemexFlame;

	/**
	 * GEMEX闪光:GEMEX闪光
	 */
	@PropertyDef(label = "GEMEX闪光", description = "GEMEX闪光:GEMEX闪光")
	private String gemexFlash;

	/**
	 * GEMEX证书费:GEMEX证书费
	 */
	@PropertyDef(label = "GEMEX证书费", description = "GEMEX证书费:GEMEX证书费")
	private BigDecimal gemexCost;

	/**
	 * 采购价格（总金额）:采购价格（总金额）
	 */
	@PropertyDef(label = "采购价格（总金额）", description = "采购价格（总金额）:采购价格（总金额）")
	private BigDecimal purPrice;

	/**
	 * 国际报价:国际报价
	 */
	@PropertyDef(label = "国际报价", description = "国际报价:国际报价")
	private BigDecimal isoPrice;

	/**
	 * 采购克拉单价:采购克拉单价
	 */
	@PropertyDef(label = "采购克拉单价", description = "采购克拉单价:采购克拉单价")
	private BigDecimal purCaratPrice;

	/**
	 * 参考单据号:参考单据号
	 */
	@PropertyDef(label = "参考单据号", description = "参考单据号:参考单据号")
	private String refOrderNum;

	/**
	 * 参考单行号:参考单行号
	 */
	@PropertyDef(label = "参考单行号", description = "参考单行号:参考单行号")
	private String refLineNum;

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
	 * 状态:状态
	 */
	@PropertyDef(label = "状态", description = "状态:状态")
	private String status;

	/**
	 * 备注:备注
	 */
	@PropertyDef(label = "备注", description = "备注:备注")
	private String remark;

	/**
	 * 锁定客户号:锁定客户号
	 */
	@PropertyDef(label = "锁定客户号", description = "锁定客户号:锁定客户号")
	private String lckClientCode;

	/**
	 * 锁定日期:锁定日期
	 */
	@PropertyDef(label = "锁定日期", description = "锁定日期:锁定日期")
	private Date lckDate;

	/**
	 * 锁定备注:锁定备注
	 */
	@PropertyDef(label = "锁定备注", description = "锁定备注:锁定备注")
	private String lckRem;

	/**
	 * 单品数量:单品数量
	 */
	@PropertyDef(label = "单品数量", description = "单品数量:单品数量")
	private BigDecimal qty;

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
	 * 扩展字段6:扩展字段6
	 */
	@PropertyDef(label = "扩展字段6", description = "扩展字段6:扩展字段6")
	private String attr6;

	/**
	 * 扩展字段7:扩展字段7
	 */
	@PropertyDef(label = "扩展字段7", description = "扩展字段7:扩展字段7")
	private String attr7;

	/**
	 * 扩展字段8:扩展字段8
	 */
	@PropertyDef(label = "扩展字段8", description = "扩展字段8:扩展字段8")
	private String attr8;

	/**
	 * 扩展字段9:扩展字段9
	 */
	@PropertyDef(label = "扩展字段9", description = "扩展字段9:扩展字段9")
	private String attr9;

	/**
	 * 扩展字段10:扩展字段10
	 */
	@PropertyDef(label = "扩展字段10", description = "扩展字段10:扩展字段10")
	private String attr10;

	/**
	 * 删除标记:删除标记
	 */
	@PropertyDef(label = "删除标记", description = "删除标记:删除标记")
	private String delFlg;

	public StoneUniInfo() {
		super();
	}

	public StoneUniInfo(String pkId, String client, String batchCode,
			String spName, String prodCode, String firstCatg, String secCatg,
			BigDecimal retPrice, BigDecimal weight, String cerType,
			String cerNum, String cerColor, String cerNeat, String cerGoodName,
			BigDecimal cerWet, String shape, String specs, String color,
			String neat, String fluo, String cut, String polish,
			String symmetry, BigDecimal level, BigDecimal minDia,
			BigDecimal maxDia, BigDecimal height, BigDecimal tabScale,
			BigDecimal depthScale, String gemexCer, String gemexCerPri,
			String gemexLight, String gemexFlame, String gemexFlash,
			BigDecimal gemexCost, BigDecimal purPrice, BigDecimal isoPrice,
			BigDecimal purCaratPrice, String refOrderNum, String refLineNum,
			String supCode, String supName, String status, String remark,
			String lckClientCode, Date lckDate, String lckRem, BigDecimal qty,
			String createdBy, Date createdD, String updatedBy, Date updatedD,
			String attr1, String attr2, String attr3, String attr4,
			String attr5, String attr6, String attr7, String attr8,
			String attr9, String attr10, String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.batchCode = batchCode;
		this.spName = spName;
		this.prodCode = prodCode;
		this.firstCatg = firstCatg;
		this.secCatg = secCatg;
		this.retPrice = retPrice;
		this.weight = weight;
		this.cerType = cerType;
		this.cerNum = cerNum;
		this.cerColor = cerColor;
		this.cerNeat = cerNeat;
		this.cerGoodName = cerGoodName;
		this.cerWet = cerWet;
		this.shape = shape;
		this.specs = specs;
		this.color = color;
		this.neat = neat;
		this.fluo = fluo;
		this.cut = cut;
		this.polish = polish;
		this.symmetry = symmetry;
		this.level = level;
		this.minDia = minDia;
		this.maxDia = maxDia;
		this.height = height;
		this.tabScale = tabScale;
		this.depthScale = depthScale;
		this.gemexCer = gemexCer;
		this.gemexCerPri = gemexCerPri;
		this.gemexLight = gemexLight;
		this.gemexFlame = gemexFlame;
		this.gemexFlash = gemexFlash;
		this.gemexCost = gemexCost;
		this.purPrice = purPrice;
		this.isoPrice = isoPrice;
		this.purCaratPrice = purCaratPrice;
		this.refOrderNum = refOrderNum;
		this.refLineNum = refLineNum;
		this.supCode = supCode;
		this.supName = supName;
		this.status = status;
		this.remark = remark;
		this.lckClientCode = lckClientCode;
		this.lckDate = lckDate;
		this.lckRem = lckRem;
		this.qty = qty;
		this.createdBy = createdBy;
		this.createdD = createdD;
		this.updatedBy = updatedBy;
		this.updatedD = updatedD;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		this.attr4 = attr4;
		this.attr5 = attr5;
		this.attr6 = attr6;
		this.attr7 = attr7;
		this.attr8 = attr8;
		this.attr9 = attr9;
		this.attr10 = attr10;
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

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	@Column(name = "BATCH_CODE", length = 10, unique = true, nullable = false)
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

	public void setRetPrice(BigDecimal retPrice) {
		this.retPrice = retPrice;
	}

	@Column(name = "RET_PRICE")
	public BigDecimal getRetPrice() {
		return retPrice;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Column(name = "WEIGHT")
	public BigDecimal getWeight() {
		return weight;
	}

	public void setCerType(String cerType) {
		this.cerType = cerType;
	}

	@Column(name = "CER_TYPE", length = 10)
	public String getCerType() {
		return cerType;
	}

	public void setCerNum(String cerNum) {
		this.cerNum = cerNum;
	}

	@Column(name = "CER_NUM", length = 20)
	public String getCerNum() {
		return cerNum;
	}

	public void setCerColor(String cerColor) {
		this.cerColor = cerColor;
	}

	@Column(name = "CER_COLOR", length = 10)
	public String getCerColor() {
		return cerColor;
	}

	public void setCerNeat(String cerNeat) {
		this.cerNeat = cerNeat;
	}

	@Column(name = "CER_NEAT", length = 10)
	public String getCerNeat() {
		return cerNeat;
	}

	public void setCerGoodName(String cerGoodName) {
		this.cerGoodName = cerGoodName;
	}

	@Column(name = "CER_GOOD_NAME", length = 60)
	public String getCerGoodName() {
		return cerGoodName;
	}

	public void setCerWet(BigDecimal cerWet) {
		this.cerWet = cerWet;
	}

	@Column(name = "CER_WET")
	public BigDecimal getCerWet() {
		return cerWet;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}

	@Column(name = "SHAPE", length = 10)
	public String getShape() {
		return shape;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	@Column(name = "SPECS", length = 10)
	public String getSpecs() {
		return specs;
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

	public void setFluo(String fluo) {
		this.fluo = fluo;
	}

	@Column(name = "FLUO", length = 10)
	public String getFluo() {
		return fluo;
	}

	public void setCut(String cut) {
		this.cut = cut;
	}

	@Column(name = "CUT", length = 10)
	public String getCut() {
		return cut;
	}

	public void setPolish(String polish) {
		this.polish = polish;
	}

	@Column(name = "POLISH", length = 10)
	public String getPolish() {
		return polish;
	}

	public void setSymmetry(String symmetry) {
		this.symmetry = symmetry;
	}

	@Column(name = "SYMMETRY", length = 10)
	public String getSymmetry() {
		return symmetry;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	@Column(name = "LEVEL")
	public BigDecimal getLevel() {
		return level;
	}

	public void setMinDia(BigDecimal minDia) {
		this.minDia = minDia;
	}

	@Column(name = "MIN_DIA")
	public BigDecimal getMinDia() {
		return minDia;
	}

	public void setMaxDia(BigDecimal maxDia) {
		this.maxDia = maxDia;
	}

	@Column(name = "MAX_DIA")
	public BigDecimal getMaxDia() {
		return maxDia;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	@Column(name = "HEIGHT")
	public BigDecimal getHeight() {
		return height;
	}

	public void setTabScale(BigDecimal tabScale) {
		this.tabScale = tabScale;
	}

	@Column(name = "TAB_SCALE")
	public BigDecimal getTabScale() {
		return tabScale;
	}

	public void setDepthScale(BigDecimal depthScale) {
		this.depthScale = depthScale;
	}

	@Column(name = "DEPTH_SCALE")
	public BigDecimal getDepthScale() {
		return depthScale;
	}

	public void setGemexCer(String gemexCer) {
		this.gemexCer = gemexCer;
	}

	@Column(name = "GEMEX_CER", length = 20)
	public String getGemexCer() {
		return gemexCer;
	}

	public void setGemexCerPri(String gemexCerPri) {
		this.gemexCerPri = gemexCerPri;
	}

	@Column(name = "GEMEX_CER_PRI", length = 10)
	public String getGemexCerPri() {
		return gemexCerPri;
	}

	public void setGemexLight(String gemexLight) {
		this.gemexLight = gemexLight;
	}

	@Column(name = "GEMEX_LIGHT", length = 10)
	public String getGemexLight() {
		return gemexLight;
	}

	public void setGemexFlame(String gemexFlame) {
		this.gemexFlame = gemexFlame;
	}

	@Column(name = "GEMEX_FLAME", length = 10)
	public String getGemexFlame() {
		return gemexFlame;
	}

	public void setGemexFlash(String gemexFlash) {
		this.gemexFlash = gemexFlash;
	}

	@Column(name = "GEMEX_FLASH", length = 10)
	public String getGemexFlash() {
		return gemexFlash;
	}

	public void setGemexCost(BigDecimal gemexCost) {
		this.gemexCost = gemexCost;
	}

	@Column(name = "GEMEX_COST")
	public BigDecimal getGemexCost() {
		return gemexCost;
	}

	public void setPurPrice(BigDecimal purPrice) {
		this.purPrice = purPrice;
	}

	@Column(name = "PUR_PRICE")
	public BigDecimal getPurPrice() {
		return purPrice;
	}

	public void setIsoPrice(BigDecimal isoPrice) {
		this.isoPrice = isoPrice;
	}

	@Column(name = "ISO_PRICE")
	public BigDecimal getIsoPrice() {
		return isoPrice;
	}

	public void setPurCaratPrice(BigDecimal purCaratPrice) {
		this.purCaratPrice = purCaratPrice;
	}

	@Column(name = "PUR_CARAT_PRICE")
	public BigDecimal getPurCaratPrice() {
		return purCaratPrice;
	}

	public void setRefOrderNum(String refOrderNum) {
		this.refOrderNum = refOrderNum;
	}

	@Column(name = "REF_ORDER_NUM", length = 20)
	public String getRefOrderNum() {
		return refOrderNum;
	}

	public void setRefLineNum(String refLineNum) {
		this.refLineNum = refLineNum;
	}

	@Column(name = "REF_LINE_NUM", length = 10)
	public String getRefLineNum() {
		return refLineNum;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	@Column(name = "SUP_CODE", length = 20)
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

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", length = 255)
	public String getRemark() {
		return remark;
	}

	public void setLckClientCode(String lckClientCode) {
		this.lckClientCode = lckClientCode;
	}

	@Column(name = "LCK_CLIENT_CODE", length = 10)
	public String getLckClientCode() {
		return lckClientCode;
	}

	public void setLckDate(Date lckDate) {
		this.lckDate = lckDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LCK_DATE")
	public Date getLckDate() {
		return lckDate;
	}

	public void setLckRem(String lckRem) {
		this.lckRem = lckRem;
	}

	@Column(name = "LCK_REM", length = 40)
	public String getLckRem() {
		return lckRem;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	@Column(name = "QTY")
	public BigDecimal getQty() {
		return qty;
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

	public void setAttr6(String attr6) {
		this.attr6 = attr6;
	}

	@Column(name = "ATTR6", length = 255)
	public String getAttr6() {
		return attr6;
	}

	public void setAttr7(String attr7) {
		this.attr7 = attr7;
	}

	@Column(name = "ATTR7", length = 255)
	public String getAttr7() {
		return attr7;
	}

	public void setAttr8(String attr8) {
		this.attr8 = attr8;
	}

	@Column(name = "ATTR8", length = 255)
	public String getAttr8() {
		return attr8;
	}

	public void setAttr9(String attr9) {
		this.attr9 = attr9;
	}

	@Column(name = "ATTR9", length = 255)
	public String getAttr9() {
		return attr9;
	}

	public void setAttr10(String attr10) {
		this.attr10 = attr10;
	}

	@Column(name = "ATTR10", length = 255)
	public String getAttr10() {
		return attr10;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	@Column(name = "DEL_FLG", length = 1, nullable = false)
	public String getDelFlg() {
		return delFlg;
	}

	public String toString() {
		return "StoneUniInfo [pkId=" + pkId + ",client=" + client
				+ ",batchCode=" + batchCode + ",spName=" + spName
				+ ",prodCode=" + prodCode + ",firstCatg=" + firstCatg
				+ ",secCatg=" + secCatg + ",retPrice=" + retPrice + ",weight="
				+ weight + ",cerType=" + cerType + ",cerNum=" + cerNum
				+ ",cerColor=" + cerColor + ",cerNeat=" + cerNeat
				+ ",cerGoodName=" + cerGoodName + ",cerWet=" + cerWet
				+ ",shape=" + shape + ",specs=" + specs + ",color=" + color
				+ ",neat=" + neat + ",fluo=" + fluo + ",cut=" + cut
				+ ",polish=" + polish + ",symmetry=" + symmetry + ",level="
				+ level + ",minDia=" + minDia + ",maxDia=" + maxDia
				+ ",height=" + height + ",tabScale=" + tabScale
				+ ",depthScale=" + depthScale + ",gemexCer=" + gemexCer
				+ ",gemexCerPri=" + gemexCerPri + ",gemexLight=" + gemexLight
				+ ",gemexFlame=" + gemexFlame + ",gemexFlash=" + gemexFlash
				+ ",gemexCost=" + gemexCost + ",purPrice=" + purPrice
				+ ",isoPrice=" + isoPrice + ",purCaratPrice=" + purCaratPrice
				+ ",refOrderNum=" + refOrderNum + ",refLineNum=" + refLineNum
				+ ",supCode=" + supCode + ",supName=" + supName + ",status="
				+ status + ",remark=" + remark + ",lckClientCode="
				+ lckClientCode + ",lckDate=" + lckDate + ",lckRem=" + lckRem
				+ ",qty=" + qty + ",createdBy=" + createdBy + ",createdD="
				+ createdD + ",updatedBy=" + updatedBy + ",updatedD="
				+ updatedD + ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3="
				+ attr3 + ",attr4=" + attr4 + ",attr5=" + attr5 + ",attr6="
				+ attr6 + ",attr7=" + attr7 + ",attr8=" + attr8 + ",attr9="
				+ attr9 + ",attr10=" + attr10 + ",delFlg=" + delFlg + "]";
	}

}
