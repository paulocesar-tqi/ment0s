<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<style type="text/css">
th.fundoVerde {background:#D7E4BC;}
th.fundoAzul {background:#B8CCE4;}
th.fundoVermelho {background:#FF5544;}
</style>

<script>
	$(document).ready(function(){
		$('#tabs').tabs();
		$("#anoRelatorio").mask("9999");	
		$('#pesquisar_button').click(pesquisar);
		$('#excel_button').click(excel);
		
		$("#dtInicio").mask("99/99/9999");	
		$("#dtFim").mask("99/99/9999");
		$("#dtInicio" ).datepicker();
		$("#dtFim" ).datepicker();		
		
		$("#dtInicioProcExterna").mask("99/99/9999");	
		$("#dtFimProcExterna").mask("99/99/9999");
		$("#dtInicioProcExterna" ).datepicker();
		$("#dtFimProcExterna" ).datepicker();	
		
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

		
		$('#dialog').dialog({
			autoOpen: false,
			width: 200,
			buttons: {
				"Ok": function() { 
					$(this).dialog("close"); 
				}
			}
		});
		
	});
	
	

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="relatorio.disponibilizacao.pacotes.pre.titulo"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/disponibilizacaoPacotes/pre/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td width="10%"><spring:message code="relatorio.label.operadora.claro"/>:</td>
    				<td ><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" />   
    				<form:errors path="cdEOTClaro" />
    				</td> 
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.label.operadora.ld"/>:</td>
    				<td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />   
    				<form:errors path="cdEOTLD" />
    				</td> 
				</tr>
				<tr>    
				    <td width="10%"><spring:message code="relatorio.label.pacote"/>:</td>
				    <td ><form:select path="cdPacote" id="cdPacote" items="${pacotes}" itemLabel="noPacotePrepago" itemValue="cdPacotePrepago" /></td>    
				</tr>

				<tr>
					<td width="10%"><spring:message code="relatorio.label.dtInicial"/>:</td>
					<td><form:input path="dtInicio"/><form:errors path="dtInicio" /></td>
					<td width="10%"><spring:message code="relatorio.label.dtFinal"/>:</td>
					<td><form:input path="dtFim"/><form:errors path="dtFim" /></td>
					<td width="30%"></td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.label.dtInicialProcExterna"/>:</td>
					<td><form:input path="dtInicioProcExterna"/><form:errors path="dtInicioProcExterna" /></td>
					<td width="15%"><spring:message code="relatorio.label.dtFinalProcExterna"/>:</td>
					<td><form:input path="dtFimProcExterna"/><form:errors path="dtFimProcExterna" /></td>
				</tr>
				
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">				
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="31" id="assinaturas" requestURI="/scc/user/relatorio/disponibilizacaoPacotes/pre/tab1.scc" class="ui-state-default">
							<display:column property="dtReferencia" title="Data Referência"/>
							<display:column property="operadoraClaro" title="Operadora Claro"/>
							<display:column property="operadoraLD" title="Operadora LD"/>
							<display:column property="terminal" title="Terminal"/>
							<display:column property="status" title="Status"/>
							<display:column property="descricaoPacote" title="Descrição dos Pacotes"/>
							<display:column property="produto" title="Cod Produto"/>
							<display:column property="qtdPacotes" title="Qtd Pacotes"/>
							<display:column property="vlBruto" title="Valor Bruto"/>		
							<display:column property="qtdChamadas" title="Qtd CDRs"/>
							<display:column property="duracaoReal" title="Duração Real"/>
							<display:column property="qtdConsumida" title="Duração Tarifada"/>
						</display:table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
    				<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    				<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    					<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.executar"/> />
    					<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">  
    					<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />  
    					</c:if>
    				</td>
				</tr>
			</table>
			
	</div>
</form:form>
</div>
<script>
$(function() {
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
