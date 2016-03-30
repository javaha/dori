var baseUploadCount = 5;
var uploadedPaintingId = '';

//업로드화면
function upload(){
	if (userID == "") {
		showLogin();
		return ;
	}

    postedInfo();
}
function postedInfo() {
	AjaxCall.call(apiUrl+"/user/me/postedCountInfo", null, "GET", postedInfoRes);
}
function postedInfoRes(result, status) {
	if(result.errorNo == 0) {
		var postedCount = parseInt(result.postedCount, 10);
		var uploadedCount = parseInt(result.uploadedCount, 10);
		var uploadCount = 1;
		var doUploadCout = 0;

		if(postedCount >= baseUploadCount) {
			uploadCount = Math.floor(postedCount/baseUploadCount) + 1;
		}

		doUploadCout = uploadCount - uploadedCount;

		boxStatus = "upload"
		$(".upload_container").show();

		initUpload(postedCount, uploadCount, doUploadCout);
		setBox();
	}
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
        $(".upload_box").append($('<div>').addClass('painting_preview').css('text-align', 'center'));
        $(".upload_box").append(this.bottom);
    }
}

function checkPainteeFile(file) {
	var reader = new FileReader();
	var image  = new Image();

	reader.readAsDataURL(file);  
	reader.onload = function(_file) {
		image.src    = _file.target.result;
		image.onload = function() {
			var width = this.width;
			var height = this.height;
			if(width < 1080 || height < 1500) {
				alert($.i18n.t('uploadPop.failContent'));
				resetUpload();
			} else {
				$('.painting_preview').append('<img src="'+ this.src +'" width="120px" height="150px"/>');
			}
		};

		image.onerror= function() {
			console.log('Invalid file type: '+ file.type);
		};
	};
}

function createPainting(form) {
	AjaxCall.callMultipart(apiUrl+"/painting", form, createPaintingRes);
}
function createPaintingRes(result, status) {
	console.log(result);
	if(result.errorNo == 0) {
		alert("등록 되었습니다.");
	} else {
		alert('error');
	}
}

function resetUpload() {
	$('.uploadFileBox').empty();
	$('.uploadFileBox').html("<form id='paintingCreateForm' name='paintingCreateForm' method='POST' enctype='multipart/form-data'><label for='painteeFile' class='upload_btn_text'>Select image file </label><i id='do-upload' class='material-icons'>folder</i><input type='file' id='painteeFile' name='painteeFile' title='' class='upload-input-hidden' /></form>");

	$("#do-upload").on('click', function(){
		if($('#painteeFile').val() == '') {
			alert('업로드 파일을 선택하세요.');
			return;
		}

		var form = new FormData($('#paintingCreateForm').get(0));

		createPainting(form);
	})
	$('#painteeFile').on('change', function(e) {
		if(this.files[0]) {
			checkPainteeFile(this.files[0]);
		}
	});
}
function initUpload(postedCount, uploadCount, doUploadCout){
    $(".upload_box").empty();
    var upload = new Upload();
    upload.setTitle("Upload Painting");
//    upload.setContents("당신의 그림이 Post될 때 마다,<br>추가로 업로드할 수 있는 그림의 수가 늘어납니다.<br>지금까지 253회 Post된 당신은 최대 50개의 그림을 올릴 수 있고<br> 지금 <span class='reward_money'>7</span>개 의 그림을 더 올릴 수 있습니다.<br><br><br>업로드를 위해서는<br>가로 사이즈 <b>1080px</b> 세로 사이즈 <b>1440px</b><br>이상의 이미지가 필요합니다.");
    upload.setContents("<span data-i18n='[html]uploadPop.content1'></span>"+postedCount+"<span data-i18n='[html]uploadPop.content2'></span>"+uploadCount+"<span data-i18n='[html]uploadPop.content3'></span><span class='reward_money'>"+doUploadCout+"</span><span data-i18n='[html]uploadPop.content4'></span>");
    upload.setBottom("<div class='popup_btn upload_btn uploadFileBox'></div>");
    upload.buildUpload();

    if(doUploadCout > 0) {
        resetUpload();
    }

    delete upload;

    // 다국어 처리
    exeTranslation('.base_position', lang);
}

/* 사용해야 하는경우가 어느 경우인지?
 * function failUpload(){
    $(".upload_box").empty();
    var uploadFail = new Upload();
    uploadFail.setTitle("Upload Painting");
    uploadFail.setContents('<span data-i18n="[html]uploadPop.failContent"></span>');
    uploadFail.setBottom("<div class='popup_btn upload_btn uploadFileBox'><label for='painteeFile' class='upload_btn_text'>Select image file </label><i class='material-icons'>folder</i><input type='file' id='painteeFile' name='painteeFile' title='' class='upload-input-hidden' /></div>");
    uploadFail.buildUpload();
    $(".upload_btn").click(function(){
        successUpload();
    })
    delete uploadFail;
    // 다국어 처리
    exeTranslation('.base_position', lang);
}*/

function successUpload(){
    $(".upload_box").empty();
    var uploadSuccess = new Upload();
    uploadSuccess.setTitle("Upload Painting");
    uploadSuccess.setContents('<span data-i18n="[html]uploadPop.successContent"></span><br><div class="upload_sentence"><span class="character_counter">0/200</span><textarea class="upload_sentence_textarea" length="200"></textarea><input type="checkbox"> private</div>');
    uploadSuccess.setBottom("<div class='popup_cancle_btn upload_btn uploadFileBox'><i class='material-icons'>folder</i><label for='painteeFile' class='upload_btn_text'>Select image file </label><input type='file' id='painteeFile' name='painteeFile' title='' class='upload-input-hidden' /></div><div class='popup_btn upload_btn'><div class='purchase_btn_text'>Done </div><i class='material-icons'>done</i></div>");
    uploadSuccess.buildUpload();
    delete uploadSuccess;
    // 다국어 처리
    exeTranslation('.base_position', lang);
}
