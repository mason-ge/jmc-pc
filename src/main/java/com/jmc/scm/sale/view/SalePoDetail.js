//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人
var action = '${param.action}';// 操作类型
var docCode = '${param.docCode}';// 单号

// @Bind view.onReady
!function(dsBase, dsItem, ajGetItemInfo) {
	if (action == 'new') {
		dsBase.set('readOnly', false);
		dsItem.set('readOnly', false);
		dsBase.insert();

		view.set('#btnSave.disabled', false);
		view.set('#btnCancel.disabled', true);
		view.set('#btnRestore.disabled', true);
		view.set('#btnRmvLine.disabled', false);

		ajGetItemInfo.execute(function(re) {
			if (re) {
				dsItem.getData().fromJSON(re);
				setItemCode();
				calcuNum();
			}
		});
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
	var pkId = baseData.get('pkId');
	if (pkId) {
		flushCode = baseData.get('salePoCode');
	}
	// 保存前校验
	doValidateBeforeSave(UserActionType.SAVE);
	uaSaveOrUpdate.set('parameter', UserActionType.SAVE);
	var re = uaSaveOrUpdate.execute();
	var seqCodeReturn = re.seqCode;
	if (seqCodeReturn) {
		flushCode = seqCodeReturn;
		dorado.MessageBox.alert("生成销售单号：" + seqCodeReturn);
	}
	// 根据单据号刷新数据
	flushData(flushCode);
};

// 取消订单按钮
// @Bind #btnCancel.onClick
!function(dlgCancel) {
	dlgCancel.show();
};

// @Bind #btnSure.onClick
!function(dsBase, dsItem, uaSaveOrUpdate, dlgCancel) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('salePoCode');
	// 保存前校验
	doValidateBeforeSave(UserActionType.DEL);
	uaSaveOrUpdate.set('parameter', UserActionType.DEL);
	uaSaveOrUpdate.execute();
	// 根据单据号刷新数据
	flushData(flushCode);
	dlgCancel.hide();
};

// @Bind #btnDrop.onClick
!function(dlgCancel) {
	dlgCancel.hide();
};

// 恢复订单按钮
// @Bind #btnRestore.onClick
!function(dsBase, dsItem, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('salePoCode');
	// 保存前校验
	doValidateBeforeSave(UserActionType.RE_DEL);
	uaSaveOrUpdate.set('parameter', UserActionType.RE_DEL);
	uaSaveOrUpdate.execute();
	// 根据单据号刷新数据
	flushData(flushCode);
};

// @Bind @dtBase.onDataChange
!function(self, arg, dsItem, dsBase, ajGetCltInfo) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	if (newValue !== null && newValue !== '') {
		self.disableListeners();
		if (property == 'cltCode') {
			// 根据客户编码带信息
			ajGetCltInfo.set('parameter', newValue);
			ajGetCltInfo.execute(function(re) {
				if (re) {
					currEntity.set({
						cltName : re.cltName,
						cltAdd : re.adr1,
						phone : re.conPhone1,
						payCon : re.payment,
						disctCmmt : re.discountDesc,
					});
				}
			});
		}
		self.enableListeners();
	}
};

// 设置行项目号,和数量
function setItemCode() {
	var dsItem = view.id('dsItem');
	var itemData = dsItem.getData();
	if (itemData && itemData.entityCount > 0) {
		var i = 10;
		itemData.each(function(e) {
			e.set({
				itemCode : i,
			});
			i = i + 10;
		});
	}
};

function calcuNum() {
	var dsBase = view.id('dsBase');
	var dsItem = view.id('dsItem');
	var baseData = dsBase.getData('#');
	var itemData = dsItem.getData();
	var totNum = 0;

	var nums = 0;
	if (itemData && itemData.entityCount > 0) {
		itemData.each(function(e) {
			if (e.get('nums')) {
				nums = e.get('nums');
				totNum = totNum * 1 + nums * 1;
			}
		});
		baseData.set({
			totNum : totNum,
		});
	}
};

// @Bind @dtItem.onDataChange
!function(self, arg, dsItem, dsBase) {
	var property = arg.property;
	var newValue = arg.newValue;
	if (newValue !== null && newValue !== '') {
		if (property == 'nums') {
			calcuNum();
		}
	}
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
		if (baseData.validate() !== 'ok') {
			flagReq = false;
		}
		itemData.each(function(e) {
			if (e.validate() !== 'ok') {
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
				if (result == CommonStatus.CREATED) {
					// 已创建
					view.set('#btnSave.disabled', false);
					view.set('#btnCancel.disabled', false);
					view.set('#btnRestore.disabled', true);
					view.set('#btnRmvLine.disabled', false);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				} else if (result == CommonStatus.SUBMIT) {
					// 已发货
					view.set('#btnSave.disabled', true);
					view.set('#btnCancel.disabled', true);
					view.set('#btnRestore.disabled', true);
					view.set('#btnRmvLine.disabled', true);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				} else if (result == CommonStatus.DELETED) {
					view.set('#btnSave.disabled', true);
					view.set('#btnCancel.disabled', true);
					view.set('#btnRestore.disabled', false);
					view.set('#btnRmvLine.disabled', true);

					dsBase.set('readOnly', true);
					dsItem.set('readOnly', true);
				}
			} else if (action == 'watch') {
				view.set('#btnSave.disabled', true);
				view.set('#btnCancel.disabled', true);
				view.set('#btnRestore.disabled', true);
				view.set('#btnRmvLine.disabled', true);

				dsBase.set('readOnly', true);
				dsItem.set('readOnly', true);
			}
		} else {
			// 已删除
			view.set('#btnSave.disabled', true);
			view.set('#btnCancel.disabled', true);
			view.set('#btnRestore.disabled', true);
			view.set('#btnRmvLine.disabled', true);

			dsBase.set('readOnly', true);
			dsItem.set('readOnly', true);
			dsItem.clear();
		}
	});
	return result;
}

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
		flushCode = baseData.get('salePoCode');
		status = getStatus(flushCode);
	}
	var flg = true;
	if (action == UserActionType.SAVE) {
		if (status !== CommonStatus.CREATED && status !== null && status != '') {
			flg = false;
		}
	} else if (action == UserActionType.SUBMIT) {
		if (status !== CommonStatus.CREATED) {
			flg = false;
		}
	} else if (action == UserActionType.DEL) {
		if (status !== CommonStatus.CREATED) {
			flg = false;
		}
	} else if (action == UserActionType.RE_DEL) {
		if (status !== CommonStatus.DELETED) {
			flg = false;
		}
	}
	if (!flg) {
		dorado.MessageBox.alert('当前数据状态不符合操作条件！将刷新数据');
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

// @Bind #disctColumn.onRenderCell
// @Bind #remarkColumn.onRenderCell
!function(self, arg) {
	arg.dom.style.background = "#43CD80";
	arg.processDefault = true;
};

// @Bind #imgCol.onRenderCell
!function(arg) {
	var entity = arg.data, image = '#';
	if (entity.get("prodCode") !== null && entity.get("prodCode") !== '') {
		if (entity.get('img')) {
			image = entity.get('img');
		}
		$(arg.dom).empty().xCreate({
			tagName : "img",
			src : image,
			style : "width: 50px; height: 50px; margin: 2px"
		});
	}
};
