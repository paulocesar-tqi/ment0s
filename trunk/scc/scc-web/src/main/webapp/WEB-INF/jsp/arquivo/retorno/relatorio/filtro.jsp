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
	$('#excel_button').click(excel);	
	$('#pesquisar_button').click(pesquisar);	
	$("#dataInicial").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");	
	$( "#dataFinal" ).datepicker();
	$( "#dataInicial" ).datepicker();
	$("#tipoOperadora").change(trocaTipoOperadora);
	$('#tabs').tabs();
});


function trocaTipoOperadora()
{
var sel = $("#tipoOperadora option:selected");
if (sel.val() == "O")
	mostraOperadoraClaro();
else
	mostraHoldingClaro();
}

function mostraHoldingClaro()
{
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

function mostraOperadoraClaro()
{
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

	

function excel()
{			
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");
	$('#form1').submit();		
}

function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivo/retorno/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >


<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.tipoOperadora"/>:</td>
    <td ><form:select path="tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
</tr>


<tr>
<td width="15%">Operadora Claro:</td>
<td> <form:select id="comboOperadora" path="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
</tr>



<tr>
<td width="15%">Operadora LD:</td>
<td> <form:select path="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
</tr>


<tr>
<td width="15%">Tipo de Arquivo:</td>
<td> <form:select path="cdTipoArquivo" items="${tiposArquivo}" itemLabel="label" itemValue="key"></form:select> </td>
</tr>

<tr>
<td width="15%">Status do Arquivo:</td>
<td> <form:select path="statusArquivo" items="${statusArquivo}" itemLabel="label" itemValue="key"></form:select> </td>
</tr>

<tr>
<td width="15%">Arquivo para Sistema:</td>
<td> <form:select path="sistemaDestino" items="${sistemas}" itemLabel="label" itemValue="key"></form:select> </td>
</tr>

<tr>
<td width="15%">Filtro de Data:</td>
<td> <form:select path="tipoPeriodo" items="${tipoPeriodo}" itemLabel="label" itemValue="key"></form:select> </td>
</tr>

<tr>
<td width="15%">Data Inicial:</td>
<td> <form:input path="dataInicial"/><form:errors path="dataInicial"></form:errors> </td>
</tr>

<tr>
<td width="15%">Data Final:</td>
<td> <form:input path="dataFinal"/><form:errors path="dataFinal"></form:errors> </td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="pesquisar_button" type="button" value="Pesquisar" />
    </td>
</tr>
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="30"  id="repasses" requestURI="/scc/user/arquivo/retorno/tab1.scc" class="ui-state-default">
<display:column property="dataProcClaro" title="Dt Proc Claro"/>
<display:column property="dataReferencia" title="Dt Referencia"/>
<display:column property="noComposto" title="Arquivo"/>
<display:column property="cdCiclo" title="Ciclo"/>
<display:column property="mmCiclo" title="Mês"/>
<display:column property="aaCiclo" title="Ano"/>
<display:column property="qtCDR" title="Qtde"/>
<display:column property="minutosTarifados" title="Minutos"/>
<display:column property="valorLiquido" title="Vlr. Liq."/>
<display:column property="valorBruto" title="Vlr. Brt."/>
<display:column property="status" title="Status"/>
</display:table>
</td></tr>
</table>

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
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
