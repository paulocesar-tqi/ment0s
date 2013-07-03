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
	$( "#dataFinal" ).datepicker();
	$( "#dataInicial" ).datepicker();
	$('#pesquisar_button').click(pesquisar);
	$('#excel_button').click(excel);		
	$('#tabs').tabs();
});


function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}


function excel()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");	
	$('#form1').submit();
}
</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivos/credito/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
<td width="15%">Origem:</td><td><form:select path="cdOrigem" items="${origem}" itemLabel="label" itemValue="key"/> </td>
</tr>
<tr>
<td width="15%">Status:</td><td><form:select path="cdStatus" items="${status}" itemLabel="label" itemValue="key"/></td>
</tr>
<tr>
<td width="15%">Data Inicial:</td><td><form:input path="dataInicial" id="dataInicial"/> </td>
</tr>
<tr>
<td width="15%">Data Final:</td><td><form:input path="dataFinal" id="dataFinal"/></td>
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
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/arquivos/credito/execute.scc" class="ui-state-default">
<display:column property="row.noArquivo" title="Nome do Arquivo" />
<display:column property="row.sqArquivoCredito" title="Arquivo Crédito" />
<display:column property="origem" title="Origem" />
<display:column property="status" title="Status" />
<display:column property="dataProcessamento" title="Data Processamento" />
<display:column property="row.qtRegistros" title="Registros" />
<display:footer>
    <tr>
      <th align="left">Total:</th>
      <th></th>
      <th></th>
      <th></th>
      <th></th>
      <th align="left">${sessionScope._DISPLAY_TAG_SPACE_2.totalRegistros}</th>
    <tr>
</display:footer>
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