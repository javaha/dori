// 상세화면의 구조
function DetailStructure(index, fileId, artistName, artstId, artistSentence, uploadDate, postedNum){
    this.index          = index;

    this.fileId          = fileId;
    this.artistName     = artistName;
    this.artstId        = artstId;
    this.artistSentence = artistSentence;
    this.uploadDate     = uploadDate;
    this.postedNum      = postedNum;

    this.detail             =$(".detail");

    this.detailBgContainer  =$("<div>").addClass("detail_bg_container");
    this.detailBgImg        =$("<img>").addClass("detail_bg_img");
    this.detailBgBottom     =$("<div>").addClass("detail_bg_bottom")

    this.detailContainer    =$("<div>").addClass("detail_container").addClass("swiper_container_detail");
    this.wrapper            =$("<div>").addClass("swiper-wrapper");

    this.detailMargin       =$("<div>").addClass("detail_margin").addClass("swiper-slide");
    this.detailCloseIcon    =$("<i>").addClass("material-icons").addClass("detail_margin_close").html("close");
    this.detailMarginIcon   =$("<i>").addClass("material-icons").addClass("detail_margin_guide").html("keyboard_arrow_up");

    this.detailArtist       =$("<div>").addClass("detail_artist").addClass("swiper-slide");
    this.detailArtistTop    =$("<div>").addClass("detail_artist_top");
    this.detailArtistBtn    =$("<div>").addClass("detail_artist_btn");
    this.detailArtistFollow =$("<div>").addClass("detail_artist_follow");
    this.detailArtistSentence=$("<div>").addClass("detail_artist_sentence");
    this.detailArtistDate   =$("<div>").addClass("detail_artist_date");
    this.detailArtistBottom =$("<div>").addClass("detail_artist_bottom").html("Share to ");
    this.sociconFacebook    =$("<span>").addClass("social_btn").addClass("socicon-facebook");
    this.sociconTwitter     =$("<span>").addClass("social_btn").addClass("socicon-twitter");
    this.sociconInstagram   =$("<span>").addClass("social_btn").addClass("socicon-instagram");
    this.sociconPinterest   =$("<span>").addClass("social_btn").addClass("socicon-pinterest");

    this.detailPostbar      =$("<div>").addClass("detail_postbar").addClass("swiper-slide");
    this.detailPostbarPostnum=$("<div>").addClass("detail_postbar_postnum");
    this.detailPostbarPostedNum=$("<span>").addClass("list_info_posted_num");

    this.detailPostBtn      =$("<div>").addClass("detail_post_btn").html("post it").click(function(){purchase()});
    this.detailScroll       =$("<div>").addClass("swiper-scrollbar").addClass("swiper-scrollbar-detail");
    this.returnBtn          =$("<div>").addClass("return_btn").append($("<i>").addClass("material-icons").html("keyboard_backspace"));
}

DetailStructure.prototype   ={
    setBG       : function(fileId){
        this.detailBgImg.attr("src", imageUrl+"/cmm/file/view/"+fileId);
    },
    setArtist   : function(artistName){
        this.detailArtistBtn.html(artistName);
    },
    setFollow   : function(artstId){
        this.detailArtistFollow.append('<i class="material-icons" style="font-size:12px">star</i> follow artist');
    },
    setSentence : function(artistSentence){
        this.detailArtistSentence.html(artistSentence);
    },
    setDate     : function(uploadDate){
        this.detailArtistDate.html(uploadDate);
    },
    setPostedNum: function(postedNum){
        this.detailPostbarPostedNum.html(postedNum);
    },
    buildDetail : function(){
        this.setBG(this.fileId);
        this.setArtist(this.artistName);
        this.setFollow(this.artstId);
        this.setSentence(this.artistSentence);
        this.setDate(this.uploadDate);
        this.setPostedNum(this.postedNum);

        this.detailBgContainer.append(this.detailBgImg);

        this.detailMargin.append(this.detailCloseIcon);
        this.detailMargin.append(this.detailMarginIcon);

        this.detailArtistTop.append(this.detailArtistBtn);
        this.detailArtistTop.append(this.detailArtistFollow);
        this.detailArtistSentence.append(this.detailArtistDate);
        this.detailArtistBottom.append(this.sociconFacebook);
        this.detailArtistBottom.append(this.sociconTwitter);
        this.detailArtistBottom.append(this.sociconInstagram);
        this.detailArtistBottom.append(this.sociconPinterest);
        this.detailArtist.append(this.detailArtistTop);
        this.detailArtist.append(this.detailArtistSentence);
        this.detailArtist.append(this.detailArtistBottom);

        this.detailPostbarPostnum.append(this.detailPostbarPostedNum).append(" people already posted it");
        this.detailPostbar.append(this.detailPostbarPostnum);

        this.wrapper.append(this.detailMargin);
        this.wrapper.append(this.detailArtist);
        this.wrapper.append(this.detailPostbar);

        this.detailContainer.append(this.wrapper);

        this.detail.append(this.detailBgContainer);
        this.detail.append(this.detailBgBottom);
        this.detail.append(this.detailContainer);
        this.detail.append(this.detailPostBtn);
        this.detail.append(this.detailScroll);
    }
}

//상세화면을 위한 변수들
var isDetail = false;
var postedLock = true;
var postedObj = new Array();
var postedIndex = new Array();
var postedLockBreakpoint;

var DetailController = {
	//디테일화면에서 보여질 데이터 조회
	getDetailData: function (paintingId, index, color, colorDark) {
		//TODO:임시로 paintingId 를 변경함.
		paintingId = "b0645fc6-a7bb-4f61-a133-d29ae45c48fe";
		AjaxCall.call(apiUrl+"/painting/"+paintingId, null, "GET", DetailController.getDetailDataRes);
	},
	getDetailDataRes: function (result, status, paintingId, index, color, colorDark) {
		console.log(result);

		//loadDetail 에서 하던내용
		initDetail(index, result);
		setDetailLayout();

		$(".detail").show().css("top", 200);
		$(".detail").animate({top: 0, opacity: 1}, 200);
		$(".detail_bg_container").css("background-color", "hsl("+color+")");
		$(".detail_bg_bottom").css("background-color", "hsla("+colorDark+", 1)");
		$(".detail_artist").css("background-color", "hsla("+colorDark+", 0.7)");
		$(".detail_postbar").css("background-color", "hsla("+colorDark+", 1)");

		lockPosted(detailSwiper);
	}
}

//디테일화면 표시
function loadDetail(index, color, colorDark){
	DetailController.getDetailData(index, color, colorDark);
}

//디테일화면 초기화
function initDetail(index, paintingInfo){
 isDetail = true;
 postedLock = true;
 postedObj = new Array();
 postedIndex = new Array();

 //this.detailStructure = new DetailStructure(index);
 this.detailStructure = new DetailStructure(index, paintingInfo.fileId, paintingInfo.artistName, paintingInfo.artistId, paintingInfo.sentence, paintingInfo.uploadDate, paintingInfo.postedNum);
 this.detailStructure.buildDetail();

 this.detailSwiper = new Swiper('.swiper_container_detail', {
     direction: 'vertical',
     slidesPerView: 'auto',
     centeredSlides: false,
     freeMode: false,
     freeModeMomentumRatio: 0.4,
     freeModeMomentumBounceRatio: 0.5,
     mousewheelControl : true,
     scrollbar: '.swiper-scrollbar-detail',
     scrollbarHide: true
 });
 this.detailSwiper.on("onSliderMove", function(swiper){
     changeMode(swiper);
 });
 this.detailSwiper.on("onSetTranslate", function(swiper){
     changeMode(swiper);
 });
}

//디테일화면 css값 설정
function setDetailLayout(){
 mainWidth = $(window).width();
 mainHeight = $(window).height();
 $(".detail_bg_container").css("height", mainHeight-50);
 $(".detail_bg_container").find("img").css("height", "100%");
 $(".detail_margin").css("height", mainHeight-50);
 $(".detail_artist_sentence").css("width", mainWidth);
 $(".detail_artist_sentence").css("height", $(".detail_artist").height()-100);
 if(mainHeight<625){
     postedLockBreakpoint = -250;
     $(".detail_artist_sentence").css("height", 150);
 }else{
     postedLockBreakpoint = -(mainHeight*0.4);
     $(".detail_artist_sentence").css("height", -(postedLockBreakpoint)-100);
 };
}

//디테일화면 닫기
function closeDetail(){
 if(isDetail){
     $(".detail").animate({top: 200, opacity: 0}, 200, "linear", function(){
         $(".detail").empty();
         $(".detail").hide().css("top", 0);
         delete detailStructure;
         delete detailSwiper;
     });
     isDetail = false;   
 }
}

//디테일화면의 스크롤 잠금/열기
function changeMode(swiper){            
 var translate = swiper.translate;

 if(translate>50){
     closeDetail();
 }else if(translate<postedLockBreakpoint){
     if(postedLock){
         unlockPosted(swiper);
     }
     callPosted(swiper);
 }else if(translate>=postedLockBreakpoint){
     if(!postedLock){
         lockPosted(swiper);
     }
 }
}

//디테일화면의 스크롤 잠금
function lockPosted(swiper){
 console.log("lock!");

 postedLock = true;
 hidePosted(swiper);
 swiper.params.freeMode = false;

 $(".swiper-scrollbar-detail").hide();
 $(".detail_post_btn").appendTo($(".swiper_container_detail"))
}

//디테일화면의 스크롤 열기
function unlockPosted(swiper){
 console.log("unlock!");

 postedLock = false;
 swiper.params.freeMode = true;

 callPosted(swiper);

 $(".swiper-scrollbar-detail").show();
 $(".detail_post_btn").appendTo($(".detail_postbar"))
}

//Detail화면의 댓글 구조
function Posted(index){
 this.index      =index;
 this.container  =$("<div>").addClass("detail_posted swiper-slide").html("posted by ").css("background-color", "hsl("+colorDark+")");
 this.postee     =$("<div>").addClass("detail_postee_btn");
 this.sentence   =$("<div>").addClass("detail_posted_sentence").css("width", mainWidth*0.96);
}
Posted.prototype = {
 setPostee:      function(index){
     this.postee.html("postee");
 },
 setSentence:    function(index){
     this.sentence.html("여기서는 구매한 사람들이 남긴 한마디들을<br>볼수있을거야");
 },
 buildPosted:    function(){
     this.setPostee(this.index);
     this.setSentence(this.index);
     this.container.append(this.postee);
     this.container.append(this.sentence);

     return this.container;
 }
}

//Detail화면의 댓글 무한스크롤 (10개씩 추가)
function callPosted(swiper){
 var isPostedEnd;
 if(swiper.slides.length<swiper.activeIndex+10 && swiper.slides.length<=20){
     for(swiper.slides.length ; swiper.slides.length < swiper.activeIndex+10 ;){
         if(swiper.slides.length > 20){
             break;
         }
         if(!postedObj[swiper.slides.length-3]){
             var newPosted = new Posted(swiper.slides.length-3);
             postedObj[swiper.slides.length-3] = newPosted.buildPosted();
             postedIndex.push(swiper.slides.length);
             delete newPosted;
         }
         swiper.appendSlide(postedObj[swiper.slides.length-3]);
     }
 }
}

//Detail화면의 댓글 지우기
function hidePosted(swiper){
 swiper.removeSlide(postedIndex);
}

/* ori
DetailStructure.prototype   ={
    setBG       : function(index){
        this.detailBgImg.attr("src", "p"+index%5+".png");
    },
    setArtist   : function(index){
        this.detailArtistBtn.html("artist"+index);
    },
    setFollow   : function(index){
        this.detailArtistFollow.append('<i class="material-icons" style="font-size:12px">star</i> follow artist');
    },
    setSentence : function(index){
        this.detailArtistSentence.html("여기에는 작가가 쓴 한마디가 나오겠지?");
    },
    setDate     : function(index){
        this.detailArtistDate.html("05. May");
    },
    setPostedNum: function(index){
        this.detailPostbarPostedNum.html(1+index);
    },
    buildDetail : function(){
        this.setBG(this.index);
        this.setArtist(this.index);
        this.setFollow(this.index);
        this.setSentence(this.index);
        this.setDate(this.index);
        this.setPostedNum(this.index);

        this.detailBgContainer.append(this.detailBgImg);

        this.detailMargin.append(this.detailCloseIcon);
        this.detailMargin.append(this.detailMarginIcon);

        this.detailArtistTop.append(this.detailArtistBtn);
        this.detailArtistTop.append(this.detailArtistFollow);
        this.detailArtistSentence.append(this.detailArtistDate);
        this.detailArtistBottom.append(this.sociconFacebook);
        this.detailArtistBottom.append(this.sociconTwitter);
        this.detailArtistBottom.append(this.sociconInstagram);
        this.detailArtistBottom.append(this.sociconPinterest);
        this.detailArtist.append(this.detailArtistTop);
        this.detailArtist.append(this.detailArtistSentence);
        this.detailArtist.append(this.detailArtistBottom);

        this.detailPostbarPostnum.append(this.detailPostbarPostedNum).append(" people already posted it");
        this.detailPostbar.append(this.detailPostbarPostnum);

        this.wrapper.append(this.detailMargin);
        this.wrapper.append(this.detailArtist);
        this.wrapper.append(this.detailPostbar);

        this.detailContainer.append(this.wrapper);

        this.detail.append(this.detailBgContainer);
        this.detail.append(this.detailBgBottom);
        this.detail.append(this.detailContainer);
        this.detail.append(this.detailPostBtn);
        this.detail.append(this.detailScroll);
    }
}


//디테일화면 초기화
function initDetail(index){
 isDetail = true;
 postedLock = true;
 postedObj = new Array();
 postedIndex = new Array();

 this.detailStructure = new DetailStructure(index);
 this.detailStructure.buildDetail();

 this.detailSwiper = new Swiper('.swiper_container_detail', {
     direction: 'vertical',
     slidesPerView: 'auto',
     centeredSlides: false,
     freeMode: false,
     freeModeMomentumRatio: 0.4,
     freeModeMomentumBounceRatio: 0.5,
     mousewheelControl : true,
     scrollbar: '.swiper-scrollbar-detail',
     scrollbarHide: true
 });
 this.detailSwiper.on("onSliderMove", function(swiper){
     changeMode(swiper);
 });
 this.detailSwiper.on("onSetTranslate", function(swiper){
     changeMode(swiper);
 });
}
*/