<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css"
	type="text/css" />
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script>
	$(document).ready(function(){	
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$('#tabs').tabs();
	});

	$(document).ready(function() {
		$('#pesquisar_button').click(pesquisar);
		$('#excel_button').click(excel);
		$('#excel_button').attr('disabled', 'disabled');
		$('#tabs').tabs();
	});

	function pesquisar() {
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#excel_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	}

	function excel() {
		$('#operacao').val("excel");
		$('#form1').submit();
	}

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/batimentoFiscal/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.batimento.fiscal.label.operadorald" /></td>
					<td id="cdCSP"><form:select path="cdCSP" id="cdCSP" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdCsp" /></td>
				</tr>
				<tr>
					<td width="10%"><spring:message code="relatorio.batimento.fiscal.label.codigociclo"/>:</td>
				    <td ><form:input path="cdCiclo" id="cdCiclo" size="2" maxlength="2"/>
				    <form:errors path="cdCiclo" /></td>
				</tr>
				<tr>    
				    <td width="10%"><spring:message code="relatorio.batimento.fiscal.label.mesRelatorio"/>:</td>
				    <td ><form:select path="mesCiclo" id="mesCiclo" items="${meses}" itemLabel="label" itemValue="key" /></td>
				</tr>
				<tr>
					<td width="10%"><spring:message code="relatorio.batimento.fiscal.label.anoRelatorio"/>:</td>
				    <td ><form:input path="anoCiclo" id="anoCiclo" size="4" maxlength="4"/>
				    <form:errors path="anoCiclo" /></td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
						<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="batimentoFiscal" requestURI="/scc/user/relatorio/batimentoFiscal/tab1.scc" class="ui-state-default">
							<display:column property="nomeOperadora" title="Longa Distância"/>
							<display:column property="cdCSP" title="CSP"/>
							<display:column property="nomeEmpresa" title="Sistema"/>
							<display:column property="cdEOTClaro" title="Operadora"/>
							<display:column property="sgUF" title="UF"/>
							<display:column property="noArquivo" title="DS Name"/>
							<display:column property="cdStatusArquivo" title="Status"/>
							<display:column property="cdCiclo" title="Ciclo"/>
							<display:column property="vlTotalNF" title="Valor Fiscal"/>
							<display:column property="dtConnect" title="Data da Transmissão"/>
						</display:table>
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
