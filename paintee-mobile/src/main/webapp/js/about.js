$(".about_wrapper").width(mainWidth*5);
$(".about_card").width(mainWidth);

var aboutIndex = 0;

slideAboutNavi(aboutIndex);

var overviewSwiper = new Swiper('.swiper_container_overview', {
    slidesPerView: 'auto',
    pagination: '.swiper-pagination-overview',
    lazyLoading: true,
    lazyLoadingInPrevNext: true
});
var postSwiper = new Swiper('.swiper_container_post', {
    slidesPerView: 'auto',
    pagination: '.swiper-pagination-post',
    lazyLoading: true,
    lazyLoadingInPrevNext: true
});
var uploadSwiper = new Swiper('.swiper_container_upload', {
    slidesPerView: 'auto',
    pagination: '.swiper-pagination-upload',
    lazyLoading: true,
    lazyLoadingInPrevNext: true
});

$(".tab_overview").click(function(){slideAboutNavi(0)});
$(".tab_purchase").click(function(){slideAboutNavi(1)});
$(".tab_upload").click(function(){slideAboutNavi(2)});
$(".tab_faq").click(function(){slideAboutNavi(3)});
$(".tab_contact").click(function(){slideAboutNavi(4)});

function slideAboutNavi(index){  
    aboutIndex = index;
    $(".about_navi_tab").removeClass("about_navi_selected");
    $(".about_navi_tab").eq(index).addClass("about_navi_selected");
    $(".about_navi").css("left", (mainWidth/2)-(aboutIndex*140)-70);
    $(".about_wrapper").css("left", -(aboutIndex*mainWidth));
}

overviewSwiper.on("onSetTranslate", function(swiper, translate){
    if(translate < -(mainWidth*2.25)){
         slideAboutNavi(1)
         swiper.slideTo(0)
     }
});
postSwiper.on("onSetTranslate", function(swiper, translate){
    if(translate>(mainWidth/4)){
         slideAboutNavi(0)
     }else if(translate < -(mainWidth*5.25)){
         slideAboutNavi(2)
         swiper.slideTo(0)
     }
});
uploadSwiper.on("onSetTranslate", function(swiper, translate){
    if(translate>(mainWidth/4)){
         slideAboutNavi(1)
     }else if(translate < -(mainWidth*3.25)){
         slideAboutNavi(3)
         swiper.slideTo(0)
     }
});

$(".return_btn").click(function(){
    $(".about_container").hide();
})