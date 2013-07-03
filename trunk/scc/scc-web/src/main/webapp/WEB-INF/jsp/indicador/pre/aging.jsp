<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css"
	type="text/css" />
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script>
	$(document).ready(function(){	
		$('#pesquisar_button').click(pesquisar);
		//$(".button_salvar").click(salvar);
		$('#excel_button').click(excel);
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
				url : "/scc/user/indicador/aging/pre/salvarEntity.scc",
				
				data: dados,
				success : function(data) {
					
					//$("#form1").html(data);
				}
			});
		}
	}
	
	function remover() {
		var r=confirm("Remover?");
		if(r==true){
			
			var dados = $("#form1").serialize();
			
			$.ajax({
				cache:false,
				type: "POST",
				url : "/scc/user/indicador/aging/pre/removerAging.scc",
				
				data: dados,
				success : function(data) {
					alert("funcionou");
					//$("#form1").html(data);
				}
			});
		}
	}


	
	function pesquisar() {
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#excel_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	}

	function excel() {
		$('#operacao').val("excel");
		$('#form1').submit();
	}

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/indicador/aging/pre/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<form:hidden path="entity.cdUsuarioManut" id="cdUsuarioManut"/>
		<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
		<form:hidden path="idIndicador" id="idIndicadorform"/>
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.indicador" /></td>
					<td><form:select path="filtro.idIndicador" id="idIndicador" items="${indicadores}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
						<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					</td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					
						<display:table style="width:90%" name="requestScope.filtro.listAgingIndicador" pagesize="20" 
									   id="repasses" requestURI="/scc/user/indicador/aging/pre/listar.scc" 
									   class="ui-state-default" 
									   decorator="com.claro.sccweb.decorator.view.SccAgingDecorator">
							<display:column property="cdTipoContrato"  href="editarAging.scc?indicador=${repasses.id.cdIndicador}" title="Tipo"/>
							<display:column property="id.cdIndicador" href="editarAging.scc?indicador=${repasses.id.cdIndicador}" title="Indicador"/>
							<display:column property="aging" href="editarAging.scc?indicador=${repasses.id.cdIndicador}" title="Aging"/>
							<display:column property="vlMinimoAging" title="Valor Minimo"/>
							<display:column property="vlMaximoAging" title="Valor Maximo"/>
							<display:column> <input type="button" value="Remover" class="buttom_remover" onclick="remover()" style="width:90px;"/>
							</display:column>
						</display:table>
						 
					</td>
				</tr>
			</table>
		</div>
		<div id="tabs-2" style="height: 500px;">
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.indicador" /></td>
					<td><form:select path="entity.id.cdIndicador" id="idIndicadoredit" items="${indicadores}" itemLabel="label" itemValue="key" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.aging" /></td>
					<td><form:input path="entity.id.sqAgingIndicador" id="aging" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.vlr.minimo" /></td>
					<td><form:input path="entity.vlMinimoAging" id="minimo" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.indicador.aging.label.vlr.maximo" /></td>
					<td><form:input path="entity.vlMaximoAging" id="maximo"  /></td>
				</tr>
				
				<tr>
					<td class='label' colspan='3'>
						&nbsp;
					</td>
				</tr>					
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
