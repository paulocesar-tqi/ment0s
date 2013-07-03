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
	$('#pesquisar_button').click(pesquisar);	
	$("#dataInicioRepasse").mask("99/99/9999");	
	$("#dataFimRepasse").mask("99/99/9999");	
	$("#dataFimRepasse" ).datepicker();
	$("#dataInicioRepasse" ).datepicker();
	$('#tabs').tabs();

	$('#dialog').dialog({
		autoOpen: false,
		width: 200,
		buttons: {
			"Ok": function() { 
				$(this).dialog("close"); 
			}
		}
	});

});


function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function download(valor) {
	$('#itemSelecionado').val(valor);
	$('#operacao').val("download");	
	$('#form1').submit();
}

</script>


<div id="tabs">
<ul>
	<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/admin/vum/download/execute.scc" id="form1">
	<form:hidden path="operacao" id="operacao"/>
	<form:hidden path="itemSelecionado" id="itemSelecionado"/>
	<form:hidden path="erro" id="erro"/>
<div id="tabs-1">
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="15%"><spring:message code="solicitacao_vum.ld"/>:</td>
			<td><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
		</tr>
		<tr>
			<td width="15%"><spring:message code="solicitacao_vum.plataforma"/>:</td>
			<td><form:select path="plataforma" id="plataforma" items="${plataformas}" itemLabel="label" itemValue="key" /></td>
		</tr>
		<tr>
			<td width="15%"><spring:message code="solicitacao_vum.tipoArquivo"/>:</td>
		    <td ><form:select path="tipoArquivo" id="tipoArquivo" items="${tipoArquivo}" itemLabel="label" itemValue="key" /></td>
		</tr>
		
		<tr>
			<td width="15%"><spring:message code="solicitacao_vum.dtInicial"/>:</td>
			<td><form:input path="dataInicioRepasse"/><form:errors path="dataInicioRepasse" /></td>
		</tr>
		
		<tr>
			<td width="15%"><spring:message code="solicitacao_vum.dtFinal"/>:</td>
			<td><form:input path="dataFimRepasse"/><form:errors path="dataFimRepasse" /></td>
		</tr>
	
	</table>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
		    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
		    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
		    	<input id="pesquisar_button" type="button" value="Pesquisar" />
		    </td>
		</tr>
	</table>
	<br/>
	
	<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td>                            
				<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/admin/vum/download/tab1.scc" class="ui-state-default">
					<display:column property="nomeArquivoLink" title="Arquivos" style="align:center"/>
				</display:table>
			</td>
		</tr>
	</table>


</div>
</form:form>

</div>
<div id="dialog" title="Erro!">
	<font color="red">Arquivo não existe!</font>
</div>

<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	if ($('#erro').val() != "") {
		$('#dialog').dialog('open');
	}
	
});
</script>


