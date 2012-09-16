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
	$(document).ready(function(){	
		$("#dataInicialPeriodo").mask("99/99/9999");	
		$("#dataFinalPeriodo").mask("99/99/9999");
		$( "#dataFinalPeriodo" ).datepicker();
		$( "#dataInicialPeriodo" ).datepicker();		
		$("#tipoOperadora").change(trocaTipoOperadora);
		$('#pesquisar_button').click(pesquisar);
		$('#excel_button').click(excel);
		$('#tabs').tabs();
	});
});

function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function excel() {
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");	
	$('#form1').submit();
}

function trocaTipoOperadora() {
	var sel = $("#tipoOperadora option:selected");
	if (sel.val() == "O")
		mostraOperadoraClaro();
	else
		mostraHoldingClaro();
}

function mostraHoldingClaro() {
	$.ajax({   
		 url: "/scc/user/recepcao_transmissao/holding/json.scc",  
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('comboOperadora');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}}); 
}

function mostraOperadoraClaro() {
	$.ajax({   
		 url: "/scc/user/recepcao_transmissao/operadoras/json.scc",  
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('comboOperadora');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}}); 
}
</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.titulo.filtro"/></a></li>
</ul>
<div id="tabs-1">
<form:form id="form1" modelAttribute="filtro" method="post" action="execute.scc">
<form:hidden path="operacao"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
    <td width="10%">Tipo Operadora:</td>
    <td ><form:select path="pesquisa.tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.statusArquivo"/>:</td>
    <td ><form:select path="pesquisa.statusArquivo" items="${statusArquivo}" itemLabel="dsStatusArquivo" itemValue="cdStatusArquivo" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.erroProtocolo"/>:</td>
    <td ><form:select path="pesquisa.cdErroProtocolo" items="${errosProtocolo}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.operadoraClaro"/>:</td>    
    <td ><form:select id="comboOperadora" path="pesquisa.operadoraClaro"  items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.operadoraExterna"/>:</td>
    <td ><form:select path="pesquisa.operadoraExterna" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
</tr>

</br>
<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.tipoArquivo"/>:</td>
    <td ><form:select path="pesquisa.cdTipoBatimento" items="${tiposArquivo}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.periodo"/>:</td>
    <td ><form:select path="pesquisa.tipoPeriodo" items="${tiposPeriodo}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.dataInicio"/>:</td>
    <td><form:input id="dataInicialPeriodo" path="pesquisa.dataInicialPeriodo" />
    <form:errors path="pesquisa.dataInicialPeriodo" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.dataFinal"/>:</td>
    <td><form:input id="dataFinalPeriodo" path="pesquisa.dataFinalPeriodo" />
    <form:errors path="pesquisa.dataFinalPeriodo" />
    </td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input type="button" id="pesquisar_button" value=<spring:message code="comum.botao.pesquisar"/> />
    <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
    <input type="button" id="excel_button"  value=<spring:message code="comum.botao.excel"/> />
    </c:if>
    </td>
</tr>
</table>
</br>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr><td align="center">
<display:table style="width:100%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/recepcao_transmissao/tab1.scc" class="ui-state-default">
<display:column property="dataConnect" title="Dt Connect" />
<display:column property="dataReferencia" title="Dt Referência" />
<display:column property="dataProcClaro" title="Dt Proc Claro" />
<display:column property="dataProcPPC" title="Dt Proc PPC" />
<display:column property="noComposto" title="Arquivo" />
<display:column property="minutosTarifados" title="Minutos Tarifados" />
<display:column property="qtCDR" title="CDRs" />
<display:column property="valorLiquido" title="Vlr Liquido" />
<display:column property="valorBruto" title="Vlr Bruto" />
<display:column property="status" title="Status Arquivo" />
<display:column property="statusProtocolo" title="Status Protocolo" />
</display:table>
</td></tr>
</table>
</c:if>

</form:form>
</div>
</div>
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
