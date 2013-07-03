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
	$('#dtEnvioBoletimLd').mask("99/99/9999");
	$('#dtEnvioBoletimLd').datepicker();
	
	$('#dtInicioParalizacao').mask("99/99/9999");
	$('#dtInicioParalizacao').datepicker();
	
	$('#dtInicioParalizacao').mask("99/99/9999");
	$('#dtInicioParalizacao').datepicker();
	
	$('#dtFimParalizacao').mask("99/99/9999");
	$('#dtFimParalizacao').datepicker();
	
	$('#dtFinal').mask("99/99/9999");
	$('#dtFinal').datepicker();
	
	$('#dtInicial').mask("99/99/9999");
	$('#dtInicial').datepicker();
	
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#pesquisar_button').click(pesquisar);	
	$('#salvar_button').click(salvar);
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
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();
}

function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

function cancelar() {
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}

function num(dom) {
    dom.value=dom.value.replace(/\D/g,""); 
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
<form:hidden path="mensagem" id="mensagem"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Operadora LD:</td> 
<td><form:select path="filtro.cdEotLd" itemValue="cdEot" itemLabel="dsOperadoraForCombos" items="${operadorasExternas}"/></td>
</tr>

<tr>
<td width="15%">No. BA Claro:</td> 
<td><form:input path="filtro.nuBoletimClaro" size="31" maxlength="30"/></td>
</tr>

<tr>
<td width="15%">CNs da área associada:</td> 
<td><form:input path="filtro.cdAreaPlataforma" size="4" maxlength="3"/></td>
</tr>

<tr>
<td width="15%">Tecnologia da Plataforma:</td> 
<td><form:select path="filtro.cdTecnologiaPlataforma" itemValue="key" itemLabel="label" items="${tecnologia}"/></td>
</tr>

<tr>
<td width="15%">Tipo de Falha:</td> 
<td><form:select path="filtro.cdTipoFalha" itemValue="key" itemLabel="label" items="${tipoFalha}"/></td>
</tr>

<tr>
<td width="15%">Desbloqueio Solicitado pela LD:</td> 
<td><form:select path="filtro.fgSolicDesbloqueioLd" itemValue="key" itemLabel="label" items="${desbloqueioSolicitado}"/></td>
</tr>

<tr>
<td width="15%">No. BA LD:</td> 
<td><form:input path="filtro.nuBoletimLd" size="31" maxlength="30"/></td>
</tr>

<tr>
<td width="15%">Data Inicial:</td> 
<td><form:input path="dtInicial" id="dtInicial"/><form:errors path="dtInicial"/> </td>
</tr>

<tr>
<td width="15%">Data Final:</td> 
<td><form:input path="dtFinal" id="dtFinal"/><form:errors path="dtFinal"/></td>
</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />
    <input id="pesquisar_button" type="button" value="Pesquisar" />
    <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
    	<input id="novo_button" type="button" value="Excel" />
    </c:if>
    </td>
</tr>
</table>
<br/>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/paralisacao/tab1.scc" class="ui-state-default">
<display:column property="row.cdEotLd" title="Operadora LD" />
<display:column property="row.txMotivoFalha" title="Motivo Falha" />
<display:column property="dtInicioParalizacao" title="Início da Paralização" />
<display:column property="dtFimParalizacao" title="Fim da Paralização" />
<display:column property="row.nuBoletimClaro" title="No. Boletim Claro" />
<display:column property="row.nuBoletimLd" title="No. Boletim LD" />
<display:column property="qtDuracaoParalizacao" title="Duração da Paralisação" />
<display:column property="editar" title="Editar" />
</display:table>
</td></tr>
</table>
</c:if>

</div>

<div id="tabs-2" style="height: 500px;">
<form:hidden path="entity.sqParalizacaoPlat" id="sqParalizacaoPlat"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">CN da área associada a Plataforma:</td>
<td> <form:input path="entity.cdAreaPlataforma" id="cdAreaPlataforma" size="4" maxlength="3"/>
</tr>

<tr>
<td width="15%">Operadora LD:</td>
<td> <form:select path="entity.cdEotLd" id="cdEotLd" itemValue="cdEot" itemLabel="dsOperadoraForCombos" items="${operadorasExternas}"/>
</tr>

<tr>
<td width="15%">Tecnologia da Plataforma (TDMA/GSM):</td>
<td> <form:select path="entity.cdTecnologiaPlataforma" id="cdTecnologiaPlataforma" items="${tecnologia}" itemValue="key" itemLabel="label"/>
</tr>

<tr>
<td width="15%">Tipo de Falha:</td>
<td> <form:select path="entity.cdTipoFalha" id="cdTipoFalha" items="${tipoFalha}" itemValue="key" itemLabel="label"/>
</tr>

<tr>
<td width="15%">Data do Envio BA LD:</td>
<td> <form:input path="entity.dtEnvioBoletimLd" id="dtEnvioBoletimLd"/>
</tr>

<tr>
<td width="15%">Início Paralisação:</td>
<td> <form:input path="entity.dtInicioParalizacao" id="dtInicioParalizacao"/><form:errors path="entity.dtInicioParalizacao"/>
</tr>

<tr>
<td width="15%">Fim Paralisação:</td>
<td> <form:input path="entity.dtFimParalizacao" id="dtFimParalizacao"/><form:errors path="entity.dtFimParalizacao"/>
</tr>

<tr>
<td width="15%">Desbloqueio Solicitado pela LD (S/N):</td>
<td> <form:select path="entity.fgSolicDesbloqueioLd" id="fgSolicDesbloqueioLd" itemLabel="label" itemValue="key" items="${desbloqueioSolicitado}"/>
</tr>

<tr>
<td width="15%">No. BA da Claro:</td>
<td> <form:input path="entity.nuBoletimClaro" id="nuBoletimClaro" size="35" maxlength="30"/><form:errors path="entity.nuBoletimClaro"/>
</tr>

<tr>
<td width="15%">No. BA da LD:</td>
<td> <form:input path="entity.nuBoletimLd" id="nuBoletimLd" size="35" maxlength="30"/>
</tr>

<tr>
<td width="15%">Motivo de Falha:</td>
<td> <form:textarea cols="100" path="entity.txMotivoFalha" id="txMotivoFalha"/>
</tr>

<tr>
<td width="15%">Observação:</td>
<td> <form:textarea cols="100" path="entity.txObservacao" id="txObservacao"/>
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
	var op = $('#operacao').val();
	$('#tabs').tabs('select',${filtro.activeTab});
	if (op == 'CRUD.editar') {
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
		$('#cdStatusDe').attr('disabled', 'disabled');
		$('#cdStatusPara').attr('disabled', 'disabled');
	} else if (op == 'CRUD.novo') {	
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();	
	} else if (op == 'erro_validacao') {
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
		alert($('#mensagem').val());
	} else if(op == 'CRUD.atualizar') {
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
	} else {		
		$('#salvar_button').show();
		$('#atualizar_button').hide();
		$('#cancelar_button').hide();		
	}
});
</script>
