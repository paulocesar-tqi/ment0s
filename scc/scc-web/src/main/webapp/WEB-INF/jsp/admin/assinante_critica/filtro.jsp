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
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#salvar_button').click(salvar);
	$('#pesquisar_button').click(pesquisar);	
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
	$('#novo_button').attr('disabled', 'disabled');	
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
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<form:hidden path="entity.cdUsuarioManut" id="cdUsuarioManut"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tabela_filtro">
<tr>
<td width="15%">Assinante Crítica:</td><td> <form:select path="cdCritica" id="cdCritica" items="${codigos}" itemValue="cdCritica" itemLabel="dsCritica"/>  
<input type="button" id="pesquisar_button" value="Pesquisar"/>  </td>
</tr>
</table>
<br/>
<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/critica/tab1.scc" class="ui-state-default">
<display:column property="row.id.cdArea" title="Cd. Área" />
<display:column property="row.id.nuPrefixo" title="Nu. Prefixo" />
<display:column property="row.id.nuSufixoInicial" title="Faixa Inicial" />
<display:column property="row.id.nuSufixoFinal" title="Faixa Final" />
<display:column property="row.id.cdCsp" title="CSP" />
<display:column property="row.criticaPreBilling.cdCritica" title="Cd. Crítica" />
<display:column property="dtInicioVigencia" title="Dt. Início Vigência" />
<display:column property="dtFimVigencia" title="Dt. Fim Vigência" />
<display:column property="editar" title="Editar" />
<display:column property="remover" title="Remover" />
</display:table>
</td></tr>
</table>
</c:if>

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
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="tabela_edicao">
<tr>
<td width="15%">Critica:</td>
<td><form:select path="entity.criticaPreBilling.cdCritica" items="${codigosEdicao}" itemValue="cdCritica" itemLabel="dsCritica"/></td>
</tr>


<tr>
<td width="15%">Código de Área:</td>
<td><form:input path="entity.id.cdArea" maxlength="2" size="3" onkeyup="num(this)"/><form:errors path="entity.id.cdArea"/></td>
</tr>


<tr>
<td width="15%">Número Prefixo:</td>
<td><form:input path="entity.id.nuPrefixo" maxlength="4" size="6" onkeyup="num(this)"/><form:errors path="entity.id.nuPrefixo"/></td>
</tr>


<tr>
<td width="15%">Número Faixa Inicial:</td>
<td><form:input path="entity.id.nuSufixoInicial" maxlength="4" size="6" onkeyup="num(this)"/><form:errors path="entity.id.nuSufixoInicial"/></td>
</tr>

<tr>
<td width="15%">Número Faixa Final:</td>
<td><form:input path="entity.id.nuSufixoFinal" maxlength="4" size="6" onkeyup="num(this)"/><form:errors path="entity.id.nuSufixoFinal"/></td>
</tr>

<tr>
<td width="15%">CSP:</td>
<td><form:select path="entity.id.cdCsp" items="${csp}" itemValue="cdCsp" itemLabel="cdCsp"/></td>
</tr>


<tr>
<td width="15%">Data Início Vigência:</td>
<td><form:input path="entity.dtInicioVigencia" id="dtInicioVigencia" /><form:errors path="entity.dtInicioVigencia"/></td>
</tr>

<tr>
<td width="15%">Data Final Vigência:</td>
<td><form:input path="entity.dtFimVigencia" id="dtFimVigencia" /><form:errors path="entity.dtInicioVigencia"/></td>
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
	$('#pesquisar_button').removeAttr('disabled');
	$('#novo_button').removeAttr('disabled');	
	$('#tabs').tabs('select',${filtro.activeTab});
	var op = $('#operacao').val();
	if (op == 'CRUD.editar') {
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
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
	else if(op == 'CRUD.atualizar'){
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
