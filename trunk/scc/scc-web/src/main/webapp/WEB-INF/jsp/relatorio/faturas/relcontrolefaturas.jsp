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
		$("#tipoRelatorio").change(trocaTipoOperadora); 
		$('#tabs').tabs();
	});

	$(document).ready(function() {

		$("#tipoRelatorio").change(trocaTipoOperadora);
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

	function desaativarNumeroFatura(){
		$("#fatura").attr("disabled",true); 
	}

	function ativarNumeroFatura(){
		$("#fatura").removeAttr("disabled"); 
	}

	function trocaTipoRelatorio(){
		if($("#rdbFatura").is(':checked') ) {
			ativarNumeroFatura();
		}
	}
	
	function trocaTipoOperadora() {
		var sel = $("#tipoRelatorio option:selected");
		if (sel.val() == "S")
			desaativarNumeroFatura();
		else
			ativarNumeroFatura();
	}

	function ativarDesativarNumFatura(){
		if (  document.forms[0].tipoConsulta[0].checked  ){
			document.forms[0].numFatura.disabled = true;
			document.forms[0].numFatura.value = '';
		}else{
			document.forms[0].numFatura.disabled = false;
		}
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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/faturas/controle/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
			
				<tr>
					<td width="10%"><spring:message code="relatorio.faturas.controle.label.tiporelatorio" /></td>
					<td><form:select path="filtro.relatorioSelecionado" id="tipoRelatorio" items="${tipoRelatorio}" itemLabel="label" itemValue="key" /></td>
				</tr>
			

				<tr>
					<td width="10%"><spring:message code="controle.remessa.evento.label.eot.externa" /></td>
					<td><form:select path="filtro.cdCsp" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdCsp" /></td>
				</tr>
				
				<tr>
					<td width="10%"><spring:message code="controle.remessa.evento.label.eot.claro" /></td>
					<td><form:select path="filtro.cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.faturas.controle.label.status"/></td>
    				<td ><form:select path="filtro.status" items="${statusFatura}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
					<td width="10%"><spring:message code="relatorio.faturas.controle.label.filtrapor" /></td>
					<td ><form:select path="filtro.tipoData" id="tipoDatas" items="${tipoData}" itemLabel="label" itemValue="key" /></td>
				</tr>
				<tr>
					<td width="10%"><spring:message code="relatorio.faturas.controle.label.numerofatura"/></td>
					<td id="numerofatura"><form:input path="filtro.numeroFatura" id="fatura" disabled="true"/>
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
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<display:table style="width:90%" name="requestScope.filtro.listFaturas" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/faturas/controle/listar.scc" class="ui-state-default">
							<display:column property="eotClaro" title="Operadora Claro"/>
							<display:column property="csp" title="Csp"/>
							<display:column property="operadoraLD" title="Operadora Ld"/>
							<display:column property="uf" title="UF"/>
							<display:column property="cicloMesAno" title="Ciclo"/>
							<display:column property="numeroFatura" title="Numero da Fatura"/>
							<display:column property="dataEmissao" title="Data Emissão" format="{0,date,dd-MM-yyyy}" style="width:30%; text-align:right"/>
							<display:column property="dataVencimento" title="Vencimento " format="{0,date,dd-MM-yyyy}" style="text-align:right"/>
							<display:column property="valor" title="Valor" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="valorOriginal" title="Valor Original" format="{0, number, #,##0.00}" style="width:15%; text-align:right" />
							<display:column property="valorICMS" title="Valor ICMS" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="status" title="Status" style="text-align:right" />
							<display:column property="situacaoEvento" title="Situação/Evento"  />		
							<display:column property="aging" title="Aging " />
							<display:column property="ajuste" title="Ajuste" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="numeroNotaFiscal" title="Numero Nota Fiscal"  />
							<display:column property="serie" title="Serie" />
							<display:column property="subSerie" title="SubSerie" style="text-align:right" />
							<display:column property="totalCreditos" title="Total de Créditos" format="{0, number, #,##0.00}" style="width:15%; text-align:right" />
							<display:column property="valorOfertasLD" title="Ofertas LD" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="valorDescontosLD" title="Descontos LD" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="valorCreditosLD" title="Creditos LD" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="juros" title="juros" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="valorPago" title="Valor Pago" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="quantidadeEventos" title="Qtde. Eventos" style="width:15%; text-align:right"/>
							<display:column property="juros" title="juros" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>
							<display:column property="multas" title="Multa" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>					
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
