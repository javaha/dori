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
	var hostAndFileName = "www.paintee.com:8080/index.html?";
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
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http%3A//" + hostAndFileName + param;
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=http://me2.do/xndJIG4P";
		url = "https://kr.pinterest.com/pin/create/button?media=http://www.mlec.co.kr/upload/memberFile/mlec-0975e88b-7600-4ebd-93f3-376ccae91ab9.jpg&url=http://www.naver.com";
//		url = "https://pinterest.com/pin/create/button?url=http://www.naver.com";
//		url = "https://pinterest.com/pin/create/button?url=" + encodeURIComponent("http://www.naver.com");
//		url = "https://pinterest.com/pin/create/button?media=" + imageUrl + "/cmm/file/view/" + data.fileId + "&url=" + encodeURIComponent("http://www.naver.com");
		
		https://kr.pinterest.com/pin/create/button/?url=https%3A%2F%2Fwww.youtube.com%2Fattribution_link%3Fa%3D3qHmOcyQVYY%26u%3D%252Fwatch%253Fv%253DOg_kaPMZxN0%2526feature%253Dshare&description=%EC%8A%B9%EA%B1%B4%EC%9D%B4%EC%9D%98%20%ED%86%A0%EB%AF%B8%EC%B9%B4%EC%B9%B4%EA%B3%A0%20%EC%A0%90%EB%B3%B4%EC%A0%9C%ED%8A%B8%EA%B8%B0%20%EC%86%8C%EA%B0%9C&is_video=true&media=https%3A%2F%2Fi.ytimg.com%2Fvi%2FOg_kaPMZxN0%2Fmaxresdefault.jpg
		
		break;
	}
	
	var pop =  window.open(url, "social", "width=630,height=250,scrollbars=yes,resizable=yes,toolbar=no");
	if (pop) pop.focus();  

}
function urlCopy(data) {
	var url = "http%3A//";
	var hostAndFileName = "www.paintee.com:8080/index.html?";
	hostAndFileName = encodeURIComponent(hostAndFileName);
	var param = "user=" + data.name + "&page=" + data.page;
		param = encodeURIComponent(param);

	url += hostAndFileName + param;
	
}

function toDate (timestamp, dateFormat) {
    var date = new Date(timestamp);

    var retVal = $.datepicker.formatDate(dateFormat, date);
    return retVal;
};

/**
 * 미디어에 따른 이미지 경로를 처리하기 위한 함수
 * 고정된 값을 설정하므로 공통적인 부분을 반환함
 * @param fileId
 * @returns {Array}
 */
function getImageUrls(fileId) {
	return [imageUrl + "/cmm/file/view/1/" + fileId, imageUrl + "/cmm/file/view/2/" + fileId, imageUrl + "/cmm/file/view/3/" + fileId]
	
}
