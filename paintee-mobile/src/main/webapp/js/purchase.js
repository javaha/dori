// 구매화면으로 이동
function purchase(){
    $(".purchase_container").show();
    $(".purchase_pay_btn").click(function(){payment()});
    purchaseStatus = "sentence";
    setWidth();
}

function setPurchase(){
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
    $(".purchase_container").hide();
    purchaseStatus = "";
    boxStatus = "";
});

$(".purchase_box").click(function(e){
    e.stopPropagation();
});

	
	
	
	
	