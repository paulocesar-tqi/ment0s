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
<li><a href="#tabs-1"><spring:message code="repasse_pre_relatorios.sintetico.titulo"/></a></li>
</ul>
<div id="tabs-1">
<form:form  modelAttribute="filtro"  method="POST" action="/scc/user/repasse/pre/relatorios/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>


<table  width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td align="center"><h3>Cobilling Pré Pago - Relatório Sintético de Chamadas</h3></td>
</tr>
 <tr><td>                            
<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1"  decorator="com.claro.sccweb.decorator.RelSinteticoFechamentoPrePagoViewDecorator" pagesize="20"  id="sintetico" requestURI="/scc/user/repasse/pre/relatorios/tab1_sintetico.scc" class="ui-state-default">
<display:column property="operadoraClaro" title="Op. Claro" />
<display:column property="uf" title="UF" />
<display:column property="trafego" title="Tráfego" />
<display:column property="cnAssinante" title="CN Ass." />
<display:column property="cnOrigem" title="CN Orig." />
<display:column property="cdEotOrigem" title="EOT Orig." />
<display:column property="tipo" title="Tipo" />
<display:column property="periodo" title="Período" />
<display:column property="chamadas" title="Chamadas" />
<display:column property="minutos" title="Minutos" />
<display:column property="valorLiquido" title="Vlr. Líquido" />
<display:column property="pisCofins" title="Pis/Cofins" />
<display:column property="icmsRepassar" title="ICMS Repassar" />
<display:column property="valorRepassar" title="Vlr. Repassar" />
<display:column property="icmsNaoRepassado" title="ICMS Não Repassado" />
<display:column property="valorBruto" title="Vlr. Bruto" />
</display:table>
</td></tr>
<tr>
<td align="center"><h3>Serviço Prestado (Utilização da Plataforma)</h3></td>
</tr>
<tr>
<td>
</br>                       
<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_2"  decorator="com.claro.sccweb.decorator.RelSinteticoServicoPrePagoViewDecorator" pagesize="10"  id="sintetico" requestURI="/scc/user/repasse/pre/relatorios/tab1_sintetico.scc" class="ui-state-default">
<display:column property="chamadas" title="Chamadas" />
<display:column property="valorLiquido" title="Vlr. Líquido" />
<display:column property="pisCofins" title="Pis/Cofins" />
<display:column property="iss" title="ISS" />
<display:column property="valorBruto" title="Vlr. Bruto" />
</display:table>
</td>
</tr>
</table>
</div>
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
</div>


</form:form>
</div>
</div>
