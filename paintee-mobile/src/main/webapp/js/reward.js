var rewardController = null;
// 리워드화면
function reward(){
    boxStatus = "reward";
    $(".reward_container").show();
    
    // 리워드 정보 조회
    rewardController = new RewardController()
    rewardController.getRewardInfo();
}

function Reward(){
    this.title      = $("<div>").addClass("reward_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("reward_contents").addClass("popup_contents");
    this.bottom     = $("<div>").addClass("reward_bottom").addClass("popup_bottom");
}

Reward.prototype = {
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
        $(".reward_box").append(this.title);
        $(".reward_box").append(this.contents);
        $(".reward_box").append(this.bottom);
    }
}

function initReward(){
	
	replaceHistory({"call": "rewardPop"});
    addHistory({"call": "rewardStep1"});
    
	var data = rewardController.result;
	console.log(JSON.stringify(data));
    $(".reward_box").empty();
    var reward = new Reward();
    reward.setTitle("Reward");
    reward.setContents(
    		"당신의 그림이 post될 때 마다 reward가 쌓입니다.<br> 지금까지 " + data.reward.sellCount + 
    		"회 post된 당신이 얻은 총 Reward는<br><span class='reward_money'>$" + data.reward.earnTotalMoney 
    		+ " </span>입니다.<br><br><br>지금 Reward를 신청하면 남은 <b>$" + data.reward.remainMoney + 
    		"</span> </b>에<br><b>수수료 $5</b>가 제외된 금액을 받을 수 있습니다.<br>");
    reward.setBottom("<div class='popup_btn reward_btn'><div class='purchase_btn_text'>Get reward now </div><i class='material-icons'>attach_money</i></div>");
    reward.buildUpload();
    $(".reward_btn").click(function(){
        checkReward();
    });
    
    delete reward;
}

function checkReward(){
	var data = rewardController.result;
	console.log("data : " + JSON.stringify(data));
	if (data.reward.requestCount > 0) {
		alert("이미 신청중인 리워드가 있습니다.");
		return;
	}
	
	if (data.reward.remainMoney < 10) {
		alert("리워드는 $10부터 신청가능합니다.");
		return;
	}
	
	// 히스토리 적용
    addHistory({"call": "rewardStep2"});
    
    boxStatus = "rewardStep2";
    
    $(".reward_box").empty();
    var reward = new Reward();
    reward.setTitle("Reward");

    var bankSelect = "<select name='bank' class='purchase_select' style='width:50%'>"
    $.each(data.banks, function (index, item) {
    	bankSelect += "<option value='" + item.codeValue + "'>" + item.codeName + "</option>";
    });		
    bankSelect += "</select>";
    
    var content = '아래 계좌로 지금 받을 수 있는 <b>$' + data.reward.remainMoney + '</b> 에' 
                + "<br>reward <b>수수료 $<span id='commission'>5</span></b>를 제외한<br><br>"
                + "<span class='reward_money'>$" + (data.reward.remainMoney - 5) + "</span> 이 입금됩니다.<br><br><br>" 
                + bankSelect
                + "<br><br>"
                + "<span id='directSpan' style='display: none'>"
                + "<input type='text' name='directInputBank' class='purchase_input' placeholder='name of bank'>"
                + "<br>" 
                + "</span>"
                + "<input type='text' name='accountName' class='purchase_input' placeholder='name of account holder'>"
                + "<br>" 
                + "<input type='text' name='accountNo' class='purchase_input' placeholder='account'>"
                + "<br>계좌명과 계좌번호를 정확하게 입력해주세요."
                + "<br>계좌명이 정확하지 않을 경우, 입금에 장애가 있을 수 있습니다.";
    var bottom = "<div class='popup_btn upload_btn'>"
    	       + "  <div class='purchase_btn_text'>Done </div>"
    	       + "  <i class='material-icons'>done</i>"
    	       + "</div>";
    reward.setContents(content);
    reward.setBottom(bottom);
    reward.buildUpload();
    
    $("[name=bank]").change(function(e){
    	if (this.value == "99") {
    		$("#directSpan").show();
    		$("#commission").text(7);
    		$(".reward_money").text("$" + (data.reward.remainMoney - 7));
    	} else {
    		$("#directSpan").hide();
    		$("[name=directInputBank]").val("");
    		$("#commission").text(5);
    		$(".reward_money").text("$" + (data.reward.remainMoney - 5));
    	}
    });
    
    $(".popup_btn.upload_btn").click(function(){
    	rewardController.addReward();
    });
    
    // 계좌번호 입력박스 키이벤트 등록
    $("[name=accountNo]").keydown(function (event) {
    	limitNumber(event);
    })
    .keyup(function (event) {
    	limitNumber(event);
    });
}

function validReward() {
	if ($("[name=bank]").val() == '99') {
		if ($("[name=directInputBank]").val().trim() == "") {
			alert("은행이름을 입력하세요");
			$("[name=directInputBank]").focus();
			return false;
		}
	}
	
	if ($("[name=accountName]").val().trim() == "") {
		alert("이름을 입력하세요");
		$("[name=accountName]").focus();
		return false;
	}
	if ($("[name=accountNo]").val().trim() == "") {
		alert("계좌번호를 입력하세요");
		$("[name=accountNo]").focus();
		return false;
	}
	
	if (!chkNum($("[name=accountNo]").val())) {
		alert("계좌번호는 숫자만 가능합니다.");
		$("[name=accountNo]").focus();
		return false;
	}
	
	return true;
}

function RewardController() {
}

RewardController.prototype = {
	getRewardInfo : function () {
		var controller = this;
		AjaxCall.call(
			apiUrl + "/reward/info", 
			null,
			"GET", 
			function(result) {
				controller.getRewardInfoRes(result);
			}
		);
	}, 	
	getRewardInfoRes : function (result) {
		rewardController.result = result;
		console.log("RewardController.getRewardInfoRes ::: " + JSON.stringify(result));
		initReward();
		setBox();
	},
	addReward: function () {
		if (!validReward()) {
			return;
		}
		
		var controller = this;
		var data = {
			accountName: $("[name=accountName]").val(),
			accountNo: $("[name=accountNo]").val(),
			bank: $("[name=bank]").val()
		};
		
		if ($("[name=bank]").val() == '99') {
			data.directInputBank = $("[name=directInputBank]").val();
			// 은행 직접 입력 시 수수료 $7
			data.earmRequestedMoney = controller.result.reward.remainMoney - 7;
		} else {
			// 은행 직접 입력 시 수수료 $5
			data.earmRequestedMoney = controller.result.reward.remainMoney - 5;
		}
		
		AjaxCall.call(apiUrl + "/reward", 
			data, 
			"POST", 
			function (result) {
				controller.addRewardRes(result);			
			}
		);
	},
	addRewardRes: function (result) {
		alert("처리되었습니다.");
		$(".popup_container").hide();
	}
}