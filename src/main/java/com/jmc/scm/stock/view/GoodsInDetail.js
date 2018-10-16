//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人
var action = '${param.action}';// 操作类型
var docCode = '${param.docCode}';// 单号
var client = '';

// @Bind view.onReady
!function(dsBase, dsItem, ajGetClient) {
	if (action == 'new') {
//		//默认借货为“否”
//		dsBase.insert({
//			loan : LoanFlg.NO
//		});
		dsBase.insert();
		view.set('#btnSave.disabled', false);
		view.set('#btnSubmit.disabled', true);
		view.set('#btnDisSubmit.disabled', true);

		view.set('#btnPay.disabled', true);
		view.set('#btnImp.disabled', false);
		view.set('#btnDown.disabled', false);
		view.set('#btnExport.disabled', true);
		view.set('#btnRmvLine.disabled', false);

		dsBase.set('readOnly', false);
		dsItem.set('readOnly', false);
	} else if (action == 'mod') {
		flushData(docCode);
	} else if (action == 'watch') {
		flushData(docCode);
	}
	client = ajGetClient.execute();
};

// 保存按钮
// @Bind #btnSave.onClick
!function(dsBase, dsItem, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	var pkId = baseData.get('pkId');
	if (pkId) {
		flushCode = baseData.get('prestoCode');
	}
	// 保存前校验
	doValidateBeforeSave(UserActionType.SAVE);
	uaSaveOrUpdate.set('parameter', UserActionType.SAVE);
	var re = uaSaveOrUpdate.execute();
	var seqCodeReturn = re.seqCode;
	if (seqCodeReturn) {
		flushCode = seqCodeReturn;
		dorado.MessageBox.alert("生成入库单号：" + seqCodeReturn);
	}
	// 根据单据号刷新数据
	flushData(flushCode);
};

// 提交
// @Bind #btnSubmit.onClick
!function(dsBase, dsItem, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('prestoCode');
	// 保存前校验
	doValidateBeforeSave(UserActionType.SUBMIT);
	uaSaveOrUpdate.set('parameter', UserActionType.SUBMIT);
	uaSaveOrUpdate.execute();
	flushData(flushCode);
};

// 反提交
// @Bind #btnDisSubmit.onClick
!function(dsBase, dsItem, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('prestoCode');
	// 保存前校验
	doValidateBeforeSave(UserActionType.DIS_SUBMIT);
	uaSaveOrUpdate.set('parameter', UserActionType.DIS_SUBMIT);
	uaSaveOrUpdate.execute();
	flushData(flushCode);
};

// 收款
// @Bind #btnPay.onClick
!function(dsBase, dlgPay, dsPaySure) {
	var baseData = dsBase.getData('#');
	var totPrice = baseData.get('totPrice');
	var totPay = baseData.get('totPay');
	var totLeftPay = (totPrice * 1 - totPay * 1).toFixed(2);
	dsPaySure.clear();
	dsPaySure.insert({
		payDate : new Date(),
		totSaleAmt : totPrice,
		totPay : totPay,
		totLeftPay : totLeftPay
	});
	dlgPay.show();
};

// 收款确认
// @Bind #btnSurePay.onClick
!function(dlgPay, dsBase, uaSavePay) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('prestoCode');
	uaSavePay.execute(function() {
		// 根据单据号刷新数据
		flushData(flushCode);
		dlgPay.hide();
	});
};

// 收款取消
// @Bind #btnCancelPay.onClick
!function(dlgPay) {
	dlgPay.hide();
};

// @Bind #btnRmvLine.onClick
!function(dsItem, dsBase) {
	var curr = dsItem.getData('#');
	if (curr) {
		dorado.MessageBox.confirm('确定删除吗？', function() {
			var prestoCode = dsBase.getData('#').get('prestoCode');
			curr.remove();
			if (prestoCode == '' || prestoCode == null) {
				// 重新计算一遍行号，防止删除中间行跳号
				var data = dsItem.getData();
				var i = 10;
				if (data) {
					data.each(function(e) {
						e.set('prestoItemCode', i);
						i = i + 10;
					});
				}
			}
		});
	}
};
// @Bind @dtBase.onDataChange
!function(self, arg, ajGetSupInfo) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	if (newValue!== null && newValue!== '') {
		self.disableListeners();
		if (property == 'supCode') {
			ajGetSupInfo.set('parameter', newValue);
			ajGetSupInfo.execute(function(re) {
				if (re) {
					currEntity.set({
						supName : re.supName
					});
				}
			});
		}
		self.enableListeners();
	}
};

// @Bind @dtItem.onDataChange
!function(self, arg) {
	var property = arg.property;
	var newValue = arg.newValue;
	if (newValue!== null && newValue!== '') {
		self.disableListeners();
		if (property == 'facSettlePx') {
			calcuItemNum();
		}
		self.enableListeners();
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
	var dsPay = view.id('dsPay');

	dsBase.set('parameter', flushCode);
	dsItem.set('parameter', flushCode);
	dsPay.set('parameter', flushCode);
	dsBase.flushAsync(function(re) {
		if (re) {
			dsItem.flushAsync();
			dsPay.flushAsync();
			result = re.get('status');
			// 根据状态设置按钮可用性
			if (action == 'mod' || action == 'new') {
				if (result == '10') {
					// 已创建
					view.set('#btnSave.disabled', false);
					view.set('#btnSubmit.disabled', false);
					view.set('#btnDisSubmit.disabled', true);
					view.set('#btnPay.disabled', false);
					view.set('#btnImp.disabled', false);
					view.set('#btnDown.disabled', false);
					view.set('#btnExport.disabled', false);
					view.set('#btnRmvLine.disabled', false);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				} else if (result == '20') {
					// 已提交
					view.set('#btnSave.disabled', true);
					view.set('#btnSubmit.disabled', true);
					view.set('#btnDisSubmit.disabled', false);

					view.set('#btnPay.disabled', false);
					view.set('#btnImp.disabled', true);
					view.set('#btnDown.disabled', true);
					view.set('#btnExport.disabled', false);
					view.set('#btnRmvLine.disabled', true);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				}
			} else if (action == 'watch') {
				view.set('#btnSave.disabled', true);
				view.set('#btnSubmit.disabled', true);
				view.set('#btnDisSubmit.disabled', true);

				view.set('#btnPay.disabled', true);
				view.set('#btnImp.disabled', true);
				view.set('#btnDown.disabled', true);
				view.set('#btnExport.disabled', false);
				view.set('#btnRmvLine.disabled', true);

				dsBase.set('readOnly', true);
				dsItem.set('readOnly', true);
			}
		} else {
			// 已删除
			view.set('#btnSave.disabled', true);
			view.set('#btnSubmit.disabled', true);
			view.set('#btnDisSubmit.disabled', true);

			view.set('#btnPay.disabled', true);
			view.set('#btnImp.disabled', true);
			view.set('#btnDown.disabled', true);
			view.set('#btnExport.disabled', true);
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
	var ajCheckSt = view.id('ajCheckSt');
	var baseData = dsBase.getData('#');
	var itemData = dsItem.getData();
	var dataCount = itemData.entityCount;
	var pkId = baseData.get('pkId');
	var status = "";// 数据库状态
	var flushCode = "";// 刷新数据的PO号
	// 校验数据库的状态
	if (pkId) {
		flushCode = baseData.get('prestoCode');
		status = getStatus(flushCode);
	}
	var flg = true;
	if (action == UserActionType.SAVE) {
		if (status!== CommonStatus.CREATED && status!== null && status!== '') {
			flg = false;
		}
	} else if (action == UserActionType.SUBMIT) {
		if (status!== CommonStatus.CREATED) {
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
			// 校验页面上的货号不能重复
			var checkArray = [];
			itemData.each(function(e) {
				checkArray.push(e.get('batchCode'));
			});
			if (isRepeat(checkArray)) {
				dorado.MessageBox.alert('存在相同的货号！');
				throw new dorado.AbortException();
			}
			// 校验要保存的行项目在单品属性没有
			ajCheckSt.set('parameter', itemData);
			var re = ajCheckSt.execute();
			if (re && re!== '') {
				dorado.MessageBox.alert("以下货号已经入库！：" + re);
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
		} else {
			dorado.MessageBox.alert("请至少填一条明细行！");
			throw new dorado.AbortException();
		}
	}
}

// @Bind #importExcel.onSuccess
!function(self, arg) {
	var ajax = view.id('ajGetImpData');
	var dsItem = view.id('dsItem');
	var data = dsItem.getData();
	ajax.set('parameter', 'info');
	ajax.execute(function(re) {
		if (re) {
			data.fromJSON(re);
			calcuItemNum();
		}
	});
};

function calcuItemNum() {
	var dsBase = view.id('dsBase');
	var baseData = dsBase.getData('#');
	var dsItem = view.id('dsItem');
	var itemData = dsItem.getData();
	var totPrice = 0;
	var num = 0;
	var i = 10;
	if (itemData && itemData.entityCount > 0) {
		itemData.each(function(e) {
			e.set({
				prestoItemCode : i,
				nums : 1
			});
			if (e.get('facSettlePx')!== null && e.get('facSettlePx')!== '') {
				num = e.get('facSettlePx');
				totPrice = totPrice * 1 + num * 1;
			}
			i = i * 1 + 10 * 1;
		});
		baseData.set({
			totAmt : itemData.entityCount,
			totPrice : totPrice.toFixed(2)
		});
	}
}

// @Bind #btnDown.onClick
!function(self, arg) {
	var pathName = null;
	pathName = '/scm/template/成品入库导入模板.xlsx';
	pathName = new Base64().encode(pathName);
	var download = view.id('downExcel');
	var params = {
		"pathName" : pathName
	};
	download.set('parameter', params);
	download.execute();
};

// @Bind #btnAllPrint.onClick
!function(self, arg) {
	var code = view.id("dsBase").getData("#").get("prestoCode");
	if (code) {
		window
				.open("output?__report=report/goodsBatch.rptdesign&_format=pdf&overwrite=true&__dpi=96&__format=pdf&__pageoverflow=0&__overwrite=false&client="
						+ client + "&prestoCode=" + code);
	}
};