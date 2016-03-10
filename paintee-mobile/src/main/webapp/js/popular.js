// list container 시작  
var popularSwiper = new Swiper('.swiper_container_popular', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-popular',
    scrollbarHide: true
});

popularSwiper.on("onSlideChangeStart", function(swiper){
	// console.log("popularSwiper onSlideChangeStart");
	// 화면에 로딩된 슬라이드 그림 개수
	var slidesCnt = swiper.slides.length - 1;
	// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
	// console.log(swiper.slides.length + "-" + swiper.activeIndex);
	if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
		var controller = new PopularController();
		controller.getListData(slidesCnt);
	}
});

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
popularSwiper.on("onTransitionEnd", function(swiper){
	// console.log("popularSwiper onTransitionEnd");
	listLock(swiper);
});

//side menu에 이벤트 설정
$("#menu_popular").on('click', function(){
	// console.log("popularSwiper menu_popular");
    selectMenu(1);
});

//초기 설정들
//가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
popularSwiper.disableMousewheelControl();

popularSwiper.on("onSetTranslate", function(swiper, translate){
	// console.log("popularSwiper onSetTranslate");
	swipeToMenu(swiper, translate);
});


$(document).ready(function () {
	// 페이지 로딩 시 Popular 스와이프 홈 화면 정보구성
	initPopular();
	// 테이블에서 가져올 데이터의 시작 위치를 처음 로딩시 0번째 부터 조회
	new PopularController().getListData(0);
});

function PopularController() {
	this.startRow = 0;
}

PopularController.prototype = {
	getListData: function (startRow) {
		this.startRow = startRow;
		var controller = this;
		AjaxCall.call(apiUrl + "/popularIndex?startRow=" + startRow,
			null, 
			"GET", 
			function (result) {
				controller.getListDataRes(result);			
			}
		);
	},
	getListDataRes: function (result) {
		// console.log(result + "this.startRow : " + this.startRow);
		if (!this.startRow) {
			$("#popular_count").text(result.count);
		}
		for (var index in result.list) {
			addPainting(popularSwiper, 1, "popular", result.list[index]);
			if (popularSwiper.slides.length > 100) {
				break;
			}
		} 
	}
};

//각각의 home 화면 설정
function initPopular(){
	// console.log("init popular");

  var popularHome = new Home();

  popularHome.setTitle("Popular");
  popularHome.setExplain("가장 인기있는 그림입니다.");
  popularHome.setContents(totalPainting());
  popularSwiper.appendSlide(popularHome.buildStructure());

  delete popularHome;
}

//전체그림/전체좋아요 숫자설정
function totalPainting(){
	// console.log("totalPainting");

    var contents = $("<div>");
    contents.html("<span id='popular_count'>0</span>명이 paintee에서 좋아하는 그림을 찾았습니다.")
    return contents;
}