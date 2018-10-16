package com.jmc.scm.common;

/**
 * 系统公用枚举值
 * 
 * @author 三影塔
 * 
 */
public class CommonEnum {

	/**
	 * 接口传输系统
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum TransferClient {
		/**
		 * SAP ECC
		 */
		ECC("ecc"),
		/**
		 * SCM
		 */
		SCM("scm"),
		/**
		 * POS
		 */
		POS("pos");

		private String clientName;

		private TransferClient(String clientName) {
			this.clientName = clientName;
		}

		public String value() {
			return this.clientName;
		}
	}

	/**
	 * 接口传输类型
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum TransferType {
		/**
		 * 发送数据
		 */
		SEND("S"),
		/**
		 * 接收数据
		 */
		RECEIVE("R");

		private String transferType;

		private TransferType(String transferType) {
			this.transferType = transferType;
		}

		public String value() {
			return this.transferType;
		}
	}

	/**
	 * 接口传输结果标记
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum TransferFlag {
		/**
		 * 接收成功
		 */
		SUCCESS("S"),
		/**
		 * 接收失败
		 */
		FAILURE("E");

		private String transferFlag;

		private TransferFlag(String transferFlag) {
			this.transferFlag = transferFlag;
		}

		public String value() {
			return this.transferFlag;
		}
	}

	/**
	 * 接口传输名称，接口名枚举对应SCM名称
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum TransferName {
		/**
		 * 商品主数据
		 */
		PRODUCT("productService"),
		/**
		 * 供应商主数据
		 */
		SUPPLIER("supplierService"),
		/**
		 * 客户主数据
		 */
		CLIENT("clientService"),
		/**
		 * 码表
		 */
		ENUM_SIZE("enumSizeService"),
		/**
		 * 增量库存中间表
		 */
		INVTY_INCR_TEMP("invtyIncrTempService"),
		/**
		 * SAP汇率
		 */
		SYS_EXCG_RATE("sysExcgRateService"),
		/**
		 * SAP裸石折扣
		 */
		SYS_ST_DISCT("sysStDisctService"),
		/**
		 * SAP裸石价格
		 */
		PROD_ST_PX("prodStPxService"),
		/**
		 * 单品属性
		 */
		ST_PROPERTY("stPropertyService"),
		/**
		 * 非现货订单
		 */
		N_SPOT("nSpotService"),
		/**
		 * 全量库存
		 * */
		INVTY_FULL("invtyFullService"),
		/**
		 * 预入库异步接口018
		 * */
		PRE_STOCK("preStockService"),
		/**
		 * 指定单审核
		 * */
		INST_APPR("instApprService"),
		/**
		 * 指定单反审核
		 * */
		INST_DIS_APPR("instDisApprService");
		private String transferName;

		private TransferName(String transferName) {
			this.transferName = transferName;
		}

		public String value() {
			return this.transferName;
		}
	}

	/**
	 * 删除标记
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum DeletedFlag {
		/**
		 * 已删除
		 */
		YES("1"),
		/**
		 * 未删除
		 */
		NO("0");

		private String deletedFlag;

		private DeletedFlag(String deletedFlag) {
			this.deletedFlag = deletedFlag;
		}

		public String value() {
			return this.deletedFlag;
		}

	}

	/**
	 * 切工类型
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum CutProccessType {

		/**
		 * 普通钻石
		 */
		NORMAL("20102"),
		/**
		 * 十心十箭
		 */
		TEN("20101");

		private String type;

		private CutProccessType(String type) {
			this.type = type;
		}

		public String value() {
			return this.type;
		}
	}

	/**
	 * 用户操作类型
	 * 
	 * @author Mason_Ge
	 * 
	 */
	public enum UserActionType {
		/**
		 * 保存
		 */
		SAVE("save"),
		/**
		 * 提交
		 */
		SUBMIT("submit"),
		/**
		 * 反提交
		 */
		DIS_SUBMIT("disSubmit"),
		/**
		 * 审核
		 */
		APPROVE("appr"),
		/**
		 * 反审核
		 */
		DIS_APPROVE("disAppr"),
		/**
		 * 删除
		 */
		DEL("del"),
		/**
		 * 配石
		 */
		MATCH_STONE("match"),
		/**
		 * 删除恢复
		 */
		RE_DEL("reDel");
		private String action;

		private UserActionType(String action) {
			this.action = action;
		}

		public String value() {
			return this.action;
		}
	}

	/**
	 * 布产单类型
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum CpsType {
		/**
		 * 素金
		 */
		GOLD("Z001"),
		/**
		 * 配件
		 */
		PARTS("Z002"),
		/**
		 * 布产单
		 */
		CPS("Z003"),
		/**
		 * 裸石采购
		 */
		STONE("Z004"),
		/**
		 * 维修
		 */
		MAINTAIN("Z005");

		private String type;

		private CpsType(String type) {
			this.type = type;
		}

		public String value() {
			return this.type;
		}
	}

	/**
	 * 一般状态
	 * 
	 * @author mason
	 * 
	 */
	public enum CommonStatus {
		/**
		 * 已创建
		 */
		CREATED("10"),
		/**
		 * 已提交
		 */
		SUBMITED("20"),

		/**
		 * 已审核
		 */
		APPROVED("30"),
		/**
		 * 已删除
		 */
		DELETED("90");

		private String status;

		private CommonStatus(String status) {
			this.status = status;
		}

		public String value() {
			return this.status;
		}

	}

	/**
	 * 流程审批模版编码
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum WorkFlowModel {
		/**
		 * 裸石采购订单审批流程编码
		 */
		STONE_PO("WF00001");

		private String modelCode;

		private WorkFlowModel(String modelCode) {
			this.modelCode = modelCode;
		}

		public String value() {
			return this.modelCode;
		}
	}

	/**
	 * 流程操作
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum WorkFlowAction {
		/**
		 * 流程进行中
		 */
		PROCESSING("PROCESSING"),
		/**
		 * 流程节点审批通过
		 */
		APPROVED("APPROVED"),
		/**
		 * 流程节点审批驳回 （流程驳回）
		 */
		REJECTED("REJECTED"),
		/**
		 * 流程正常审批结束
		 */
		FINISHED("FINISHED");

		private String action;

		private WorkFlowAction(String action) {
			this.action = action;
		}

		public String value() {
			return this.action;
		}
	}

	/**
	 * 是否启用枚举值
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum EnabledFlag {
		/**
		 * 启用
		 */
		ENABLED("1"),
		/**
		 * 禁用
		 */
		DISABLED("0");

		private String flag;

		private EnabledFlag(String flag) {
			this.flag = flag;
		}

		public String value() {
			return this.flag;
		}
	}

	/**
	 * 金成色、金种类、金料编码对应关系
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum GoldFinenessGoldTypGoldCodeMapping {
		/**
		 * 18K白
		 */
		WHITE_18K("101", "10", "GA001"),
		/**
		 * 18K红
		 */
		RED_18K("102", "10", "GA001"),
		/**
		 * 18K黄
		 */
		YELLOW_18K("103", "10", "GA001"),
		/**
		 * 18K红白
		 */
		RED_WHITE_18K("104", "10", "GA001"),
		/**
		 * 18K黄白
		 */
		YELLOW_WHITE_18K("105", "10", "GA001"),
		/**
		 * Pt900
		 */
		PT_900("201", "30", "GP001"),
		/**
		 * Pt950
		 */
		PT_950("301", "20", "?"),
		/**
		 * Pt950铂钌
		 */
		PT950_RU("401", "40", "?");

		/**
		 * 金成色
		 */
		private String goldFineness;

		/**
		 * 金种类
		 */
		private String goldTyp;

		/**
		 * 金料编码
		 */
		private String goldCode;

		private GoldFinenessGoldTypGoldCodeMapping(String goldFineness,
				String goldTyp, String goldCode) {
			this.goldFineness = goldFineness;
			this.goldTyp = goldTyp;
			this.goldCode = goldCode;
		}

		public String getGoldFineness() {
			return this.goldFineness;
		}

		public String getGoldTyp() {
			return this.goldTyp;
		}

		public String getGoldCode() {
			return this.goldCode;
		}

		/**
		 * 通过金成色获取枚举值
		 * 
		 * @param goldFineness
		 *            金成色
		 * @return GoldFinenessGoldTypGoldCodeMapping 枚举值
		 */
		public static GoldFinenessGoldTypGoldCodeMapping getMappingByGoldFineness(
				String goldFineness) {
			GoldFinenessGoldTypGoldCodeMapping mapping = null;
			switch (goldFineness) {
			case "101":
				mapping = GoldFinenessGoldTypGoldCodeMapping.WHITE_18K;
				break;
			case "102":
				mapping = GoldFinenessGoldTypGoldCodeMapping.RED_18K;
				break;
			case "103":
				mapping = GoldFinenessGoldTypGoldCodeMapping.YELLOW_18K;
				break;
			case "104":
				mapping = GoldFinenessGoldTypGoldCodeMapping.RED_WHITE_18K;
				break;
			case "105":
				mapping = GoldFinenessGoldTypGoldCodeMapping.YELLOW_WHITE_18K;
				break;
			case "30":
				mapping = GoldFinenessGoldTypGoldCodeMapping.PT_900;
				break;
			case "20":
				mapping = GoldFinenessGoldTypGoldCodeMapping.PT_950;
				break;
			case "40":
				mapping = GoldFinenessGoldTypGoldCodeMapping.PT950_RU;
				break;
			}
			return mapping;
		}

		/**
		 * 通过描述获取枚举值
		 * 
		 * @param desc
		 *            描述
		 * @return GoldFinenessGoldTypGoldCodeMapping 枚举值
		 */
		public static GoldFinenessGoldTypGoldCodeMapping getMappingByDesc(
				String desc) {
			GoldFinenessGoldTypGoldCodeMapping mapping = null;
			switch (desc) {
			case "18K白":
				mapping = GoldFinenessGoldTypGoldCodeMapping.WHITE_18K;
				break;
			case "18K红":
				mapping = GoldFinenessGoldTypGoldCodeMapping.RED_18K;
				break;
			case "18K黄":
				mapping = GoldFinenessGoldTypGoldCodeMapping.YELLOW_18K;
				break;
			case "18K红白":
				mapping = GoldFinenessGoldTypGoldCodeMapping.RED_WHITE_18K;
				break;
			case "18K黄白":
				mapping = GoldFinenessGoldTypGoldCodeMapping.YELLOW_WHITE_18K;
				break;
			case "Pt900":
				mapping = GoldFinenessGoldTypGoldCodeMapping.PT_900;
				break;
			case "Pt950":
				mapping = GoldFinenessGoldTypGoldCodeMapping.PT_950;
				break;
			case "Pt950铂钌":
				mapping = GoldFinenessGoldTypGoldCodeMapping.PT950_RU;
				break;
			}
			return mapping;
		}

	}

	/**
	 * 处理标记
	 * 
	 * @author CaoYu
	 * 
	 */
	public enum ManageDealFlg {
		/**
		 * 未处理
		 */
		UNPROCESSED("0"),
		/**
		 * 已处理
		 */
		PROCESSED("1"),
		/**
		 * 处理失败
		 */
		PROCESSE_FAILED("2");

		private String manageDealFlg;

		private ManageDealFlg(String manageDealFlg) {
			this.manageDealFlg = manageDealFlg;
		}

		public String value() {
			return this.manageDealFlg;
		}
	}

	/**
	 * 借贷项
	 * 
	 * @author CaoYu
	 * 
	 */
	public enum BorrowItem {
		/**
		 * 借
		 */
		BORROW("S"),
		/**
		 * 贷
		 */
		LOAN("H");

		private String borrowItem;

		private BorrowItem(String borrowItem) {
			this.borrowItem = borrowItem;
		}

		public String value() {
			return this.borrowItem;
		}
	}

	/**
	 * 石头来源
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum StoneSource {
		/**
		 * 公司配石
		 */
		COMPANY("20"),
		/**
		 * 工厂配石
		 */
		FACTORY("10");

		private String source;

		private StoneSource(String source) {
			this.source = source;
		}

		public String value() {
			return this.source;
		}
	}

	/**
	 * 报价类别
	 * 
	 * @author 三影塔
	 */
	public enum PriceCategory {
		/**
		 * 国际报价
		 */
		INTERNATIONAL("Z903"),
		/**
		 * 标准价
		 */
		STANDARD("Z905");

		private String category;

		private PriceCategory(String category) {
			this.category = category;
		}

		public String value() {
			return this.category;
		}
	}

	/**
	 * 采购组
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum PurchaseGroup {
		/**
		 * 裸石采购组
		 */
		STONE("A10"),
		/**
		 * 饰品采购组
		 */
		ACCESSORY("A20"),
		/**
		 * 通用采购组
		 */
		NORMAL("A90"),
		/**
		 * 固定资产采购组
		 */
		FIXED_ASSETS("A40"),
		/**
		 * 费用采购组
		 */
		COST("A50"),
		/**
		 * 物料采购组
		 */
		MATERIAL("A30");

		private String group;

		private PurchaseGroup(String group) {
			this.group = group;
		}

		public String value() {
			return this.group;
		}
	}

	/**
	 * 采购组织
	 * 
	 * @author 三影塔
	 * 
	 */
	public enum PurchaseOrganization {
		/**
		 * 内部采购组织
		 */
		INTERNAL("1001"),
		/**
		 * 一般采购组织
		 */
		NORMAL("1000");

		private String organization;

		private PurchaseOrganization(String organization) {
			this.organization = organization;
		}

		public String value() {
			return this.organization;
		}
	}

}
