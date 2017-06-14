<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<body>
	<div class="emptyCenter">
		<form method="post" action="../administration/login">
			<fieldset>
				<legend>Autentificare in sistem</legend>
				<label>Introduceti numele de utilizator:</label>
				<input type="text" name="username"><br/>
				<label>Introduceti parola:</label>
				<input type="password" name="password"><br/>
				<input type="submit" class="btn btn-success" value="Logare in sistem"/>
				<c:if test="${state=='error'}"><br/><br/>
					<span class="errorText">Datele de autentficare au fost introduse gresit!</span>
				</c:if>
			</fieldset>
		</form>
	</div>
</body>
</html>