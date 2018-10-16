//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人
var action = '${param.action}';// 操作类型
var docCode = '${param.docCode}';// 单号

// @Bind view.onReady
!function(dsBase, dsItem) {
	if (action == 'new') {
		dsBase.set('readOnly', false);
		dsItem.set('readOnly', false);
		dsBase.insert();
	} else if (action == 'mod') {
		flushData(docCode);
	} else if (action == 'watch') {
		flushData(docCode);
	}
};

// @Bind #btnAddLine.onClick
!function(dsItem) {
	dsItem.insert();
};

// @Bind #btnRmvLine.onClick
!function(dsItem) {
	var itemData = dsItem.getData('#');
	if (itemData) {
		dorado.MessageBox.confirm('您确定要删除当前信息吗？', function() {
			itemData.remove();
		});
	}
};

// 保存按钮
// @Bind #btnSave.onClick
!function(dsBase, dsItem, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('cltCode');
	// 保存前校验
	doValidateBeforeSave(UserActionType.SAVE);
	uaSaveOrUpdate.set('parameter', UserActionType.SAVE);
	uaSaveOrUpdate.execute();
	// 根据单据号刷新数据
	flushData(flushCode);
};

// 删除
// @Bind #btnDel.onClick
!function(dsBase, dsItem, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('cltCode');
	dorado.MessageBox.confirm('您确定要删除当前信息吗？', function() {
		uaSaveOrUpdate.set('parameter', UserActionType.DEL);
		uaSaveOrUpdate.execute();
		flushData(flushCode);
	});
};

/**
 * 校验主子表数据的必填性和错误行
 */
function validateData() {
	var flagErro = true;
	var flagReq = true;
	var baseData = view.id('dsBase').getData('#');
	var itemData = view.id('dsItem').getData();
	// 校验错误数据行
	if ((baseData.getMessageState()) && (baseData.getMessageState() == 'error')) {
		flagErro = false;
	}
	itemData.each(function(e) {
		if ((e.getMessageState()) && (e.getMessageState() == 'error')) {
			flagErro = false;
		}
	});
	if (flagErro) {
		// 校验必填
		if (baseData.validate()!== 'ok') {
			flagReq = false;
		}
		itemData.each(function(e) {
			if (e.validate()!== 'ok') {
				flagReq = false;
			}
		});
	}
	if (flagReq && flagErro) {
		return true;
	} else {
		return false;
	}
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

// 根据单号刷新数据
function flushData(flushCode) {
	var result = "";
	var dsBase = view.id('dsBase');
	var dsItem = view.id('dsItem');
	dsBase.set('parameter', flushCode);
	dsItem.set('parameter', flushCode);
	dsBase.flushAsync(function(re) {
		if (re) {
			dsItem.flushAsync();
			result = re.get('status');
			// 根据状态设置按钮可用性
			if (action == 'mod' || action == 'new') {
				if (result == '10') {
					// 已创建
					view.set('#btnSave.disabled', false);
					view.set('#btnDel.disabled', false);
					view.set('#btnAddLine.disabled', false);
					view.set('#btnRmvLine.disabled', false);

					view.set('#cltCodeForm.readOnly', true);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				}
			} else if (action == 'watch') {
				view.set('#btnSave.disabled', true);
				view.set('#btnDel.disabled', true);
				view.set('#btnAddLine.disabled', true);
				view.set('#btnRmvLine.disabled', true);

				dsBase.set('readOnly', true);
				dsItem.set('readOnly', true);
			}
		} else {
			// 已删除
			view.set('#btnSave.disabled', true);
			view.set('#btnDel.disabled', true);
			view.set('#btnAddLine.disabled', true);
			view.set('#btnRmvLine.disabled', true);

			dsBase.set('readOnly', true);
			dsItem.set('readOnly', true);
			dsItem.clear();
		}
	});
	return result;
}
// @Bind @dtBase.onDataChange
!function(self, arg, dsItem, dsBase, ajCheckCltCode, ajGetCltByCode) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	self.disableListeners();
	if (newValue!== null && newValue!== '') {
		if (property == 'cltCode') {
			ajCheckCltCode.set('parameter', newValue);
			ajCheckCltCode.execute(function(re) {
				if (!re) {
					dorado.MessageBox.alert('存在相同的客户编码！');
					currEntity.setMessages(property, '存在相同的客户编码！');
				}
			});
		} else if (property == 'intclientCode') {
			ajGetCltByCode.set('parameter', newValue);
			ajGetCltByCode.execute(function(re) {
				if (re) {
					currEntity.set({
						intclientName : re.cltName
					});
				} else {
					dorado.MessageBox.alert('输入的客户编码不存在！');
					currEntity.setMessages(property, '输入的客户编码不存在！');
				}
			});
		}
	}
	self.enableListeners();
};
function doValidateBeforeSave(action) {
	var dsBase = view.id('dsBase');
	var dsItem = view.id('dsItem');
	var baseData = dsBase.getData('#');
	var itemData = dsItem.getData();
	var dataCount = itemData.entityCount;
	var pkId = baseData.get('pkId');
	var status = "";// 数据库状态
	var flushCode = "";// 刷新数据的PO号
	// 校验数据库的状态
	if (pkId) {
		flushCode = baseData.get('cltCode');
		status = getStatus(flushCode);
	}
	var flg = true;
	if (action == UserActionType.SAVE) {
		if (status!== CommonStatus.CREATED && status!== null && status!== '') {
			flg = false;
		}
	} else if (action == UserActionType.DEL) {
		if (status!== CommonStatus.DELETED) {
			flg = false;
		}
	}
	if (!flg) {
		dorado.MessageBox.alert('当前数据状态不符合保存条件！将刷新数据');
		flushData(flushCode);
		throw new dorado.AbortException();
	} else {
		if (dataCount > 0) {
			// 校验数据必填项和错误行
			var flagVali = true;
			flagVali = validateData();
			if (!flagVali) {
				dorado.MessageBox.alert("请校验数据必填项和错误行！");
				throw new dorado.AbortException();
			}
		} else {
			dorado.MessageBox.alert("请至少填一条明细行！");
			throw new dorado.AbortException();
		}
	}
}
