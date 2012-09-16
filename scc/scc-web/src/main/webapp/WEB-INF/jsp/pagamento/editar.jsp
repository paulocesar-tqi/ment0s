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
	
	
	$("#dtEmissaoNf").mask("99/99/9999");
	$("#dtEmissaoNf" ).datepicker();
	
	$("#dtVencimentoNf").mask("99/99/9999");
	$("#dtVencimentoNf").datepicker();
	
	$("#dtEntregaRepasse").mask("99/99/9999");
	$("#dtEntregaRepasse").datepicker();
	
	$("#dtPagamentoRepasse").mask("99/99/9999");
	$("#dtPagamentoRepasse").datepicker();
	
	$("#dtPagamentoRepasse").mask("99/99/9999");
	$("#dtPagamentoRepasse").datepicker();
	
	$('#salvar_button').click(function(){
		$('#operacao').val("salvar");
		$('#form1').submit();
	});
	
	
	$('#cancelar_button').click(function(){
		$('#operacao').val("cancelar");
		$('#form1').submit();
	});
	
});

</script>
<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pos_consulta.filtro.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
<td width="10%">Operadora Claro</td> 
<td><form:input path="operadoraClaro" id="operadoraClaro" size="25" maxlength="25" readonly="true"/></td>
</tr>

<tr>
<td width="10%">Operadora LD</td>
<td><form:input path="operadoraLD" id="operadoraLD" size="25" maxlength="25" readonly="true"/></td>
</tr>


<tr>
<td width="10%">Nu. Repasse</td>
<td><form:input path="entity.id.nuRepasse" id="nuRepasse" size="12" maxlength="10" readonly="true"/></td>
</tr>

<tr>
<td width="10%">UF</td>
<td><form:input path="entity.sgUf" id="sgUf" size="2" maxlength="2" readonly="true"/></td>
</tr>

<tr>
<td width="10%">Número NF.</td>
<td><form:input path="entity.nuNf" id="nuNf" size="12" maxlength="10"/></td>
</tr>

<tr>
<td width="10%">Data Emissão NF.</td>
<td><form:input path="entity.dtEmissaoNf" id="dtEmissaoNf" /></td>
</tr>

<tr>
<td width="10%">Data de Vencimento</td>
<td><form:input path="entity.dtVencimentoNf" id="dtVencimentoNf" /></td>
</tr>

<tr>
<td width="10%">Valor Bruto NF.</td>
<td><form:input path="entity.vlBrutoNf" id="vlBrutoNf" size="25" maxlength="23"/></td>
</tr>

<tr>
<td width="10%">Valor do Repasse</td>
<td><form:input path="entity.vlRepasse" id="vlRepasse" size="25" maxlength="23"  readonly="true"/></td>
</tr>

<tr>
<td width="10%">Data Entrega do Repasse</td>
<td><form:input path="entity.dtEntregaRepasse" id="dtEntregaRepasse" /></td>
</tr>

<tr>
<td width="10%">Forma de Pagamento</td>
<td><form:select path="entity.cdFormaPagamento" id="cdFormaPagamento" items="${formasPagamento}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
<td width="10%">Data de Pagamento do Repasse</td>
<td><form:input path="entity.dtPagamentoRepasse" id="dtPagamentoRepasse" /></td>
</tr>

<tr>
<td width="10%">Dias em Atraso</td>
<td><form:input path="entity.qtDiasAtraso" id="qtDiasAtraso" size="4" maxlength="3"/></td>
</tr>

<tr>
<td width="10%">Valor Pagamento do Repasse</td>
<td><form:input path="entity.vlPagamentoRepasse" id="vlPagamentoRepasse" size="25" maxlength="23"/></td>
</tr>

<tr>
<td width="10%">Observação</td>
<td><form:input path="entity.txObservacao" id="txObservacao" size="25" maxlength="40"/></td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="salvar_button" type="button" value=<spring:message code="comum.botao.salvar"/> />
    <input id="cancelar_button" type="button" value=<spring:message code="comum.botao.cancelar"/> />
    </td>
</tr>
</table>
</form:form>