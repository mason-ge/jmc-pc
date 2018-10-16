package com.jmc.scm.baseData.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * sale_po_main:SALE_PO_MAIN
 */
@Entity
@Table(name = "sale_po_main")
public class SalePoMain implements Serializable {

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
	 * 销售订单号:销售订单号
	 */
	@PropertyDef(label = "销售订单号", description = "销售订单号:销售订单号")
	private String salePoCode;

	/**
	 * 销售订单类型:销售订单类型
	 */
	@PropertyDef(label = "销售订单类型", description = "销售订单类型:销售订单类型")
	private String salePoType;

	/**
	 * 状态:状态
	 */
	@PropertyDef(label = "状态", description = "状态:状态")
	private String status;

	/**
	 * 发货日期:发货日期
	 */
	@PropertyDef(label = "发货日期", description = "发货日期:发货日期")
	private Date deliDate;

	/**
	 * 客户编码:客户编码
	 */
	@PropertyDef(label = "客户编码", description = "客户编码:客户编码")
	private String cltCode;

	/**
	 * 客户名称:客户名称
	 */
	@PropertyDef(label = "客户名称", description = "客户名称:客户名称")
	private String cltName;

	/**
	 * 客户地址:客户地址
	 */
	@PropertyDef(label = "客户地址", description = "客户地址:客户地址")
	private String cltAdd;

	/**
	 * 联系电话:联系电话
	 */
	@PropertyDef(label = "联系电话", description = "联系电话:联系电话")
	private BigDecimal phone;

	/**
	 * 付款条件:付款条件
	 */
	@PropertyDef(label = "付款条件", description = "付款条件:付款条件")
	private String payCon;

	/**
	 * 付款方式:付款方式
	 */
	@PropertyDef(label = "付款方式", description = "付款方式:付款方式")
	private String payment;

	/**
	 * 折扣说明:折扣说明
	 */
	@PropertyDef(label = "折扣说明", description = "折扣说明:折扣说明")
	private String disctCmmt;

	/**
	 * 快递公司:快递公司
	 */
	@PropertyDef(label = "快递公司", description = "快递公司:快递公司")
	private String expressCmpy;

	/**
	 * 快递单号:快递单号
	 */
	@PropertyDef(label = "快递单号", description = "快递单号:快递单号")
	private String expressCode;

	/**
	 * 备注:备注
	 */
	@PropertyDef(label = "备注", description = "备注:备注")
	private String remark;

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
	 * 取消原因:取消原因
	 */
	@PropertyDef(label = "取消原因", description = "取消原因:取消原因")
	private String cancelReason;

	public SalePoMain() {
		super();
	}

	public SalePoMain(String pkId, String client, String salePoCode,
			String salePoType, String status, Date deliDate, String cltCode,
			String cltName, String cltAdd, BigDecimal phone, String payCon,
			String payment, String disctCmmt, String expressCmpy,
			String expressCode, String remark, String submitBy, Date submitD,
			String createdBy, Date createdD, String updatedBy, Date updatedD,
			String attr1, String attr2, String attr3, String attr4,
			String attr5, String delFlg, String cancelReason) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.salePoCode = salePoCode;
		this.salePoType = salePoType;
		this.status = status;
		this.deliDate = deliDate;
		this.cltCode = cltCode;
		this.cltName = cltName;
		this.cltAdd = cltAdd;
		this.phone = phone;
		this.payCon = payCon;
		this.payment = payment;
		this.disctCmmt = disctCmmt;
		this.expressCmpy = expressCmpy;
		this.expressCode = expressCode;
		this.remark = remark;
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
		this.cancelReason = cancelReason;
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

	public void setSalePoCode(String salePoCode) {
		this.salePoCode = salePoCode;
	}

	@Column(name = "SALE_PO_CODE", length = 30, unique = true, nullable = false)
	public String getSalePoCode() {
		return salePoCode;
	}

	public void setSalePoType(String salePoType) {
		this.salePoType = salePoType;
	}

	@Column(name = "SALE_PO_TYPE", length = 5, nullable = false)
	public String getSalePoType() {
		return salePoType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS", length = 2, nullable = false)
	public String getStatus() {
		return status;
	}

	public void setDeliDate(Date deliDate) {
		this.deliDate = deliDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DELI_DATE")
	public Date getDeliDate() {
		return deliDate;
	}

	public void setCltCode(String cltCode) {
		this.cltCode = cltCode;
	}

	@Column(name = "CLT_CODE", length = 10)
	public String getCltCode() {
		return cltCode;
	}

	public void setCltName(String cltName) {
		this.cltName = cltName;
	}

	@Column(name = "CLT_NAME", length = 70)
	public String getCltName() {
		return cltName;
	}

	public void setCltAdd(String cltAdd) {
		this.cltAdd = cltAdd;
	}

	@Column(name = "CLT_ADD", length = 80)
	public String getCltAdd() {
		return cltAdd;
	}

	public void setPhone(BigDecimal phone) {
		this.phone = phone;
	}

	@Column(name = "PHONE")
	public BigDecimal getPhone() {
		return phone;
	}

	public void setPayCon(String payCon) {
		this.payCon = payCon;
	}

	@Column(name = "PAY_CON", length = 10)
	public String getPayCon() {
		return payCon;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Column(name = "PAYMENT", length = 10)
	public String getPayment() {
		return payment;
	}

	public void setDisctCmmt(String disctCmmt) {
		this.disctCmmt = disctCmmt;
	}

	@Column(name = "DISCT_CMMT", length = 100)
	public String getDisctCmmt() {
		return disctCmmt;
	}

	public void setExpressCmpy(String expressCmpy) {
		this.expressCmpy = expressCmpy;
	}

	@Column(name = "EXPRESS_CMPY", length = 10)
	public String getExpressCmpy() {
		return expressCmpy;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	@Column(name = "EXPRESS_CODE", length = 60)
	public String getExpressCode() {
		return expressCode;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "REMARK", length = 255)
	public String getRemark() {
		return remark;
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

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@Column(name = "CANCEL_REASON", length = 255)
	public String getCancelReason() {
		return cancelReason;
	}

	public String toString() {
		return "SalePoMain [pkId=" + pkId + ",client=" + client
				+ ",salePoCode=" + salePoCode + ",salePoType=" + salePoType
				+ ",status=" + status + ",deliDate=" + deliDate + ",cltCode="
				+ cltCode + ",cltName=" + cltName + ",cltAdd=" + cltAdd
				+ ",phone=" + phone + ",payCon=" + payCon + ",payment="
				+ payment + ",disctCmmt=" + disctCmmt + ",expressCmpy="
				+ expressCmpy + ",expressCode=" + expressCode + ",remark="
				+ remark + ",submitBy=" + submitBy + ",submitD=" + submitD
				+ ",createdBy=" + createdBy + ",createdD=" + createdD
				+ ",updatedBy=" + updatedBy + ",updatedD=" + updatedD
				+ ",attr1=" + attr1 + ",attr2=" + attr2 + ",attr3=" + attr3
				+ ",attr4=" + attr4 + ",attr5=" + attr5 + ",delFlg=" + delFlg
				+ ",cancelReason=" + cancelReason + "]";
	}

}
