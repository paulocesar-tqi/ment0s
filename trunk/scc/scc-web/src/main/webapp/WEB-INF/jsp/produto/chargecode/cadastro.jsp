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
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);
	$('#salvar_button').click(salvar);		
	$('#atualizar_button').click(atualizar);
	$("#dtInicioVigencia").mask("99/99/9999");	
	$("#dtFimVigencia").mask("99/99/9999");
	$('#cancelar_button').click(cancelar);
	$("#dtFimVigencia").datepicker();
	$("#dtInicioVigencia").datepicker();
	$('#pesquisar_button').click(pesquisar);
	$('#tabs').tabs();
});

function cancelar()
{
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}
	
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
	$('#salvar_button').hide();
	$('#atualizar_button').show();
	$('#form1').submit();
}

function novo()
{			
	$('#operacao').val("novo");
	$('#form1').submit();		
}

function pesquisar()
{
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/produto/chargecode/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
<tr><td width="15%">Produto:</td>
<td> <form:select path="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling"></form:select> 
<input id="pesquisar_button" type="button" value=<spring:message code="crud.botao.pesquisar"/> /></td>
</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/produto/chargecode/tab1.scc" class="ui-state-default">
<display:column property="chargeCode" title="Charge Code" />
<display:column property="tipoCharge" title="Tipo Charge" />
<display:column property="dataCriacao" title="Dt. Criação" />
<display:column property="dataAtualizacao" title="Dt. Atualização" />
<display:column property="usuario" title="Usuário Manutenção" />
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

<tr><td width="15%">Charge Code:</td>
<td>
<form:input path="entity.cdCharge" id="cdCharge" size="18" maxlength="15"/><form:errors path="entity.cdCharge"/>
</td></tr>


<tr><td width="15%">Produto:</td>
<td>
<form:select id="selProdutos"  path="entity.sccProdutoCobilling.cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
</td></tr>

<tr><td width="15%">Tipo Charge:</td>
<td>
<form:select id="cdTipoCharge"  path="entity.cdTipoCharge" items="${tiposCharge}" itemLabel="label" itemValue="key" />
</td></tr>

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
		$('#atualizar_button').show();	
		$('#selProdutos').attr('disabled', 'disabled');
		$('#cdCharge').attr('readonly', 'readonly');
	}
	else if (op == 'novo'){
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();	
		$('#selProdutos').removeAttr('disabled');
		$('#cdCharge').removeAttr('readonly');
	}
	else if (op == 'cancelar'){
		$('#tabs').tabs('select',0);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
	}
	else if(op == 'CRUD.atualizar'){
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
	}
	else{		
		$('#selProdutos').removeAttr('disabled');
		$('#cdCharge').removeAttr('readonly');
	}
});
</script>
