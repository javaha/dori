function ProfileController() {
}
ProfileController.prototype = {
	getUserInfo: function() {
		var controller = this;

		AjaxCall.call(apiUrl+"/user/me", null, "GET", function(result, status) { controller.getUserInfoRes(result, status); });
	},
	getUserInfoRes: function(result, status) {
		if(result.errorNo == 0) {
			console.log(result);

			var userInfo = result.userInfo;

			$('#profileUserName').val(userInfo.name);
			$('#profileIntroduction').val(userInfo.introduce);
			$('#profileBasicAddr').val(userInfo.basicAddr);
			$('#profileDetailAddr').val(userInfo.detailAddr);
			$('#profileCity').val(userInfo.city);
			$('#profileZipcode').val(userInfo.zipcode);

			if(userInfo.location) {
				$('#profileLocation').val(userInfo.location);
			}

			//사용자 정보 set
		    boxStatus = "profile";
		    setBox();
		    sideOff();

		    $(".profile_container").show();
		}
	},
	updateProfile: function() {
		var controller = this;

		if(controller.validUserInfo()) {
			//email, user name 중복 체크
			controller.checkDuplication();
		}
	},
	updateProfileRes: function(result, status) {
		if(result.errorNo == 0) {
			alert('수정 되었습니다.');
			$(".profile_container").hide();
		}
	},
	validUserInfo: function() {
		var result = true;

		result = chkAlphaNum($('#profileUserName').val());

		if(!result) {
			alert("이름은 최소 4자 최대 10자 의 영문, 숫자 조합으로 입력하셔야 합니다.");
		} else {
			if($('#profileUserPassword').val() != '' || $('#profileConfirmPassord').val() != '') {
				if($('#profileUserPassword').val() != $('#profileConfirmPassord').val()) {
					alert("비밀번호와 비밀번화 확인 값이 일치하지 않습니다.");
				} else {
					result = chkPassword($('#profileUserPassword').val());

					if(!result) {
						alert("비밀번호는 최소 8자 최대 12자 의 문자와 숫자 조합으로 입력하셔야 합니다.");
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
		param.name=$('#profileUserName').val();

		AjaxCall.call(apiUrl+"/user/chkduplicate", param, "POST", function (result, status) { controller.checkDuplicationRes(result, status); });
	},
	checkDuplicationRes: function(result, status) {
		var controller = this;

		if(result.errorNo == 0) {
			console.log('update user');
			var param = {};

			if($('#profileUserPassword').val() != '') {
				param.password=$('#profileUserPassword').val();
			}

			param.name=$('#profileUserName').val();
			param.introduce=$('#profileIntroduction').val();
			param.basicAddr=$('#profileBasicAddr').val();
			param.detailAddr=$('#profileDetailAddr').val();
			param.city=$('#profileCity').val();
			param.zipcode=$('#profileZipcode').val();
			param.location=$('#profileLocation option:selected').val();

			AjaxCall.call(apiUrl+"/user/me", param, "POST", function (result, status) { controller.updateProfileRes(result, status); });
		} else if(result.errorNo == 1) {
			alert('이미 사용중인 이름 입니다.');
		}
	}
}

// 프로필 수정화면
function showProfile() {
	new ProfileController().getUserInfo();
}

$("[name=profileLocation]").change(function(e){
	// console.log("change");
	switch ($("[name=profileLocation]").val()) {
	case "1":
		setProfilePostUI("KOREA");
		break;
	default:
		setProfilePostUI("NOKOREA");
		break;
	}
	e.stopPropagation();
});

function setProfilePostUI(type) {
	var className = "account_form";
	var styleName = "none";
	if (type == 'KOREA') {
		className = "account_form2";
		styleName = "inline";
//		$("[name=receiverBasicAddr]").attr("readOnly", "readOnly");
	}
	$("[name=receiverBasicAddr]").attr("class", className);
	$("#profilePostSearch").css("display", styleName);
}

$('#profileUpdateBtn').on('click', function() {
	new ProfileController().updateProfile();
});