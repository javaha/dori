window.history.pushState("dummy", "", "");
window.addEventListener("popstate", function(e) {
	console.log("popstate:" + e);
	if(e.state){
		console.log("e.state.call :: " + e.state.call);
		if (e.state.call == "detailPop") {
			closeDetail();
		} 
		// 구매 1단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "purchasePop") {
			// 구매 1단계 팝업 닫기
			closePurchaseStep01();
		}
		// 구매 2단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "purchaseStep1" && boxStatus == "payment") {
			purchaseStatus = "sentence";
			boxStatus = "";
			$(".payment_container").hide();
			$(".purchase_container").show();
		}
		// follow 팝업에서 호출된 경우
		else if (e.state.call == "followPop") {
			$(".popup_container").hide();
		}
		// 리워드 1단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "rewardPop") {
			$(".popup_container").hide();
		}
		// 리워드 2단계에서 뒤로가기 버튼을 클릭했을 경우에만 동작
		else if (e.state.call == "rewardStep1") {
			reward();
		}
	}
}, false);

/**
 * 히스토리 추가
 * @param data
 */
function addHistory(data) {
	window.history.pushState(data, "", "");
}

/**
 * 히스토리 가장 상단 정보 변경
 */
function replaceHistory(data) {
	window.history.replaceState(data, "", "");
}