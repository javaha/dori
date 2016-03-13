function SignupController() {
}

SignupController.prototype = {
	doSignup: function() {
		var controller = this;

		if(controller.validSinupInfo()) {
			//email, user name 중복 체크
			controller.checkDuplication();
		}
	},
	doSignupRes: function(result, status) {
		if(result.errorNo == 0) {
			alert('회원가입이 정상적으로 처리되었습니다.\n이메일을 확인하세요.');
			location.href = "/";
		} else if(result.errorNo != 0) {
			alert('이메일과 비밀번호를 확인하세요.');
		}
	},
	validSinupInfo: function() {
		var result = true;

		result = chkEmail($('#signupUserId').val());

		if(!result) {
			alert("바른 이메일 주소를 입력하셔야 합니다.");
		} else {
			if($('#signupUserPassword').val() != $('#signupConfirmPassord').val()) {
				alert("비밀번호와 비밀번화 확인 값이 일치하지 않습니다.");
			} else {
				result = chkPassword($('#signupUserPassword').val());

				if(!result) {
					alert("비밀번호는 최소 8자 최대 12자 의 문자와 숫자 조합으로 입력하셔야 합니다.");
				} else {
					result = chkAlphaNum($('#signupUserName').val());

					if(!result) {
						alert("이름은 최소 4자 최대 10자 의 영문, 숫자 조합으로 입력하셔야 합니다.");
					} else {
						result = true;
					}
				}
			}
		}

		return result;
	},
	checkDuplication: function() {
		var controller = this;

		var param = {};
		param.email=$('#signupUserId').val();
		param.name=$('#signupUserName').val();

		AjaxCall.call(apiUrl+"/signup/chkduplicate", param, "POST", function (result, status) { controller.checkDuplicationRes(result, status); });
	},
	checkDuplicationRes: function(result, status) {
		var controller = this;

		if(result.errorNo == 0) {
			console.log('regist user');
			var param = {};
			param.email=$('#signupUserId').val();
			param.password=$('#signupUserPassword').val();
			param.name=$('#signupUserName').val();

			AjaxCall.call(apiUrl+"/signup", param, "POST", function (result, status) { controller.doSignupRes(result, status); });
		} else if(result.errorNo == 1) {
			alert('이미 사용중인 email 입니다.');
		} else if(result.errorNo == 2) {
			alert('이미 사용중인 이름 입니다.');
		}
	}
}

$('#signup_btn').on("click", function() { new SignupController().doSignup(); });