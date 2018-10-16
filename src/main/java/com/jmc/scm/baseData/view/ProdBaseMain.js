// @Bind view.onReady
!function(dsCondition, dsShop, dsTree, tree) {
	dsCondition.insert();
	dsTree.set('parameter', 'PLYJ');
	dsTree.flushAsync();
	tree.setCurrentItemDom();
	calcuShopNum();
};

// @Bind #tree.onDataRowClick
!function() {
	doSearch();
};
// @Bind #tree.onExpand
!function() {
	view.set('#btnSearch.disabled', false);
};

// @Bind #btnSearch.onClick
!function() {
	doSearch();
};

// @Bind #dbvMain.onCreate
!function(self) {
	self.set("renderer", new ImageLabelRenderer({
		labelHeight : 30
	}));
};

var ImageLabelRenderer = $extend(dorado.widget.blockview.ImageBlockRenderer, {
	render : function(dom, arg) {
		var entity = arg.data;
		var image = '#', imageDom = null, labelDom = null;
		if (entity.get('img')) {
			image = entity.get('img');
		}else{
			image = 'images/no_photo.jpg';
		}
		imageDom = $DomUtils.xCreate({
			tagName : "img",
			src : image,
			style : {
				height : '80%',
				margin : 'auto',
				display : 'block',
				padding : '1px'
			}
		});
		labelDom = $DomUtils.xCreate({
			tagName : "label",
			content : "商品编码：" + entity.get('prodCode') + "\n"
					+ entity.get('prodName'),
			style : {
				width : '80%',
				display : 'block',
				textAlign : 'center'
			}
		});
		$(dom).empty();
		dom.appendChild(imageDom);
		dom.appendChild(labelDom);
	}
});

// 重置按钮点击
// @Bind #btnReset.onClick
!function(dsCondition) {
	dsCondition.clear();
	dsCondition.insert({});
};

// @Bind #btnCreate.onClick
!function(self, arg) {
	var detailUrl = "com.jmc.scm.baseData.view.ProdBaseDetail.d?action=new";
	var detailTitle = "创建商品主数据";
	top.competingEntity = arg.data;
	top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
};

// @Bind #btnMod.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('prodCode');
		var detailUrl = "com.jmc.scm.baseData.view.ProdBaseDetail.d?action=mod&docCode="
				+ currCode;
		var detailTitle = "商品主数据详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// @Bind #btnWatch.onClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('prodCode');
		var detailUrl = "com.jmc.scm.baseData.view.ProdBaseDetail.d?action=watch&docCode="
				+ currCode;
		var detailTitle = "商品主数据详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// dbvMain双击事件
// @Bind #dbvMain.onBlockDoubleClick
!function(self, arg) {
	var curr = view.id('dsMain').getData('#');
	if (curr) {
		var currCode = curr.get('prodCode');
		var detailUrl = "com.jmc.scm.baseData.view.ProdBaseDetail.d?action=watch&docCode="
				+ currCode;
		var detailTitle = "商品主数据详情页";
		top.competingEntity = arg.data;
		top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
	}
};

// @Bind #btnShow.onClick
!function(dsMain) {
	var dsImg = view.id('_dsImageViewer');
	var dlgImg = view.id('_dialogImageViewer');
	var baseData = dsMain.getData('#');
	if (baseData) {
		var prodCode = baseData.get('prodCode');
		if (prodCode) {
			dsImg.set('parameter', prodCode);
			dsImg.flushAsync(function(re) {
				dlgImg.show();
			});

		} else {
			dorado.MessageBox.alert("请先生成主数据！");
		}
	}
};

// @Bind #btnShop.onClick
!function(dsMain, dlgShop, dsShop) {
	var mainData = dsMain.getData('#');
	if (mainData) {
		var prodCode = mainData.get('prodCode');
		if (prodCode) {
			dsShop.clear();
			dsShop.insert({
				prodCode : prodCode,
				prodName : mainData.get('prodName'),
				nums : 1,
			});
			dlgShop.show();
		}
	} else {
		dorado.MessageBox.alert("请选择一个商品！");
	}
};
// @Bind #btnSure.onClick
!function(dsMain, dlgShop, uaSaveShop) {
	uaSaveShop.execute(function() {
		dorado.MessageBox.alert("加入购物车成功！");
		dlgShop.hide();
		calcuShopNum();
	});
};

// @Bind #btnCancel.onClick
!function(dsMain, dlgShop) {
	dlgShop.hide();
};

function calcuShopNum() {
	var ajGetShopNum = view.id('ajGetShopNum');
	// 获取已加入购物车的数据
	ajGetShopNum.execute(function(re) {
		view.set('#btnShop.caption', '加入购物车' + "(" + re + ")");
	});
}
// @Bind #btnSettle.onClick
!function(self, arg) {
	var detailUrl = "com.jmc.scm.sale.view.SalePoDetail.d?action=new";
	var detailTitle = "销售订单创建";
	top.competingEntity = arg.data;
	top.openUrlInFrameTab(detailUrl, detailTitle, "", true);
};

function doSearch() {
	var dsMain = view.id('dsMain');
	var condition = view.get('#dsCondition.data').toJSON();
	var sort = view.id('tree').getCurrentItem()._data._data;
	dsMain.set('parameter', {
		condition : condition,
		sort : sort
	});
	dsMain.flushAsync();
	calcuShopNum();
};
