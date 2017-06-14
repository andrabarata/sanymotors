<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<link href="${propertiesData.addressValue}/resources/css/bootstrap.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/bootstrap.min.client.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/bootstrap-responsive.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/bootstrap-responsive.min.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/client.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/jquery.bxslider.css" rel="stylesheet" media="all">
<link href="${propertiesData.addressValue}/resources/css/colorbox.css" rel="stylesheet" media="all">
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/client.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/jquery.bxslider.min.js"></script>
<script type="text/javascript" src="${propertiesData.addressValue}/resources/js/jquery.colorbox-min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-52134097-1', 'sanymotors.ro');
  ga('send', 'pageview');

</script>
</head>
<body>
	<div id="fb-root"></div>
	<script>(function(d, s, id) {
	  var js, fjs = d.getElementsByTagName(s)[0];
	  if (d.getElementById(id)) return;
	  js = d.createElement(s); js.id = id;
	  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
	  fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));</script>
	<div class="row-fluid">
		<div class="span12 main">
			<tiles:insertAttribute name="header" />
			<div class="content">
				<tiles:insertAttribute name="body" />
				<tiles:insertAttribute name="footer" />
			</div>
			<input type="hidden" id="host" value="${propertiesData.addressValue}">
		</div>
	</div>
</body>
</html>