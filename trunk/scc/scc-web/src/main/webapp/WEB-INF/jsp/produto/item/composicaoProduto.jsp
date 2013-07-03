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
	$('#pesquisar_button').click(pesquisar);
	$('#salvar_button').click(salvar);		
	$('#atualizar_button').click(atualizar);	
	$('#cancelar_button').click(cancelar);
	$('#tabs').tabs();
});

$(function() {
    $( "#tabs" ).tabs();
});


function cancelar()
{
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}

	



$('body').delegate('.edit_composicao', 'click', function() {
	 
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			//data:dados,
			url : this.href,
			success : function(data) {
				atribuirValor(data);
				$('#atualizar_button').show();					
				$('#salvar_button').hide();		
				$('#tabs').tabs('select',1);
				
			}
		});

	return false;
});

$('body').delegate('.delete_composicao', 'click', function() {
	 var r=confirm("Remover?");
	 if(r==true){
		 	var dados = $("#form1").serialize();
			$.ajax({
				cache:false,
				type: "DELETE",
				url : this.href,
				data:dados,				
				success : function(data) {
					$('#form1').submit();
				}
			});
		}
	 return false;
});



function atribuirValor(data){
	var cdComponenteProduto = data.cdComponenteProduto;
	var cdProdutoCobilling = data.sccProdutoCobilling.cdProdutoCobilling;
	var cdItemCobilling = data.sccItemCobilling.cdItemCobilling;
	var cdMotivoRetarifacao = data.cdMotivoRetarifacao;
	var dtCriacao = data.dtCriacao;
	$('#cdComponenteProduto').attr('value', cdComponenteProduto);
	$('#selProdutos').attr('value', cdProdutoCobilling);
	$('#cdItemCobilling').attr('value', cdItemCobilling);
	$('#cdMotivoRetarifacao').attr('value', cdMotivoRetarifacao);
	$('#selProdutos').attr('disabled', 'disabled');
	//$('#cdItemCobilling').attr('disabled', 'disabled');
	$('#dtCriacao').attr('value', formatarDataPtBr(dtCriacao));	
	$('#cdProdutoCobilling').attr('value', cdProdutoCobilling);
}

function atualizar(){

	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/produto/item/updateEntity.scc",
			data: dados,
			success : function(data) {
				limpar();
				$('#form1').submit();
				$('#tabs').tabs('select',0);						
			}
		});
	}
}

function salvar(){

	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/produto/item/saveEntity.scc",
			data: dados,
			success : function(data) {
				$('#operacao').attr('value', 'pesquisar');
				limpar();
				$('#form1').submit();
				
			}
		});
	}
}


function novo()
{			
	$('#operacao').val("novo");
	$('#selProdutos').removeAttr('disabled'); 
	$('#cdItemCobilling').removeAttr('disabled'); 
	$('#form1').submit();		
}

function pesquisar()
{
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}

function limpar(){
	$('#selProdutos').attr('value', "");
	$('#cdItemCobilling').attr('value', "");
	$('#cdMotivoRetarifacao').attr('value', "");
	
}


</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/produto/item/listar.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="entity.cdComponenteProduto" id="cdComponenteProduto"/>
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<form:hidden path="produtoCobilling.cdProdutoCobilling" id="cdProdutoCobilling"/>

<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
<tr><td width="15%">Produto:</td>
<td> <form:select id="cdProdutoCobillingFiltro" path="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling"></form:select> 
<input id="pesquisar_button" type="button" value=<spring:message code="crud.botao.pesquisar"/> /></td>

</tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="requestScope.filtro.lstComposicaoProduto"   
				pagesize="20"  id="repasses" 
				requestURI="/scc/user/produto/item/listar.scc" 
				class="ui-state-default" 
				decorator="com.claro.sccweb.decorator.view.ComposicaoProdutoDecorator">
<display:column property="produto" title="Produto" />
<display:column property="itemCobilling" title="Item Cobilling" />
<display:column property="tipoAtivacao" title="Tipo Ativação" />
<display:column property="abrangenciaChamada" title="Abrangência" />
<display:column property="tipoTerminal" title="Tipo Terminal" />
<display:column property="tipoFranquia" title="Tipo Franquia" />
<display:column property="motivoRetarifacao" title="Motivo Retarifação" />
							<display:column title="Editar"><a href="editarSccComposicao.scc?cdComponente=${repasses.cdComponenteProduto}" 
																class="edit_composicao" >
																<img  src="/scc/images/editIcon.gif"></a>
							</display:column>
							
							<display:column title="Remover"><a href="removeEntity.scc?cdComponente=${repasses.cdComponenteProduto}" 
																class="delete_composicao" >
																<img  src="/scc/images/delete.gif"></a>
							</display:column>
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
<form:select id="selProdutos"  path="entity.sccProdutoCobilling.cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
</td></tr>


<tr><td width="15%">Item Produto:</td>
<td>
<form:select id="cdItemCobilling" path="entity.sccItemCobilling.cdItemCobilling" items="${itemsCobilling}" itemLabel="noItemCobilling" itemValue="cdItemCobilling"/>
</td></tr>


<tr><td width="15%">Motivo Retarifação:</td>
<td>
<form:input id="cdMotivoRetarifacao" path="entity.cdMotivoRetarifacao" size="5" maxlength="3"/>
</td></tr>


</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="atualizar_button" class="update" type="button" value=<spring:message code="crud.botao.atualizar"/> />    
    <input id="cancelar_button" type="button" value="Cancelar" />
    <input id="salvar_button" class="save" type="button" value=<spring:message code="crud.botao.salvar"/> />
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
	}
	else if (op == 'novo'){
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
		$('#selProdutos').removeAttr('disabled'); 
	}
	else if (op == 'cancelar'){
		$('#tabs').tabs('select',0);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
	}
	else{	
		$('#selProdutos').removeAttr('disabled');
	}
});
</script>
