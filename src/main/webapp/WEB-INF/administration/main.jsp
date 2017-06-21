<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<body>
	<div class="adminContent row-fluid">
		<c:set var="pos">${(page-1)*propertiesData.adminPageItems+1}</c:set>
		<span class="title">Anunturi</span>
		<c:if test="${not empty msg}">
			<div class="clearfix"></div>
			<span class="success-msg">${msg}</span>
		</c:if>
		<button class="btn btn-success" onclick="changeHref('/administration/post')" style="float:right;margin-bottom:1%">Adauga anunt</button>
		<div id="content" class="span12">
			<c:choose>
				<c:when test="${not empty announces}">
					<table class="bordered span11" style="width:99%">
						<tr>
							<th>Nr crt</th>
							<th>Titlu anunt</th>
							<th>Categorie</th>
							<th>Imagine principala</th>
							<th>Data crearii</th>
							<th></th>
						</tr>
						<c:forEach items="${announces}" var="announce" varStatus="id">
							<tr>
								<td>${id.index+pos}</td>
								<td>${announce.title}</td>
								<td>${announce.categoryName}</td>
								<td><img class="mainImg" src="${announce.image}" style="height:150px!important;width:250px!important"/></td>
								<td>${announce.dateCreated}</td>
								<td><input type="radio" value="${announce.index}" onclick="showPopUp('elementBox')"></td>
							</tr>
						</c:forEach>
					</table>
					<div class="clearfix"></div>
					<div class="pagination pagination-centered">
		                <ul>
		                	<c:if test="${page!=1 }">
		                		<li class="paginationItem" id="pg1" onclick="displayPage('1')">«</li>
		                	</c:if>
		                 	<c:if test="${page>4 and propertiesData.lastPage>5}">
		                    	<li class="dotPaged">...</li>
		                    </c:if>
		                    <c:choose>
		                    	<c:when test="${page<5}">
		                    		<c:choose>
		                    			<c:when test="${propertiesData.lastPage>5}">
		                    				<c:forEach begin="1" end="5" var="pageCount">
			                 					<c:set var="contPage" value=""/>
			                 					<c:if test="${page==pageCount}">
			                 						<c:set var="contPage" value="active"/>
			                 					</c:if>
			                 					 <li class="paginationItem ${contPage}"  id="pg${pageCount}" onclick="displayPage('${pageCount}')">${pageCount}</li>
			                 				</c:forEach>
		                    			</c:when>
		                    			<c:otherwise>
		                    				<c:forEach var="pageCount" begin="1" end="${propertiesData.lastPage}">
		                    					<c:set var="contPage" value=""/>
			                 					<c:if test="${page==pageCount}">
			                 						<c:set var="contPage" value="active"/>
			                 					</c:if>
			                 					 <li class="paginationItem ${contPage}"  id="pg${pageCount}" onclick="displayPage('${pageCount}')">${pageCount}</li>
		                    				</c:forEach>
		                    			</c:otherwise>
		                    		</c:choose>
		                    	</c:when>
		                    	<c:otherwise>
		                    	<c:choose>
		                    		<c:when test="${propertiesData.lastPage-page>2}">
		                    			<c:forEach var="pageCount" begin="${page-2}" end="${page+2}">
			                 				<c:set var="contPage" value=""/>
			                 				<c:if test="${page==pageCount}">
			                 					<c:set var="contPage" value="active"/>
			                 				</c:if>
			                 					<li class="paginationItem ${contPage}"  id="pg${pageCount}" onclick="displayPage('${pageCount}')">${pageCount}</li>
			                 			</c:forEach>
		                    		</c:when>
		                    		<c:otherwise>
		                    			<c:forEach var="pageCount" begin="${page-2}" end="${propertiesData.lastPage}">
		                    				<c:set var="contPage" value=""/>
			                 				<c:if test="${page==pageCount}">
			                 					<c:set var="contPage" value="active"/>
			                 				</c:if>
		                    					 <li class="paginationItem" id="pg${pageCount}" onclick="displayPage('${pageCount}')">${pageCount}</li>
		                    				</c:forEach>
		                    		</c:otherwise>
		                    	</c:choose>
		                    	</c:otherwise>
		                    </c:choose>
		                    <c:if test="${propertiesData.lastPage-page>2 and propertiesData.lastPage>5}">
				                    	<li class="paginationItem dotPaged">...</li>
							</c:if>
		                      <c:if test="${page!=propertiesData.lastPage && propertiesData.lastPage!=0}">
				                    	<li class="paginationItem" id="pg${propertiesData.lastPage}" onclick="displayPage('${propertiesData.lastPage}')">»</li>
							</c:if>
		                 </ul>
	            	</div>
				</c:when>
				<c:otherwise>
					Nu exista date in baza de date.
				</c:otherwise>
			</c:choose>
		</div>
		<div id="elementBox" class="alert-error modal hide fade" tabindex="-1" role="dialog" aria-labelledby="warningLabel" aria-hidden="true">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		        <h3 id="warningLabel">Avertisment</h3>
		    </div>
		
		    <div class="modal-body">
		    	<span>Selectati ce doriti sa faceti cu anuntul curent.</span>
		    </div>
		
		    <div class="modal-footer">
		    	<button id="modal_adapt_btn" class="btn" data-dismiss="modal" aria-hidden="true" onclick="cancelBox('elementBox')" style="float:left">Anuleaza</button>
		    	<button id="modal_adapt_btn" class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="modifyPost()">Modifica anuntul</button>
		        <button id="modal_adapt_btn" class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="showPopUp('cancelBox')">Sterge anuntul</button>
		    </div>
    	</div>
    	<div id="cancelBox" class="alert-error modal hide fade" tabindex="-1" role="dialog" aria-labelledby="warningLabel" aria-hidden="true">
		    <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
		        <h3 id="warningLabel">Avertisment</h3>
		    </div>
		
		    <div class="modal-body">
		    	<span>Sigur vreti sa stergeti anuntul?</span>
		    </div>
		
		    <div class="modal-footer">
		    	<button id="modal_adapt_btn" class="btn" data-dismiss="modal" aria-hidden="true" onclick="cancelBox('elementBox')" style="float:left">Anuleaza</button>
		        <button id="modal_adapt_btn" class="btn btn-success" data-dismiss="modal" aria-hidden="true" onclick="deletePost()">Sterge</button>
		    </div>
    	</div>
	</div>
</body>
</html>