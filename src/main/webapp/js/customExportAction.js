(function() {
	CustomExportAction = $extend(dorado.widget.Export2ReportAction, {
		ATTRIBUTES : {
			/**
			 * ��̨excel������
			 */
			service : {
				defaultValue : 'customExportPR#generateReportFile'
			},
			/**
			 * �����view����Ҳ���ǹ���ҳ���view����
			 */
			topView : {
				setter : function(topView) {
					this._topView = topView;
				}
			}
		},
		getAjaxOptions : function() {
			// �������ؼ���view��������Ϊ����ҳ���view�����Ա��ȡ�ؼ�������
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