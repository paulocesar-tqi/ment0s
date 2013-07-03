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
		
	$('#tabs').tabs();
});
	

function excel()
{			
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");
	$('#form1').submit();		
}

/**
 * 
 */
function bindProcedente(obj, target){	
	
	document.getElementById(target).value = obj.value;
	
}

</script>



<html>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="POST" action="/scc/user/questionamento/arquivo/confirmarAnalise.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<form:hidden path="filtro.sqQuestionamento"/>

		<div id="tabs-1">
			<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td>                            
						<display:table style="width:90%"  name="requestScope.filtro.listQuestionamentoAnalise"  pagesize="20"  id="repasses" 
									   requestURI="/scc/user/questionamento/arquivo/tab2.scc" class="ui-state-default" 
									   decorator="com.claro.sccweb.decorator.view.QuestionamentoAnaliseChamadaDecorator" >
														
							<display:column property="cdMotivoRejeicao"  titleKey="questionamento.analise.display.column.mtvo.rejeicao" style="width:10%; text-align:right"/>								
							<display:column property="nuMsisdnOrigem" titleKey="questionamento.analise.display.column.assinantea" style="width:10%; text-align:right"/>
							<display:column property="nuTelefoneDestino" titleKey="questionamento.analise.display.column.assinanteb"  style="width:10%; text-align:right"/>
							<display:column property="dtChamada" titleKey="questionamento.analise.display.column.dtchamada" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:right"/>
							<display:column property="hrInicioChamada" titleKey="questionamento.analise.display.column.iniciochamada" style="width:10%; text-align:right;"/>
							<display:column property="miDuracaoTarifada" titleKey="questionamento.analise.display.column.duracaotarifada" style="width:13%; text-align:right;"/>
							<display:column property="vlLiquidoChamada" titleKey="questionamento.analise.display.column.vlrliquido" format="{0, number, #,##0.00}" style="width:15%; text-align:right"/>							
							<display:column property="procedente" titleKey="questionamento.analise.display.column.procedente" style="width:40%; text-align:center"/>
							 
							<display:column>								
								<spring:bind path="filtro.sqRemessaQuestionamentos"  >	
									<input type="hidden" name="sqRemessaQuestionamentos" value="${repasses.sqRemessaQuestionamento}" />
								</spring:bind>
								
								<spring:bind path="filtro.sqCdrQuestionamentos"  >	
									<input type="hidden" name="sqCdrQuestionamentos" value="${repasses.sqCdrQuestionamento}" />
								</spring:bind>	

								<spring:bind path="filtro.filtro.sqRemessaQuestionamento"  >	
									<input type="hidden" name="sqRemessaQuestionamento" value="${repasses.sqRemessaQuestionamento}" />
								</spring:bind>
								
								<spring:bind path="filtro.filtro.sqCdrQuestionamento"  >	
									<input type="hidden" name="sqCdrQuestionamento" value="${repasses.sqCdrQuestionamento}" />
								</spring:bind>				
											
								<spring:bind path="filtro.filtro.sqQuestionamento"  >	
									<input type="hidden" name="sqQuestionamento" value="${repasses.sqQuestionamento}" />
								</spring:bind>				
								 
							</display:column>
							
						</display:table>
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
			    	<td colspan="5" class="TdFormularioUp">    
			    	
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="voltar_button" type="button" value=<spring:message code="comum.botao.voltar"/> />
				    </td>
			    	
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
				    </td>
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="procedente_todos_button" type="button" value=<spring:message code="comum.botao.procedente.todos"/> />
				    </td>
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="nao_procedente_todos_button" type="button" value=<spring:message code="comum.botao.nao.procedente.todos"/> />
				    </td>
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="efetivar_analise_button" type="submit" value=<spring:message code="comum.botao.efetivar.analise"/> />
				    </td>
				   </td>
				</tr>
			</table>
		</div>
	
	</form:form>
</div>
<script>
$(document).ready(function(){
	$('#efetivar_analise_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
</html>