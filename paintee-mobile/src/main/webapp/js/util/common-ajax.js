//ajax function...............................................
var AjaxCall = {
	call: function (url, data, type, successFunc, showProcess) {

		var option = {
			url: url,
			type: type,
			async: true,
			cache: false,
			success: successFunc,
			data: (data ? JSON.stringify(data) : ""),
			dataType: "json",
			contentType: "application/json",
			beforeSend: function(xhr){
				if(userInfo) {
					xhr.setRequestHeader('X-PAINTEE-HASH', userInfo.hash);
				}
			},
			error: function (jqXHR, textStatus, errorThrown) {
				alert($.i18n.t('alert.common.unkownServerError'));
				location.href = "/";
			}
		};

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
			beforeSend: function(xhr){
				if(userInfo) {
					xhr.setRequestHeader('X-PAINTEE-HASH', userInfo.hash);
				}
			},
			success: function(result, status) {
				successFunc(result, status);
			},
			error: function (jqXHR, textStatus, errorThrown) {
				location.href = "/";
			}
		});
	}
};