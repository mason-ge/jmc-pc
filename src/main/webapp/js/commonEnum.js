(function() {
	/**
	 * 删除标记
	 * 
	 * @author 三影塔
	 * 
	 */
	DeletedFlag = {
		/**
		 * 已删除
		 */
		YES : "1",
		/**
		 * 未删除
		 */
		NO : "0",
	};

	/**
	 * 用户操作类型
	 * 
	 * @author Mason_Ge
	 * 
	 */
	UserActionType = {
		/**
		 * 保存
		 */
		SAVE : "save",
		/**
		 * 提交
		 */
		SUBMIT : "submit",
		/**
		 * 反提交
		 */
		DIS_SUBMIT : "disSubmit",
		/**
		 * 审核
		 */
		APPROVE : "appr",
		/**
		 * 反审核
		 */
		DIS_APPROVE : "disAppr",
		/**
		 * 删除
		 */
		DEL : "del",
		/**
		 * 删除恢复
		 */
		RE_DEL : "reDel",
	};

	/**
	 * 一般状态
	 * 
	 * @author mason
	 * 
	 */
	CommonStatus = {
		/**
		 * 已创建
		 */
		CREATED : "10",
		/**
		 * 已提交
		 */
		SUBMITED : "20",
		/**
		 * 已审核
		 */
		APPROVED : "30",
		/**
		 * 已删除
		 */
		DELETED : "90",
	};

	/**
	 * 流程操作
	 * 
	 * @author 三影塔
	 * 
	 */
	WorkFlowAction = {
		/**
		 * 流程进行中
		 */
		PROCESSING : 'PROCESSING',
		/**
		 * 流程节点审批通过
		 */
		APPROVED : 'APPROVED',
		/**
		 * 流程节点审批驳回 （流程驳回）
		 */
		REJECTED : 'REJECTED',
		/**
		 * 流程正常审批结束
		 */
		FINISHED : 'FINISHED',
	};

	/**
	 * 是否启用枚举值
	 * 
	 * @author 三影塔
	 * 
	 */
	EnabledFlag = {
		/**
		 * 启用
		 */
		ENABLED : "1",
		/**
		 * 禁用
		 */
		DISABLED : "0",
	};
	/**
	 * 借货标识
	 */
	LoanFlg = {
		/**
		 * 是
		 */
		YES : '1',
		/**
		 * 否
		 */
		NO : '0',
	};

})();