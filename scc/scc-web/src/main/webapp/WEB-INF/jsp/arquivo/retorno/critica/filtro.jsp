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
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivo/retorno/critica/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >


<tr>
<td width="15%">Operadora Claro:</td>
<td> <form:select id="comboOperadora" path="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
</tr>



<tr>
<td width="15%">Operadora LD:</td>
<td> <form:select path="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
</tr>

<tr>
<td width="15%">Motivo Rejeição (Crítica):</td>
<td> <form:select path="cdMotivoRejeicao" items="${criticas}" itemLabel="label" itemValue="key"></form:select> </td>
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
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="30"  id="repasses" requestURI="/scc/user/arquivo/retorno/critica/tab1.scc" class="ui-state-default">

<display:column property="evento" title="Evt."/>
<display:column property="destinoChamada" title="Reg."/>
<display:column property="naturezaCobranca" title="Nat."/>
<display:column property="operadoraLD" title="Long"/>
<display:column property="operadoraClaro" title="Claro"/>
<display:column property="numeroA" title="Assinante A"/>
<display:column property="paisDestino" title="País"/>
<display:column property="numeroB" title="Assinante B"/>
<display:column property="dataChamada" title="Dt. Chamada"/>
<display:column property="horaChamada" title="Hr. Chamada"/>
<display:column property="duracao" title="Dur. Tar."/>
<display:column property="valorLiquido" title="Vl. Líq."/>
<display:column property="localidadeOrigem" title="CnlOrigem"/>
<display:column property="CSP" title="CSP"/>
<display:column property="destinoChamada" title="CnlDest"/>
<display:column property="duracaoReal" title="Dur. Real"/>
<display:column property="grupoHorario" title="Grp. H."/>
<display:column property="degrau" title="Degrau"/>
<display:column property="areaRoaming" title="CN"/>
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
