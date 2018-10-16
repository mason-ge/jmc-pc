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
 * stock_move_main:入库主表
 */
@Entity
@Table(name = "stock_move_main")
public class StockMoveMain implements Serializable {

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
	 * 状态:状态
	 */
	@PropertyDef(label = "状态", description = "状态:状态")
	private String status;

	/**
	 * 库存地点:库存地点
	 */
	@PropertyDef(label = "库存地点", description = "库存地点:库存地点")
	private String stockLocale;

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
	 * 参考单据号:参考单据号
	 */
	@PropertyDef(label = "参考单据号", description = "参考单据号:参考单据号")
	private String refOrderNum;

	/**
	 * 采购订单号:采购订单号
	 */
	@PropertyDef(label = "采购订单号", description = "采购订单号:采购订单号")
	private String poCode;

	/**
	 * 备注:备注
	 */
	@PropertyDef(label = "备注", description = "备注:备注")
	private String remark;

	/**
	 * 大类:大类
	 */
	@PropertyDef(label = "大类", description = "大类:大类")
	private String biggerClass;

	/**
	 * 总件数:总件数
	 */
	@PropertyDef(label = "总件数", description = "总件数:总件数")
	private BigDecimal totAmt;

	/**
	 * 总金额:总金额
	 */
	@PropertyDef(label = "总金额", description = "总金额:总金额")
	private BigDecimal totPrice;

	/**
	 * 入库日期:入库日期
	 */
	@PropertyDef(label = "入库日期", description = "入库日期:入库日期")
	private Date recDate;

	/**
	 * 提交人:提交人
	 */
	@PropertyDef(label = "提交人", description = "提交人:提交人")
	private String submitBy;

	/**
	 * 提交时间:提交时间
	 */
	@PropertyDef(label = "提交时间", description = "提交时间:提交时间")
	private Date submitD;

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

	/**
	 * 借货:借货
	 */
	@PropertyDef(label = "借货", description = "借货:借货")
	private String loan;

	public StockMoveMain() {
		super();
	}

	public StockMoveMain(String pkId, String client, String prestoCode,
			String status, String stockLocale, String supCode, String supName,
			String refOrderNum, String poCode, String remark,
			String biggerClass, BigDecimal totAmt, BigDecimal totPrice,
			Date recDate, String submitBy, Date submitD, String createdBy,
			Date createdD, String updatedBy, Date updatedD, String attr1,
			String attr2, String attr3, String attr4, String attr5,
			String delFlg, String loan) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.prestoCode = prestoCode;
		this.status = status;
		this.stockLocale = stockLocale;
		this.supCode = supCode;
		this.supName = supName;
		this.refOrderNum = refOrderNum;
		this.poCode = poCode;
		this.remark = remark;
		this.biggerClass = biggerClass;
		this.totAmt = totAmt;
		this.totPrice = totPrice;
		this.recDate = recDate;
		this.submitBy = submitBy;
		this.submitD = submitD;
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
		this.loan = loan;
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

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS", length = 2)
	public String getStatus() {
		return status;
	}

	public void setStockLocale(String stockLocale) {
		this.stockLocale = stockLocale;
	}

	@Column(name = "STOCK_LOCALE", length = 30)
	public String getStockLocale() {
		return stockLocale;
	}

	public void setSupCode(String supCode) {
		this.supCode = supCode;
	}

	@Column(name = "SUP_CODE", length = 20, nullable = false)
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

	public void setRefOrderNum(String refOrderNum) {
		this.refOrderNum = refOrderNum;
	}

	@Column(name = "REF_ORDER_NUM", length = 20)
	public String getRefOrderNum() {
		return refOrderNum;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	@Column(name = "PO_CODE", length = 20)
	public String getPoCode() {
		return poCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", length = 500)
	public String getRemark() {
		return remark;
	}

	public void setBiggerClass(String biggerClass) {
		this.biggerClass = biggerClass;
	}

	@Column(name = "BIGGER_CLASS", length = 10)
	public String getBiggerClass() {
		return biggerClass;
	}

	public void setTotAmt(BigDecimal totAmt) {
		this.totAmt = totAmt;
	}

	@Column(name = "TOT_AMT")
	public BigDecimal getTotAmt() {
		return totAmt;
	}

	public void setTotPrice(BigDecimal totPrice) {
		this.totPrice = totPrice;
	}

	@Column(name = "TOT_PRICE")
	public BigDecimal getTotPrice() {
		return totPrice;
	}

	public void setRecDate(Date recDate) {
		this.recDate = recDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "REC_DATE")
	public Date getRecDate() {
		return recDate;
	}

	public void setSubmitBy(String submitBy) {
		this.submitBy = submitBy;
	}

	@Column(name = "SUBMIT_BY", length = 60)
	public String getSubmitBy() {
		return submitBy;
	}

	public void setSubmitD(Date submitD) {
		this.submitD = submitD;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SUBMIT_D")
	public Date getSubmitD() {
		return submitD;
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

	public void setLoan(String loan) {
		this.loan = loan;
	}

	@Column(name = "LOAN", length = 30)
	public String getLoan() {
		return loan;
	}

	public String toString() {
		return "StockMoveMain [pkId=" + pkId + ",client=" + client
				+ ",prestoCode=" + prestoCode + ",status=" + status
				+ ",stockLocale=" + stockLocale + ",supCode=" + supCode
				+ ",supName=" + supName + ",refOrderNum=" + refOrderNum
				+ ",poCode=" + poCode + ",remark=" + remark + ",biggerClass="
				+ biggerClass + ",totAmt=" + totAmt + ",totPrice=" + totPrice
				+ ",recDate=" + recDate + ",submitBy=" + submitBy + ",submitD="
				+ submitD + ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ ",loan=" + loan + "]";
	}

}
