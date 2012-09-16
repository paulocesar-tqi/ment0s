<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>


<script>
$(document).ready(function(){	
	$("#dataInicio").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");
	$( "#dataFinal" ).datepicker();
	$( "#dataInicio" ).datepicker();
	$('#tabs').tabs();
	});
</script>

<div id="tabs">
<ul>
	<li><a href="#tabs-1"><spring:message code="comum.titulo.filtro"/></a></li>
</ul>
<div id="tabs-1">
<form:form modelAttribute="filtro" method="post" action="execute.scc">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
		    <td width="10%"><spring:message code="dominio.tipo_arquivo"/>:</td>
		    <td><form:select path="tipoArquivo" items="${tiposArquivo}" itemLabel="label" itemValue="key" /></td>
		</tr>
		
		<tr>
		    <td width="10%"><spring:message code="dominio.status_arquivo"/>:</td>
		    <td ><form:select path="statusArquivo" items="${statusArquivo}" itemLabel="label" itemValue="key" /></td>
		</tr>
		
		<tr>
			<td width="10%"><spring:message code="controle.arquivos.nome_arquivo"/>:</td>
			<td><form:input id="nomeArquivo" path="nomeArquivo"  size="100"  maxlength="150"/></td>
		</tr>
		
		<tr>
		    <td width="10%"><spring:message code="comum.filtro.data_inicial"/>:</td>
		    <td><form:input id="dataInicio" path="dataInicio" /><form:errors path="dataInicio" /></td>
		</tr>
		
		<tr>
		    <td width="10%"><spring:message code="controle.arquivos.resultado.dtFim"/>:</td>
		    <td><form:input id="dataFinal" path="dataFinal" /> <form:errors path="dataFinal" /> </td>
		</tr>
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
		    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">    
		    	<input type="button" value=<spring:message code="comum.botao.voltar"/> />
		    	<input type="submit" value=<spring:message code="comum.botao.pesquisar"/> />    
		    </td>
		</tr>
	</table>
</form:form>
</div>
</div>

