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
	$('#voltar_button').click(voltar)	
	$('#excel_button').click(excel)
	$('#tabs').tabs();
});

function voltar()
{	
	$('#operacao').val("voltarArquivo");	
	$('#form1').submit();
}



function selecionar(linha)
{
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("selecionarCDR");
	$('#form1').submit();
}


function excel(linha)
{
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("excelCDRs");
	$('#form1').submit();
}


</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/pos/processados/pesquisa/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            

<display:table  style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_3" pagesize="50"  id="cdrs" requestURI="/scc/user/pos/processados/pesquisa/pagina.scc" partialList="true" size="sessionScope._DISPLAY_TAG_SPACE_3.fullListSize" class="ui-state-default">
<display:column property="cdMotivo" title="Cod. Motivo" />
<display:column property="status" title="Status" />
<display:column property="dataStatus" title="Data Status" />
<display:column property="CSP" title="CSP" />
<display:column property="numeroA" title="A#" />
<display:column property="numeroB" title="B#" />
<display:column property="EOTOrigem" title="EOT Origem" />
<display:column property="EOTExterna" title="EOT Externa" />
<display:column property="dataChamada" title="Data Chamada" />
<display:column property="horaChamada" title="Hora Chamada" />
<display:column property="duracao" title="Duração" />
<display:column property="valorLiquido" title="Vl. Líquido" />
<display:column property="arquivoRetorno" title="Arquivo Retorno" />
</display:table>
</td></tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">    
    <input id="voltar_button" type="button" value="Voltar" />
    <input id="excel_button" type="button" value="Excel" />
    </td>
</tr>
</table>
</div>
</form:form>
</div>
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');	
});
</script>
