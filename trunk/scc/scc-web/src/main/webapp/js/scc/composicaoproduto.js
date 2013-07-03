$(document).ready(function(){	
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);
	$('#salvar_button').click(salvar);		
	$('#atualizar_button').click(atualizar);	
	$('#cancelar_button').click(cancelar);
	$('#tabs').tabs();
});

$('body').delegate('.edit_composicao', 'click', function() {
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

function pesquisar()
{
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

function atribuirValor(data){
	var cdProdutoCobilling = data.sccProdutoCobilling.cdProdutoCobilling;
	var cdItemCobilling = data.sccItemCobilling.cdItemCobilling;
	var cdMotivoRetarifacao = data.cdMotivoRetarifacao;
	$('#selProdutos').attr('value', cdProdutoCobilling);
	$('#cdItemCobilling').attr('value', cdItemCobilling);
	$('#cdMotivoRetarifacao').attr('value', cdMotivoRetarifacao);

}	

