<%@ page session="true" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<%@ taglib uri="/tags/displaytag" prefix="display" %>
<script language="javascript" src="/scc/js/jquery.js"></script>
<script language="javascript" src="/scc/js/jquery.maskedinput-1.3.js"></script>
<script type="text/javascript" src="/scc/js/jquery.dataTables.js"></script>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<script>
$(document).ready(function() {

$('#tabs').tabs();
	
$('#novaButton').click(function(){
	$('#operacao').val("nova");
	$('#form1').submit();
});

$('#paginarButton').click(function(){
	$('#operacao').val("paginar");
	$('#form1').submit();
});
});

function selecionaDetalhe(seqArquivo,seqCredito)
{
$('#operacao').val("detalhes");
$('#seqArquivo').val("seqArquivo");
$('#seqCredito').val("seqCredito");
$('#form1').submit();
}
</script>
<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form  modelAttribute="filtro"  method="POST" action="/scc/user/repasse/pre/creditos/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="seqCredito" id="seqCredito"/>
<form:hidden path="seqArquivo" id="seqArquivo"/>


<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_1}">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table  name="sessionScope._DISPLAY_TAG_SPACE_1"  decorator="com.claro.sccweb.decorator.RelCreditosPrePagoDecorator" pagesize="20"  id="repasses" requestURI="/scc/user/repasse/pre/creditos/execute.scc" class="ui-state-default">
<display:column property="nomeArquivo" title="Nome do Aquivo" />
<display:column property="operadoraLD" title="EOT LD" />
<display:column property="operadoraClaro" title="EOT Claro" />
<display:column property="tipoCredito" title="Tipo de Crédito" />
<display:column property="statusCredito" title="Status Crédito" />
<display:column property="telefone" title="Telefone" />
<display:column property="dataCerdito" title="Dt. do Crédito" />
<display:column property="valorCredito" title="Vlr. Crédito" />
<display:column property="qtChamadasAgrupadas" title="Qt. Chamadas Agrupadas" />
<display:column property="minutosQueimados" title="Min. Queimados" />
<display:column property="minutosAjustados" title="Min. Ajustados" />
</display:table>
</td></tr>
</table>
</c:if>

</br>

<c:if test="${!empty sessionScope._DISPLAY_TAG_SPACE_2}">
<spring:message code="creditos_pre.detalhes"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr><td>
<display:table  style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_2"  decorator="com.claro.sccweb.decorator.RelDetalheCreditoPrePagoDecorator" pagesize="50"  id="repasses" requestURI="/scc/user/repasse/pre/creditos/tab1.scc" class="ui-state-default">
<display:column property="nomeArquivo" title="Nome do Aquivo" />
<display:column property="numeroDestino" title="Número Destino" />
<display:column property="dataChamada" title="Dt. Chamada" />
<display:column property="horaInicioChamada" title="Hora Início" />
<display:column property="nuCDR" title="CDR" />
<display:column property="dataCredito" title="Dt. Crédito" />
<display:column property="valorCredito" title="Vlr. Crédito" />
<display:column property="duracaoTarifada" title="Dur. Tarifada" />
<display:column property="valorBruto" title="Vlr. Bruto" />
</display:table>
</td></tr>
</table>
</c:if>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" >&nbsp;</td>    
    <td colspan="1" align="right"  nowrap="nowrap">
    <input type="button" id="novaButton" name="novaButton"  value=<spring:message code="comum.botao.nova"/> />
    </td>
</tr>
</table>
</form:form>
</div>
</div>

