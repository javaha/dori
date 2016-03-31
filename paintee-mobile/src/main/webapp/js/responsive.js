var responsive;
  
function setResponsive() {
	if ($('div#media-1080').css('display') == 'block') {
		responsive = 1;
	} else if ($('div#media-648').css('display') == 'block') {
		responsive = 2;
	} else if ($('div#media-360').css('display') == 'block') {
		responsive = 3;
	} else {
		responsive = 4;
	}
}

$(window).on('load', function () {
	setResponsive();
});

$(window).on('resize', function () {
	setResponsive();
});