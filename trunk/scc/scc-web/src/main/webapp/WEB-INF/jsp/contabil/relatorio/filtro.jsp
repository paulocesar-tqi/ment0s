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
	$("#tipoOperadora").change(trocaTipoOperadora); 
	$('#excel_button').click(excel);
	$('#gerar_button').click(gerar);
	$("#ano").mask("9999");
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

function gerar()
{
	$('#gerar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("gerar");	
	$('#form1').submit();
}

function excel()
{	
	$('#operacao').val("excel");	
	$('#form1').submit();
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/contabil/relatorio/execute.scc" id="form1">
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
    <td><form:select path="cdEOTLD" id ="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" /></td>    
</tr>

<tr>
    <td width="15%">Motivo:</td>    
    <td><form:select path="cdMotivoRejeicao" id ="cdMotivoRejeicao" items="${motivos}" itemLabel="label" itemValue="key" /></td>    
</tr>

<tr>
    <td width="15%">M�s:</td>    
    <td><form:select path="mes" id ="mes" items="${meses}" itemLabel="label" itemValue="key" /></td>    
</tr>

<tr>
	<td width="10%">Ano:</td>
    <td ><form:input path="ano" id="ano" size="4" maxlength="4"/>
    <form:errors path="ano" /></td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="gerar_button" type="button" value="Gerar" />
    <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
    <input id="excel_button" type="button" value="Exportar" />
    </c:if>
    </td>
</tr>
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/contabil/relatorio/tab1.scc" class="ui-state-default">
<display:column property="row.cdEOTLD" title="Longa Dist�ncia"/>
<display:column property="row.cdEOTClaro" title="Empresa Claro"/>
<display:column property="row.dataFechamento" title="M�s/Ano"/>
<display:column property="row.descricao" title="Descri��o"/>
<display:column property="row.cdContabil" title="C�digo Cont�bil"/>
<display:column property="qtCdrs" title="Qtde."/>
<display:column property="vlLiquido" title="Valor L�quido"/>
<display:column property="vlBruto" title="Valor Bruto"/>
</display:table>
</td></tr>
</table>


</div>

</form:form>
</div>
<script>
$(document).ready(function(){
	$('#gerar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
