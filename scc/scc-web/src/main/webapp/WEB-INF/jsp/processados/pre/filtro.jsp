<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>


<script>
$(document).ready(function(){
	$(document).ready(function(){	
		$("#dataInicialPeriodo").mask("99/99/9999");	
		$("#dataFinalPeriodo").mask("99/99/9999");
		$( "#dataFinalPeriodo" ).datepicker();
		$( "#dataInicialPeriodo" ).datepicker();
		$("#comboHoldingClaro").hide();
		$("#tipoOperadora").change(trocaTipoOperadora); 
		$('#tabs').tabs();		
		});
});

function pesquisar()
{
	$('#excel_button').attr('disabled', 'disabled');
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function trocaTipoOperadora()
{
var sel = $("#tipoOperadora option:selected");
if (sel.val() == "OP")
	mostraOperadoraClaro();
else
	mostraHoldingClaro();
}

function mostraHoldingClaro()
{
	$.ajax({   
		 url: "/scc/user/recepcao_transmissao/holding/json.scc",  
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('comboOperadora');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}}); 
}

function mostraOperadoraClaro()
{
	$.ajax({   
		 url: "/scc/user/recepcao_transmissao/operadoras/json.scc",  
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('comboOperadora');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}}); 
}
</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.titulo.filtro"/></a></li>
</ul>
<div id="tabs-1">
<form:form modelAttribute="filtro" method="post" action="execute.scc">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.tipoArquivo"/>:</td>
    <td ><form:select path="tipoArquivo" items="${tiposArquivo}" itemLabel="dsTipoArquivo" itemValue="cdTipoArquivo" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.statusArquivo"/>:</td>
    <td ><form:select path="statusArquivo" items="${statusArquivo}" itemLabel="dsStatusArquivo" itemValue="cdStatusArquivo" /></td>
</tr>


<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.dataInicio"/>:</td>
    <td><form:input id="dataInicialPeriodo" path="dataInicialPeriodo" />
    <form:errors path="dataInicialPeriodo" /></td>
</tr>

<tr>
    <td width="10%"><spring:message code="recepcao_transmissao.dataFinal"/>:</td>
    <td><form:input id="dataFinalPeriodo" path="dataFinalPeriodo" />
    <form:errors path="dataFinalPeriodo" />
    </td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">    
    <input type="button" id="excel_button" value="Excel" />
    <input type="button" id="pesquisar_button" value=<spring:message code="comum.botao.pesquisar"/> />    
    </td>
</tr>
</table>
</form:form>
</div>
</div>
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
