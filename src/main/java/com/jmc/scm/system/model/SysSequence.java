package com.jmc.scm.system.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * sys_sequence:业务主键序列表
 */
@Entity
@Table(name = "sys_sequence")
public class SysSequence implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键:主键
	 */
	@PropertyDef(label = "主键", description = "主键:主键")
	private String pkId;

	/**
	 * 序列号名称:序列号名称
	 */
	@PropertyDef(label = "序列号名称", description = "序列号名称:序列号名称")
	private String sequenceName;

	/**
	 * 业务主键序列表达式:业务主键序列表达式
	 */
	@PropertyDef(label = "业务主键序列表达式", description = "业务主键序列表达式:业务主键序列表达式")
	private String sequenceExpression;

	/**
	 * 当前序列号:当前序列号
	 */
	@PropertyDef(label = "当前序列号", description = "当前序列号:当前序列号")
	private BigDecimal serialNo;

	/**
	 * 序列号长度:序列号长度
	 */
	@PropertyDef(label = "序列号长度", description = "序列号长度:序列号长度")
	private BigDecimal serialLength;

	/**
	 * 序列号长度:序列号最大值
	 */
	@PropertyDef(label = "序列号长度", description = "序列号长度:序列号最大值")
	private BigDecimal serialMaxValue;

	/**
	 * 格式化后的业务主键编号前缀:格式化后的业务主键编号前缀
	 */
	@PropertyDef(label = "格式化后的业务主键编号前缀", description = "格式化后的业务主键编号前缀:格式化后的业务主键编号前缀")
	private String expressionFormat;

	public SysSequence() {
		super();
	}

	public SysSequence(String pkId, String sequenceName,
			String sequenceExpression, BigDecimal serialNo,
			BigDecimal serialLength, BigDecimal serialMaxValue,
			String expressionFormat) {
		super();
		this.pkId = pkId;
		this.sequenceName = sequenceName;
		this.sequenceExpression = sequenceExpression;
		this.serialNo = serialNo;
		this.serialLength = serialLength;
		this.serialMaxValue = serialMaxValue;
		this.expressionFormat = expressionFormat;
	}

	public void setPkId(String pkId) {
		this.pkId = pkId;
	}

	@Id
	@Column(name = "PK_ID", length = 36, nullable = false)
	public String getPkId() {
		return pkId;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

	@Column(name = "SEQUENCE_NAME", length = 50, unique = true, nullable = false)
	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceExpression(String sequenceExpression) {
		this.sequenceExpression = sequenceExpression;
	}

	@Column(name = "SEQUENCE_EXPRESSION", length = 50, nullable = false)
	public String getSequenceExpression() {
		return sequenceExpression;
	}

	public void setSerialNo(BigDecimal serialNo) {
		this.serialNo = serialNo;
	}

	@Column(name = "SERIAL_NO")
	public BigDecimal getSerialNo() {
		return serialNo;
	}

	public void setSerialLength(BigDecimal serialLength) {
		this.serialLength = serialLength;
	}

	@Column(name = "SERIAL_LENGTH", nullable = false)
	public BigDecimal getSerialLength() {
		return serialLength;
	}

	public void setSerialMaxValue(BigDecimal serialMaxValue) {
		this.serialMaxValue = serialMaxValue;
	}

	@Column(name = "SERIAL_MAX_VALUE", nullable = false)
	public BigDecimal getSerialMaxValue() {
		return serialMaxValue;
	}

	public void setExpressionFormat(String expressionFormat) {
		this.expressionFormat = expressionFormat;
	}

	@Column(name = "EXPRESSION_FORMAT", length = 50)
	public String getExpressionFormat() {
		return expressionFormat;
	}

	public String toString() {
		return "AlloveSequence [pkId=" + pkId + ",sequenceName=" + sequenceName
				+ ",sequenceExpression=" + sequenceExpression + ",serialNo="
				+ serialNo + ",serialLength=" + serialLength
				+ ",serialMaxValue=" + serialMaxValue + ",expressionFormat="
				+ expressionFormat + "]";
	}

}
