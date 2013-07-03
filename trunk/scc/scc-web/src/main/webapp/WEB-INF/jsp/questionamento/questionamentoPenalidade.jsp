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





<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/questionamento/penalidade/execute.scc" id="form1">
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
						<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   
							pagesize="20"  id="repasses" requestURI="/scc/user/questionamento/penalidade/tab1.scc" class="ui-state-default">
							
							<display:column property="descricao" title="Descrição" style="width:40%; text-align:left"/>
							<display:column property="qtdChamadas" title="Qtd. Chamadas" style="width:20%; text-align:right;"/>
							<display:column property="qtdMinutos" title="Qtd. Minutos" style="width:20%; text-align:right;"/>
							<display:column property="vlrLiquido" title="Vlr. Liquido" style="width:20%; text-align:right;"/>
							<display:column property="vlrBruto" title="Vlr. Bruto R$" style="width:20%; text-align:right;"/>
							<display:column property="qtdChamadasRefaturadas" title="Qtd. Chamadas Refaturadas" style="width:30%; text-align:right;"/>
							<display:column property="qtdMinutosRefaturadas" title="qtd. Minutos" style="width:30%; text-align:right;"/>
							<display:column property="qtdChamadasNaoRefaturadas" title="Qtd. Chamadas Não Refaturadas" style="width:30%; text-align:right"/>
							<display:column property="qtdMinutosNaoRefaturadas" title="Qtd. Minutos Não Refaturadas" style="width:30%; text-align:right"/>
							<display:column property="vlrPenalidade" title="Vlr. Penalidade R$" style="width:30%; text-align:right"/>
							
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
