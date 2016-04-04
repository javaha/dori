$(".about_wrapper").width(mainWidth*5);
$(".about_card").width(mainWidth);

var overviewSwiper="";      // 4.3 수정
var postSwiper="";          // 4.3 수정
var uploadSwiper="";        // 4.3 수정

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
    $(".about_navi").hide();        // 4.3 수정
    $(".about_card").hide();        // 4.3 수정
    $(".about_guide").hide();       // 4.3 수정    
});

function showAbout(){               // 4.3 수정
    $(".about_container").show();
    $(".about_navi").show();
    showAboutOverview();
    showAboutPost();
    showAboutUpload();
    $(".about_card").show();
    $(".about_guide").hide();
}

function showAboutOverview(){      // 4.3 수정
    $(".about_container").show();
    $(".swiper_container_overview").show();
    $(".about_guide").show();
    if(overviewSwiper==""){
        console.log("1");
        overviewSwiper = new Swiper('.swiper_container_overview', {
            slidesPerView: 'auto',
            pagination: '.swiper-pagination-overview',
            lazyLoading: true,
            lazyLoadingInPrevNext: true
        });
        overviewSwiper.on("onSetTranslate", function(swiper, translate){
            if($(".about_navi").css("display")=="block"){
                if(translate < -(mainWidth*2.25)){
                     slideAboutNavi(1)
                     swiper.slideTo(0)
                 }
            }
        });
    }
}

function showAboutPost(){       // 4.3 수정
    $(".about_container").show();
    $(".swiper_container_post").show();
    $(".about_guide").show();
    if(postSwiper==""){
        console.log("1");
        postSwiper = new Swiper('.swiper_container_post', {
            slidesPerView: 'auto',
            pagination: '.swiper-pagination-post',
            lazyLoading: true,
            lazyLoadingInPrevNext: true
        });
        postSwiper.on("onSetTranslate", function(swiper, translate){
            if($(".about_navi").css("display")=="block"){
                if(translate>(mainWidth/4)){
                     slideAboutNavi(0)
                 }else if(translate < -(mainWidth*5.25)){
                     slideAboutNavi(2)
                     swiper.slideTo(0)
                 }    
            }
        });                
    }
}

function showAboutUpload(){     // 4.3 수정
    $(".about_container").show();
    $(".swiper_container_upload").show();
    $(".about_guide").show();
    if(uploadSwiper==""){
        console.log("1");
        uploadSwiper = new Swiper('.swiper_container_upload', {
            slidesPerView: 'auto',
            pagination: '.swiper-pagination-upload',
            lazyLoading: true,
            lazyLoadingInPrevNext: true
        });
        uploadSwiper.on("onSetTranslate", function(swiper, translate){
            if($(".about_navi").css("display")=="block"){
                if(translate>(mainWidth/4)){
                     slideAboutNavi(1)
                 }else if(translate < -(mainWidth*5.25)){
                     slideAboutNavi(3)
                     swiper.slideTo(0)
                 }
            }
        });                
    }
}

$(".about_card").swipe({    // 4.3 수정
    swipeUp:function(){
        $(".about_container").hide();
        $(".about_navi").hide();
        $(".about_card").hide();
        slideAboutNavi(0);
    },
    threshold:10
});

$(".about_card_faq").swipe({    // 4.3 수정
    swipeLeft:function(){
        slideAboutNavi(4);
    },
    swipeRight:function(){
        slideAboutNavi(2);
        uploadSwiper.slideTo(0);
    },
    threshold:10
});

$(".about_card_contact").swipe({    // 4.3 수정
    swipeRight:function(){
        slideAboutNavi(3);
    },
    threshold:10
});