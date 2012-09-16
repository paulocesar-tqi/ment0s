<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<script>
$(document).ready(function(){
	
	$('#pcValor').mask("999.99");
	
	$('#tabs').tabs();
	
	$('#voltar_button').click(function(){
		$('#operacao').val("VOLTAR");
		$("#form1").attr("action", "/scc/user/repasse/pos/consulta/back.scc"); 
		$('#form1').submit();
	});	
	
	$('#ajustar_button').click(function(){
		$('#operacao').val("OPERACAO_AJUSTAR");		
		$('#form1').submit();
	});	
	
	
});

function ajustar(cdItem,dsItem)
{
$('#operacao').val("OPERACAO_AJUSTAR");
$('#cdItemRepasse').val(cdItem);	
$('#dialog').dialog('open');
}

function remover(cdItem)
{
$('#operacao').val("OPERACAO_REMOVER");
$('#cdItemRepasse').val(cdItem);
$('#form1').submit();
}
</script>
<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pos_ajuste.titulo"/></a></li>
</ul>
<div id="tabs-1">
<form:form modelAttribute="ajuste" method="post" action="/scc/user/repasse/pos/ajuste/execute.scc" id="form1">

<form:hidden path="operacao" id="operacao"/>
<form:hidden path="modificador" id="modificador"/>
<form:hidden path="nuRepasse" id="nuRepasse"/>

<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr><td>
<h3><c:out value="${ajuste.titulo}"></c:out></h3>
</br>
<table style="width: 90%; border: 1px;"  class="ui-state-default">
<tr>
<th align="left"><spring:message code="repasse_pos_ajuste.coluna.item"/></th>
<th align="left"><spring:message code="repasse_pos_ajuste.coluna.valorLiquido"/></th>
<th align="left"><spring:message code="repasse_pos_ajuste.coluna.valorBruto"/></th>
<th align="left"><spring:message code="repasse_pos_ajuste.coluna.operacao"/></th>
<th align="left"><spring:message code="repasse_pos_ajuste.coluna.apagar"/></th>
<c:forEach var="item" items="${_DISPLAY_TAG_SPACE_1}" varStatus="rowCounter">
<c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td><c:out value="${item.itemRepasse}"/></td>
		 <td>${item.valorLiquido}</td>
		 <td>${item.valorBruto}</td>
		 <td align="center">${item.operacao}</td>
		 <td align="center">${item.apagar}</td>		 		 
		 </tr>
</c:forEach>
</tr>
</table>
</td></tr>
</table> 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="voltar_button" type="button" value=<spring:message code="comum.botao.voltar"/> />    
    </td>
</tr>
</table>
<table>
<tr>
<td><spring:message code="repasse_pos_ajuste.coluna.item"/></td>
<td><form:select path="cdItemRepasse" id="cdItemRepasse" items="${editaveis}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
<td><spring:message code="repasse_pos_ajuste.obs"/></td>
<td align="left"><form:input path="observacao" id="observacao" size="150"/> </td>
</tr>

<tr>
<td><spring:message code="repasse_pos_ajuste.valor"/></td>
<td align="left"><form:select path="creditoDebito" id="creditoDebito" items="${opcoes}" itemLabel="label" itemValue="key" />
<input  id="pcValor" type="text" name="valor" name="valor" /></td></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="ajustar_button" type="button" value=<spring:message code="comum.botao.ajustar"/> />    
    </td>
</tr>
</table>
</form:form>
</div>
</div>



