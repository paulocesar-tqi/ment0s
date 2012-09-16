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
	$('#dtInicioVigencia').mask("99/99/9999");
	$('#dtInicioVigenciaEdit').mask("99/99/9999");	
	$('#dtFimVigencia').mask("99/99/9999");
	
	$('#pcAliquotaImposto').mask("99.99");
	
	$('#dtInicioVigencia').datepicker();
	$('#dtFimVigencia').datepicker();
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
<td width="15%">Tipo de Contrato:</td> 
<td><form:select path="inPlataformaTarifacao" itemValue="key" itemLabel="label" items="${tiposContrato}"/></td>
</tr>

<tr>
<td width="15%">Imposto:</td>
<td> <form:select path="noImposto" itemValue="key" itemLabel="label" items="${impostos}"/></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />
    <input id="pesquisar_button" type="button" value="Pesquisar" />
    <!-- <c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}"> -->
    <!-- <input id="novo_button" type="button" value="Excel" /> -->
    <!-- </c:if> -->
    </td>
</tr>
</table>
<br/>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/aliquotas/tab1.scc" class="ui-state-default">
<display:column property="imposto" title="Imposto" />
<display:column property="tipoServico" title="Tipo de Serviço" />
<display:column property="row.id.sgUf" title="UF" />
<display:column property="dtInicioVigencia" title="Início Vigência" />
<display:column property="dtFimVigencia" title="Fim Vigência" />
<display:column property="aliquota" title="Alíquota" />
<display:column property="row.id.inPlataformaTarifacao" title="Tipo Contrato" />
<display:column property="editar" title="Editar" />
<display:column property="remover" title="Remover" />
</display:table>
</td></tr>
</table>
</c:if>

</div>

<div id="tabs-2" style="height: 500px;">
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Imposto:</td>
<td> <form:select id="combo_noImposto" path="entity.id.noImposto" itemValue="key" itemLabel="label" items="${impostos}"/></td>
</tr>

<tr>
<td width="15%">Tipo de Contrato:</td> 
<td><form:select id="combo_inPlataformaTarifacao" path="entity.id.inPlataformaTarifacao" itemValue="key" itemLabel="label" items="${tiposContrato}"/></td>
</tr>

<tr>
<td width="15%">Tipo de Serviço:</td> 
<td><form:select id="inTipoServico" path="entity.id.inTipoServico" itemValue="key" itemLabel="label" items="${tiposServico}"/></td>
</tr>

<tr>
<td width="15%">UF:</td> 
<td><form:select  id="sgUf" path="entity.id.sgUf" itemValue="key" itemLabel="label" items="${listaUF}"/></td>
</tr>

<tr>
<td width="15%">Data Inicial:</td> 
<td><form:input id="dtInicioVigencia" path="entity.id.dtInicioVigencia"/>
	<form:input path="" id="dtInicioVigenciaEdit" style="display: none;"/>
<form:errors path="entity.id.dtInicioVigencia"/></td>
</tr>

<tr>
<td width="15%">Data Final:</td> 
<td><form:input path="entity.dtFimVigencia" id="dtFimVigencia"/><form:errors path="entity.dtFimVigencia"/></td>
</tr>

<tr>
<td width="15%">Alíquota:</td> 
<td><form:input id="pcAliquotaImposto" path="entity.pcAliquotaImposto" size="10" maxlenght="8"/><form:errors path="entity.pcAliquotaImposto"/></td>
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
		$('#combo_noImposto').attr('disabled', 'disabled');

		$('#dtInicioVigencia').hide();
		$('#dtInicioVigenciaEdit').val($('#dtInicioVigencia').val());
		$('#dtInicioVigenciaEdit').show();
		$('#dtInicioVigenciaEdit').attr('disabled', 'disabled');			
		
		$('#noImposto').attr('disabled', 'readonly');
		$('#combo_inPlataformaTarifacao').attr('disabled', 'disabled');
		$('#inTipoServico').attr('disabled', 'disabled');
		$('#sgUf').attr('disabled', 'disabled');
	}
	else if (op == 'CRUD.atualizar') {	
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
		$('#combo_noImposto').attr('disabled', 'disabled');
	
		$('#dtInicioVigencia').hide();
		$('#dtInicioVigenciaEdit').val($('#dtInicioVigencia').val());
		$('#dtInicioVigenciaEdit').show();
		$('#dtInicioVigenciaEdit').attr('disabled', 'disabled');			
		
		$('#noImposto').attr('disabled', 'readonly');
		$('#combo_inPlataformaTarifacao').attr('disabled', 'disabled');
		$('#inTipoServico').attr('disabled', 'disabled');
		$('#sgUf').attr('disabled', 'disabled');
	}
	 else if (op == 'CRUD.novo') {	
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();	
	}
	else if (op == 'erro_validacao')
		{
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
		alert($('#mensagem').val());
		}
	else{		
		$('#salvar_button').show();
		$('#atualizar_button').hide();
		$('#cancelar_button').hide();
		
	}
});
</script>
