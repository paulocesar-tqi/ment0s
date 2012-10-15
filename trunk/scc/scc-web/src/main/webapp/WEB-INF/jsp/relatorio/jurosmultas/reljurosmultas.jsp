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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/juros/multas/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
						
				<tr>
					<td width="15%"><spring:message code="controle.remessa.evento.label.eot.externa" /></td>
					<td id="cdEOTLD"><form:select path="entity.csp" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdCsp" /></td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="controle.remessa.evento.label.eot.claro" /></td>
					<td id="comboOperadoraClaro"><form:select path="entity.eotClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.faturas.controle.label.datainicial"/></td>
    				<td><form:input id="dataInicial" path="dataInicialPeriodo" />
    				<form:errors path="dataInicialPeriodo" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.faturas.controle.label.datafinal"/></td>
    				<td>
    					<form:input id="dataFinal" path="dataFinalPeriodo" />
    					<form:errors path="dataFinalPeriodo" />
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/juros/multas/tab1.scc" class="ui-state-default">
							<display:column property="csp" title="Csp" style="text-align:right"/>						
							<display:column property="operadoraClaro" title="Operadora Claro" style="text-align:right"/>
							<display:column property="uf" title="UF"/>
							<display:column property="numeroFatura" title="Fatura destino" style="text-align:right"/>
							<display:column property="dataEmissao" title="Data Emissão" style="text-align:right"/>
							<display:column property="dataVencimento" title="Vencimento " style="text-align:right"/>
							<display:column property="juros" title="Valor de Juros" style="text-align:right"/>							
							<display:column property="multa" title="Valor Multa" style="text-align:right" />							
							<display:column property="valor" title="Total" style="text-align:right" />
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
