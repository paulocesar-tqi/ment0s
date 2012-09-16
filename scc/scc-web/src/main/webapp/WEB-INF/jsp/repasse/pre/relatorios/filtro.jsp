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
	$('#tabs').tabs();
	$("#anoRelatorio").mask("9999");
	
	
	
	$('#executar_button').click(function(){
		$('#executar_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	});	
		
});




</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pre_relatorios.filtro.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="repasse_pre_relatorios.operadoraLD"/>:</td>
    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />    
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pre_relatorios.operadoraClaro"/>:</td>
    <td ><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" />    
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pre_relatorios.statusRepasse"/>:</td>
    <td ><form:select path="cdStatusFechamento" id="cdStatusFechamento" items="${status}" itemLabel="label" itemValue="key" />    
</tr>

<tr>    
    <td width="10%"><spring:message code="repasse_pre_relatorios.produto"/>:</td>
    <td>
    <form:select path="cdProdutoPrepago" id="cdProdutoPrepago" items="${produtos}" itemLabel="noProdutoPrepago" itemValue="cdProdutoPrepago" />
 	<form:errors path="cdProdutoPrepago"/> </td>       
</tr>

<tr>    
    <td width="10%"><spring:message code="repasse_pre_relatorios.tipoRelatorios"/>:</td>
    <td ><form:select path="cdTipoRelatorio" id="cdTipoRelatorio" items="${tipos}" itemLabel="label" itemValue="key" /></td>    
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pre_relatorios.mes"/>:</td>
    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>    
</tr>

<tr>
	<td width="10%"><spring:message code="repasse_pre_relatorios.ano"/>:</td>
    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
    <form:errors path="anoRelatorio" /></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="executar_button" type="button" value=<spring:message code="comum.botao.executar"/> />    
    </td>
</tr>
</table>
</div>
</form:form>
</div>
<script>
$(function() {
	$('#executar_button').removeAttr('disabled');
});
</script>
