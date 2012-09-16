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
	$("#dataInicial").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");
	$( "#dataFinal" ).datepicker();
	$( "#dataInicial" ).datepicker();
	
	$('#pesquisar_button').click(function(){
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	});
	
	$('#excel_button').click(function(){
		$('#operacao').val("excel");
		$('#form1').submit();
	});
	
	$('#detalhesDialog').dialog({
		autoOpen: false,
		width: 720,
		height : 550,
		buttons: {
			"Fechar": function() { 
				$(this).dialog("close"); 
			}
		}
	});
	
});


function selecionaDetalhe(seqArquivo,seqCredito)
{
$('#operacao').val("detalhes");
$('#seqArquivo').val("seqArquivo");
$('#seqCredito').val("seqCredito");
$('#form1').submit();
}

</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pre_consulta.filtro.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="seqCredito" id="seqCredito"/>
<form:hidden path="seqArquivo" id="seqArquivo"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="creditos_pre_pesquisa.operadoraClaro"/>:</td>
    <td ><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" />    
</tr>
<tr>
    <td width="10%"><spring:message code="creditos_pre_pesquisa.operadoraLD"/>:</td>
    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />    
</tr>

<tr>
    <td width="10%"><spring:message code="creditos_pre_pesquisa.tipoCredito"/>:</td>
    <td ><form:select path="cdTipoCredito" id="cdTipoCredito" items="${tiposCredito}" itemLabel="label" itemValue="key" />    
</tr>

<tr>
    <td width="10%"><spring:message code="creditos_pre_pesquisa.statusCredito"/>:</td>
    <td ><form:select path="cdStatusCredito" id="cdStatusCredito" items="${statusCredito}" itemLabel="label" itemValue="key" />    
</tr>

<tr>
    <td width="10%"><spring:message code="creditos_pre_pesquisa.dtInicial"/>:</td>
    <td><form:input id="dataInicial" path="dataInicial" />
    <form:errors path="dataInicial" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="creditos_pre_pesquisa.dtFinal"/>:</td>
    <td><form:input id="dataFinal" path="dataFinal" />
    <form:errors path="dataFinal" />
    </td>
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
</br>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table  name="sessionScope._DISPLAY_TAG_SPACE_1"  decorator="com.claro.sccweb.decorator.RelCreditosPrePagoDecorator" pagesize="50"  id="repasses" partialList="true" size="sessionScope._DISPLAY_TAG_SPACE_1.fullListSize" requestURI="/scc/user/repasse/pre/creditos/pagina" class="ui-state-default">
<display:column property="nomeArquivo" title="Nome do Aquivo" />
<display:column property="operadoraLD" title="EOT LD" />
<display:column property="operadoraClaro" title="EOT Claro" />
<display:column property="tipoCredito" title="Tipo de Crédito" />
<display:column property="statusCredito" title="Status Crédito" />
<display:column property="telefone" title="Telefone" />
<display:column property="dataCerdito" title="Dt. do Crédito" />
<display:column property="valorCredito" title="Vlr. Crédito" />
<display:column property="qtChamadasAgrupadas" title="Qt. Chamadas Agrupadas" />
<display:column property="minutosQueimados" title="Min. Queimados" />
<display:column property="minutosAjustados" title="Min. Ajustados" />
</display:table>
</td></tr>
</table>
</c:if>
</div>
</form:form>
</div>

<div id="detalhesDialog" title="Demonstrativo">
<spring:message code="creditos_pre.detalhes"/>
<table style="width: 100%; border: 1px;"  class="ui-state-default">
<tr><td>
<display:table  style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_2"  decorator="com.claro.sccweb.decorator.RelDetalheCreditoPrePagoDecorator" pagesize="50"  id="repasses" requestURI="/scc/user/repasse/pre/creditos/tab1.scc" class="ui-state-default">
<display:column property="nomeArquivo" title="Nome do Aquivo" />
<display:column property="numeroDestino" title="Número Destino" />
<display:column property="dataChamada" title="Dt. Chamada" />
<display:column property="horaInicioChamada" title="Hora Início" />
<display:column property="nuCDR" title="CDR" />
<display:column property="dataCredito" title="Dt. Crédito" />
<display:column property="valorCredito" title="Vlr. Crédito" />
<display:column property="duracaoTarifada" title="Dur. Tarifada" />
<display:column property="valorBruto" title="Vlr. Bruto" />
</display:table>
</td></tr>
</table>


<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	
	var op = $('#operacao').val();
	if (op == 'detalhes')
	{
	$('#detalhesDialog').dialog('open');	
	}
	
});
</script>



