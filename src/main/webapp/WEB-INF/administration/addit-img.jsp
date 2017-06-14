<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<body>
	<div class="adminContent">
		<nav id="nav">
			<div class="navbar navbar-inverse" style="text-align: center;">
				<div class="navbar-inner">
					<button type="button" class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="nav-collapse collapse">
						<ul class="nav-ul">
							<li class="nav-li"><a class="span2 navBook" href="posts">Anunturi</a></li>
							<li class="nav-li"><a class="span2 navBook" href="#">Comentarii</a></li>
						</ul>
					</div>
				</div>
			</div>
		</nav>
		<span class="title">Adaugare imagini aditionale</span>
		<div class="clearfix"></div>
		<button class="btn btn-success" style="float:left;" onclick="addAdditImg()">Adauga inca o imagine</button>
		<button class="btn btn-success" style="float:right;" onclick="goOn('postForm','additImg',event)">Mai departe >></button>
		<button class="btn" style="float:right;margin-right:1%" onclick="goBack('posts')">Anuleaza</button>
		<button class="btn btn-success" style="float:right;margin-right:1%" onclick="goAjaxBack('/administration/post')">Inapoi <<</button>
		<div class="clearfix"></div>
		<input type="hidden" id="position" value="1"/>
		<div id="content">
			<div class="addit-img">
				<div class="span12 addit-img-content" style="float:none; margin:0 auto">
					<fieldset>
						<legend>Imagine aditionala</legend>
						<div class="emptyImage">
							<img class="addit-img" id="additImg1" src="../resources/img/no-img.jpg"/>
						</div>
						<input type="file" class="hidden-file" id="additImg1Content" onchange="readURL('additImgContent1','additImg1')" /><br/>
						<c:set var="disabled" value="disabled"/>
						<c:if test="${not empty postData.additionalImages}">
							<c:set var="disabled" value="btn-success"/>
						</c:if>
						<input type="hidden" name="base64Content" id="additImg1b64" class="validate-field"/>
						<button class="btn btn-success" onclick="selectImg(event,'additImgContent1')">Alege imaginea</button>
						<button id="cutadditImg1" class="btn ${disabled}" onclick="showImageBox(event,'additImg1')">Modifica imaginea</button>
					</fieldset>
				</div>
			</div>
		</div>
		<div id="pictureBox" class="alert-error modal hide fade" tabindex="-1" role="dialog" aria-labelledby="warningLabel" aria-hidden="true">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		        <h3 id="warningLabel">Modificarea imaginii</h3>
		    </div>
		
		    <div class="modal-body">
		  	 	<a class="button add" onclick="addPixels()">Mareste</a>
		    	<a class="button remove" onclick="removePixels()">Micsoreaza</a> 
		    	<div  id="boxImage">
			    	<div class="image-box" id="imageContent">
				    	<canvas id="boxCanvas"></canvas>
			    	</div>
		    	</div>
		    </div>
		
		    <div class="modal-footer">
		    	<button id="modal_adapt_btn" class="btn" data-dismiss="modal" aria-hidden="true" onclick="cancelBox('pictureBox')">Anuleaza</button>
		        <button id="modal_adapt_btn" class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="updateImage()">
		            Continua
		        </button>
		    </div>
    	</div>
	</div>
</body>
</html>