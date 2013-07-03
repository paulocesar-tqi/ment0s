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
		$("#dtInicioEvento").mask("99/99/9999");	
		$("#dtFimEvento").mask("99/99/9999");
		$( "#dtInicioEvento" ).datepicker();
		$( "#dtFimEvento" ).datepicker();		
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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/eventos/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.eventos.label.operadorald" /></td>
					<td id="cdEOTLD"><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.eventos.label.operadoraclaro" /></td>
					<td id="comboOperadoraClaro"><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.eventos.label.tipostatus"/></td>
    				<td ><form:select path="tpStatus" id="tpStatus" items="${tiposStatus}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.eventos.label.datainicial"/></td>
    				<td><form:input id="dtInicioEvento" path="dtInicioEvento" />
    				<form:errors path="dtInicioEvento" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.eventos.label.datafinal"/></td>
    				<td>
    					<form:input id="dtFimEvento" path="dtFimEvento" />
    					<form:errors path="dtFimEvento" />
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/eventos/tab1.scc" class="ui-state-default">
							<display:column property="dtReferencia" title="Data Referência"/>
							<display:column property="dsOperadoraClaro" title="Operadora Claro"/>
							<display:column property="dsOperadoraExterna" title="Operadora LD"/>
							<display:column property="statusChamada" title="Status da Chamada"/>
							<display:column property="motivoRejeicao" title="Cd Descrição Rejeição"/>
							<display:column property="dsDefeito" title="Cd Descrição Defeito"/>
							<display:column property="quantidadeCdrs" title="Quantidade" />
							<display:column property="duracaoReal" title="Duração Real" />
							<display:column property="duracaoTarifada" title="Duração Tarifada" />
							<display:column property="valorBruto" title="Valor Bruto" />
							<display:footer>
							    <tr>
							      <th align="left">Total:</th>
							      <th></th>
							      <th></th>
							      <th></th>
							      <th></th>
							      <th></th>
							      <th align="left">${sessionScope._DISPLAY_TAG_SPACE_2.totalQuantidade}</th>
							      <th align="left">${sessionScope._DISPLAY_TAG_SPACE_2.totalDuracaoReal}</th>
							      <th align="left">${sessionScope._DISPLAY_TAG_SPACE_2.totalDuracaoTarifada}</th>
							      <th align="left">${sessionScope._DISPLAY_TAG_SPACE_2.totalValorBruto}</th>
							    <tr>
							</display:footer>						
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
