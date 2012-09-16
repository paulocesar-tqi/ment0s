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
	$( "#dtFimVigencia" ).datepicker();
	$( "#dtInicioVigencia" ).datepicker();	
	
	$("#peInicioFaixaPenalidade").mask("999.9999");
	$("#peFimFaixaPenalidade").mask("999.9999");
	
	$("#vlFatorCalculoPenalidade").mask("9999999.99");
	
	$("#dtFimVigencia").mask("99/99/9999");	
	$("#dtInicioVigencia").mask("99/99/9999");
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
<td width="15%">Penalidade:</td><td> <form:select path="cdTipo" items="${tipos}" itemValue="key" itemLabel="label"/> 
<input id="pesquisar_button" type="button" value="Pesquisar" /> 
</td>
</tr>
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/penalidade/tab1.scc" class="ui-state-default">
<display:column property="cdFaixaPenalidade" title="Código" />
<display:column property="peInicioFaixaPenalidade" title="Início da Faixa" />
<display:column property="peFimFaixaPenalidade" title="Fim da Faixa" />
<display:column property="vlFatorCalculoPenalidade" title="Valor" />
<display:column property="dtInicioVigencia" title="Início Vigência" />
<display:column property="dtFimVigencia" title="Fim Vigência" />
<display:column property="dsFaixaPenalidade" title="Descrição" />
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

<form:hidden path="entity.cdFaixaPenalidade" id="cdFaixaPenalidade"/>

<tr>
<td width="15%">Descrição:</td>
<td ><form:select   path="entity.dsFaixaPenalidade" items="${descricao}" itemLabel="label" itemValue="key"/>
</td>
</tr>

<tr>
<td width="15%">Tipo:</td>
<td ><form:select   path="entity.inTipoPenalidade" items="${tipos}" itemLabel="label" itemValue="key"/>
</td>
</tr>


<tr>
<td width="15%">Início da Faixa:</td>
<td ><form:input id="peInicioFaixaPenalidade"  path="entity.peInicioFaixaPenalidade" maxlength="8" size="10"/><form:errors path="entity.peInicioFaixaPenalidade"/>
</td>
</tr>

<tr>
<td width="15%">Fim da Faixa:</td>
<td ><form:input id="peFimFaixaPenalidade"  path="entity.peFimFaixaPenalidade" maxlength="8" size="10"/><form:errors path="entity.peFimFaixaPenalidade"/>
</td>
</tr>


<tr>
<td width="15%">Valor:</td>
<td ><form:input id="vlFatorCalculoPenalidade"  path="entity.vlFatorCalculoPenalidade" maxlength="8" size="10"/><form:errors path="entity.vlFatorCalculoPenalidade"/>
</td>
</tr>

<tr>
<td width="15%">Data Inicial:</td>
<td ><form:input id="dtInicioVigencia"  path="entity.dtInicioVigencia" /><form:errors path="entity.dtInicioVigencia"/>
</td>
</tr>

<tr>
<td width="15%">Data Final:</td>
<td ><form:input id="dtFimVigencia"  path="entity.dtFimVigencia" /><form:errors path="entity.dtInicioVigencia"/>
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
