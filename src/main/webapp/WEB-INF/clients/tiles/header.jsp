<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<body>
	<div class="mainHeader"></div>
	<div id="nav" class='cssmenu'>
		<div class="navbar navbar-inverse" style="text-align: center;">
			<div class="navbar-inner">
				<button type="button" class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="nav-collapse collapse">
				<ul class="nav-ul" style="width:100%">
					<li class='active nav-li'><a href='${propertiesData.addressValue}' class='span2' title="Pagina principala motociclete"><span>Pagina principala</span></a></li>
					<li class='nav-li'><a href='${propertiesData.addressValue}/motociclete' class='span2' title="Motociclete"><span>Lista actuala</span></a></li>
					<li class='nav-li'><a href='${propertiesData.addressValue}/piese' class='span2' title="Piese motociclete"><span>Piese de vanzare</span></a></li>
					<li class='nav-li'><a href='${propertiesData.addressValue}/inchirieri' class='span2' title="Inchirieri motociclete"><span>Inchirieri</span></a></li>
					<li class='nav-li'><a href='${propertiesData.addressValue}/service' class='span2' title="Service motociclete"><span>Service</span></a></li>
					<li class='nav-li'><a href='${propertiesData.addressValue}/forum' class='span2' title="Forum motociclete"><span>Forum</span></a></li>
					<li class='last nav-li'><a href='${propertiesData.addressValue}/contact' class='span2' title="Contact"><span>Contact</span></a></li>
				</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>