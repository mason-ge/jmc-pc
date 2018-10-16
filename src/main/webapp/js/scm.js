/**
 * 重写toFixed方法，避免精度缺失问题
 * 
 * @param s
 * @returns
 */
Number.prototype.toFixed = function(s) {

	if (this < 0) {
		changenum = (parseInt(this * Math.pow(10, s) - 0.5) / Math.pow(10, s))
				.toString();
	} else {
		changenum = (parseInt(this * Math.pow(10, s) + 0.5) / Math.pow(10, s))
				.toString();
	}

	index = changenum.indexOf(".");
	if (index < 0 && s > 0) {
		changenum = changenum + ".";
		for (var i = 0; i < s; i++) {
			changenum = changenum + "0";
		}
	} else {
		index = changenum.length - index;
		for (var i = 0; i < (s - index) + 1; i++) {
			changenum = changenum + "0";
		}
	}
	return changenum;
};
function GUID() {
	this.date = new Date();

	/* 判断是否初始化过，如果初始化过以下代码，则以下代码将不再执行，实际中只执行一次 */
	if (typeof this.newGUID != 'function') {

		/* 生成GUID码 */
		GUID.prototype.newGUID = function() {
			this.date = new Date();
			var guidStr = '';
			sexadecimalDate = this.hexadecimal(this.getGUIDDate(), 16);
			sexadecimalTime = this.hexadecimal(this.getGUIDTime(), 16);
			for (var i = 0; i < 9; i++) {
				guidStr += Math.floor(Math.random() * 16).toString(16);
			}
			guidStr += sexadecimalDate;
			guidStr += sexadecimalTime;
			while (guidStr.length < 32) {
				guidStr += Math.floor(Math.random() * 16).toString(16);
			}
			return this.formatGUID(guidStr);
		};

		/*
		 * 功能：获取当前日期的GUID格式，即8位数的日期：19700101 返回值：返回GUID日期格式的字条串
		 */
		GUID.prototype.getGUIDDate = function() {
			return this.date.getFullYear()
					+ this.addZero(this.date.getMonth() + 1)
					+ this.addZero(this.date.getDay());
		};

		/*
		 * 功能：获取当前时间的GUID格式，即8位数的时间，包括毫秒，毫秒为2位数：12300933 返回值：返回GUID日期格式的字条串
		 */
		GUID.prototype.getGUIDTime = function() {
			return this.addZero(this.date.getHours())
					+ this.addZero(this.date.getMinutes())
					+ this.addZero(this.date.getSeconds())
					+ this.addZero(parseInt(this.date.getMilliseconds() / 10));
		};

		/*
		 * 功能: 为一位数的正整数前面添加0，如果是可以转成非NaN数字的字符串也可以实现 参数:
		 * 参数表示准备再前面添加0的数字或可以转换成数字的字符串 返回值: 如果符合条件，返回添加0后的字条串类型，否则返回自身的字符串
		 */
		GUID.prototype.addZero = function(num) {
			if (Number(num).toString() != 'NaN' && num >= 0 && num < 10) {
				return '0' + Math.floor(num);
			} else {
				return num.toString();
			}
		};

		/*
		 * 功能：将y进制的数值，转换为x进制的数值
		 * 参数：第1个参数表示欲转换的数值；第2个参数表示欲转换的进制；第3个参数可选，表示当前的进制数，如不写则为10 返回值：返回转换后的字符串
		 */
		GUID.prototype.hexadecimal = function(num, x, y) {
			if (y != undefined) {
				return parseInt(num.toString(), y).toString(x);
			} else {
				return parseInt(num.toString()).toString(x);
			}
		};

		/*
		 * 功能：格式化32位的字符串为GUID模式的字符串 参数：第1个参数表示32位的字符串 返回值：标准GUID格式的字符串
		 */
		GUID.prototype.formatGUID = function(guidStr) {
			var str1 = guidStr.slice(0, 8) + '-', str2 = guidStr.slice(8, 12)
					+ '-', str3 = guidStr.slice(12, 16) + '-', str4 = guidStr
					.slice(16, 20)
					+ '-', str5 = guidStr.slice(20);
			return str1 + str2 + str3 + str4 + str5;
		};
	}
}
/**
 * 
 * Base64 encode / decode
 * 
 * @author haitao.tu
 * @date 2010-04-26
 * @email tuhaitao@foxmail.com
 * 
 */

function Base64() {
	// private property
	_keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

	// public method for encoding
	this.encode = function(input) {
		var output = "";
		var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
		var i = 0;
		input = _utf8_encode(input);
		while (i < input.length) {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			} else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output + _keyStr.charAt(enc1) + _keyStr.charAt(enc2)
					+ _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
		}
		return output;
	};

	// public method for decoding
	this.decode = function(input) {
		var output = "";
		var chr1, chr2, chr3;
		var enc1, enc2, enc3, enc4;
		var i = 0;
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		while (i < input.length) {
			enc1 = _keyStr.indexOf(input.charAt(i++));
			enc2 = _keyStr.indexOf(input.charAt(i++));
			enc3 = _keyStr.indexOf(input.charAt(i++));
			enc4 = _keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
		}
		output = _utf8_decode(output);
		return output;
	};

	// private method for UTF-8 encoding
	_utf8_encode = function(string) {
		string = string.replace(/\r\n/g, "\n");
		var utftext = "";
		for (var n = 0; n < string.length; n++) {
			var c = string.charCodeAt(n);
			if (c < 128) {
				utftext += String.fromCharCode(c);
			} else if ((c > 127) && (c < 2048)) {
				utftext += String.fromCharCode((c >> 6) | 192);
				utftext += String.fromCharCode((c & 63) | 128);
			} else {
				utftext += String.fromCharCode((c >> 12) | 224);
				utftext += String.fromCharCode(((c >> 6) & 63) | 128);
				utftext += String.fromCharCode((c & 63) | 128);
			}

		}
		return utftext;
	};

	// private method for UTF-8 decoding
	_utf8_decode = function(utftext) {
		var string = "";
		var i = 0;
		var c = c1 = c2 = 0;
		while (i < utftext.length) {
			c = utftext.charCodeAt(i);
			if (c < 128) {
				string += String.fromCharCode(c);
				i++;
			} else if ((c > 191) && (c < 224)) {
				c2 = utftext.charCodeAt(i + 1);
				string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
				i += 2;
			} else {
				c2 = utftext.charCodeAt(i + 1);
				c3 = utftext.charCodeAt(i + 2);
				string += String.fromCharCode(((c & 15) << 12)
						| ((c2 & 63) << 6) | (c3 & 63));
				i += 3;
			}
		}
		return string;
	};
}
// 供应商主数据公用调用框调用地址
var supUrl = {
	all : "com.jmc.scm.system.view.common.SupBaseInfo.d?mode=single",// （单选全部）
	Z007 : "com.jmc.scm.system.view.common.SupBaseInfo.d?mode=single&supGrp=Z007",//
	// 成品供应商组（单选）
	Z006 : "com.jmc.scm.system.view.common.SupBaseInfo.d?mode=single&supGrp=Z006",//
	// 裸石供应商组（单选）

	allMulti : "com.jmc.scm.system.view.common.SupBaseInfo.d",// （多选全部）
};
// // 客户主数据公用调用框调用地址
// var cltUrl = {
// all : "com.allove.scm.system.view.common.ClientBaseInfo.d?mode=single",//
// （单选）
// allMulti : "com.allove.scm.system.view.common.ClientBaseInfo.d",// （多选）
// };
// // 商品主数据公用调用框调用地址
// var prodUrl = {
// all : "com.allove.scm.system.view.common.ProdBaseInfo.d?mode=single",// （单选）
// allMulti : "com.allove.scm.system.view.common.ProdBaseInfo.d",// （多选）
// };
// 音效公用地址
var audioUrl = {
	alarm : "audio/alarm.wav",
};
/**
 * 播放音效
 */
function playSound(url) {
	var borswer = window.navigator.userAgent.toLowerCase();
	if (borswer.indexOf("ie") >= 0) {
		// IE内核浏览器
		var strEmbed = "<embed name='embedPlay' src= '" + url
				+ "'  autostart='true' hidden='true' loop='false'></embed>";
		if ($("body").find("embed").length <= 0)
			$("body").append(strEmbed);
		var embed = document.embedPlay;

		// 浏览器不支持 audion，则使用 embed 播放
		embed.volume = 100;
		// embed.play();这个不需要
	} else {
		// 非IE内核浏览器
		var strAudio = "<audio id='audioPlay' src= '" + url
				+ "'  hidden='true'>";
		if ($("body").find("audio").length <= 0)
			$("body").append(strAudio);
		var audio = document.getElementById("audioPlay");
		// 浏览器支持 audion
		audio.play();
	}
};
/**
 * 获取指定日期，指定的前/后一个月的日期
 * 
 * @param date
 *            当前的日期
 * @param flg
 *            "+"/"-"
 * @returns {String}
 */
function getLastMonth(date, flg) {
	var Nowdate = date;
	var vYear = Nowdate.getFullYear();
	var vMon = Nowdate.getMonth() + 1;
	var vDay = Nowdate.getDate();
	// 每个月的最后一天日期（为了使用月份便于查找，数组第一位设为0）
	var daysInMonth = new Array(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
			31);
	if (flg == '-') {
		if (vMon == 1) {
			vYear = vYear - 1;
			vMon = 12;
		} else {
			vMon = vMon - 1;
		}
	} else if (flg == '+') {
		if (vMon == 12) {
			vYear = vYear + 1;
			vMon = 1;
		} else {
			vMon = vMon + 1;
		}
	}

	// 若是闰年，二月最后一天是29号
	if (vYear % 4 == 0 && vYear % 100 != 0 || vYear % 400 == 0) {
		daysInMonth[2] = 29;
	}
	if (daysInMonth[vMon] < vDay) {
		vDay = daysInMonth[vMon];
	}
	if (vDay < 10) {
		vDay = "0" + vDay;
	}
	if (vMon < 10) {
		vMon = "0" + vMon;
	}
	var date = vYear + "-" + vMon + "-" + vDay;
	return date;
}

/**
 * 校验实体，并校验子实体
 * 
 * @param entity
 *            待校验实体
 * @param alertMsg
 *            是否弹出校验错误信息
 * @returns string 校验结果
 */
function validateWithReference(entity, alertMsg) {
	var validateContext = {};

	entity.validate({
		validateSimplePropertyOnly : false,
		context : validateContext
	});

	if (alertMsg) {
		alertValidateMessage(validateContext);
	}

	return validateContext.result;
}

/**
 * 数据校验后，弹出校验后的错误提示
 * 
 * @param validateContext
 *            检验上下文
 */
function alertValidateMessage(validateContext) {
	var context = validateContext;
	var dataStr = "dorado.baseWidget.SubmitInvalidData";
	var summaryStr = "dorado.baseWidget.SubmitValidationSummary";
	try {
		var errorLength = context.error.length + context.warn.length;
		if (context.result == "invalid" && errorLength > 0) {
			var errorMessage = $resource(dataStr) + "\n";
			if (errorLength == 1) {
				if (context.error.length) {
					errorMessage += context.error[0].text;
				} else {
					errorMessage += context.warn[0].text;
				}
			} else {
				errorMessage += $resource(summaryStr, context.error.length,
						context.warn.length);
			}
			throw new dorado.widget.UpdateAction.ValidateException(
					errorMessage, context);
		}
	} catch (e) {
		if (e instanceof dorado.widget.UpdateAction.ValidateException) {
			dorado.Exception.removeException(e);
			var eventArg = {
				success : false,
				error : e,
				processDefault : true
			};
			if (eventArg.processDefault) {
				if (dorado.widget.UpdateAction.alertException) {
					dorado.widget.UpdateAction.alertException(e);
				} else {
					throw e;
				}
			}
			throw new dorado.AbortException();
		} else {
			throw e;
		}
	}
}

function isEmpty(data) {
	if (data == null || data == undefined) {
		return true;
	} else {
		return false;
	}
}

/**
 * 判断数组是否都是相同的元素，如果有不同的元素，返回false
 * 
 * @param array
 * @returns
 */
function isAllEqual(array) {
	if (array.length > 0) {
		return !array.some(function(value, index) {
			return value !== array[0];
		});
	} else {
		return true;
	}
}

function arrRepeat(arr) {
	var arrStr = JSON.stringify(arr);
	for (var i = 0; i < arr.length; i++) {
		if (arrStr.indexOf(arr[i]) != arrStr.lastIndexOf(arr[i])) {
			return true;
		}
	}
	return false;
}

/**
 * 使用循环的方式判断一个元素是否存在于一个数组中
 * 
 * @param {Object}
 *            arr 数组
 * @param {Object}
 *            value 元素值
 */
function isInArray(arr, value) {
	for (var i = 0; i < arr.length; i++) {
		if (value === arr[i]) {
			return true;
		}
	}
	return false;
}

// 验证重复元素，有重复返回true；否则返回false
function isRepeat(arr) {
	var hash = {};
	for ( var i in arr) {
		if (hash[arr[i]]) {
			return true;
		}
		// 不存在该元素，则赋值为true，可以赋任意值，相应的修改if判断条件即可
		hash[arr[i]] = true;
	}
	return false;
}