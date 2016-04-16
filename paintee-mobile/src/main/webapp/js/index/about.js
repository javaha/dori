var aboutIndex=0;

function Card(){
    this.card   = $("<div>").addClass("randing_card").addClass("swiper-slide");
    this.margin = $("<div>").addClass("randing_card_margin");
    this.title  = $("<div>").addClass("randing_card_title");
    this.contents   = $("<div>").addClass("randing_card_contents");
}

Card.prototype = {
    buildCard : function(title, contents){
        $(this.title).attr("data-i18n", title);
        $(this.contents).attr("data-i18n", contents);

        this.card.append(this.margin);
        this.card.append(this.title);
        this.card.append(this.contents);
    }
}

function About(){
    this.parallax   = $("<div>").addClass("parallax-bg").attr("data-swiper-parallax", "-22%");
    this.wrapper    = $("<div>").addClass("swiper-wrapper");
    this.pagination = $("<div>").addClass("swiper-pagination")
}

About.prototype = {
    setParallax : function(url){
                    this.parallax.css("background-image", url);
    },
    setPagination : function(type){
                    this.pagination.addClass("swiper-pagination-"+type);
    },
    addCard     : function(title, contents){
                    var newCard = new Card();
                    newCard.buildCard(title, contents);
                    this.wrapper.append(newCard.card);
                    delete newCard;
                },
    buildAbout  : function(container){
                    container.append(this.parallax);
                    container.append(this.wrapper);
                    container.append(this.pagination);
                }
}
function buildOverview(){
    var overview = new About();
    overview.setParallax("url(./images/about_2.gif)");
    overview.setPagination("overview");
    overview.addCard('[html]about.overview.randingCardTitle',
                     '[html]about.overview.randingCardContents');
    overview.addCard('[html]about.overview.randingCardTitle2',
                     '[html]about.overview.randingCardContents2');
    overview.addCard('[html]about.overview.randingCardTitle3',
                     '[html]about.overview.randingCardContents3');
    overview.addCard('[html]about.overview.randingCardTitle2',
                     '[html]about.overview.randingCardContents2');
    overview.buildAbout($(".swiper_container_overview"));
    delete overview;
};

function buildPost(){
    var post = new About();
    post.setParallax("url(about_2.gif)");
    post.setPagination("post");
    post.addCard('[html]about.post.randingCardTitle1',
                 '[html]about.post.randingCardContents1');
    post.addCard('[html]about.post.randingCardTitle2',
                 '[html]about.post.randingCardContents2');
    post.addCard('[html]about.post.randingCardTitle3',
                 '[html]about.post.randingCardContents3');
    post.addCard('[html]about.post.randingCardTitle4',
                 '[html]about.post.randingCardContents4');
    post.addCard('[html]about.post.randingCardTitle5',
                 '[html]about.post.randingCardContents5');
    post.addCard('[html]about.post.randingCardTitle6',
                 '[html]about.post.randingCardContents6');
    post.buildAbout($(".swiper_container_post"));
    delete post;
};

function buildUpload(){
    var upload = new About();
    upload.setParallax("url(about_3.gif)");
    upload.setPagination("upload");
    upload.addCard('[html]about.upload.randingCardTitle1',
                   '[html]about.upload.randingCardContents1');
    upload.addCard('[html]about.upload.randingCardTitle2',
                   '[html]about.upload.randingCardContents2');
    upload.addCard('[html]about.upload.randingCardTitle3',
                   '[html]about.upload.randingCardContents3');
    upload.addCard('[html]about.upload.randingCardTitle4',
                   '[html]about.upload.randingCardContents4');
    upload.addCard('[html]about.upload.randingCardTitle5',
                   '[html]about.upload.randingCardContents5');
    upload.addCard('[html]about.upload.randingCardTitle6',
                   '[html]about.upload.randingCardContents6');
    delete upload;
};

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
        buildOverview();
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
        buildPost();
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
        buildUpload();
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
