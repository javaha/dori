//ajax function...............................................
var AjaxCall = {
	call: function (url, data, type, successFunc) {

		var option = {
			url: url,
			type: type,
			async: true,
			cache: false,
			success: successFunc,
			data: (data ? JSON.stringify(data) : ""),
			dataType: "json",
			contentType: "application/json",
			error: function (jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(jqXHR.responseText);
				console.log(textStatus);
				console.log(errorThrown);

				if (jqXHR.responseText != null) {
					try {
						var object = JSON.parse(jqXHR.responseText);

						if (object == null) {
							if (object.errorNo == 500) {
								var url = object.contextPath + "/common/error/500.html";

								$(location).attr('href', url);
							} else if (object.errorNo == 9999) {
								alert(object.errorMsg);

								$(location).attr('href', '/');
							} else {
								$(location).attr('href', object.redirectUrl);
							}
						} else {
							if (object.errorNo == 500) {
								var url = object.contextPath + "/common/error/500.html";

								$(opener.location).attr('href', url);
							} else if (object.errorNo == 9999) {
								alert(object.errorMsg);

								$(location).attr('href', '/');
							} else {
								$(opener.location).attr('href', object.redirectUrl);
							}

							self.close();
						}
					} catch (e) {
						alert(e.message);
						alert(jqXHR.responseText);
						alert('response not null == Error == \n' +
							'code : ' + jqXHR.status + '\n' +
							'statusText : ' + jqXHR.statusText
						);
					}
				} else {
					alert('=== Error === \n' +
						'code : ' + jqXHR.status + '\n' +
						'statusText : ' + jqXHR.statusText
					);
				}

				return;
			}
		};

		if(type == "DELETE" || type == 'PUT') {
			option.contentType = "application/json";
			option.data = JSON.stringify(data);
		}

		$.ajax(option);
		return;
	},
	callMultipart: function(url, data, successFunc) {
		$.ajax({
			type: 'POST',
			url: url,
			dataType: "json",
			processData: false,
			contentType: false,
			data: data,
			async: true,
			cache: false,
			"data-ajax": "false",
			success: function(result, status) {
				successFunc(result, status);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				//alert(jqXHR.responseText);
				if (jqXHR.responseText != null) {
					try {
						var object = JSON.parse(jqXHR.responseText);

						if (opener == null) {
							if (object.errorNo == 500) {
								var url = object.contextPath + "/common/error/500.html";

								$(location).attr('href', url);
							} else {
								$(location).attr('href', object.redirectUrl);
							}
						} else {
							if (object.errorNo == 500) {
								var url = object.contextPath + "/common/error/500.html";

								$(opener.location).attr('href', url);
							} else {
								$(opener.location).attr('href', object.redirectUrl);
							}

							self.close();
						}
					} catch (e) {
						alert('== Error == \n' +
							'code : ' + jqXHR.status + '\n' +
							'statusText : ' + jqXHR.statusText
						);
					}
				} else {
					alert('=== Error === \n' +
						'code : ' + jqXHR.status + '\n' +
						'statusText : ' + jqXHR.statusText
					);
				}

				return;
			}
		});
	}
};

var convertEngMonth = function (mon){
	if     (mon == '1')    	return 'Jan';
	else if(mon == '2')		return 'Feb';
	else if(mon == '3')		return 'Mar';
	else if(mon == '4')		return 'Apr';
	else if(mon == '5')		return 'May';
	else if(mon == '6')		return 'Jun';
	else if(mon == '7')		return 'Jul';
	else if(mon == '8')		return 'Aug';
	else if(mon == '9')		return 'Sep';
	else if(mon == '10')	return 'Oct';
	else if(mon == '11')	return 'Nov';
	else if(mon == '12')	return 'Dec';
}

var toDate = function (timestamp) {
	var date = new Date(timestamp);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();

	var retVal = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	// " " + (hour < 10 ? "0" + hour : hour) + ":"
	// + (min < 10 ? "0" + min : min) + ":"
	// + (sec < 10 ? "0" + sec : sec);
	return retVal;
};

var toEngDateStr = function (timestamp) {
	var date = new Date(timestamp);
	return date.getDate() + ". " + convertEngMonth(date.getMonth() + 1);
};

var toDateTime = function (timestamp) {
	var date = new Date(timestamp);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();

	var retVal = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day)
		+ " " + (hour < 10 ? "0" + hour : hour) + ":"
		+ (min < 10 ? "0" + min : min)
		+ ":" + (sec < 10 ? "0" + sec : sec);
	return retVal;
};

var setUserInfoCookie = function(userInfo) {
	var cDay = 7;
	var cName = "userInfo";
	var cValue = JSON.stringify(userInfo);
	var expire = new Date();
	expire.setDate(expire.getDate() + cDay);
	cookies = 'userInfo=' + escape(cValue) + '; path=/ ';
	console.log(cookies);
	if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
	document.cookie = cookies;
};
var clearUserInfoCookie = function() {
	var cDay = -7;
	var cName = "userInfo";
	var cValue = '';
	var expire = new Date();
	expire.setDate(expire.getDate() + cDay);
	cookies = 'userInfo=' + escape(cValue) + '; path=/ ';
	console.log(cookies);
	if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
	document.cookie = cookies;
};
var getUserInfoCookie = function() {
	var cName = 'userInfo=';
	var cookieData = document.cookie;
	var start = cookieData.indexOf(cName);
	var cValue = '';

	if(start != -1) {
		start += cName.length;

		var end = cookieData.indexOf(';', start);

		if(end == -1) {
			end = cookieData.length;
		}

		cValue = cookieData.substring(start, end);
	}

	var userInfo = null;

	if(cValue != null && cValue != '') {
		userInfo = JSON.parse(unescape(cValue));
	}
	console.log(userInfo);
	return userInfo;
};

Date.prototype.timestampToDate = function () {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var day = this.getDate();
	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
};

Date.prototype.timestampToFullDate = function () {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	var day = this.getDate();
	var hour = this.getHours();
	var min = this.getMinutes();
	var sec = this.getSeconds();
	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day) + " " + (hour < 10 ? "0" + hour : hour) + ":" + (min < 10 ? "0" + min : min) + ":" + (sec < 10 ? "0" + sec : sec);
};

Number.prototype.toDateFormat = function () {
	var date = new Date(this);
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();

	year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
	return year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
};

(function ($) {
	// check box multiple select/deselect
	$.checkboxGroup = function (group_name) {
		var checkAll = $('.' + group_name + '.checkbox-head');
		var checkboxes = $('.' + group_name + '.checkbox-item');

		$('.' + group_name).iCheck({
			checkboxClass: 'icheckbox_square-green',
			radioClass: 'iradio_square-green',
		});

		checkAll.on('ifChecked ifUnchecked', function (event) {
			if (event.type == 'ifChecked') {
				checkboxes.iCheck('check');
			} else {
				checkboxes.iCheck('uncheck');
			}
		});

		checkboxes.on('ifChanged', function (event) {
			checkAll.prop('checked', checkboxes.filter(':checked').length == checkboxes.length);
			checkAll.iCheck('update');
		});
	};

})(jQuery);

// jquery 확장
jQuery.fn.serializeObject = function() {
	var obj = null;
	try {
		// this[0].tagName이 form tag일 경우
		if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray();
			if(arr){
				obj = {};
				jQuery.each(arr, function() {
					// obj의 key값은 arr의 name, obj의 value는 value값
					obj[this.name] = this.value;
				});
			}
		}
	} catch(e) {
		alert(e.message);
	} finally  {}

	return obj;
};
