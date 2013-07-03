$(document).ready(function(){		
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);
	$('#atualizar_button').click(atualizar);
	$('#cancelar_button').click(cancelar);
	$('#pesquisar_button').click(pesquisar);	
	$('#salvar_button').click(salvar);
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
			url : "/scc/user/admin/grupo/salvarGrupo.scc",
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
			url : "/scc/user/admin/grupo/atualizarGrupo.scc",
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

$('body').delegate('.edit_grupo', 'click', function() {
	 
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
	
	var sqGrupo = data.sqGrupo;
	var noGrupo = data.noGrupo;
	var dtInicioVigencia = data.dtInicioVigencia;
	var dtCriacao = data.dtCriacao;
	
	$('#sqGrupo').attr('value', sqGrupo);
	$('#descricao').attr('value', noGrupo);
	$('#dtInicioVigencia').attr('value', formatarDataPtBr(dtInicioVigencia));
	$('#dtCriacao').attr('value', formatarDataPtBr(dtCriacao));								   
}

function limpar(){
	$('#sqGrupo').attr('value', "");
	$('#descricao').attr('value', "");
	$('#dtReferencia').attr('value', "");	
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