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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/faturas/acoes/cobranca/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
						
				<tr>
					<td width="15%"><spring:message code="relatorio.faturas.acoes.cobranca.label.operadora" /></td>
					<td><form:select path="filtro.cdCsp" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdCsp" /></td>
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.faturas.controle.label.datainicial"/></td>
    				<td><form:input id="dataInicial" path="filtro.dataInicialPeriodo" />
    				<form:errors path="filtro.dataInicialPeriodo" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.faturas.controle.label.datafinal"/></td>
    				<td>
    					<form:input id="dataFinal" path="filtro.dataFinalPeriodo" />
    					<form:errors path="filtro.dataFinalPeriodo" />
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<display:table style="width:90%" name="requestScope.filtro.listAcoesCobranca" pagesize="20" id="repasses" 
							requestURI="/scc/user/relatorio/faturas/acoes/cobranca/listar.scc" class="ui-state-default">
							<display:column property="ban" title="BAN" style="text-align:right" />						
							<display:column property="terminal" title="Terminal" style="text-align:right"/>
							<display:column property="faturaLD" title="Fatura LD"/>
							<display:column property="valorFatura" title="Valor R$" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>							
							<display:column property="dataEmissao" title="Data Emissão" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>
							<display:column property="dataVencimento" title="Data Vencto " format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>
							<display:column property="dataCarta" title="Data Carta" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>							
							<display:column property="arquivoCarta" title="Arquivo Carta" style="text-align:right" />							
							<display:column property="dataConnectCarta" title="Data Connect Arq Carta" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>							
							<display:column property="dataBloqueio" title="Data Bloqueio" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>
							<display:column property="arquivoBloqueio" title="Arquivo Bloqueio " style="text-align:right"/>
							<display:column property="dataConnectBloqueio" title="Data Connect Arq Bloqueio" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>							
							<display:column property="dataPagamento" title="Data Pagamento" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>							
							<display:column property="arquivoPagamento" title="Arquivo Pagamento" style="text-align:right" />
							<display:column property="dataConnectPagamento" title="Data Connect Arq Pgto" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>
							
						</display:table>
					</td>
				</tr>
			</table>
			
		</div>
	</form:form>
</div>
<script>

</script>
