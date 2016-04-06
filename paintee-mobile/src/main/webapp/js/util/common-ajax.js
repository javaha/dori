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
				// console.log(jqXHR);
				// console.log(jqXHR.responseText);
				// console.log(textStatus);
				// console.log(errorThrown);

				if (jqXHR.responseText != null) {
					try {
						var object = JSON.parse(jqXHR.responseText);
						var contextPath = object.contextPath?object.contextPath:'';

						if (opener == null) {
							if (object.errorNo == 500) {
								var url = contextPath + "/common/error/500.html";

								$(location).attr('href', url);
							} else if (object.errorNo == 9999) {
								clearUserInfoCookie();

								alert(object.errorMsg);

								$(location).attr('href', contextPath + '/');
							} else {
								$(location).attr('href', object.redirectUrl);
							}
						} else {
							if (object.errorNo == 500) {
								var url = object.contextPath + "/common/error/500.html";

								$(opener.location).attr('href', url);
							} else if (object.errorNo == 9999) {
								clearUserInfoCookie();

								alert(object.errorMsg);

								$(location).attr('href', contextPath + '/');
							} else {
								$(opener.location).attr('href', object.redirectUrl);
							}

							self.close();
						}
					} catch (e) {
						alert(e.message);
						alert(jqXHR.responseText);
						alert('response not null == Error1 == \n' +
							'code : ' + jqXHR.status + '\n' +
							'statusText : ' + jqXHR.statusText
						);
					}
				} else {
					alert('=== Error2 === \n' +
						'code : ' + jqXHR.status + '\n' +
						'statusText : ' + jqXHR.statusText
					);
				}

				return;
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
				//alert(jqXHR.responseText);
				if (jqXHR.responseText != null) {
					try {
						var object = JSON.parse(jqXHR.responseText);
						var contextPath = object.contextPath?object.contextPath:'';

						if (opener == null) {
							if (object.errorNo == 500) {
								var url = contextPath + "/common/error/500.html";

								$(location).attr('href', url);
							} else {
								$(location).attr('href', object.redirectUrl);
							}
						} else {
							if (object.errorNo == 500) {
								var url = contextPath + "/common/error/500.html";

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