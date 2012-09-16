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
	$( "#dataInicial" ).datepicker();
	$( "#dataFinal" ).datepicker();	
	$("#dataFinal").mask("99/99/9999");	
	$("#dataInicial").mask("99/99/9999");
	$('#inserir_button').click(inserir);	
	$('#tabs').tabs();
});

function num(dom){
    dom.value=dom.value.replace(/\D/g,""); 
}

function financeiro(dom){
	dom.value=dom.value.replace(/\D/g,"");
	dom.value=dom.value.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/,"$1.$2");    
	dom.value=dom.value.replace(/(\d)(\d{2})$/,"$1.$2");
}

	
function inserir()
{
	var r=confirm("Gravar Solicitação?");
	if (r==true)
		{		
		$('#operacao').val("inserir");	
		$('#form1').submit();	
		}
}



</script>



<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivo/suspeito/solicitacao/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="resultado" id="resultado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
<td width="15%">Minutos:</td><td><form:input path="minutos" id="minutos" size="7" maxlength="6" onkeyup="num(this)"/><form:errors path="minutos"/></td>
</tr>
 
<tr>
<td width="15%">Valor Bruto:</td><td><form:input path="valorBruto" id="valorBruto" size="11" maxlength="10" onkeyup="financeiro(this)"/><form:errors path="valorBruto"/></td>
</tr>

<tr>
<td width="15%">Data Inicial:</td><td> <form:input path="dataInicial"/><form:errors path="dataInicial"/> </td>
</tr>

<tr>
<td width="15%">Data Final:</td><td><form:input path="dataFinal"/><form:errors path="dataFinal"/></td>
</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="inserir_button" type="button" value="Inserir" />
    </td>
</tr>
</table>
</div>

</form:form>
</div>
<script>
$(document).ready(function(){
	if ($( "#resultado" ).val() == 'OK')
	{
		alert("Solicitação criada com sucesso");
		$( "#resultado" ).val(" ");
	}
	
});
</script>
