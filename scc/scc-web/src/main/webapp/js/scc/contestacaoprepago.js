$(document).ready(function(){	
	
	$('#excel_button').click(excel);	
		
	$(".button_salvar").click(salvar2);

	$("#pesquisar_button").click(pesquisar);
		
	$('#tabs').tabs();
});

$(function() {
    $( "#tabs" ).tabs();
});


$(function() {
	$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
	$( ".datepicker" ).datepicker();
});	


function salvar2() {
	var dados = $("#form1").serialize();
	$.ajax({
		type: "POST",
		url : "/scc/user/contestacao/pre/cadastro/salvarEntity.scc",
		
		data: dados,
		success : function(data) {
			//alert("funcionou");
			alert("Gravado com sucesso");
			//$("#form1").html(data);
		}
	});
}


function pesquisar2() {

	var formdata = $("#form1").serialize();
	$.ajax({
		type: "POST",
		url : "/scc/user/contestacao/pre/cadastro/listar.scc",
		
		data: formdata,
		success : function(data) {
		
			$("#form1").html(data);
			
		}
	});

}

$('body').delegate('.edit_contestacao', 'click', function() {
	 
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
	var cdAnalistaInputCartaLd = data.cdAnalistaInputCartaLd;
	var dtInputCartaLd = data.dtInputCartaLd;
	var sqContestacaoPrePago = data.sqContestacaoPrePago;
	var opExterna = data.sccOperadora.cdEot;
	var cdIdentificacaoCartaLd = data.cdIdentificacaoCartaLd;
	var dtProtocoloLd = data.dtProtocoloLd;
	var noResponsavelCartaLd = data.noResponsavelCartaLd;
	var dtRepasseContestacao = data.dtRepasseContestacao;
	var vlPleito = data.vlPleito;
	var dsPleito = data.dsPleito;
	var noArquivoLd = data.noArquivoLd;
	var dtProtocoloArquivoLd = data.dtProtocoloArquivoLd;
	var qtChamadasArquivoLd = data.qtChamadasArquivoLd;
	var qtMinutosReaisArquivoLd = data.qtMinutosReaisArquivoLd;
	var qtMinutosArredArquivoLd = data.qtMinutosArredArquivoLd;
	var vlBrutoArquivoLd = data.vlBrutoArquivoLd;
	var dtContestacao = data.dtContestacao;
	var dtPrazoContestacao = data.dtPrazoContestacao;
	var fgContestacaoForaDoPrazo = data.fgContestacaoForaDoPrazo;
	var fgConfirmacaoAnalise = data.fgConfirmacaoAnalise;
	var dtPrazoResposta = data.dtPrazoResposta;
	var fgContestacaoSemResposta = data.fgContestacaoSemResposta;
	var fgConfirmacaoResposta = data.fgConfirmacaoResposta;
	var cdStatusContestacao2 = data.cdStatusContestacao;
	var cdAnalistaDownloadBatimento = data.cdAnalistaDownloadBatimento;
	var dtArquivoBatimento = data.dtArquivoBatimento;
	var qtChamadasArqBatimento = data.qtChamadasArqBatimento;
	var qtMinutosReaisArqBatimento = data.qtMinutosReaisArqBatimento;
	var qtMinutosArredArqBatimento = data.qtMinutosArredArqBatimento;
	var vlBrutoArqBatimento = data.vlBrutoArqBatimento;
	var cdTipoContestacao = data.cdTipoContestacao;

	$('#cdAnalistaInputCartaLd').attr('value',cdAnalistaInputCartaLd);
	$('#dtInputCartaLd').attr('value',formatarDataPtBr(dtInputCartaLd));
	$('#sqContestacaoPrePago').attr('value',sqContestacaoPrePago);
	$('#opExterna').attr('value',opExterna);
	$('#cdIdentificacaoCartaLd').attr('value',cdIdentificacaoCartaLd);
	$('#dtProtocoloLd').attr('value',formatarDataPtBr(dtProtocoloLd));
	$('#noResponsavelCartaLd').attr('value',noResponsavelCartaLd);
	$('#dtRepasseContestacao').attr('value',formatarDataPtBr(dtRepasseContestacao));
	$('#vlPleito').attr('value',vlPleito);
	$('#dsPleito').attr('value',dsPleito);
	$('#noArquivoLd').attr('value',noArquivoLd);
	$('#dtProtocoloArquivoLd').attr('value',formatarDataPtBr(dtProtocoloArquivoLd));
	$('#qtChamadasArquivoLd').attr('value',qtChamadasArquivoLd);
	$('#qtMinutosReaisArquivoLd').attr('value',qtMinutosReaisArquivoLd);
	$('#qtMinutosArredArquivoLd').attr('value',qtMinutosArredArquivoLd);
	$('#vlBrutoArquivoLd').attr('value',vlBrutoArquivoLd);
	$('#dtContestacao').attr('value',formatarDataPtBr(dtContestacao));
	$('#dtPrazoContestacao').attr('value',formatarDataPtBr(dtPrazoContestacao));
	$('#fgContestacaoForaDoPrazo').attr('value',fgContestacaoForaDoPrazo);
	$('#fgConfirmacaoAnalise').attr('value',fgConfirmacaoAnalise);
	$('#dtPrazoResposta').attr('value',formatarDataPtBr(dtPrazoResposta));
	$('#fgContestacaoSemResposta').attr('value',fgContestacaoSemResposta);
	$('#fgConfirmacaoResposta').attr('value',fgConfirmacaoResposta);
	$('#cdStatusContestacao2').attr('value',cdStatusContestacao2);
	$('#cdAnalistaDownloadBatimento').attr('value',cdAnalistaDownloadBatimento);
	$('#dtArquivoBatimento').attr('value',formatarDataPtBr(dtArquivoBatimento));
	$('#qtChamadasArqBatimento').attr('value',qtChamadasArqBatimento);
	$('#qtMinutosReaisArqBatimento').attr('value',qtMinutosReaisArqBatimento);
	$('#qtMinutosArredArqBatimento').attr('value',qtMinutosArredArqBatimento);
	$('#vlBrutoArqBatimento').attr('value',vlBrutoArqBatimento);
	$('#cdTipoContestacao').attr('value',cdTipoContestacao);
}


function excel()
{			
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("excel");
	$('#form1').submit();		
}



function pesquisar()
{
	$('#pesquisar_button').attr('disabled', 'disabled');
	$('#excel_button').attr('disabled', 'disabled');
	$('#operacao').val("pesquisar");	
	$("#form1").submit();	
	
	
}

function salvar() {
	var r=confirm("Salvar?");
	if (r==true) {
		alert("#operacao");
		$('#operacao').val("CRUD.salvar");	
		$('#form1').submit();	
	}
}

function atualizar() {
	var r=confirm("Atualizar Registro?");
	if (r==true) {		
		alert("#operacao");
		$('#operacao').val("CRUD.atualizar");	
		$('#form1').submit();	
	}
}


function editar(linha) {
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("CRUD.editar");	
	$('#salvar_button').hide();
	$('#atualizar_button').show();
	$('#form1').submit();
}

function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}


function cancelar() {
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}
