// 개인페이지 생성
var personal = "";
var isPersonal = false;

/**
 * 특정 아티스트 정보 보이기
 * @param username
 */             
function showPersonal(username, paintingId){
    if (personal != "") hidePersonal();
    isPersonal = true;
    color = "250,60%,50%";
    colorDark = "250,60%,20%";
    
    personal = new Personal(username);
    mainSwiper.appendSlide(personal.buildStructure());
    personal.setSwiper();
    personal.swiper.on("onSlideChangeStart", function(swiper){
    	// 화면에 로딩된 슬라이드 그림 개수
		var slidesCnt = swiper.slides.length - 1;
		// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
		console.log(swiper.slides.length + "-" + swiper.activeIndex);
		if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
			new PersonalController().getPersonInfo(slidesCnt);
		}
    });
    personal.swiper.on("onTransitionEnd", function(swiper){listLock(swiper)});
    personal.swiper.on("onSetTranslate", function(swiper, translate){swipeToMenu(swiper, translate)});
      
    initPersonal(paintingId);
    selectMenu(4);
}

/**
 * 아티스트 정보 초기화 및 숨기기
 */
function hidePersonal(){
    isPersonal = false;
    mainSwiper.removeSlide(4);
    personal = "";
}

function Personal(username){
	this.username = username;
    this.container  = $("<div>").addClass("personal_container").addClass("swiper-slide");
    this.list       = $("<div>").addClass("list_container").addClass("swiper_container_personal");
    this.homeBtn    = $("<div>").addClass("home_btn").css("font-weight", 700).html(username).click(function(){currentSwiper.slideTo(0);});
    this.bottom     = $("<div>").addClass("bottom_bar").css("background-color", "hsl(250,60%,20%)");
    this.wrapper    = $("<div>").addClass("swiper-wrapper");
    this.scroll     = $("<div>").addClass("swiper-scrollbar").addClass("swiper-scrollbar-personal");
    this.swiper;
}

Personal.prototype = {
    setSwiper       : function(){
                        this.swiper = new Swiper('.swiper_container_personal', {
                            slidesPerView: 'auto',
                            centeredSlides: true,
                            spaceBetween: mainWidth*0.05,
                            mousewheelControl : true,
                            scrollbar: '.swiper-scrollbar-personal',
                            scrollbarHide: true
                        })
                    },
    buildStructure  : function(){
                        this.list.append(this.homeBtn);
                        this.list.append(this.bottom);
                        this.list.append(this.wrapper);
                        this.list.append(this.scroll);
                        this.container.append(this.list);
        
                        return this.container;
                    }
}

function initPersonal(paintingId) {
	console.log("paintingId ::: " + paintingId);
	
	// 기본 페이지 로딩 시의 데이터 조회
	new PersonalController(paintingId).getPersonInfo(0);
}

function setPersonal(result) {
    var personalHome = new Home();
    personalHome.setTitle(personal.username);
    var introduce = (result.personal.introduce) ? "<br />" + result.personal.introduce : "";
    personalHome.setExplain(personal.username + "<span data-i18n='personal.contents'>님이 업로드한 그림들입니다.</span>" + introduce);
    var content1 =
        $("<div>").addClass("home_btn_my").html("uploaded ").append($("<b>").html(" " + result.personal.uploadCount))
    personalHome.hideNext();
    personalHome.setContents(content1);
    personal.swiper.appendSlide(personalHome.buildStructure());
    delete personalHome;
    delete content1;
    
    // 다국어 처리
    exeTranslation('.main_container', lang);      
}

function PersonalController(paintingId) {
	this.startRow = 0;
	this.paintingId = paintingId;
}

PersonalController.prototype = {
	getPersonInfo : function (startRow) {
		this.startRow = startRow;
		var controller = this;
		var param = "?startRow=" + startRow  + "&artistName=" + personal.username;
		// 
		if (this.paintingId) {
			param += "&paintingId=" + this.paintingId;
		}
		console.log("param ::: " + param);
		AjaxCall.call(
			apiUrl + "/index/personal" + param, 
			null,
			"GET", 
			function(result) {
				controller.getPersonInfoRes(result);
			}
		);
	}, 	
	getPersonInfoRes : function (result) {
		var controller = this;
		// 처음 로딩시에만 메인화면 구성
		if (this.startRow == 0) {
			setPersonal(result);
		}
		for ( var index in result.list) {
			addPainting(personal.swiper, 1, "my", result.list[index]);
		}
		if (controller.paintingId) {
			personal.swiper.slideTo(personal.swiper.slides.length - 1, 0);
		}
	}
};

//get 방식으로 user, painting 가져오기 
function getRequest() {
    if(location.search.length > 1) {
        var get = new Object();
        var ret = location.search.substr(1).split('&');
        for(var i = 0; i < ret.length; i++) {
            var r = ret[i].split('=');
            get[r[0]] = r[1];
        }
        return get;
    } else {
//  		console.log("소셜에서 누르고 들어온 경우 아님");
        return false;
    }
}

var get = getRequest();
// user만 있으면 개인페이지로 이동, user, page가 있으면 상세화면으로 이동
// http://localhost:9080/index.html?user=a01&page=b0645fc6-a7bb-4f61-a133-d29ae45c4801
if(get) {
//	get.page = 'b0645fc6-a7bb-4f61-a133-d29ae45c4801';
//	get.user = 'a01';
	console.log("개인 페이지 들어옴 : " + JSON.stringify(get));
    if(get.page) {
        loadDetail(get.page, "200,60%,50%", "200,60%,20%");
        showPersonal(get.user, get.page);
    } else if(get.user) {
        showPersonal(get.user);
    }
};