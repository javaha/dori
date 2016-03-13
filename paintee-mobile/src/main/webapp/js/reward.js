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
    })
    delete reward;
}

function checkReward(){
	var data = rewardController.result;
	
    $(".reward_box").empty();
    var reward = new Reward();
    reward.setTitle("Reward");

    var bankSelect = "<select name='bank' class='purchase_select' style='width:50%'>"
    $.each(data.banks, function (index, item) {
    	bankSelect += "<option value='" + item.codeValue + "'>" + item.codeName + "</option>";
    });		
    bankSelect += "</select>";
    
    var content = '아래 계좌로 지금 받을 수 있는 <b>$' + data.reward.remainMoney + '</b> 에' 
                + "<br>reward <b>수수료 $5</b>를 제외한<br><br>"
                + "<span class='reward_money'>$" + (data.reward.remainMoney - 5) + "</span> 이 입금됩니다.<br><br><br>" 
                + bankSelect
                + "<br><br>"
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
    
    $(".popup_btn.upload_btn").click(function(){
    	rewardController.addReward();
    })
}

function validReward() {
	if ($("[name=accountName]").val().trim().length == 0) {
		alert("이름을 입력하세요");
		$("[name=accountName]").focus();
		return false;
	}
	if ($("[name=accountNo]").val().trim().length == 0) {
		alert("계좌번호를 입력하세요");
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
			bank: $("[name=bank]").val(),
			earmRequestedMoney: controller.result.reward.remainMoney - 5
		};

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