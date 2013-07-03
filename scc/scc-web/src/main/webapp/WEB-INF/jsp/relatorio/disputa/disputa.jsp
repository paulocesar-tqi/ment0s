<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui.css" type="text/css"/>
 <script type="text/javascript" src="<c:url value="/js/jquery-1.8.2.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery-ui.js"/>"></script>
 <script type="text/javascript" src="<c:url value="/js/jquery.ui.datepicker-pt-BR.js"/>"></script>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<script>
$(document).ready(function(){		
	$('#excel_button').click(excel);	
	$('#pesquisar_button').click(pesquisar);	
		
	$('#tabs').tabs();
});

$(function() {
    $( "#tabs" ).tabs();
});


$(function() {
	$.datepicker.setDefaults( $.datepicker.regional[ "pt-BR" ] );
	$( ".datepicker" ).datepicker();
});	


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
	$('#form1').submit();
}

function salvar() {
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/disputa/cadastro/salvarEntity.scc",
			
			data: dados,
			success : function(data) {
				
				//$("#form1").html(data);
			}
		});
	}
}

$('body').delegate('.edit_disputa', 'click', function() {

		$.ajax({
			cache:false,
			type: "GET",
			dataType:"json",
			contentType: "application/json; charset=utf-8",
			url : this.href,
			success : function(data) {				
				atribuirValor(data);
				$('#tabs').tabs('select',1);
			}
		});

	return false;
});


function atribuirValor(data){
	$('#sqDisputa').attr('value', data.sqDisputa);	
	$('#cdEot').attr('value', data.sccOperadora.cdEot);
	$('#dataEvento').attr('value', formatarDataPtBr(data.dtEvento));
	$('#cdStatusDisputa').attr('value', data.cdStatusDisputa);
	$('#dtProtocoloLd').attr('value', formatarDataPtBr(data.dtProtocoloLd));
	$('#txComentario').attr('value', data.txComentario);
	$('#txContestacao').attr('value', data.txContestacao);
	$('#cdIdentificacaoCartaLd').attr('value', data.cdIdentificacaoCartaLd);
	$('#noResponsavelClaro').attr('value', data.noResponsavelClaro);
	$('#noResponsavelLd').attr('value', data.noResponsavelLd);
	$('#vlContestado').attr('value', data.vlContestado);
	$('#dtRepasseContestacao').attr('value', formatarDataPtBr(data.dtRepasseContestacao));
	$('#dtPrazoContestacao').attr('value', formatarDataPtBr(data.dtPrazoContestacao));
	$('#dtPrazoResposta').attr('value', formatarDataPtBr(data.dtPrazoResposta));
	$('#dtAnalise').attr('value', formatarDataPtBr(data.dtAnalise));	
	$('#inRiscoDisputa').attr('value', data.inRiscoDisputa);
	$('#txAnalise').attr('value', formatarDataPtBr(data.txAnalise));
	$('#dtAprovacao').attr('value', formatarDataPtBr(data.dtAprovacao));
	$('#noResponsavelAprovacao').attr('value', data.noResponsavelAprovacao);
	$('#vlProposto').attr('value', data.vlProposto);
	$('#dtProvisao').attr('value', formatarDataPtBr(data.dtProvisao));
	$('#vlProvisao').attr('value', data.vlProvisao);
	$('#dtProtocoloClaro').attr('value', formatarDataPtBr(data.dtProtocoloClaro));
	$('#cdIdentificacaoCartaClaro').attr('value', data.cdIdentificacaoCartaClaro);
	$('#txResposta').attr('value', data.txResposta);
	$('#txConciliacao').attr('value', data.txConciliacao);
	$('#dtConflito').attr('value', formatarDataPtBr(data.dtConflito));
	$('#dtPrazoConflito').attr('value', formatarDataPtBr(data.dtPrazoConflito));
	$('#dtReclamacaoAnatel').attr('value', formatarDataPtBr(data.dtReclamacaoAnatel));
	$('#dtPrevisaoConclusaoRa').attr('value', formatarDataPtBr(data.dtPrevisaoConclusaoRa));
	$('#dtAcaoJudicial').attr('value', formatarDataPtBr(data.dtAcaoJudicial));
	$('#dtPrevisaoConclusaoAj').attr('value', formatarDataPtBr(data.dtPrevisaoConclusaoAj));
	$('#dtAcordo').attr('value', formatarDataPtBr(data.dtAcordo));
	$('#cdIdentificacaoAtaAcordo').attr('value', data.cdIdentificacaoAtaAcordo);
	$('#vlAcordo').attr('value', data.vlAcordo);
	$('#qtParcelasAcordo').attr('value', data.qtParcelasAcordo);
	$('#dtPagamentoAcordo').attr('value', formatarDataPtBr(data.dtPagamentoAcordo));
	$('#vlDiferencaContestado').attr('value', data.vlDiferencaContestado); 
	$('#dtRepasseDisputa').attr('value', formatarDataPtBr(data.dtRepasseDisputa));
	$('#vlSaldoRepassar').attr('value', data.vlSaldoRepassar);
	$('#dtTermoQuitacao').attr('value', formatarDataPtBr(data.dtTermoQuitacao));
	$('#cdIdentificacaoTrmQuitacao').attr('value', data.cdIdentificacaoTrmQuitacao);
	$('#dtTerminoDisputa').attr('value', formatarDataPtBr(data.dtTerminoDisputa));
	$('#dtCriacao').attr('value', formatarDataPtBr(data.dtCriacao));	


}

function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
}


function nova() {
	var dados = $("#form1").serialize();
	$.ajax({
		cache:false,
		type: "POST",
		url : "/scc/user/disputa/cadastro/novaEntity.scc",
		
		data: dados,
		success : function(data) {
			
			//$("#form1").html(data);
		}
	});
	
}

function atualizar() {
	var r=confirm("Atualizar Registro?");
	if (r==true) {		
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

function cancelar() {
	$('#tabs').tabs('select',1);	
	$('#operacao').val("cancelar");	
	$('#form1').submit();
}


</script>
<html>
	<div id="tabs">
		<ul>
			<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
			<li><a href="#tabs-2"><spring:message code="menu.aba.registro.controle"/></a></li>
			<li><a href="#tabs-3"><spring:message code="menu.aba.fase.projeto"/></a></li>
			<li><a href="#tabs-4"><spring:message code="menu.aba.fase.analise"/></a></li>
			<li><a href="#tabs-5"><spring:message code="menu.aba.fase.resposta"/></a></li>
			<li><a href="#tabs-6"><spring:message code="menu.aba.fase.conciliacao"/></a></li>
			<li><a href="#tabs-7"><spring:message code="menu.aba.fase.repasse"/></a></li>
			<li><a href="#tabs-8"><spring:message code="menu.aba.fase.termino"/></a></li>
		</ul>
		<form:form modelAttribute="filtro" method="post" action="/scc/user/disputa/cadastro/listar.scc" id="form1">
			<form:hidden path="operacao" id="operacao"/>
			<form:hidden path="itemSelecionado" id="itemSelecionado"/>
			<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
			
			
			<div id="tabs-1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.operadorald" /></td>
						<td> <form:select path="filtro.cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.mes" /></td>
						<td> 
							<form:select id="mmCiclo" path="filtro.mes" itemValue="key" itemLabel="label" items="${meses}"/>
						</td>
					</tr>
								
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.ano" /></td>
						<td>
							<form:input path="filtro.ano" size="5" maxlength="4"/>
							<form:errors path="filtro.ano"/> 
						</td>
					</tr>
				
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
				    	<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
					    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						    <input id="pesquisar_button" type="button" value="Pesquisar" />
					    </td>
					</tr>
				</table>
				<br/>
				<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td>                            
							<display:table style="width:90%"  name="requestScope.filtro.listDisputa"   
								pagesize="20"  id="repasses" 
								requestURI="/scc/user/disputa/cadastro/listar.scc" class="ui-state-default">
								<display:column property="sqDisputa" titleKey="relatorio.disputa.displaytag.nrd" style="text-align:right"/>
								<display:column property="sccOperadora.cdEot" titleKey="relatorio.disputa.displaytag.eotld" style="text-align:right"/>
								<display:column property="cdStatusDisputa" titleKey="relatorio.disputa.displaytag.status"/>
								<display:column property="inRiscoDisputa" titleKey="relatorio.disputa.displaytag.risco" style="text-align:right"/>
								<display:column property="dtCriacao" titleKey="relatorio.disputa.displaytag.criado" style="width:10%; text-align:left;"/>/>
								<display:column property="dtAtualizacao" titleKey="relatorio.disputa.displaytag.alterado" style="width:10%; text-align:left;"/>
								<display:column property="cdUsuarioManut" titleKey="relatorio.disputa.displaytag.usuario" style="text-align:right"/>
								<display:column title="Editar"><a href="editarDisputa.scc?sqDisputa=${repasses.sqDisputa}" 
																	class="edit_disputa" >
																	<img  src="/scc/images/editIcon.gif"></a>
								</display:column>
								
							</display:table>
						</td>
					</tr>
				</table>
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
				    	<td colspan="3" class="TdFormularioUp">&nbsp;</td>    
					    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						    <input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					    </td>
					</tr>
				</table>
			</div>
			<div id="tabs-2" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.registro.controle.nr" /></td>
						<td><form:input path="entity.sqDisputa" id="sqDisputa" size="10" maxlength="11"/><form:errors path="entity.sqDisputa"/></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.registro.controle.operadorald" /></td>
						<td> <form:select path="entity.sccOperadora.cdEot" id="cdEot" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.registro.controle.data.evento"/></td>
	    				<td><form:input id="dataEvento" path="entity.dtEvento" />
	    				<form:errors path="entity.dtEvento" /></td>
					</tr>
					
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.registro.controle.status" /></td>
						<td> <form:select id="cdStatusDisputa" path="entity.cdStatusDisputa" items="${status}" itemLabel="label" itemValue="key"></form:select> </td>
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
					
				</table>
			</div>
			<div id="tabs-3" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.ld"/></td>
	    				<td><form:input id="dtProtocoloLd" cssClass="datepicker" path="entity.dtProtocoloLd" />
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.comentario" /></td>
						<td><form:textarea  path="entity.txComentario" id="txComentario"  /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.contestacao" /></td>
						<td><form:textarea  path="entity.txContestacao" id="txContestacao"  /><form:errors path="entity.txContestacao"/></td>
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.ref.carta.ld"/></td>
	    				<td><form:input id="cdIdentificacaoCartaLd" path="entity.cdIdentificacaoCartaLd" />
					</tr>
	
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.responsavel.claro"/></td>
	    				<td><form:input id="noResponsavelClaro" path="entity.noResponsavelClaro" />
	    				
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.responsavel.ld"/></td>
	    				<td><form:input id="noResponsavelLd" path="entity.noResponsavelLd" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.valor.contestado"/></td>
	    				<td><form:input id="vlContestado" path="entity.vlContestado" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.data.repasse.contestacao"/></td>
	    				<td><form:input id="dtRepasseContestacao" cssClass="datepicker" path="entity.dtRepasseContestacao" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.data.prazo.contestacao"/></td>
	    				<td><form:input id="dtPrazoContestacao" cssClass="datepicker" path="entity.dtPrazoContestacao" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.registro.dataprotocolo.data.prazo.resposta"/></td>
	    				<td><form:input id="dtPrazoResposta" cssClass="datepicker" path="entity.dtPrazoResposta" />
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				
				</table>
			
			
			</div>
			<div id="tabs-4" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.data.analise.risco.disputa"/></td>
						<td><form:input id="dtAnalise" cssClass="datepicker" path="entity.dtAnalise" />					
					</tr>
					<tr>
						<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.classificacao.risco.disputa"/></td>
						<td> <form:select path="entity.inRiscoDisputa" id="inRiscoDisputa" items="${riscoDisputa}" itemLabel="label" itemValue="key"></form:select> </td>					
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.fase.analise..comentario.analise" /></td>
						<td><form:textarea  path="entity.txAnalise" id="txAnalise"  /></td>
					</tr>
					<tr >
						<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.data.aprovacao"/></td>
						<td><form:input id="dtAprovacao" cssClass="datepicker" path="entity.dtAprovacao" />					
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.responsavel.aprovacao"/></td>
	    				<td><form:input id="noResponsavelAprovacao" path="entity.noResponsavelAprovacao" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.valor.proposto"/></td>
	    				<td><form:input id="vlProposto" path="entity.vlProposto" />
					</tr>
					<tr>
						<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.data.provisao"/></td>
						<td><form:input id="dtProvisao" cssClass="datepicker" path="entity.dtProvisao" />					
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.analise.valor.provisao"/></td>
	    				<td><form:input id="vlProvisao" path="entity.vlProvisao" />
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
					
					
				</table>			
			
			</div>
			<div id="tabs-5" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.resposta.data.protocolo.carta.claro"/></td>
	    				<td><form:input id="dtProtocoloClaro" cssClass="datepicker" path="entity.dtProtocoloClaro" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.resposta.referencia.carta.claro"/></td>
	    				<td><form:input id="cdIdentificacaoCartaClaro" path="entity.cdIdentificacaoCartaClaro" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.fase.resposta.resposta.claro"  /></td>
						<td><form:textarea  path="entity.txResposta" id="txResposta" rows="15"  /></td>
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>
			
			</div>
			<div id="tabs-6" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.comentario.conciliacao"  /></td>
						<td><form:textarea  path="entity.txConciliacao" id="txConciliacao" rows="15"  /></td>
					</tr>
				
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.data.conflito"/></td>
	    				<td><form:input id="dtConflito" cssClass="datepicker" path="entity.dtConflito" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.data.prazo.conflito"/></td>
	    				<td><form:input id="dtPrazoConflito" cssClass="datepicker" path="entity.dtPrazoConflito" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.data.ra.anatel"/></td>
	    				<td><form:input id="dtReclamacaoAnatel" cssClass="datepicker" path="entity.dtReclamacaoAnatel" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.data.previsao.ra.anatel"/></td>
	    				<td><form:input id="dtPrevisaoConclusaoRa" cssClass="datepicker" path="entity.dtPrevisaoConclusaoRa" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.data.acao.judicial"/></td>
	    				<td><form:input id="dtAcaoJudicial" cssClass="datepicker" path="entity.dtAcaoJudicial" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.previsao.conclusao.acao.judicial"/></td>
	    				<td><form:input id="dtPrevisaoConclusaoAj" cssClass="datepicker" path="entity.dtPrevisaoConclusaoAj" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao..data.acordo"/></td>
	    				<td><form:input id="dtAcordo" cssClass="datepicker" path="entity.dtAcordo" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.referencia.ata.acordo"/></td>
	    				<td><form:input id="cdIdentificacaoAtaAcordo" cssClass="datepicker" path="entity.cdIdentificacaoAtaAcordo" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.valor.acordo"/></td>
	    				<td><form:input id="vlAcordo" path="entity.vlAcordo" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.quantidade.parcela.acordo"/></td>
	    				<td><form:input id="qtParcelasAcordo" cssClass="datepicker" path="entity.qtParcelasAcordo" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.data.pagamento.acordo"/></td>
	    				<td><form:input id="dtPagamentoAcordo" cssClass="datepicker" path="entity.dtPagamentoAcordo" />
					</tr>
					
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.conciliacao.valor.diferencao.contestada"/></td>
	    				<td><form:input id="vlDiferencaContestado" path="entity.vlDiferencaContestado" />
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>
			
			
			</div>
			<div id="tabs-7" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.repasse.data.repasse.disputa"/></td>
	    				<td><form:input id="dtRepasseDisputa" cssClass="datepicker" path="entity.dtRepasseDisputa" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.repasse.valor.acerto.conciliacao"/></td>
	    				<td><form:input id="vlAcertoConciliacao"  path="entity.vlAcertoConciliacao" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.repasse.saldo.repassar.disputa"/></td>
	    				<td><form:input id="vlSaldoRepassar"  path="entity.vlSaldoRepassar" />
					</tr>

					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>
			
			</div>
			<div id="tabs-8" >
				
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.termino.data.assinatura.termo.quitacao"/></td>
	    				<td><form:input id="dtTermoQuitacao" cssClass="datepicker" path="entity.dtTermoQuitacao" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.termino.referencia.termo.quitacao"/></td>
	    				<td><form:input id="cdIdentificacaoTrmQuitacao"  path="entity.cdIdentificacaoTrmQuitacao" />
					</tr>
					<tr>
	    				<td width="10%"><spring:message code="relatorio.disputa.aba.fase.termino.data.termino.disputa"/></td>
	    				<td><form:input id="dtTerminoDisputa" cssClass="datepicker" path="entity.dtTerminoDisputa" />
	    				
					</tr>

					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
				</table>
			
			
			</div>
		
		</form:form>
	</div>
</html>
	
<script>
$(document).ready(function(){
	$('#pesquisar_button').removeAttr('disabled');
	$('#excel_button').removeAttr('disabled');
});


</script>
