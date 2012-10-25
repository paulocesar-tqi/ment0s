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
		<li><a href="#tabs-1"><spring:message code="relatorio.batimento.wrupp.pre.titulo"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/batimento/wrupp/pre/execute.scc" id="form1">
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
					<td align="center">
						<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
						<table id="header1" style="width:100%">
							<thead>
								<tr>
									<th>&nbsp;</th>
									<th class="fundoVerde" style="width: 60px;">WRUPP</th>
									<th class="fundoAzul" style="width: 40px;">SCC</th>
									<th class="fundoVermelho" style="width: 40px;">Diferença</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
						</table>
						</c:if>
					</td>
				</tr>
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="31" id="batimentos" requestURI="/scc/user/relatorio/batimento/wrupp/pre/tab1.scc" class="ui-state-default">
							<display:column property="data" title="Data"/>
							<display:column property="cdEOTLD" title="EOT LD"/>
							<display:column property="cdEOTClaro" title="EOT Claro"/>
							<display:column property="qtdChamadasWrupp" title="Qtd Chamadas" headerClass="fundoVerde"/>
							<display:column property="vlrBrutoWrupp" title="Valor Bruto" headerClass="fundoVerde"/>
							<display:column property="qtdChamadasScc" title="Qtd Chamadas" headerClass="fundoAzul"/>
							<display:column property="vlrBrutoScc" title="Valor Bruto" headerClass="fundoAzul"/>
							<display:column property="qtdChamadasDif" title="Qtd Chamadas" headerClass="fundoVermelho"/>
							<display:column property="vlrBrutoDif" title="Valor Bruto" headerClass="fundoVermelho"/>
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
