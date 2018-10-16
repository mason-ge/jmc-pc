var mode = '${param.mode}';//勾选模式（single为单选）
var supGrp = '${param.supGrp}';//供应商组

//@Bind view.onReady
!function(dsCondition,dsMain,dgMain) {
	dsCondition.insert({
		supGrp : supGrp
	});
	if (mode == 'single') {
		dgMain.removeColumn(view.id('rowSelector'));
		dgMain.refresh();
		dgMain.set('selectionMode', 'singleRow');
	}
}; 
//@Bind #btnQuery.onClick
!function(){
	doSearch();
};
//重置按钮点击
//@Bind #btnReset.onClick
!function(dsCondition){
	dsCondition.clear();
	dsCondition.insert({
		supGrp : supGrp
	});
};
//@Bind #afCondition.onKeyDown
!function(arg){
	if(arg.keyCode == 13){
		doSearch();
	}
};
//选择按钮
//@Bind #btnSelect.onClick
!function(dsMain) {
	var count = view.id('dgMain').getCurrentEntity();
	if(count == null){
		dorado.MessageBox.alert('请至少选择一条数据！');
	}else{
		if (mode == 'single') {
			var entity = count;
			window.parent.setSupInfo(entity);
			view.id('toolSelect').setFocus ();
		} else {
			var entitys = view.get('#dgMain.selection');
			if(entitys.length <1){
				dorado.MessageBox.alert('请至少勾选一条数据！');
			}else{		
				window.parent.setSupInfo(entitys);
				view.id('toolSelect').setFocus ();
			}
		
		}
	}
};
//双击按钮
//@Bind #dgMain.onDataRowDoubleClick
!function(dsMain) {
	var count = view.id('dgMain').getCurrentEntity();
	if(count == null){
		dorado.MessageBox.alert('请至少选择一条数据！');
	}else{
		if (mode == 'single') {
			var entity = count;
			window.parent.setSupInfo(entity);
			view.id('toolSelect').setFocus ();
		} else {
			var entitys = view.get('#dgMain.selection');
			if(entitys.length <1){
				dorado.MessageBox.alert('请至少勾选一条数据！');
			}else{		
				window.parent.setSupInfo(entitys);
				view.id('toolSelect').setFocus ();
			}
		}
	}
};
function doSearch(){
	var dsCondition = view.id('dsCondition');
	var dsMain = view.id('dsMain');
	dsMain.set('parameter', dsCondition.getData());
	dsMain.flushAsync();
}