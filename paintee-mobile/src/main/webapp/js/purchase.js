var purchaseController;
// 구매화면으로 이동
function purchase(paintingId) {
	
	if (userID == "") {
		showLogin();
		return ;
	} 
	
	// 구매 팝업 정보 조회
	purchaseController = new PurchaseController(paintingId);
	purchaseController.purchasePopInfo();
}

function initPurchasePop(result) {
	// console.log("purchase : " + paintingId);
	 console.log("result : " + JSON.stringify(result));
    
	 purchaseController.basicAddr  = result.user.basicAddr;
	 purchaseController.detailAddr = result.user.detailAddr;
	 
	 $(".purchase_container").show();
    
    // 주소설정...
    $("[name=location]").val(result.user.location ? result.user.location : 1);
    $("[name=receiverBasicAddr]").val(result.user.basicAddr);
    $("[name=receiverDetailAddr]").val(result.user.detailAddr);
    $("[name=receiverZipcode]").val(result.user.zipcode);
    
    // 기존 설정된 이벤트 제거
    $(".purchase_pay_btn").off("click");
    $(".purchase_pay_btn" ).click(function() {
    	payment();
    });

    // 우편번호 입력박스 키이벤트 등록
    $("[name=receiverZipcode]").keydown(
    	function (event) {
    		return limitNumber(event);
    	}	
    );
    
    purchaseStatus = "sentence";
    setWidth();
    
    setPostUI($("[name=location]").val());
}

function setPurchase(){
	// console.log("setPurchase");
    if(mainWidth<500){
        purchaseWidth = mainWidth*0.9;
        if(purchaseStatus!="address"){
            $(".purchase_box").css("left", mainWidth*0.1);
            $(".purchase_next_btn").show();   
        }else{
            $(".purchase_box").css("left", -purchaseWidth);
            $(".purchase_prev_btn").show();
        }
    }else if(mainWidth<950){
        purchaseWidth = 450;
        if(purchaseStatus!="address"){
            $(".purchase_box").css("left", mainWidth*0.1);
            $(".purchase_next_btn").show();
        }else{
            $(".purchase_box").css("left", mainWidth*0.9-900);
            $(".purchase_prev_btn").show();
        }
    }else{
        purchaseWidth = 450;
        $(".purchase_box").css("left", (mainWidth-900)/2);
        $(".purchase_next_btn").hide();
        $(".purchase_prev_btn").hide();
    }
    $(".purchase_box").width(purchaseWidth*2);
    $(".purchase_box_left").width(purchaseWidth);            
    $(".purchase_box_right").width(purchaseWidth);
}

$(".purchase_next_btn").click(function(){
	// console.log("purchase_next_btn");
    purchaseStatus="address";
    if(mainWidth<500){
        $(".purchase_box").css("left", -purchaseWidth);
        $(".purchase_next_btn").hide();
        $(".purchase_prev_btn").show();
    }else if(mainWidth<950){
        $(".purchase_box").css("left", mainWidth*0.9-900);
        $(".purchase_next_btn").hide();
        $(".purchase_prev_btn").show();
    }
});

$(".purchase_prev_btn").click(function(){
	// console.log("purchase_prev_btn");
    purchaseStatus="sentence";
    if(mainWidth<500){
        purchaseWidth = mainWidth*0.9;
        $(".purchase_box").css("left", mainWidth*0.1);
        $(".purchase_next_btn").show();
        $(".purchase_prev_btn").hide();
    }else if(mainWidth<950){
        purchaseWidth = 450;
        $(".purchase_box").css("left", mainWidth*0.1);
        $(".purchase_next_btn").show();
        $(".purchase_prev_btn").hide();
    }
});

$(".purchase_container").click(function(){
	// console.log("purchase_container");
    $(".purchase_container").hide();
    purchaseStatus = "";
    boxStatus = "";
    // 입력데이터 초기화
    resetPurchase();
});

$(".purchase_box").click(function(e){
	// console.log("purchase_box");
    e.stopPropagation();
});

$("[name=location]").change(function(e){
	setPostUI($("[name=location]").val());
	e.stopPropagation();
});

function setPostUI(type) {
	if (type == '1') {
		// 기본 주소 선택시 
		$("[name=receiverCity]").attr("disabled", "disabled");
		$("[name=receiverBasicAddr]").attr("readOnly", "readOnly");
		$("[name=receiverBasicAddr]").focus(function () {
			execDaumPostcode('purchase', 'receiverZipcode', 'receiverBasicAddr')
		});
	} else {
		// 주소에 설정된 이벤트 삭제
		$("[name=receiverBasicAddr]").off();
		$("[name=receiverCity]").attr("disabled", false);
		$("[name=receiverBasicAddr]").attr("readOnly", false);
	}
}

// 구매시의 한마디 
$("[name=sentence]").keyup(function () {
    // 남은 글자 수를 구합니다.
    var inputLength = getCharCount($(this).val());    
    var remain = 200 - inputLength;

    $('#pSentenceCount').html(inputLength);
    if (remain >= 0) {
        $('#pSentenceCount').css('color', 'black');
    } else {
        $('#pSentenceCount').css('color', 'red');
    }
});

$("[name=sentence]").blur(function () {
	var enter = getEnterCount($("[name=sentence]"));
	if (enter > 5) {
		alert("줄바꿈은 5회까지 가능합니다. ");
	}
});

//결재화면
/**
 * 차후 구현 예정
 * noPostCard의 값이 있는 경우 발송없이 결재
 */
function payment() {
	// 구매입력 항목 체크
	if (!validPurchase()) { return false; }
	
    purchaseStatus = "";
    boxStatus = "payment";
    // console.log(1);
    $(".purchase_container").hide();
    // console.log(2);
    $(".payment_container").show();
    // console.log(3);
    initPayment();
    // console.log(4);
    setBox();
}

function validPurchase() {
	if ($("[name=sentence]").val().trim().length == 0) {
		alert("한마디를 입력하세요");
		$("[name=sentence]").focus();
		return false;
	}
	
	if (getCharCount($("[name=sentence]").val()) > 200) {
		alert("한마디는 200자까지 가능합니다.");
		$("[name=sentence]").focus();
		return false;
	}
	
	var enter = getEnterCount($("[name=sentence]"));
	if (enter > 5) {
		alert("줄바꿈은 5회까지 가능합니다.");
		$("[name=sentence]").focus();
		return false;
	}
	
	if ($("[name=senderName]").val().trim().length == 0) {
		alert("보내는 사람을 입력하세요");
		$("[name=senderName]").focus();
		return false;
	}
	if ($("[name=receiverName]").val().trim().length == 0) {
		alert("보내는 사람을 입력하세요");
		$("[name=receiverName]").focus();
		return false;
	}
	if ($("[name=receiverBasicAddr]").val().trim().length == 0) {
		alert("주소를 입력하세요");
		$("[name=receiverBasicAddr]").focus();
		return false;
	}
	if ($("[name=location]").val() != 1) {
		if ($("[name=receiverCity]").val().trim().length == 0) {
			alert("도시명을 입력하세요");
			$("[name=receiverCity]").focus();
			return false;
		}
	}
	if ($("[name=receiverZipcode]").val().trim().length == 0) {
		alert("우편번호를 입력하세요");
		$("[name=receiverZipcode]").focus();
		return false;
	}
	
	if (!chkNum($("[name=receiverZipcode]").val())) {
		alert("우편번호는 숫자만 가능합니다.");
		$("[name=receiverZipcode]").focus();
		return false;
	}
	var change = false;
	if (purchaseController.basicAddr != $("[name=receiverBasicAddr]").val().trim()) {
		change = true;
	}
	if (purchaseController.detailAddr != $("[name=receiverDetailAddr]").val().trim()) {
		change = true;
	}
	if (change) {
		if (confirm("그림 구매시 변경된 주소로 기존 주소를 바꾸시겠습니까?")) {
			purchaseController.changeAddr = "Y";
		}
	}
	return true;
}

function Payment(){
    this.title      = $("<div>").addClass("payment_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("payment_contents").addClass("popup_contents");
    this.bottom     = $("<div>").addClass("payment_bottom").addClass("popup_bottom");
    this.sociconFacebook    =$("<span>").addClass("social_btn").addClass("socicon-facebook");
    this.sociconTwitter     =$("<span>").addClass("social_btn").addClass("socicon-twitter");
    this.sociconInstagram   =$("<span>").addClass("social_btn").addClass("socicon-instagram");
    this.sociconPinterest   =$("<span>").addClass("social_btn").addClass("socicon-pinterest");
}

Payment.prototype = {
    setTitle    : function(title){
        $(this.title).html(title);
    },
    setContents : function(contents){
        $(this.contents).html(contents);
    },
    setBottom   : function(bottom){
        $(this.bottom).html(bottom);
    },
    buildPayment : function(){
        $(".payment_box").append(this.title);
        $(".payment_box").append(this.contents);
        $(".payment_box").append(this.bottom);
    }
}

function initPayment(){
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Payment");
    payment.setContents("<span class='reward_money'>$2</span><br>추가적인 배송료나 포장비가 없습니다.<br>어디든 $2면 충분합니다.<br><br><br>구매한 $2의 일부는 작가에게 후원금으로 지급됩니다. <br>아름다운 그림을 나눠준 작가에게 큰 힘이 되어주세요.<br><br>아래 구매버튼을 눌러 결재를 계속하세요.");
    payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><i class='material-icons'>edit</i><div class='purchase_btn_text'>edit address</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><i class='material-icons'>payment</i></div>");
    payment.buildPayment();
    $(".payment_btn").click(function(){
        purchaseController.addPurchase();
    })
    delete payment;
}

function PurchaseController(paintingId) {
	this.paintingId = paintingId;
	this.basicAddr;
	this.detailAddr;
	this.changeAddr = 'N';
}

PurchaseController.prototype = {
	purchasePopInfo: function () {
		var controller = this;
		AjaxCall.call(apiUrl + "/purchasePopInfo", 
			"", 
			"GET", 
			function (result) {
				controller.purchasePopInfoRes(result);			
			}
		);		
	},
	purchasePopInfoRes: function (result) {
		console.log("성공함");
		initPurchasePop(result);
	},
	addPurchase: function () {
		// console.log("addPurchase");
		var controller = this;
		
		// userId는 로그인 후 쿠키에서 가져와서 처리하도록 해야함
		var data = {
			userId: userID,
			paintingId: this.paintingId,
			sentence: $("[name=sentence]").val(),
			privateAt: ($("[name=privateAt]").prop("checked")) ? "Y" : "N",
			receiverBasicAddr: $("[name=receiverBasicAddr]").val(),
			receiverDetailAddr: $("[name=receiverDetailAddr]").val(),
			receiverZipcode: $("[name=receiverZipcode]").val(),
			receiverCity: $("[name=receiverCity]").val(),
			receiverName: $("[name=receiverName]").val(),
			senderName: $("[name=senderName]").val(),
			location: $("[name=location]").val(),
			purchaseStatus: "C",
			changeAddr: controller.changeAddr,
		};

		AjaxCall.call(apiUrl + "/purchase", 
			data, 
			"POST", 
			function (result) {
				// console.log("ajax 호출 결과...");
				controller.addPurchaseRes(result);			
			}
		);
	},
	addPurchaseRes: function (result) {
		// console.log("addPurchaseRes---");
		// 기존 입력 내용 지우기
		resetPurchase();
		completePayment();
	}
};

// 구매 입력 내용 지우기
function resetPurchase() {
	// console.log("resetPurchase -- ");
	// console.log("sentence -- " + $("[name=sentence]").val(""));
	$("[name=privateAt]").prop("checked", false),
	$("[name=sentence]").val("");
	$("[name=receiverBasicAddr]").val("");
	$("[name=receiverDetailAddr]").val("");
	$("[name=receiverZipcode]").val("");
	$("[name=receiverCity]").val("");
	$("[name=receiverName]").val("")
	$("[name=senderName]").val("");
	$("[name=location]").val("");
	$('#pSentenceCount').html(0);
}

function completePayment(){
	// console.log("sentence : " + $("[name=sentence]").val());
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Thanks!");
    payment.setContents("곧 엽서가 발송됩니다.<br>하지만, 기다리세요, 조금 더 시간이 걸립니다.<br>구매한 엽서는 우편을 통해 배송됩니다. 우편은 충분히 빠르지 않습니다.<br>기다린 만큼 더 큰 기쁨이 될 수 있습니다.<br><br><br><b>Post한 그림을 친구들과 함께 하세요.</b><br><br>");
    payment.contents.append(payment.sociconFacebook.css("color", "rgb(80,80,80)"));
    payment.contents.append(payment.sociconTwitter.css("color", "rgb(80,80,80)"));
    payment.contents.append(payment.sociconInstagram.css("color", "rgb(80,80,80)"));
    payment.contents.append(payment.sociconPinterest.css("color", "rgb(80,80,80)"));
    payment.setBottom("<div class='popup_btn payment_btn'><div class='purchase_btn_text'>Go to my history </div><i class='material-icons'>person</i></div>");
    payment.buildPayment();
    $(".payment_btn").click(function(){
        $(".popup_container").hide();
        selectMenu(3);
        mySwiper.slideTo(1);
    })
    delete payment;
}	