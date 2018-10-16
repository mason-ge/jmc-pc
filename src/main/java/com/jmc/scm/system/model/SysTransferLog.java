package com.jmc.scm.system.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * sys_transfer_log:传输接口日志表
 */
@Entity
@Table(name = "sys_transfer_log")
public class SysTransferLog implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键:主键
	 */
	@PropertyDef(label = "主键", description = "主键:主键")
	private String pkId;

	/**
	 * 传输编码:传输编码
	 */
	@PropertyDef(label = "传输编码", description = "传输编码:传输编码")
	private String transferCode;

	/**
	 * 传输名称:传输名称
	 */
	@PropertyDef(label = "传输名称", description = "传输名称:传输名称")
	private String transferName;

	/**
	 * 传输类型（S：发送，R：接收）:传输类型（S：发送，R：接收）
	 */
	@PropertyDef(label = "传输类型（S：发送，R：接收）", description = "传输类型（S：发送，R：接收）:传输类型（S：发送，R：接收）")
	private String transferType;

	/**
	 * 传输结果（E：失败，S：成功）:传输结果（E：失败，S：成功）
	 */
	@PropertyDef(label = "传输结果（E：失败，S：成功）", description = "传输结果（E：失败，S：成功）:传输结果（E：失败，S：成功）")
	private String transferFlag;

	/**
	 * 传输数据:传输数据
	 */
	@PropertyDef(label = "传输数据", description = "传输数据:传输数据")
	private byte[] transferData;

	/**
	 * 传输人:传输人
	 */
	@PropertyDef(label = "传输人", description = "传输人:传输人")
	private String transmitter;

	/**
	 * 传输时间:传输时间
	 */
	@PropertyDef(label = "传输时间", description = "传输时间:传输时间")
	private Date transferDate;

	/**
	 * 接收人:接收人
	 */
	@PropertyDef(label = "接收人", description = "接收人:接收人")
	private String receiver;

	/**
	 * 接收数据:接收数据
	 */
	@PropertyDef(label = "接收数据", description = "接收数据:接收数据")
	private byte[] returnData;

	/**
	 * 接收时间:接收时间
	 */
	@PropertyDef(label = "接收时间", description = "接收时间:接收时间")
	private Date returnDate;

	/**
	 * 错误描述:错误描述
	 */
	@PropertyDef(label = "错误描述", description = "错误描述:错误描述")
	private String errorMessage;

	public SysTransferLog() {
		super();
	}

	public SysTransferLog(String pkId, String transferCode,
			String transferName, String transferType, String transferFlag,
			byte[] transferData, String transmitter, Date transferDate,
			String receiver, byte[] returnData, Date returnDate,
			String errorMessage) {
		super();
		this.pkId = pkId;
		this.transferCode = transferCode;
		this.transferName = transferName;
		this.transferType = transferType;
		this.transferFlag = transferFlag;
		this.transferData = transferData;
		this.transmitter = transmitter;
		this.transferDate = transferDate;
		this.receiver = receiver;
		this.returnData = returnData;
		this.returnDate = returnDate;
		this.errorMessage = errorMessage;
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

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	@Column(name = "TRANSFER_CODE", length = 20, nullable = false)
	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}

	@Column(name = "TRANSFER_NAME", length = 20, nullable = false)
	public String getTransferName() {
		return transferName;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	@Column(name = "TRANSFER_TYPE", length = 1)
	public String getTransferType() {
		return transferType;
	}

	public void setTransferFlag(String transferFlag) {
		this.transferFlag = transferFlag;
	}

	@Column(name = "TRANSFER_FLAG", length = 1)
	public String getTransferFlag() {
		return transferFlag;
	}

	public void setTransferData(byte[] transferData) {
		this.transferData = transferData;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "TRANSFER_DATA", columnDefinition = "BLOB")
	public byte[] getTransferData() {
		return transferData;
	}

	public void setTransmitter(String transmitter) {
		this.transmitter = transmitter;
	}

	@Column(name = "TRANSMITTER", length = 60)
	public String getTransmitter() {
		return transmitter;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFER_DATE")
	public Date getTransferDate() {
		return transferDate;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "RECEIVER", length = 60)
	public String getReceiver() {
		return receiver;
	}

	public void setReturnData(byte[] returnData) {
		this.returnData = returnData;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "RETURN_DATA", columnDefinition = "BLOB")
	public byte[] getReturnData() {
		return returnData;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RETURN_DATE")
	public Date getReturnDate() {
		return returnDate;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Column(name = "ERROR_MESSAGE", length = 2000)
	public String getErrorMessage() {
		return errorMessage;
	}

	public String toString() {
		return "AlloveTransferLog [pkId=" + pkId + ",transferCode="
				+ transferCode + ",transferName=" + transferName
				+ ",transferType=" + transferType + ",transferFlag="
				+ transferFlag + ",transferData=" + transferData
				+ ",transmitter=" + transmitter + ",transferDate="
				+ transferDate + ",receiver=" + receiver + ",returnData="
				+ returnData + ",returnDate=" + returnDate + ",errorMessage="
				+ errorMessage + "]";
	}

}
