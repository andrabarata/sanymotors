<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<body>	
	<script type="text/javascript">
	</script>
	<div class="adminContent row-fluid">
		<c:set var="title">Adaugare anunt</c:set>
		<c:if test="${not empty postData.elementId}">
			<c:set var="title">Modificare anunt</c:set>
		</c:if>
		<span class="title">${title}</span>	
		<div class="clearfix"></div>
		<button class="btn btn-success" style="float:right;margin-bottom:1%" onclick="goOn('postForm','/administration/additImg',event)">Mai departe >></button>
		<button class="btn" style="float:right;margin-right:1%" onclick="goBack('posts')">Anuleaza</button>
		<div class="clearfix"></div>
		<form id="postForm" method="post">
			<div id="content">
				<div class="image-content span6 capsule">
					<fieldset>
						<label>Imaginea principala</label>
						<div id="imageBoxing">
							<c:choose>
								<c:when test="${not empty postData.image}">
									<img class="prodImg" name="pig" id="mainImg" src="${postData.image}" style="height:300px!important; width:500px!important"/>
								</c:when>
								<c:otherwise>
									<img class="prodImg" name="pig" id="mainImg" src="../resources/img/no-image-blog-one.png" style="height:300px!important; width:500px!important"/>
								</c:otherwise>
							</c:choose>
						</div>
						<input type="hidden" name="id" value="${postData.elementId}">
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
				<div class="text-content span6 capsule">
					<fieldset>
						<label>Titlu:</label>
						<input type="text" value="${postData.title}" name="title" id="titlu" class="validate-field"><br/>
						<label>Categorie:</label>
						<c:set var="selected" value=""/>
						<select name="category">
							<c:forEach items="${postData.categories}" var="category">
								<c:if test="${category.key==postData.category}">
									<c:set var="selected" value="selected"/>
								</c:if>
								<option value="${category.key}" ${selected}>${category.value}</option>
								<c:set var="selected" value=""/>
							</c:forEach>
						</select>
						<c:set var="selected" value=""/>
						<label>Pret:</label>
						<input type="text" name="price" value="${postData.price}">
						<select name="currency">
							<c:forTokens items="LEI,USD,EUR" delims="," var="currency">
								<c:if test="${currency==postData.currency}">
									<c:set var="selected" value="selected"/>
								</c:if>	
								<option value="${currency}" ${selected}>${currency}</option>
								<c:set var="selected" value=""/>
							</c:forTokens>
						</select>
						<label>Descriere:</label>
						<textarea rows="10" cols="50" name="description" id="descriere">${postData.description}</textarea>
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