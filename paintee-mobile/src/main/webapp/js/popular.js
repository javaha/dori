/**
 * 
 */
var popularSwiper = new Swiper('.swiper_container_popular', {
    slidesPerView: 'auto',
    centeredSlides: true,
    spaceBetween: mainWidth*0.05,
    mousewheelControl : true,
    scrollbar: '.swiper-scrollbar-popular',
    scrollbarHide: true
});

//각각의 home 화면 설정
function initPopular(){
    var popularHome = new Home();
    popularHome.setTitle("Popular");
    popularHome.setExplain("가장 인기있는 그림입니다.");
    popularHome.setContents(totalPainting());
    popularSwiper.appendSlide(popularHome.buildStructure());
    delete popularHome;

    addPainting(popularSwiper, 0, "popular");
}

popularSwiper.on("onSlideChangeStart", function(swiper){addPainting(swiper, swiper.activeIndex, "popular")});

//최초 5개 미리 생성
initPopular();

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
popularSwiper.on("onTransitionEnd", function(swiper){listLock(swiper)});

//side menu에 이벤트 설정
$("#menu_popular").click(function(){
    selectMenu(1);
});

//초기 설정들
//가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
popularSwiper.disableMousewheelControl();

popularSwiper.on("onSetTranslate", function(swiper, translate){swipeToMenu(swiper, translate)});