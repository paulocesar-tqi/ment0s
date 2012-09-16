<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css" />
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
		$( "#dtInicioVigencia" ).datepicker();
		$( "#dtFimVigencia" ).datepicker();
		$('#cancelar_button').click(cancelar);
		$('#tabs').tabs();
	});
	
	function salvar() {
		var r=confirm("Salvar?");
		if (r==true)
			{		
			$('#operacao').val("CRUD.salvar");	
			$('#form1').submit();	
			}
	}
	
	function repasse(cdContrato,cdPRoduto) {	
		$("#form1").attr("action", "/scc/user/contrato/produtos/repasse/select/"+cdContrato+"/"+cdPRoduto+".scc");	
		$('#form1').submit();	
	}
	
	function periodicidade(cdContrato,cdPRoduto)
	{	
		$("#form1").attr("action", "/scc/user/contrato/produtos/periodicidade/select/"+cdContrato+"/"+cdPRoduto+".scc");	
		$('#form1').submit();	
	}
	
	function atualizar() {
		var r=confirm("Atualizar Registro?");
		if (r==true)
			{		
			$('#operacao').val("CRUD.atualizar");	
			$('#form1').submit();	
			}
	}
	
	function remover(linha) {
		var r=confirm("Remover?");
		if (r==true)
			{
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
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="15%">Contrato:</td>
					<td>
						<form:select path="cdContratoCobilling" id="cdContratoCobilling" items="${contratos}" itemLabel="dsContratoCobilling" itemValue="cdContratoCobilling" />
						<input id="pesquisar_button" type="button" value=<spring:message code="crud.botao.pesquisar"/> />
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/contrato/produtos/tab1.scc" class="ui-state-default">
						<display:column property="contrato" title="Contrato" />
						<display:column property="produto" title="Produto" />
						<display:column property="produtoPadrao" title="Produto Padrão" />
						<display:column property="inicio" title="Início" />
						<display:column property="fim" title="Fim" />
						<display:column property="repasse" title="Repasse" />
						<display:column property="periodicidade" title="Periodicidade" />
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
				<form:hidden path="entity.dtCriacao" id="dtCriacao" />
				<form:hidden path="entity.cdUsuarioManut" id="cdUsuarioManut" />
				<form:hidden path="entity.id.cdContratoCobilling" id="sccContratoCobl.cdContratoCobilling" />
				<tr>
					<td width="15%">Produto:</td>
					<td>
						<form:select path="entity.id.cdProdutoCobilling" id="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
					</td>
				</tr>
				<tr>
					<td width="15%">Data Inicial:</td>
					<td>
						<form:input id="dtInicioVigencia" path="entity.dtInicioVigencia" />
						<form:errors path="entity.dtInicioVigencia" />
					</td>
				</tr>
				<tr>
					<td width="15%">Data Final:</td>
					<td>
						<form:input id="dtFimVigencia" path="entity.dtFimVigencia" />
						<form:errors path="entity.dtFimVigencia" />
					</td>
				</tr>
				<tr>
					<td width="15%">Produto Padrão:</td>
					<td>
						<form:checkbox path="entity.fgProdutoPadrao" value="S" />
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
		var op = $('#operacao').val();
		$('#tabs').tabs('select',${filtro.activeTab});
		if (op == 'CRUD.editar'){
			$('#tabs').tabs('select',1);
			$('#salvar_button').hide();
			$('#cancelar_button').show();
			$('#atualizar_button').show();
		} else if(op == 'CRUD.atualizar'){
			$('#salvar_button').hide();
			$('#cancelar_button').show();
			$('#atualizar_button').show();
		}else{			
			$('#salvar_button').show();
			$('#atualizar_button').hide();
			$('#cancelar_button').hide();		
		}
	});
</script>
