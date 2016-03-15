// 프로필 수정화면
function showProfile(){
    boxStatus = "profile";
    setBox();
    sideOff();
    $(".profile_container").show();
}

$("[name=profileLocation]").change(function(e){
	// console.log("change");
	switch ($("[name=profileLocation]").val()) {
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
	var className = "account_form";
	var styleName = "none";
	if (type == 'KOREA') {
		className = "account_form2";
		styleName = "inline";
//		$("[name=receiverBasicAddr]").attr("readOnly", "readOnly");
	}
	$("[name=receiverBasicAddr]").attr("class", className);
	$("#profilePostSearch").css("display", styleName);
}