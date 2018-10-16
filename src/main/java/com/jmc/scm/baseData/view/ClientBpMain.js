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

// @Bind #btnMod.onClick
!function(self, arg, dlgBp, dsNew) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		dsNew.clear();
		dsNew.insert({
			cltCode : curr.get('cltCode'),
			cltName : curr.get('cltName'),
		});
		dlgBp.show();
	}
};

// @Bind #btnSure.onClick
!function(self, arg, dsNew, dlgBp, uaSaveChange) {
	var newData = dsNew.getData('#');
	if (newData.validate() == 'ok') {
		uaSaveChange.execute(function(re) {
			dlgBp.hide();
			doSearch();
		});
	} else {
		dorado.MessageBox.alert('请校验必填项！');
	}

};
// @Bind #btCancel.onClick
!function(self, arg, dlgBp) {
	dlgBp.hide();
};
function doSearch() {
	var dsMain = view.id('dsMain');
	var dsCondition = view.id('dsCondition');
	dsMain.set('parameter', dsCondition.getData('#'));
	dsMain.flushAsync();
}
