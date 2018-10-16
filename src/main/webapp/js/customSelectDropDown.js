(function() {
	CustomSelectDropDown = $extend(dorado.widget.CustomDropDown, {
		ATTRIBUTES : {
			control : {
				writeBeforeReady : true,
				innerComponent : ""
			},
			autoOpen : {
				defaultValue : false
			},
			buttonVisible : {
				defaultValue : true
			},
			editable : {
				defaultValue : true
			},
			ignored : {
				defaultValue : false
			},
			postValueOnSelect : {
				defaultValue : true
			},
			dataProvider : {
				defaultValue : 'enumPR#getEnumvByEnumCode'
			},
			parameter : {
				defaultValue : ''
			},
			dataType : {
				defaultValue : 'dtAlloveEnumv'
			},
			displayColumn : {
				defaultValue : [ 'enumvName' ]
			},
			width : {
				defaultValue : 200
			},
			height : {
				defaultValue : 200
			},
			keyProperty : {
				defaultValue : 'enumvCode'
			},
			unusedKeyList : {
				defaultValue : []
			},
			dataSet : {
				writeBeforeReady : true
			}
		},
		createDefaultDataSet : function() {
			var datatype;

			// 初始化DataType
			datatype = new dorado.AggregationDataType({
				elementDataType : this._dataType
			});
			datatype.getElementDataType();

			//设置DataProvider
			this._dataProvider = dorado.DataProvider.create(this._dataProvider);
			this._dataSet = new dorado.widget.DataSet({
				dataProvider : this._dataProvider,
				dataType : datatype
			});
			return this._dataSet;
		},
		flushData : function() {
			var _self = this, dataSet = this._dataSet;

			if(!dorado.Object.isInstanceOf(dataSet, dorado.widget.DataSet)){
				dataSet = this.createDefaultDataSet();
			}

			// 加载并删除不要显示的下拉列表
			dataSet.set('parameter', this._parameter);
			dataSet.flushAsync(function(item){
				if(_self._unusedKeyList.length > 0){
					var entityList = item.toJSON();
					for (var index = entityList.length; index > 0; index--) {
						var buffer = entityList[index - 1];

						_self._unusedKeyList.each(function(key){
							if (buffer[_self._keyProperty] === key) {
								entityList.removeAt(index - 1);
								return false;
							}
						});
					}
					dataSet.setData(entityList);
				}
			});
			return dataSet;
		},
		createDefaultGrid : function() {
			var dataSet = this._dataSet, grid;

			if(!dorado.Object.isInstanceOf(dataSet, dorado.widget.DataSet)){
				dataSet = this.flushData();
			}

			// 设置DataGrid
			grid = new dorado.widget.DataGrid({
				dataSet : dataSet,
				width : this._width,
				height : this._height,
				selectionMode : 'multiRows'
			});

			// 添加列
			grid.addColumn(new dorado.widget.grid.RowNumColumn());
			grid.addColumn(new dorado.widget.grid.RowSelectorColumn());
			this._displayColumn.each(function(item) {
				grid.addColumn({
					name : item,
					property : item
				});
			});

			return grid;
		},
		createDropDownBox : function() {
			var box, control = this._control;
			if (dorado.Object.isInstanceOf(control, dorado.widget.FloatControl)) {
				box = control;
			} else {
				if (control == undefined) {
					control = this.createDefaultGrid();
					this._control = control;
				}
				box = $invokeSuper.call(this, arguments);
				box.bind("beforeShow", doShowBox);
			}
			return box;

			function doShowBox() {
				var $box = jQuery(box.getDom().firstChild);
				var boxWidth = $box.width();
				var boxHeight = $box.height();
				var $dom = jQuery(control.getDom()), buffer;
				var realWidth = $dom.outerWidth(), dropDown;
				var realHeight = $dom.outerHeight(), shouldRefresh;

				dropDown = box._dropDown;

				buffer = boxWidth - dropDown._edgeWidth;
				if (realWidth < buffer) {
					control.set("width", buffer);
					shouldRefresh = true;
				}
				buffer = boxWidth - dropDown._edgeHeight;
				if (realHeight < buffer) {
					control.set("height", buffer);
					shouldRefresh = true;
				}
				if (shouldRefresh) {
					control.refresh();
				}
			}
		},
		getSelection : function() {
			var selection = this._control.get('selection');
			return selection;
		},
		clearSelection : function() {
			this._control.unselectAll();
		},
		getData : function() {
			var dataSet = this._dataSet;
			if (dorado.Object.isInstanceOf(dataSet, dorado.widget.DataSet)) {
				return dataSet.getData();
			} else {
				return null;
			}
		}
	});
})();