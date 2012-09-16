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
	

$('#sumarioDataTable').dataTable( {
	"aoColumns" : [{"fnRender": function(oObj,val)
        {	
			var data = oObj.aData[2];
			return "<a href=/scc/user/pos/processados/lista/data/"+data+".scc>"+oObj.aData[0]+"</>";
        }},null],
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
	"sAjaxSource": '/scc/user/pos/processados/sumario/data/table/json.scc',	
	"sPaginationType": "full_numbers"
} );



});




</script>


<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="comum.resultados"/></a></li>
</ul>
<div id="tabs-1">
<form:form   method="GET" action="/scc/user/pos/processados/sumario/status/back.scc" id="form1">								 
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr><td>
<table  id="sumarioDataTable" width="100%" border="1" cellspacing="0" cellpadding="0" >
<thead> 
<tr>
<th><spring:message code="pesquisa_processados_pos.sumario.data.data"/></th>
<th><spring:message code="pesquisa_processados_pos.sumario.data.quantidade"/></th>
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
    <input type="button" id="voltarButton" name="voltarButton"  value=<spring:message code="comum.botao.voltar"/> />    
    </td>
</tr>
</table>
</td></tr>
</form:form>


</div>

