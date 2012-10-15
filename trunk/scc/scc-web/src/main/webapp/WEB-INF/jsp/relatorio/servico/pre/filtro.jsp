<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<script>
	$(document).ready(function(){
		$('#tabs').tabs();
		$("#anoRelatorio").mask("9999");	
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$('#inserirButton').click(function(){
			$('#operacao').val("CRIAR");
			$('#criados').val("");
			$('#form1').submit();
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
		<li><a href="#tabs-1"><spring:message code="relatorio.titulo.prestacao.servico.pos"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/servico/pre/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td width="10%"><spring:message code="relatorio.label.operadora.claro"/>:</td>
    				<td ><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" />    
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.label.operadora.ld"/>:</td>
    				<td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />    
				</tr>


				<tr>    
				    <td width="10%"><spring:message code="repasse_pre_relatorios.mes"/>:</td>
				    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>    
				</tr>

				<tr>
					<td width="10%"><spring:message code="repasse_pre_relatorios.ano"/>:</td>
				    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
				    <form:errors path="anoRelatorio" /></td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/servico/pre/tab1.scc" class="ui-state-default">
							<display:column property="operadoraClaro" title="Operadora Claro"/>
							<display:column property="embratel" title="Embratel"/>
							<display:column property="intelig" title="Intelig"/>
							<display:column property="brasilTelecom" title="Brasil Telecom"/>
							<display:column property="telefonica" title="Telefonica"/>
							<display:column property="tnl" title="TNL"/>
							<display:column property="gvt" title="GVT"/>
							<display:column property="sercomtel" title="Sercomtel"/>
							<display:column property="tim" title="TIM"/>
							<display:column property="ctbc" title="CTBC"/>
							<display:column property="telemar" title="Telemar"/>
							<display:column property="ipCorp" title="IPCORP" />
							<display:column property="nexus" title="Nexus"/>
						</display:table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
    				<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    				<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    					<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.executar"/> />  
    					<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />  
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
