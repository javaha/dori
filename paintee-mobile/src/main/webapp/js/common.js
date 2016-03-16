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
	console.log(keyCode);
	return ( keyCode >=48 && keyCode <= 57 ) || ( keyCode >=96 && keyCode <= 105 ) || keyCode == 8;
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