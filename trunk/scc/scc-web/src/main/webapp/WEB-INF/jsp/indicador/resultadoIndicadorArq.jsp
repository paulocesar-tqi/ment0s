<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css"
	type="text/css" />
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

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
	
	function remover() {
		var r=confirm("Remover?");
		if(r==true){
			
			var dados = $("#form1").serialize();
			
			$.ajax({
				cache:false,
				type: "POST",
				url : "/scc/user/indicador/aging/pre/removerAging.scc",
				
				data: dados,
				success : function(data) {
					alert("funcionou");
					//$("#form1").html(data);
				}
			});
		}
	}


	
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
		
	</ul>
	<form:form modelAttribute="filtro" method="get" action="/scc/user/indicador/resultado/listarArquivos.scc" id="form1">
				
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">

						<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					</td>
				</tr>
			</table>
			<br />

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>
							<display:table style="width:90%" name="requestScope.filtro.listArq" pagesize="20" 
										   id="repasses" 
										   requestURI="${pageContext.request.contextPath}/user/indicador/resultado/listarArquivos.scc"
										   class="ui-state-default" >
								<display:column property="cdEotLd" title="EotLD"/>
								<display:column property="cdRegional" title="Eot Claro"/>
								<display:column property="destFile" title="Arquivo"/>
								<display:column property="stopDate" title="Dt Connect"/>
								<display:column property="coblDate" title="Dt Carga Scc"/>
								
							</display:table>
												 
						</td>
					</tr>
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
