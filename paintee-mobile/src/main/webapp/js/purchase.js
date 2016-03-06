// 구매화면으로 이동
function purchase(paintingId){
	console.log("purchase : " + paintingId);
    $(".purchase_container").show();
    $(".purchase_pay_btn" ).click(function(){payment(paintingId)});
    // 좋아요 버튼일 경우 카드 발송하지 않는다 : 현재 미정임
    $(".purchase_like_btn").click(function(){payment(paintingId, true)});
    purchaseStatus = "sentence";
    setWidth();
}

function setPurchase(){
	console.log("setPurchase");
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
	console.log("purchase_next_btn");
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
	console.log("purchase_prev_btn");
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
	console.log("purchase_container");
    $(".purchase_container").hide();
    purchaseStatus = "";
    boxStatus = "";
    // 입력데이터 초기화
    resetPurchase();
});

$(".purchase_box").click(function(e){
	console.log("purchase_box");
    e.stopPropagation();
});

$("[name=location]").change(function(e){
	console.log("change");
	switch ($("[name=location]").val()) {
	case "1":
		setPostUI("KOREA");
		break;
	default:
		setPostUI("NOKOREA");
		break;
	}
	e.stopPropagation();
});

function setPostUI(type) {
	var className = "purchase_input";
	var styleName = "none";
	if (type == 'KOREA') {
		className = "purchase_input2";
		styleName = "inline";
	}
	$("[name=receiverBasicAddr]").attr("class", className);
	$("#postSearch").css("display", styleName);
}

//결재화면
/**
 * noPostCard 일 경우 발송없이 결재
 */
function payment(paintingId, noPostCard){
    purchaseStatus = "";
    boxStatus = "payment";
    $(".purchase_container").hide();
    $(".payment_container").show();
    initPayment(paintingId);
    setBox();
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

function initPayment(paintingId){
    $(".payment_box").empty();
    var payment = new Payment();
    payment.setTitle("Payment");
    payment.setContents("<span class='reward_money'>$2</span><br>추가적인 배송료나 포장비가 없습니다.<br>어디든 $2면 충분합니다.<br><br><br>구매한 $2의 일부는 작가에게 후원금으로 지급됩니다. <br>아름다운 그림을 나눠준 작가에게 큰 힘이 되어주세요.<br><br>아래 구매버튼을 눌러 결재를 계속하세요.");
    payment.setBottom("<div class='popup_cancle_btn payment_cancle_btn'><i class='material-icons'>edit</i><div class='purchase_btn_text'>edit address</div></div><div class='popup_btn payment_btn'><div class='purchase_btn_text'>Payment </div><i class='material-icons'>payment</i></div>");
    payment.buildPayment();
    $(".payment_btn").click(function(){
        new PurchaseController().addPurchase(paintingId);
    })
    delete payment;
}

function PurchaseController() {
}

PurchaseController.prototype = {
	addPurchase: function (paintingId) {
		var controller = this;
		
		// userId는 로그인 후 쿠키에서 가져와서 처리하도록 해야함
		var data = {
			userId: 'aa',
			paintingId: paintingId,
			sentence: sentence,
			privateAt: ($("[name=privateAt]").prop("checked")) ? "Y" : "N",
			receiverBasicAddr: $("[name=receiverBasicAddr]").val(),
			receiverDetailAddr: $("[name=receiverDetailAddr]").val(),
			receiverZipcode: $("[name=receiverZipcode]").val(),
			receiverCity: $("[name=receiverCity]").val(),
			receiverName: $("[name=receiverName]").val(),
			senderName: $("[name=senderName]").val(),
			location: $("[name=location]").val(),
			purchaseStatus: "C"
		};

		AjaxCall.call(apiUrl + "/purchase", 
			data, 
			"POST", 
			function (result) {
				console.log("ajax 호출 결과...");
				controller.addPurchaseRes(result);			
			}
		);
	},
	addPurchaseRes: function (result) {
		console.log("addPurchaseRes---");
		// 기존 입력 내용 지우기
		resetPurchase();
		completePayment();
	}
};

// 구매 입력 내용 지우기
function resetPurchase() {
	console.log("resetPurchase -- ");
	console.log("sentence -- " + $("[name=sentence]").val(""));
	$("[name=privateAt]").prop("checked", false),
	$("[name=sentence]").val("");
	$("[name=receiverBasicAddr]").val("");
	$("[name=receiverDetailAddr]").val("");
	$("[name=receiverZipcode]").val("");
	$("[name=receiverCity]").val("");
	$("[name=receiverName]").val("")
	$("[name=senderName]").val("");
	$("[name=location]").val("");
}

function completePayment(){
	console.log("sentence : " + $("[name=sentence]").val());
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