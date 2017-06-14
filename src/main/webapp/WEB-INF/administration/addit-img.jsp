<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<div class="adminContent">
		<span class="title">Adaugare imagini aditionale</span>
		<div class="clearfix"></div>
		<button class="btn btn-success" style="float:left;" onclick="addAdditImg()">Adauga inca o imagine</button>
		<button class="btn btn-success" style="float:right;" onclick="goOn('additImgForm','/administration/props',event)">Mai departe >></button>
		<button class="btn" style="float:right;margin-right:1%" onclick="goBack('posts')">Anuleaza</button>
		<button class="btn btn-success" style="float:right;margin-right:1%" onclick="goAjaxBack('/administration/post')">Inapoi <<</button>
		<div class="clearfix"></div>
		<input type="hidden" id="position" value="${fn:length(postData.additionalImages)+1}"/>
		<div id="content">
			<form id="additImgForm">
				<div class="addit-images">
					<c:choose>
						<c:when test="${empty postData.additionalImages}">
							<div class="span12 addit-img-content" >
								<fieldset>
									<legend>Imagine aditionala</legend>
									<div class="emptyImage">
										<img class="addit-img" id="additImg1" src="../resources/img/no-image-blog-one.png"/>
									</div>
									<input type="file" class="hidden-file" id="additImg1Content" onchange="readURL('additImg1Content','additImg1')" /><br/>
									<input type="hidden" name="base64Content" id="additImg1b64"/>
									<button class="btn btn-success" onclick="selectImg(event,'additImg1Content')">Alege imaginea</button>
									<button id="cutadditImg1" class="btn disabled" onclick="showImageBox(event,'additImg1')">Modifica imaginea</button>
									<button class="btn btn-success" onclick="deleteImg(event,'additImg1')">Sterge imaginea</button>
								</fieldset>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${postData.additionalImages}" var="additImg" varStatus="id">
								<div class="span12 addit-img-content" >
									<fieldset>
										<legend>Imagine aditionala</legend>
										<div class="emptyImage">
											<img class="addit-img" id="additImg${id.index+1}" src="${additImg}"/>
										</div>
										<input type="file" class="hidden-file" id="additImg${id.index+1}Content" onchange="readURL('additImg${id.index+1}Content','additImg${id.index+1}')" /><br/>
										<c:set var="disabled" value="disabled"/>
										<input type="hidden" name="base64Content" id="additImg${id.index+1}b64" value="${additImg}"/>
										<button class="btn btn-success" onclick="selectImg(event,'additImg${id.index+1}Content')">Alege imaginea</button>
										<button id="cutadditImg${id.index+1}" class="btn btn-success" onclick="showImageBox(event,'additImg${id.index+1}')">Modifica imaginea</button>
										<button id="remadditImg${id.index+1}" class="btn btn-success" onclick="deleteImg(event,'additImg${id.index+1}')">Sterge imaginea</button>
									</fieldset>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</form>	
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
    	<jsp:include page="error.jsp"/> 
	</div>
