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
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#pesquisar_button').click(pesquisar);	
	$('#salvar_button').click(salvar);
	$('#tabs').tabs();
});

$(function() {
    $( "#tabs" ).tabs();
});


function salvar() {
	
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();		
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/admin/grupo/salvarGrupo.scc",
			data: dados,
			success : function(data) {
				$('#salvar_button').hide();					
				$('#form1').submit();
				$('#tabs').tabs('select',0);	
			}
		});
	}
}

function atualizar() {
	
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/admin/grupo/atualizarGrupo.scc",
			data: dados,
			success : function(data) {
				$('#atualizar_button').hide();
				limpar();
				$('#form1').submit();
			
			}
		});
	}
}
$('body').delegate('.delete_grupo', 'click', function() {
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

$('body').delegate('.edit_grupo', 'click', function() {
	 
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : this.href,
			success : function(data) {
				limparCampos("#tabs-2");
				atribuirValor(data);
				$('#atualizar_button').show();
				$('#salvar_button').hide();	
				$('#tabs').tabs('select',1);
			}
		});

	return false;
});

function atribuirValor(data){
	
	var sqGrupo = data.sqGrupo;
	var noGrupo = data.noGrupo;
	var dtInicioVigencia = data.dtInicioVigencia;
	var dtCriacao = data.dtCriacao;
	
	$('#sqGrupo').attr('value', sqGrupo);
	$('#descricao').attr('value', noGrupo);
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(dtInicioVigencia));
	$('#dtCriacao').attr('value', formatarDataPtBr(dtCriacao));								   
}

function limpar(){
	$('#sqGrupo').attr('value', "");
	$('#descricao').attr('value', "");
	$('#dtReferencia').attr('value', "");	
}


function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}

function limparCampos(container) {
    $(container).find(":input, select").each(function () {
        switch (this.type) {
            case "file":
            case "password":
            case "text":
            case "textarea":
                $(this).val("");
                break;
            case "checkbox":
            case "radio":
                this.checked = false;
        }

        $(this).children("option:selected").removeAttr("selected").end()
               .children("option:first").attr("selected", "selected");
    });
}

function novo() {	
	
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();

}

function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function cancelar() {
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
	<form:form modelAttribute="filtro" method="post" action="grupo.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<form:hidden path="mensagem" id="mensagem"/>
		<form:hidden path="entity.sqGrupo" id="sqGrupo"/>
		<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			</table>
		
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />
					    <input id="pesquisar_button" type="button" value="Pesquisar" />
					    <c:if test="${!empty requestScope.filtro.listGrupo}">
						    <!-- <input id="novo_button" type="button" value="Excel" />-->
					    </c:if>
				    </td>
				</tr>
			</table>
			<br/>
			<c:if test="${!empty requestScope.filtro.listGrupo}">
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					 <tr><td>                            
						<display:table style="width:90%"  name="requestScope.filtro.listGrupo"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/grupo/grupo.scc" class="ui-state-default">
						<display:column property="noGrupo" title="Grupo" />
						<display:column title="Editar"><a href="editarGrupo.scc?sqGrupo=${repasses.sqGrupo}" 
						    							  class="edit_grupo" >
																<img  src="/scc/images/editIcon.gif"></a>
						</display:column>
							
						<display:column title="Remover"><a href="removerGrupo.scc?sqGrupo=${repasses.sqGrupo}" 
														   class="delete_grupo" >
																<img  src="/scc/images/delete.gif"></a>
						</display:column>
						</display:table>
					</td></tr>
				</table>
			</c:if>
		
		</div>
		
		<div id="tabs-2" style="height: 500px;">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			
				<tr>
					<td width="15%"><spring:message code="admin.label.tab2.grupo"/></td>
					<td>
						<form:input path="entity.noGrupo" id="descricao" size="61" maxlength="60"/>
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
	$('#tabs').tabs('select',${filtro.activeTab});
	var op = $('#operacao').val();
	if (op == 'CRUD.editar') {
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
		//$('#descricao').attr('disabled', 'disabled');		
		$('#descricao').show();
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
