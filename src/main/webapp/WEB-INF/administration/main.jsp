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
		<c:set var="pos">${(page-1)*propertiesData.pageItems+1}</c:set>
		<span class="title">Anunturi</span>
		<button class="btn btn-success" onclick="addPost()" style="float:right;margin-bottom:1%">Adauga anunt</button>
		<div id="content" class="span12">
			<c:if test="${not empty announces}">
				<table class="bordered span11">
					<tr>
						<th>Nr crt</th>
						<th>Imagine principala</th>
						<th>Titlu anunt</th>
						<th></th>
					</tr>
					<c:forEach items="${announces}" var="announce" varStatus="id">
						<tr>
							<td>${id.index+pos}</td>
							<td><img class="mainImg" src="data:application/octet-stream;base64,${announce.image}"/></td>
							<td>${announce.title}</td>
							<td><input type="radio" value="${announce.index}"></td>
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
		                 				<c:if test="${userSelection.productPage==pageCount}">
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
	                    <c:if test="${propertiesData.lastPage-userSelection.productPage>2 and propertiesData.lastPage>5}">
			                    	<li class="paginationItem dotPaged">...</li>
						</c:if>
	                      <c:if test="${page!=propertiesData.lastPage && propertiesData.lastPage!=0}">
			                    	<li class="paginationItem" id="pg${propertiesData.lastPage}" onclick="displayPage('${propertiesData.lastPage}')">»</li>
						</c:if>
	                 </ul>
            	</div>
			</c:if>
		</div>
	</div>
</body>
</html>