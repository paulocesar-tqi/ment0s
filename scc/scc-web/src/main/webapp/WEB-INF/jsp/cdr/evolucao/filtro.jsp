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
	$('#excel_button').click(excel);	
	$('#gerar_button').click(gerar);
	$("#tipoOperadora").change(trocaTipoOperadora);
	$("#anoInicial").mask("9999");	
	$("#anoFinal").mask("9999");	
	$('#tabs').tabs();
});

	

function excel()
{			
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");
	$('#form1').submit();		
}

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
<form:form modelAttribute="filtro" method="post" action="/scc/user/cdr/evolucao/execute.scc" id="form1">
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
    <td ><form:select id="comboOperadora" path="cdEOTClaro"  items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>    
</tr>

<tr>
    <td width="15%">Operadora LD:</td>    
    <td id="cdEOTLD"><form:select path="cdEOTLD" id ="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>    
</tr>

<tr>
    <td width="15%">Produto:</td>    
    <td ><form:select id="comboProduto" path="cdProdutoCobilling"  items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" /></td>    
</tr>

<tr>    
    <td width="10%">Mês Início:</td>
    <td ><form:select path="mesInicial" id="mesInicial" items="${meses}" itemLabel="label" itemValue="key" />
    <form:errors path="mesInicial" /></td>
</tr>
<tr>
	<td width="10%">Ano Início:</td>
    <td ><form:input path="anoInicial" id="anoInicial" size="4" maxlength="4"/>
    <form:errors path="anoInicial" /></td>
</tr>

<tr>    
    <td width="10%">Mês Fim:</td>
    <td ><form:select path="mesFinal" id="mesFinal" items="${meses}" itemLabel="label" itemValue="key" />
    <form:errors path="mesFinal" /></td>
</tr>
<tr>
	<td width="10%">Ano Fim:</td>
    <td ><form:input path="anoFinal" id="anoFinal" size="4" maxlength="4"/>
    <form:errors path="anoFinal" /></td>
</tr>
<tr>
	<td width="10%">Selecione os Tipos de Arquivo:</td>
	<td></td>
</tr>
<tr>
	<td width="10%">&nbsp;</td>
	<td>
		<table width="60%">
			<tr>
				<c:forEach var="item" items="${gruposStatus}" varStatus="rowCounter">
					<td><form:checkbox path="lstCdr" value="${item.statusConcretosString}" label="${item.descricao}" checked="true" /></td>
					<c:choose>
						<c:when test="${rowCounter.count % 2 == 0}">
							</tr><tr>
						</c:when>
					</c:choose>
				</c:forEach>
			</tr>
		</table>
	</td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="gerar_button" type="button" value="Gerar" />
    </td>
</tr>
</table>
<br/>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  style="width:90%"  width="90%" border="1" cellspacing="0" cellpadding="0" class="ui-state-default">
 <tr>                            

<c:forEach items="${sessionScope._DISPLAY_TAG_SPACE_1}" var="periodo">
<td>
<table width="100%">
<tr><th colspan="3" align="center">${periodo.mesAno}</th></tr>
<tr>
	 
	 <th align="left">Aceito</th>
	 <th align="left">${periodo.aceitos}</th>
 	 <th align="left">100%</th>
</tr>

<c:forEach items="${periodo.cdrs}" var="grupo" varStatus="rowCounter">
<c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		

<tr class="${rowStyle}">
<td>${grupo.descricao}</td>
<td>${grupo.quantidadeCDR}</td>
<td>${grupo.proporcaoCDR}</td>
</tr>
</c:forEach>
</table>
</td>
</c:forEach>
</tr>
</table>
</c:if>
</br>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">

<table width="100%" height="50" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<img src="/scc/user/cdr/evolucao/grafico/rejeitado/qt.scc" width='90%' border='1'/>
		</td>
		<td>
			<img src="/scc/user/cdr/evolucao/grafico/encaminhado_reciclar/qt.scc" width='90%' border='1'/>
		</td>
	</tr>
	<tr>
		<td>
			<img src="/scc/user/cdr/evolucao/grafico/faturado/qt.scc" width='90%' border='1'/>
		</td>
		<td>
			<img src="/scc/user/cdr/evolucao/grafico/aceitos/qt.scc" width='90%' border='1'/>
		</td>
	</tr>
</table>
</c:if>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="excel_button" type="button" value="Gerar PDF" />
    </td>
</tr>
</table>
</c:if>
</div>

</form:form>
</div>
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
