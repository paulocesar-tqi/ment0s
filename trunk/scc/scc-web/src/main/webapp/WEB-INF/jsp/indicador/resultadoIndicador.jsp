<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css"	type="text/css" />

 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmpl.min.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.tmplPlus.js"/>"></script>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script id="template_resultadoIndicadorArq" type="text/x-jquery-tmpl">
	<tr>		
		<td align=right>\${SccResultadoIndicadorView.cdEotLd}</td>         
		<td align=right>\${SccResultadoIndicadorView.cdRegional}</td> 
		<td align=right>\${SccResultadoIndicadorView.destFile}</td>         
		<td align=right>\${SccResultadoIndicadorView.stopDate}</td> 
		<td align=right>\${SccResultadoIndicadorView.coblDate}</td>

		</td>    

	</tr>

</script>


<script>
	$(document).ready(function(){	
		$('#pesquisar_button').click(pesquisar);
		$( "#dataFinal" ).datepicker();
		$( "#dataInicial" ).datepicker();	
		$("#dataFinal").mask("99/99/9999");	
		$("#dataInicial").mask("99/99/9999");
		
		$('#excel_button').click(excel);
		var dsPeriodicidade = $('#periodicidade').val();
		var tipoContrato = $('#tipo').val();
		carregarComboIndicador(dsPeriodicidade,tipoContrato);
		$('#tabs').tabs();
	});

	$(function() {
	    $( "#tabs" ).tabs();
	});


	$('body').delegate('.carregarDescricao', 'change', function() {
		
        var idIndicador = $('#idIndicador').find('option').filter(':selected').text();
        carregarDescricao(idIndicador);		
	});		
	

	function carregarDescricao(idIndicador){
		var idIndicador = $('#idIndicador').find('option').filter(':selected').text();
		$.ajax({
			cache:false,
			type:"GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",		
			url : "/scc/user/indicador/resultado/carregarDescricao.scc?cdIndicador="+idIndicador,			
			success : function(data){
				$('#descricao').attr('value', data.dsIndicador);				
			}
		});

	}
	
	function carregarComboIndicador(dsPeriodicidade, tipoContrato){
			$.ajax({
				cache:false,
				type: "GET",
				dataType:"json",
				contentType: "application/json; charset=utf-8",
				url : "/scc/user/indicador/resultado/carregarIndicadores.scc?dsPeriodicidade="+dsPeriodicidade+"&tipoContrato="+tipoContrato,			
				
				success : function(data) {
			        var options = [];   
			        for (var i = 0; i < data.length; i++){   			        
			            options.push('<option value="' + data[i].cdIndicador + '">' + data[i].cdIndicador + '</option>');   
			        }   
			  
			        // Aqui usamos um recurso bem legal do jQuery, ele retorna o próprio elemento em alguns métodos:   
			        $('#idIndicador').empty().append(options.join('')); // Assim, primeiro limpamos e depois adicionamos os options. 				

			        var idIndicador = $('#idIndicador').find('option').filter(':selected').text();
			        carregarDescricao(idIndicador);
				}
			});
		
	}


	$('body').delegate('.gr_arquivo', 'click', function() {
		 
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : this.href,
			success : function(data) {
				$('#tbl_arq').show();
								
				$('#tbl_resultadoIndicadorArq_body').html($('#template_resultadoIndicadorArq').tmpl(data));
				$('#tabs').tabs('select',1);
				$("#tbl_arq tr:odd").css("background-color", "#E2E4FF");
			}
		});

	return false;
});


	
	$('body').delegate('.carregarComboIndicador', 'change', function() {

		var dsPeriodicidade = $('#periodicidade').val();
		var tipoContrato = $('#tipo').val();
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : "/scc/user/indicador/resultado/carregarIndicadores.scc?dsPeriodicidade="+dsPeriodicidade+"&tipoContrato="+tipoContrato,			
			
			success : function(data) {
		        var options = [];   
		        for (var i = 0; i < data.length; i++){   			        
		            options.push('<option value="' + data[i].cdIndicador + '">' + data[i].cdIndicador + '</option>');   
		            
		        }   
		  
   
		        $('#idIndicador').empty().append(options.join('')); // Primeiro limpar e depois adiciona os options. 				

		        var idIndicador = $('#idIndicador').find('option').filter(':selected').text();
		        carregarDescricao(idIndicador);
		        
			}
		});
		var idIndicador = $('#idIndicador').val();
		carregarDescricao(idIndicador);
		
	});
	


	
	function pesquisar() {
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#excel_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	}

	function excel() {
		$('#operacao').val("excel");
		$('#form1').submit();
	}

</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
		<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/indicador/resultado/listar.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />	
		<form:hidden path="descricao" id="desc" />	
		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
			
				<tr>
					<td width="15%"><spring:message code="relatorio.resultado.label.operadora.claro" /></td>
					<td><form:select path="cdEotClaro" id="cdEotClaro" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
				<tr>
					<td width="15%"><spring:message code="relatorio.resultado.label.operadora.ld" /></td>
					<td><form:select path="cdEotLd" id="cdEotLd" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" /></td>
				</tr>
			
				<tr>
					<td width="15%"><spring:message code="relatorio.resultado.label.periodicidade" /></td>
					<td><form:select path="dsPeriodicidade" id="periodicidade" items="${periodicidades}" itemLabel="label" itemValue="key" cssClass="carregarComboIndicador" /></td>
				</tr>

				<tr>
					<td width="15%"><spring:message code="relatorio.resultado.label.tipo" /></td>
					<td><form:select path="tipoContrato" id="tipo" items="${tipos}" itemLabel="label" itemValue="key"  cssClass="carregarComboIndicador"/></td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.resultado.label.indicador" /></td>
					<td><form:select path="cdIndicador" id="idIndicador"  cssClass="carregarDescricao" >
							<c:forEach var="item" items="${listIndicadores}">
								<form:option value="${item.cdIndicador}"/>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				
				<tr>
					<td width="15%"><spring:message code="relatorio.resultado.label.descricao" /></td>
					<td><form:input path="descricao" id="descricao" /></td>
				</tr>
				
				<tr>
    				<td width="10%"><spring:message code="relatorio.resultado.label.dt.inicial"/></td>
    				<td><form:input id="dataInicial" path="dataInicial" />
    				<form:errors path="dataInicial" /></td>
				</tr>

				<tr>
    				<td width="10%"><spring:message code="relatorio.resultado.label.dt.final"/></td>
    				<td>
    					<form:input id="dataFinal" path="dataFinal" />
    					<form:errors path="dataFinal" />
    				</td>
				</tr>
				
				
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
						<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					</td>
				</tr>
			</table>
			<br />
			<c:if test="${dsPeriodicidade eq DIARIA }">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<display:table style="width:90%" name="requestScope.filtro.listResultadoIndicadorView" pagesize="20" 
										   id="repasses" 
										   requestURI="${pageContext.request.contextPath}/user/indicador/resultado/listar.scc"
										   class="ui-state-default" 
										   >
								
								<display:column title="Aging - dias">
									<a href="listarArquivos.scc?cdEotLd=${repasses.cdEotLd}&cdEotClaro=${repasses.cdEotClaro}&dataInicial=${repasses.dtIniFiltro}&dataFinal=${repasses.dtFimFiltro}"
										class="gr_arquivo">agingDias</a>
								</display:column>
								<display:column property="vlIndicador" title="Resultado(qtde)"/>
							</display:table>
												 
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${dsPeriodicidade eq MENSAL }">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<display:table style="width:90%" name="requestScope.filtro.listResultadoIndicadorViewMensal" pagesize="20" 
										   id="repasses" 
										   requestURI="${pageContext.request.contextPath}/user/indicador/resultado/listar.scc"
										   class="ui-state-default" >
								<display:column property="dtReferenciaStr" title="Data"/>
								<display:column property="vlOrigemIndicador1" title="Origem 1"/>
								<display:column property="vlOrigemIndicador2" title="Origem 2"/>
							</display:table>
												 
						</td>
					</tr>
				</table>
			</c:if>
			
		</div>
		<div id="tabs-2" style="height: 500px;">
			<table id="tbl_arq" style="width: 100%; border: 1px;" bgcolor="white" background="white">
				<td>
					<table id="tbl_arq" style="width: 50%; border: 1px;" class="ui-state-default">
						<thead>
							<tr>
								<th>EOT LD</th>
								<th>EOT CLARO</th>
								<th>Arquivo</th>							
								<th>Dt. Connect</th>
								<th>Dt. Carga SCC</th>													
							</tr>
						</thead>
						<tbody id="tbl_resultadoIndicadorArq_body"></tbody> 
					</table>
				</td>
			
			</table>
			
		</div>
		
	</form:form>
</div>
<script>
	$(document).ready(function(){
		$('#pesquisar_button').removeAttr('disabled');
		$('#excel_button').removeAttr('disabled');
	});
</script>
