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
	$('#tabs').tabs();
});




	

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
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivo/retorno/eventos/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
<td width="15%">Operadora LD:</td>
<td> <form:select path="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot"></form:select> </td>
</tr>

<tr>
<td width="15%">Operadora Claro:</td>
<td> <form:select path="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot"></form:select> </td>
</tr>

<tr>
<td width="15%">Evento:</td>
<td> <form:select path="cdStatusCdr" items="${eventos}" itemLabel="dsStatusCdr" itemValue="cdStatusCdr"></form:select> </td>
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
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/arquivo/retorno/eventos/tab1.scc" class="ui-state-default">
<display:column property="operadoraClaro" title="Operadora Claro"/>
<display:column property="operadoraExterna" title="Operadora Externa"/>
<display:column property="dataEvento" title="Data Evento"/>
<display:column property="dataReferencia" title="Data Referência"/>
<display:column property="quantidadeChamadas" title="Qtq. Chamadas"/>
<display:column property="durcaoTarifada" title="Duração Tarigada"/>
<display:column property="valorLiquido" title="Vlr. Líquido"/>
<display:column property="valorBruto" title="Vlr. Bruto"/>
<display:column property="evento" title="Evento"/>
<display:column property="info" title="Informação Adcional"/>
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
