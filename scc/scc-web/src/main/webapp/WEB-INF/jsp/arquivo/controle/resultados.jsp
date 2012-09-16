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
});
</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1">Resultados</a></li>
</ul>
<div id="tabs-1">
<form:form modelAttribute="operacao"  method="post" action="report.scc" id="form1">
<form:hidden path="operation" id="operation"/>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table id="resultadosControleArquivos" width="100%" border="1" cellspacing="0" cellpadding="0">
				<thead> 
					<tr>
						<th><spring:message code="controle.arquivos.nome_arquivo"/></th>						  
						<th><spring:message code="controle.arquivos.resultado.dtInicio"/></th>
						<th><spring:message code="controle.arquivos.resultado.dtFim"/></th>
						<th><spring:message code="controle.arquivos.resultado.info"/></th>
					</tr>
				</thead>
				<tbody>
					<td></td>
				</tbody>
			</table>
		</td>
	</tr>
</table>


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
</div>

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

$('#resultadosControleArquivos').dataTable( {
	"oLanguage" : {
		"sSearch" : "Filtro:",
		"sInfo": "Mostrando _START_ a _END_ de _TOTAL_ registro(s)",
		"sLengthMenu": "Mostrar _MENU_ registros",
		"sZeroRecords": "Nenhum registro encontrado!",
		"sLoadingRecords" : "Carregando Dados...",
		"sInfoFiltered": "de filtro aplicado em _MAX_ registros",
		"oPaginate" : {
			"sNext" : "Próxima",
			"sPrevious" : "Anterior",
			"sFirst" : "Primeira",
			"sLast" : "Última"
		}
	} ,
	"bProcessing": true,
	"bJQueryUI": true,
	"sAjaxSource": '/scc/user/controle/arquivos/json.scc',
	"sPaginationType": "full_numbers"
} );

});
</script>
