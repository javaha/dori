$(document).ready(function() {
	// 페이지 로딩 시 Popular 스와이프 홈 화면 정보구성
	initMy();
});

//list container 시작        
var mySwiper = new Swiper('.swiper_container_my', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-my',
    scrollbarHide: true
})

mySwiper.on("onSlideChangeStart", function(swiper) {
	if (userID !== "") {
		// 화면에 로딩된 슬라이드 그림 개수
		var slidesCnt = swiper.slides.length - 1;
		// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
		console.log(swiper.slides.length + "-" + swiper.activeIndex);
		if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
			new MyHomeController().getListData(slidesCnt);
		}
	}
});

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
mySwiper.on("onTransitionEnd", function(swiper){listLock(swiper)});

// side menu에 이벤트 설정
$("#menu_my").click(function(){
    selectMenu(3);
});

// 초기 설정들
// 가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
mySwiper.disableMousewheelControl();

mySwiper.on("onSetTranslate", function(swiper, translate) {
	swipeToMenu(swiper, translate)
});

function MyHomeController() {
}

MyHomeController.prototype = {
	getHomeCount : function () {
		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/myhome/count", 
			null,
			"GET", 
			function(result) {
				controller.getHomeCountRes(result);
			}
		);
	}, 	
	getHomeCountRes : function (result) {
		setMyHome(result);
	}, 	
	getListData : function(startRow) {
		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/myhome/list?startRow=" + startRow, null,
			"GET", 
			function(result) {
				controller.getListDataRes(result);
			}
		);
	},
	getListDataRes : function(result) {
		for ( var index in result.list) {
			addPainting(mySwiper, 1, "my", result.list[index]);
			if (mySwiper.slides.length > 100) {
				break;
			}
		}
	}
};

// 각각의 home 화면 설정
function initMy(){
    if (userID == "") {
        var myHome = new Home();
        var logInBtn = $("<div>").addClass("login_btn").html("Log in").click(function(){showLogin()});
        myHome.setTitle("my");
        myHome.setExplain("로그인해서 나와 팔로워의 그림을 확인하세요<br><br><br>");
        myHome.hideNext();
        myHome.setContents(logInBtn);
        mySwiper.appendSlide(myHome.buildStructure());
        delete myHome;
        delete logInBtn;
    }else{
		// 로그인 상태일 경우 홈카운트 가져오기
		new MyHomeController().getHomeCount();
    }
}

function setMyHome(result) {
    mySwiper.removeAllSlides();
    var myHome = new Home();
    myHome.setTitle("my");
    myHome.setExplain("내가 올리거나 포스트한 그림입니다.<br>여기에 자신을 소개할 문구를 넣어주세요. <i class='material-icons' style='font-size:1em'>create</i>");
    var content1 =
        $("<div>").addr("id", "uploadBtn").addClass("home_btn_my").html("uploaded ").append($("<b>").html(" " + result.myhome.uploadCount))
    var content2 =
        $("<div>").addr("id", "postBtn").addClass("home_btn_my").html("posted ").append($("<b>").html(" " + result.myhome.postCount))
    content1.click(function(){
    	
    	btnToggle(this);
    });
    content2.click(function(){
    	
    	btnToggle(this)
    });
    myHome.hideNext();
    myHome.setContents(content1);
    myHome.setContents(content2);
    mySwiper.appendSlide(myHome.buildStructure());
    delete myHome;
    delete content1;
    delete content2;
	
	// 테이블에서 가져올 데이터의 시작 위치를 처음 로딩시 0번째 부터 조회
	new MyHomeController().getListData(0);
}