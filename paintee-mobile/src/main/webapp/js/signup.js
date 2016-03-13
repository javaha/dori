function SignupController() {
}

SignupController.prototype = {
	doSignup: function() {
		var controller = this;
		var param = {};
		param.email=$('#signupUserId').val();
		param.password=$('#signupUserPassword').val();
		param.name=$('#signupUserName').val();

		AjaxCall.call(apiUrl+"/signup", param, "POST", function (result, status) { controller.doSignupRes(result, status); });
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
		var result = false;

		validEmail();
	}
}

$('#signup_btn').on("click", function() { new SignupController().doSignup(); });