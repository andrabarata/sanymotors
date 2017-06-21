<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<body>
	<div class="adminContent row-fluid">
		<div >
		<button class="btn btn-success" style="float:left;" onclick="addProperty()">Adauga inca o proprietate</button>
		<button class="btn btn-success" style="float:right;" onclick="checkProps(event)">Finalizeaza</button>
		<button class="btn" style="float:right;margin-right:1%" onclick="goBack('posts')">Anuleaza</button>
		<button class="btn btn-success" style="float:right;margin-right:1%" onclick="goAjaxBack('/administration/additImg')">Inapoi <<</button>
		<div class="clearfix"></div>
		<c:choose>
			<c:when test="${empty attributes}">
				<input type="hidden" id="position" value="1"/>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="position" value="${fn:length(attributes)}"/>
			</c:otherwise>
		</c:choose>
			<div id="content">
				<form id="propsForm" method="post" action="posts">
				<input type="hidden" name="save" value="true"/>
					<div class="bordered" style="margin-top:1%;width:17%">
						<c:set var="checked" value=""/>
						<c:forTokens items="1,2,3" delims="," var="status" varStatus="id">
							<c:if test="${postData.state == status}">
								<c:set var="checked" value="checked"/>
							</c:if>
							<input type="radio" name="stats" value="${status}" ${checked } style="float:left;margin-left:2%"><span style="float:left;margin-left:2%">${postData.statusTexts[id.index]}</span><br>
							<c:set var="checked" value=""/>
						</c:forTokens>
					</div>
					<table class="attr-table" border="1">
						<thead>
							<tr>
								<th>Informatie</th>
								<th>Text</th>
								<th></th>
							</tr>
						</thead>
						<c:choose>
							<c:when test="${not empty attributes}">
								<c:forEach items="${attributes}" var="attribute" varStatus="id">
									<tr id="${id.index}">
										<td style="width:45%">
											<span class="attrName">${attribute.name}</span><br/>
											<div class="bordered" style='width: 50%;margin: 0 auto;display: block!important;'>
												<c:set var="checked" value=""/>
												<c:if test="${attribute.interfaceValue}">
													<c:set var="checked" value="checked"/>
												</c:if>
												<input type="checkbox" class="interfaceValue" style="margin-top:-1px" ${checked}/><span style="padding-left: 2px">Doresc sa apara in interfata</span>
												<c:set var="value" value="0"/>
												<c:if test="${attribute.interfaceValue}">
													<c:set var="value" value="1"/>
												</c:if>
												<input type="hidden" id="interface" name="interfaceValue" value="${value}">
											</div>
											<input type="hidden" name="attrName" value="${attribute.name}"/>
											<input type="hidden" name="elemId" value="${attribute.elementId}"/>
										</td>
										<td style="width:45%"><textarea class="attr-textarea" rows="3" cols="30">${attribute.value}</textarea><input type="hidden" name="attrValue" value="${attribute.value}"/></td>
										<td><button class="btn btn-success" onclick="deleteProperty(event,'${id.index}')">Sterge</button></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr id="0" class="extra-prop">
									<td style="width:45%"><button class="btn" onclick="openAttributes(event,'0')">Apasa pentru a vedea toate informatiile</button><input type="hidden" name="attrName"/></td>
									<td style="width:45%"><textarea class="attr-textarea" rows="3" cols="30" readonly></textarea><input type="hidden" name="attrValue"/></td>
									<td><button class="btn btn-success" onclick="deleteProperty(event,'0')">Sterge</button></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</table>
				</form>
			</div>
			<input type="hidden" id="selectedId"/>
				<div id="attrBox" class="alert-error modal hide fade" tabindex="-1" role="dialog" aria-labelledby="warningLabel" aria-hidden="true">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				        <h3 id="warningLabel">Selectati informatia</h3>
				    </div>
					<button class="btn btn-success" style="float:right" onclick="addAttributeName(event)">+</button>
				    <div class="modal-body atribute-names" style="width:94.5%">
				    	<c:forEach items="${postData.availableAttributes}" var="attribute">
				    		<div class="attribute-tab button" onclick="setAttrib('${attribute}')">
				    			<span>${attribute}</span>
				    		</div>
				    	</c:forEach>
				    </div>
				
				    <div class="modal-footer">
				    	<button id="modal_adapt_btn" class="btn" data-dismiss="modal" aria-hidden="true" onclick="cancelBox('elementBox')" style="float:right">Anuleaza</button>
				    </div>
	    		</div>
	    		<div id="attr-modal" class="alert-error modal hide fade" tabindex="-1" role="dialog" aria-labelledby="warningLabel" aria-hidden="true">
				    <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
				        <h3 id="warningLabel">Avertisment!</h3>
				    </div>	
				    <div class="modal-body">
				    	<span id="attr-error"></span>
				    </div>
				
				    <div class="modal-footer">
				    	<button id="modal_adapt_btn" class="btn" data-dismiss="modal" aria-hidden="true" onclick="cancelBox('elementBox')" style="float:left">Anuleaza</button>
				    </div>
	    		</div>
			</div>
		</div>
	</body>
</html>