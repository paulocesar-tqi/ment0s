<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<style type="text/css">
td.hidden {display: none;}
th.hidden {display: none;}
</style>

<script>
$(document).ready(function(){
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);	
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#salvar_button').click(salvar);
	
	
	$('#tabs').tabs();
	
	$(".vlpenal").mask("999999,99");
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

function haschgd (which) { 
	which.style.background = '#FFFF00' 
}


function salvarLinha(linha)
{
	var valorPenalidade = $("#vl" + linha);
	var indCobraPenalidade = $("#cdEOTLD option:selected");	
	var valCdOperadoraLD = $("#valCdOperadoraLD" + linha);
	var valCdMotivoRejeicao = $("#valCdMotivoRejeicao" + linha);
	$.ajax({   
		 url: "/scc/user/repasse/pos/retorno/json/lista_periodos/"+sel.val()+"/"+eot.val()+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('cdPeriodicidade');      
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
<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td width="15%">Operadora LD:</td><td> <form:select path="cdOperadoraLD" items="${operadorasLDTodas}" itemValue="cdEot" itemLabel="dsOperadora"/> </td>
	</tr>
	<tr>
		<td width="15%">Tipo Rejeição:</td><td> <form:select path="cdMotivoRejeicao" items="${tiposRejeicaoTodos}" itemValue="cdMotivoRejeicao" itemLabel="dsMotivoRejeicao"/> </td>
	</tr>
</table>
<input id="pesquisar_button" type="button" value="Pesquisar" /> 
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
	<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/configrejeicao/tab1.scc" class="ui-state-default">
		<display:column property="cdperadoraLD" title="Operadora LD" class="hidden" headerClass="hidden">
			<input type="text" id="valCdOperadoraLD${repasses.rownum}" value="<c:out value="${repasses.cdOperadoraLD}"/>" />
		</display:column>
		<display:column property="cdMotivoRejeicao" title="Motivo Rejeição" class="hidden" headerClass="hidden">
			<input type="text" id="valCdMotivoRejeicao${repasses.rownum}" value="<c:out value="${repasses.cdMotivoRejeicao}"/>" />
		</display:column>
		<display:column property="nomeOperadoraLD" title="Operadora LD" />
		<display:column property="nomeMotivoRejeicao" title="Motivo Rejeição" />
		<display:column property="vlPenalidade" title="Valor da Penalidade" />
		<display:column title="Valor da Penalidade" >
			<input type="text" class="vlpenal" id="vl${repasses.rownum}" style="padding: 0" value="<c:out value="${repasses.vlPenalidade}"/>" onchange = "haschgd(this)" />
		</display:column>
		<display:column property="descCobraPenalidade" title="Cobrança de Penalidade" />
		<display:column title="Cobrança de Penalidade Combo">
			<select name="descCobraPenalidade" id="cb${repasses.rownum}"  onchange = "haschgd(this)">
				<option value="Sim" <c:if test="${repasses.descCobraPenalidade eq 'Sim'}"> selected </c:if>>Sim </option> 
				<option value="Não" <c:if test="${repasses.descCobraPenalidade eq 'Não'}"> selected </c:if>>Não </option> 
			</select>		
		</display:column>
		<display:column title="Salvar">
			<a href='#' onClick="salvarLinha(${repasses.rownum})"> Salvar </a>
		</display:column>
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
