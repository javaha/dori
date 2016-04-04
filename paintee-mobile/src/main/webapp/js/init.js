$(function () {
	// 로그인 되어있지 않은 경우 about 페이지 띄우기
	if(!userInfo) {
		showAboutOverview();
	}
});
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

// 나중에 로그인 사용자의 언어를 설정해야 한다.
var lang = "en";

//if(userInfo) {
//	console.log('userInfo.location:'+userInfo.location);
//	lang = userInfo.location;
//}

console.log('lang:'+lang);
console.log('userID:'+userID);

// 최초 화면 로딩시 해야할 일
$(function () {
	setSideMenu();  // 사이드 메뉴 설정
});

/**
 * 사이드 메뉴의 액션 설정 및 활성화/비활성화 처리
 */
function setSideMenu() {
	// 사이드 메뉴 활성화 및 비활성화 설정
	if (!userID) {
		$("#menu_upload").addClass("side_menu_minor_inactive").click(sideOff);
		$("#menu_reward").addClass("side_menu_minor_inactive").click(sideOff);
	}
	else {
		$("#menu_upload").click(function(){
		    upload();
		    sideOff();
		});
		$("#menu_reward").click(function(){
		    reward();
		    sideOff();
		});
	}

	// 사이드 메뉴 언어 설정
	// 로그인 정보를 매번 가져오지 않기 때문에 사이트 로딩시 로그인 상태일 경우만 로그인 정보를 가져온다.
	if (userInfo) {
		AjaxCall.call(apiUrl + "/user/me", 
			null, 
			"GET", 
			function (result) {
				lang = result.userInfo.language ? result.userInfo.language : "en";
				$(".side_menu_lang_select").val(lang);
			}
		);
	}

	// 사이드메뉴 about 링크
	$('#menu_about').on('click', function() { showAbout(); });
}


var imageUrl="http://localhost:8090";
//var imageUrl="http://192.168.43.89:8090";
//var imageUrl="http://192.168.0.3:8090";
//var imageUrl="http://www.paintee.com:8090";
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
function Structure(data) {
        this.index              =data.index;
        this.container          =$("<div>").addClass("list_contents swiper-slide");

        this.listInfo           =$("<div>").addClass("list_info");
        this.listInfoRow_1      =$("<div>").addClass("list_info_row");
        this.listInfoRow_2      =$("<div>").addClass("list_info_row");
        this.listInfoSentence   =$("<div>").addClass("list_info_sentence");
        this.listInfoPosted     =$("<div>").addClass("list_info_posted");
        this.listInfoDate       =$("<div>").addClass("list_info_date");

        this.listPainting       =$("<div>").addClass("list_painting").attr("index", this.index); // img 태그로 수정
        // mobile 기기의 pixel ratio를 반영한 가변 이미지 반영
        // this.listPainting       =$("<img>").addClass("list_painting").attr("index", this.index);

        this.bottom             =$("<div>").addClass("bottom_bar");
        this.listArtist         =$("<div>").addClass("list_artist_btn").click(function() {
        							console.log("currentSwiper : " + currentSwiper);
						        	// 히스토리 설정
						        	replaceHistory({"call": "list", "mainIndex": mainSwiper.activeIndex, "index": currentSwiper.activeIndex ? currentSwiper.activeIndex : data.index});
						        	addHistory({"call": "personal"});
        							showPersonal(data.artistName)
        						});
        this.listPostBtn        =$("<div>").addClass("list_post_btn").html("post it")
                                           .click( function() {
                                        	   		   if (data.paintingStatus == "D") {
                                        	   			   alert($.i18n.t('alert.common.delPainting'));
                                        	   			   return;
                                        	   		   }
                                        	           purchase(data.paintingId, data.artistName);
                                        	       }
                                           );
        this.listStatusBtn      =$("<div>").addClass("list_status_btn");                 
        this.listStatusStc      =$("<div>").addClass("list_status_sentence");            
        this.listCancelBtn      =$("<div>").addClass("list_cancel_btn").html("Cancel");  
        this.listRefundBtn      =$("<div>").addClass("list_refund_btn").html("Cancel Refund");  
        this.listResendBtn      =$("<div>").addClass("list_resend_btn").html("Resend");  
        this.listConfirmBtn     =$("<div>").addClass("list_confirm_btn").html("Confirm"); 
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
                                /*
                            	// mobile 기기의 pixel ratio를 반영한 가변 이미지 반영
                                this.listPainting.attr("src", "p0-s.png");
                                if(window.devicePixelRatio<=1){
                                    this.listPainting.attr("srcset", imageUrl+"-m.png 729w, "+imageUrl+"-s.png 405w");
                                }else if(window.devicePixelRatio>1 && window.devicePixelRatio<=2){
                                    this.listPainting.attr("srcset", imageUrl+"-l.png 675w, "+imageUrl+"-m.png 405w, "+imageUrl+"-s.png 225w");
                                }else if(window.devicePixelRatio>2){
                                    this.listPainting.attr("srcset", imageUrl+"-l.png 450w, "+imageUrl+"-m.png 270w, "+imageUrl+"-s.png 150w");
                                }
                            	*/
                            	
                            	/*
                            	// image lazy loading을 사용할 경우, 아래 코드 이용 + swiper 초기화시 lazyLoading: true 선언 필요
                                this.listPainting.attr("src", "p0-s.png");
                                if(window.devicePixelRatio<=1){
                                    this.listPainting.attr("srcset", imageUrl+"-m.png 729w, "+imageUrl+"-s.png 405w");
                                }else if(window.devicePixelRatio>1 && window.devicePixelRatio<=2){
                                    this.listPainting.attr("data-srcset", imageUrl+"-l.png 675w, "+imageUrl+"-m.png 405w, "+imageUrl+"-s.png 225w");
                                }else if(window.devicePixelRatio>2){
                                    this.listPainting.attr("data-srcset", imageUrl+"-l.png 450w, "+imageUrl+"-m.png 270w, "+imageUrl+"-s.png 150w");
                                }
                            	*/
                            	
                                this.listPainting.css("background-image", "url(" + imageUrl + ")"); // 가변이미지 코딩으로 대체
                                this.listPainting.swipe({
                                    swipeUp:function(){
                                        loadDetail(paintingId, color, colorDark);
                                        replaceHistory({"call": "detailPop"});
                                        addHistory({"call": "dummy"});
                                    },
                                    tap:function(){
                                        loadDetail(paintingId, color, colorDark);
                                        replaceHistory({"call": "detailPop"});
                                        addHistory({"call": "dummy"});
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
        setStatus:          function(listData){                                                                   
                                if(listData.paintingStatus == "1"){                                                        
                                    this.listStatusBtn.addClass("list_status_preparing")
                                                      .html("preparing")
                                                      .attr("id", "exeBtn" + listData.seq)
                                                      .click(function(){
                                                    	  		showCancel(this, listData);
                                                      });
                                } else if(listData.paintingStatus == "2"){                                                     
                                    this.listStatusBtn.addClass("list_status_sended")
                                                      .html("sended")
                                                      .attr("id", "exeBtn" + listData.seq)
                                                      .click(function(){
						                                  showResend(this, listData);
						                              });
                                } else if(listData.paintingStatus == "3"){                                                     
                                    this.listStatusBtn.addClass("list_status_refund")
					                                  .html("refund")
					                                  .attr("id", "exeBtn" + listData.seq)
					                                  .click(function(){
							                              showRefund(this, listData);
							                          });
                                } else if(listData.paintingStatus == "99"){                                                       
                                    this.listStatusBtn.addClass("list_status_done")
                                    				  .html("delete")
                                    				  .click(function () {
                                    					  new PurchaseController().delStatusPurchase(listData); 
                                    				  });             
                                } else if (listData.paintingStatus == "N"){                                                       
                                	this.listStatusBtn.addClass("list_status_done")
					                  				  .html("delete")
					                  				  .click(function () {
					                  					  new PurchaseController().delStatusPainting(listData); 
					                  				  });                          
                                }
                                
        },                            
        buildStructure:     function(type, listData){
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
                                // 마이페이지의 그림 하단의 상태표시 버튼
                                if (type == "my") {
                                	switch(listData.paintingStatus) {
                                	case "1":
                                	case "2":
                                	case "3":
                                	case "99":
                                	case "N":
                                		this.container.append(this.listStatusBtn);  
                                		this.container.append(this.listCancelBtn);  
                                		this.container.append(this.listRefundBtn);  
                                		this.container.append(this.listResendBtn);  
                                		this.container.append(this.listConfirmBtn); 
                                		this.container.append(this.listStatusStc);
                                		break;
                                	}
                                }
                                return this.container;
                            }
}

// 현재 슬라이드 위치에서 앞으로 5개의 슬라이드가 없으면 새로 생성 (무한스크롤)
function addPainting(swiper, currentIndex, type, listData){
	
	if (!listData) { return; }
	var data = {
		index: swiper.slides.length,
		paintingId: listData.paintingId,
		artistName: listData.artistName
	};

	// 업로드된 그림일 경우 U, 구매된 그림일 경우 P
	// 사용 페이지 : My
	if (listData.paintingStatus) {
		data.paintingStatus = listData.paintingStatus;
	}
	var newSlide = new Structure(data);
    newSlide.setSentence((listData.paintingStatus == "B") ? "It was blind by the administrator." : listData.sentence, listData.sentenceName ? listData.sentenceName : listData.artistName);
    newSlide.setPostedNumber(listData.postedPeopleCnt);
    newSlide.setDate(toEngDateStr(listData.uploadDate));
    newSlide.setArtist(listData.artistName);
    newSlide.setPainting(listData.paintingId, imageUrl + "/cmm/file/view/" + responsive + "/" + listData.fileId);
    if (type=="follow") {
        newSlide.setColor("hsl(200,60%,20%)");
    } else if (type=="popular") {
        newSlide.setColor("hsl(330,60%,20%)");
    } else if (type=="new") {
        newSlide.setColor("hsl(90,60%,20%)");
    } else if (type=="my") {
        newSlide.setColor("hsl(250,60%,20%)");
        newSlide.setStatus(listData);   
    }
    
    swiper.appendSlide(newSlide.buildStructure(type, listData));
    delete newSlide;    
}

function showCancel(clicked, listData){
    $(clicked).parent().find(".list_cancel_btn").fadeIn().one("click", function () { 
   		new PurchaseController().cancelPurchase(listData); 
   		hideCancel(this);
    });
    $(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.cancelStatusPurchase'></span>").fadeIn().click(function(){hideCancel(this)});
    setTimeout(function(){hideCancel(clicked)}, 5000);
    exeTranslation('.main_container', lang);
}
function hideCancel(clicked){
    $(clicked).parent().find(".list_cancel_btn").fadeOut();
    $(clicked).parent().find(".list_status_sentence").fadeOut();
}

function showRefund(clicked, listData){
	$(clicked).parent().find(".list_refund_btn").fadeIn().one("click", function () { 
		new PurchaseController().cancelRefundPurchase(listData); 
		hideRefund(this);
	});
	$(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.refundStatusPurchase'></span>").fadeIn().click(function(){hideRefund(this)});
	setTimeout(function(){hideRefund(clicked)}, 5000);
	exeTranslation('.main_container', lang);
}
function hideRefund(clicked){
	$(clicked).parent().find(".list_refund_btn").fadeOut();
	$(clicked).parent().find(".list_status_sentence").fadeOut();
}
function showResend(clicked, listData){
    $(clicked).parent().find(".list_resend_btn").fadeIn().one("click", function () { 
   		new PurchaseController().resendPurchase(listData); 
   		hideResend(this);
    });
    $(clicked).parent().find(".list_confirm_btn").fadeIn().click(function(){
    	new PurchaseController().completePurchase(listData); 
    	hideResend(this)
    });
    $(clicked).parent().find(".list_status_sentence").empty().html("<span data-i18n='[html]my.sendStatusPurchase'></span>").fadeIn().click(function(){hideResend(this)});
    setTimeout(function(){hideResend(clicked)}, 5000);
    exeTranslation('.main_container', lang);
}
function hideResend(clicked){
    $(clicked).parent().find(".list_resend_btn").fadeOut();
    $(clicked).parent().find(".list_confirm_btn").fadeOut();
    $(clicked).parent().find(".list_status_sentence").fadeOut();
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

        //TODO:profile edit 버튼과 logout 버튼 분리 디자인 확인해야함.
        var editBtn = $("<a>").html("edit").on("click", function(){showProfile()});
        var logoutBtn = $("<a>").html("logout").on("click", function(){logout()});

        var btnGroup = $("<div>").append(editBtn).append(" | ").append(logoutBtn);

        sideLogin.append(btnGroup);
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

// 팝업 닫기
$(".return_btn").on('click', function(){
	boxStatus = 'clickedCloseBtn';

	// 구매 정보 초기화
	closePopup();
});

$(".popup_container").click(function(){
	closePopup();
    popName = "";  // 다시 초기화
});

var openedAboutUploadPopup = false;

function closePopup() {
	console.log("boxStatus : " + boxStatus);
	var isPopupOpened = false;

	var openPopupContainer;

	$('.popup_container').each(function( index ) {
		if($(this).css("display") != "none") {
			isPopupOpened = true;
			openPopupContainer = $(this);
		}
	});

	if(isPopupOpened) {
		var divClass = openPopupContainer.attr('class');

		// boxStatus payment
		if (divClass.indexOf('payment_container') > -1) {
			// 구매 정보 초기화
			resetPurchase();
			purchaseStatus = "";
			openPopupContainer.hide();
			history.go(-2);
			boxStatus = "";
		} 
		else if (boxStatus == "rewardStep2") {
			history.go(-2);
		}
		else if (divClass.indexOf('reward_container') > -1
					|| divClass.indexOf('people_container') > -1
					|| divClass.indexOf('profile_container') > -1) {
			openPopupContainer.hide();
		}
		else if (divClass.indexOf('upload_container') > -1) {
			if(boxStatus == "clickedCloseBtn" && openedAboutUploadPopup == false) {
				openPopupContainer.hide();
				history.back();
			}
		}
		else {
			history.back();
		}
	}

	openedAboutUploadPopup = false;
	boxStatus = "";
}

$(".popup_box").click(function(e){
    e.stopPropagation();
});

/**
 *   홈 페이지 데이터 리로딩
 *   그림 구매시 : My, Popular  
 *   팔로우 대상 변경 시 : Follow, 
 */
function dataReload(loadPages) {
	console.log("dataReload.....");
	for (var index in loadPages) {
		eval(loadPages[index]);
	}
}

/**
 *  사이드 메뉴 언어 선택시 테이블 언어 변경 및 화면 언어 적용
 */
$(".side_menu_lang_select").change(function(event) {
	lang = $(this).val();
	exeTranslation(".main_container", lang);
	if (userID) {
		AjaxCall.call(apiUrl + "/user/me", 
			{"language": lang}, 
			"PUT", 
			function (result) {
				console.log(result);			
			}
		);
	} 
});
