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
	$('#atualizar_button').hide();	
	$('#excel_button').click(excel);	
	$('#pesquisar_button').click(pesquisar);	
	$("#dataInicial").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");
	
	$( "#dataFinal" ).datepicker();
	$( "#dataInicial" ).datepicker();
	
	$("#tipoOperadora").change(trocaTipoOperadora); 
	$('#tabs').tabs();
});


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

function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function excel()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");	
	$('#form1').submit();
}
	


</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/arquivo/controle/cdr/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="15%">Tipo de Operadora:</td>
    <td ><form:select path="tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
    <td width="15%">Operadora Claro:</td>    
    <td ><form:select id="comboOperadora" path="cdEOTClaro"  items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /></td>    
</tr>

<tr>
    <td width="15%">Operadora LD:</td>    
    <td id="cdEOTLD"><form:select path="cdEOTLD" id ="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" /></td>    
</tr>


<tr>
    <td width="10%">Data Inicial:</td>
    <td><form:input id="dataInicial" path="dataInicial" />
    <form:errors path="dataInicial" /></td>
</tr>

<tr>
    <td width="10%">Data Final:</td>
    <td><form:input id="dataFinal" path="dataFinal" />
    <form:errors path="dataFinal" />
    </td>
</tr>

</table>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap"><input id="pesquisar_button" type="button" value="Pesquisar" /></td>
				</tr>
			</table>
			<br />

			<!-- 
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" decorator="com.claro.sccweb.decorator.RelCDRPrePagoViewDecorator" pagesize="50" id="repasses" requestURI="/scc/user/arquivo/controle/cdr/tab1.scc" class="ui-state-default">
							<display:column property="operadoraLD" title="Operadora LD" />
							<display:column property="operadoraClaro" title="Operadora Claro" />
							<display:column property="uf" title="UF" />
							<display:column property="dataChamada" title="Data da Chamada" />
							<display:column property="dataCarga" title="Data da Apuração" />
							<display:column property="dataFechamento" title="Data do fechamento" />
							<display:column property="cnAssinante" title="CN do Assinante" />
							<display:column property="origemChamada" title="Origem da Chamada" />
							<display:column property="operadoraOrigem" title="Operadora de Origem" />
							<display:column property="codigoDefeito" title="Código de Defeito" />
							<display:column property="grupoHorario" title="Grupo Horário" />
							<display:column property="tipoChamada" title="Tipo de Chamada" />
							<display:column property="statusRegistro" title="Status" />
						</display:table>
					</td>
				</tr>
			</table>
 			-->

			<c:if test="${filtro.mostrarSomatorio}">
				<h3>Totais</h3>
				<table width="90%" border="0" cellspacing="0" cellpadding="0"
					class="ui-state-default">
					<tr>
						<td width="15%">Quantidade de CDRs:</td>
						<td>${filtro.quantidadeCDRs}</td>
					</tr>
					<tr>
						<td width="15%">Minutos Reais:</td>
						<td>${filtro.minutosReais}</td>
					</tr>
					<tr>
						<td width="15%">Minutos Tarifados:</td>
						<td>${filtro.minutosTarifados}</td>
					</tr>
					<tr>
						<td width="15%">Valor Bruto:</td>
						<td>${filtro.valorBruto}</td>
					</tr>
				</table>
			</c:if>

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
