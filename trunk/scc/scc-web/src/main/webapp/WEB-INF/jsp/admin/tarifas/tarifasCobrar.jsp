<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui.css" type="text/css"/>

 <script type="text/javascript" src="<c:url value="/js/jquery-1.8.2.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.min.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmplPlus.js"/>"></script>
 
 
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<link rel="stylesheet" href="/scc/css/styles.css" type="text/css">

<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script>

$(document).ready(function(){
	$('#pesquisar_button').click(pesquisar);
	
	/*
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
		
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
	*/
});

$('body').delegate('.edit_itemstarifa', 'click', function() {

	$.ajax({
		type: "GET",
		dataType:"json",				
		url : this.href,
		contentType: "application/json; charset=utf-8",
		success : function(data) {
			atribuirValor(data);

			var _html = "";
			
			$.each(data.listItems, function(key, preItem){	   		
				
		   		_html = _html + "<input type='text' value='" + preItem.id.sgUf + "' />" + "<input type='text' value='" + preItem.vlBruto + "' />" +  "<br />";
		  	});

			_html = _html + "<input type='hidden' value='123' />"

			$("#conteudo_itens_tarifas").html(_html);
			
			$('#tabs').tabs('select',1);
			
			//$('#tabs').tabs('select',1);
		}
	});

	return false;
});

function atribuirValor(data){

	$('#cdEot2').attr('value', data.cdEotLd);
	$('#cdTipoPlano').attr('value', data.cdTipoPlano);
	$('#cdStatus').attr('value', data.cdStatus);
	$('#nmPlano').attr('value', data.nmPlano);
	$('#cdSoc').attr('value', data.cdSoc);						
	$('#dtIniVigencia').attr('value', formatarDataPtBr(data.dtIniVigencia));
	$('#dtFimVigencia').attr('value', formatarDataPtBr(data.dtFimVigencia));

	$('#dtCriacao').attr('value', formatarDataPtBr(dtCriacao));
	$('#dtReferencia').attr('value', formatarDataPtBr(dtReferencia));
	$('#cdUsuarioManut').attr('value', cdUsuarioManut);
		
}


$(function() {
    $( "#tabs" ).tabs();
});


function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}


function novo() {	
	
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();

}

$(function() {
	$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
	$( ".datepicker" ).datepicker();
});	

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
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/admin/tarifas/cobrar/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<form:hidden path="entity.dtAtualizacao" id="dtAtualizacao" />
		<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
		<form:hidden path="entity.cdUsuarioManut" id="cdUsuarioManut"/>
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				
				<tr>
					<td width="15%"><spring:message code="cadastro.tarifas.cobrar.operadorald" /></td>
					<td><td> <form:select path="cdEotLd" items="${operadorasExternas}" itemValue="cdEot" itemLabel="dsOperadoraForCombos"/> 
					</td>
				</tr>
				
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="pesquisar_button" class="btn_pesquisar" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
						
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					
						<display:table style="width:90%" name="requestScope.filtro.listPreTarifaAcb" pagesize="20" 
									   id="repasses" 
									   requestURI="${pageContext.request.contextPath}/user/admin/tarifas/cobrar/listar.scc"
									   class="ui-state-default">
							<display:column property="cdEotLd" title="Operadora Externa"/>
							<display:column property="cdTipoPlano" title="Tipo Plano"/>
							<display:column property="cdSoc" title="SDC"/>
							<display:column property="dtIniVigencia" title="Inicio Vigência" format="{0, date, dd/MM/yyyy }"/>
							<display:column property="dtFimVigencia" title="Fim Vigência" format="{0, date, dd/MM/yyyy }"/>
							<display:column property="cdStatus" title="Ativo"/>
							<display:column title="Editar"><a href="editarItemsTarifa.scc?idTarifa=${repasses.sqPreTarifaAcb}" 
																class="edit_itemstarifa" >
																<img  src="/scc/images/editIcon.gif"></a>
							</display:column>
							
						</display:table>
					</td>
				</tr>
			</table>
		</div>
		<div id="tabs-2" style="height: 500px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >

				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.operadorald" /></td>
					<td> <form:select id="cdEot2"  path="entity.cdEotLd" items="${operadorasExternas}" itemValue="cdEot" itemLabel="dsOperadoraForCombos"/></td>
				</tr>
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.tipo.plano" /></td>
					<td> <form:radiobuttons path="entity.cdTipoPlano" id="cdTipoPlano" items="${tipoPlanos}" itemValue="key" itemLabel="label"/></td>
				</tr>

				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.status" /></td>
					<td> <form:radiobuttons path="entity.cdStatus" id="cdStatus" items="${status}" itemValue="key" itemLabel="label"/></td>
				</tr>
			
	
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.descricao" /></td>
					<td><form:input path="entity.nmPlano" id="nmPlano" /> </td>
				</tr>
	
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.sdc" /></td>
					<td><form:input path="entity.cdSoc" id="cdSoc" /> </td>
				</tr>
				
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.vigencia.inicio" /></td>
					<td><form:input path="entity.dtIniVigencia" id="dtIniVigencia" cssClass="datepicker"/>
				</tr>
				
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.vigencia.final" /></td>
					<td><form:input path="entity.dtFimVigencia" id="dtFimVigencia" cssClass="datepicker"/>
				</tr>
				
			
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.label.valor.tarifa" /></td>
					
				</tr>
	
				<tr>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.label.uf" /></td>
					<td width="5%"><spring:message code="cadastro.tarifas.cobrar.label.valor" /></td>
				</tr>
				<br>
			</table>
			
			<div id="conteudo_itens_tarifas" />
	
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>				
						
		</div>
		
	</form:form>
</div>
<script>
	$(document).ready(function(){
		$('#pesquisar_button').removeAttr('disabled');
		$('#excel_button').removeAttr('disabled');
	});
</script>
