var initX=0;
var initY=0;
var mouseX = -1;
var mouseY = -1;
var cutQuery = "";
var width = 0;
var height = 0;
var scale = 0;
var imageId = "";
function doAjaxRequest(uriValue, dataValue,functionValue){
	var rootValue = $("#host").val();
	var urlValue = rootValue + uriValue;
	$.ajax({
		type : "POST",
		url : urlValue,
		data : dataValue,
		async : true,
		success :function(response){
			functionValue(response);
		},
		error : function(xhr, status, error) {
			alert(error);
		}
	});
}
function doAdminAjaxRequest(pageName, dataValue){
	var functionValue = function(response){
		var $data = $($.parseHTML(response));
		var $templateBody = $($data.find('.adminContent'));
		$('.adminContent').html($templateBody.html());
	};
	doAjaxRequest(pageName, dataValue,functionValue);
}
$(document).ready(function(){
	initDraw(document.getElementById('imageContent'));
});
function displayPage(page){
	var uriValue = "/administration/changePage";
	var data = "page="+page;
	doAdminAjaxRequest(uriValue,data);
}
function addPost(){
	var rootValue = $("#host").val();
	var uriValue = "/administration/post";
	var urlValue = rootValue + uriValue;
	window.location.href = urlValue;
	
}
function readURL(fileId,imageId) {
	 var input = $("#"+fileId)[0];
   if (input.files && input.files[0]) {
       var reader = new FileReader();
       reader.onload = function(e) {
    	   if ($(".addit-img").length>0)
    		   $(".emptyImage").find('#'+imageId).attr('src', e.target.result);
    	   else 
    		   $('#'+imageId).attr('src', e.target.result);
           $("#"+imageId+"b64").val(e.target.result);
       };

       reader.readAsDataURL(input.files[0]);
   }
   if (!$("#cut"+imageId).hasClass("btn-success")){
	   $("#cut"+imageId).addClass("btn-success");
	   $("#cut"+imageId).removeClass("disabled");
   }
   $(".addit-img-content").find("#cut"+imageId).removeClass("disabled");
   $(".addit-img-content").find("#cut"+imageId).addClass("btn-success");
}
function showImageBox(event, imageIdParam){
	imageId = imageIdParam;
	if (!event)
		event = window.event;
	(event.preventDefault) ? event.preventDefault() : event.returnValue = false;
	var imgId = "#"+imageId;
	if (!$("#cut"+imageId).hasClass("disabled")){
		var imageSrc = $(imgId).attr("src");
		imageSrc = "url('"+imageSrc+"')";
		$("#imageContent").width($(imgId).width());
		$("#imageContent").height($(imgId).height());
		if ($(imgId).width()>560){
			$("#pictureBox").width($(imgId).width()+40);
			var width = $(imgId).width()*100 / $(window).width()+1;
			var leftProcent = 21+(100-width)/2+"%";
			$("#pictureBox").css("left",leftProcent);
		}
		if ($(imgId).height()>380){
			$("#pictureBox").height($(imgId).height()+100);
			$("#pictureBox").find(".modal-body").css("max-height",($(imgId).height()+100)+"px");
		}
		$("#imageContent").css("background-image",imageSrc);
		$("#imageContent").css("background-repeat","no-repeat");
		$("#imageContent").css("background-size","100% 100%");
		$("#imageContent").find("div").remove();
		$("#pictureBox").modal();
	}
}

function initDraw(canvas) {
    function setMousePosition(e) {
        var ev = e || window.event; //Moz || IE
        if (ev.pageX) { //Moz
            mouse.x = ev.pageX;
            mouse.y = ev.pageY;
        } else if (ev.clientX) { //IE
            mouse.x = ev.clientX;
            mouse.y = ev.clientY;
        }
    };

    var mouse = {
        x: 0,
        y: 0,
        startX: 0,
        startY: 0
    };
    var element = null;

    $("#imageContent").mousemove(function (e) {
        setMousePosition();
        if (element !== null) {
            element.style.width = Math.abs(mouse.x - mouse.startX) + 'px';
            element.style.height = Math.abs(mouse.y - mouse.startY) + 'px';
            element.style.left = (mouse.x - mouse.startX < 0) ? mouse.x -$(this).offset().left + 'px' : mouse.startX-$(this).offset().left + 'px';
            element.style.top = (mouse.y - mouse.startY < 0) ? mouse.y-$(this).offset().top + 'px' : mouse.startY-$(this).offset().top + 'px';
        }
    });

    $("#imageContent").click(function (e) {
        if (element !== null) {
           
            canvas.style.cursor = "default";
            mouseX = element.style.left;
            mouseY = element.style.top;
            width = element.style.width;
            height = element.style.height;
            element = null;
        } else {
        	$(this).find("div").remove();
            console.log("begun.");
            mouse.startX = mouse.x;
            mouse.startY = mouse.y;
            element = document.createElement('div');
            element.className = 'rectangle';
            element.style.left = mouse.x + 'px';
            element.style.top = mouse.y + 'px';
            canvas.appendChild(element);
            canvas.style.cursor = "crosshair";
        }
    });
}
function cancelBox(modalName){
	$("#"+modalName).modal('hide');
}
function goBack(pageName){
	if (typeof pageName == "undefined")
			pageName = "";
	var urlValue = window.location.href;
	urlValue = urlValue.substring(0,urlValue.lastIndexOf("/"));
	window.location.href = urlValue;
}
function goAjaxBack(pageName){
	doAdminAjaxRequest(pageName, "");
}
function updateImage(){
	var imgId = "#"+imageId;
	var base64Content = $(imgId).attr("src");
	base64Content = base64Content.substring(base64Content.indexOf(",")+1,base64Content.length);
	base64Content = base64Content.replace(/\+/g, "%2B");
	 var cutQuery = "base64Content="+base64Content;
	if (scale!=0)
		cutQuery+="&scale="+scale;
	if (width!=0 && height!=0){
		var additParams ="&x="+mouseX+"&y="+mouseY+"&width="+width+"&height="+height;
		additParams = additParams.replace(/px/g,"");
		cutQuery+=additParams;
	}
	var extension = "";
	var contentId = imageId+"Content";
	var path = document.getElementById(contentId).value;
	if (path.length==0){
		var src = $(imgId).attr("src");
		extension = src.substring(src.indexOf("/")+1,src.indexOf(";"));
	} else {
		extension = path.substring(path.lastIndexOf(".")+1,path.length);
	}
	cutQuery+="&extension="+extension;
	var functionValue = function(response){
		var src = $(imgId).attr("src");
		var srcExtension = src.substring(0,src.indexOf(";"));
		if (response.indexOf("data:image")!=-1)
			response = response.substring(response.indexOf(",")+1,response.length);
		$(imgId).attr("src",srcExtension+";base64,"+response);
		$("#"+imageId+"b64").val(srcExtension+";base64,"+response);
		cutQuery = "";
		scale = 0;
	};
	doAjaxRequest("/administration/updateImage", cutQuery, functionValue);
}
function addPixels(){
	scale+=10;
	$("#imageContent").width($("#imageContent").width()+10);
	$("#imageContent").height($("#imageContent").height()+10);
	if ($("#imageContent").width()>560){
		$("#pictureBox").width($("#imageContent").width()+40);
		var width = $("#mainImg").width()*100 / $(window).width()+1;
		var leftProcent = 21+(100-width)/2+"%";
		$("#pictureBox").css("left",leftProcent);
	}
	if ($("#imageContent").height()>360){
		$("#pictureBox").height($("#imageContent").height()+100);
		$("#pictureBox").find(".modal-body").css("max-height",($("#imageContent").height()+100)+"px");
	}
	
}
function removePixels(){
	scale-=10;
	$("#imageContent").width($("#imageContent").width()-10);
	$("#imageContent").height($("#imageContent").height()-10);
	if ($("#imageContent").width()>560){
		$("#pictureBox").width($("#imageContent").width()+40);
		var width = $("#imageContent").width()*100 / $(window).width()+1;
		var leftProcent = 21+(100-width)/2+"%";
		$("#pictureBox").css("left",leftProcent);
	}
	if ($("#imageContent").height()>360){
		$("#pictureBox").height($("#imageContent").height()+100);
		$("#pictureBox").find(".modal-body").css("max-height",($("#imageContent").height()+100)+"px");
	}
}
function goOn(formId,pageName,event){
	if (!event)
		event = window.event;
	(event.preventDefault) ? event.preventDefault() : event.returnValue = false;
	var errorMsg = "";
	$(".validate-field").each(function(){
		var value = $(this).val();
		value = value.replace(/ /g, "");
		if (value.length==0){
			var field = $(this).attr("id");
			errorMsg+="<span>Introduceti date corecte in campul "+field+"!</span><br/>";
		}
	});
	if (errorMsg.length==0){
		var formData = $("#"+formId).serialize();
		doAdminAjaxRequest(pageName, formData);
	}
	else{
		$("#errorMsg").html("");
		$("#errorMsg").append(errorMsg);
		$("#errorBox").modal();
	}
}
function addAdditImg(){
	$(".addit-img-content").css("float","left");
	var value = parseInt($("#position").val())+1;
	var data =$(".addit-img-content").html();
	var data1 = data.substring(0,data.indexOf("img")-1);
	var data2 = data.substring(data.indexOf("img")-1,data.length);
	data2 = data2.substring(data2.indexOf(">")+1,data2.length);
	var img = "<img class=\"addit-img\" id=\"additImg"+value+"\" src=\"../resources/img/no-img.jpg\"/>";
	var field = "<div class=\"span12 addit-img-content\">"+data1+img+data2+"</div>";
	field = field.replace(/1/g,value+"");
	$(".addit-img").append(field);
	$(".addit-img-content").find("#cutadditImg"+value).removeClass("btn-success");
	$(".addit-img-content").find("#cutadditImg"+value).addClass("disabled");
	$(".addit-img-content").css("margin-left","1%");
	$(".addit-img-content").css("margin-top","1%");
	$(".addit-img-content").css("padding-bottom","1%");
	$("#position").val(value);
}
function selectImg(event,imageId){
	if (!event)
		event = window.event;
	(event.preventDefault) ? event.preventDefault() : event.returnValue = false;
	$("#"+imageId).click();
}