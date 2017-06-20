var audio;
$(document).ready(function(){
	$(".categ-img").hover(function() {
		$(this).removeClass("img-round");
		$(this).addClass('transition');
    
    }, function() {
    	$(this).addClass("img-round");
		$(this).removeClass("img-fixed");
    	$(this).removeClass('transition');
    });
});
function playclip(id) {
	audio = new Audio("resources/sounds/" + id+".mp3");
	audio.play();
}
function stopSound(){
	audio.pause();
}
function doAjaxRequest(uriValue, dataValue,functionValue,syncType){
	var rootValue = $("#host").val();
	var urlValue = rootValue + uriValue;
	if (typeof syncType == "undefined")
		syncType = true;
	$.ajax({
		type : "POST",
		url : urlValue,
		data : dataValue,
		processData: false,
		async : syncType,
		success :function(response){
			functionValue(response);
		},
		error : function(xhr, status, error) {
			alert(error);
		}
	});
}
function doClientAjaxRequest(pageName, dataValue){
	var functionValue = function(response){
		var $data = $($.parseHTML(response));
		var $templateBody = $($data.find('.client-content'));
		$('.client-content').html($templateBody.html());
	};
	doAjaxRequest(pageName, dataValue,functionValue);	
}
function goOn(formId,pageName,event){
	if (!event)
		event = window.event;
	(event.preventDefault) ? event.preventDefault() : event.returnValue = false;
	var formData = $("#"+formId).serialize();
	doClientAjaxRequest(pageName, formData);
}
function selectCategory(categoryId){
	$("input[name='category']").val(categoryId);
	$("#categoryId").submit();
}
function displayPage(pageNumber){
	var page = $("#host").val()+"/motociclete?pagina="+pageNumber;
	document.location.href = page;
}
function showPost(elementId){
	document.location.href = $("#host").val()+"/motociclete/anunt?p="+btoa(elementId);
}
function showPiesa(elementId){
	document.location.href = $("#host").val()+"/piese/anunt?p="+btoa(elementId);
}
$(document).ready(function(){
	$('.bxslider').bxSlider();
	$(".img-box").click(function(){
		var width = $(window).width();
		$.colorbox({
			html:"<img src='" +$(this).attr("src") + "' max-width='" + width + "px'/>",
			maxWidth:"100%",
			maxHeight:"100%"
		});
	});
	if (document.location.href.indexOf("contact")!=-1)
		google.maps.event.addDomListener(window, 'load', initialize);
});
function initialize() {
	var mapCanvas = document.getElementById('map_canvas');
	var mapOptions = {
	      center: new google.maps.LatLng(46.766383, 23.610211),
	      zoom: 18,
	      mapTypeId: google.maps.MapTypeId.ROADMAP
		};
	var map = new google.maps.Map(mapCanvas, mapOptions);
	 var Marker = new google.maps.Marker({
	      position: new google.maps.LatLng(46.766383, 23.610211),
	      map: map,
	      title: "Sany Motors"
	    });
}