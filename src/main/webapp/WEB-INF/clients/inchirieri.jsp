<title>Inchirieri motociclete</title>
<meta name="description" content="Sany Motors e o firma care s-a nascut din pasiunea pentru motociclete. Inchiriem diferite motociclete la un pret rezonabil">
<meta name="keywords" content="motociclete, inchirieri motociclete">
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<div class="client-content">
	<div class="posts">
		<div class="clearfix" style="margin-bottom:2%"></div>
		<c:forEach items="${posts}" var="post" begin="0" end="2">
			<div class="post-container span3" onclick="showInchiriere('${post.elementId}')">
				<div class="container-title">
					<span>${post.title}</span>
				</div>
				<div class="clearfix"></div>
				<div class="container-img">
					<img alt="Inchireri" src='${post.mainImage.base64Content}'>
				</div>
				<div class="clearfix"></div>
				<div class="container-text">
					<span>${post.price}</span>
					<div class="clearfix"></div>
					<c:forEach items="${post.attributes}" var="additDetails">
						<c:if test="${additDetails.interfaceValue}">
							<span>${additDetails.name}: ${additDetails.value}</span>
							<div class="clearfix"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="clearfix" style="margin-bottom:2%"></div>
		<div>
		<c:forEach items="${posts}" var="post" begin="3" end="5">
			<div class="post-container span3" onclick="showPiesa('${post.elementId}')">
				<div class="container-title">
					<span>${post.title}</span>
				</div>
				<div class="clearfix"></div>
				<div class="container-img">
					<img alt="Piese motociclete" src='${post.mainImage.base64Content}'>
				</div>
				<div class="clearfix"></div>
				<div class="container-text">
					<span>${post.price}</span>
					<div class="clearfix"></div>
					<c:forEach items="${post.attributes}" var="additDetails">
						<c:if test="${additDetails.interfaceValue}">
							<span>${additDetails.name}: ${additDetails.value}</span>
							<div class="clearfix"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="clearfix" style="margin-bottom:2%"></div>
		<div>
		<c:forEach items="${posts}" var="post" begin="6" end="8">
			<div class="post-container span3" onclick="showPiesa('${post.elementId}')">
				<div class="container-title">
					<span>${post.title}</span>
				</div>
				<div class="clearfix"></div>
				<div class="container-img">
					<img alt="Piese motociclete" src='${post.mainImage.base64Content}'>
				</div>
				<div class="clearfix"></div>
				<div class="container-text">
					<span>${post.price}</span>
					<div class="clearfix"></div>
					<c:forEach items="${post.attributes}" var="additDetails">
						<c:if test="${additDetails.interfaceValue}">
							<span>${additDetails.name}: ${additDetails.value}</span>
							<div class="clearfix"></div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:forEach>
	</div>
		<div class="clearfix" style="margin-bottom:2%"></div>
					<div class="pagination pagination-centered">
		                <ul>
		                	<c:if test="${page!=1 }">
		                		<li class="paginationItem" id="pg1" onclick="displayPage('1','inchirieri')">�</li>
		                	</c:if>
		                 	<c:if test="${page>4 and propertiesData.lastRentPage>5}">
		                    	<li class="dotPaged">...</li>
		                    </c:if>
		                    <c:choose>
		                    	<c:when test="${page<5}">
		                    		<c:choose>
		                    			<c:when test="${propertiesData.lastRentPage>5}">
		                    				<c:forEach begin="1" end="5" var="pageCount">
			                 					<c:set var="contPage" value=""/>
			                 					<c:if test="${page==pageCount}">
			                 						<c:set var="contPage" value="active"/>
			                 					</c:if>
			                 					 <li class="paginationItem ${contPage}"  id="pg${pageCount}" onclick="displayPage('${pageCount}','inchirieri')">${pageCount}</li>
			                 				</c:forEach>
		                    			</c:when>
		                    			<c:otherwise>
		                    				<c:forEach var="pageCount" begin="1" end="${propertiesData.lastRentPage}">
		                    					<c:set var="contPage" value=""/>
			                 					<c:if test="${page==pageCount}">
			                 						<c:set var="contPage" value="active"/>
			                 					</c:if>
			                 					 <li class="paginationItem ${contPage}"  id="pg${pageCount}" onclick="displayPage('${pageCount}','inchirieri')">${pageCount}</li>
		                    				</c:forEach>
		                    			</c:otherwise>
		                    		</c:choose>
		                    	</c:when>
		                    	<c:otherwise>
		                    	<c:choose>
		                    		<c:when test="${propertiesData.lastRentPage-page>2}">
		                    			<c:forEach var="pageCount" begin="${page-2}" end="${page+2}">
			                 				<c:set var="contPage" value=""/>
			                 				<c:if test="${page==pageCount}">
			                 					<c:set var="contPage" value="active"/>
			                 				</c:if>
			                 					<li class="paginationItem ${contPage}"  id="pg${pageCount}" onclick="displayPage('${pageCount}','inchirieri')">${pageCount}</li>
			                 			</c:forEach>+
		                    		</c:when>
		                    		<c:otherwise>
		                    			<c:forEach var="pageCount" begin="${page-2}" end="${propertiesData.lastRentPage}">
		                    				<c:set var="contPage" value=""/>
			                 				<c:if test="${page==pageCount}">
			                 					<c:set var="contPage" value="active"/>
			                 				</c:if>
		                    					 <li class="paginationItem" id="pg${pageCount}" onclick="displayPage('${pageCount}','inchirieri')">${pageCount}</li>
		                    				</c:forEach>
		                    		</c:otherwise>
		                    	</c:choose>
		                    	</c:otherwise>
		                    </c:choose>
		                    <c:if test="${propertiesData.lastRentPage-page>2 and propertiesData.lastRentPage>5}">
				                    	<li class="paginationItem dotPaged">...</li>
							</c:if>
		                      <c:if test="${page!=propertiesData.lastRentPage && propertiesData.lastRentPage!=0}">
				                    	<li class="paginationItem" id="pg${propertiesData.lastRentPage}" onclick="displayPage('${propertiesData.lastRentPage}','inchirieri')">�</li>
							</c:if>
		                 </ul>
	            	</div>
	
</div>