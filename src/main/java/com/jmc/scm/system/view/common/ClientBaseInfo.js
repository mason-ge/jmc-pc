var mode = '${param.mode}';//勾选模式（single为单选）
//@Bind view.onReady
!function(dsCondition,dsMain,dgMain) {
	dsCondition.insert();
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
!function(dsMain,dsPushEntity,dsPushEntitys) {
	var count = view.id('dgMain').getCurrentEntity();
	if(count == null){
		dorado.MessageBox.alert('请至少选择一条数据！');
	}else{
		if (mode == 'single') {
			var entity = count;
			var code = entity.get('clientCode');
			dsPushEntity.set('parameter',{
				clientCode : code
			});
			dsPushEntity.flushAsync(function(re){
				window.parent.setClientInfo(re);
				view.id('toolSelect').setFocus ();
			});
		} else {
			var entitys = view.get('#dgMain.selection');
			if(entitys.length <1){
				dorado.MessageBox.alert('请至少勾选一条数据！');
			}else{
				var codes = '';
				entitys.each(function(e){
					codes += e.get('clientCode')+",";
				});
				codes = codes.substring(0,codes.length-1);
				dsPushEntitys.set('parameter',codes);
				dsPushEntitys.flushAsync(function(re){
					window.parent.setClientInfo(re);
					view.id('toolSelect').setFocus ();
				});
			}
		}
	}
};
//双击按钮
//@Bind #dgMain.onDataRowDoubleClick
!function(dsMain,dsPushEntity,dsPushEntitys) {
	var count = view.id('dgMain').getCurrentEntity();
	if(count == null){
		dorado.MessageBox.alert('请至少选择一条数据！');
	}else{
		if (mode == 'single') {
			var entity = count;
			var code = entity.get('clientCode');
			dsPushEntity.set('parameter',{
				clientCode : code
			});
			dsPushEntity.flushAsync(function(re){
				window.parent.setClientInfo(re);
				view.id('toolSelect').setFocus ();
			});
		} else {
			var entitys = view.get('#dgMain.selection');
			if(entitys.length <1){
				dorado.MessageBox.alert('请至少勾选一条数据！');
			}else{
				var codes = '';
				entitys.each(function(e){
					codes += e.get('clientCode')+",";
				});
				codes = codes.substring(0,codes.length-1);
				dsPushEntitys.set('parameter',codes);
				dsPushEntitys.flushAsync(function(re){
					window.parent.setClientInfo(re);
					view.id('toolSelect').setFocus ();
				});
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