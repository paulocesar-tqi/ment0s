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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/criticas/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.criticas.label.operadorald" /></td>
					<td id="cdEOTLD"><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.criticas.label.operadoraclaro" /></td>
					<td id="comboOperadoraClaro"><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.criticas.label.tipostatus"/></td>
    				<td ><form:select path="cdStatus" id="cdStatus" items="${tiposStatus}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.criticas.label.cdmotivorejeicao"/></td>
    				<td ><form:select path="cdMotivoRejeicao" id="cdMotivoRejeicao" items="${motivoRejeicao}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.criticas.label.cddefeito"/></td>
    				<td ><form:select path="cdDefeito" id="cdDefeito" items="${codigoDefeito}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.criticas.label.datainicial"/></td>
    				<td><form:input id="dtInicioEvento" path="dtInicioEvento" />
    				<form:errors path="dtInicioEvento" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.criticas.label.datafinal"/></td>
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/criticas/tab1.scc" class="ui-state-default">
							<display:column property="dtChamada" title="Dt Chamada"/>
							<display:column property="dsOperadoraClaro" title="Operadora Claro"/>
							<display:column property="dsOperadoraExterna" title="Operadora LD"/>
							<display:column property="statusChamada" title="Status da Chamada"/>
							<display:column property="motivoRejeicao" title="Motivo Rejeicao"/>
							<display:column property="cdDefeito" title="Codigo Defeito"/>
							<display:column property="csp" title="CSP"/>
							<display:column property="nroA" title="Numero de A"/>
							<display:column property="nroB" title="Numero de B"/>
							<display:column property="cdPais" title="Codigo de Pais"/>
							<display:column property="cnAreaVisitada" title="CN da Area Visitada"/>
							<display:column property="tipoChamada" title="Tipo da Chamada"/>
							<display:column property="duracaoReal" title="Duração Real" />
							<display:column property="duracaoTarifada" title="Duração Tarifada" />
							<display:column property="valorBruto" title="Valor Bruto" />
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
