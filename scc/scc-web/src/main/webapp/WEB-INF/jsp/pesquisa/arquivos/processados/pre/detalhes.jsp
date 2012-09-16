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
$(document).ready(function() {	
$('#tabs').tabs();	
$("#voltar_button").click(voltar);
});


function voltar()
{	
	$('#operacao').val("voltar");	
	$('#form1').submit();
}
</script>
<div id="tabs">
<ul>
<li><a href="#tabs-1">Detalhes</a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<div id="tabs-1">


<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td align="center">                            
<display:table style="width:100%"  name="sessionScope._DISPLAY_TAG_SPACE_2"   pagesize="20"  id="repasses" requestURI="/scc/user/pre/processados/pesquisa/tab1.scc" class="ui-state-default">
<display:column property="row.NO_ARQUIVO" title="Nome do Arquivo" />
<display:column property="row.NO_DIRETORIO_ARQUIVO" title="Diretório do Arquivo" />
<display:column property="row.NO_MAQUINA_ARQUIVO" title="Máquina do Arquivo" />
<display:column property="dataMovimento" title="Data do Movimento" />
<display:column property="row.NO_SISTEMA_RESP" title="Sistema Resp." />
<display:column property="row.NO_PROCESSO_RESP" title="Resp. Processo" />
<display:column property="row.IN_STATUS_PROCESSO" title="Status" />
<display:column property="dataProcesso" title="Dt. Processo" />
<display:column property="row.QT_REGISTROS_ARQUIVO" title="Qtd. Registros" />
<display:column property="row.QT_REGISTROS_PROCESSADOS" title="Qtd. Processados" />
</display:table>
</td></tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="voltar_button" type="button" value="Voltar" />    
    </td>
</tr>
</table>


</div>

</form:form>
</div>