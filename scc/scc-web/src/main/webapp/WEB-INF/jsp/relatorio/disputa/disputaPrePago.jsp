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
<link rel="stylesheet" href="/scc/css/styles.css" type="text/css">
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


$('body').delegate('.edit_disputa_pre', 'click', function() {

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
	$('#sqDisputaPrePago').attr('value', data.sqDisputaPrePago);	
	$('#cdAnalistaInputCartaLd').attr('value', data.cdAnalistaInputCartaLd);
	$('#cdEot').attr('value', data.sccOperadora.cdEot);	
	$('#cdIdentificacaoCartaLd').attr('value', data.cdIdentificacaoCartaLd);
	$('#dtProtocoloLd').attr('value', formatarDataPtBr(data.dtProtocoloLd));	
	$('#noResponsavelCartaLd').attr('value', data.noResponsavelCartaLd);
	$('#dtRepasseDisputa').attr('value', formatarDataPtBr(data.dtRepasseDisputa));	
	$('#vlPleito').attr('value', data.vlPleito);
	$('#txAnalise').attr('value', data.dsPleito);	
	$('#dtDisputa').attr('value', formatarDataPtBr(data.dtDisputa));
	$('#dtPrazoDisputa').attr('value', formatarDataPtBr(data.dtPrazoDisputa));	
	$('#fgDisputaForaDoPrazo').attr('value', data.fgDisputaForaDoPrazo);
	$('#dtPrazoResposta').attr('value', formatarDataPtBr(data.dtPrazoResposta));	
	$('#fgDisputaSemResposta').attr('value', data.fgDisputaSemResposta);
	$('#cdStatusDisputa').attr('value', data.cdStatusDisputa);	
	$('#cdAnalistaInputCartaClaro').attr('value', data.cdAnalistaInputCartaClaro);
	$('#dtInputCartaClaro').attr('value', formatarDataPtBr(data.dtInputCartaClaro));	
	$('#inRiscoDisputa').attr('value', data.inRiscoDisputa);
	$('#vlPropostoCartaClaro').attr('value', data.vlPropostoCartaClaro);	
	$('#dtProtocoloCartaClaro').attr('value', formatarDataPtBr(data.dtProtocoloCartaClaro));
	$('#cdIdentificacaoCartaClaro').attr('value', data.cdIdentificacaoCartaClaro);	
	$('#vlProvisaoDisputa').attr('value', data.vlProvisaoDisputa);
	$('#dsRespostaCartaClaro').attr('value', data.dsRespostaCartaClaro);	
	$('#txAvaliacaoOperacional').attr('value', data.txAvaliacaoOperacional);
	$('#txAvaliacaoJuridica').attr('value', data.txAvaliacaoJuridica);	
	$('#txAvaliacaoRegulatoria').attr('value', data.txAvaliacaoRegulatoria);
	$('#txComentarioAnalise').attr('value', data.txComentarioAnalise);	
	$('#cdAnalistaInputRa').attr('value', data.cdAnalistaInputRa);
	$('#dtInputRa').attr('value', formatarDataPtBr(data.dtInputRa));	
	$('#fgRaAnatel').attr('value', data.fgRaAnatel);
	$('#dtRaAnatel').attr('value', formatarDataPtBr(data.dtRaAnatel));	
	$('#dtPrevTerminoRaAnatel').attr('value', formatarDataPtBr(data.dtPrevTerminoRaAnatel));
	$('#txComentarioRaAnatel').attr('value', data.txComentarioRaAnatel);	
	$('#cdAnalistaInputAcaoJudic').attr('value', data.cdAnalistaInputAcaoJudic);
	$('#fgAcaoJudicial').attr('value', data.fgAcaoJudicial);	
	$('#dtNotificacaoAcaoJudicial').attr('value', formatarDataPtBr(data.dtNotificacaoAcaoJudicial));
	$('#dtPrevTerminoAcaoJudicial').attr('value', formatarDataPtBr(data.dtPrevTerminoAcaoJudicial));	
	$('#txComentarioAcaoJudicial').attr('value', data.txComentarioAcaoJudicial);
	$('#cdIdentificacaoAtaAcordo').attr('value', data.cdIdentificacaoAtaAcordo);	
	$('#dtAssinaturaAcordo').attr('value', formatarDataPtBr(data.dtAssinaturaAcordo));
	$('#noResponsavelAcordoLd').attr('value', data.noResponsavelAcordoLd);	
	$('#noResponsavelAcordoClaro').attr('value', data.noResponsavelAcordoClaro);
	$('#vlAcordado').attr('value', data.vlAcordado);	
	$('#vlDiferencaVlPleito').attr('value', data.vlDiferencaVlPleito);
	$('#vlDiferencaVlProposto').attr('value', data.vlDiferencaVlProposto);	
	$('#txComentarioConciliacao').attr('value', data.txComentarioConciliacao);
	$('#cdAnalistaInputRepasse').attr('value', data.cdAnalistaInputRepasse);	
	$('#dtRepasseScc').attr('value', formatarDataPtBr(data.dtRepasseScc));
	$('#fgRepasseLiberado').attr('value', data.fgRepasseLiberado);
	$('#dtLiberacaoRepasse').attr('value', formatarDataPtBr(data.dtLiberacaoRepasse));	
	$('#txComentarioRepasse').attr('value', data.txComentarioRepasse);
	$('#cdTipoDisputa').attr('value', data.cdTipoDisputa);
	$('#dtInputCartaLd').attr('value', formatarDataPtBr(data.dtInputCartaLd));

		
}

function formatarDataPtBr(data){
	var currentDate = null;
	var dateTimeSplit =data.split(' ');
	var dateSplit = dateTimeSplit[0].split('-');
	currentDate = dateSplit[2] + '/' + dateSplit[1] + '/' + dateSplit[0];
	return currentDate;
	
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
	$('#form1').submit();
}

function salvar() {
	var r=confirm("Salvar?");
	if(r==true){
		
		var dados = $("#form1").serialize();
		
		$.ajax({
			cache:false,
			type: "POST",
			url : "/scc/user/disputa/pre/cadastro/salvarEntity.scc",
			
			data: dados,
			success : function(data) {
				
				//$("#form1").html(data);
			}
		});
	}
}

function nova() {
	var dados = $("#form1").serialize();
	$.ajax({
		cache:false,
		type: "POST",
		url : "/scc/user/disputa/pre/cadastro/novaEntity.scc",
		
		data: dados,
		success : function(data) {
						
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
			<li><a href="#tabs-2"><spring:message code="menu.disputa.pre.aba.registro.controle"/></a></li>
			<li><a href="#tabs-3"><spring:message code="menu.disputa.pre.aba.registro.carta.ld"/></a></li>
			<li><a href="#tabs-4"><spring:message code="menu.disputa.pre.aba.registro.pleito"/></a></li>
			<li><a href="#tabs-5"><spring:message code="menu.disputa.pre.aba.analise.carta.claro"/></a></li>
			<li><a href="#tabs-6"><spring:message code="menu.disputa.pre.aba.analise.avaliacao"/></a></li>
			<li><a href="#tabs-7"><spring:message code="menu.disputa.pre.aba.conciliacao.anatel"/></a></li>
			<li><a href="#tabs-8"><spring:message code="menu.disputa.pre.aba.conciliacao.judicial"/></a></li>
			<li><a href="#tabs-9"><spring:message code="menu.disputa.pre.aba.conciliacao.acordo"/></a></li>
			<li><a href="#tabs-10"><spring:message code="menu.disputa.pre.aba.conciliacao.valores"/></a></li>
			<li><a href="#tabs-11"><spring:message code="menu.disputa.pre.aba.repasse"/></a></li>
			
		</ul>
		<form:form modelAttribute="filtro" method="post" action="/scc/user/disputa/pre/cadastro/listar.scc" id="form1">
			<form:hidden path="operacao" id="operacao"/>
			<form:hidden path="itemSelecionado" id="itemSelecionado"/>
			<form:hidden path="entity.cdTipoDisputa" id="cdTipoDisputa"/>
			
			<div id="tabs-1">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.operadorald" /></td>
						<td> <form:select path="filtro.cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.label.mes" /></td>
						<td> 
							<form:select id="filtro.mes" path="filtro.mes" itemValue="key" itemLabel="label" items="${meses}"/>
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
							<display:table style="width:90%"  name="requestScope.filtro.listDisputaPrePago"   
								pagesize="20"  id="repasses" decorator="com.claro.sccweb.decorator.view.SccDisputaPrePagoDecorator"
								requestURI="/scc/user/disputa/pre/cadastro/listar.scc" class="ui-state-default">
								<display:column property="sqDisputaPrePago"  titleKey="relatorio.disputa.pre.displaytag.nr.disputa" style="text-align:right"/>
								<display:column property="operadora" titleKey="relatorio.disputa.pre.displaytag.operadora.ld" style="text-align:right"/>
								<display:column property="dtRepasseDisputa" titleKey="relatorio.disputa.pre.displaytag.dt.Dem.Repasse.Disputa" format="{0,date,dd-MM-yyyy}"/>
								<display:column property="dtLiberacaoRepasse" titleKey="relatorio.disputa.pre.displaytag.dt.input.liberacao.repasse" format="{0,date,dd-MM-yyyy}" style="text-align:right"/>
								<display:column property="dtLiberacaoRepasse" titleKey="relatorio.disputa.pre.displaytag.dt.liberacao.repasse" format="{0,date,dd-MM-yyyy}" style="width:10%; text-align:left;"/>/>
								<display:column title="Editar"><a href="editarDisputa.scc?sqDisputaPrePago=${repasses.sqDisputaPrePago}" 
																	class="edit_disputa_pre" >
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
			<div id="tabs-2">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase Registro</label> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.usuario.registro" /></td>
						<td><form:input id="cdAnalistaInputCartaLd" path="entity.cdAnalistaInputCartaLd" readonly="true"/></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.data.input.registro" /></td>
						<td><form:input path="entity.dtInputCartaLd" id="dtInputCartaLd" cssClass="datepicker"/>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.nr.disputa.pre" /></td>
						<td><form:input id="sqDisputaPrePago" path="entity.sqDisputaPrePago" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.operadora.ld" /></td>
						<td> <form:select path="entity.sccOperadora.cdEot" id="cdEot" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot"></form:select> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.ref.carta.ld" /></td>
						<td><form:input id="cdIdentificacaoCartaLd" path="entity.cdIdentificacaoCartaLd" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.data.carta.ld" /></td>
						<td><form:input cssClass="datepicker" path="entity.dtProtocoloLd"  id="dtProtocoloLd"/>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.resp.carta.ld" /></td>
						<td><form:input id="noResponsavelCartaLd" path="entity.noResponsavelCartaLd" />
					</tr>
					<tr>
						<td class='label' colspan='3'>
							&nbsp;
						</td>
					</tr>					
					<tr>
						<td class='label' colspan='3' align="center" valign="top">
							<center>
								<input type="button" value="Limpa/Novo" class="button_limpar" onclick="nova()" style="width:90px;"/>
								<input type="button" value="Salvar" class="button_salvar" onclick="salvar()"  style="width:90px;"/>
							</center>
						</td>
					</tr>
					
									
				</table>				
			</div>
			<div id="tabs-3">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase Registro</label> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.data.dem.repasse.disputa" /></td>
						<td><form:input id="dtRepasseDisputa" cssClass="datepicker" path="entity.dtRepasseDisputa" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.valor.pleito" /></td>
						<td><form:input id="vlPleito" path="entity.vlPleito" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.descricao.pleito" /></td>
						<td><form:textarea  path="entity.dsPleito" id="txAnalise"  /></td>
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
			<div id="tabs-4">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase Registro</label> </td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.data.disputa" /></td>
						<td><form:input id="dtDisputa" cssClass="datepicker" path="entity.dtDisputa" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.prazo.disputa" /></td>
						<td><form:input id="dtPrazoDisputa" cssClass="datepicker" path="entity.dtPrazoDisputa" readonly="true" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.fora.prazo" /></td>
						<td><form:checkbox id="fgDisputaForaDoPrazo"  path="entity.fgDisputaForaDoPrazo" value ="S"  /></td>
						
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.prazo.resposta" /></td>
						<td><form:input id="dtPrazoResposta" cssClass="datepicker" path="entity.dtPrazoResposta"  readonly="true"/>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.sem.resposta" /></td>
						<td><form:checkbox  path="entity.fgDisputaSemResposta" value ="S"id="fgDisputaSemResposta"  /></td>
						
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.status.disputa" /></td>
						<td> <form:select id="cdStatusDisputa" path="entity.cdStatusDisputa" items="${statusDisputas}" itemLabel="label" itemValue="key"></form:select> </td>
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
			<div id="tabs-5">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase de Análise</label> </td>
					</tr>

					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.usuario.registro" /></td>
						<td><form:input path="entity.cdAnalistaInputCartaClaro" id="cdAnalistaInputCartaClaro" readonly="true"/></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.data.input.registro" /></td>
						<td><form:label path="entity.dtInputCartaClaro" id="dtInputCartaClaro" cssClass="datepicker" />
					</tr>
					
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.carta.claro.risco.disputa" /></td>
						<td> <form:select id="inRiscoDisputa" path="entity.inRiscoDisputa" items="${riscoDisputa}" itemLabel="label" itemValue="key"></form:select> </td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.carta.claro.valor.proposto" /></td>
						<td><form:input path="entity.vlPropostoCartaClaro" id="vlPropostoCartaClaro" /></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.carta.claro.data.resposta" /></td>
						<td><form:label path="entity.dtProtocoloCartaClaro" id="dtProtocoloCartaClaro" cssClass="datepicker" />
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.carta.claro.ref.carta.claro" /></td>
						<td><form:input path="entity.cdIdentificacaoCartaClaro" id="cdIdentificacaoCartaClaro" /></td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.carta.claro.valor.provisao.disputa" /></td>
						<td><form:input path="entity.vlProvisaoDisputa" id="vlProvisaoDisputa" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.carta.claro.descricao.resposta" /></td>
						<td><form:textarea path="entity.dsRespostaCartaClaro" id="dsRespostaCartaClaro" /></td>
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
			<div id="tabs-6">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.analise.avaliacao.aval.operacional" /></td>
						<td><form:textarea path="entity.txAvaliacaoOperacional" id="txAvaliacaoOperacional" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.analise.avaliacao.aval.juridica" /></td>
						<td><form:textarea path="entity.txAvaliacaoJuridica" id="txAvaliacaoJuridica" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.analise.avaliacao.aval.regulatoria" /></td>
						<td><form:textarea path="entity.txAvaliacaoRegulatoria" id="txAvaliacaoRegulatoria" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.analise.avaliacao.comentario.analise" /></td>
						<td><form:textarea path="entity.txComentarioAnalise" id="txComentarioAnalise" /></td>
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
			<div id="tabs-7">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase de Análise</label> </td>
					</tr>

					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.anatel.usuario.anatel" /></td>
						<td><form:input path="entity.cdAnalistaInputRa" id="cdAnalistaInputRa" readonly="true"/></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.anatel.data.input.ra.anatel" /></td>
						<td><form:input path="entity.dtInputRa" id="dtInputRa" cssClass="datepicker" /></td>
					</tr>
					
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.anatel.ra.anatel" /></td>
						<td><form:checkbox path="entity.fgRaAnatel" value="S" id="fgRaAnatel"  /></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.anatel.data.ra.anatel" /></td>
						<td><form:input path="entity.dtRaAnatel" id="dtRaAnatel" cssClass="datepicker" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.anatel.previsao.ra.anatel" /></td>
						<td><form:input path="entity.dtPrevTerminoRaAnatel" id="dtPrevTerminoRaAnatel" cssClass="datepicker" /></td>
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.anatel.comentario.anatel" /></td>
						<td><form:input path="entity.txComentarioRaAnatel" id="txComentarioRaAnatel" /></td>
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
			
			<div id="tabs-8">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
				
					<tr>
						<td><label for="txtNome" class="SubtituloRed" >Fase de Análise</label> </td>
					</tr>

					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.judicial.usuario.judicial" /></td>
						<td><form:input path="entity.cdAnalistaInputAcaoJudic" id="cdAnalistaInputAcaoJudic" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.judicial.acao.judicial.sim" /></td>
						<td><form:checkbox path="entity.fgAcaoJudicial" value="S" id="fgAcaoJudicial" /></td>
					</tr>
					
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.judicial.dt.acao.judicial" /></td>
						<td><form:input path="entity.dtNotificacaoAcaoJudicial" cssClass="datepicker" id="dtNotificacaoAcaoJudicial"  /></td>

					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.judicial.previsao.acao.judicial" /></td>
						<td><form:input path="entity.dtPrevTerminoAcaoJudicial" id="dtPrevTerminoAcaoJudicial" cssClass="datepicker" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.judicial.comentario.acao.judicial" /></td>
						<td><form:textarea path="entity.txComentarioAcaoJudicial" id="txComentarioAcaoJudicial" /></td>
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
			<div id="tabs-9">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.acordo.ref.ata.acordo" /></td>
						<td><form:textarea path="entity.cdIdentificacaoAtaAcordo" id="cdIdentificacaoAtaAcordo" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.acordo.dt.acordo" /></td>
						<td><form:input path="entity.dtAssinaturaAcordo" id="dtAssinaturaAcordo" cssClass="datepicker" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.acordo.resp.acordo.ld" /></td>
						<td><form:input path="entity.noResponsavelAcordoLd" id="noResponsavelAcordoLd" cssClass="datepicker" /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.acordo.resp.acordo.claro" /></td>
						<td><form:input path="entity.noResponsavelAcordoClaro" id="noResponsavelAcordoClaro" cssClass="datepicker" /></td>
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
			<div id="tabs-10">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.valores.valor.acordado" /></td>
						<td><form:input path="entity.vlAcordado" id="vlAcordado"  /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.valores.dif.acordo.proposto" /></td>
						<td><form:input path="entity.vlDiferencaVlPleito" id="vlDiferencaVlPleito"/></td>
						
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.valores.dif.acordo.proposto" /></td>
						<td><form:input path="entity.vlDiferencaVlProposto" id="vlDiferencaVlProposto"/></td>
						
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.conciliacao.valores..comentario.conciliacao" /></td>
						<td><form:textarea path="entity.txComentarioConciliacao" id="txComentarioConciliacao"/></td>
						
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
			<div id="tabs-11">
				<table width="100%" border="0" cellspacing="0" cellpadding="0" >
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.repasse.usuario.repasse" /></td>
						<td><form:input path="entity.cdAnalistaInputRepasse" id="cdAnalistaInputRepasse"  /></td>
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.repasse.dt.input.repasse" /></td>
						<td><form:input path="entity.dtRepasseScc" id="dtRepasseScc" cssClass="datepicker" /></td>
						
					</tr>
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.repasse.repasse.liberado" /></td>
						<td><form:checkbox path="entity.fgRepasseLiberado" value="S" id="fgRepasseLiberado"/></td>
						
					</tr>
				
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.repasse.dt.dem.repasse.acordo" /></td>
						<td><form:input path="entity.dtLiberacaoRepasse" id="dtLiberacaoRepasse" cssClass="datepicker" /></td>
					</tr>
			
					<tr>
						<td width="15%"><spring:message code="relatorio.disputa.pre.label.aba.repasse.comentario.repasse" /></td>
						<td><form:textarea path="entity.txComentarioRepasse" id="txComentarioRepasse" /></td>
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
