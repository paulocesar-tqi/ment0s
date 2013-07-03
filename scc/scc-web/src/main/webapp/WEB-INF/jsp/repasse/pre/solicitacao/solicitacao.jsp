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
	$('#cdEOTLD').change(selecionaOperadora);
	$('#cdEODClaro').change(selecionaOperadora);
	
	
	$('#inserirButton').click(function(){
		$("#operacao").val('INSERIR');
		$("#form1").attr("action", "/scc/user/repasse/pre/solicitacao/insere.scc");
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

function remove(nmParametro)
{
$('#nmParam').val(nmParametro);
$("#form1").attr("action", "/scc/user/repasse/pre/solicitacao/delete.scc");
$('#form1').submit();
}

function selecionaOperadora()
{
 $("#cdProdutoPrepago").empty().append('<option selected="selected" value="0">Selecione...</option>'); 
 var eotLD = $("#cdEOTLD option:selected");
 var eotClaro = $("#cdEODClaro option:selected");
 
  $.ajax({   
	 url: "/scc/user/repasse/pre/solicitacao/json/lista_produtos/"+eotLD.val()+"/"+eotClaro.val()+".scc",	 
	 dataType: "json",   success: function(data) 
	   	{     
		var name, select, option;        
	    select = document.getElementById('cdProdutoPrepago');      
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
<li><a href="#tabs-1"><spring:message code="repasse_pre_solicitacao.solicitar"/></a></li>
<li><a href="#tabs-2"><spring:message code="repasse_pre_solicitacao.processando"/></a></li>
<li><a href="#tabs-3"><spring:message code="repasse_pre_solicitacao.finalizados"/></a></li>

</ul>
<form:form modelAttribute="form" method="post" action="insere.scc" id="form1">
<form:hidden path="operacao"/>
<form:hidden path="criados"/>
<form:hidden path="nmParam"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
	<td><spring:message code="repasse_pre_solicitacao.mes"/>:</td>
    <td ><form:select path="mesRelatorio" id="mesRelatorio" items="${meses}" itemLabel="label" itemValue="key" /></td>

	<td><spring:message code="repasse_pre_solicitacao.ano"/>:</td>
    <td ><form:input path="anoRelatorio" id="anoRelatorio" size="4" maxlength="4"/>
    </td>

    <td><spring:message code="repasse_pre_solicitacao.ld"/>:</td>
    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />
    </td>

	<td><spring:message code="repasse_pre_solicitacao.operadoraClaro"/>:</td>
    <td ><form:select path="cdEODClaro" id="cdEODClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
	
	<td><spring:message code="repasse_pre_solicitacao.produto"/>:</td>
    <td ><form:select path="cdProdutoPrepago" id="cdProdutoPrepago" items="${produtos}" itemLabel="noProdutoPrepago" itemValue="cdProdutoPrepago" />
    </td>
    </td>
	
	<td><spring:message code="repasse_pre_solicitacao.criterio"/>:</td>
    <td ><form:select path="cdCriterioCusto" id="cdCriterioCusto" items="${criterios}" itemLabel="label" itemValue="key" /></td>
	<td>
	<input type="button" id="inserirButton" value=<spring:message code="comum.botao.inserir" /> />
	</td>
	</tr>
	<tr>
	<td></td><td></td>
	<td></td><td><form:errors path="anoRelatorio" /></td>
	<td></td><td><form:errors path="cdEOTLD" /></td>
	<td></td><td></td>
	<td></td><td><form:errors path="cdProdutoPrepago" /></td>
	<td></td><td></td>
	</tr>
	</form:form>
</table>

<h3><spring:message code="repasse_pre_solicitacao.tab1"/></h3>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<display:table requestURI="new.scc"  style="width: 90%; border: 1px;" name="sessionScope._DISPLAY_TAG_SPACE_1" decorator="com.claro.sccweb.decorator.RelatorioRepassePreDecorator" pagesize="50"  id="cdrs" class="ui-state-default" >
<display:column property="referencia" title="Ano / Mês Referência" />
<display:column property="requisicao" title="No. Requisição" />
<display:column property="operadoraLD" title="Empresa LD" />
<display:column property="operadoraClaro" title="Empresa Claro" />
<display:column property="criterio" title="Critério de Custo" />
<display:column property="produto" title="Produto" />
<display:column property="dtCriacao" title="Dt. Criação" />
<display:column property="dtEvento" title="Data Evento" />
<display:column property="usuario" title="Usuário" />
<display:column property="remover" title="Remover" />
</display:table>
</c:if>
<br/>

</div>

<div id="tabs-2">
<h3><spring:message code="repasse_pre_solicitacao.tab2"/></h3>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_2}">
<display:table requestURI="new.scc" style="width: 100%; border: 1"  name="sessionScope._DISPLAY_TAG_SPACE_2" decorator="com.claro.sccweb.decorator.RelatorioRepassePreDecorator" pagesize="50"  id="cdrs" class="ui-state-default">
<display:column property="referencia" title="Ano / Mês Referência" />
<display:column property="requisicao" title="No. Requisição" />
<display:column property="empresaLD" title="Empresa LD" />
<display:column property="empresaClaro" title="Empresa Claro" />
<display:column property="criterio" title="Critério de Custo" />
<display:column property="produto" title="Produto" />
<display:column property="dtCriacao" title="Dt. Criação" />
<display:column property="dtEvento" title="Data Evento" />
<display:column property="usuario" title="Usuário" />
</display:table>
</c:if>
<br/>
</div>

<div id="tabs-3">
<h3><spring:message code="repasse_pre_solicitacao.tab3"/></h3>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_3}">
<display:table requestURI="new.scc" style="width: 100%; border: 1"  name="sessionScope._DISPLAY_TAG_SPACE_3" decorator="com.claro.sccweb.decorator.RelatorioRepassePreDecorator" pagesize="50"  id="cdrs" class="ui-state-default">
<display:column property="referencia" title="Ano / Mês Referência" />
<display:column property="requisicao" title="No. Requisição" />
<display:column property="empresaLD" title="Empresa LD" />
<display:column property="empresaClaro" title="Empresa Claro" />
<display:column property="criterio" title="Critério de Custo" />
<display:column property="produto" title="Produto" />
<display:column property="dtCriacao" title="Dt. Criação" />
<display:column property="dtEvento" title="Data Evento" />
<display:column property="usuario" title="Usuário" />
</display:table>
</c:if>
<br/>
</div>
</div>

<div id="dialog" title="Resultado">
		<c:out value="Foram criadas ${form.criados} solicitações."/>
		</div>

<script>
$(document).ready(function()
{
	$("#operacao").val('');
if ($('#criados').val() != "")
	{
	$('#dialog').dialog('open');
	}
});
</script>
