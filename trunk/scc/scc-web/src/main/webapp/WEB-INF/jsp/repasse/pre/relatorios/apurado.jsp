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
          		
						<display:table  
							name="sessionScope._DISPLAY_TAG_SPACE_1"  
							pagesize="20" 
							id="sintetico" 
							requestURI="/scc/user/repasse/pre/relatorios/tab1_apurado.scc" 									
							class="ui-state-default"
							varTotals="totals"
							
							decorator="com.claro.sccweb.decorator.view.RelApuracaoFechamentoPrePagoView3Decorator">
							<display:column property="dsOperadora" title="UF - OP Claro" style="width:70%" />							
							<display:column property="valorApuradoLiquido" title="Vlr Apurado Liquido"  style="width:20%;text-align:right" />
							<display:column property="pisCofins" title="Pis Cofins"  style="width:20%;text-align:right"/>
							<display:column property="valorIcmsRepassar" title="ICMS A Repassar"  style="width:20%;text-align:right"/>
							<display:column property="valorIcmsNaoRepassado" title="ICMS Não Repassado"   />
							<display:column property="valorRepassar" title="Vlr A Repassar"  style="width:20%;text-align:right" />
							<display:column property="servicoPrestadoLiquido" title="Serv Prest Liquido"  style="width:20%;text-align:right"/>
							<display:column property="pisCofinsServicePrestado" title="Pis Cofins Serv Prest"  style="width:20%;text-align:right"/>
							<display:column property="iss" title="Iss"  style="width:20%;text-align:right"/>
							<display:column property="valorBrutoServico" title="Vlr Bruto Serv Prest"  style="width:20%;text-align:right" />
							<display:column property="creditosAutorizados" title="Créditos Autorizados"   style="width:20%;text-align:right"/>
							<display:column property="creditos226" title="Créditos 226"  style="width:20%;text-align:right"/>
							<display:column property="penalidadesMinutosPerdidos" title="Penalidades Minutos Perdidos"  style="width:20%;text-align:right"/>
							<display:column property="totalMultasJuros" title="Total Multas e Juros"  style="width:20%;text-align:right"/>
							<display:column property="totalAcertosConciliacoes" title="Total Acertos Conciliações"  style="width:20%;text-align:right" />
							<display:column property="cpmfDescontar" title="CPMF A Descontar"   style="width:20%;text-align:right"/>
							<display:column property="icmsDescontar" title="ICMS A Descontar"  style="width:20%;text-align:right" />
							<display:column property="icmsRepassar" title="ICMS A Repassar"   style="width:20%;text-align:right"/>
							<display:column property="valorFinalRepassar" title="Vlr Final A Repassar"  style="width:20%;text-align:right" />
							<display:column property="valorNotaFiscal" title="Valor Nota Fiscal"   style="width:20%;text-align:right" />
							<display:column property="destaqueIcms" title="Destaque ICMS"  style="width:20%; text-align:right"/>
														
							<display:footer >
							</display:footer>
						</display:table>
					</td>
				</tr>
				<tr>
					<td>
					
						<display:table  
							name="sessionScope._DISPLAY_TAG_SPACE_2"  
							pagesize="1"  
							id="sintetico" 
							requestURI="/scc/user/repasse/pre/relatorios/tab1_apurado.scc" 									
							class="ui-state-default">
							<display:setProperty name="basic.show.header" value="false"/>							
							<display:column property="dsOperadora" title="UF - OP Claro" style="width:20%" />							
							<display:column property="valorApuradoLiquido" title="Vlr Apurado Liquido"  style="width:10%;text-align:right" />
							<display:column property="pisCofins" title="Pis Cofins"  style="width:10%;text-align:right"/>
							<display:column property="valorIcmsRepassar" title="ICMS A Repassar"  style="width:10%;text-align:right"/>
							<display:column property="valorIcmsNaoRepassado" title="ICMS Não Repassado" style="width:10%;text-align:right"/>
							<display:column property="valorRepassar" title="Vlr A Repassar"  style="width:10%;text-align:right"/>
							<display:column property="servicoPrestadoLiquido" title="Serv Prest Liquido"  style="width:10%;text-align:right"/>
							<display:column property="pisCofinsServicePrestado" title="Pis Cofins Serv Prest"  style="width:10%;text-align:right"/>
							<display:column property="iss" title="Iss"  style="width:10%;text-align:right"/>
							<display:column property="valorBrutoServico" title="Vlr Bruto Serv Prest"  style="width:10%;text-align:right"/>
							<display:column property="creditosAutorizados" title="Créditos Autorizados"   style="width:10%;text-align:right"/>
							<display:column property="creditos226" title="Créditos 226"  style="width:10%;text-align:right"/>
							<display:column property="penalidadesMinutosPerdidos" title="Penalidades Minutos Perdidos"  style="width:10%;text-align:right"/>
							<display:column property="totalMultasJuros" title="Total Multas e Juros"  style="width:10%;text-align:right"/>
							<display:column property="totalAcertosConciliacoes" title="Total Acertos Conciliações"  style="width:10%;text-align:right"/>
							<display:column property="cpmfDescontar" title="CPMF A Descontar"   style="width:10%;text-align:right"/>
							<display:column property="icmsDescontar" title="ICMS A Descontar"  style="width:10%;text-align:right" />
							<display:column property="icmsRepassar" title="ICMS A Repassar"   style="width:10%;text-align:right"/>
							<display:column property="valorFinalRepassar" title="Vlr Final A Repassar"  style="width:10%;text-align:right" />
							<display:column property="valorNotaFiscal" title="Valor Nota Fiscal"   style="width:10%;text-align:right" />
							<display:column property="destaqueIcms" title="Destaque ICMS"   style="width:10%; text-align:right" format="{0, number, #,##0.00}"/>
							
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