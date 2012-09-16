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

$('#voltarButton').click(function(){
	$('#form1').submit();
	});
	
$('#tabs').tabs();


});




</script>


<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form   modelAttribute="cdr" method="GET" action="/scc/user/pos/processados/lista/back.scc" id="form1">								 
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr><td>
<table  id="detalheCDR" width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.codNatureza"/>:</td>
<td><form:input size="60" readonly="true" path="codNatureza"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.numA"/>:</td>
<td><form:input size="60" readonly="true" path="numA"/>
</td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.numB"/>:</td>
<td><form:input size="60" readonly="true" path="numB"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.dataHoraChamada"/>:</td>
<td><form:input size="60" readonly="true" path="dataHoraChamada"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.areaVisitada"/>:</td>
<td><form:input size="60" readonly="true" path="areaVisitada"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.tipoChamada"/>:</td>
<td><form:input size="60" readonly="true" path="tipoChamada"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.grupoHorario"/>:</td>
<td><form:input size="60" readonly="true" path="grupoHorario"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.valorBruto"/>:</td>
<td><form:input size="60" readonly="true" path="valorBruto"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.duracaoTarifada"/>:</td>
<td><form:input size="60" readonly="true" path="duracaoTarifada"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.CSP"/>:</td>
<td><form:input size="60" readonly="true" path="CSP"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.pais"/>:</td>
<td><form:input size="60" readonly="true" path="pais"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.status"/>:</td>
<td><form:input size="60" readonly="true" path="status"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.dataStatus"/>:</td>
<td><form:input size="60" readonly="true" path="dataStatus"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.arquivoRetorno"/>:</td>
<td><form:input size="60" readonly="true" path="arquivoRetorno"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.codMotivo"/>:</td>
<td><form:input size="60" readonly="true" path="motivoRejeicao"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.erro"/>:</td>
<td><form:input size="60" readonly="true" path="erro"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.ciclo"/>:</td>
<td><form:input size="60" readonly="true" path="ciclo"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.cicloMesAno"/>:</td>
<td><form:input size="60" readonly="true" path="cicloMesAno"/></td>
</tr>


<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.dataEmissaoFatura"/>:</td>
<td><form:input size="60" readonly="true" path="dataEmissaoFatura"/></td>
</tr>


<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.dataVencimentoDatura"/>:</td>
<td><form:input size="60" readonly="true" path="dataVencimentoFatura"/></td>
</tr>

<tr>
<td width="15%"><spring:message code="pesquisa_processados_pos.cdr.numeroNF"/>:</td>
<td><form:input size="60" readonly="true" path="numeroFatura"/></td>
</tr>

</table>

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
</td></tr>
</form:form>


</div>

