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
		$("#dataInicial").mask("99/99/9999");	
		$("#dataFinal").mask("99/99/9999");
		$( "#dataInicial" ).datepicker();
		$( "#dataFinal" ).datepicker();		
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$("#tipoOperadora").change(trocaTipoOperadora); 
		$('#tabs').tabs();
	});

	$(document).ready(function() {
		$("#comboHoldingClaro").hide();
		$("#tipoOperadora").change(trocaTipoOperadora);
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

	function trocaTipoOperadora() {
		var sel = $("#tipoOperadora option:selected");
		if (sel.val() == "O")
			mostraOperadoraClaro();
		else
			mostraHoldingClaro();
	}

	function mostraHoldingClaro() {
		$.ajax({
			url : "/scc/user/recepcao_transmissao/holding/json.scc",
			dataType : "json",
			success : function(data) {
				var name, select, option;
				select = document.getElementById('cdEOTClaro');
				select.options.length = 0;
				for (name in data) {
					if (data.hasOwnProperty(name)) {
						select.options.add(new Option(data[name], name));
					}
				}
			}
		});
	}

	function mostraOperadoraClaro() {
		$.ajax({
			url : "/scc/user/recepcao_transmissao/operadoras/json.scc",
			dataType : "json",
			success : function(data) {
				var name, select, option;
				select = document.getElementById('cdEOTClaro');
				select.options.length = 0;
				for (name in data) {
					if (data.hasOwnProperty(name)) {
						select.options.add(new Option(data[name], name));
					}
				}
			}
		});
	}
</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/financeiro/perda/faturamento/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<form:hidden path="entity.dtProcExterna" id="dtProcExterna"/>
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.perda.faturamento.label.tipooperadora" /></td>
					<td><form:select path="tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.perda.faturamento.label.operadoraclaro" /></td>
					<td id="comboOperadoraClaro"><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.perda.faturamento.label.operadorald" /></td>
					<td id="cdEOTLD"><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>

				
				
				<tr>
					<td width="15%"><spring:message code="relatorio.perda.faturamento.label.evento"/></td>
					<td> <form:select path="tipoEvento" items="${eventos}" itemLabel="label" itemValue="key"/> </td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.perda.faturamento.label.datainicial"/></td>
    				<td><form:input id="dataInicial" path="dataInicial" />
    				<form:errors path="dataInicial" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.perda.faturamento.label.datafinal"/></td>
    				<td>
    					<form:input id="dataFinal" path="dataFinal" />
    					<form:errors path="dataFinal" />
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/financeiro/perda/faturamento/tab1.scc" class="ui-state-default">
							<display:column property="dtProcExterna" title="Data referencia" style="text-align:right"/>
							<display:column property="operadoraLd" title="Operadora LD" style="text-align:right"/>
							<display:column property="operadoraClaro" title="Operadora Claro" style="text-align:right"/>
							<display:column property="valorLiquido" title="Valor Liquido" style="text-align:right"/>
							<display:column property="valorBruto" title="VL Bruto" style="text-align:right"/>
							<display:column property="qtdCdr" title="Qtde Cdr" style="text-align:right" />
							<display:column property="fileType" title="Status" style="text-align:right" />
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
