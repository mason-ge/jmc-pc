package com.jmc.scm.stock.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import com.bstek.dorado.annotation.PropertyDef;

/**
 * stock_config:库存地点配置表
 */
@Entity
@Table(name = "stock_config")
public class StockConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * PK_ID:
	 */
	@PropertyDef(label = "PK_ID", description = "PK_ID:")
	private String pkId;

	/**
	 * CLIENT:
	 */
	@PropertyDef(label = "CLIENT", description = "CLIENT:")
	private String client;

	/**
	 * STOCK_LOCALE:
	 */
	@PropertyDef(label = "STOCK_LOCALE", description = "STOCK_LOCALE:")
	private String stockLocale;

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
	 * 删除标记:删除标记
	 */
	@PropertyDef(label = "删除标记", description = "删除标记:删除标记")
	private String delFlg;

	public StockConfig() {
		super();
	}

	public StockConfig(String pkId, String client, String stockLocale,
			String createdBy, Date createdD, String updatedBy, Date updatedD,
			String delFlg) {
		super();
		this.pkId = pkId;
		this.client = client;
		this.stockLocale = stockLocale;
		this.createdBy = createdBy;
		this.createdD = createdD;
		this.updatedBy = updatedBy;
		this.updatedD = updatedD;
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

	public void setStockLocale(String stockLocale) {
		this.stockLocale = stockLocale;
	}

	@Column(name = "STOCK_LOCALE", length = 30, unique = true)
	public String getStockLocale() {
		return stockLocale;
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

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	@Column(name = "DEL_FLG", length = 1, nullable = false)
	public String getDelFlg() {
		return delFlg;
	}

	public String toString() {
		return "StockConfig [pkId=" + pkId + ",client=" + client
				+ ",stockLocale=" + stockLocale + ",createdBy=" + createdBy
				+ ",createdD=" + createdD + ",updatedBy=" + updatedBy
				+ ",updatedD=" + updatedD + ",delFlg=" + delFlg + "]";
	}

}
