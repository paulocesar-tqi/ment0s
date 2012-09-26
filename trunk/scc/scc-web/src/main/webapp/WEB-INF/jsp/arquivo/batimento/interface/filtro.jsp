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
	$("#dataInicial").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");
	$( "#dataInicial" ).datepicker();
	$( "#dataFinal" ).datepicker();		
	$('#pesquisar_button').click(pesquisar)
	$('#excel_button').click(excel)
	$("#tipoOperadora").change(trocaTipoOperadora); 
	$('#tabs').tabs();
});

function pesquisar() {
	$('#excel_button').attr('disabled', 'disabled');
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function excel() {
	$('#excel_button').attr('disabled', 'disabled');
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");	
	$('#form1').submit();
}

function trocaTipoOperadora()
{
var sel = $("#tipoOperadora option:selected");
if (sel.val() == "OP")
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
</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivo/batimento/interface/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Tipo Operadora:</td>
<td><form:select path="tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
<td width="15%">Operadora Claro:</td>
<td><form:select path="cdEOTClaro" id="comboOperadora" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
</tr>

<tr>
<td width="15%">Operadora Externa:</td>
<td><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
</tr>

<tr>
<td width="15%">Tipo de Arquivo:</td>
<td><form:select path="tipoArquivo" id="tipoArquivo" items="${tiposArquivo}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
    <td width="10%">Data Início:</td>
    <td><form:input id="dataInicial" path="dataInicial" />
    <form:errors path="dataInicial" /></td>
</tr>

<tr>
    <td width="10%">Data Final:</td>
    <td><form:input id="dataFinal" path="dataFinal" />
    <form:errors path="dataFinal" />
    </td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="pesquisar_button" type="button" value="Pesquisar" />
    <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
    <input id="excel_button" type="button" value="Excel" />
    </c:if>
    </td>
</tr>
</table>
<br/>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td align="center">                            
<display:table style="width:100%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/arquivo/batimento/interface/tab1.scc" class="ui-state-default">
<display:column property="nomeArquivo" title="Nome do Arquivo" />
<display:column property="operadoraLD" title="Operadora LD" />
<display:column property="operadoraClaro" title="Operadora Claro" />
<display:column property="dataMovimentacao" title="Data de Movimentação" />
<display:column property="dataTransferencia" title="Data de Transferência" />
<display:column property="quantidadeRegistrosMobile" title="Quantidade de Registros" />
<display:column property="dataProcessamento" title="Data de Processamento" />
<display:column property="quantidadeRegistrosScc" title="Quantidade de Registros" />
<display:column property="diferenca" title="Diferença" />
<display:column property="status" title="Status" />
</display:table>
</td></tr>
</table>
</c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">

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