var bizCode = '';
//@Bind view.onReady
!function(dsAttHead,dsAtt) {
	if ('${param.bizCode}' != null && '${param.bizCode}' != '') {
		bizCode='${param.bizCode}';
		dsAtt.set('parameter',{
			"bizCode" : bizCode
		}).flushAsync();
	}else{
		dsAtt.clear();
		var guid = new GUID();
		var uuid = guid.newGUID();
		bizCode = uuid;
	}
	if ('${param.state}' === 'mod') {
		view.id("btnUploadAtt").set('visible',true);
		view.id("btnDeleteAtt").set('visible',true);	
	}else if('${param.state}' === 'watch'){
		view.id("btnUploadAtt").set('visible',false);
		view.id("btnDeleteAtt").set('visible',false);
	}
}; 

// @Bind #uploadAtt.onFileUploaded
!function(arg) {
	var re = arg.returnValue;
	var abPath = re.absolutePath;
	var sName = re.sName;
	view.id("updateAttAction").set('parameter',{ 
		abPath : abPath,    
		sName : sName,
		bizCode : bizCode,
	}).execute(function(){
		var dsAtt = view.id("dsAtt");
		dsAtt.set('parameter',bizCode);
		dsAtt.flushAsync();
	});
	
};

//@Bind #btnDeleteAtt.onClick
!function(self) {
	var pkIds = '';
	var selection = view.id('dgAtt').get('selection');
	if(selection.length > 0){
		selection.each(function(e){
			var id = e.get('pkId');
			pkIds = pkIds+id+",";
		});
	}else{
		var currentData = view.id("dsAtt").getData("#");
		if(currentData){
			pkIds = currentData.get("pkId")+",";
		}
	}
	if(pkIds != ''){
		view.id("deleteAttAction").set('parameter',{
			pkIds : pkIds.substring(0, pkIds.length-1)
		}).execute(function(){
			var dsAtt = view.id("dsAtt");
			dsAtt.set('parameter',bizCode);
			dsAtt.flushAsync();
		});
	}
};
/**
 * 下载附件
 * @param fileId
 */
//@Global
function downloadAttach(fileId){
	var daDownload = view.id("downloadAttAction");
	var b = new Base64();
	var codedId = b.encode(fileId);
	daDownload.set("parameter",{
		pathName : codedId
	});
	daDownload.execute();
}

//@Bind #fileNameCol.onRenderCell
!function(arg) {
	if(arg.data.get("fileAbsolutePath")!=null && arg.data.get("fileAbsolutePath")!=''){	
		arg.dom.innerHTML = "<a href='javascript:void(0)' onclick='javascript:downloadAttach(\"" + arg.data.get("fileAbsolutePath") + "\")'>" + arg.data.get("fileName") + "</a>";
	}
};
//@Bind #btnSureAtt.onClick
!function() {
	window.parent.setAttBizCode(bizCode);
};