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
	$('#pesquisar_button').click(pesquisar);
	$('#excel_button').click(excel)
	$("#dataInicio").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");
	$("#dataFinal" ).datepicker();
	$("#dataInicio" ).datepicker();
	$('#tabs').tabs();
	});

function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");
	$('#form1').submit();
}

 function excel() {
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");
	$('#form1').submit();		
		
}

function gerarExcel() {
	var dados = $("#form1").serialize();
		
	$.ajax({
		type: "POST",
		url : "/scc/user/controle/arquivos/excel.scc",
		
		data: dados,
		success : function(data) {
			//$("#form1").html(data);
		}
	});
	
}
 
</script>

<html>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1"><spring:message code="comum.titulo.filtro"/></a></li>
		</ul>
		<form:form modelAttribute="filtro" method="post" action="/scc/user/controle/arquivos/execute.scc" id="form1">
			<form:hidden path="operacao" id="operacao" />
			<div id="tabs-1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
					    <td width="10%"><spring:message code="dominio.tipo_arquivo"/>:</td>
					    <td><form:select path="filtro.tipoArquivo" items="${tiposArquivo}" itemLabel="label" itemValue="key" /></td>
					</tr>
					
					<tr>
					    <td width="10%"><spring:message code="dominio.status_arquivo"/>:</td>
					    <td ><form:select path="filtro.statusArquivo" items="${statusArquivo}" itemLabel="label" itemValue="key" /></td>
					</tr>
					
					<tr>
						<td width="10%"><spring:message code="controle.arquivos.nome_arquivo"/>:</td>
						<td><form:input id="nomeArquivo" path="filtro.nomeArquivo"  size="100"  maxlength="150"/></td>
					</tr>
					
					<tr>
					    <td width="10%"><spring:message code="comum.filtro.data_inicial"/>:</td>
					    <td><form:input id="dataInicio" path="filtro.dataInicio" />
					    	<form:errors path="filtro.dataInicio" />
					    </td>
					</tr>
					
					<tr>
					    <td width="10%"><spring:message code="controle.arquivos.resultado.dtFim"/>:</td>
					    <td><form:input id="dataFinal" path="filtro.dataFinal" /> 
					    	<form:errors path="filtro.dataFinal" /> 
					    </td>
					</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td colspan="3" class="TdFormularioUp">&nbsp;</td>
						<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
							<input id="pesquisar_button" class="btn_pesquisar" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
							<input id="excel_button" type="button" class="excel" value=<spring:message code="comum.botao.excel"/> />
						</td>
					</tr>
				</table>
				
				<br />
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<display:table style="width:90%" name="requestScope.filtro.listControlConnectFiles" pagesize="20" id="repasses" 
											requestURI="${pageContext.request.contextPath}/user/controle/arquivos/execute.scc"
											class="ui-state-default" export="false">
								<display:column property="ORIG_FILE" title="Nome do Arquivo" style="width:10%; text-align:left"/>
								<display:column property="pk.STRT_DATE" title="Data de Início" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:center"/>
								<display:column property="STOP_DATE" title="Data de Fim" format="{0,date,dd-MM-yyyy}"   style="width:10%; text-align:center"/>
								<display:column property="EXIT_DESC" title="Info" style="width:10%; text-align:center"/>
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
	
</html>	

