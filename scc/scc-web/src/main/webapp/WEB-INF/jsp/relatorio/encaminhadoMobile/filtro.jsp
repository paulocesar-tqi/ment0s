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
		$("#dtInicial").mask("99/99/9999");	
		$("#dtFinal").mask("99/99/9999");
		$( "#dtInicial" ).datepicker();
		$( "#dtFinal" ).datepicker();		
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
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

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/encaminhadoMobile/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />
		
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
				<tr>
    				<td width="10%"><spring:message code="relatorio.encaminhado.mobile.label.datainicial"/></td>
    				<td><form:input id="dtInicial" path="dtInicial" />
    				<form:errors path="dtInicial" /></td>
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.encaminhado.mobile.label.datafinal"/></td>
    				<td>
    					<form:input id="dtFinal" path="dtFinal" />
    					<form:errors path="dtFinal" />
    				</td>
				</tr>								
				<tr>
					<td width="20%"><spring:message code="relatorio.encaminhado.mobile.label.nomedoarquivo"/>:</td>
				    <td ><form:input path="noArquivoGerado" id="noArquivoGerado" size="50" maxlength="50"/>
				    <form:errors path="noArquivoGerado" /></td>
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
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="encaminhadoMobile" requestURI="/scc/user/relatorio/encaminhadoMobile/tab1.scc" class="ui-state-default">
							<display:column property="noArquivoReferencia" title="Nome do Arquivo"/>
							<display:column property="qtChamadas" title="Quantidade de Chamadas"/>
							<display:column property="qtMinutoTarifados" title="Quantidade de Minutos Tarifados"/>
							<display:column property="vlLiquido" title="Valor L�quido Total das Chamadas"/>
							<display:column property="dtRelatorio" title="Data de Emiss�o do Relat�rio"/>
							<display:column property="dtProcClaro" title="Data de Refer�ncia"/>							
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
