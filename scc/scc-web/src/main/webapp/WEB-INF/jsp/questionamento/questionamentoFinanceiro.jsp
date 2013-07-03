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
	$('#excel_button').click(excel);	
	$('#pesquisar_button').click(pesquisar);	
		
	$('#tabs').tabs();
});
	

function excel()
{			
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");
	$('#form1').submit();		
}

function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

</script>


<html>
	
	
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
		</ul>
		<form:form modelAttribute="filtro" method="post" action="/scc/user/questionamento/financeiro/execute.scc" id="form1">
			<form:hidden path="operacao" id="operacao"/>
			<form:hidden path="itemSelecionado" id="itemSelecionado"/>
			<div id="tabs-1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td width="15%"><spring:message code="questionamento.financeiro.label.operadora.claro" /> </td>
						<td> <form:select path="filtro.cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="questionamento.financeiro.label.operadora.ld" /></td>
						<td> <form:select path="filtro.cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="questionamento.financeiro.label.questionamento" /></td>
						<td> <form:select path="filtro.sqQuestionamento" items="${Questionamento}" itemLabel="descricaoEotLD" itemValue="sqQuestionamento"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="questionamento.financeiro.label.mes" /></td>
						<td> 
							<form:select id="mmCiclo" path="filtro.mes" itemValue="key" itemLabel="label" items="${meses}"/>
						</td>
					</tr>
								
					<tr>
						<td width="15%"><spring:message code="questionamento.financeiro.label.ano" /></td>
						<td>
							<form:input path="filtro.ano" size="5" maxlength="4"/>
							<form:errors path="ano"/> 
						</td>
					</tr>
				
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
				    	<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
					    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						    <input id="pesquisar_button" type="button" value="Pesquisar" />
					    </td>
					</tr>
				</table>
				<br/>
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td>                            
							<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" 
							requestURI="/scc/user/questionamento/financeiro/tab1.scc" class="ui-state-default" decorator="com.claro.sccweb.decorator.view.SccQuestionamentoFinanceiroDecorator">
								<display:column property="descricaoEot" title="Eot LD"/>
								<display:column property="cdEotClaro" title="Eot Claro" style="text-align:right"/>
								<display:column property="sgUf" title="UF"/>
								<display:column property="sqQuestionamento" title="Nr(Q)" style="text-align:right"/>
								<display:column property="noArquivo" title="Arquivo" style="width:10%; text-align:left;"/>/>
								<display:column property="totais" title="Totais" style="width:10%; text-align:left;"/>
								<display:column property="cdrQuestionados" title="Cdr Questionados" style="text-align:right"/>
								<display:column property="cdrConfirmadosSemAnalise" title="CDR Confiramdos Sem Análise" style="text-align:right"/>
								<display:column property="cdrConfirmadosComAnalise" title="CDR Confirmados Com Análise (P)" style="text-align:right"/>
								<display:column property="cdrConfirmadosComAnaliseSemProcedencia" title="CDR Confirmados com Análise(NP)" style="text-align:right"/>
								<display:column property="cdrPenalidadeClaro" title="Penalidade Claro" style="text-align:right"/>
								<display:column property="cdrPenalidadeLd" title="Penalidade LD" style="text-align:right"/>
								
							</display:table>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
				    	<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
					    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
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
</html>