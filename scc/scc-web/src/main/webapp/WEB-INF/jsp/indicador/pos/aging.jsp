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
				type: "POST",
				url : "/scc/user/indicador/aging/pos/salvarEntity.scc",
				
				data: dados,
				success : function(data) {
					
					$('#form1').submit();
				}
			});
		}
	}

	 $('body').delegate('.edit_aging', 'click', function() {
		 //var dados = $("#form1").serialize();
		  
			$.ajax({
				type: "GET",
				dataType:"json",
				contentType: "application/json; charset=utf-8",
				//data:dados,
				url : this.href,
				success : function(data) {
					atribuirValor(data);
					$('#tabs').tabs('select',1);
				}
			});

	 	return false;
	});

	 function atribuirValor(data){
		 var idIndicador = data.id.cdIndicador;
		 var sqAgingIndicador = data.id.sqAgingIndicador;
		 var vlMinimoAging = data.vlMinimoAging;
		 var vlMaximoAging =data.vlMaximoAging;
		 var cdUsuarioManut = data.cdUsuarioManut;
		 var dtcriacao = data.dtCriacao;
		 var dtAtualizacao = data.dtAtualizacao;
		 $('#idIndicadoredit').attr('value', idIndicador);
		 $('#aging').attr('value', sqAgingIndicador);
		 $('#minimo').attr('value', vlMinimoAging);
		 $('#maximo').attr('value', vlMaximoAging);	
		 $('#cdUsuarioManut').attr('value', cdUsuarioManut);
		 $('#dtAtualizacao').attr('value', formatarDataPtBr(dtAtualizacao));
		 $('#dtCriacao').attr('value', formatarDataPtBr(dtcriacao));
		 		  
	 }

	 $('body').delegate('.delete_aging', 'click', function() {
		 var r=confirm("Remover?");
		 if(r==true){
			 	var dados = $("#form1").serialize();
				$.ajax({
					type: "DELETE",
					url : this.href,
					data: dados,
					success : function(data) {
						$('#form1').submit();
					}
				});
			}
		 return false;
	});
	 
		 		
		function formatarDataPtBr(data){
			var currentDate = null;
			var dateTimeSplit =data.split(' ');
			var dateSplit = dateTimeSplit[0].split('-');
			currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
			return currentDate;
			
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
	<form:form modelAttribute="filtro" method="post" action="/scc/user/indicador/aging/pos/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		<form:hidden path="entity.cdUsuarioManut" id="cdUsuarioManut"/>
		<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
		<form:hidden path="entity.dtAtualizacao" id="dtAtualizacao"/>
		
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
									   id="repasses" requestURI="/scc/user/indicador/aging/pos/listar.scc" 
									   class="ui-state-default" 
									   decorator="com.claro.sccweb.decorator.view.SccAgingDecorator">
							<display:column property="cdTipoContratoPos"  title="Tipo"/>
							<display:column property="id.cdIndicador" title="Indicador"/>
							<display:column property="aging" title="Aging"/>
							<display:column property="vlMinimoAging" title="Valor Minimo"/>
							<display:column property="vlMaximoAging" title="Valor Maximo"/>
							<display:column title="Editar"><a href="editarAging.scc?idIndicador=${repasses.id.cdIndicador}&sqAgingIndicador=${repasses.id.sqAgingIndicador}" 
																class="edit_aging" >
																<img  src="/scc/images/editIcon.gif"></a>
							</display:column>
							
							<display:column title="Remover"><a href="removerAging.scc?idIndicador=${repasses.id.cdIndicador}&sqAgingIndicador=${repasses.id.sqAgingIndicador}" 
																class="delete_aging" >
																<img  src="/scc/images/delete.gif"></a>
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
