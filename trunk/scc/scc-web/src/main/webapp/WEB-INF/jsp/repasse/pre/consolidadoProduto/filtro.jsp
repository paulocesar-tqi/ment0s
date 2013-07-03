<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<style type="text/css">
th.fundoVerde {background:#D7E4BC;}
th.fundoAzul {background:#B8CCE4;}
th.fundoVermelho {background:#FF5544;}
</style>

<script>
	$(document).ready(function(){
		$('#tabs').tabs();
		$("#anoRelatorio").mask("9999");	
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$('#cdEOTClaro').change(selecionaOperadora);
		$('#cdEOTLD').change(selecionaOperadora);	
		
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

		
		$('#dialog').dialog({
			autoOpen: false,
			width: 200,
			buttons: {
				"Ok": function() { 
					$(this).dialog("close"); 
				}
			}
		});
		
	});
	
	function selecionaOperadora() {
		$('#pesquisar_button').attr('disabled', 'disabled');
		$("#cdProdutoPrepago").empty().append('<option selected="selected" value="-1">Carregando...</option>');
		var eotClaro = $("#cdEOTClaro option:selected");
		var eotLD = $("#cdEOTLD option:selected");
				
		$('#cdProdutoPrepago').removeAttr('disabled');
		$.ajax({   
			url: "/scc/user/repasse/consolidado/produto/pre/json/lista_produtos/"+eotLD.val()+ "/" + eotClaro.val() + ".scc",	 
			dataType: "json", success: function(data) {
				$('#pesquisar_button').removeAttr('disabled');
				var name, select, option;        
			    select = document.getElementById('cdProdutoPrepago');      
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
		<li><a href="#tabs-1"><spring:message code="relatorio.consolidado.produto.pre.titulo"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/repasse/consolidado/produto/pre/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				<tr>
					<td width="10%"><spring:message code="relatorio.label.operadora.claro"/>:</td>
    				<td ><form:select path="cdEOTClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />    
				</tr>
				<tr>
    				<td width="10%"><spring:message code="relatorio.label.operadora.ld"/>:</td>
    				<td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />    
				</tr>
				<tr>
					<td width="10%"><spring:message code="relatorio.label.produto.cobilling"/>:</td>
				    <td ><form:select path="cdProdutoPrepago" id="cdProdutoPrepago" items="${produtos}" itemLabel="noProdutoPrepago" itemValue="cdProdutoPrepago" />
				    <form:errors path="cdProdutoPrepago" /></td>
				</tr>
				<tr>    
				    <td width="10%"><spring:message code="relatorio.label.dtInicial"/>:</td>
					<td><form:input path="dataInicial"/><form:errors path="dataInicial" /></td>  
				</tr>

				<tr>
					<td width="10%"><spring:message code="relatorio.label.dtFinal"/>:</td>
					<td><form:input path="dataFinal"/><form:errors path="dataFinal" /></td>
				</tr>
			</table>
			<br />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">				
				<tr>
					<td>
						<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/servico/pre/tab1.scc" class="ui-state-default">
							<display:column property="cspLD" title="CSP LD" />
							<display:column property="operadoraClaro" title="Operadora Claro"/>
							<display:column property="cdProdutoPrepago" title="Cd Prod. Pré-Pago"/>
							<display:column property="cdTipoEvento" title="Cd Tipo Evento"/>
							<display:column property="dataChamada" title="Data Chamada"/>
							<display:column property="inChamadaRepassada" title="Chamada Repassada"/>
							<display:column property="dataRepasse" title="Data Repasse"/>
							<display:column property="totalDuracaoFaturada" title="Dur. Tot. Faturada"/>
							<display:column property="totalValor" title="Valor Total"/>
							<display:column property="duracaoTarifada" title="Duração Tarifada"/>
							<display:column property="valorBruto" title="Valor Bruto"/>
							
							<display:column property="pacote01" title="Pacote 1"/>
							<display:column property="minutosPacote01" title="Min. Pacote 1" />
							<display:column property="valorPacote01" title="Valor Pacote 1"/>
							
							<display:column property="pacote02" title="Pacote 2"/>
							<display:column property="minutosPacote02" title="Min. Pacote 2" />
							<display:column property="valorPacote02" title="Valor Pacote 2"/>
							
							<display:column property="pacote03" title="Pacote 3"/>
							<display:column property="minutosPacote03" title="Min. Pacote 3" />
							<display:column property="valorPacote03" title="Valor Pacote 3"/>
							
							<display:column property="pacote04" title="Pacote 4"/>
							<display:column property="minutosPacote04" title="Min. Pacote 4" />
							<display:column property="valorPacote04" title="Valor Pacote 4"/>
							
							<display:column property="pacote05" title="Pacote 5"/>
							<display:column property="minutosPacote05" title="Min. Pacote 5" />
							<display:column property="valorPacote05" title="Valor Pacote 5"/>
							
							<display:column property="pacote06" title="Pacote 6"/>
							<display:column property="minutosPacote06" title="Min. Pacote 6" />
							<display:column property="valorPacote06" title="Valor Pacote 6"/>
							
							<display:column property="pacote07" title="Pacote 7"/>
							<display:column property="minutosPacote07" title="Min. Pacote 7" />
							<display:column property="valorPacote07" title="Valor Pacote 7"/>
							
							<display:column property="pacote08" title="Pacote 8"/>
							<display:column property="minutosPacote08" title="Min. Pacote 8" />
							<display:column property="valorPacote08" title="Valor Pacote 8"/>
							
							<display:column property="pacote09" title="Pacote 9"/>
							<display:column property="minutosPacote09" title="Min. Pacote 9" />
							<display:column property="valorPacote09" title="Valor Pacote 9"/>
							
							<display:column property="pacote10" title="Pacote 10"/>
							<display:column property="minutosPacote10" title="Min. Pacote 10" />
							<display:column property="valorPacote10" title="Valor Pacote 10"/>
						</display:table>
					</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
    				<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    				<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    					<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.executar"/> />
    					<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">  
    					<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />  
    					</c:if>
    				</td>
				</tr>
			</table>
			
	</div>
</form:form>
</div>
<script>
$(function() {
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});
</script>
