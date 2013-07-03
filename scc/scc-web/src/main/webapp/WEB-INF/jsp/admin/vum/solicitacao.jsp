<%@ page session="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<script type="text/javascript" src="/scc/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>

<script>
$(document).ready(function(){
	$('#tabs').tabs();
	$("#dataInicioRepasse").mask("99/99/9999");	
	$("#dataFimRepasse").mask("99/99/9999");
	$("#dataInicioRepasse").datepicker();
	$("#dataFimRepasse").datepicker();
	
	$('#inserirButton').click(function(){
		$("#operacao").val('INSERIR');
		$("#form1").attr("action", "/scc/user/admin/vum/cadastro/insere.scc");
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

function remove(nmParametro) {
	$('#nmParam').val(nmParametro);
	$("#form1").attr("action", "/scc/user/admin/vum/cadastro/delete.scc");
	$('#form1').submit();
}

</script>

<div id="tabs">
<ul>
	<li><a href="#tabs-1"><spring:message code="solicitacao_vum.solicitar"/></a></li>
	<li><a href="#tabs-2"><spring:message code="solicitacao_vum.processando"/></a></li>
	<li><a href="#tabs-3"><spring:message code="solicitacao_vum.finalizados"/></a></li>
</ul>
<div id="tabs-1">
<form:form modelAttribute="filtro" method="post" action="insere.scc" id="form1">
	<form:hidden path="operacao"/>
	<form:hidden path="nmParam"/>

	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	
		<tr>
		    <td><spring:message code="solicitacao_vum.dtInicial"/>:</td>
		    <td><form:input id="dataInicioRepasse" path="dataInicioRepasse" /></td>
		
		    <td><spring:message code="solicitacao_vum.dtFinal"/>:</td>
		    <td><form:input id="dataFimRepasse" path="dataFimRepasse" /></td>
		
		    <td><spring:message code="solicitacao_vum.ld"/>:</td>
		    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />
		    </td>
		
			<td><spring:message code="solicitacao_vum.plataforma"/>:</td>
		    <td ><form:select path="plataforma" id="plataforma" items="${plataformas}" itemLabel="label" itemValue="key" /></td>
			
			<td><spring:message code="solicitacao_vum.tipoArquivo"/>:</td>
		    <td ><form:select path="tipoArquivo" id="tipoArquivo" items="${tipoArquivo}" itemLabel="label" itemValue="key" />
		    </td>	   
			
			<td>
				<input type="button" id="inserirButton" value=<spring:message code="comum.botao.inserir" /> />
			</td>
		</tr>
		<tr>
			<td></td>
			<td><form:errors path="dataInicioRepasse" /></td>
			<td></td>
			<td><form:errors path="dataFimRepasse" /></td>
			<td></td>
			<td><form:errors path="cdEOTLD" /></td>
			<td></td>
			<td><form:errors path="plataforma" /></td>
			<td></td>
			<td><form:errors path="tipoArquivo" /></td>
		</tr>
	
	</table>
</form:form>

<h3><spring:message code="solicitacao_vum.solicitar"/></h3>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
	<display:table requestURI="new.scc"  style="width: 90%; border: 1px;" name="sessionScope._DISPLAY_TAG_SPACE_1" decorator="com.claro.sccweb.decorator.SolicitacaoVumDecorator" pagesize="50"  id="cdrs" class="ui-state-default" >
		<display:column property="operadoraLD" title="Empresa LD" />
		<display:column property="dtInicioRepasse" title="Data Inicial Repasse" />
		<display:column property="dtFimRepasse" title="Data Fim Repasse" />
		<display:column property="tipoArquivo" title="Relatório" />
		<display:column property="plataforma" title="Plataforma" />
		<display:column property="dtCriacao" title="Data Criação" />
		<display:column property="dtEvento" title="Data Evento" />
		<display:column property="usuario" title="Usuário" />
		<display:column property="remover" title="Remover" />
	</display:table>
</c:if>
<br/>

</div>

<div id="tabs-2">
<h3><spring:message code="solicitacao_vum.processando"/></h3>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_2}">
	<display:table requestURI="new.scc" style="width: 100%; border: 1"  name="sessionScope._DISPLAY_TAG_SPACE_2" decorator="com.claro.sccweb.decorator.SolicitacaoVumDecorator" pagesize="50"  id="cdrs" class="ui-state-default">
		<display:column property="operadoraLD" title="Empresa LD" />
		<display:column property="dtInicioRepasse" title="Data Inicial Repasse" />
		<display:column property="dtFimRepasse" title="Data Fim Repasse" />
		<display:column property="tipoArquivo" title="Relatório" />
		<display:column property="plataforma" title="Plataforma" />
		<display:column property="dtCriacao" title="Data Criação" />
		<display:column property="dtEvento" title="Data Evento" />
		<display:column property="usuario" title="Usuário" />
	</display:table>
</c:if>
<br/>
</div>

<div id="tabs-3">
<h3><spring:message code="solicitacao_vum.finalizados"/></h3>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_3}">
	<display:table requestURI="new.scc" style="width: 100%; border: 1"  name="sessionScope._DISPLAY_TAG_SPACE_3" decorator="com.claro.sccweb.decorator.SolicitacaoVumDecorator" pagesize="50"  id="cdrs" class="ui-state-default">
		<display:column property="operadoraLD" title="Empresa LD" />
		<display:column property="dtInicioRepasse" title="Data Inicial Repasse" />
		<display:column property="dtFimRepasse" title="Data Fim Repasse" />
		<display:column property="tipoArquivo" title="Relatório" />
		<display:column property="plataforma" title="Plataforma" />
		<display:column property="dtCriacao" title="Data Criação" />
		<display:column property="dtEvento" title="Data Evento" />
		<display:column property="usuario" title="Usuário" />
	</display:table>
</c:if>
<br/>
</div>


</div>

<script>
$(document).ready(function()
{
	$("#operacao").val('');
	
});
</script>
