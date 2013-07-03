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
	$('#salvar_button').click(salvar);
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#tabs').tabs();
});

	
function salvar()
{
	var r=confirm("Salvar?");
	if (r==true)
		{		
		$('#operacao').val("CRUD.salvar");	
		$('#form1').submit();	
		}
}

function atualizar()
{
	var r=confirm("Atualizar Registro?");
	if (r==true)
		{		
		$('#operacao').val("CRUD.atualizar");	
		$('#form1').submit();	
		}
}

function remover(linha)
{
	var r=confirm("Remover?");
	if (r==true)
		{
		$('#itemSelecionado').val(linha);	
		$('#operacao').val("CRUD.remover_tabela");	
		$('#form1').submit();	
		}	
}


function editar(linha)
{
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("CRUD.editar");	
//	$('#salvar_button').hide();
//	$('#atualizar_button').show();
$('#tabs').tabs('select',1);
	$('#form1').submit();
}

function novo()
{
	$('#tabs').tabs('select',1);
	$('#atualizar_button').hide();
	$('#salvar_button').show();	
}

function pesquisar()
{
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

function cancelar()
{
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
		<form:form modelAttribute="filtro" method="post" action="/scc/user/contrato/produtos/pre/execute.scc" id="form1">
			<form:hidden path="operacao" id="operacao"/>
			<form:hidden path="itemSelecionado" id="itemSelecionado"/>

			<div id="tabs-1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.operadora.claro" /> </td>
						<td> <form:select path="cdEotClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.operadora.ld" /></td>
						<td> <form:select path="cdEotLd" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>


				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
				    <input id="pesquisar_button" type="button" value=<spring:message code="crud.botao.pesquisar"/> />    
				    </td>
				</tr>
				</table>
				<br/>
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					 <tr>
					 	<td>                            
							<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" 
										   requestURI="/scc/user/contrato/produtos/pre/execute.scc" class="ui-state-default">
								<display:column property="operadoraLD" title="Operadora LD" />
								<display:column property="operadoraClaro" title="Operadora Claro" />
								<display:column property="dsCriterioCusto" title="Criterio de Custo" />
								<display:column property="dtInicioContrato" title="Data de Inicio" format="{0,date,dd-MM-yyyy}"/>
								<display:column property="editar" title="Editar"/>								
							</display:table>
						</td>
					</tr>
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
					 	<td>                            
							<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_2"   pagesize="20"  id="repasses" 
										   requestURI="/scc/user/contrato/produtos/pre/execute.scc" class="ui-state-default">
								<display:column property="descProduto" title="Produto" />
								<display:column property="flagRepasse" title="Flag Produto" />
								<display:column property="descTipoEvento" title="Criterio de Custo" />
								<display:column property="flagRepasse" title="Flag Evento" />
							</display:table>
						</td>
					</tr>
				</table>
				<br>
				<br>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<h1>Produto X Contrato</h1>
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.operadora.claro" /> </td>
						<td> <form:input path="entity.cdEotClaro" id="cdEotClaro2" disabled="true"/> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.operadora.ld" /> </td>
						<td> <form:input path="entity.cdEotLd" id="cdEotLd2" disabled="true"/> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.proudto.label.dtiniciocontrato" /> </td>
						<td> <form:input path="entity.dtInicioContrato" id="dtInicioContrato2" disabled="true"/> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.criterio.custo" /> </td>
						<td> <form:input path="entity.dsCriterioCusto" id="dsCriterioCusto2" disabled="true"/> </td>
					</tr>

					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.produto" /> </td>
						<td> <form:select path="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="cadastro.contrato.produto.label.eventos" /></td>
						<td> <form:select path="inCategoriaEvento" items="${eventos}" itemLabel="noTipoEvento" itemValue="id.inCategoriaEvento"></form:select> </td>
					</tr>
					
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
	}
	else{		
		$('#salvar_button').show();
		$('#atualizar_button').hide();
		$('#cancelar_button').hide();	
	}
});
</script>

<% if (request.getAttribute("confirmacao").equals("SIM")) { %>
<script language="javascript">
alert("O registro nao pode ser atualizado!");
</script>
<% } %>

