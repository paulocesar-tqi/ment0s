<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html><%@ page session="true" isELIgnored="false"%>
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
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$('#tabs').tabs();
	});

	$(document).ready(function() {
		$('#pesquisar_button').click(pesquisar);
		$('#excel_button').click(excel);
		$('#excel_button').attr('disabled', 'disabled');
		$('#tabs').tabs();
	});

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

	function trocaTipoOperadora() {
		var sel = $("#tipoOperadora option:selected");
		if (sel.val() == "O")
			mostraOperadoraClaro();
		else
			mostraHoldingClaro();
	}

	function mostraHoldingClaro() {
		$.ajax({
			url : "/scc/user/recepcao_transmissao/holding/json.scc",
			dataType : "json",
			success : function(data) {
				var name, select, option;
				select = document.getElementById('cdEOTClaro');
				select.options.length = 0;
				for (name in data) {
					if (data.hasOwnProperty(name)) {
						select.options.add(new Option(data[name], name));
					}
				}
			}
		});
	}

	function mostraOperadoraClaro() {
		$.ajax({
			url : "/scc/user/recepcao_transmissao/operadoras/json.scc",
			dataType : "json",
			success : function(data) {
				var name, select, option;
				select = document.getElementById('cdEOTClaro');
				select.options.length = 0;
				for (name in data) {
					if (data.hasOwnProperty(name)) {
						select.options.add(new Option(data[name], name));
					}
				}
			}
		});
	}
</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/naoconf/faturamento/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
					<td width="15%"><spring:message code="relatorio.naoconforme.ajustes.label.operadorald" /></td>
					<td id="cdEOTLD"><form:select path="cdCsp" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdCsp" /></td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.naoconforme.faturamento.label.cdciclo"/>	</td>
					<td>
						<form:input path="cdCiclo" id="cdCiclo" maxlength="3" />
						
					</td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.naoconforme.faturamento.label.mmciclo" /></td>
					<td> 
						<form:select id="mmCiclo" path="mmCiclo" itemValue="key" itemLabel="label" items="${meses}"/>
					</td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.naoconforme.faturamento.label.aaCiclo" /></td>
					<td>
						<form:input path="aaCiclo" size="5" maxlength="4"/>
						<form:errors path="aaCiclo"/> 
					</td>
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/naoconf/faturamento/tab1.scc" class="ui-state-default">
							<display:column property="operadoraClaro" title="Operadora Claro"/>
							<display:column property="operadoraLd" title="Operadora Ld"/>
							<display:column property="uf" title="Uf"/>
							<display:column property="ciclo" title="Ciclo"/>
							<display:column property="numeroNota" title="Numero Nf"/>
							<display:column property="serieNotaFiscal" title="Serie Nf"/>
							<display:column property="subSerieNotaFiscal" title="Sub Serie Nf"/>
						</display:table>
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
