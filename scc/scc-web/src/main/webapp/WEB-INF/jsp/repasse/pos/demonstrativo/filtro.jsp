<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<style>
	.group { zoom: 1 }
</style>

<script>
$(document).ready(function(){
	$("#accordion").accordion({
		header: "> div > h3"		
	});

	$( "#accordion" ).accordion( "option", "collapsible", true );
	
	
	$('#tabs').tabs();
	$("#anoRelatorio").mask("9999");	
	$('#operadoraExterna').change(selecionaLD);
	$('#produtoCobilling').change(selecionaProduto);
	
	$('#pesquisar_button').click(function(){
		$('#operacao').val("PESQUISAR");
		$('#form1').submit();
	});	
	
	$('#excel_button').click(function(){
		$('#operacao').val("EXCEL");
		$('#form1').submit();
	});	
	
});


function selecionarParOperadoras(cdEOT,cdEOTLD)
{
	$('#operacao').val("SOLICITAR");
	$('#cdEOTClaro').val(cdEOT);
	$('#cdEOT').val(cdEOTLD);
	$('#form1').submit();
}





function selecionaLD()
{
 $("#produtoCobilling").empty().append('<option selected="selected" value="0">Carregando...</option>');
 $("#periodo").empty().append('<option selected="selected" value="0">Selecione...</option>');
 var sel = $("#operadoraExterna option:selected");
 
  $.ajax({   
	 url: "/scc/user/repasse/pos/demonstrativo/json/lista_produtos/"+sel.val()+".scc",	 
	 dataType: "json",   success: function(data) 
	   	{     
		var name, select, option;        
	    select = document.getElementById('produtoCobilling');      
	        select.options.length = 0;         
	        for (name in data) 
	           {       
	           if (data.hasOwnProperty(name)) {         
			select.options.add(new Option(data[name], name));  
	            }}}});
    
}

function selecionaProduto()
{
	
	var sel = $("#produtoCobilling option:selected");
	var eot = $("#operadoraExterna option:selected");	
	$("#periodo").empty().append('<option selected="selected" value="0">Carregando...</option>');
	$.ajax({   
		 url: "/scc/user/repasse/pos/demonstrativo/json/lista_periodos/"+sel.val()+"/"+eot.val()+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('periodo');      
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
<li><a href="#tabs-1"><spring:message code="repasse_pos_demonstrativo.filtro.titulo"/></a></li>
<li><a href="#tabs-2"><spring:message code="repasse_pos_demonstrativo.resultado.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="executa.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="cdEOTClaro" id="cdEOTClaro"/>
<form:hidden path="cdEOT" id="cdEOT"/>
<form:hidden path="cdProdutoCobilling" id="cdProdutoCobilling"/>
<form:hidden path="cdPeriodicidadeRepasse" id="cdPeriodicidadeRepasse"/>
<form:hidden path="dtInicialPeriodo" id="dtInicialPeriodo"/>
<form:hidden path="dtFinalPeriodo" id="dtFinalPeriodo"/>


<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.claro"/>:</td>
    <td ><form:select path="operadoraClaro" id="operadoraClaro" items="${holdingClaro}" itemLabel="dsOperadora" itemValue="cdEot" />
    <form:errors path="operadoraClaro" /></td>
</tr>
<tr>
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.ld"/>:</td>
    <td ><form:select path="operadoraExterna" id="operadoraExterna" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" />
    <form:errors path="operadoraExterna" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pos_demonstrativo.produto"/>:</td>
    <td ><form:select path="produtoCobilling" id="produtoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
    <form:errors path="produtoCobilling" /></td>
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.periodo"/>:</td>
    <td ><form:select path="periodo" id="periodo" items="${periodos}" itemLabel="noPeriodicidadeRepasse" itemValue="cdPeriodicidadeRepasse" />
    <form:errors path="periodo" /></td>
</tr>
<tr>    
    <td width="10%"><spring:message code="repasse_pos_demonstrativo.mes"/>:</td>
    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" />
    <form:errors path="mesRelatorio" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="repasse_pos_demonstrativo.ano"/>:</td>
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

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr><td>

<display:table style="width: 100%;" name="sessionScope._DISPLAY_TAG_SPACE_1" decorator="com.claro.sccweb.decorator.ParOperadorasRepassePosDecorator" pagesize="25"  id="par_operadoras" requestURI="/scc/user/repasse/pos/demonstrativo/tab1.scc" class="ui-state-default">
<display:column property="operadoraClaro" title="Operadora Claro" />
<display:column property="operadoraExterna" title="Operadora LD" />
<display:column property="criacaoContrato" title="Data Início Contrato" />
<display:column property="finalVigencia" title="Final da Vigência" />
</display:table>
</td></tr>
</table>
</div>
<div id="tabs-2" style="height: 500px;">
<div id="accordion">	
	<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.operadora"/></a></h3>	
		<div>
		<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.descricao"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.chamadas"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.minutos"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.liquido"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.pis"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.cofins"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.icms"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.iss"/></th>
		<th align="left"><spring:message code="repasse_pos_demonstrativo.coluna.bruto"/></th>
		</tr>	
		<c:forEach var="item" items="${DEMONSTRATIVO_OPERADORA}" varStatus="rowCounter">
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td><c:out value="${item.descricao}"/></td>
		 <td>${item.chamadas}</td>
		 <td>${item.minutos}</td>
		 <td>${item.liquido}</td>
		 <td>${item.pis}</td>
		 <td>${item.cofins}</td>
		 <td>${item.icms}</td>
		 <td>${item.iss}</td>
		 <td>${item.bruto}</td>		 
		 </tr>
		 </c:forEach>

		
		</table>
		</div>
	</div>
	
	<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.holding"/></a></h3>
	<div>	
		<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.descricao"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.chamadas"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.minutos"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.liquido"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.pis"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.cofins"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.icms"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.iss"/></th>
		<th><spring:message code="repasse_pos_demonstrativo.coluna.bruto"/></th>
		</tr>
		 <c:forEach var="item" items="${DEMONSTRATIVO_HOLDING}" varStatus="rowCounter">		 
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td>${item.descricao}</td>
		 <td>${item.chamadas}</td>
		 <td>${item.minutos}</td>
		 <td>${item.liquido}</td>
		 <td>${item.pis}</td>
		 <td>${item.cofins}</td>
		 <td>${item.icms}</td>
		 <td>${item.iss}</td>
		 <td>${item.bruto}</td>		 
		 </tr>
		 </c:forEach>
		</table>
		</div>
	</div>	


<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.assinatura"/></a></h3>	
		<div>
		<table style="width: 90%; border: 1px;"  class="ui-state-default">
		<tr>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.operadora"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.qtFaturasAssinadas"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.qtChamadas"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.minutagem"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.valorSemDesconto"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.desconto"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.valorFaturado"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.valorArrecadadoDesconto"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.valorArrecadadoInadimplente"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.contestado"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.reversao"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.custo"/></th>
		<th><spring:message code="repasse_pos_demonstrativo_assinatura.repasse"/></th>
		</tr>	
		<c:forEach var="item" items="${DEMONSTRATIVO_ASSINATURAS}" varStatus="rowCounter">		 
		 <c:choose>
          <c:when test="${rowCounter.count % 2 == 0}">
            <c:set var="rowStyle" scope="page" value="odd"/>
          </c:when>
          <c:otherwise>
            <c:set var="rowStyle" scope="page" value="even"/>
          </c:otherwise>
        </c:choose>		 
		 <tr class="${rowStyle}">
		 <td>${item.operadora}</td>
		 <td>${item.qtFaturasAssinadas}</td>
		 <td>${item.qtChamadas}</td>		 
		 <td>${item.minutagem}</td>
		 <td>${item.valorSemDesconto}</td>
		 <td>${item.desconto}</td>
		 <td>${item.valorFaturado}</td>
		 <td>${item.valorArrecadadoDesconto}</td>
		 <td>${item.valorArrecadadoInadimplente}</td>
		 <td>${item.contestado}</td>	
		 <td>${item.reversao}</td>
		 <td>${item.custo}</td>
		 <td>${item.repasse}</td>		 
		 </tr>
		 </c:forEach>
		
		</table>
		</div>
	</div>

<div class="group">
	<h3><a href="#"><spring:message code="repasse_pos_demonstrativo.resultado.juros_multas"/></a></h3>	
		<div>				
		<display:table style="width: 100%;"  name="sessionScope._DISPLAY_TAG_SPACE_2"  requestURI="/scc/user/repasse/pos/demonstrativo/tab2.scc" pagesize="25"  id="juros_multas_tab"  class="ui-state-default">
		<display:column property="link" title="Selecionar" />
		<display:column property="CSP" title="CSP" />
		<display:column property="operadoraClaro" title="Operadora Claro" />
		<display:column property="UF" title="UF" />
		<display:column property="fatura" title="Fatura" />
		<display:column property="dataArrecadacao" title="Dt. Arrecadação" />
		<display:column property="dataVencimento" title="Dt. vencimento" />
		<display:column property="valorJuros" title="Valor Juros" />
		<display:column property="valorMulta" title="Valor Multa" />
		<display:column property="total" title="Total" />
		<display:column property="dsName" title="DSNAME" />		
		</display:table>		
		</div>
	</div>

</div>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />    
    </td>
</tr>
</table>
</div>
</div>

</form:form>
<script>
$(function() {
	var op = $('#operacao').val();
	if (op == 'SOLICITAR'){
		$('#tabs').tabs('select',1);
	}
	
	if (op == 'REFRESH'){
		$('#tabs').tabs('select',1);
		$("#accordion").accordion( "activate" , 3);
	}
});
</script>