//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人
var action = '${param.action}';// 操作类型
var docCode = '${param.docCode}';// 单号

// @Bind view.onReady
!function(dsBase, dsEnumvFirst) {
	if (action == 'new') {
		dsBase.set('readOnly', false);
		dsBase.insert();
		view.set('#prodCodeForm.readOnly', false);
	} else if (action == 'mod') {
		flushData(docCode);
	} else if (action == 'watch') {
		flushData(docCode);
	}
	// 加载品类一级枚举值
	dsEnumvFirst.set('parameter', 'PLYJ');
	dsEnumvFirst.flushAsync();
};

// 保存按钮
// @Bind #btnSave.onClick
!function(dsBase, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('prodCode');
	// 保存前校验
	doValidateBeforeSave(UserActionType.SAVE);
	uaSaveOrUpdate.set('parameter', UserActionType.SAVE);
	uaSaveOrUpdate.execute();
	// 根据单据号刷新数据
	flushData(flushCode);
};

// 删除
// @Bind #btnDel.onClick
!function(dsBase, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('prodCode');
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
	// 校验错误数据行
	if ((baseData.getMessageState()) && (baseData.getMessageState() == 'error')) {
		flagErro = false;
	}
	if (flagErro) {
		// 校验必填
		if (baseData.validate()!== 'ok') {
			flagReq = false;
		}
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
	dsBase.set('parameter', flushCode);
	dsBase.flushAsync(function(re) {
		if (re) {
			result = re.get('status');
			// 根据状态设置按钮可用性
			if (action == 'mod' || action == 'new') {
				if (result == '10') {
					// 已创建
					view.set('#btnSave.disabled', false);
					view.set('#btnDel.disabled', false);
					view.set('#prodCodeForm.readOnly', true);

					dsBase.set('readOnly', false);
				}
			} else if (action == 'watch') {
				view.set('#btnSave.disabled', true);
				view.set('#btnDel.disabled', true);
				view.set('#prodCodeForm.readOnly', true);

				dsBase.set('readOnly', true);
			}
		} else {
			// 已删除
			view.set('#btnSave.disabled', true);
			view.set('#btnDel.disabled', true);
			view.set('#prodCodeForm.readOnly', true);
			dsBase.set('readOnly', true);

		}
	});
	return result;
}
// @Bind @dtBase.onDataChange
!function(self, arg, dsBase, ajCheckProdCode, ajGetSupInfo) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	self.disableListeners();
	if (newValue!== null && newValue!== '') {
		if (property == 'prodCode') {
			ajCheckProdCode.set('parameter', newValue);
			ajCheckProdCode.execute(function(re) {
				if (!re) {
					dorado.MessageBox.alert('存在相同的商品编码！');
					currEntity.setMessages(property, '存在相同的商品编码！');
				}
			});
		} else if (property == 'supCode') {
			ajGetSupInfo.set('parameter', newValue);
			ajGetSupInfo.execute(function(re) {
				if (re) {
					currEntity.set({
						supName : re.supName
					});
				}
			});
		}
	}
	self.enableListeners();
};

function doValidateBeforeSave(action) {
	var dsBase = view.id('dsBase');
	var baseData = dsBase.getData('#');
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
		// 校验数据必填项和错误行
		var flagVali = true;
		flagVali = validateData();
		if (!flagVali) {
			dorado.MessageBox.alert("请校验数据必填项和错误行！");
			throw new dorado.AbortException();
		}
	}
}

// @Bind #btnShow.onClick
!function(dsBase) {
	var dsImg = view.id('_dsImageViewer');
	var dlgImg = view.id('_dialogImageViewer');
	var baseData = dsBase.getData('#');
	if (baseData) {
		var prodCode = baseData.get('prodCode');
		if (prodCode) {
			dsImg.set('parameter', prodCode);
			dsImg.flushAsync();
			dlgImg.show();
		} else {
			dorado.MessageBox.alert("请先生成主数据！");
		}
	}
};
// @Bind #dsddFirst.onValueSelect
!function() {
	var curr = view.id('dsBase').getData('#');
	curr.set({
		secCatg : null
	});
};
// @Bind #dsddSec.beforeExecute
!function(dsEnumvSec) {
	var curr = view.id('dsBase').getData('#');
	var first = curr.get('firstCatg');
	if (!first) {
		dorado.MessageBox.alert("请先选择品类一级！");
	} else {
		dsEnumvSec.set('parameter', first);
		dsEnumvSec.flushAsync();
	}
};

// @Bind #btnDown.onClick
!function(self, arg) {
	var pathName = null;
	pathName = '/scm/template/商品主数据导入模板.xlsx';
	pathName = new Base64().encode(pathName);
	var download = view.id('downExcel');
	var params = {
		"pathName" : pathName
	};
	download.set('parameter', params);
	download.execute();
};

// @Bind #importExcel.onSuccess
!function(self, arg) {
	var ajax = view.id('ajGetImpData');
	ajax.set('parameter', 'info');
	ajax.execute(function(re) {
		if (re) {
			dorado.MessageBox.alert("导入成功！");
		}
	});
};
