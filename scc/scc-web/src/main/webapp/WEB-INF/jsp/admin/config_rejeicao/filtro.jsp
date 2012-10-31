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
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);	
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#salvar_button').click(salvar);
	
	$("#vlPenalidade").mask("9999999.99");
	
	$('#tabs').tabs();
});
	
function salvar() {
	var r=confirm("Salvar?");
	if (r==true) {		
		$('#operacao').val("CRUD.salvar");	
		$('#form1').submit();	
	}
}

function atualizar() {
	var r=confirm("Atualizar Registro?");
	if (r==true) {		
		$('#operacao').val("CRUD.atualizar");	
		$('#form1').submit();	
	}
}

function remover(linha) {
	var r=confirm("Remover?");
	if (r==true) {
		$('#itemSelecionado').val(linha);	
		$('#operacao').val("CRUD.remover_tabela");	
		$('#form1').submit();	
	}
}

function editar(linha) {
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("CRUD.editar");	
	$('#salvar_button').hide();
	$('#atualizar_button').show();
	$('#form1').submit();
}

function novo() {
	$('#tabs').tabs('select',1);
	$('#atualizar_button').hide();
	$('#salvar_button').show();	
}

function pesquisar() {
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

function cancelar() {
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}
</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="15%">Operadora LD:</td><td> <form:select path="operadoraLD" items="${operadorasLDTodas}" itemValue="cdEot" itemLabel="dsOperadora"/> </td>
	</tr>
	<tr>
		<td width="15%">Tipo Rejeição:</td><td> <form:select path="motivoRejeicao" items="${tiposRejeicaoTodos}" itemValue="cdMotivoRejeicao" itemLabel="dsMotivoRejeicao"/> </td>
	</tr>
</table>
<input id="pesquisar_button" type="button" value="Pesquisar" /> 
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
	<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/configrejeicao/tab1.scc" class="ui-state-default">
		<display:column property="nomeOperadoraLD" title="Operadora LD" />
		<display:column property="nomeMotivoRejeicao" title="Motivo Rejeição" />
		<display:column property="vlPenalidade" title="Valor da Penalidade" />
		<display:column property="descCobraPenalidade" title="Cobrança de Penalidade" />
		<display:column property="editar" title="Editar" />
		<display:column property="remover" title="Remover" />
	</display:table>
</td></tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />    
    </td>
</tr>
</table>
</div>
<div id="tabs-2" style="height: 500px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Operadora LD:</td>
<td ><form:select   path="entity.id.cdEotLd" items="${operadorasLD}" itemLabel="dsOperadora" itemValue="cdEot"/>
</td>
</tr>

<tr>
<td width="15%">Tipo Rejeição:</td>
<td ><form:select   path="entity.id.cdMotivoRejeicao" items="${tiposRejeicao}" itemLabel="dsMotivoRejeicao" itemValue="cdMotivoRejeicao"/>
</td>
</tr>

<tr>
<td width="15%">Valor da Penalidade:</td>
<td ><form:input id="vlPenalidade"  path="entity.vlPenalidade" maxlength="8" size="10"/><form:errors path="entity.vlPenalidade"/>
</td>
</tr>

<tr>
<td width="15%">Cobrança de Penalidade:</td>
<td ><form:select   path="entity.fgCobrarPenalidade" items="${simNao}" itemLabel="label" itemValue="key"/>
</td>
</tr>


</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="atualizar_button" type="button" value=<spring:message code="crud.botao.atualizar"/> />
    <input id="cancelar_button" type="button" value="Cancelar" />        
    <input id="salvar_button" type="button" value=<spring:message code="crud.botao.salvar"/> />
    </td>
</tr>
</table>
</div>
</form:form>
</div>
<script>
$(function() {
	$('#tabs').tabs('select',${filtro.activeTab});
	var op = $('#operacao').val();
	if (op == 'CRUD.editar'){
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
	}else if(op == 'CRUD.atualizar'){
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
	}
	else{		
		$('#salvar_button').show();
		$('#atualizar_button').hide();
		$('#cancelar_button').hide();	
	}
});
</script>
