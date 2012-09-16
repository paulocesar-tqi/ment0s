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
	$('#pesquisar_button').click(pesquisar);
	$('#excel_button').click(excel);	
	$("#dataInicialPeriodo").mask("99/99/9999");
	$("#dataFinalPeriodo").mask("99/99/9999");
	$( "#dataInicialPeriodo" ).datepicker();
	$( "#dataFinalPeriodo" ).datepicker();
	$('#tabs').tabs();
});


function pesquisar()
{
	$('#excel_button').attr('disabled', 'disabled');
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function excel()
{
	$('#excel_button').attr('disabled', 'disabled');
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");	
	$('#form1').submit();
}

function selecionar(linha)
{
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("selecionar");
	$('#form1').submit();
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.tipoArquivo"/>:</td>
    <td ><form:select path="pesquisa.tipoArquivo" items="${tiposArquivo}" itemLabel="dsTipoArquivo" itemValue="cdTipoArquivo" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.statusArquivo"/>:</td>
    <td ><form:select path="pesquisa.statusArquivo" items="${statusArquivo}" itemLabel="dsStatusArquivo" itemValue="cdStatusArquivo" /></td>
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
<display:table style="width:100%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/pre/processados/pesquisa/tab1.scc" class="ui-state-default">
<display:column property="row.noArquivo" title="Nome" />
<display:column property="qtCDR" title="Qtd. Registros" />
<display:column property="dataProcClaro" title="Data Processamento" />
<display:column property="dataInicioTrafego" title="Dt. Inicial" />
<display:column property="dataFinalTrafego" title="Dt. Final" />
<display:column property="status" title="Status" />
<display:column property="row.tipoArquivo.dsTipoArquivo" title="Tipo" />
<display:column property="qtDuracaoReal" title="Qt. Min. Reais" />
<display:column property="qtDuracaoTarifada" title="Qt. Min. Tarifados" />
<display:column property="valorBruto" title="Valor Bruto" />
<display:column property="selecionar" title="Selecionar" />
</display:table>
</td></tr>
</table>
</c:if>


</div>

</form:form>
</div>
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
