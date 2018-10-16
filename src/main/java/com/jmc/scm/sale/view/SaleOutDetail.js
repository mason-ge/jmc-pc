//全局变量
var today = new Date();// 当前时间
var logUser = "${loginUsername}";// 登录人
var action = '${param.action}';// 操作类型
var docCode = '${param.docCode}';// 单号
var salePoCodeParam = '${param.salePoCode}';// 销售订单号

// @Bind view.onReady
!function(dsBase, dsItem, ajGeSoBase) {
	if (action == 'new') {
		dsBase.set('readOnly', false);
		dsItem.set('readOnly', false);
		dsBase.insert();

		var baseData = dsBase.getData('#');

		view.set('#btnSave.disabled', false);
		view.set('#btnDeli.disabled', true);
		view.set('#btnDisDeli.disabled', true);
		view.set('#btnReceipts.disabled', true);
		view.set('#btnRmvLine.disabled', false);
		if (salePoCodeParam != null && salePoCodeParam != '') {
			ajGeSoBase.set('parameter', salePoCodeParam);
			ajGeSoBase.execute(function(re) {
				baseData.set({
					salePoCode : re.salePoCode,
					outType : "A",
					payment : re.payment,
					salePoCode : re.salePoCode,
					payment : re.payment,
					cltCode : re.cltCode,
					cltName : re.cltName,
					cltAdd : re.cltAdd,
					phone : re.phone,
					payCon : re.payCon,
					disctCmmt : re.disctCmmt,
				});
			});
		} else {
			baseData.set({
				outType : "B",
			});
		}
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
		flushCode = baseData.get('saleCode');
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

// 发货
// @Bind #btnDeli.onClick
!function(dsBase, dlgDeli) {
	var baseData = dsBase.getData('#');
	baseData.set({
		deliDate : new Date()
	});
	dlgDeli.show();
};

// 取消发货
// @Bind #btnDisDeli.onClick
!function(dlgDeli, dsBase, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('saleCode');
	uaSaveOrUpdate.set('parameter', UserActionType.DIS_SUBMIT);
	uaSaveOrUpdate.execute();
	// 根据单据号刷新数据
	flushData(flushCode);
	dlgDeli.hide();
};

// 收款
// @Bind #btnReceipts.onClick
!function(dsBase, dlgPay, dsPaySure) {
	var baseData = dsBase.getData('#');
	var totSaleAmt = baseData.get('totSaleAmt');
	var totPay = baseData.get('totPay');
	var totLeftPay = (totSaleAmt - totPay).toFixed(2);
	dsPaySure.clear();
	dsPaySure.insert({
		payDate : new Date(),
		totSaleAmt : totSaleAmt,
		totPay : totPay,
		totLeftPay : totLeftPay
	});
	dlgPay.show();
};

// 发货确认
// @Bind #btnSureDeli.onClick
!function(dlgDeli, dsBase, uaSaveOrUpdate) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('saleCode');
	uaSaveOrUpdate.set('parameter', UserActionType.SUBMIT);
	uaSaveOrUpdate.execute();
	// 根据单据号刷新数据
	flushData(flushCode);
	dlgDeli.hide();
};

// 发货取消
// @Bind #btnCancelDeli.onClick
!function(dlgDeli) {
	dlgDeli.hide();
};

// 收款确认
// @Bind #btnSurePay.onClick
!function(dlgPay, dsBase, uaSavePay) {
	var flushCode = "";
	var baseData = dsBase.getData('#');
	flushCode = baseData.get('saleCode');
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

// @Bind @dtBase.onDataChange
!function(self, arg, dsItem, dsBase, ajGetBatchInfo, ajGetCltInfo,
		ajGetDedMoney) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	var itemData = dsItem.getData();
	var checkStCode = "";
	var flag = true;
	if (newValue != null && newValue != '') {
		self.disableListeners();
		if (property == 'batchCode') {
			// 校验页面上的货号是否有重复
			if (itemData && itemData.entityCount > 0) {
				itemData.each(function(e) {
					checkStCode = e.get('batchCode');
					if (checkStCode == newValue) {
						flag = false;
						return;
					}
				});
			}
			if (!flag) {
				playSound(audioUrl.alarm);
				currEntity.set({
					text : '货号：' + newValue + '  已存在！',
				});
				currEntity
						.setMessages('batchCode', '货号：' + newValue + '  已存在！');
			} else {
				ajGetBatchInfo.set('parameter', newValue);
				ajGetBatchInfo.execute(function(re) {
					if (re) {

						dsItem.insert(re);
						// 设置折扣默认为空，折扣比例为0.00%,销售价等于零售价
						dsItem.getData('#').set({
							"disctScale" : '0.00%',
							"salePrice" : re.retPrice
						});
						// 设置行项目号
						setItemCode();
						// 计算数值
						calcuNum();
						// 清空PKID
						// clearItemId();
						// 清空错误信息
						currEntity.set({
							batchCode : null,
							text : null,
						});
					} else {
						playSound(audioUrl.alarm);
						currEntity.set({
							text : '输入的货号有误！',
						});
						currEntity.setMessages('batchCode', '输入的货号有误或者库存不够！');
					}
				});
			}
		} else if (property == 'cltCode') {
			// 根据客户编码带信息
			ajGetCltInfo.set('parameter', newValue);
			var re = ajGetCltInfo.execute();
			if (re) {
				var intclientCode = re.intclientCode;
				var intclientName = null;
				if (intclientCode) {
					ajGetCltInfo.set('parameter', intclientCode);
					var re1 = ajGetCltInfo.execute();
					if (re1) {
						intclientName = re1.cltName;
					}
				}
				currEntity.set({
					cltName : re.cltName,
					cltAdd : re.adr1,
					phone : re.conPhone1,
					payCon : re.payment,
					disctCmmt : re.discountDesc,
					intclientCode : intclientCode,
					intclientName : intclientName
				});
			}
			// } else if (property == 'intclientCode') {
			// // 根据推荐客户编码带信息
			// ajGetCltInfo.set('parameter', newValue);
			// var re1 = ajGetCltInfo.execute();
			// if (re1) {
			// currEntity.set({
			// intclientName : re1.cltName,
			// });
			// } else {
			// currEntity.set({
			// intclientName : null,
			// });
			// }
		} else if (property == 'dedtBp') {
			if (newValue * 1 > 0) {
				currEntity.setMessages(property, '请输入负数的抵扣积分！');
				dorado.MessageBox.alert('请输入负数的抵扣积分！');
			} else {
				ajGetDedMoney.execute(function(re) {
					if (re) {
						currEntity.set({
							dedtMoney : (newValue * 1 * re.ymoney / xbp)
									.toFixed(2)
						});
					}
				});
			}
		} else if (property == 'cltNowBp' || property == 'intclientNowBp') {
			if (newValue * 1 < 0) {
				currEntity.setMessages(property, '请输入正数的积分！');
				dorado.MessageBox.alert('请输入正数的积分！');
			}
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
				nums : 1
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
	var totSaleAmt = 0;
	var totWeight = 0;
	var dedtMoney = baseData.get('dedtMoney');
	var saleAmt = 0;
	var weight = 0;
	if (itemData && itemData.entityCount > 0) {
		itemData.each(function(e) {
			saleAmt = 0;
			if (e.get('salePrice')) {
				saleAmt = e.get('salePrice');
			}
			totSaleAmt = totSaleAmt * 1 + saleAmt * 1;
			weight = 0;
			if (e.get('weight')) {
				weight = e.get('weight');
			}
			totWeight = totWeight * 1 + weight * 1;
		});
		baseData.set({
			totSaleAmt : (totSaleAmt * 1 + dedtMoney * 1).toFixed(2),
			totNum : itemData.entityCount,
			totWeight : totWeight.toFixed(2)
		});
	}
};

function clearItemId() {
	var dsItem = view.id('dsItem');
	var itemData = dsItem.getData();
	if (itemData && itemData.entityCount > 0) {
		itemData.each(function(e) {
			e.set('pkId', null);
		});
	}
};

// @Bind @dtItem.onDataChange
!function(self, arg, dsItem, dsBase) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	var retPrice = 0;
	var disctScale = 0;
	if (currEntity.get('retPrice') != null && currEntity.get('retPrice') != '') {
		retPrice = currEntity.get('retPrice');
	}
	if (property == 'disct') {
		if (newValue == null || newValue == '') {
			newValue = 0;
		}
		disctScale = (newValue / retPrice * 100).toFixed(2);
		currEntity.set({
			disctScale : disctScale + "%",
			salePrice : (retPrice * 1 + newValue * 1).toFixed(2)
		});
		calcuNum();
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
		if (baseData.validate() != 'ok') {
			flagReq = false;
		}
		itemData.each(function(e) {
			if (e.validate() != 'ok') {
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
			dsItem.flushAsync(function(item) {
			});
			dsPay.flushAsync();
			result = re.get('status');
			// 根据状态设置按钮可用性
			if (action == 'mod' || action == 'new') {
				if (result == '10') {
					// 已创建
					view.set('#btnSave.disabled', false);
					view.set('#btnDeli.disabled', false);
					view.set('#btnDisDeli.disabled', true);
					view.set('#btnReceipts.disabled', false);
					view.set('#btnRmvLine.disabled', false);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				} else if (result == '20') {
					// 已发货
					view.set('#btnSave.disabled', true);
					view.set('#btnDeli.disabled', true);
					view.set('#btnDisDeli.disabled', false);
					view.set('#btnReceipts.disabled', false);
					view.set('#btnRmvLine.disabled', true);

					dsBase.set('readOnly', false);
					dsItem.set('readOnly', false);
				}
			} else if (action == 'watch') {
				view.set('#btnSave.disabled', true);
				view.set('#btnDeli.disabled', true);
				view.set('#btnDisDeli.disabled', true);
				view.set('#btnReceipts.disabled', true);
				view.set('#btnRmvLine.disabled', true);

				dsBase.set('readOnly', true);
				dsItem.set('readOnly', true);
			}
		} else {
			// 已删除
			view.set('#btnSave.disabled', true);
			view.set('#btnDeli.disabled', true);
			view.set('#btnDisDeli.disabled', true);
			view.set('#btnReceipts.disabled', true);
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
	var ajCheckBatch = view.id('ajCheckBatch');
	var baseData = dsBase.getData('#');
	var itemData = dsItem.getData();
	var dataCount = itemData.entityCount;
	var pkId = baseData.get('pkId');
	var status = "";// 数据库状态
	var flushCode = "";// 刷新数据的PO号
	var ajGetCltInfo = view.id('ajGetCltInfo');
	// 校验数据库的状态
	if (pkId) {
		flushCode = baseData.get('saleCode');
		status = getStatus(flushCode);
	}
	var flg = true;
	if (action == UserActionType.SAVE) {
		if (status != CommonStatus.CREATED && status != null && status != '') {
			flg = false;
		}
	} else if (action == UserActionType.SUBMIT) {
		if (status != CommonStatus.CREATED) {
			flg = false;
		}
	} else if (action == UserActionType.DEL) {
		if (status != CommonStatus.DELETED) {
			flg = false;
		}
	}
	if (!flg) {
		dorado.MessageBox.alert('当前数据状态不符合保存条件！将刷新数据');
		flushData(flushCode);
		throw new dorado.AbortException();
	} else {
		if (dataCount > 0) {
			// 校验单品属性的数量
			ajCheckBatch.set('parameter', itemData);
			var re = ajCheckBatch.execute();
			var erroCodes = re.erroCodes;
			if (erroCodes) {
				dorado.MessageBox.alert("存在已经被发货或不存在的货号： " + erroCodes);
				throw new dorado.AbortException();
			} else {
				// 校验客户编码和推荐客户编码是否存在，不存在不能填写积分

				var cltCode = baseData.get('cltCode');
				var cltNowBp = baseData.get('cltNowBp');
				var intclientCode = baseData.get('intclientCode');
				var intclientNowBp = baseData.get('intclientNowBp');
				// 根据客户编码带信息
				if (cltNowBp) {
					if (cltCode) {
						ajGetCltInfo.set('parameter', cltCode);
						var re1 = ajGetCltInfo.execute();
						if (!re1) {
							dorado.MessageBox.alert("请填写系统存在的客户编码再填写积分！");
							throw new dorado.AbortException();
						}
					} else {
						dorado.MessageBox.alert("请填写客户编码再填写积分！");
						throw new dorado.AbortException();
					}
				}
				// 根据推荐客户编码带信息
				if (intclientNowBp) {
					if (intclientCode) {
						ajGetCltInfo.set('parameter', intclientCode);
						var re2 = ajGetCltInfo.execute();
						if (!re2) {
							dorado.MessageBox.alert("请填写系统存在的客户编码再填写推荐客户积分！");
							throw new dorado.AbortException();
						}
					} else {
						dorado.MessageBox.alert("请填写推荐客户编码再填写推荐客户积分！");
						throw new dorado.AbortException();
					}
				}

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

// @Bind #disctColumn.onRenderCell
// @Bind #remarkColumn.onRenderCell
!function(self, arg) {
	arg.dom.style.background = "#43CD80";
	arg.processDefault = true;
};

// @Bind #btnExp.onClick
!function(self, arg, download2Export, dsBase) {
	var baseData = dsBase.getData('#');
	var checkCode = baseData.get('saleCode');
	download2Export.set('parameter', {
		checkCode : checkCode
	});
	download2Export.execute();
};
