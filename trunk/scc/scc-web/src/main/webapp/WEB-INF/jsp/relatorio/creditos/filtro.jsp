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
		$("#dtInicial").mask("99/99/9999");	
		$("#dtFinal").mask("99/99/9999");
		$( "#dtInicial" ).datepicker();
		$( "#dtFinal" ).datepicker();		
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$('#tabs').tabs();
	});

	$(document).ready(function() {
		$("#dtInicial").mask("99/99/9999");	
		$("#dtFinal").mask("99/99/9999");
		$( "#dtInicial" ).datepicker();
		$( "#dtFinal" ).datepicker();		
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

	function listar(linha)
	{
		$('#itemSelecionado').val(linha);	
		$('#operacao').val("listar");
		$('#form1').submit();
	}

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/pre/creditos/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.prepago.creditos.label.operadorald" /></td>
					<td id="operadoraLD"><form:select path="operadoraLD" id="operadoraLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.prepago.creditos.label.operadoraclaro" /></td>
					<td id="operadoraClaro"><form:select path="operadoraClaro" id="operadoraClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.prepago.creditos.label.tipocredito" /></td>
    				<td id="tipoCredito"><form:select path="tipoCredito" id="tipoCredito" items="${tipoCredito}" itemLabel="label" itemValue="key" /></td>
				</tr>				
				<tr>
    				<td width="10%"><spring:message code="relatorio.prepago.creditos.label.statuscredito"/></td>
    				<td id="cdStatusCredito"><form:select path="cdStatusCredito" id="cdStatusCredito" items="${cdStatusCredito}" itemLabel="label" itemValue="key" /></td>
				</tr>				
				<tr>
    				<td width="10%"><spring:message code="relatorio.prepago.creditos.label.datainicial"/></td>
    				<td><form:input id="dtInicial" path="dtInicial" />
    				<form:errors path="dtInicial" /></td>
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.prepago.creditos.label.datafinal"/></td>
    				<td>
    					<form:input id="dtFinal" path="dtFinal" />
    					<form:errors path="dtFinal" />
    				</td>
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="prePagoCreditos" requestURI="/scc/user/relatorio/pre/creditos/tab1.scc" class="ui-state-default">
							<display:column property="noArquivo" title="Nome do Arquivo"/>
							<display:column property="operadoraLD" title="Operadora LD"/>
							<display:column property="operadoraClaro" title="Operadora Claro"/>
							<display:column property="dsCredito" title="Tipo de Crédito"/>
							<display:column property="dsStatus" title="Status de Crédito"/>
							<display:column property="numeroA" title="Telefone"/>
							<display:column property="dtCredito" title="Data do Crédito"/>
							<display:column property="vlCredito" title="Valor do Crédito"/>														
							<display:column property="quantidade" title="Qtd Chamadas Apuradas"/>
							<display:column property="minutosQueimados" title="Qtd Minutos Queimados"/>
							<display:column property="minutosAjustados" title="Qtd Minutos Ajustados"/>
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
