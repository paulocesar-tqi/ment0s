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
	$('#tabs').tabs();
	$("#anoRelatorio").mask("9999");	
	$('#cdEOTLD').change(selecionaLD);
	$('#cdProdutoCobilling').change(selecionaProduto);
	
	$('#pesquisar_button').click(function(){
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	});
	
		
});

function selecionaLD()
{
 $("#cdProdutoCobilling").empty().append('<option selected="selected" value="0">Carregando...</option>');
 $("#periodo").empty().append('<option selected="selected" value="0">Selecione...</option>');
 var sel = $("#cdEOTLD option:selected");
 
  $.ajax({   
	 url: "/scc/user/repasse/pos/consulta/json/lista_produtos/"+sel.val()+".scc",	 
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
		 url: "/scc/user/repasse/pos/consulta/json/lista_periodos/"+sel.val()+"/"+eot.val()+".scc",	 
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
</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pos_consulta.filtro.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="repasse_pos_consulta.ld"/>:</td>
    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />
    <form:errors path="cdEOTLD" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pos_consulta.produto"/>:</td>
    <td ><form:select path="cdProdutoCobilling" id="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
    <form:errors path="cdProdutoCobilling" /></td>
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pos_consulta.periodo"/>:</td>
    <td ><form:select path="cdPeriodicidade" id="cdPeriodicidade" items="${periodos}" itemLabel="noPeriodicidadeRepasse" itemValue="cdPeriodicidadeRepasse" />
    <form:errors path="cdPeriodicidade" /></td>
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pos_consulta.mes"/>:</td>
    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" />
    <form:errors path="mesRelatorio" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pos_consulta.ano"/>:</td>
    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
    <form:errors path="anoRelatorio" /></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />    
    </td>
</tr>
</table>
</div>
</form:form>
</div>

