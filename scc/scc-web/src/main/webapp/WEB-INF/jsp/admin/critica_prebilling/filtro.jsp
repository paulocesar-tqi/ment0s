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
	//$('#novo_button').click(novo);
	$('#novo_button').show();
	//$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	//$('#salvar_button').click(salvar);	
	$('#tabs').tabs();
});

$(function() {
    $( "#tabs" ).tabs();
});

$(function() {
	$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
	$( ".datepicker" ).datepicker();
});	


$('body').delegate('.btn_update', 'click', function() {
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/admin/critica/prebilling/salvarEntity.scc",
			data: dados,
			success : function(data) {
				
				$('#form1').submit();
				$('#cancelar_button').show();
			}
		});
	}
	 return false;
});



 $('body').delegate('.delete_critica', 'click', function() {
	 var r=confirm("Remover?");
	 if(r==true){
		 	//var dados = $("#form1").serialize();
		 	cache:false,
			$.ajax({
				cache:false,
				type: "DELETE",
				url : this.href,
				//data: dados,
				success : function(data) {
					$('#form1').submit();
				}
			});
		}
	 return false;
});


 $('body').delegate('.edit_critica', 'click', function() {
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : this.href,
			success : function(data) {
				atribuirValor(data);
				$('#tabs').tabs('select',1);
				$('#cancelar_button').show();
			}
		});

 	return false;
});


$('body').delegate('.btn_novo', 'click', function() {
//	$.ajax({
//		cache:false,
//		type: "POST",
//		url : "/scc/user/admin/critica/prebilling/novocritica.scc",
		//data: dados,
//		success : function(data) {
//		}
//	});

limparValor();
$('#tabs').tabs('select',1);
}); 

function cancelar()
{
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}
 

	function atribuirValor(data){

		$('#cdCritica').attr('value', data.cdCritica);
		$('#dsCritica').attr('value', data.dsCritica);
		$('#cdSaida').attr('value', data.cdSaida);
		$('#cdTipoSaida1').attr('value', data.cdTipoSaida1);
		$('#cdTipoSaida2').attr('value', data.cdTipoSaida2);						
		$('#cdTipoSaida3').attr('value', data.cdTipoSaida3);
		$('#cdErroReciclagem').attr('value', data.cdErroReciclagem);
		$('#dtCriacao').attr('value', formatarDataPtBr(data.dtCriacao));
		$('#cdUsuarioManut').attr('value', data.cdUsuarioManut);
		
	}

	function limparValor(){
		var dataHoje = new Date();
		$('#dtCriacao').attr('value', "");
		$('#cdCritica').attr('value', "");
		$('#dsCritica').attr('value', "");
		$('#cdSaida').attr('value', "");
		$('#cdTipoSaida1').attr('value', "");
		$('#cdTipoSaida2').attr('value', "");						
		$('#cdTipoSaida3').attr('value', "");
		$('#cdErroReciclagem').attr('value', "");
		
	}


	function formatarDataPtBr(data){
		var teste = data;
		var currentDate = null;
		var dateTimeSplit =data.split(' ');
		var dateSplit = dateTimeSplit[0].split('-');
		currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
		return currentDate;
		
	}


//function novo()
//{		
//	$('#operacao').val("CRUD.novo");	
//	$('#form1').submit();
//}


	
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


</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="listar.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="mensagem" id="mensagem"/>
<form:hidden path="entidade.dtCriacao" id="dtCriacao"/>
<form:hidden path="entidade.cdUsuarioManut" id="cdUsuarioManut"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="requestScope.filtro.lstCritica"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/critica/prebilling/listar.scc" class="ui-state-default">
	<display:column property="cdCritica" title="Cd Critica" />
	<display:column property="dsCritica" title="Descricao" />
	<display:column property="cdSaida" title="Cd Saida" />
	<display:column property="cdTipoSaida1" title="Cd Tipo Saida 1" />
	<display:column property="cdTipoSaida2" title="Cd Tipo Saida 2" />
	<display:column property="cdTipoSaida3" title="Cd Tipo Saida 3" />
	<display:column property="cdErroReciclagem" title="Cd Erro Reciclagem" />
	<display:column title="Editar"><a href="editarcritica.scc?id=${repasses.cdCritica}" 
					class="edit_critica" >
					<img  src="/scc/images/editIcon.gif"></a>
	</display:column>
							
	<display:column title="Remover"><a href="removercritica.scc?id=${repasses.cdCritica}" 
					class="delete_critica" >
					<img  src="/scc/images/delete.gif"></a>
	</display:column>
</display:table>
</td></tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="novo_button" class="btn_novo" type="button" value=<spring:message code="crud.botao.novo"/> /> 
    </td>
</tr>
</table>
</div>

<div id="tabs-2" style="height: 500px;">

<table width="100%" border="0" cellspacing="0" cellpadding="0" >



<tr>
<td width="15%">Cd Critica:</td>
<td><form:input id="cdCritica" path="entidade.cdCritica" maxlength="10"/></td>
</tr>

<tr>
<td width="15%">Descricao:</td>
<td><form:input id="dsCritica" path="entidade.dsCritica" maxlength="40"/></td>
</tr>

<tr>
<td width="15%">Cd Saida:</td>
<td><form:input id="cdSaida" path="entidade.cdSaida" maxlength="3"/></td>
</tr>

<tr>
<td width="15%">Cd Tipo Saida 1:</td>
<td><form:input id="cdTipoSaida1" path="entidade.cdTipoSaida1" maxlength="2"/></td>
</tr>

<tr>
<td width="15%">Cd Tipo Saida 2:</td>
<td><form:input id="cdTipoSaida2" path="entidade.cdTipoSaida2" maxlength="2"/></td>
</tr>

<tr>
<td width="15%">Cd Tipo Saida 3:</td>
<td><form:input id="cdTipoSaida3" path="entidade.cdTipoSaida3" maxlength="2"/></td>
</tr>


<tr>
<td width="15%">Cd Erro Reciclagem:</td>
<td><form:input id="cdErroReciclagem" path="entidade.cdErroReciclagem" maxlength="9"/></td>
</tr>


</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="atualizar_button" class="btn_update" type="button" value=<spring:message code="crud.botao.atualizar"/> />
    <input id="cancelar_button" type="button" value="Cancelar" />       
    <input id="salvar_button" class="btn_update" type="button" value=<spring:message code="crud.botao.salvar"/> />
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
