<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<c:if test="${not empty error}">
	<div class="client-error">${error}</div>
	
</c:if>