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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/questionamento/requisicao/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<div id="tabs-1">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<h1>Relatorios Prontos</h1>
				<br>
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td>                            
							<display:table style="width:90%"  name="requestScope.filtro.listLoaded"   
								pagesize="20"  id="repasses" requestURI="/scc/user/questionamento/requisicao/listar.scc" class="ui-state-default"
								decorator="com.claro.sccweb.decorator.view.SccRequisicaoDecorator">
								
								<display:column property="anoMesReferencia" title="Ano/Mes Referencia" style="width:10%; text-align:left"/>
								<display:column property="noRequisicao" title="No Requisicao" style="width:10%; text-align:right;"/>
								<display:column property="dtCriacao" title="Data Criação" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:right;"/>
								<display:column property="dtAtualizacao" title="Data Evento" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:right;"/>
								<display:column property="cdUsuarioManut" title="Usuario" style="width:10%; text-align:right;"/>
							</display:table>
						</td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<h1>Relatorios a Carregar</h1>
					<br>
					<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
						<tr>
							<td>                            
								<display:table style="width:90%"  name="requestScope.filtro.listToLoad"   
									pagesize="20"  id="repasses" requestURI="/scc/user/questionamento/requisicao/listar.scc" class="ui-state-default"
									decorator="com.claro.sccweb.decorator.view.SccRequisicaoDecorator">
									
									<display:column property="anoMesReferencia" title="Ano/Mes Referencia" style="width:10%; text-align:left"/>
									<display:column property="noRequisicao" title="No Requisicao" style="width:10%; text-align:right;"/>
									<display:column property="dtCriacao" title="Data Criação" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:right;"/>
									<display:column property="dtAtualizacao" title="Data Evento" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:right;"/>
									<display:column property="cdUsuarioManut" title="Usuario" style="width:10%; text-align:right;"/>
								</display:table>
							</td>
						</tr>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" >
							<tr>
								<td width="15%"><spring:message code="questionamento.requisicao.label.mes" /></td>
								<td width="15%"><spring:message code="questionamento.requisicao.label.ano" /></td>
								<td width="15%"><spring:message code="questionamento.requisicao.label.questionamento" /></td>
								<td width="15%"><spring:message code="questionamento.requisicao.label.operadorald" /></td>
							</tr>
							<tr>
							    <td><form:select path="mes" id ="mes" items="${meses}" itemLabel="label" itemValue="key" /></td>
							    <td ><form:input path="ano" id="ano" size="4" maxlength="4"/>
								<td> <form:select path="sqCdrQuestionamento" items="${Questionamento}" itemLabel="descricaoEotLD" itemValue="sqQuestionamento"></form:select> </td>
								<td> <form:select path="cdEotLd" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
							</tr>
							
						</table>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" >
						<h1>Relatorios Em Processamento</h1>
						<br>
						<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
							<tr>
								<td>                            
									<display:table style="width:90%"  name="requestScope.filtro.listLoading"   
													pagesize="20"  id="repasses" requestURI="/scc/user/questionamento/requisicao/listar.scc" class="ui-state-default"
													decorator="com.claro.sccweb.decorator.view.SccRequisicaoDecorator">
									
										<display:column property="anoMesReferencia" title="Ano/Mes Referencia" style="width:10%; text-align:left"/>
										<display:column property="noRequisicao" title="No Requisicao" style="width:10%; text-align:right;"/>
										<display:column property="dtCriacao" title="Data Criação" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:right;"/>
										<display:column property="dtAtualizacao" title="Data Evento" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:right;"/>
										<display:column property="cdUsuarioManut" title="Usuario" style="width:10%; text-align:right;"/>
									</display:table>
								</td>
							</tr>
						</table>
					</table>
						
					</table>
				
				</table>
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
