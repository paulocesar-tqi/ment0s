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
	$("#dataInicial").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");	
	$( "#dataFinal" ).datepicker();
	$( "#dataInicial" ).datepicker();
	$('#gerar_button').click(gerar);	
	$("#tipoOperadora").change(trocaTipoOperadora); 
	$('#tabs').tabs();
});



function gerar()
{			
	$('#gerar_button').attr('disabled', 'disabled');	
	$('#operacao').val("gerar");
	$('#form1').submit();		
}

function trocaTipoOperadora()
{
var sel = $("#tipoOperadora option:selected");
if (sel.val() == "O")
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
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/cdr/distribuicao/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="dadosDisponiveis" id="dadosDisponiveis"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
    <td width="15%">Tipo de Operadora:</td>
    <td ><form:select path="tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
<td width="15%">Operadora Claro:</td>
<td> <form:select id="comboOperadora" path="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot"></form:select> </td>
</tr>

<tr>
<td width="15%">Operadora LD:</td>
<td> <form:select path="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot"></form:select> </td>
</tr>


<tr>
<td width="15%">Data Inicial:</td>
<td> <form:input path="dataInicial"/><form:errors path="dataInicial"></form:errors> </td>
</tr>

<tr>
<td width="15%">Data Final:</td>
<td> <form:input path="dataFinal"/><form:errors path="dataFinal"></form:errors> </td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="gerar_button" type="button" value="Pesquisar" />    
    </td>
</tr>
</table>
<br/>

<c:if test="${!empty confirmacao}"> 

    <c:if test="${confirmacao == 'SIM'}">
   
		<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
			<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
			 <tr><td>                            
			<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="40"  id="cdrs" requestURI="/scc/user/cdr/distribuicao/tab1.scc" class="ui-state-default">
			<display:column property="statusComposto" title="Status"/>
			<display:column property="quantidade" title="Quantidade"/>
			<display:column property="valorLiquido" title="Vlr. Líquido"/>
			<display:column property="valorBruto" title="Vlr. Bruto"/>
			<display:column property="duracaoTarifada" title="Duração Tarifada"/>
			<display:column property="metrica" title="Métrica"/>
			</display:table>
			</td></tr>
			</table>
		</c:if>
		
		<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_2}">
			<br/>
			<center><img src="/scc/user/cdr/distribuicao/grafico.scc" width='550'  border='1'/></center>
		</c:if>
		
		<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_3}">
			<br/>
			<center>
			<display:table  style="width:550px"  name="sessionScope._DISPLAY_TAG_SPACE_3"   pagesize="6"  id="cdrs" requestURI="/scc/user/cdr/distribuicao/tab1.scc" class="ui-state-default">
			<display:column property="mesAno" title="Mês/Ano"/>
			<display:column property="quantidade" title="Quantidade"/>
			</display:table>
			</center>
		</c:if>
		
		<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_4}">
			<br/>
			<center>
			<display:table  style="width:550px"  name="sessionScope._DISPLAY_TAG_SPACE_4"   pagesize="6"  id="cdrs" requestURI="/scc/user/cdr/distribuicao/tab1.scc" class="ui-state-default">
			<display:column property="mesAno" title="Mês/Ano"/>
			<display:column property="quantidade" title="Total"/>
			<display:column property="metrica" title="% Fat Acum"/>
			</display:table>
			</center>
		</c:if>
	</c:if>
</c:if>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td></td>
	</tr>
</table>

</div>

</form:form>
</div>
<script>
$(document).ready(function() {
	$('#gerar_button').removeAttr('disabled');
});
</script>
