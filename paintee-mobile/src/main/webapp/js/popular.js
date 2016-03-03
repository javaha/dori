$(document).ready(function () {
	AjaxCall.callJson(
		"http://localhost:8090/api/popularIndex", 
		"", 
		"GET", 
		function (result) {
			console.log(result);
		}
	);
});
 
// list container 시작  
var popularSwiper = new Swiper('.swiper_container_popular', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-popular',
    scrollbarHide: true
});

//전체그림/전체좋아요 숫자설정
function totalPainting(){
	console.log("totalPainting");

    var contents = $("<div>");
    contents.html("1776명이 paintee에서 좋아하는 그림을 찾았습니다.")
    return contents;
}

//각각의 home 화면 설정
function initPopular(){
	console.log("init popular");

    var popularHome = new Home();

    popularHome.setTitle("Popular");
    popularHome.setExplain("가장 인기있는 그림입니다.");
    popularHome.setContents(totalPainting());
    popularSwiper.appendSlide(popularHome.buildStructure());

    delete popularHome;

    addPainting(popularSwiper, 0, "popular");
}

//전체그림/전체좋아요 숫자설정
function totalPainting(){
    return $("<div>").html("<span id='popularCount'>1776</span>명이 paintee에서 좋아하는 그림을 찾았습니다.");
}

popularSwiper.on("onSlideChangeStart", function(swiper){
	console.log("popularSwiper onSlideChangeStart");
	addPainting(swiper, swiper.activeIndex, "popular");
});

//최초 5개 미리 생성
initPopular();

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
popularSwiper.on("onTransitionEnd", function(swiper){
	console.log("popularSwiper onTransitionEnd");
	listLock(swiper);
});

//side menu에 이벤트 설정
$("#menu_popular").on('click', function(){
	console.log("popularSwiper menu_popular");
    selectMenu(1);
});

//초기 설정들
//가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
popularSwiper.disableMousewheelControl();

popularSwiper.on("onSetTranslate", function(swiper, translate){
	console.log("popularSwiper onSetTranslate");
	swipeToMenu(swiper, translate);
});
