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
	$('#salvar_button').click(salvar);	
	$('#holding').click(clickHolding);	
	$('#tabs').tabs();
});


function clickHolding()
{
if ($('#holding').attr('checked'))
	{
	$('#cdOperadoraHolding').attr('disabled', 'disabled');
	}
else
	{
	$('#cdOperadoraHolding').removeAttr('disabled');	
	}
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
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();
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

function num(dom){
    dom.value=dom.value.replace(/\D/g,""); 
}


$('body').delegate('.tipoOper', 'change', function() {
	$('#tipoServico option').each(function(){           
		if($('#tipoServico').val() == 'C'){               
			$('#holding').attr('disabled','disabled');
			$('#cdOperadoraHolding').attr('disabled','disabled');
			$('#operContabil').attr('disabled','disabled');

		}
		if($('#tipoServico').val() == 'M'){               
			$('#holding').removeAttr('disabled');	
			$('#cdOperadoraHolding').removeAttr('disabled');	
			$('#operContabil').removeAttr('disabled');	
			
		}       
	});

});

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
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/operadora/tab1.scc" class="ui-state-default">
<display:column property="row.sgUf" title="UF" />
<display:column property="row.cdTipoServico" title="Tipo de Serviço" />
<display:column property="row.cdEot" title="Operadora" />
<display:column property="EOTHolding" title="Holding" />
<display:column property="row.cdLocalNegocio" title="Local de Negócio" />
<display:column property="row.cdEmpresaContabil" title="Operadora Claro Contábil" />
<display:column property="row.cdRegional" title="Regional" />
<display:column property="row.dsOperadora" title="Nome" />
<display:column property="CSP" title="CSP" />
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
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Tipo de Serviço:</td>
<td><form:select id="tipoServico" cssClass="tipoOper" path="entity.cdTipoServico" 
				 items="${tiposServico}" itemValue="key" itemLabel="label">
				 <form:option value="${item.key}">
				 </form:option>
	</form:select>
</td>
</tr>

<tr>
<td width="15%">UF:</td>
<td><form:input path="entity.sgUf" maxlength="2"/><form:errors path="entity.sgUf"/></td>
</tr>

<tr>
<td width="15%">EOT da Operadora:</td>
<td><form:input path="entity.cdEot" maxlength="3"/><form:errors path="entity.cdEot"/></td>
</tr>

<tr>
<td width="15%">Nome da Operadora:</td>
<td><form:input path="entity.dsOperadora" maxlength="20"/><form:errors path="entity.dsOperadora"/></td>
</tr>
<tr>
<td width="15%">CSP:</td>
<td><form:input path="entity.cdCsp" maxlength="2"/></td>
</tr>


<tr>
<td width="15%">Holding:</td>
<td><form:checkbox path="holding" value="S" id="holding"/></td>
</tr>


<tr>
<td width="15%">Claro Holding:</td>
<td><form:select path="entity.cdOperadoraHolding" id="cdOperadoraHolding" items="${holdingClaro}" itemValue="cdEot" itemLabel="dsOperadoraForCombos"/></td>
</tr>


<tr>
<td width="15%">Operadora Claro Contábil:</td>
<td><form:select id="operContabil" path="entity.cdEmpresaContabil" items="${empresasContabeis}" itemValue="key" itemLabel="label"/></td>
</tr>

<tr>
<td width="15%">Código do Local de Negócio:</td>
<td><form:input path="entity.cdLocalNegocio" maxlength="4" onkeyup="num(this)"/></td>
</tr>


<tr>
<td width="15%">Regional:</td>
<td><form:input path="entity.cdRegional" maxlength="4" onkeyup="num(this)"/>
</td>
</tr>


<tr>
<td width="15%">Qtd. Dias Expira Chamada Int:</td>
<td><form:input path="entity.qtDiasChamadaIntExpira" maxlength="3" onkeyup="num(this)"/>
</td>
</tr>

<tr>
<td width="15%">Qtd. Dias Expira Chamada Nac:</td>
<td><form:input path="entity.qtDiasChamadaNacExpira" maxlength="3" onkeyup="num(this)"/>
</td>
</tr>

<tr>
<td width="15%">Qtd. Dias Exclusão: </td>
<td><form:input path="entity.qtDiasExclusao" maxlength="3" onkeyup="num(this)"/>
</td>
</tr>

<tr>
<td width="15%">Qtd. Limite Idade Int:</td>
<td><form:input path="entity.qtLimiteIdadeInt" maxlength="3" onkeyup="num(this)"/></td>
</tr>

<tr>
<td width="15%">Qtdd Limite Idade Nac:</td>
<td><form:input path="entity.qtLimiteIdadeNac" maxlength="3" onkeyup="num(this)"/></td>
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
