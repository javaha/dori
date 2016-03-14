// 전역변수 설정
var mainWidth;
var slideWidth;
var userID = "";
var currentSwiper="";
var color;
var colorDark;
var purchaseWidth;
var purchaseStatus="";
var boxWidth;
var boxHeight;
var boxStatus="";
var popName="";

var userInfo = getUserInfoCookie();

if(userInfo) {
	userID = userInfo.email;
} else {
	userID = '';
}

console.log('userID:'+userID);

var imageUrl="http://localhost:8090";
//var imageUrl="http://192.168.1.31:8090";
//var imageUrl="http://192.168.43.63:8090";
var apiUrl=imageUrl+"/api";

setWidth();

// main container 시작
var mainSwiper = new Swiper('.swiper_container', {
    direction: 'vertical',
    mousewheelControl : true
});

// 각각의 home 화면 (follow/popular/new/my)
function Home(){
        this.container  =$("<div>").addClass("home_container swiper-slide");
        this.prev       =$("<div>").addClass("home_prev").html('<i class="material-icons">keyboard_arrow_down</i>');
        this.title      =$("<div>").addClass("home_title");
        this.contents   =$("<div>").addClass("home_contents");
        this.add        =$("<div>").addClass("home_contents_add");
        this.next       =$("<div>").addClass("home_next").html('<i class="material-icons">keyboard_arrow_up</i>');
}

Home.prototype = {
        setTitle:       function(title){
                            this.title.html(title);
                        },
        setExplain:     function(explain){
                            this.contents.html(explain);
                        },
        setContents:    function(contents){
                            this.contents.append(contents);
                        },
        setAdd:         function(contents){
                            this.add.append(contents);
                        },
        hidePrev:       function(){
                            this.prev.hide();
                        },
        hideNext:       function(){
                            this.next.hide();
                        },
        buildStructure: function(){
                            this.container.append(this.prev);
                            this.container.append($("<div>").addClass("home_title_margin"));
                            this.container.append(this.title);
                            this.container.append($("<div>").addClass("home_contents_margin"));
                            this.container.append(this.contents);
                            this.container.append($("<div>").addClass("home_contents_margin"));
                            this.container.append(this.add);
                            this.container.append(this.next);
                            return this.container;
                        }
}

// 그림 목록 화면
function Structure(index, paintingId, artistName){
		
        this.index              =index;
        this.container          =$("<div>").addClass("list_contents swiper-slide");

        this.listInfo           =$("<div>").addClass("list_info");
        this.listInfoRow_1      =$("<div>").addClass("list_info_row");
        this.listInfoRow_2      =$("<div>").addClass("list_info_row");
        this.listInfoSentence   =$("<div>").addClass("list_info_sentence");
        this.listInfoPosted     =$("<div>").addClass("list_info_posted");
        this.listInfoDate       =$("<div>").addClass("list_info_date");

        this.listPainting       =$("<div>").addClass("list_painting").attr("index", this.index);

        this.bottom             =$("<div>").addClass("bottom_bar");
        this.listArtist         =$("<div>").addClass("list_artist_btn").click(function(){showPersonal(artistName)});
        this.listPostBtn        =$("<div>").addClass("list_post_btn").html("post it").click(function(){purchase(paintingId)});

}
Structure.prototype = {
        setSentence:        function(sentence, wrighter){
                                this.listInfoSentence.html(sentence+"<br> <span class='list_info_sentence_wrighter'> by <b>"+wrighter+"</b></span>");
                            },
        setPostedNumber:    function(postedByPeople){
                                this.listInfoPosted.html("<span class='list_info_posted_num'>"+postedByPeople+"</span> people already posted it")
                            }, 
        setDate:            function(date){
                                this.listInfoDate.html(date)
                            },
        setPainting:        function(paintingId, imageUrl){
                                    if(mainWidth<729){
                                        this.listPainting.css({"width": mainWidth*0.8, "height": mainWidth*10/9});
                                    }else{
                                        this.listPainting.css({"width": "648px", "height": "900px"});
                                    }
                                this.listPainting.css("background-image", "url(" + imageUrl + ")");
                                this.listPainting.swipe({
                                    swipeUp:function(){
                                        loadDetail(paintingId, color, colorDark);
                                    },
                                    tap:function(){
                                        loadDetail(paintingId, color, colorDark);
                                    },
                                    threshold:10
                                });
                            },
        setColor:           function(color){
                                this.bottom.css("background-color", color);
                                this.listPostBtn.css("color", color);
                            },
        setArtist:          function(name){
                                this.listArtist.html(name);
                            },
        buildStructure:     function(){
                                this.listInfoRow_1.append(this.listInfoSentence);
                                this.listInfo.append(this.listInfoRow_1);
                                this.listInfoRow_2.append(this.listInfoPosted);
                                this.listInfoRow_2.append(this.listInfoDate);
                                this.listInfo.append(this.listInfoRow_2);
                                this.container.append(this.listInfo);
                                this.container.append(this.listPainting);
                                this.container.append(this.bottom);
                                this.container.append(this.listArtist);
                                this.container.append(this.listPostBtn);

                                return this.container;
                            }
}

// 현재 슬라이드 위치에서 앞으로 5개의 슬라이드가 없으면 새로 생성 (무한스크롤)
function addPainting(swiper, currentIndex, type, listData){
	
	if (!listData) { return; }
	
	var newSlide = new Structure(swiper.slides.length, listData.paintingId, listData.artistName);
    newSlide.setSentence(listData.sentence, listData.sentenceName ? listData.sentenceName : listData.artistName);
    newSlide.setPostedNumber(listData.postedPeopleCnt);
    newSlide.setDate(toEngDateStr(listData.uploadDate));
    newSlide.setArtist(listData.artistName);
    newSlide.setPainting(listData.paintingId, imageUrl + "/cmm/file/view/" + listData.fileId);
    if (type=="follow") {
        newSlide.setColor("hsl(200,60%,20%)");
    } else if (type=="popular") {
        newSlide.setColor("hsl(330,60%,20%)");
    } else if (type=="new") {
        newSlide.setColor("hsl(90,60%,20%)");
    } else if (type=="my") {
        newSlide.setColor("hsl(250,60%,20%)");
    }
    swiper.appendSlide(newSlide.buildStructure());
    delete newSlide;    
}

// 최초 5개 미리 생성
initMenu(userID);

// mainSwiper의 첫항목과 마지막항목에서 스와이프 방지
function mainLock(mainSwiper){
    if(mainSwiper.activeIndex==0){
        mainSwiper.lockSwipeToPrev();
        color = "190,60%,50%";
        colorDark = "200,60%,20%";
        if(isPersonal){hidePersonal()};
    }else if(mainSwiper.activeIndex==1){
        mainSwiper.unlockSwipes();
        color = "330,60%,50%";
        colorDark = "330,60%,20%";
        if(isPersonal){hidePersonal()};
    }else if(mainSwiper.activeIndex==2){
        mainSwiper.unlockSwipes();
        color = "80,60%,45%";
        colorDark = "90,60%,20%";
        if(isPersonal){hidePersonal()};
    }else if(mainSwiper.activeIndex==3){
        mainSwiper.lockSwipeToNext();
        color = "250,60%,50%";
        colorDark = "250,60%,20%";
        if(isPersonal){hidePersonal()};
    }
    currentSwiper="";
};


// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
function listLock(swiper){  
    if(swiper.isBeginning){
        if(mainSwiper.isBeginning){
            mainSwiper.unlockSwipeToNext();
        }else if(mainSwiper.isEnd){
            mainSwiper.unlockSwipeToPrev();
        }else{
            mainSwiper.unlockSwipes();
        }
        swiper.disableMousewheelControl();
        $(".swiper-scrollbar").hide();
        $(".home_btn").hide()
        $(".bottom_bar").css("opacity", 0);
    }else{
        mainSwiper.lockSwipes();
        swiper.enableMousewheelControl();
        $(".swiper-scrollbar").show();
        $(".home_btn").show()
        $(".bottom_bar").css("opacity", 1);
        currentSwiper=swiper;
    }
}
mainSwiper.on("onTransitionEnd", function(mainSwiper){mainLock(mainSwiper)});

// side menu 초기설정
function initMenu(userID){
    var sideLogin = $("#side_menu").find(".side_menu_login");
    if(userID==""){
        sideLogin.append($("<div>").addClass("login_btn").html("Log in").css("border-color", "rgb(100,100,100)").click(function(){showLogin()}));
    }else{
        sideLogin.empty()
        sideLogin.append($("<div>").addClass("side_menu_login_id").html(userID));
//        sideLogin.append($("<div>").html("edit | logout").click(function(){showProfile()}));
        sideLogin.append($("<div>").html("edit | logout").click(function(){logout()}));
    }
}

// side menu 이동
sideMenu = $("#side_menu");
function sideOn(){
    sideMenu.css("right", mainWidth-sideMenu.width());
    sideMenu.find(".side_menu_btn").hide();
    sideMenu.find(".side_menu_close").show();

    mainSwiper.lockSwipes();
    $(".modal").show();
    $(".modal").on("touchstart mousedown", function(e){
        e.stopPropagation();
        sideOff();
    });
}
function sideOff(){
    sideMenu.css("right", "100%");
    sideMenu.find(".side_menu_close").hide();
    sideMenu.find(".side_menu_btn").show();
    mainSwiper.unlockSwipes();

    $(".modal").hide();
}
sideMenu.find(".side_menu_btn").click(function(){
    sideOn();
});
sideMenu.find(".side_menu_close").click(function(){
    sideOff();
});
sideMenu.swipe({
    swipeLeft:function(){
        sideOff();
    }
});

//side menu에 이벤트 설정
function selectMenu(index){
    if(currentSwiper!==""){
        currentSwiper.slideTo(0);   
    }
    sideOff();
    mainSwiper.slideTo(index);
}

$("#menu_upload").click(function(){
    upload();
    sideOff();
});
$("#menu_reward").click(function(){
    reward();
    sideOff();
});

// 초기 설정들
// 가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
mainSwiper.lockSwipeToPrev();
$(".swiper-scrollbar").hide();
$(".home_btn").hide()

// 화면 리사이즈할때 layout 재 설정
$(window).resize(function (){
	setWidth();
    sideOff();
    if(isDetail){
        setDetailLayout();
    }
});

function setWidth() {
    mainWidth = $(window).width();
    mainHeight = $(window).height();    
    if(mainWidth>729){
    	slideWidth=648;
        $(".list_painting").css({"width": "648px", "height": "900px"});
    }else{
    	slideWidth=mainWidth*0.8;
        $(".list_painting").css({"width": slideWidth, "height": mainWidth*10/9});
    }
    if(purchaseStatus!=""){
        setPurchase();
    }
    setBox();
}

// upload/posted 버튼 설정
function btnToggle(btn){
    $(btn).toggleClass("home_btn_inactive");
}

// list의 맨앞으로 되돌아가기 버튼
$(".home_btn").click(function(){
    currentSwiper.slideTo(0);
})

// 스와이프해서 메뉴창 띄우기
 function swipeToMenu(swiper, translate){
     if(translate>((mainWidth/2)-(slideWidth/3))){
         sideOn();
     }
 }

// 모바일 웹브라우져를 전체화면으로 표시
$("#fullscreen_btn").click(function(){toggleFullScreen()});

function toggleFullScreen() {
    var doc = window.document;
    var docEl = doc.documentElement;

    var requestFull = docEl.requestFullscreen || docEl.mozRequestFullScreen || docEl.webkitRequestFullScreen;
    var cancelFull = doc.exitFullscreen || doc.mozCancelFullScreen || doc.webkitExitFullscreen;

    if(!doc.fullscreenElement && !doc.mozFullScreenElement && !doc.webkitFullscreenElement) {
        requestFull.call(docEl);
        $("#fullscreen_btn").find("i").html("fullscreen_exit");
    }else {
        cancelFull.call(doc);
        $("#fullscreen_btn").find("i").html("fullscreen");
    }
}


// 업로드/리워드가 표시되는 팝업창 사이즈 설정
function setBox(){
    if(boxStatus!=""){
        $(".popup_box").height(mainHeight*0.8);
        $(".popup_box").width($(".popup_box").height()*0.72);

        if($(".popup_box").width()>mainWidth*0.8){
            $(".popup_box").width(mainWidth*0.8);
            $(".popup_box").height(mainWidth*10/9);
        }
    }
}

// 업로드화면
function upload(){
    boxStatus = "upload"
    $(".upload_container").show();
    initUpload();
    setBox();
}

function Upload(){
    this.title      = $("<div>").addClass("upload_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("upload_contents").addClass("popup_contents");
    this.bottom     = $("<div>").addClass("upload_bottom").addClass("popup_bottom");
}

Upload.prototype = {
    setTitle    : function(title){
        $(this.title).html(title);
    },
    setContents : function(contents){
        $(this.contents).html(contents);
    },
    setBottom   : function(bottom){
        $(this.bottom).html(bottom);
    },
    buildUpload : function(){
        $(".upload_box").append(this.title);
        $(".upload_box").append(this.contents);
        $(".upload_box").append(this.bottom);
    }
}

function initUpload(){
    $(".upload_box").empty();
    var upload = new Upload();
    upload.setTitle("Upload Painting");
    upload.setContents("당신의 그림이 Post될 때 마다,<br>추가로 업로드할 수 있는 그림의 수가 늘어납니다.<br>지금까지 253회 Post된 당신은 최대 50개의 그림을 올릴 수 있고<br> 지금 <span class='reward_money'>7</span>개 의 그림을 더 올릴 수 있습니다.<br><br><br>업로드를 위해서는<br>가로 사이즈 <b>1080px</b> 세로 사이즈 <b>1440px</b><br>이상의 이미지가 필요합니다.");
    upload.setBottom("<div class='popup_btn upload_btn'><div class='purchase_btn_text'>Select image file </div><i class='material-icons'>folder</i></div>");
    upload.buildUpload();
    $(".upload_btn").click(function(){
        failUpload();
    })
    delete upload;
}

function failUpload(){
    $(".upload_box").empty();
    var uploadFail = new Upload();
    uploadFail.setTitle("Upload Painting");
    uploadFail.setContents('고화질의 출력을 위해 더 큰 이미지가 필요합니다.<br><b>가로 사이즈 1080px 세로 사이즈 1440px</b><br>이상의 이미지가 필요합니다.');
    uploadFail.setBottom("<div class='popup_btn upload_btn'><div class='purchase_btn_text'>Select image file </div><i class='material-icons'>folder</i></div>");
    uploadFail.buildUpload();
    $(".upload_btn").click(function(){
        successUpload();
    })
    delete uploadFail;
}

function successUpload(){
    $(".upload_box").empty();
    var uploadSuccess = new Upload();
    uploadSuccess.setTitle("Upload Painting");
    uploadSuccess.setContents('성공적으로 그림이 등록되었습니다.<br>당신의 생각, 당신의 느낌을 그림과 함께 적어주세요.<br><div class="upload_sentence"><span class="character_counter">0/200</span><textarea class="upload_sentence_textarea" length="200"></textarea><input type="checkbox"> private</div>');
    uploadSuccess.setBottom("<div class='popup_cancle_btn upload_btn'><i class='material-icons'>folder</i><div class='purchase_btn_text'>Select again</div></div><div class='popup_btn upload_btn'><div class='purchase_btn_text'>Done </div><i class='material-icons'>done</i></div>");
    uploadSuccess.buildUpload();
    delete uploadSuccess;
}

// 프로필 수정화면
function showProfile(){
    boxStatus = "profile";
    setBox();
    sideOff();
    $(".profile_container").show();
}

// 팝업 닫기
$(".return_btn").click(function(){
	// 구매 정보 초기화
	resetPurchase();
    $(".purchase_container").hide();
    $(".popup_container").hide();
    purchaseStatus = "";
    boxStatus = "";
});
$(".popup_container").click(function(){
    $(".purchase_container").hide();
    $(".popup_container").hide();
    console.log(".popup_container ------------------------" + popName);
    switch (popName) {
    case "followPop":
    	initFollow();  // Follow 팝업이 닫힐 때 새로 로딩한다.
    	break;
    }
    popName = "";  // 다시 초기화
});
$(".popup_box").click(function(e){
    e.stopPropagation();
});