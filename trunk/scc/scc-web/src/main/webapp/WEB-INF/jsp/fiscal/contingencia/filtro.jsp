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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/contingenciaFiscal/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.contingencia.fiscal.label.operadorald" /></td>
					<td id="cdCSP"><form:select path="cdCSP" id="cdCSP" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdCsp" /></td>
				</tr>
				<tr>    
				    <td width="10%"><spring:message code="relatorio.contingencia.fiscal.label.mesRelatorio"/>:</td>
				    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>
				</tr>
				<tr>
					<td width="10%"><spring:message code="relatorio.contingencia.fiscal.label.anoRelatorio"/>:</td>
				    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
				    <form:errors path="anoRelatorio" /></td>
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="contingenciaFiscal" requestURI="/scc/user/relatorio/contingenciaFiscal/tab1.scc" class="ui-state-default">
							<display:column property="nomeEmpresa" title="Empresa"/>
							<display:column property="cdCSP" title="CSP"/>
							<display:column property="sgUF" title="UF"/>
							<display:column property="valorFaturado" title="Valor Faturado"/>
							<display:column property="valorBaseCalculo" title="Valor Base de Cálculo"/>
							<display:column property="valorICMS" title="Valor do ICMS"/>
							<display:column property="valorInsencao" title="Valor da Insenção"/>														
							<display:column property="nfInicial" title="NF Inicial"/>
							<display:column property="nfFinal" title="NF Final"/>
							<display:column property="razaoSocial" title="Razão Social"/>
							<display:column property="numeroCNPJ" title="Número do CNPJ"/>
							<display:column property="inscricaoEstadual" title="Inscrição Estadual"/>
							<display:column property="serieNF" title="Série/Subsérie"/>							
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
