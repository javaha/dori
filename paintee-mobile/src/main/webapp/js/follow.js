$(document).ready(function() {
	// 페이지 로딩 시 Popular 스와이프 홈 화면 정보구성
	initFollow();
});

// list container 시작  
var followSwiper = new Swiper('.swiper_container_follow', {
	slidesPerView : 'auto',
	centeredSlides : true,
	spaceBetween : mainWidth * 0.05,
	mousewheelControl : true,
	scrollbar : '.swiper-scrollbar-follow',
	scrollbarHide : true,
    lazyLoading: true,
    lazyLoadingInPrevNext: true,
    lazyLoadingInPrevNextAmount: 3
});

followSwiper.on("onSlideChangeStart", function(swiper) {
	console.log("followSwiper onSlideChangeStart");
	if (userID !== "") {
		// 화면에 로딩된 슬라이드 그림 개수
		var slidesCnt = swiper.slides.length - 1;
		// 만약, 현재 선택한 슬라이드가 로딩된 슬라이드의 수보다 하나 작을 경우 서버에 5개의 그림을 재요청
		console.log(swiper.slides.length + "-" + swiper.activeIndex);
		if (slidesCnt - 1 <= swiper.activeIndex && slidesCnt < 100) {
			new FollowController().getListData(slidesCnt);
		}
	}
});

// list 상태에서 mode container 스와이프 방지 && 마우스휠 해제/설정 && 페이지네이션 show/hide
followSwiper.on("onTransitionEnd", function(swiper) {
	listLock(swiper)
});

// side menu에 이벤트 설정
$("#menu_follow").click(function() {
	selectMenu(0);
});

// 초기 설정들
// 가로휠방지 && 페이지네이션숨김 && 위로스와이프방지
followSwiper.disableMousewheelControl();

followSwiper.on("onSetTranslate", function(swiper, translate) {
	swipeToMenu(swiper, translate)
});

function FollowController() {
}

FollowController.prototype = {
	getHomeCount : function () {
		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/follow/count", 
			null,
			"GET", 
			function(result) {
				controller.getHomeCountRes(result);
			}
		);
	}, 	
	getHomeCountRes : function (result) {
		console.log("FollowController.getHomeCountRes ::: " + result);
		setFollowHome(result);
	}, 	
	getListData : function(startRow) {
		console.log("FollowController.getListData startRow ::: " + startRow);

		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/follow/list?startRow=" + startRow, null,
			"GET", 
			function(result) {
				controller.getListDataRes(result);
			}
		);
	},
	getListDataRes : function(result) {
		console.log("FollowController.getListDataRes ::: " + result);
		for ( var index in result.list) {
			addPainting(followSwiper, 1, "follow", result.list[index]);
			if (followSwiper.slides.length > 100) {
				break;
			}
		}
	},
	getFollowsList : function() {
		console.log("FollowController.getFollowsList ");

		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/follows",
			null,
			"GET", 
			function(result) {
				controller.getFollowsListRes(result);
			}
		);
	},
	getFollowsListRes : function(result) {
		console.log("FollowController.getFollowsListRes ::: " + JSON.stringify(result));
		for ( var index in result.list) {
			addFollows(result.list[index].followsName, result.list[index].followsCount);
		}
	},
	getFollowingList : function() {
		console.log("FollowController.getFollowingList ");

		var controller = this;
		AjaxCall.call(
			apiUrl + "/index/following",
			null,
			"GET", 
			function(result) {
				controller.getFollowngListRes(result);
			}
		);
	},
	getFollowngListRes : function(result) {
		console.log("FollowController.getFollowngListRes ::: " + JSON.stringify(result));
		for ( var index in result.list) {
			addFollowing(result.list[index].followingName);
		}
	},
	addFollow : function(btn, name) {
		console.log("FollowController.addFollow ");
		this.btn = btn;
		var controller = this;
		AjaxCall.call(
				apiUrl + "/index/follows",
				{following: name},
				"POST", 
				function(result) {
					controller.addFollowRes(result);
				}
		);
	},
	addFollowRes : function(result) {
		// 추가한 사용자의 추가버튼을 비활성화 시키고 스타일을 변경 처리함
		this.btn.off("click");  // 이벤트 제거
		this.btn.find("i").css("color", "rgba(120,120,120,0.5)").html("done");
	},
	delFollowing : function(following, name) {
		console.log("FollowController.delFollowing ");
		this.following = following;
		var controller = this;
		AjaxCall.call(
				apiUrl + "/index/following/" + name,
				"", 
				"DELETE", 
				function(result) {
					controller.delFollowingRes(result);
				}
		);
	},
	delFollowingRes : function(result) {
		// 화면에서 선택한 사용자의 이름을 제거
		this.following.remove();
	}
};

// 각각의 home 화면 설정
function initFollow() {
	if (userID == "") {
		var welcome = new Home();
		welcome.setTitle("Welcome!");
		welcome.setExplain("환영합니다.<br>그림을 엽서로 보내거나 받아보세요.");
		welcome.hidePrev();
		followSwiper.appendSlide(welcome.buildStructure());
		$("#menu_follow").addClass("side_menu_major_inactive");
		delete welcome;
	} else {
		// 로그인 상태일 경우 홈카운트 가져오기
		new FollowController().getHomeCount();
	}
}

function setFollowHome(result) {
	console.log("setFollowHome ::: " + JSON.stringify(result));
	followSwiper.removeAllSlides();
	var followHome = new Home();
	var content1 = $("<div>").addClass("home_btn_follow").html("follows ")
				   .append($("<b>").html(" " + result.follow.followsCount))
				   .click(function() {
				        initFollows();
				   });

	var content2 = $("<div>").addClass("home_btn_follow").html("following ")
	               .append($("<b>").html(" " + result.follow.followingCount))
	               .click(function() {
					    initFollowing();
				   });
	
	followHome.setTitle("Follow");
	followHome.setExplain("가까운 사람들의 그림입니다.");
	followHome.setContents(content1);
	followHome.setContents(content2);
	followHome.hidePrev();
	followSwiper.appendSlide(followHome.buildStructure());
	delete followHome;
	delete content1;
	delete content2;
	$("#menu_follow").removeClass("side_menu_major_inactive");
	
	// 테이블에서 가져올 데이터의 시작 위치를 처음 로딩시 0번째 부터 조회
	new FollowController().getListData(0);
}

//팔로우즈/팔로잉 화면
function People() {
	this.title = $("<div>").addClass("people_title").addClass("popup_title");
	this.contents = $("<div>").addClass("people_contents").addClass("popup_contents");
}

People.prototype = {
	setTitle : function(title) {
		$(this.title).html(title);
	},
	buildUpload : function() {
		$(".people_box").append(this.title);
		$(".people_box").append(this.contents);
	}
}

/**
 * 나를 팔로잉 하는 사람들의 목록
 */
function initFollows() {
	setBox();
	$(".people_container").show();
	$(".people_box").empty();
	var people = new People();
	people.setTitle("Follows");
	people.buildUpload();
	
	// 나를 팔로우한 목록 요청
	new FollowController().getFollowsList();
}

function addFollows(name, isfriend) {
	var adder = new Follows();
	$(adder.build(name, isfriend)).appendTo($(".people_contents"));
	delete adder;
}

function Follows() {
	this.follows = $("<div>").addClass("people_list");
	this.name = $("<div>").addClass("people_list_name");
	this.btn = $("<div>").addClass("people_list_add")
			             .html("<div class='people_list_btn_text'> </div><i class='material-icons'>add</i>");
	this.freind = $("<div>").addClass("people_list_add")
			                .html("<div class='people_list_btn_text'> </div><i class='material-icons' style='color:rgba(120,120,120,0.5)'>done</i>");
	this.build = function(name, isfriend) {
		$(this.name).html(name);
		$(this.follows).append(this.name);
		if (isfriend) {
			$(this.follows).append(this.freind);
		} else {
			$(this.follows).append(this.btn);
		}
		var btn = this.btn;
		this.btn.click(function () {
			popName = "followPop";
			new FollowController().addFollow(btn, name);
		});
		
		return this.follows;
	}
}

function initFollowing() {
	boxStatus = "people";
	setBox();
	$(".people_container").show();
	$(".people_box").empty();
	var people = new People();
	people.setTitle("Following");
	people.buildUpload();
	
	// 내가 팔로우한 목록 요청
	new FollowController().getFollowingList();
}

function addFollowing(name) {
	var adder = new Following();
	$(adder.build(name)).appendTo($(".people_contents"));
	delete adder;
}

function Following() {
	this.following = $("<div>").addClass("people_list");
	this.name = $("<div>").addClass("people_list_name");
	this.btn = $("<div>").addClass("people_list_remove")
			             .html("<div class='people_list_btn_text'></div><i class='material-icons'>clear</i>");
	this.build = function(name) {
		$(this.name).html(name);
		$(this.following).append(this.name);
		$(this.following).append(this.btn);
		
		var following = this.following;
		this.btn.click(function () {
			popName = "followPop";
			new FollowController().delFollowing(following, name);
		});
		
		return this.following;
	}
}