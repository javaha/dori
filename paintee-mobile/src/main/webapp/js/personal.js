// 개인페이지 생성
var personal = "";
var isPersonal = false;

/**
 * 특정 아티스트 정보 보이기
 * @param username
 */             
function showPersonal(username){
    if (personal != "") hidePersonal();
    isPersonal = true;
    color = "250,60%,50%";
    colorDark = "250,60%,20%";
    
    personal = new Personal(username);
    mainSwiper.appendSlide(personal.buildStructure());
    personal.setSwiper();
    personal.swiper.on("onSlideChangeStart", function(swiper){
//    	addPainting(swiper, swiper.activeIndex, "my")

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
      
    initPersonal(username);
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
    this.homeBtn    = $("<div>").addClass("home_btn").css("font-weight", 700).html(username);
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

function initPersonal(username) {
	// 기본 페이지 로딩 시의 데이터 조회
	new PersonalController(username).getPersonInfo(0);
}

function setPersonal(result) {
    var personalHome = new Home();
    personalHome.setTitle(personal.username);
    personalHome.setExplain(personal.username + "님이 업로드한 그림들입니다.");
    var content1 =
        $("<div>").addClass("home_btn_my").html("uploaded ").append($("<b>").html(" " + result.uploadCount))
    personalHome.hideNext();
    personalHome.setContents(content1);
    personal.swiper.appendSlide(personalHome.buildStructure());
    delete personalHome;
    delete content1;
}

function PersonalController(username) {
	this.startRow = 0;
	this.username = username;
}

PersonalController.prototype = {
	getPersonInfo : function (startRow) {
		this.startRow = startRow;
		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/personal?startRow=" + startRow  + "&artistName=" + personal.username, 
			null,
			"GET", 
			function(result) {
				controller.getPersonInfoRes(result);
			}
		);
	}, 	
	getPersonInfoRes : function (result) {
		// 처음 로딩시에만 메인화면 구성
		if (this.startRow == 0) {
			setPersonal(result);
		}
		
		for ( var index in result.list) {
			addPainting(personal.swiper, 1, "my", result.list[index]);
		}
	}
};

//수정 11.Mar
//특정 index로 바로 이동
function goPainting(username, page){
  showPersonal(username);
  for(personal.swiper.slides.length ; personal.swiper.slides.length <= page ;){
      var newSlide = new Structure(personal.swiper.slides.length);
      newSlide.setSentence(newSlide.index+"번째 그림에 대한 설명입니다. <br> 200자 까지 적을 수 있습니다.", "wrighter");
      newSlide.setPostedNumber(newSlide.index);
      newSlide.setDate("11. Nov");
      newSlide.setArtist();
      newSlide.setColor("hsl(250,60%,20%)");
      personal.swiper.appendSlide(newSlide.buildStructure());
      delete newSlide;    
  }
  personal.swiper.slideTo(page, 0);
}

//수정 11.Mar
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
  	console.log("소셜에서 누르고 들어온 경우 아님");
      return false;
  }
}

var get = getRequest();

//user만 있으면 개인페이지로 이동, user, page가 있으면 상세화면으로 이동
if(get) {
	get.page = 'b0645fc6-a7bb-4f61-a133-d29ae45c4801';
	console.log("개인 페이지 들어옴 : " + JSON.stringify(get));
  if(get.page) {
      loadDetail(get.page, "200,60%,50%", "200,60%,20%");
      showPersonal(get.user);
  } else if(get.user) {
      showPersonal(get.user);
  }
};