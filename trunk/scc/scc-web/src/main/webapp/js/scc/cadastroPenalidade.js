$(document).ready(function(){		
	$('#salvar_button').hide();
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);	
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#salvar_button').click(salvar);
	$( "#dtFimVigencia" ).datepicker();
	$( "#dtInicioVigencia" ).datepicker();	
	
	$("#peInicioFaixaPenalidade").mask("999.9999");
	$("#peFimFaixaPenalidade").mask("999.9999");
	
	$("#vlFatorCalculoPenalidade").mask("9999999.99");
	
	$("#dtFimVigencia").mask("99/99/9999");	
	$("#dtInicioVigencia").mask("99/99/9999");
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
			cache:false,
			type: "POST",
			url : "/scc/user/admin/admin/penalidade/salvarEntity.scc",
			data: dados,
			success : function(data) {
				$('#salvar_button').hide();					
				$('#form1').submit();
				$('#tabs').tabs('select',0);	
			}
		});
	}
}

function atualizar() {
	
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/admin/penalidade/atualizarEntity.scc",
			data: dados,
			success : function(data) {
				$('#atualizar_button').hide();
				limpar();
				$('#form1').submit();
			
			}
		});
	}
}
$('body').delegate('.delete_grupo', 'click', function() {
	 var r=confirm("Remover?");
	 if(r==true){
		 	var dados = $("#form1").serialize();
			$.ajax({
				cache:false,
				type: "DELETE",
				url : this.href,
				data:dados,				
				success : function(data) {
					$('#form1').submit();
				}
			});
		}
	 return false;
});

$('body').delegate('.edit_entity', 'click', function() {
	 
		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : this.href,
			success : function(data) {
				limparCampos("#tabs-2");
				atribuirValor(data);
				$('#atualizar_button').show();
				$('#salvar_button').hide();	
				$('#tabs').tabs('select',1);
			}
		});

	return false;
});

function atribuirValor(data){
	
	var cdFaixaPenalidade = data.cdFaixaPenalidade;
	var dsFaixaPenalidade = data.dsFaixaPenalidade;
	var inTipoPenalidade = data.inTipoPenalidade;
	var peInicioFaixaPenalidade = data.peInicioFaixaPenalidade;
	var peFimFaixaPenalidade = data.peFimFaixaPenalidade;
	var vlFatorCalculoPenalidade = data.vlFatorCalculoPenalidade;
	var dtInicioVigencia = data.dtInicioVigencia;
	var dtFimVigencia = data.dtFimVigencia;
	
	
	$('#cdFaixaPenalidade').attr('value', cdFaixaPenalidade);
	$('#dsFaixaPenalidade').attr('value', dsFaixaPenalidade);
	$('#inTipoPenalidade').attr('value', inTipoPenalidade);
	$('#peInicioFaixaPenalidade').attr('value', peInicioFaixaPenalidade);
	$('#peFimFaixaPenalidade').attr('value', peFimFaixaPenalidade);
	$('#vlFatorCalculoPenalidade').attr('value', vlFatorCalculoPenalidade);
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(dtInicioVigencia));
	$('#dtFimVigencia').attr('value', formatarDataPtBr(dtFimVigencia));								   
	
}

function limpar(){
	$('#cdFaixaPenalidade').attr('value', "");
	$('#dsFaixaPenalidade').attr('value', "");
	$('#inTipoPenalidade').attr('value', "");
	$('#peInicioFaixaPenalidade').attr('value', "");
	$('#peFimFaixaPenalidade').attr('value', "");
	$('#vlFatorCalculoPenalidade').attr('value', "");
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(""));
	$('#dtFimVigencia').attr('value', formatarDataPtBr(""));								   
	
}


function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}

function limparCampos(container) {
    $(container).find(":input, select").each(function () {
        switch (this.type) {
            case "file":
            case "password":
            case "text":
            case "textarea":
                $(this).val("");
                break;
            case "checkbox":
            case "radio":
                this.checked = false;
        }

        $(this).children("option:selected").removeAttr("selected").end()
               .children("option:first").attr("selected", "selected");
    });
}

function novo() {	
	
	$('#operacao').val("CRUD.novo");	
	$('#form1').submit();

}

function pesquisar() {
	$('#pesquisar_button').attr('disabled', 'disabled');	
	$('#operacao').val("pesquisar");	
	$('#form1').submit();
}

function cancelar() {
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}

function num(dom){
    dom.value=dom.value.replace(/\D/g,""); 
}
