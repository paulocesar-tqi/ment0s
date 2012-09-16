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
$.ajaxSetup({ cache: false }); 
$('#tabs').tabs();
	
$('#novaButton').click(function(){
	$('#operacao').val("new");
	$('#form1').submit();
});

$('#efetivarButton').click(function(){
	$('#operacao').val("efetivar");
	$('#form1').submit();
});



$('[type=radio]').change(function(){
	var valor = $(this).val();
	//alert(valor);
	$.ajax({url: "/scc/user/repasse/pos/consulta/json/mudaStatus/"+valor+".scc"});
	 
});

});
</script>
<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form  modelAttribute="filtro"  method="POST" action="/scc/user/repasse/pos/consulta/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table  name="sessionScope._DISPLAY_TAG_SPACE_1"  pagesize="50"  id="repasses" requestURI="/scc/user/repasse/pos/consulta/tab1.scc" class="ui-state-default">
<display:column property="nuRepasse" title="No. Repasse" />
<display:column property="dataInicial" title="Data Inicial" />
<display:column property="quinzena" title="Quinzena" />
<display:column property="anoMes" title="Ano/Mês" />
<display:column property="operadoraLD" title="Operadora LD" />
<display:column property="operadoraClaro" title="Operadora Claro" />
<display:column property="valorBrutoRepasse" title="Valor de Repasse" />
<display:column property="confirmacao" title="Confirmação" />
<display:column property="ajuste" title="Ajuste" />
</display:table>
</td></tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" >&nbsp;</td>    
    <td colspan="1" align="right"  nowrap="nowrap">
    <input type="button" id="novaButton" name="novaButton"  value=<spring:message code="comum.botao.nova"/> />    
    <input type="button" id="efetivarButton" name="efetivarButton"  value=<spring:message code="comum.botao.efetivar"/> />
    </td>
</tr>
</table>
</form:form>
</div>
</div>

