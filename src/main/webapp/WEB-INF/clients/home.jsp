<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<title>Sany Motors</title>
<meta name="description" content="Sany Motors e o firma care s-a nascut din pasiunea pentru motociclete. Oferim o gama variata de motociclete si piese de motociclete. Motocicletele pot fi si inchiriate. Service motociclete de calitate.">
<meta name="keywords" content="motociclete, piese motociclete, vanzari piese motociclete, vanzari motociclete, inchirieri motociclete, service motociclete">
<div>
	<div id="clientDiv">
		<div class="row-fluid" style="margin-top: 4%">
			<c:forEach items="${categories}" var="category" varStatus="id"
				begin="0" end="2">
				<div class="categ span4">
					<img alt="motociclete" src="resources/img/${category.key-1}.jpg"
						title="${category.value}" class="categ-img img-round" onmouseover="playclip('${category.key-1}');" onmouseout="stopSound()" onclick="selectCategory('${category.key}')"/>
				</div>
			</c:forEach>
		</div>
		<div class="row-fluid" style="margin-top: 4%">
			<c:forEach items="${categories}" var="category" varStatus="id"
				begin="3" end="5">
				<div class="categ span4">
					<img alt="motociclete" src="resources/img/${category.key-1}.jpg"
						title="${category.value}" class="categ-img img-round" onmouseover="playclip('${category.key-1}');" onmouseout="stopSound()" onclick="selectCategory('${category.key}')"/>
				</div>
			</c:forEach>
		</div>
		<form method="post" id="categoryId" action="motociclete">
			<input type="hidden" name="category">
		</form>
	</div>
</div>
<audio>
	<source id="soundSource"></source>
</audio>
<div id="sounddiv">
	<bgsound id="sound">
</div>
