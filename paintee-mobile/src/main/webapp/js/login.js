
function LogInController(email, password) {
	this.email = email;
	this.password = password;
	this.accessGubun = 'W';
}
LogInController.prototype = {
	doLogin: function() {
		var param = {};
		param.email = this.email;
		param.password = this.password;
		param.accessGubun = this.accessGubun;

		var controller = this;

		AjaxCall.call(apiUrl+"/login", param, "POST", function(result, status) { controller.doLoginRes(result, status); });
	},
	doLoginRes: function(result, status) {
		var controller = this;

		if(result.errorNo == 0) {
			console.log(result);
			console.log(controller.setUserInfoCookie(result));

		    $(".login_container").hide();

		    userID = result.userId;
		    initFollow(userID);
		    initMy(userID);
		    initMenu(userID);

		    mainSwiper.slideTo(0);
		    mainSwiper.unlockSwipes();
		    mainSwiper.lockSwipeToPrev();
		} else if(result.errorNo == 401 || result.errorNo == 402 || result.errorNo == 404) {
			alert('이메일과 비밀번호를 확인하세요.');
		}
	},
	setUserInfoCookie: function(userInfo) {
		var cDay = 7;
		var cName = "userInfo";
		var cValue = JSON.stringify(userInfo);
		var expire = new Date();
		expire.setDate(expire.getDate() + cDay);
		cookies = 'userInfo=' + escape(cValue) + '; path=/ ';
		console.log(cookies);
		if(typeof cDay != 'undefined') cookies += ';expires=' + expire.toGMTString() + ';';
		document.cookie = cookies;
	},
	getUserInfoCookie: function() {
		var cName = 'userInfo=';
		var cookieData = document.cookie;
		var start = cookieData.indexOf(cName);
		var cValue = '';

		if(start != -1){
			start += cName.length;

			var end = cookieData.indexOf(';', start);

			if(end == -1) {
				end = cookieData.length;
			}

			cValue = cookieData.substring(start, end);
		}

		var userInfo = JSON.parse(unescape(cValue));
		console.log(userInfo);
		return userInfo;
	}
}

// 로그인과 함께 다시 side menu 초기화
function logIn(){
    console.log("log in!!!");

    var userEmail = $('#userEmail').val();
    var userPassword = $('#userPassword').val();

    var logInController = new LogInController(userEmail, userPassword);
    logInController.doLogin();

/*    $(".login_container").hide();
    userID = "asummer";
    initFollow(userID);
    initMy(userID);
    initMenu(userID);

    mainSwiper.slideTo(0);
    mainSwiper.unlockSwipes();
    mainSwiper.lockSwipeToPrev();*/
}

//로그인 화면
function showLogin(){
 $(".login_container").show();
}

$(".login_btn_main").click(function(){
 logIn();
});
$(".login_signup_btn").click(function(){
 $(".signup_container").show();
});
$(".signup_login_btn").click(function(){
 $(".signup_container").hide();
});
$(".login_help").click(function(){
 $(".loginhelp_container").show();
});
$(".help_login_btn").click(function(){
 $(".loginhelp_container").hide();
});