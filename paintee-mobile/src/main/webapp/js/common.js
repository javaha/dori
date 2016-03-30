/**
 * 
 * @param obj
 */
function getEnterCount(obj) {
	if (typeof(obj) === "string") {
		obj = $("#" + obj);
	}
	var temp;
	var senVal = obj.val();
	var len = senVal.length;
	var enter = 0;

	// 초기 최대길이를 텍스트 박스에 뿌려준다.
	for (var index = 0; index < len; index++) {
		temp = senVal.charAt(index);
		if (temp == '\n') { // 엔터 키 횟수 증가
			enter++;
		}
	}
	return enter;
}	

function chkEmail(v) {
	var regEmail = /\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/;

	if(!regEmail.test(v)) {
		return false;
	}

	return true;
}

function chkPassword(str){
	var regPassword = /^.*(?=.{8,12})(?=.*[0-9])(?=.*[a-zA-Z]).*$/;

	if(!regPassword.test(str)) {
		return false;
	}

	return true;
}

function chkAlphaNum(str){
	var regAlphaNum = /^[A-Za-z0-9+]{4,12}$/;

	if(!regAlphaNum.test(str)) {
		return false;
	}

	return true;
}

/**
 * 숫자 인지 체크
 * @param str
 * @returns
 */
function chkNum(str){
	return /^[0-9]+$/.test(str);
}

/**
 * 숫자만 입력받기
 * @param event
 * @returns {Boolean}
 */
function limitNumber(event) {
	event = event || window.event;
	var keyCode = (event.which) ? event.which : event.keyCode;
	event.target.value = event.target.value.replace(/[^0-9]/g, "");
}

function getCharCount(value) {
	var totalByte = 0;
	for (var i = 0; i < value.length; i++) {
	    oneChar = value.charAt(i);
	    if (escape(oneChar).length > 4) {
	        totalByte += 2;
	    } else {
	        totalByte++;
	    }
	}
	return totalByte;
}

function shareSocial(data) {
	var url = "";
//	var hostAndFileName = "www.paintee.com:8080/index.html?";
	var hostAndFileName = "www.paintee.com:9080/index.html?";
	hostAndFileName = encodeURIComponent(hostAndFileName);
	var param = "user=" + data.name + "&page=" + data.page;
		param = encodeURIComponent(param);
	switch (data.type) {
	case "facebook":
		url = "https://www.facebook.com/sharer/sharer.php?u=http://" + hostAndFileName + param;
		break;
	case "twitter":
		url = "http://twitter.com/home?status=paintee http://" + hostAndFileName + param;
		break;
	case "pinterest":
		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http%3A//" + hostAndFileName + param;
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http://me2.do/xndJIG4P";
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http://www.naver.com";
//		url = "https://pinterest.com/pin/create/button?url=http://www.naver.com";
//		url = "https://pinterest.com/pin/create/button?url=" + encodeURIComponent("http://www.naver.com");
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=" + encodeURIComponent("http://www.naver.com");
		break;
	}
	
	var pop =  window.open(url, "social", "width=630,height=250,scrollbars=yes,resizable=yes,toolbar=no");
	if (pop) pop.focus();  

}
