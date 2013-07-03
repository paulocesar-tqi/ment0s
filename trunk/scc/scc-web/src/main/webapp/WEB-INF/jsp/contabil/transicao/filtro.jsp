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
	$("#dataInicial").mask("99/99/9999");	
	$("#dataFinal").mask("99/99/9999");	
	$("#dataFinal").datepicker();
	$("#dataInicial" ).datepicker();
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


$('body').delegate('.edit_download', 'click', function() {
	 
	$.ajax({
		cache:false,
		type: "GET",
		url : this.href,
		success : function(data) {

		}
	});

return false;
});

function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}


</script>


<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/contabil/transicao/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao"/>
		<form:hidden path="itemSelecionado" id="itemSelecionado"/>
		<form:hidden path="erro" id="erro"/>
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" >
			
			<tr>
			<td width="15%">Tipo de Arquivo:</td>
			<td> <form:select id="tpArquivo" path="cdTipoArquivo" items="${tiposArquivo}" itemLabel="label" itemValue="key"></form:select> </td>
			</tr>
			
			<tr>
			<td width="15%">Data Inicial:</td>
			<td> <form:input id="dataInicial" path="dataInicial"/><form:errors path="dataInicial"></form:errors> </td>
			</tr>
			
			<tr>
			<td width="15%">Data Final:</td>
			<td> <form:input id="dataFinal" path="dataFinal"/><form:errors path="dataFinal"></form:errors> </td>
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
				 <tr><td>                            
					<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" decorator="com.claro.sccweb.decorator.SccArquivoCobillingLinkDecorator"
						   requestURI="${pageContext.request.contextPath}/user/contabil/transicao/listar.scc" class="ui-state-default">
						<display:column property="nomeArquivoLink"  title="Arquivos" style="align:center"/>
				
					</display:table>
				</td></tr>
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
	if ($('#erro').val() != "")
	{
	$('#dialog').dialog('open');
	}
	
});
</script>


