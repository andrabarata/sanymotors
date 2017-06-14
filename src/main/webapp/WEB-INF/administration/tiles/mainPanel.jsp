<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<link href="${propertiesData.addressValue}/resources/css/bootstrap.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/bootstrap.min.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/bootstrap-responsive.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/bootstrap-responsive.min.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/style.css" rel="stylesheet" media="all">
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/functions.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Platforma de administrare</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="body" />
			<tiles:insertAttribute name="footer" />
			<input type="hidden" id="host" value="${propertiesData.addressValue}">
		</div>
	</div>
</body>
</html>