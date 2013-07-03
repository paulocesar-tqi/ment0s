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
	$('#excel_button').click(excel);
	$("#anoFinal").mask("9999");	
	$("#anoInicio").mask("9999");
	$('#tabs').tabs();
});

	

function excel()
{		
	$('#operacao').val("excel");	
	$('#form1').submit();		
}

function gerar()
{
	$('#gerar_button').attr('disabled', 'disabled');	
	$('#operacao').val("gerar");	
	$('#form1').submit();
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/saldo/demonstrativo/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Operadora Claro:</td>
<td> <form:select path="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
</tr>


<tr>
<td width="15%">Operadora LD:</td>
<td> <form:select path="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
</tr>


<tr>
<td width="15%">Produto:</td>
<td> <form:select path="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling"></form:select> </td>
</tr>

<tr>
<td width="15%">Tipo do Arquivo:</td>
<td> <form:select path="cdTipoArquivo" items="${tiposArquivo}" itemLabel="label" itemValue="key"></form:select> </td>
</tr>

<tr>
<td width="15%">Mês Início:</td>
<td> <form:select path="mesInicio" items="${meses}" itemLabel="label" itemValue="key"/></td>
</tr>

<tr>
<td width="15%">Ano Início:</td>
<td> <form:input path="anoInicio" id="anoInicio" size="5" maxlength="4"/><form:errors path="anoInicio"></form:errors> </td>
</tr>



<tr>
<td width="15%">Mês Final:</td>
<td> <form:select path="mesFinal" items="${meses}" itemLabel="label" itemValue="key"/></td>
</tr>

<tr>
<td width="15%">Ano Final:</td>
<td> <form:input path="anoFinal" id="anoFinal" size="5" maxlength="4"/><form:errors path="anoFinal"></form:errors> </td>
</tr>


</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="gerar_button" type="button" value="Gerar" />
    <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
    </c:if>
    </td>
</tr>
</table>
<br/>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th align="left">Evento</th>
		<th align="left">COD</th>
		<th align="left">Motivo</th>
		<th align="left">CDRs</th>
		<th align="left">%CDRs</th>
		<th align="left">Minutos</th>
		<th align="left">%Min.</th>
		<th align="left">Valor</th>
		<th align="left">%Valor</th>
		</tr>	
		<c:forEach var="item" items="${sessionScope._DISPLAY_TAG_SPACE_1}" varStatus="rowCounter">
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td><c:out value="${item.evento}"/></td>
		 <td>${item.codigo}</td>
		 <td>${item.motivo}</td>
		 <td>${item.qtCdrs}</td>
		 <td>${item.perCdrs}</td>
		 <td>${item.qtMinutos}</td>
		 <td>${item.perMinutos}</td>
		 <td>${item.valor}</td>
		 <td>${item.perValor}</td>		 
		 </tr>
		 </c:forEach>

		
		</table>
</td></tr>
</table>
</c:if>

</div>

</form:form>
</div>
<script>
$(document).ready(function(){
	$('#gerar_button').removeAttr('disabled');	
});
</script>
