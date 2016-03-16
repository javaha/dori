// 업로드화면
function upload(){
    boxStatus = "upload"
    $(".upload_container").show();
    initUpload();
    setBox();
}

function Upload(){
    this.title      = $("<div>").addClass("upload_title").addClass("popup_title");
    this.contents   = $("<div>").addClass("upload_contents").addClass("popup_contents");
    this.bottom     = $("<div>").addClass("upload_bottom").addClass("popup_bottom");
}

Upload.prototype = {
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
        $(".upload_box").append(this.title);
        $(".upload_box").append(this.contents);
        $(".upload_box").append(this.bottom);
    }
}

function initUpload(){
    $(".upload_box").empty();
    var upload = new Upload();
    upload.setTitle("Upload Painting");
    upload.setContents("당신의 그림이 Post될 때 마다,<br>추가로 업로드할 수 있는 그림의 수가 늘어납니다.<br>지금까지 253회 Post된 당신은 최대 50개의 그림을 올릴 수 있고<br> 지금 <span class='reward_money'>7</span>개 의 그림을 더 올릴 수 있습니다.<br><br><br>업로드를 위해서는<br>가로 사이즈 <b>1080px</b> 세로 사이즈 <b>1440px</b><br>이상의 이미지가 필요합니다.");
    upload.setBottom("<div class='popup_btn upload_btn'><div class='purchase_btn_text'>Select image file </div><i class='material-icons'>folder</i></div>");
    upload.buildUpload();
    $(".upload_btn").click(function(){
        failUpload();
    })
    delete upload;
}

function failUpload(){
    $(".upload_box").empty();
    var uploadFail = new Upload();
    uploadFail.setTitle("Upload Painting");
    uploadFail.setContents('고화질의 출력을 위해 더 큰 이미지가 필요합니다.<br><b>가로 사이즈 1080px 세로 사이즈 1440px</b><br>이상의 이미지가 필요합니다.');
    uploadFail.setBottom("<div class='popup_btn upload_btn'><div class='purchase_btn_text'>Select image file </div><i class='material-icons'>folder</i></div>");
    uploadFail.buildUpload();
    $(".upload_btn").click(function(){
        successUpload();
    })
    delete uploadFail;
}

function successUpload(){
    $(".upload_box").empty();
    var uploadSuccess = new Upload();
    uploadSuccess.setTitle("Upload Painting");
    uploadSuccess.setContents('성공적으로 그림이 등록되었습니다.<br>당신의 생각, 당신의 느낌을 그림과 함께 적어주세요.<br><div class="upload_sentence"><span class="character_counter">0/200</span><textarea class="upload_sentence_textarea" length="200"></textarea><input type="checkbox"> private</div>');
    uploadSuccess.setBottom("<div class='popup_cancle_btn upload_btn'><i class='material-icons'>folder</i><div class='purchase_btn_text'>Select again</div></div><div class='popup_btn upload_btn'><div class='purchase_btn_text'>Done </div><i class='material-icons'>done</i></div>");
    uploadSuccess.buildUpload();
    delete uploadSuccess;
}