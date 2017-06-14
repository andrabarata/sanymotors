<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
		<c:set var="title">Adaugare anunt</c:set>
		<c:if test="${not empty postData.elementId}">
			<c:set var="title">Modificare anunt</c:set>
		</c:if>
		<span class="title">${title}</span>	
		<div class="clearfix"></div>
		<button class="btn btn-success" style="float:right;" onclick="goOn('postForm','/administration/additImg',event)">Mai departe >></button>
		<button class="btn" style="float:right;margin-right:1%" onclick="goBack('posts')">Anuleaza</button>
		<form id="postForm" action="administration/props" method="post">
			<div id="content">
				<div class="span5">
				<fieldset>
					<label>Titlu:</label>
					<input type="text" value="${postData.title}" name="title" id="titlu" class="validate-field"><br/>
					<label>Descriere:</label>
					<textarea rows="10" cols="50" name="description" id="descriere" class="validate-field">${postData.description}</textarea>
				</fieldset>
				</div>
				<div class="image-content span6">
					<fieldset>
						<label>Imaginea principala</label>
						<c:choose>
							<c:when test="${not empty postData.image}">
								<img id="mainImg" src="${postData.image}"/>
							</c:when>
							<c:otherwise>
								<img id="mainImg" src="../resources/img/no-img.jpg"/>
							</c:otherwise>
						</c:choose>
						<input type="hidden" name="base64Content" id="mainImgb64" class="validate-field" value="${postData.image}"/>
						<input type="file"  class="hidden-file" name="mainImgContent" id="mainImgContent" onchange="readURL('mainImgContent','mainImg')" value="${postData.image}"/>
						<c:set var="disabled" value="disabled"/>
						<div class="clearfix"></div>
						<c:if test="${not empty postData.image }">
							<c:set var="disabled" value="btn-success"/>
						</c:if>
						<button class="btn btn-success" onclick="selectImg(event,'mainImgContent')">Alege imaginea</button>
						<button id="cutmainImg" class="btn ${disabled}" onclick="showImageBox(event,'mainImg')">Modifica imaginea</button>
					</fieldset>
				</div>
			</div>
		</form>
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
    	<jsp:include page="error.jsp"/> 
	</div>
</body>
</html>