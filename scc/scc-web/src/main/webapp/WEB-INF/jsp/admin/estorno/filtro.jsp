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
	$('#ano').mask("9999");	
	$('#salvar_button').click(salvar);
	$('#tabs').tabs();
});




function salvar()
{
	var r=confirm("Salvar?");
	if (r==true)
		{		
		$('#salvar_button').attr('disabled', 'disabled');
		$('#operacao').val("salvar");	
		$('#form1').submit();	
		}
}


function num(dom){
    dom.value=dom.value.replace(/\D/g,""); 
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1">Nova Requisição</a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="mensagem" id="mensagem"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
<td width="15%">Operacora Externa:</td> 
<td><form:select path="cdEOTLD" itemValue="cdEot" itemLabel="dsOperadora" items="${operadorasExternas}"/></td>
</tr>

<tr>
<td width="15%">Operadora Claro:</td>
<td> <form:select path="cdEOTClaro" itemValue="cdEot" itemLabel="dsOperadora" items="${operadorasClaro}"/></td>
</tr>

<tr>
<td width="15%">Mês:</td>
<td> <form:select path="mes" itemValue="key" itemLabel="label" items="${meses}"/></td>
</tr>

<tr>
<td width="15%">Ano</td>
<td><form:input path="ano" size="5" maxlength="4"/><form:errors path="ano"/> </td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">            
    <input id="salvar_button" type="button" value=Salvar />
    </td>
</tr>
</table>

</div>


</form:form>
</div>

<script>
$(function() {
	$('#salvar_button').removeAttr('disabled');
	var op = $('#operacao').val();
	if (op == 'salvar')
			{
			alert($('#mensagem').val());
			}
});
</script>
