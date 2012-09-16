<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css"
	type="text/css" />
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script>
$(document).ready(function(){
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);
	$('#salvar_button').click(salvar);
	$('#atualizar_button').click(atualizar);
	$( "#dtFimContrato" ).datepicker();
	$( "#dtInicioContrato" ).datepicker();
	$('#outros').hide();
	$('#tabs').tabs();
});

function montaForm(val) {
	if (val.value=="F") {
		$('#retencao').show();
		$('#outros').hide();
	} else {
		$('#outros').show();
		$('#retencao').hide();
	}
}

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
</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="execute.scc"
		id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Operadora Claro:</td>
					<td><form:select path="cdEOTClaro" id="cdEotClaroFiltro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /> <form:errors path="cdEOTLD" /></td>
				</tr>

				<tr>
					<td width="15%">Operadora Externa:</td>
					<td><form:select path="cdEOTLD" id="cdEOTLDFiltro" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap"><input id="pesquisar_button" type="button" value=<spring:message code="crud.botao.pesquisar"/> /></td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" decorator="com.claro.sccweb.decorator.view.SccContratoCobillingSearchDecorator" pagesize="20" id="repasses" requestURI="/scc/user/contrato/pre/tab1.scc" class="ui-state-default">
							<display:column property="dsOperadoraLD" title="Longa Distância" />
							<display:column property="dsOperadoraClaro" title="Operadora Claro" />
							<display:column property="dtInicioContrato" title="Início" />
							<display:column property="dtFinalContrato" title="Fim" />
							<display:column property="dsCriterioCusto" title="Critério de Custo" />
							<display:column property="valorAssociadoCriterioCusto" title="Vlr. Associado CC" />
							<display:column property="vlCriterioCustoLiquido" title="Vlr. Cc Líquido" />
							<display:column property="dsPeriodoBase" title="Repasse" />
							<display:column property="valorCPMF" title="CPMF" />
							<display:column property="valorISS" title="ISS" />
							<display:column property="editar" title="Editar" />
							<display:column property="remover" title="Remover" />
						</display:table>
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap"><input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> /></td>
				</tr>
			</table>
		</div>
		<div id="tabs-2" style="height: 500px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<!-- <form:hidden path="entity.id.cdTipoContrato" id="cdTipoContrato" value="P" /> -->
				<form:hidden path="entity.dtCriacao" id="dtCriacao" />
				<tr>
					<td width="15%">Operadora Claro:</td>
					<td><form:select path="entity.id.cdEotClaro" id="cdEotClaro" items="${operadorasClaroNotNull}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
				</tr>
				
				<tr>
					<td width="15%">Operadora Externa:</td>
					<td><form:select path="entity.id.cdEotLd" id="cdEotLd" items="${operadorasExternasnotNull}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
				</tr>
				
				<tr>
					<td width="15%">Data Inicio Contrato:</td>
					<td><form:input id="dtInicioContrato" path="entity.id.dtInicioContrato" /> <form:errors path="entity.id.dtInicioContrato" /></td>
				</tr>
				
				<tr>
					<td width="15%">Data Final do Contrato:</td>
					<td><form:input id="dtFimContrato" path="entity.dtFimContrato" /> <form:errors path="entity.dtFimContrato" /></td>
				</tr>
				
				<tr>
					<td width="15%">Tipo Contrato:</td>
					<td>
						<form:select id="cdTipoContrato" path="entity.id.cdTipoContrato" onchange="montaForm(this);">
							<form:option value="F" label="Novo Contrato" />
							<form:option value="A" label="Arrecadado" />
							<form:option value="P" label="Pre Pago" />
						</form:select>
					</td>
				</tr>
				
				<tr>
					<td width="15%">Período Repasse:</td>
					<td><form:select path="entity.dsPeriodoRepasse" id="dsPeriodoRepasse" items="${periodos}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
					<td width="15%">Critério de Custo:</td>
					<td><form:select path="entity.dsCriterioCusto" id="dsCriterioCusto" items="${criterios}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
					<td width="15%">Valor Critério de Custo Líquido:</td>
					<td><form:input path="entity.vlCriterioCustoLiquido" size="16" maxlength="15" /> <form:errors path="entity.vlCriterioCustoLiquido" /></td>
				</tr>
				
				<tr>
					<td width="15%">Valor Associado Critério de Custo:</td>
					<td><form:input path="entity.vlAssociadoCriterioCusto" size="16" maxlength="15" /> <form:errors path="entity.vlAssociadoCriterioCusto" /></td>
				</tr>
				
				<tr>
					<td width="15%">Percentual CPMF:</td>
					<td><form:input path="entity.vlCpmf" size="16" maxlength="15" /><form:errors path="entity.vlCpmf" /></td>
				</tr>
				
				<tr>
					<td width="15%">Percentual ISS:</td>
					<td><form:input path="entity.vlIss" size="16" maxlength="15" /><form:errors path="entity.vlIss" /></td>
				</tr>
			</table>
				
				
				
		<div id="retencao">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Percentual Reten&ccedil;&atilde;o:</td>
					<td><form:input path="entity.peRetencao" size="16" maxlength="15" /><form:errors path="entity.peRetencao" /></td>
				</tr>
			</table>
		</div>
				
		<div id="outros" style="display:block;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Percentual Pis:</td>
					<td><form:input path="entity.pePis" size="12" maxlength="11" /><form:errors path="entity.pePis" /></td>
				</tr>
				
				<tr>
					<td width="15%">Percentual Cofins:</td>
					<td><form:input path="entity.peCofins" size="12" maxlength="11" /> <form:errors path="entity.peCofins" /></td>
				</tr>
				
				<tr>
					<td width="15%">Repassa ICMS:</td>
					<td><form:checkbox path="entity.flRepassaCpmf" value="S" /></td>
				</tr>
				
				<tr>
					<td width="15%">Repassa CPMF:</td>
					<td><form:checkbox path="entity.flRepassaIcms" value="S" /></td>
				</tr>
			</table>
		</div>
			
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap"><input id="atualizar_button" type="button" value=<spring:message code="crud.botao.atualizar"/> /> <input id="salvar_button" type="button" value=<spring:message code="crud.botao.salvar"/> /></td>
				</tr>
			</table>
		</div>
	</form:form>
</div>
<script>
$(function() {
	var op = $('#operacao').val();
	$('#tabs').tabs('select',${filtro.activeTab});
	if (op == 'CRUD.editar'){
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#atualizar_button').show();
	}
	
});
</script> 
