<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css" />
 <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script>
$(document).ready(function(){
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);
	//$('#salvar_button').click(salvar);
	//$('#atualizar_button').click(atualizar);
	//$('#dtFimContrato').datepicker();
	//$('#dtInicioContrato').datepicker();
	$('#outros').hide();
	$('#tabs').tabs();
});

$(function() {
	$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
	$( ".datepicker" ).datepicker();
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
		//$('#form1').submit();	
		pesquisar();
	}
}

function atualizar() {
	var r=confirm("Atualizar Registro?");
	if (r==true) {
		$('#operacao').val("CRUD.atualizar");	
		pesquisar();
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
	limpar();
}


$('body').delegate('.save_btn', 'click', function() {
	
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			type: "POST",
			url : "/scc/user/contrato/pre/salvarEntity.scc",
			data: dados,
			success : function(data) {
				$('#salvar_button').hide();
				$('#atualizar_button').hide();
				$('#form1').submit();
				$('#tabs').tabs('select',0);
				
			
			}
		});
	}
});



$('body').delegate('.update_btn', 'click', function() {
	
	atualizar();
	$('#atualizar_button').hide();
	$('#salvar_button').hide();		
	$('#tabs').tabs('select',0);		

});


$('body').delegate('.delete_contrato', 'click', function() {
	 var r=confirm("Remover?");
	 if(r==true){
		 	var dados = $("#form1").serialize();
			$.ajax({
				type: "DELETE",
				url : this.href,
				data: dados,
				success : function(data) {
					$('#form1').submit();
				}
			});
		}
	 return false;
});


function limpar(){
	$('#cdEotClaroCad').attr('value', "");
	$('#cdEotLdCad').attr('value', "");
	$('#dtInicioContrato').attr('value', "");
	$('#dtFimContrato').attr('value', "");
	$('#cdTipoContrato').attr('value', "");
	$('#dsPeriodoRepasse').attr('value', "");						
	$('#dsCriterioCusto').attr('value', "");
	$('#vlCriterioCustoLiquido').attr('value', "");
	$('#vlAssociadoCriterioCusto').attr('value', "");
	$('#vlCpmf').attr('value', "");
	$('#vlIss').attr('value', "");
	$('#peRetencao').attr('value', "");
	$('#pePis').attr('value', "");
	$('#peCofins').attr('value', "");
	$('#flRepassaCpmf').attr('value', "");
	$('#flRepassaIcms').attr('value', "");
//	$('#dtCriacao').attr('value', new Date());
	$('#cdUsuarioManut').attr('value', "");
}


function atribuirValor(data){
	$('#cdEotClaroCad').attr('value', data.id.cdEotClaro);
	$('#cdEotLdCad').attr('value', data.id.cdEotLd);
	$('#dtInicioContrato').attr('value', formatarDataPtBr(data.id.dtInicioContrato));
	$('#dtFimContrato').attr('value', formatarDataPtBr(data.dtFimContrato));
	$('#cdTipoContrato').attr('value', data.cdTipoContrato);
	$('#dsPeriodoRepasse').attr('value', data.dsPeriodoRepasse);						
	$('#dsCriterioCusto').attr('value', data.dsCriterioCusto);
	$('#vlCriterioCustoLiquido').attr('value', data.vlCriterioCustoLiquido);
	$('#vlAssociadoCriterioCusto').attr('value', data.vlAssociadoCriterioCusto);
	$('#vlCpmf').attr('value', data.vlCpmf);
	$('#vlIss').attr('value', data.vlIss);
	$('#peRetencao').attr('value', data.peRetencao);
	$('#pePis').attr('value', data.pePis);
	$('#peCofins').attr('value', data.peCofins);
	$('#flRepassaCpmf').attr('value', data.flRepassaCpmf);
	$('#flRepassaIcms').attr('value', data.flRepassaIcms);
	$('#dtCriacao').attr('value', formatarDataPtBr(data.dtCriacao));
	$('#cdUsuarioManut').attr('value', data.cdUsuarioManut);
}


$('body').delegate('.edit_contrato', 'click', function() {
	 
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			
			url : this.href,
			success : function(data) {
				atribuirValor(data);
				$('#tabs').tabs('select',1);
				$('#atualizar_button').show();
				$('#salvar_button').hide();				
				
			}
		});

	return false;
});

$('body').delegate('.edit_contrato', 'click', function() {
	 var r=confirm("Remover?");
	 if(r==true){
		 	var dados = $("#form1").serialize();
			$.ajax({
				cache:false,
				type: "DELETE",
				url : this.href,
				data: dados,
				success : function(data) {
					$('#form1').submit();
				}
			});
		}
	 return false;
});


function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
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
		<form:hidden path="entity.cdUsuarioManut" id="cdUsuarioManut" />
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Operadora Claro:</td>
					<td><form:select path="cdEOTClaro" id="cdEotClaroFiltro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /> <form:errors path="cdEOTLD" /></td>
				</tr>

				<tr>
					<td width="15%">Operadora Externa:</td>
					<td><form:select path="cdEOTLD" id="cdEOTLDFiltro" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				
				<tr>
					<td width="15%">Tipo Contrato:</td>
					<td><form:select path="cdTipo" id="cdTipo" items="${tipos}" itemLabel="label" itemValue="key" /> </td>
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
						<display:table style="width:90%" name="requestScope.filtro.listContratoSearchView" decorator="com.claro.sccweb.decorator.view.SccContratoCobillingSearchDecorator" pagesize="20" id="repasses" requestURI="/scc/user/contrato/pre/tab1.scc" class="ui-state-default">
							<display:column property="dsOperadoraLD" title="Longa Distância" />
							<display:column property="dsOperadoraClaro" title="Operadora Claro" />
							<display:column property="dtInicioContrato" title="Início" />
							<display:column property="dtFinalContrato" title="Fim" />
							<display:column property="cdTipoContrato" title="Tipo de Contrato" />
							<display:column property="dsCriterioCusto" title="Critério de Custo" />
							<display:column title="Vlr. Associado CC">
								<fmt:formatNumber type="number" maxFractionDigits="4" value="${repasses.valorAssociadoCriterioCusto}"/>							
							</display:column>
							<display:column title="Vlr. Cc Líquido">
								<fmt:formatNumber type="number" maxFractionDigits="5" value="${repasses.vlCriterioCustoLiquido}"/>							
							</display:column>
							<display:column property="dsPeriodoBase" title="Repasse" />
							<display:column property="valorCPMF" title="CPMF" />
							<display:column property="valorISS" title="ISS" />
							<display:column property="editar" title="Editar" />
							<display:column property="remover" title="Remover" />
							
							<display:column title="Editar"><a href="editarContratoPre.scc?cdEotLd=${repasses.cdEOTLD}&cdEotClaro=${repasses.cdEOTClaro}&dtInicioContrato=${repasses.dtInicioContrato}&cdTipoContrato=${repasses.cdTipoContrato}" 
																class="edit_contrato" >
																<img  src="/scc/images/editIcon.gif"></a>
							</display:column>
							
							<display:column title="Remover"><a href="removerContratoPre.scc?cdEotLd=${repasses.cdEOTLD}&cdEotClaro=${repasses.cdEOTClaro}&dtInicioContrato=${repasses.dtInicioContrato}&cdTipoContrato=${repasses.cdTipoContrato}" 
																class="delete_contrato" >
																<img  src="/scc/images/delete.gif"></a>
							</display:column>
							
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
					<td><form:select path="entity.id.cdEotClaro" id="cdEotClaroCad" items="${operadorasClaroNotNull}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				
				<tr>
					<td width="15%">Operadora Externa:</td>
					<td><form:select path="entity.id.cdEotLd" id="cdEotLdCad" items="${operadorasExternasnotNull}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				
				<tr>
					<td width="15%">Data Inicio Contrato:</td>
					<td><form:input id="dtInicioContrato" path="entity.id.dtInicioContrato" cssClass="datepicker" /> <form:errors path="entity.id.dtInicioContrato" /></td>
				</tr>
				
				<tr>
					<td width="15%">Data Final do Contrato:</td>
					<td><form:input id="dtFimContrato" path="entity.dtFimContrato" cssClass="datepicker"/> <form:errors path="entity.dtFimContrato" /></td>
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
					<td><form:select path="entity.dsPeriodoRepasse" id="dsPeriodoRepasse" cssClass="datepicker" items="${periodos}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
					<td width="15%">Critério de Custo:</td>
					<td><form:select path="entity.dsCriterioCusto" id="dsCriterioCusto" items="${criterios}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
					<td width="15%">Valor Critério de Custo Líquido:</td>
					<td><form:input id="vlCriterioCustoLiquido" path="entity.vlCriterioCustoLiquido" size="16" maxlength="15" /> <form:errors path="entity.vlCriterioCustoLiquido" /></td>
				</tr>
				
				<tr>
					<td width="15%">Valor Associado Critério de Custo:</td>
					<td><form:input id="vlAssociadoCriterioCusto" path="entity.vlAssociadoCriterioCusto" size="16" maxlength="15" /> <form:errors path="entity.vlAssociadoCriterioCusto" /></td>
				</tr>
				
				<tr>
					<td width="15%">Percentual CPMF:</td>
					<td><form:input id="vlCpmf" path="entity.vlCpmf" size="16" maxlength="15" /><form:errors path="entity.vlCpmf" /></td>
				</tr>
				
				<tr>
					<td width="15%">Percentual ISS:</td>
					<td><form:input id ="vlIss" path="entity.vlIss" size="16" maxlength="15" /><form:errors path="entity.vlIss" /></td>
				</tr>
			</table>
				
				
				
		<div id="retencao">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Percentual Reten&ccedil;&atilde;o:</td>
					<td><form:input  id="peRetencao" path="entity.peRetencao" size="16" maxlength="15" /><form:errors path="entity.peRetencao" /></td>
				</tr>
			</table>
		</div>
				
		<div id="outros" style="display:block;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Percentual Pis:</td>
					<td><form:input id="" path="entity.pePis" size="12" maxlength="11" /><form:errors path="entity.pePis" /></td>
				</tr>
				
				<tr>
					<td width="15%">Percentual Cofins:</td>
					<td><form:input id="peCofins" path="entity.peCofins" size="12" maxlength="11" /> <form:errors path="entity.peCofins" /></td>
				</tr>
				
				<tr>
					<td width="15%">Repassa ICMS:</td>
					<td><form:checkbox id="flRepassaCpmf" path="entity.flRepassaCpmf" value="S" /></td>
				</tr>
				
				<tr>
					<td width="15%">Repassa CPMF:</td>
					<td><form:checkbox id="flRepassaIcms" path="entity.flRepassaIcms" value="S" /></td>
				</tr>
			</table>
		</div>
			
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="atualizar_button" class="update_btn" type="button" value=<spring:message code="crud.botao.atualizar"/> /> 
						<input id="salvar_button" class="save_btn" type="button" value=<spring:message code="crud.botao.salvar"/> />
					</td>
				</tr>
			</table>
		</div>
	</form:form>
</div>
