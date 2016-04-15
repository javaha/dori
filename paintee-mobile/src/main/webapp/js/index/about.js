var aboutIndex=0;

$(".about_wrapper").width(mainWidth*5);
$(".about_card").width(mainWidth);


var overviewSwiper="";	  // 4.3 수정
var postSwiper="";		  // 4.3 수정
var uploadSwiper="";		// 4.3 수정

slideAboutNavi(aboutIndex);

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

$(".return_btn").click(function(){
	$(".about_container").hide();
	$(".about_navi").hide();		// 4.3 수정
	$(".about_card").hide();		// 4.3 수정
	$(".about_guide").hide();	   // 4.3 수정
	slideAboutNavi(0);
})

function showAbout(){			   // 4.3 수정
	$(".about_container").show();
	$(".about_navi").show();
	showAboutOverview();
	showAboutPost();
	showAboutUpload();
	$(".about_card").show();
	$(".about_guide").hide();
}

function showAboutOverview(){	  // 4.3 수정
	boxStatus="about";
	$(".about_container").show();
	$(".swiper_container_overview").show();
	$(".about_guide").show();
	if(overviewSwiper==""){
		overviewSwiper = new Swiper('.swiper_container_overview', {
			slidesPerView: 'auto',
			pagination: '.swiper-pagination-overview',
			parallax: true
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

function showAboutPost(){	   // 4.3 수정
	boxStatus="about";
	$(".about_container").show();
	$(".swiper_container_post").show();
	$(".about_guide").show();
	if(postSwiper==""){
		postSwiper = new Swiper('.swiper_container_post', {
			slidesPerView: 'auto',
			pagination: '.swiper-pagination-post',
			parallax: true
		});
		postSwiper.on("onSetTranslate", function(swiper, translate){
			if($(".about_navi").css("display")=="block"){
				if(translate>(mainWidth/4)){
					 slideAboutNavi(0)
					 swiper.slideTo(0)
				 }else if(translate < -(mainWidth*5.25)){
					 slideAboutNavi(2)
					 swiper.slideTo(0)
				 }	
			}
		});				
	}
}

function showAboutUpload(){	 // 4.3 수정
	boxStatus="about";
	openedAboutUploadPopup = true;

	$(".about_container").show();
	$(".swiper_container_upload").show();
	$(".about_guide").show();
	if(uploadSwiper==""){
		uploadSwiper = new Swiper('.swiper_container_upload', {
			slidesPerView: 'auto',
			pagination: '.swiper-pagination-upload',
			parallax: true
		});
		uploadSwiper.on("onSetTranslate", function(swiper, translate){
			if($(".about_navi").css("display")=="block"){
				if(translate>(mainWidth/4)){
					 slideAboutNavi(1)
					 swiper.slideTo(0)
				 }else if(translate < -(mainWidth*5.25)){
					 slideAboutNavi(3)
					 swiper.slideTo(0)
				 }
			}
		});				
	}
}

$(".about_card").swipe({	// 4.3 수정
	swipeUp:function(){
		$(".about_container").hide();
		$(".about_navi").hide();
		$(".about_card").hide();
		slideAboutNavi(0);
		boxStatus="";
	},
	threshold:10
});

$(".about_card_faq").swipe({	// 4.3 수정
	swipeLeft:function(){
		slideAboutNavi(4);
	},
	swipeRight:function(){
		slideAboutNavi(2);
		uploadSwiper.slideTo(0);
	},
	threshold:10
});

$(".about_card_contact").swipe({	// 4.3 수정
	swipeRight:function(){
		slideAboutNavi(3);
	},
	threshold:10
});
