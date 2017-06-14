<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<title>Informatii motocicleta</title>
<meta name="description" content="Sany Motors e o firma care s-a nascut din pasiunea pentru motociclete. Oferim o gama variata de motociclete spre vanzare la un pret rezonabil.">
<meta name="keywords" content="motociclete, vanzari motociclete">
<div class="client-content">
	<div class="post">
		<ul class="bxslider">
			<li style="text-align:center"><img alt="Vanzari motociclete" class="img-box" src="${post.mainImage.base64Content}" style="margin: 0 auto; max-width:450px"/></li>
			<c:forEach items="${post.additionalImages }" var="additionalImage">
				<li style="text-align:center"><img alt="Vanzari motociclete" class="img-box" src="${additionalImage.base64Content}" style="margin: 0 auto; max-width:450px"/></li>
			</c:forEach>
		</ul>
		<div class="clearfix"></div>
		<c:if test="${post.state==1}">
			<img alt="Vanzari motociclete" src="../resources/img/selled.jpg" style="max-height:160px"/>
			<div class="clearfix"></div>
		</c:if>
		<span class="title">${post.title}</span>
		<div class="clearfix" style="margin-bottom:1%"></div>
		<div class="post-content">
		<table>
			<c:if test="${not empty post.description}">
				<tr>
					<td colspan="2">${post.description}</td>
				</tr>
			</c:if>
			<tr>
				<td style="text-align: center; width:30%">Pret:</td>
				<td style="text-align: center; width:70%">${post.price}</td>
			</tr>		
			<c:forEach items="${post.attributes}" var="attribute">
				<tr>
					<td style="text-align: center; width:30%">${attribute.name}:</td>
					<td style="text-align: center; width:70%">${attribute.value}</td>
				</tr>
			</c:forEach>
		</table>
		</div>
	</div>
</div>