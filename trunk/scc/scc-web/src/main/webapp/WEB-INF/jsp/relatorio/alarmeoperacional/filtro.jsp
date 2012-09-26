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
		$("#dtInicio").mask("99/99/9999");	
		$("#dtFim").mask("99/99/9999");
		$("#dtInicio").datepicker();
		$("#dtFim").datepicker();		
		$('#pesquisar_button').click(pesquisar);
		$('#excel_button').click(excel);
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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/alarmeoperacional/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
    				<td width="10%"><spring:message code="relatorio.alarmeoperacional.label.nomearquivo"/></td>
    				<td ><form:select path="nmArquivo" items="${nomesArquivo}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.alarmeoperacional.label.datainicial"/></td>
    				<td><form:input id="dtInicio" path="dtInicio" />
    				<form:errors path="dtInicio" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.alarmeoperacional.label.datafinal"/></td>
    				<td>
    					<form:input id="dtFim" path="dtFim" />
    					<form:errors path="dtFim" />
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/batimento/arquivos/tab1.scc" class="ui-state-default">
							<display:column property="nomA" title="Número de A"/>
							<display:column property="qtdChamadas" title="Qtde Chamadas"/>
							<display:column property="qtdMinutos" title="Qtde Minutos Tarifados"/>
							<display:column property="valorLiquidoClaro" title="Valor Liquido Total das Chamadas" />
							<display:column property="erroProtocoloClaro" title="Número da Fatura" />
							<display:column property="descErroProtocoloClaro" title="Número da Nota Fiscal" />
							<display:column property="nomeArquivoLD" title="Operadora LD" />
							<display:column property="quantidadeLD" title="Data de Referência"  />
							<display:column property="statusLD" title="Status"  />
							<display:column property="quantidadeBat" title="Quantidade" />
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
