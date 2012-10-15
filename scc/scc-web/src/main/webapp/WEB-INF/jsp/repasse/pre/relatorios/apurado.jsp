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

$('#novaButton').click(function(){
	$('#operacao').val("NOVO");
	$('#form1').submit();
});


$('#excelButton').click(function(){
	$('#operacao').val("EXCEL");
	$('#form1').submit();
});

	
$('#tabs').tabs();
});
</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="repasse_pre_relatorios.apurado.titulo"/></a></li>
	</ul>
	<div id="tabs-1">
		<form:form  modelAttribute="filtro"  method="POST" action="/scc/user/repasse/pre/relatorios/execute.scc" id="form1">
			<form:hidden path="operacao" id="operacao"/>
			<form:hidden path="cdTipoRelatorio" id="cdTipoRelatorio"/>
			<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
				
				<tr>
					<td align="center"><h3>Cobilling Pré Pago - Relatório Resumo de Apuração</h3></td>
				</tr>
				
				
				 <tr>
				 	<td>                            
						<display:table  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="sintetico" requestURI="/scc/user/repasse/pre/relatorios/tab1_apurado.scc" class="ui-state-default">
							<display:column property="operadoraClaro" title="Op. Claro" />
							<display:column property="valorApuradoLiquido" title="Vl. Apurado Liq." />
							<display:column property="pisCofins" title="Pis/Cofins" />
							<display:column property="valorIcmsRepassar" title="ICMS a Repassar" />
							<display:column property="valorIcmsNaoRepassado" title="ICMS Não Repassado" />
							<display:column property="valorRepassar" title="Valor Repassar" />
							<display:column property="servicoPrestadoLiquido" title="Ser. Prest. Líquido" />
							<display:column property="pisCofinsServicePrestado" title="Pis/Cofins Ser. Prestado" />
							<display:column property="iss" title="ISS" />
							<display:column property="valorBrutoServico" title="Vlr. Bruto Serv." />
							<display:column property="creditosAutorizados" title="Créd. Autorizados" />
							<display:column property="creditos226" title="Créd. 226" />
							<display:column property="penalidadesMinutosPerdidos" title="Penalidades Min. Perd." />
							<display:column property="totalMultasJuros" title="Total Jutos e Multas" />
							<display:column property="totalAcertosConciliacoes" title="Total Acertos e Conc." />
							<display:column property="cpmfDescontar" title="CPMF Descontar" />
							<display:column property="icmsDescontar" title="ICMS Descontar" />
							<display:column property="icmsRepassar" title="ICMS Repassar" />
							<display:column property="valorFinalRepassar" title="Vlr. Final Repassar" />
						</display:table>
					</td>
				</tr>
			</table>
			
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td colspan="3" >&nbsp;</td>    
				    <td colspan="1" align="right"  nowrap="nowrap">
				    	<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
						    <input type="button" id="excelButton" name="excelButton"  value=<spring:message code="comum.botao.excel"/> />
					    </c:if>
					    <input type="button" id="novaButton" name="novaButton"  value=<spring:message code="comum.botao.nova"/> />
				    </td>
				</tr>
			</table>
		
		</form:form>
	</div>
</div>
