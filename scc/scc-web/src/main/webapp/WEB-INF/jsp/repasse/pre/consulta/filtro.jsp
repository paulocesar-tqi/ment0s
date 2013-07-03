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
	
	$('#pesquisar_button').click(function(){
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	});
	
	$('#efetivarButton').click(function(){
		$('#operacao').val("efetivar");
		$('#form1').submit();
	});
	

	$('body').delegate('.rdb', 'change', function() {
		var valor = $(this).val();
		$.ajax({url: "/scc/user/repasse/pre/consulta/json/mudaStatus/"+valor+".scc"});
		$('#efetivarButton').removeAttr('disabled');


});

	
	
	$('#demonstrativoDialog').dialog({
		autoOpen: false,
		width: 720,
		height : 550,
		buttons: {
			"Fechar": function() { 
				$(this).dialog("close"); 
			}
		}
	});
	
});

function selecionaLD() {  
 $('#pesquisar_button').attr('disabled', 'disabled');
 $("#cdProdutoPrepago").empty().append('<option selected="selected" value="0">Carregando...</option>');
 $("#periodo").empty().append('<option selected="selected" value="0">Selecione...</option>');
 var sel = $("#cdEOTLD option:selected"); 
  $.ajax({   
	 url: "/scc/user/repasse/pre/consulta/json/lista_produtos/"+sel.val()+".scc",	 
	 dataType: "json",   success: function(data) {     
		var name, select, option;        
	    select = document.getElementById('cdProdutoPrepago');      
		select.options.length = 0;         
		for (name in data) {       
			if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
			}
		}
	    $('#pesquisar_button').removeAttr('disabled');    
	   	}
  	  }
   );
}

function selecionar(linhaSelecionada) {
	$('#itemSelecionado').val(linhaSelecionada);
	$('#operacao').val("selecionar");
	$('#form1').submit();
}

</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pre_consulta.filtro.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="efetivarLigado"/>
<form:hidden path="itemSelecionado"/>
<div id="tabs-1">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
		    <td width="10%"><spring:message code="repasse_pre_consulta.ld"/>:</td>
		    <td ><form:select path="to.cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />
		    <form:errors path="to.cdEOTLD" /></td>
		</tr>
		<tr>
			<td width="10%"><spring:message code="repasse_pre_consulta.produto"/>:</td>
		    <td ><form:select path="to.cdProdutoPrepago" id="cdProdutoPrepago" items="${produtos}" itemLabel="noProdutoPrepago" itemValue="cdProdutoPrepago" />
		    <form:errors path="to.cdProdutoPrepago" /></td>
		</tr>
		<tr>    
		    <td width="10%"><spring:message code="repasse_pre_consulta.status"/>:</td>
		    <td ><form:select path="to.cdStatusRepasse" id="cdStatusRepasse" items="${status}" itemLabel="label" itemValue="key" />
		    <form:errors path="to.cdStatusRepasse" /></td>
		</tr>
		<tr>    
		    <td width="10%"><spring:message code="repasse_pre_consulta.mes"/>:</td>
		    <td ><form:select path="to.mes" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" />
		    <form:errors path="to.mes" /></td>
		</tr>
		<tr>
			<td width="10%"><spring:message code="repasse_pre_consulta.ano"/>:</td>
		    <td ><form:input path="to.ano" id="ano" size="4" maxlength="4"/>
		    <form:errors path="to.ano" /></td>
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

	<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
		<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
			<tr>
				<td>                            
					<display:table style="width : 90%" name="sessionScope._DISPLAY_TAG_SPACE_1"  pagesize="50"  id="repasses" requestURI="/scc/user/repasse/pre/consulta/tab1.scc" class="ui-state-default">
					<display:column property="anoMes" title="Ano/Mês" />
					<display:column property="operadoraLD" title="Operadora LD" />
					<display:column property="operadoraClaro" title="Operadora Claro" />
					<display:column property="valorRepasse" title="Valor do Repasse" />
					<display:column property="confirmacao" title="Confirmação" />
					<display:column property="selecionar" title="Demonstrativo" />
					</display:table>
				</td>
			</tr>
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
		    	<td colspan="3" >&nbsp;</td>    
		    	<td colspan="1" align="right"  nowrap="nowrap">        
			    	<input type="button" id="efetivarButton" name="efetivarButton"  value=<spring:message code="comum.botao.efetivar"/> />
			    </td>
			</tr>
		</table>
	</c:if>

</div>
</form:form>
</div>

<div id="demonstrativoDialog" title="Demonstrativo">
	<table style="width: 100%; border: 1px;"  class="ui-state-default">
		<tr>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.descricao"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.chamadas"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.minutos"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.bruto"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.icmsNaoRepassado"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.icmsRepassar"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.pisCofins"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.iss"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.valorLiquido"/></th>
			<th align="left"><spring:message code="repasse_pre_demonstrativo.coluna.valorRepassar"/></th>
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
				 <td>${item.campoChamadas}</td>
				 <td>${item.campoMinutos}</td>
				 <td>${item.campoValorBruto}</td>
				 <td>${item.campoIcmsNaoRepassado}</td>
				 <td>${item.campoIcmsRepassar}</td>
				 <td>${item.campoPisCofins}</td>
				 <td>${item.campoIss}</td>
				 <td>${item.campoValorLiquido}</td>
				 <td>${item.campoValorRepassar}</td>
			</tr>
		</c:forEach>
	</table>
</div>

<script>
$(function() {
	var op = $('#efetivarLigado').val();
	if (op == 'N'){
		$('#efetivarButton').attr('disabled', 'disabled');		
	}
	$('#pesquisar_button').removeAttr('disabled');
	
	op = $('#operacao').val();
	if (op == 'DEMONSTRATIVO') {
		$('#demonstrativoDialog').dialog('open');	
	}
});
</script>