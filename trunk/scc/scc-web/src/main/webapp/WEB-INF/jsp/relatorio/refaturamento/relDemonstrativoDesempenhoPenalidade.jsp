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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/DemonstrativoDesempenhoPenalidade/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.demonstrativo.desempenho.penalidade.label.operadorald" /></td>
					<td id="cdCSP"><form:select path="cdCSP" id="cdCSP" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				<tr>    
				    <td width="10%"><spring:message code="relatorio.demonstrativo.desempenho.penalidade.label.mesRelatorio"/>:</td>
				    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>
				</tr>
				<tr>
					<td width="10%"><spring:message code="relatorio.demonstrativo.desempenho.penalidade.label.anoRelatorio"/>:</td>
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="disputaDetalhe" requestURI="/scc/user/relatorio/DemonstrativoDesempenhoPenalidade/tab1.scc" class="ui-state-default">
								<display:column property="indicador" title="Ref" style="width:6%"/>
								<display:column property="descIndicador" title="Nome" style="width:6%"/> 
								<display:column property="respIndicador" title="Resp" style="width:6%"/> 
								<display:column property="vlOrigem" title="Real %" style="width:6%"/> 
								<display:column property="metaIndicador" title="Meta %" style="width:6%"/>
								<display:column property="atingimento" title="Atingimento %" style="width:6%"/>
								<display:column property="acelerador" title="Acelerador" style="width:6%"/>
								<display:column property="pesoIndicador" title="Peso %" style="width:6%"/>
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
