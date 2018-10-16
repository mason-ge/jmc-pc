(function() {
	CustomExportAction = $extend(dorado.widget.Export2ReportAction, {
		ATTRIBUTES : {
			/**
			 * 后台excel处理方法
			 */
			service : {
				defaultValue : 'customExportPR#generateReportFile'
			},
			/**
			 * 顶层的view对象，也就是功能页面的view对象
			 */
			topView : {
				setter : function(topView) {
					this._topView = topView;
				}
			}
		},
		getAjaxOptions : function() {
			// 将导出控件的view对象设置为功能页面的view对象，以便获取控件的数据
			this._view = this._topView;
			var parameter = this._generateReportParameter();
			var jsonData = {
				action : "remote-service",
				service : this._service,
				parameter : dorado.JSON.evaluate(parameter),
				context : (this._view ? this._view.get("context") : null)
			};
			return dorado.Object.apply({
				jsonData : jsonData,
				timeout : this._timeout,
				batchable : this._batchable
			}, $setting["ajax.remoteServiceOptions"]);
		}
	});
})();