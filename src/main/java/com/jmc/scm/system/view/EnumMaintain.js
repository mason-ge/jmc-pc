// @Bind #btnAddEnum.onClick
!function(dsMain) {
	dsMain.insert();
	view.id('dialogDetail').show();
};

// @Bind #btnAddEnumv.onClick
!function(dsMain) {
	var enumvs = dsMain.getData('#.refEnumv');
	enumvs.insert();
};

// @Bind #dsCondition.onReady
!function(self) {
	self.insert();
};

// @Bind #dialogDetail.onHide
!function(dsMain) {
	dsMain.getData('#').cancel();
};

// @Bind #deleteEnumvAction.onGetUpdateData
!function(arg, dgDetailEnumv) {
	arg.data = dgDetailEnumv.get('selection');
};

// @Bind #deleteEnumvAction.onSuccess
!function(dgDetailEnumv) {
	var list = dgDetailEnumv.get('selection');
	list.each(function(item) {
		item.remove();
	});
};

// @Bind #deleteEnumAction.onSuccess
!function(dsMain) {
	var entity = dsMain.getData('#');
	entity.remove();
};

// @Bind #btnAlterEnum.onClick
!function() {
	view.id('dialogDetail').show();
};

// @Bind #btnSearch.onClick
!function(dsMain) {
	var data = view.get('#dsCondition.data');
	dsMain.set('parameter', data);
	dsMain.flushAsync();
};
// @Bind view.onReady
!function(){
};