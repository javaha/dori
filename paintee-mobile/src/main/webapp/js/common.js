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