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

function selecionaLD()
{
 $("#cdProdutoCobilling").empty().append('<option selected="selected" value="0">Carregando...</option>');
 $("#periodo").empty().append('<option selected="selected" value="0">Selecione...</option>');
 var sel = $("#cdEOTLD option:selected");
 
  $.ajax({   
	 url: "/scc/user/repasse/pos/retorno/json/lista_produtos/"+sel.val()+".scc",	 
	 dataType: "json",   success: function(data) 
	   	{     
		var name, select, option;        
	    select = document.getElementById('cdProdutoCobilling');      
	        select.options.length = 0;         
	        for (name in data) 
	           {       
	           if (data.hasOwnProperty(name)) {         
			select.options.add(new Option(data[name], name));  
	            }}}});
    
}

function selecionaProduto()
{
	
	var sel = $("#cdProdutoCobilling option:selected");
	var eot = $("#cdEOTLD option:selected");	
	$("#cdPeriodicidade").empty().append('<option selected="selected" value="0">Carregando...</option>');
	$.ajax({   
		 url: "/scc/user/repasse/pos/retorno/json/lista_periodos/"+sel.val()+"/"+eot.val()+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('cdPeriodicidade');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}});
	
}

$(document).ready(function(){	
	$("#anoRelatorio").mask("9999");	
	$('#cdEOTLD').change(selecionaLD);
	$('#cdProdutoCobilling').change(selecionaProduto);		
	$('#pesquisar_button').click(pesquisar)
	$('#excel_button').click(excel)
	$("#tipoOperadora").change(trocaTipoOperadora); 
	$('#tabs').tabs();
});

function pesquisar() {
	$('#excel_button').attr('disabled', 'disabled');
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function excel() {
	$('#operacao').val("excel");	
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
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Tipo Operadora:</td>
<td><form:select path="tipoOperadora" id="tipoOperadora" items="${tiposOperadora}" itemLabel="label" itemValue="key" /></td>
</tr>

<tr>
<td width="15%">Operadora Claro:</td>
<td><form:select path="cdEOTClaro" id="comboOperadora" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
</tr>

<tr>
<td width="15%">Operadora Externa:</td>
<td><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
</tr>

<tr>
	<td width="15%"><spring:message code="repasse_pos_consulta.produto"/>:</td>
    <td ><form:select path="cdProdutoCobilling" id="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
    <form:errors path="cdProdutoCobilling" /></td>
</tr>
<tr>    
    <td width="15%"><spring:message code="repasse_pos_consulta.periodo"/>:</td>
    <td ><form:select path="cdPeriodicidade" id="cdPeriodicidade" items="${periodos}" itemLabel="noPeriodicidadeRepasse" itemValue="cdPeriodicidadeRepasse" />
    <form:errors path="cdPeriodicidade" /></td>
</tr>
<tr>    
    <td width="15%"><spring:message code="repasse_pos_consulta.mes"/>:</td>
    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" />
    <form:errors path="mesRelatorio" /></td>
</tr>
<tr>
	<td width="15%"><spring:message code="repasse_pos_consulta.ano"/>:</td>
    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
    <form:errors path="anoRelatorio" /></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="pesquisar_button" type="button" value="Pesquisar" />
    <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
    <input id="excel_button" type="button" value="Excel" />
    </c:if>
    </td>
</tr>
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td align="center">                            
<display:table style="width:100%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/arquivo/batimento/interface/tab1.scc" class="ui-state-default">
<display:column property="nomeArquivo" title="Nome do Arquivo" />
<display:column property="operadoraLD" title="Op. LD" />
<display:column property="operadoraClaro" title="Op. Claro" />
<display:column property="dataMovimentacao" title="Data de Movimentação" />
<display:column property="dataTransferencia" title="Data de Transferência" />
<display:column property="quantidadeRegistrosMobile" title="Quantidade de Registros" />
<display:column property="dataProcessamento" title="Data de Processamento" />
<display:column property="quantidadeRegistrosScc" title="Quantidade de Registros" />
<display:column property="diferenca" title="Diferença" />
<display:column property="status" title="Status" />
</display:table>
</td></tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">

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