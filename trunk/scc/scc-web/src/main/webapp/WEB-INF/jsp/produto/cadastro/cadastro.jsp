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
	$("#dtFimVigencia").datepicker();
	$("#dtInicioVigencia").datepicker();
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
<form:form modelAttribute="filtro" method="post" action="/scc/user/produto/cadastro/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<form:hidden path="entity.cdProdutoCobilling" id="cdProdutoCobilling"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/produto/cadastro/tab1.scc" class="ui-state-default">
<display:column property="produto" title="Produto" />
<display:column property="tipoProduto" title="Tipo do Produto" />
<display:column property="dataInicioVigencia" title="Data Início Vigência" />
<display:column property="dataFinalVigencia" title="Data Final Vigência" />
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


<tr><td width="15%">Produto:</td>
<td>
<form:input path="entity.noProdutoCobilling" id="noProdutoCobilling" size="52" maxlength="50"/><form:errors path="entity.noProdutoCobilling"/>
</td></tr>

<tr><td width="15%">Tipo do Produto:</td>
<td>
<form:input path="entity.cdTipoProduto" id="cdTipoProduto" size="5" maxlength="3"/>
</td></tr>

<tr><td width="15%">Data Início Vigência:</td>
<td>
<form:input path="entity.dtInicioVigencia" id="dtInicioVigencia"/><form:errors path="entity.dtInicioVigencia"/>
</td></tr>

<tr><td width="15%">Data Final Vigência:</td>
<td>
<form:input path="entity.dtFimVigencia" id="dtFimVigencia"/><form:errors path="entity.dtFimVigencia"/>
</td></tr>



</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="atualizar_button" type="button" value=<spring:message code="crud.botao.atualizar"/> />    
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
		$('#atualizar_button').show();	
	}
	else if (op == 'novo'){
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();		 
	}
	else if(op == 'CRUD.atualizar'){
		$('#salvar_button').hide();
		$('#atualizar_button').show();
	}
		
});
</script>
