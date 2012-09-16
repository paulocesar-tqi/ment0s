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
	
$('#voltarButton').click(function(){
	$('#form1').submit();
});
});
</script>


<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form  method="GET" action="/scc/user/pos/processados/sumario/data/back.scc" id="form1">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table  name="sessionScope._cdrs_" decorator="com.claro.sccweb.decorator.SccCdrCobillingDecorator" pagesize="50"  id="cdrs" requestURI="/scc/user/pos/processados/lista/pagina.scc" partialList="true" size="sessionScope._cdrs_.fullListSize" class="ui-state-default">
<display:column property="motivoRejeicao" title="Motivo" />	
<display:column property="status" title="Status" />	
<display:column property="dataStatus" title="Data do Status" />	
<display:column property="csp" title="CSP" />	
<display:column property="numeroA" title="A#"  />	
<display:column property="numeroB" title="B#" />	
<display:column property="EOT" title="EOT" />	
<display:column property="EOTExterna" title="EOT Externa" />	
<display:column property="dataChamada" title="Data da Chamada"  />	
<display:column property="horaChamada" title="Hora da Chamada" />	
<display:column property="duracao" title="Duração" />	
<display:column property="valorLiquido" title="Valor Liq." />	
<display:column property="arquivoRetorno" title="Arq. de Retorno" />
</display:table>
</td></tr>
</table>
</div>




<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" >&nbsp;</td>    
    <td colspan="1" align="right"  nowrap="nowrap">
    <input type="button" id="voltarButton" name="voltarButton"  value=<spring:message code="comum.botao.voltar"/> />    
    </td>
</tr>
</table>

</form:form>

</div>


