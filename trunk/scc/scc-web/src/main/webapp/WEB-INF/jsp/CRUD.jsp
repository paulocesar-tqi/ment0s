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
	$('#dtFimVigencia').mask("99/99/9999");
	$('#dtInicioVigencia').datepicker();
	$('#dtFimVigencia').datepicker();	
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#pesquisar_button').click(pesquisar);	
	$('#salvar_button').click(salvar);
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
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();
}

function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

function cancelar()
{
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}

function num(dom){
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
<td width="15%">De:</td> 
<td><form:select path="filtroDe" itemValue="cdStatusCdr" itemLabel="detalhes" items="${listaStatus}"/></td>
</tr>

<tr>
<td width="15%">Para:</td>
<td> <form:select path="filtroPara" itemValue="cdStatusCdr" itemLabel="detalhes" items="${listaStatus}"/></td>
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
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/mapa/tab1.scc" class="ui-state-default">
<display:column property="row.id.cdStatusDe" title="De" />
<display:column property="row.id.cdStatusPara" title="Para" />
<display:column property="dtInicioVigencia" title="Data Início Vigência" />
<display:column property="dtFimVigencia" title="Data Fim Vigência" />
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
<td width="15%">Status De:</td>
<td><form:select path="entity.id.cdStatusDe" id="cdStatusDe" itemValue="cdStatusCdr" itemLabel="detalhes" items="${listaStatusEdicao}"/></td>
</tr>

<tr>
<td width="15%">Status Para:</td>
<td><form:select path="entity.id.cdStatusPara" id="cdStatusPara" itemValue="cdStatusCdr" itemLabel="detalhes" items="${listaStatusEdicao}"/></td>
</tr>

<tr>
<td width="15%">Data Início:</td>
<td> <form:input path="entity.id.dtInicioVigencia" id="dtInicioVigencia"/><form:errors path="entity.id.dtInicioVigencia"/> </td>
</tr>

<tr>
<td width="15%">Data Fim:</td>
<td> <form:input path="entity.dtFimVigencia" id="dtFimVigencia"/> <form:errors path="entity.dtFimVigencia"/></td>
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
