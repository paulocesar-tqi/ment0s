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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/faturas/acoes/cobranca/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
						
				<tr>
					<td width="15%"><spring:message code="relatorio.faturas.acoes.cobranca.label.operadora" /></td>
					<td id="cdEOTLD"><form:select path="cdCsp" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdCsp" /></td>
				</tr>
				<tr>
		    		<td><spring:message code="relatorio.faturas.acoes.cobranca.label.mes"/>:</td>
				    <td ><form:select path="mes" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>
		    		<td><spring:message code="relatorio.faturas.acoes.cobranca.label.ano"/>:</td>
				    <td >
				    	<form:input path="ano" id="anoRelatorio" size="4" maxlength="4"/>
						<form:errors path="ano" />
					</td>				    
				</tr>
				<tr>
			</table>
			<!-- 
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/faturas/acoes/cobranca/tab1.scc" class="ui-state-default">
							<display:column property="ban" title="BAN" style="text-align:right" />						
							<display:column property="terminal" title="Terminal" style="text-align:right"/>
							<display:column property="faturaLD" title="Fatura LD"/>
							<display:column property="valorFatura" title="Valor R$" style="text-align:right" format="#,##0.00/>							
							<display:column property="dataEmissao" title="Data Emissão" style="text-align:right" format="dd/MM/yyyy"/>
							<display:column property="dataVencimento" title="Data Vencto " style="text-align:right" format="dd/MM/yyyy"/>
							<display:column property="dataCarta" title="Data Carta" style="text-align:right" format="dd/MM/yyyy"/>							
							<display:column property="arquivoCarta" title="Arquivo Carta" style="text-align:right" />							
							<display:column property="dataConnectCarta" title="Data Connect Arq Carta" style="text-align:right" format="dd/MM/yyyy"/>							
							<display:column property="dataBloqueio" title="Data Bloqueio" style="text-align:right" format="dd/MM/yyyy"/>
							<display:column property="arquivoBloqueio" title="Arquivo Bloqueio " style="text-align:right"/>
							<display:column property="dataConnectBloqueio" title="Data Connect Arq Bloqueio" style="text-align:right" format="dd/MM/yyyy"/>							
							<display:column property="dataPagamento" title="Data Pagamento" style="text-align:right" format="dd/MM/yyyy"/>							
							<display:column property="arquivoPagamento" title="Arquivo Pagamento" style="text-align:right" />
							<display:column property="dataConnectPagamento" title="Data Connect Arq Pgto" style="text-align:right" format="dd/MM/yyyy"/>
							
							
						</display:table>
					</td>
				</tr>
			</table>
			 -->
		</div>
	</form:form>
</div>
<script>
	$(document).ready(function(){
		$('#pesquisar_button').removeAttr('disabled');
		$('#excel_button').removeAttr('disabled');
	});
</script>
