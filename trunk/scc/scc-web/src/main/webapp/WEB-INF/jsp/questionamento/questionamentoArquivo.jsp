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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/questionamento/arquivo/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<form:hidden path="filtro.sqQuestionamento"/>
		<form:hidden path="filtro.sqRemessaQuestionamento"/>
		<form:hidden path="filtro.status" id="status"/>
		<form:hidden path="filtro.cdEOTLD" id="opLd"/>
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >

				<tr>
					<td width="15%"><spring:message code="questionamento.arquivo.label.questionamento" /></td>
					<td> <form:select path="filtro.sqQuestionamento" items="${Questionamento}" itemLabel="descricaoEotLD" itemValue="sqQuestionamento"></form:select> </td>
				</tr>
			
				<tr>
					<td width="15%"><spring:message code="questionamento.arquivo.label.arquivo" /></td>
					<td> <form:select path="filtro.sqQuestionamento" items="${Arquivos}" itemLabel="descricaoEotLD" itemValue="sqQuestionamento"></form:select> </td>
				</tr>

			
				<tr>
					<td width="15%"><spring:message code="questionamento.arquivo.label.operadora.ld" /></td>
					<td> <form:select path="filtro.cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
				</tr>
			
				<tr>
					<td width="15%"><spring:message code="questionamento.arquivo.label.status" /></td>
					<td> <form:select path="filtro.status" items="${status}" itemLabel="label" itemValue="key"></form:select> </td>
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
						<display:table style="width:90%"  name="requestScope.filtro.listQuestionamentoArquivo"  pagesize="20"  id="repasses" 
									   requestURI="/scc/user/questionamento/arquivo/tab1.scc" class="ui-state-default" decorator="com.claro.sccweb.decorator.view.QuestionamentoArquivoDecorator" >
							
							
							<display:column property="noArquivo" href="analiseChamadas.scc?sqQuestionamento=${repasses.sqQuestionamento}&sqRemessaQuestionamento=${repasses.sqRemessaQuestionamento}"  titleKey="questionamento.arquivo.display.arquivo"/>								
							<display:column property="qtdAssinante" title="Qtd. Assinante" style="width:10%; text-align:right"/>
							<display:column property="qtdRegra" title="Qtd. Regra"  style="width:10%; text-align:right"/>
							<display:column property="qtdParametro" title="Qtd Parametro" style="width:10%; text-align:right"/>
							<display:column property="qtdLogica" title="Qtd. Logica" style="width:10%; text-align:right;"/>
							<display:column property="qtdCdr" title="Qtd. Cdr" style="width:13%; text-align:right;"/>
							<display:column property="minutosTarifados" title="Minutos Tarifados" style="width:15%; text-align:right"/>
							
							<display:column property="vlrLiquido" title="Vlr. Liquido" style="width:15%; text-align:right" format="{0, number, #,##0.00}">
								
							</display:column>
							
							
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