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

// @Bind #btnCreate.onClick
!function(self, arg) {
	var detailUrl = "com.jmc.scm.stock.view.GoodsInDetail.d?action=new";
	var detailTitle = "成品入库单创建";
	top.competingEntity = arg.data;
	top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
};

// @Bind #btnMod.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('prestoCode');
		var detailUrl = "com.jmc.scm.stock.view.GoodsInDetail.d?action=mod&docCode="
				+ currCode;
		var detailTitle = "成品入库单详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// @Bind #btnWatch.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('prestoCode');
		var detailUrl = "com.jmc.scm.stock.view.GoodsInDetail.d?action=watch&docCode="
				+ currCode;
		var detailTitle = "成品入库单详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// dgMain双击事件
// @Bind #dgMain.onDataRowDoubleClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('prestoCode');
		var detailUrl = "com.jmc.scm.stock.view.GoodsInDetail.d?action=watch&docCode="
				+ currCode;
		var detailTitle = "成品入库单详情页";
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
