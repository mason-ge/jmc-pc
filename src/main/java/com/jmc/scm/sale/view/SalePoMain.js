//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人

// @Bind view.onReady
!function(dsCondition, dsMain) {
	dsCondition.insert();
};

// 查询按钮点击
// @Bind #btnQuery.onClick
!function() {
	doSearch();
};
// @Bind #afCondition.onKeyDown
!function(arg) {
	if (arg.keyCode == 13) {
		doSearch();
	}
};
// 重置按钮点击
// @Bind #btnReset.onClick
!function(dsCondition) {
	dsCondition.clear();
	dsCondition.insert({});
};

// @Bind #btnDeli.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('salePoCode');
		var status = getStatus(currCode);
		if (status == CommonStatus.CREATED) {
			var detailUrl = "com.jmc.scm.sale.view.SaleOutDetail.d?action=new&salePoCode="
					+ currCode;
			var detailTitle = "销售出库单创建";
			top.competingEntity = arg.data;
			top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
		} else {
			dorado.MessageBox.alert('请选择状态为正常可发货的订单！');
		}
	}
};

// @Bind #btnMod.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('salePoCode');
		var detailUrl = "com.jmc.scm.sale.view.SalePoDetail.d?action=mod&docCode="
				+ currCode;
		var detailTitle = "销售采购订单详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// @Bind #btnWatch.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('salePoCode');
		var detailUrl = "com.jmc.scm.sale.view.SalePoDetail.d?action=watch&docCode="
				+ currCode;
		var detailTitle = "销售采购订单详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// dgMain双击事件
// @Bind #dgMain.onDataRowDoubleClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('salePoCode');
		var detailUrl = "com.jmc.scm.sale.view.SalePoDetail.d?action=watch&docCode="
				+ currCode;
		var detailTitle = "销售采购订单详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

function doSearch() {
	var dsMain = view.id('dsMain');
	var dsCondition = view.id('dsCondition');
	dsMain.set('parameter', dsCondition.getData('#'));
	dsMain.flushAsync();
}

/**
 * 根据单据号查询状态
 * 
 * @param docCode
 */
function getStatus(docCode) {
	var ajGetStatus = view.id('ajGetStatus');
	ajGetStatus.set('parameter', docCode);
	var re = ajGetStatus.execute();
	return re;
}

// @Bind #dgMain.onRenderRow
!function(self, arg) {
	var soStatus = arg.data.get('soStatus');
	if (soStatus == CommonStatus.DELETED) {
		$(arg.dom).css('background-color', 'red');
	} else {
		$(arg.dom).css('background-color', '');
	}
};