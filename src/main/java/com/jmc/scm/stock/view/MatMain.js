/**
 * 全局变量
 */
// 当前时间
var today = new Date();
// 登录人
var logUser = "${loginUsername}";
// 出库还是入库标记
var flgInOut;

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

// @Bind #btnFirstIn.onClick
!function(self, arg) {
	var dsAddMain = view.id('dsAddMain');
	dsAddMain.clear();
	dsAddMain.insert({
		storageDate : new Date()
	});
	view.id('dlgAddFirst').show();
};

// @Bind #btnSave.onClick
!function(self, arg) {
	// 保存前校验
	doValidateBeforeSave();
	// 将抬头信息写入明细的dataSet
	var baseData = view.id('dsAddMain').getData('#');
	var itemData = view.id('dsAddDetail').getData();
	itemData.each(function(e) {
		e.set({
			storageDate : baseData.get('storageDate'),
			supCode : baseData.get('supCode'),
			supName : baseData.get('supName'),
			supNo : baseData.get('supNo'),
			remarks : baseData.get('remarks'),
		});
	});
	var uaSave = view.id('uaSave');
	var dlgAddFirst = view.id('dlgAddFirst');
	uaSave.execute();
	doSearch();
	dlgAddFirst.hide();
};

// @Bind #btnCancel.onClick
!function(self, arg) {
	var dlgAddFirst = view.id('dlgAddFirst');
	dlgAddFirst.hide();
};

// @Bind #btnOut.onClick
// @Bind #btnIn.onClick
!function(self, arg) {
	var btnId = self.get('id');
	var curr = view.id('dsMain').getData('#');
	var dsMod = view.id('dsMod');
	var dlgMod = view.id('dlgMod');
	if (curr) {
		dsMod.clear();
		dsMod.insert({
			matCode : curr.get('matCode'),
			matName : curr.get('matName'),
			storageDate : new Date(),
		});
		// 相关字段名称修改和隐藏
		if (btnId === 'btnIn') {
			view.set('#storageDateForm.label', '入库日期');
			view.set('#numsForm.label', '入库数量');

			view.set('#amountForm.readOnly', false);
			view.set('#saleCodeForm.visible', false);
			flgInOut = "in";
		} else if (btnId === 'btnOut') {
			view.set('#storageDateForm.label', '出库日期');
			view.set('#numsForm.label', '出库数量');

			view.set('#amountForm.readOnly', true);
			view.set('#saleCodeForm.visible', true);
			flgInOut = "out";
		}
		dlgMod.show();
	}
};

// @Bind #btnSaveMod.onClick
!function(self, arg) {
	var dlgMod = view.id('dlgMod');
	var uaSaveMod = view.id('uaSaveMod');
	var ajCheckStock = view.id('ajCheckStock');
	// 校验必填项
	var flagReq = true;
	var modData = view.id('dsMod').getData('#');
	if (modData.validate() !== 'ok') {
		flagReq = false;
	}
	if (!flagReq) {
		dorado.MessageBox.alert("请校验数据必填项！");
	} else {
		if (flgInOut === 'out') {
			// 出库的时候，校验数量、金额不能大于已有库存
			ajCheckStock.set('parameter', modData);
			var reCheck = ajCheckStock.execute();
			if (reCheck && reCheck !== '') {
				dorado.MessageBox.alert(reCheck);
				throw new dorado.AbortException();
			}
		}
		uaSaveMod.set('parameter', flgInOut);
		uaSaveMod.execute();
		if (flgInOut === 'in') {
			dorado.MessageBox.alert("补货入库成功！");
		} else if (flgInOut === 'out') {
			dorado.MessageBox.alert("出库成功！");
		}
		doSearch();
		dlgMod.hide();
	}
};

// @Bind #btnCancelMod.onClick
!function(self, arg) {
	var dlgMod = view.id('dlgMod');
	dlgMod.hide();
};

// @Bind #btnImg.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		if (!curr.get('imgId')) {
			var guid = new GUID();
			curr.set({
				imgId : guid.newGUID(),
			});
		}
		view.id('dlgUpImg').show();
	}
};

// @Bind #btnWatch.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	var dsDetail = view.id('dsDetail');
	var dlgWatchDetail = view.id('dlgWatchDetail');
	if (curr) {
		dsDetail.set('parameter', {
			matCode : curr.get('matCode'),
		});
		dsDetail.flushAsync();
		dlgWatchDetail.show();
	}
};

// @Bind #dgMain.onDataRowDoubleClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	var dsDetail = view.id('dsDetail');
	var dlgWatchDetail = view.id('dlgWatchDetail');
	if (curr) {
		dsDetail.set('parameter', {
			matCode : curr.get('matCode'),
		});
		dsDetail.flushAsync();
		dlgWatchDetail.show();
	}
};

// @Bind #btnClose.onClick
!function(self, arg) {
	var dlgWatchDetail = view.id('dlgWatchDetail');
	dlgWatchDetail.hide();
};

function doSearch() {
	var dsMain = view.id('dsMain');
	var dsCondition = view.id('dsCondition');
	dsMain.set('parameter', dsCondition.getData('#'));
	dsMain.flushAsync();
}

function doValidateBeforeSave(action) {
	// var dsBase = view.id('dsAddMain');
	var dsItem = view.id('dsAddDetail');
	var ajCheckMat = view.id('ajCheckMat');
	// var baseData = dsBase.getData('#');
	var itemData = dsItem.getData();
	var dataCount = itemData.entityCount;
	// var pkId = baseData.get('pkId');
	// var status = "";// 数据库状态
	// var flushCode = "";// 刷新数据的PO号
	// // 校验数据库的状态
	// if (pkId) {
	// flushCode = baseData.get('prestoCode');
	// status = getStatus(flushCode);
	// }
	var flg = true;
	// if (action == UserActionType.SAVE) {
	// if (status!== CommonStatus.CREATED && status!== null && status!== '') {
	// flg = false;
	// }
	// } else if (action == UserActionType.SUBMIT) {
	// if (status!== CommonStatus.CREATED) {
	// flg = false;
	// }
	// } else if (action == UserActionType.DEL) {
	// if (status!== CommonStatus.DELETED) {
	// flg = false;
	// }
	// }
	if (!flg) {
		dorado.MessageBox.alert('当前数据状态不符合保存条件！将刷新数据');
		flushData(flushCode);
		throw new dorado.AbortException();
	} else {
		if (dataCount > 0) {
			// 校验页面上的物料号不能重复
			var checkArray = [];
			itemData.each(function(e) {
				checkArray.push(e.get('matCode'));
			});
			if (isRepeat(checkArray)) {
				dorado.MessageBox.alert('存在相同的物料号！');
				throw new dorado.AbortException();
			}
			// 校验要保存的行项目在库存主表中没有
			ajCheckMat.set('parameter', itemData);
			var re = ajCheckMat.execute();
			if (re && re !== '') {
				dorado.MessageBox.alert("以下物料号已经入库！：" + re);
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

/**
 * 校验主子表数据的必填性和错误行
 */
function validateData() {
	var flagErro = true;
	var flagReq = true;
	var baseData = view.id('dsAddMain').getData('#');
	var itemData = view.id('dsAddDetail').getData();
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

// @Bind #triggerSup.onExecute
!function() {
	view.set('#_IframeSup.path', supUrl.all);
	view.id('_dlgSup').show();
};

// 供应商主数据公用框回调
window.setSupInfo = function(re) {
	var supCode = re.get('supCode');
	var supName = re.get('supName');
	var conData = view.id('dsAddMain').getData('#');
	if (conData) {
		conData.set('supCode', supCode);
		conData.set('supName', supName);
	}
	view.id('_dlgSup').hide();
};

// @Bind @dtAddMain.onDataChange
!function(self, arg, ajGetSupInfo) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	if (newValue !== null && newValue !== '') {
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
// @Bind #btnAddLine.onClick
!function() {
	var dsAddDetail = view.id('dsAddDetail');
	dsAddDetail.insert();
};

// @Bind #btnRemoveLine.onClick
!function() {
	var curr = view.id('dsAddDetail').getData('#');
	if (curr) {
		curr.remove();
	}
};

// @Bind @dtMod.onDataChange
!function(self, arg, ajGetSupInfo) {
	var property = arg.property;
	var newValue = arg.newValue;
	var currEntity = arg.entity;// 当前被修改数据的entity
	var ajGetStock = view.id('ajGetStock');
	// 现有库存的数量
	var stockNum = 0;
	// 现有库存金额
	var stockAmount = 0;
	var nowAmount = null;
	if (flgInOut === 'out') {
		if (property == 'nums') {
			self.disableListeners();
			if (newValue == null || newValue === '') {
				newValue = 0;
			}
			ajGetStock.set('parameter', currEntity.get('matCode'));
			ajGetStock.execute(function(re) {
				if (re) {
					if (re.nums) {
						stockNum = re.nums;
					}
					if (re.amount) {
						stockAmount = re.amount;
					}
					nowAmount = (newValue * 1 * stockAmount * 1 / stockNum)
					.toFixed(3);
					if(nowAmount == 0){
						nowAmount = null;
					}
					currEntity.set('amount',nowAmount);
				}
			});
			self.enableListeners();
		}
	}
};

// @Bind #dbvImage.onCreate
!function(self) {
	self.set("renderer", new ImageLabelRenderer({
		labelHeight : 30
	}));
};

var ImageLabelRenderer = $extend(dorado.widget.blockview.ImageBlockRenderer, {
	render : function(dom, arg) {
		var entity = arg.data;
		var path = entity.get('fileHttpPath');
		var imageDom = $DomUtils.xCreate({
			tagName : "img",
			src : path,
			style : {
				width : '90%',
				height : '95%',
				margin : 'auto',
				display : 'block',
				padding : '2px'
			}
		});
		dorado.TipManager.initTip(imageDom, {
			content : {
				tagName : "IMG",
				src : path,
				style : "width: 400px; height: 400px; margin: 8px"
			},
		});

		$(dom).empty();
		dom.appendChild(imageDom);
	}
});

// @Bind #imgDel.onClick
!function() {
	var refImg = view.id('dsMain').getData('#.#refImg');
	if (refImg) {
		view.id('uaDelImg').execute(function() {
			refImg.remove();
		});
	}
};

// @Bind #btnCloseImg.onClick
!function() {
	view.id('dlgUpImg').hide();
};

// @Bind #uploadPicture.onFileUploaded
!function(self, arg) {
	var retMsg = arg.returnValue;
	var currData = view.id('dsMain').getData('#');
	var imgId = currData.get('imgId');
	var refImg = view.id('dsMain').getData('#.refImg');
	var uaSaveImg = view.id('uaSaveImg');
	if (retMsg.sName) {
		// 保存附件信息到数据库
		uaSaveImg.set('parameter', {
			abPath : retMsg.absolutePath,
			sName : retMsg.sName,
			bizCode : imgId,
		});
		uaSaveImg.execute(function(re) {
			refImg.insert({
				fileHttpPath : re.httpPath,
				pkId : re.pkId,
			});
		});
	} else {
		dorado.MessageBox.alert('图片上传失败，请重试！');
	}
};

// @Bind #btnSaveImg.onClick
!function() {
	var uaUpdateMain = view.id('uaUpdateMain');
	var dlgUpImg = view.id('dlgUpImg');
	var data = view.id('dsMain').getData('#.refImg');
	if (data && data.entityCount > 0) {
		uaUpdateMain.execute(function() {
			dlgUpImg.hide();
		});
	} else {
		dorado.MessageBox.alert('请至少上传一张图片！');
	}
};

// @Bind #imgDelAll.onClick
!function() {
	var dlgUpImg = view.id('dlgUpImg');
	var data = view.id('dsMain').getData('#.refImg');
	if (data && data.entityCount > 0) {
		uaDelImgs.set('successMessage', '删除成功！');
		uaDelImg.execute();
		dlgUpImg.hide();
		data.clear();
	}
};
// @Bind #dlgUpImg.onHide
!function() {
	// 校验当前物料有无图片ID，没有的话，代表没有点保存，需要删除数据库所有附件
	var ajGetStock = view.id('ajGetStock');
	var uaDelImgs = view.id('uaDelImgs');
	var currData = view.id('dsMain').getData('#');
	var imgData = view.id('dsMain').getData('#.refImg');
	ajGetStock.set('parameter', currData.get('matCode'));
	if (imgData && imgData.entityCount > 0) {
		ajGetStock.execute(function(re) {
			if (re) {
				if (!re.imgId) {
					uaDelImgs.set('successMessage', null);
					uaDelImgs.execute();
					imgData.clear();
				}
			}
		});
	}

};