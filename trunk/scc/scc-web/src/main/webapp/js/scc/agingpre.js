	$(document).ready(function(){	
		$('#pesquisar_button').click(pesquisar);
		//$(".button_salvar").click(salvar);
		$('#excel_button').click(excel);
		$('#tabs').tabs();
	});

	$(function() {
	    $( "#tabs" ).tabs();
	});

	function salvar() {
		var r=confirm("Salvar?");
		if(r==true){
			
			var dados = $("#form1").serialize();
			
			$.ajax({
				type: "POST",
				url : "/scc/user/indicador/aging/pre/salvarEntity.scc",
				
				data: dados,
				success : function(data) {
					
					//$("#form1").html(data);
				}
			});
		}
	}
	
	function remover() {
		var r=confirm("Remover?");
		if(r==true){
			
			var dados = $("#form1").serialize();
			
			$.ajax({
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

	