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
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />    
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
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/rejeicao/tab1.scc" class="ui-state-default">
<display:column property="row.cdMotivoRejeicao" title="Código" />
<display:column property="row.dsMotivoRejeicao" title="Descrição" />
<display:column property="row.txDetalhamentoMotivo" title="Detalhamento" />
<display:column property="row.txComentarioMotivo" title="Comentário" />
<display:column property="row.cdClassificacaoMotivo" title="Classificação" />
<display:column property="row.inTipoClassificacao" title="Tipo" />
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
<td width="15%">Código:</td>
<td><form:input path="entity.cdMotivoRejeicao" id="cdMotivoRejeicao" size="4" maxlength="3"/>
	<form:input path="" id="cdMotivoRejeicaoEdit" size="4" style="display: none;"/>
	<form:errors path="entity.cdMotivoRejeicao"/></td>
</tr>

<tr>
<td width="15%">Descrição:</td>
<td><form:input path="entity.dsMotivoRejeicao" id="dsMotivoRejeicao" size="101" maxlength="100"/><form:errors path="entity.dsMotivoRejeicao"/></td>
</tr>

<tr>
<td width="15%">Detalhamento:</td>
<td><form:textarea path="entity.txDetalhamentoMotivo" id="txDetalhamentoMotivo" size="1001" maxlength="1000" cols="100"/><form:errors path="entity.txDetalhamentoMotivo"/></td>
</tr>

<tr>
<td width="15%">Comentário:</td>
<td><form:textarea path="entity.txComentarioMotivo" id="txComentarioMotivo" size="1001" maxlength="1000" cols="100"/></td>
</tr>

<tr>
<td width="15%">Classificação:</td>
<td><form:input path="entity.cdClassificacaoMotivo" id="cdClassificacaoMotivo" size="3" maxlength="2" onkeyup="num(this)"/><form:errors path="entity.cdClassificacaoMotivo"/></td>
</tr>

<tr>
<td width="15%">Tipo Classificação:</td>
<td><form:input path="entity.inTipoClassificacao" id="inTipoClassificacao" size="2" maxlength="1" /><form:errors path="entity.inTipoClassificacao"/></td>
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

		$('#cdMotivoRejeicao').hide();
		$('#cdMotivoRejeicaoEdit').val($('#cdMotivoRejeicao').val());
		$('#cdMotivoRejeicaoEdit').show();
		$('#cdMotivoRejeicaoEdit').attr('disabled', 'disabled');
		
	} else if (op == 'CRUD.novo') {	
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();	
	}
	else if (op == 'erro_validacao'){
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
		alert($('#mensagem').val());
	}
	else if(op == 'CRUD.atualizar'){
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();

		$('#cdMotivoRejeicao').hide();
		$('#cdMotivoRejeicaoEdit').val($('#cdMotivoRejeicao').val());
		$('#cdMotivoRejeicaoEdit').show();
		$('#cdMotivoRejeicaoEdit').attr('disabled', 'disabled');
	}
	else{		
		$('#salvar_button').show();
		$('#atualizar_button').hide();
		$('#cancelar_button').hide();		
	}
});
</script>
