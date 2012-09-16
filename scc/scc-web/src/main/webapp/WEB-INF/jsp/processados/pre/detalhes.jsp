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
$("#voltarButton").click(voltar);
});
</script>


<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form  method="GET" action="/scc/user/pre/processados/pesquisa/back.scc" id="form1">

<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr><td>
<table  id="tabelaDetalhes" width="100%" border="1" cellspacing="0" cellpadding="0" >
<thead> 
<tr>

<th><spring:message code="pesquisa_processados_pre_detalhes.nomeArquivo"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.nomeDiretorio"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.nomeMaquina"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.dataMovimento"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.respSistema"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.respProcesso"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.statusProcesso"/></th>
<th><spring:message code="pesquisa_processados_pre_detalhes.dataProcesso"/></th>

</tr>
</thead>
<tbody>
<td></td>
</tbody>
</table>
</td></tr>
</table>
</div>




<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" >&nbsp;</td>    
    <td colspan="1" align="right"  nowrap="nowrap">
    <input type="button" id="voltar_button" name="voltar_button"  value=<spring:message code="comum.botao.voltar"/> />    
    </td>
</tr>
</table>
</td></tr>
</form:form>




