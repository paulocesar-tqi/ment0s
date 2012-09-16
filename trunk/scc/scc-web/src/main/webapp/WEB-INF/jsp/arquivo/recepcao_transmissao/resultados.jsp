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
	$('#tabs').tabs();
	});

</script>


<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form modelAttribute="filtro"  method="post" action="report.scc" id="form1">
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr><td>
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/recepcao_transmissao/tab1.scc" class="ui-state-default">
<display:column property="dataConnect" title="Dt Connect" />
<display:column property="dataReferencia" title="Dt Refer�ncia" />
<display:column property="dataProcClaro" title="Dt Proc Claro" />
<display:column property="dataProcPPC" title="Dt Proc PPC" />
<display:column property="noComposto" title="Arquivo" />
<display:column property="minutosTarifados" title="Minutos Tarifados" />
<display:column property="qtCDR" title="CDRs" />
<display:column property="valorLiquido" title="Vlr Liquido" />
<display:column property="valorBruto" title="Vlr Bruto" />
<display:column property="status" title="Status Arquivo" />
<display:column property="statusProtocolo" title="Status Protocolo" />
</display:table>
</td></tr>
</table>
</div>



<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" >&nbsp;</td>    
    <td colspan="1" align="right"  nowrap="nowrap">
    <input type="button" id="novaButton" name="novaButton"  value=<spring:message code="comum.botao.nova"/> />
    <input type="button" id="excelButton" name="excelButton" value=<spring:message code="comum.botao.excel"/> />
    </td>
</tr>
</table>
</td></tr>
</form:form>



<script>
$(document).ready(function() {

$("#novaButton").click(function(){
	$("#operation").val("COMMAND_RESET");	
	$('#form1').submit();
});

$("#excelButton").click(function(){
	$("#operation").val("COMMAND_EXCEL");	
	$('#form1').submit();
});


});
</script>
