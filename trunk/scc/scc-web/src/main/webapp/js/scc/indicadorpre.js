	$(document).ready(function(){	
		$('#pesquisar_button').click(pesquisar);
		//$(".button_salvar").click(salvar);
		$('#excel_button').click(excel);
		$('#tabs').tabs();
	});

	$(function() {
	    $( "#tabs" ).tabs();
	});

	$(function() {
		$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
		$( ".datepicker" ).datepicker();
	});	
	

	

	function salvar() {
		
		var r=confirm("Salvar?");
		if(r==true){
			
			var dados = $("#form1").serialize();
			
			$.ajax({
				type: "POST",
				url : "/scc/user/indicador/indicador/pre/salvarEntity.scc",
				data: dados,
				success : function(data) {
					
					$('#form1').submit();
				
				}
			});
		}
	}


	 $('body').delegate('.delete_indicadores', 'click', function() {
		 var r=confirm("Remover?");
		 if(r==true){
			 	var dados = $("#form1").serialize();
				$.ajax({
					type: "DELETE",
					url : this.href,
					data: dados,
					success : function(data) {
						$('#form1').submit();
					}
				});
			}
		 return false;
	});


	 $('body').delegate('.edit_indicadores', 'click', function() {
		 //var dados = $("#form1").serialize();
			$.ajax({
				type: "GET",
				dataType:"json",
				contentType: "application/json; charset=utf-8",
				//data:dados,
				url : this.href,
				success : function(data) {
					atribuirValor(data);
					$('#tabs').tabs('select',1);
				}
			});

	 	return false;
	});

		function atribuirValor(data){
			var cdIndicador = data.cdIndicador;
			var dsIndicador = data.dsIndicador;
			var cdDwlind    = data.cdDwlind;
			var dsPeriodicidade = data.dsPeriodicidade;
			var cdTipoIndicador = data.cdTipoIndicador;
			var dsFormatoIndicador = data.dsFormatoIndicador;
			var cdStatusIndicador = data.cdStatusIndicador;
			var dtReferencia = data.dtReferencia;
			var cdUsuarioManut = data.cdUsuarioManut;
			var dtAtualizacao = data.dtAtualizacao;
			var dtCriacao = data.dtCriacao;
			var cdUsuarioManut = data.cdUsuarioManut;

			$('#cdIndicador').attr('value', cdIndicador);
			$('#dsIndicador').attr('value', dsIndicador);
			$('#cdDwlind').attr('value', cdDwlind);
			$('#dsPeriodicidade').attr('value', dsPeriodicidade);
			$('#cdTipoIndicador').attr('value', cdTipoIndicador);						
			$('#dsFormatoIndicador').attr('value', dsFormatoIndicador);
			$('#cdStatusIndicador').attr('value', cdStatusIndicador);
			$('#dtCriacao').attr('value', formatarDataPtBr(dtCriacao));
			$('#dtReferencia').attr('value', formatarDataPtBr(dtReferencia));
			$('#cdUsuarioManut').attr('value', cdUsuarioManut);
			
		}

		function limpar(){
			$('#cdIndicador').attr('value', "");
			$('#dsIndicador').attr('value', "");
			$('cdDwlind').attr('value', "");
			//$('#dsPeriodicidade').attr('value', dsPeriodicidade);
			//$('#cdTipoIndicador').attr('value', cdTipoIndicador);						
			//$('#dsFormatoIndicador').attr('value', dsFormatoIndicador);
			//$('#cdStatusIndicador').attr('value', cdStatusIndicador);
			$('#dtReferencia').attr('value', "");
			
			
		}

		function formatarDataPtBr(data){
			var currentDate = null;
			var dateTimeSplit =data.split(' ');
			var dateSplit = dateTimeSplit[0].split('-');
			currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
			return currentDate;
			
		}

	function pesquisarByFiltro() {
		$.ajax({
			type: "POST",
			url : "/scc/user/indicador/indicador/pre/listar.scc",
			
			success : function(data) {
			}
		});
	}

	
	function pesquisar() {
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#excel_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		limpar();
		$('#form1').submit();
	}

	function excel() {
		$('#operacao').val("excel");
		$('#form1').submit();
	}
