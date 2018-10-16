//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人
var client = '';

// @Bind view.onReady
!function(dsCondition, dsMain,ajGetClient) {
	dsCondition.insert();
	client = ajGetClient.execute();
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

function doSearch() {
	var dsMain = view.id('dsMain');
	var dsCondition = view.id('dsCondition');
	dsMain.set('parameter', dsCondition.getData('#'));
	dsMain.flushAsync();
}

//@Bind #btnPrint.onClick
!function(self, arg) {
	var code = view.id("dsMain").getData("#").get("batchCode");
	if (code) {
		window.open("output?__report=report/batch.rptdesign&_format=pdf&overwrite=true&__dpi=96&__format=pdf&__pageoverflow=0&__overwrite=false&client="+client+"&batchCode="
						+ code);
	}
};

//@Bind #btnMod.onClick
!function(){
	view.id('dlgMod').show();
};
//@Bind #btnSure.onClick
!function(uaUpdate){
	uaUpdate.execute(function(re){
		view.id('dlgMod').hide();
	});
};
//@Bind #btnCancel.onClick
!function(){
	view.id('dlgMod').hide();
};