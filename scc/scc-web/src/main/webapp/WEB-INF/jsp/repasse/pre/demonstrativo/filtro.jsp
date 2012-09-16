<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<style>
	.group { zoom: 1 }
</style>

<script>
$(document).ready(function(){

	$("#accordion").accordion({
		header: "> div > h3"		
	});

	$( "#accordion" ).accordion( "option", "collapsible", true );	
	$('#tabs').tabs();
	
	$("#anoDemonstrativo").mask("9999");	
	$('#cdEOTHolding').change(selecionaOperadora);
	$('#cdEOTLD').change(selecionaOperadora);	
	
	$('#pesquisar_button').click(function(){
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#operacao').val("OPERACAO_PESQUISAR");
		$('#form1').submit();
	});	
	
	$('#excel_button').click(function(){
		$('#operacao').val("OPERACAO_EXCEL");		
		$('#form1').submit();
	});	

	

});

function selecionaOperadora()
{
$('#pesquisar_button').attr('disabled', 'disabled');
$("#cdProdutoPrepago").empty().append('<option selected="selected" value="0">Carregando...</option>');
var eotHolding = $("#cdEOTHolding option:selected");
var eotLD = $("#cdEOTLD option:selected");

$.ajax({   
	 url: "/scc/user/repasse/pre/demonstrativo/json/lista_produtos/"+eotHolding.val()+"/"+eotLD.val()+".scc",	 
	 dataType: "json",   success: function(data) 
	   	{
		 $('#pesquisar_button').removeAttr('disabled');
		var name, select, option;        
	    select = document.getElementById('cdProdutoPrepago');      
	        select.options.length = 0;         
	        for (name in data) 
	           {       
	           if (data.hasOwnProperty(name)) {         
			select.options.add(new Option(data[name], name));  
	            }}}
	            });
}

function selecionarParOperadoras(cdEOT)
{
	$('#operacao').val("OPERACAO_SOLICITAR");
	$('#cdEOTClaro').val(cdEOT);	
	$('#form1').submit();
}


</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pre_demonstrativo.filtro.titulo"/></a></li>
<li><a href="#tabs-2"><spring:message code="repasse_pre_demonstrativo.resultado.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="cdEOTClaro" id="cdEOTClaro"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.claro"/>:</td>
    <td ><form:select path="cdEOTHolding" id="cdEOTHolding" items="${holdingClaro}" itemLabel="dsOperadora" itemValue="cdEot" />
    <form:errors path="cdEOTHolding" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.ld"/>:</td>
    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />
    <form:errors path="cdEOTLD" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pos_demonstrativo.produto"/>:</td>
    <td ><form:select path="cdProdutoPrepago" id="cdProdutoPrepago" items="${produtos}" itemLabel="noProdutoPrepago" itemValue="cdProdutoPrepago" />
    <form:errors path="cdProdutoPrepago" /></td>
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.mes"/>:</td>
    <td ><form:select path="mesDemonstrativo" id="mesDemonstrativo" items="${meses}" itemLabel="label" itemValue="key" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pos_demonstrativo.ano"/>:</td>
    <td ><form:input path="anoDemonstrativo" id="anoDemonstrativo" size="4" maxlength="4"/>
    <form:errors path="anoDemonstrativo" /></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />    
    </td>
</tr>
</table>

<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr><td>
<display:table style="width: 100%;" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="25"  id="par_operadoras" requestURI="/scc/user/repasse/pos/demonstrativo/tab1.scc" class="ui-state-default">
<display:column property="dsOperadoraClaro" title="Operadora Claro" />
<display:column property="dsOperadoraLD" title="Operadora LD" />
</display:table>
</td></tr>
</table>
</c:if>
</div>

<div id="tabs-2">
<div id="accordion">
<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.operadora"/></a></h3>
<div>
<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.descricao"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.chamadas"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.minutos"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.bruto"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.icmsNaoRepassado"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.icmsRepassar"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.pisCofins"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.iss"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.valorLiquido"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.valorRepassar"/></th>
		</tr>	
		<c:forEach var="item" items="${DEMONSTRATIVO_OPERADORA}" varStatus="rowCounter">
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td><c:out value="${item.descricao}"/></td>
		 <td>${item.campoChamadas}</td>
		 <td>${item.campoMinutos}</td>
		 <td>${item.campoValorBruto}</td>
		 <td>${item.campoIcmsNaoRepassado}</td>
		 <td>${item.campoIcmsRepassar}</td>
		 <td>${item.campoPisCofins}</td>
		 <td>${item.campoIss}</td>
		 <td>${item.campoValorLiquido}</td>
		 <td>${item.campoValorRepassar}</td>
		 </tr>
		 </c:forEach>

		
		</table>
</div> <!-- Accordion 1 Operadora --> 
</div>


<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.holding"/></a></h3>
<div>
<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.descricao"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.chamadas"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.minutos"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.bruto"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.icmsNaoRepassado"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.icmsRepassar"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.pisCofins"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.iss"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.valorLiquido"/></th>
		<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.valorRepassar"/></th>
		</tr>	
		<c:forEach var="item" items="${DEMONSTRATIVO_HOLDING}" varStatus="rowCounter">
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td><c:out value="${item.descricao}"/></td>
		 <td>${item.campoChamadas}</td>
		 <td>${item.campoMinutos}</td>
		 <td>${item.campoValorBruto}</td>
		 <td>${item.campoIcmsNaoRepassado}</td>
		 <td>${item.campoIcmsRepassar}</td>
		 <td>${item.campoPisCofins}</td>
		 <td>${item.campoIss}</td>
		 <td>${item.campoValorLiquido}</td>
		 <td>${item.campoValorRepassar}</td>
		 </tr>
		 </c:forEach>

		
		</table>
</div> <!-- Accordion 2 Holding --> 
</div>

<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.assinatura"/></a></h3>
<div>
<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th><spring:message code="repasse_pre_assinaturas_col1"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col2"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col3"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col4"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col5"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col6"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col7"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col8"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col9"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col10"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col11"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col12"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col13"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col14"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col15"/></th>
		<th><spring:message code="repasse_pre_assinaturas_col16"/></th>
		</tr>	
		<c:forEach var="item" items="${DEMONSTRATIVO_ASSINATURAS}" varStatus="rowCounter">		 
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td>${item.operadoraClaroDs}</td>
		<td>${item.qtAssinaturas}</td>
		<td>${item.qtChamadas}</td>
		<td>${item.qtMinutos}</td>
		<td>${item.minutosQueimados}</td>
		<td>${item.minutosExpirados}</td>
		<td>${item.valorBruto}</td>
		<td>${item.valorLiquido}</td>
		<td>${item.pisCofins}</td>
		<td>${item.icmsRepassar}</td>
		<td>${item.icmsNaoRepassado}</td>
		<td>${item.valorRepassar}</td>
		<td>${item.custo}</td>
		<td>${item.icmsDescontarMesAnt}</td>
		<td>${item.icmsRepassarMesAnt}</td>
		<td>${item.repasse}</td>	 
		 </tr>
		 </c:forEach>
		
		</table>
</div>  
</div>

</div>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />    
    </td>
</tr>
</table>
</div>


</form:form>
</div>
<script>
$(function() {
	var op = $('#operacao').val();
	if (op == 'OPERACAO_SOLICITAR'){
		$('#tabs').tabs('select',1);
	}
	
	if (op == 'REFRESH'){
		$('#tabs').tabs('select',1);
		$("#accordion").accordion( "activate" , 3);
	}
	
	$('#pesquisar_button').removeAttr('disabled');
});
</script>