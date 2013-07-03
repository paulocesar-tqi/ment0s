<%@ page session="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<script type="text/javascript" src="/scc/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<script>
$(document).ready(function(){
	$('#tabs').tabs();
	$("#anoRelatorio").mask("9999");	
	$('#cdEOT').change(selecionaLD);
	$('#cdProdutoCobilling').change(selecionaProduto);
	
	$('#inserirButton').click(function(){
		$('#operacao').val("CRIAR");
		$('#criados').val("");
		$('#form1').submit();
	});
	
	$('#dialog').dialog({
		autoOpen: false,
		width: 200,
		buttons: {
			"Ok": function() { 
				$(this).dialog("close"); 
			}
		}
	});
	
});

function selecionaLD()
{
 $("#cdProdutoCobilling").empty().append('<option selected="selected" value="0">Selecione...</option>');
 $("#cdPeriodicidadeRepasse").empty().append('<option selected="selected" value="0">Selecione...</option>');
 var sel = $("#cdEOT option:selected");
 
  $.ajax({   
	 url: "/scc/user/repasse/pos/solicitacao/json/lista_produtos/"+sel.val()+".scc",	 
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
	var eot = $("#cdEOT option:selected");	
	$("#cdPeriodicidadeRepasse").empty().append('<option selected="selected" value="0">Selecione...</option>');
	$.ajax({   
		 url: "/scc/user/repasse/pos/solicitacao/json/lista_periodos/"+sel.val()+"/"+eot.val()+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('cdPeriodicidadeRepasse');      
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
<li><a href="#tabs-1"><spring:message code="repasse_pos_solicitacao.solicitar"/></a></li>
<li><a href="#tabs-2"><spring:message code="repasse_pos_solicitacao.processando"/></a></li>
<li><a href="#tabs-3"><spring:message code="repasse_pos_solicitacao.finalizados"/></a></li>

</ul>
<form:form modelAttribute="form" method="post" action="/scc/user/repasse/pos/solicitacao/insere.scc" id="form1">
<form:hidden path="operacao"/>
<form:hidden path="criados"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td><spring:message code="repasse_pos_solicitacao.ld"/>:</td>
    <td ><form:select path="cdEOT" id="cdEOT" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>

	<td><spring:message code="repasse_pos_solicitacao.produto"/>:</td>
    <td ><form:select path="cdProdutoCobilling" id="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" /></td>
    
    <td><spring:message code="repasse_pos_solicitacao.periodo"/>:</td>
    <td ><form:select path="cdPeriodicidadeRepasse" id="cdPeriodicidadeRepasse" items="${periodos}" itemLabel="noPeriodicidadeRepasse" itemValue="cdPeriodicidadeRepasse" /></td>
    
    <td><spring:message code="repasse_pos_solicitacao.mes"/>:</td>
    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>

	<td><spring:message code="repasse_pos_solicitacao.ano"/>:</td>
    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
    <form:errors path="anoRelatorio" /></td>

	<td>
	<input type="button" id="inserirButton" value=<spring:message code="comum.botao.inserir" /> />
	</td>     
</tr>
</table>

<h3><spring:message code="repasse_pos_solicitacao.tab1"/></h3>
<display:table requestURI="new.scc"  style="width: 90%; border: 1px;" name="sessionScope._DISPLAY_TAG_SPACE_1" decorator="com.claro.sccweb.decorator.RelatorioRepasseDecorator" pagesize="50"  id="cdrs" class="ui-state-default" >
<display:column property="periodo" title="Período" />	
<display:column property="requisicao" title="No. Requisição" />
<display:column property="operadoraLD" title="Empresa LD" />
<display:column property="operadoraClaro" title="Operadora Claro" />
<display:column property="dataCriacao" title="Data Criação" />
<display:column property="usuario" title="Usuário" />
<display:column property="produto" title="Produto" />
<display:column property="remover" title="Remover" />
</display:table>
<br/>

</div>
</form:form>
<div id="tabs-2">
<h3><spring:message code="repasse_pos_solicitacao.tab2"/></h3>
<display:table requestURI="new.scc" style="width: 100%; border: 1"  name="sessionScope._DISPLAY_TAG_SPACE_2" decorator="com.claro.sccweb.decorator.RelatorioRepasseDecorator" pagesize="50"  id="cdrs" class="ui-state-default">
<display:column property="periodo" title="Período" />	
<display:column property="requisicao" title="No. Requisição" />
<display:column property="operadoraLD" title="Empresa LD" />
<display:column property="operadoraClaro" title="Operadora Claro" />
<display:column property="dataCriacao" title="Data Criação" />
<display:column property="usuario" title="Usuário" />
<display:column property="produto" title="Produto" />
</display:table>
<br/>
</div>

<div id="tabs-3">
<h3><spring:message code="repasse_pos_solicitacao.tab3"/></h3>
<display:table requestURI="new.scc" style="width: 100%; border: 1"  name="sessionScope._DISPLAY_TAG_SPACE_3" decorator="com.claro.sccweb.decorator.RelatorioRepasseDecorator" pagesize="50"  id="cdrs" class="ui-state-default">
<display:column property="periodo" title="Período" />	
<display:column property="requisicao" title="No. Requisição" />
<display:column property="operadoraLD" title="Empresa LD" />
<display:column property="operadoraClaro" title="Operadora Claro" />
<display:column property="dataCriacao" title="Data Criação" />
<display:column property="usuario" title="Usuário" />
<display:column property="produto" title="Produto" />
</display:table>
<br/>
</div>
</div>

<div id="dialog" title="Resultado">
		<c:out value="Foram criadas ${form.criados} solicitações."/>
		</div>

<script>
$(document).ready(function()
{
if ($('#criados').val() != "")
	{
	$('#dialog').dialog('open');
	}
});
</script>