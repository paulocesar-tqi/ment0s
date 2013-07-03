<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>

<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<link rel="stylesheet" href="/scc/css/styles.css" type="text/css">

<%@ taglib uri="/tags/displaytag" prefix="display" %>
<script>
$(document).ready(function(){		
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);	
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#salvar_button').click(salvar);
	$( "#dtFimVigencia" ).datepicker();
	$( "#dtInicioVigencia" ).datepicker();	
	
	$("#peInicioFaixaPenalidade").mask("999.9999");
	$("#peFimFaixaPenalidade").mask("999.9999");
	
	$("#vlFatorCalculoPenalidade").mask("9999999.99");
	
	$("#dtFimVigencia").mask("99/99/9999");	
	$("#dtInicioVigencia").mask("99/99/9999");
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
			url : "/scc/user/admin/admin/penalidade/salvarEntity.scc",
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
			url : "/scc/user/admin/penalidade/atualizarEntity.scc",
			data: dados,
			success : function(data) {
				$('#atualizar_button').hide();

				$('#form1').submit();
			
			}
		});
	}
}
$('body').delegate('.delete_entity', 'click', function() {
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

$('body').delegate('.edit_entity', 'click', function() {
	 
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
	
	var cdFaixaPenalidade = data.cdFaixaPenalidade;
	var dsFaixaPenalidade = data.dsFaixaPenalidade;
	var inTipoPenalidade = data.inTipoPenalidade;
	var peInicioFaixaPenalidade = data.peInicioFaixaPenalidade;
	var peFimFaixaPenalidade = data.peFimFaixaPenalidade;
	var vlFatorCalculoPenalidade = data.vlFatorCalculoPenalidade;
	var dtInicioVigencia = data.dtInicioVigencia;
	var dtFimVigencia = data.dtFimVigencia;
	
	
	$('#cdFaixaPenalidade').attr('value', cdFaixaPenalidade);
	$('#dsFaixaPenalidade').attr('value', dsFaixaPenalidade);
	$('#inTipoPenalidade').attr('value', inTipoPenalidade);
	$('#peInicioFaixaPenalidade').attr('value', peInicioFaixaPenalidade);
	$('#peFimFaixaPenalidade').attr('value', peFimFaixaPenalidade);
	$('#vlFatorCalculoPenalidade').attr('value', vlFatorCalculoPenalidade);
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(dtInicioVigencia));
	$('#dtFimVigencia').attr('value', formatarDataPtBr(dtFimVigencia));								   
	
}

function limpar(){
	$('#cdFaixaPenalidade').attr('value', "");
	$('#dsFaixaPenalidade').attr('value', "");
	$('#inTipoPenalidade').attr('value', "");
	$('#peInicioFaixaPenalidade').attr('value', "");
	$('#peFimFaixaPenalidade').attr('value', "");
	$('#vlFatorCalculoPenalidade').attr('value', "");
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(""));
	$('#dtFimVigencia').attr('value', formatarDataPtBr(""));								   
	
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
	<form:form modelAttribute="filtro" method="post" action="listarPenalidades.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0 >
				<tr>
					<td width="15%">Penalidade:</td><td> <form:select path="cdTipo" items="${tipos}" itemValue="key" itemLabel="label"/> 
						<input id="pesquisar_button" type="button" value="Pesquisar" /> 
					</td>
				</tr>
			</table>
			<br/>
			<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 				<tr>
 					<td>                            
						<display:table style="width:90%"  name="requestScope.filtro.listFaixaPenalidade"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/penalidade/listarPenalidades.scc" class="ui-state-default">
							<display:column property="cdFaixaPenalidade" title="Código" />
							<display:column property="peInicioFaixaPenalidade" title="Início da Faixa" />
							<display:column property="peFimFaixaPenalidade" title="Fim da Faixa" />
							<display:column property="vlFatorCalculoPenalidade" title="Valor" />
							<display:column property="dtInicioVigencia" title="Início Vigência" />
							<display:column property="dtFimVigencia" title="Fim Vigência" />
							<display:column property="dsFaixaPenalidade" title="Descrição" />
							<display:column title="Editar"><a href="editarEntity.scc?cdFaixaPenalidade=${repasses.cdFaixaPenalidade}" 
							    							  class="edit_entity" >
																	<img  src="/scc/images/editIcon.gif"></a>
							</display:column>
								
							<display:column title="Remover"><a href="removerEntity.scc?cdFaixaPenalidade=${repasses.cdFaixaPenalidade}" 
															   class="delete_entity" >
																	<img  src="/scc/images/delete.gif"></a>
							</display:column>
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
			
				<form:hidden path="entity.cdFaixaPenalidade" id="cdFaixaPenalidade"/>
				
				<tr>
					<td width="15%">Descrição:</td>
					<td ><form:select id="dsFaixaPenalidade"   path="entity.dsFaixaPenalidade" items="${descricao}" itemLabel="label" itemValue="key"/>
					</td>
				</tr>
				
				<tr>
					<td width="15%">Tipo:</td>
					<td ><form:select id="inTipoPenalidade"  path="entity.inTipoPenalidade" items="${tipos}" itemLabel="label" itemValue="key"/>
					</td>
				</tr>
				
				
				<tr>
					<td width="15%">Início da Faixa:</td>
					<td ><form:input id="peInicioFaixaPenalidade"  path="entity.peInicioFaixaPenalidade" maxlength="8" size="10"/><form:errors path="entity.peInicioFaixaPenalidade"/>
					</td>
				</tr>
				
				<tr>
					<td width="15%">Fim da Faixa:</td>
					<td ><form:input id="peFimFaixaPenalidade"  path="entity.peFimFaixaPenalidade" maxlength="8" size="10"/><form:errors path="entity.peFimFaixaPenalidade"/>
					</td>
				</tr>
				
				
				<tr>
					<td width="15%">Valor:</td>
					<td ><form:input id="vlFatorCalculoPenalidade"  path="entity.vlFatorCalculoPenalidade" maxlength="8" size="10"/>
					</td>
				</tr>
				
				<tr>
					<td width="15%">Data Inicial:</td>
					<td ><form:input id="dtInicioVigencia"  path="entity.dtInicioVigencia" cssClass="datepicker"/>
					</td>
				</tr>
				
				<tr>
					<td width="15%">Data Final:</td>
					<td ><form:input id="dtFimVigencia"  path="entity.dtFimVigencia" cssClass="datepicker"/> 
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
	if (op == 'CRUD.editar'){
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#cancelar_button').show();
		$('#atualizar_button').show();
	}else if(op == 'CRUD.atualizar'){
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
