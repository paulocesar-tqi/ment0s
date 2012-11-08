<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<script language="javascript" src="/scc/js/jquery.price_format.1.7.min.js"></script>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<style type="text/css">
td.hidden {display: none;}
th.hidden {display: none;}
</style>

<script>
$(document).ready(function(){
	$('#salvar_button').hide();
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);	
	$('#cancelar_button').click(cancelar);
	$('#salvar_button').click(salvar);
	
	
	$('#tabs').tabs();
	
	$(".vlpenal").priceFormat({	prefix: '',
								centsSeparator: ',',
								thousandsSeparator: '.',
								limit: 9,
								centsLimit: 2
							 });
});
	
function salvar() {
	var r=confirm("Salvar?");
	if (r==true) {	
		var maskedValue = $("#vlPenalidade").val();
		if(maskedValue.indexOf(",") > -1) {		
			maskedValue = maskedValue.replace(/\./g, "").replace(",","."); 
			$("#vlPenalidade").val(maskedValue);
		}
		$('#operacao').val("CRUD.salvar");	
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

function novo() {
	$('#tabs').tabs('select',1);
	$('#salvar_button').show();	
}

function pesquisar() {
	var maskedValue = $("#vlPenalidade").val();
	if(maskedValue.indexOf(",") > -1) {		
		maskedValue = maskedValue.replace(/\./g, "").replace(",","."); 
		$("#vlPenalidade").val(maskedValue);
	}
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
	var valorPenalidade = $("#vl" + linha).val();
	var indCobraPenalidade = $("#cb" + linha + " option:selected").val();	
	var valCdOperadoraLD = $("#valCdOperadoraLD" + linha).val();
	var valCdMotivoRejeicao = $("#valCdMotivoRejeicao" + linha).val();
	$.ajax({   
		 url: "/scc/user/admin/configrejeicao/json/salva_configrejeicao/"+valCdOperadoraLD+"/"+valCdMotivoRejeicao+"/"+valorPenalidade+"/"+indCobraPenalidade+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
				if(data["retorno"] == "true") 
				{
					$("#vl" + linha).css("background", "white");
					$("#cb" + linha).css("background", "white");
				}
				else alert("Erro ao atualizar o registro.");
			}});
	
}

</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
<li><a href="#tabs-2">Novo</a></li>
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
		<td width="15%">Tipo Rejeição:</td><td> <form:select path="cdMotivoRejeicao" items="${tiposRejeicaoTodos}" itemValue="cdMotivoRejeicao" itemLabel="dsMotivoRejeicaoLabel"/> </td>
	</tr>
</table>
 
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
		<input id="pesquisar_button" type="button" value="Pesquisar" />
		<input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />    
    </td>
</tr>
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
	<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/configrejeicao/tab1.scc" class="ui-state-default">
		<display:column title="Operadora LD" class="hidden" headerClass="hidden">
			<input type="text" id="valCdOperadoraLD${repasses.rownum}" value="<c:out value="${repasses.cdOperadoraLD}"/>" />
		</display:column>
		<display:column title="Motivo Rejeição" class="hidden" headerClass="hidden">
			<input type="text" id="valCdMotivoRejeicao${repasses.rownum}" value="<c:out value="${repasses.cdMotivoRejeicao}"/>" />
		</display:column>
		<display:column property="nomeOperadoraLD" title="Operadora LD" />
		<display:column property="nomeMotivoRejeicao" title="Motivo Rejeição" />
		<display:column title="Valor da Penalidade" >
			<input type="text" class="vlpenal" id="vl${repasses.rownum}" style="padding: 0" value="<c:out value="${repasses.vlPenalidade}"/>" onchange = "haschgd(this)" />
		</display:column>
		<display:column title="Cobrança de Penalidade">
			<select name="descCobraPenalidade" id="cb${repasses.rownum}"  onchange = "haschgd(this)">
				<option value="Sim" <c:if test="${repasses.descCobraPenalidade eq 'Sim'}"> selected </c:if>>Sim </option> 
				<option value="Não" <c:if test="${repasses.descCobraPenalidade eq 'Não'}"> selected </c:if>>Não </option> 
			</select>		
		</display:column>
		<display:column title="Salvar">
			<a href='#' onClick="salvarLinha(${repasses.rownum})"> Salvar </a>
		</display:column>
		<display:column property="remover" title="Remover" />
	</display:table>
</td></tr>
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
<td ><form:select   path="entity.id.cdMotivoRejeicao" items="${tiposRejeicao}" itemLabel="dsMotivoRejeicaoLabel" itemValue="cdMotivoRejeicao"/>
</td>
</tr>

<tr>
<td width="15%">Valor da Penalidade:</td>
<td ><form:input id="vlPenalidade"  class="vlpenal" path="entity.vlPenalidade" size="13"/><form:errors path="entity.vlPenalidade"/>
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
	}else if(op == 'CRUD.atualizar'){
		$('#salvar_button').hide();
		$('#cancelar_button').show();
	}
	else{		
		$('#salvar_button').show();
		$('#cancelar_button').hide();	
	}
});
</script>
